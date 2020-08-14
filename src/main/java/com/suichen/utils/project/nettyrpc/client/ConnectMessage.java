package com.suichen.utils.project.nettyrpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectMessage {
    private static Logger logger = LoggerFactory.getLogger(ConnectMessage.class);
    private volatile static ConnectMessage connectMessage;

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(16,16,600L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

    private CopyOnWriteArrayList<RpcClientHandler> connectHandlers = new CopyOnWriteArrayList<>();
    private Map<InetSocketAddress, RpcClientHandler> connectedServerNodes = new ConcurrentHashMap<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition connected = lock.newCondition();
    private long connectTimeoutMillis = 6000;
    private AtomicInteger roundRobin = new AtomicInteger(0);
    private volatile boolean isRunning = true;

    private ConnectMessage() {

    }

    public static ConnectMessage getInstance() {
        if (connectMessage == null) {
            synchronized (ConnectMessage.class) {
                if (connectMessage == null) {
                    connectMessage = new ConnectMessage();
                }
            }
        }
        return connectMessage;
    }

    public void updateConnectedServer(List<String> allServerAddress) {
        if (allServerAddress!=null) {
            if (allServerAddress.size() > 0) {
                HashSet<InetSocketAddress> newAllServerNodeSet = new HashSet<>();
                for (int i = 0; i < allServerAddress.size(); i++) {
                    String[] array = allServerAddress.get(i).split(":");
                    if (array.length == 2) {
                        String host = array[0];
                        int port = Integer.parseInt(array[1]);
                        final InetSocketAddress remotePeer = new InetSocketAddress(host, port);
                        newAllServerNodeSet.add(remotePeer);
                    }
                }

                for (final InetSocketAddress serverNodeAddress:newAllServerNodeSet) {
                    if (!connectedServerNodes.keySet().contains(serverNodeAddress)) {
                        connectServerNode(serverNodeAddress);
                    }
                }

                for (int i = 0; i < connectHandlers.size(); i++) {
                    RpcClientHandler connectedServerHandler = connectHandlers.get(i);
                    SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                    if (!newAllServerNodeSet.contains(remotePeer)) {
                        logger.info("Remove invalid server node "+remotePeer);
                        RpcClientHandler handler = connectedServerNodes.get(remotePeer);
                        if (handler!=null) {
                            handler.close();
                        }

                        connectedServerNodes.remove(remotePeer);
                        connectHandlers.remove(connectedServerHandler);
                    }
                }
            }else {
                logger.error("No available server node. All server nodes are down!!!");
                for (final RpcClientHandler connectedServerHandler:connectHandlers) {
                    SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                    RpcClientHandler handler = connectedServerNodes.get(remotePeer);
                    handler.close();
                    connectedServerNodes.remove(connectedServerHandler);
                }

                connectHandlers.clear();
            }
        }
    }

    public void reconnect(final RpcClientHandler handler, final SocketAddress remotePeer) {
        if (handler!=null) {
            connectHandlers.remove(handler);
            connectedServerNodes.remove(handler);
        }
        connectServerNode((InetSocketAddress) remotePeer);
    }
    private void connectServerNode(final InetSocketAddress remotePeer) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Bootstrap b = new Bootstrap();

                b.group(eventLoopGroup)
                        .channel(NioServerSocketChannel.class)
                        .handler(new RpcClientInitializer());

                ChannelFuture channelFuture = b.connect(remotePeer);
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            logger.debug("Successfully connect to remote peer. remote peer = "+remotePeer);
                            RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                            addHandler(handler);
                        }
                    }
                });
            }
        });
    }

    private void addHandler(RpcClientHandler handler) {
        connectHandlers.add(handler);
        InetSocketAddress rempteAddress = (InetSocketAddress) handler.getChannel().remoteAddress();
        connectedServerNodes.put(rempteAddress, handler);
        signalAvailableHandler();
    }

    private void signalAvailableHandler() {
        lock.lock();
        try {
            connected.signalAll();
        }finally {
            lock.unlock();
        }
    }

    private boolean waitingForHandler() throws InterruptedException {
        lock.lock();
        try {
            return connected.await(this.connectTimeoutMillis, TimeUnit.MILLISECONDS);
        }finally {
            lock.unlock();
        }
    }

    public RpcClientHandler chooseHandler() {
        int size = connectHandlers.size();
        while (isRunning && size <= 0) {
            try {
                boolean available = waitingForHandler();
                if (available) {
                    size = connectHandlers.size();
                }
            }catch (InterruptedException e) {
                logger.error("Waiting for available node is interrupted!", e);
                throw new RuntimeException("Cann't connect any servers!", e);
            }
        }

        int index = (roundRobin.getAndAdd(1)+size)%size;
        return connectHandlers.get(index);
    }

    public void stop() {
        isRunning = false;
        for (int i = 0; i < connectHandlers.size(); i++) {
            RpcClientHandler handler = connectHandlers.get(i);
            handler.close();
        }

        signalAvailableHandler();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }
}
