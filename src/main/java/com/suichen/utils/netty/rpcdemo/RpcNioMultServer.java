package com.suichen.utils.netty.rpcdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RpcNioMultServer {
    private Selector selector;


    public byte[] readMsgFromClient(SocketChannel channel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        try {
            //首先读取消息头(自己设计的协议头, 此处时消息体的长度)
            int headCount = channel.read(byteBuffer);
            if (headCount < 0) {
                return null;
            }

            byteBuffer.flip();
            int length = byteBuffer.getInt();
            byteBuffer = ByteBuffer.allocate(length);
            int bodyCount = channel.read(byteBuffer);
            if (bodyCount < 0) {
                return null;
            }
            return byteBuffer.array();
        }catch (Exception e) {
            System.out.println("读取数据异常！");
            e.printStackTrace();
            return null;
        }
    }

    public void listen() {
        System.out.println("服务端启动成功！");
        //轮询访问selector
        while (true) {
            try {
                //当注册的事件到达时,方法返回;否则，该方法会一直阻塞
                selector.select();
                //获得selector中选项中的项的迭代器,选中的项为注册的事件
                Iterator ite = selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = (SelectionKey) ite.next();
                    //删除已选的key，以防重复处理
                    ite.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        //获得和客户端连接的通道
                        SocketChannel channel = server.accept();
                        //设置成非阻塞
                        channel.configureBlocking(false);
                        //在和客户端连接成功后，为了可以接收客户端的信息，需要给通道设置读的权限
                        channel.register(this.selector, SelectionKey.OP_READ);
                    }else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        byte[] bytes = readMsgFromClient(channel);
                        if (bytes!=null && bytes.length > 0) {
                            RpcNioMultServerTask task = new RpcNioMultServerTask(bytes, channel);
                            ThreadPoolUtil.addTask(task);
                        }
                    }
                }
            }catch (Exception e) {

            }
        }
    }
    public void initServer(int port) throws IOException {
        //获取一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //设置通道为非阻塞
        serverChannel.configureBlocking(false);
        //将通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        //获取一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,
        //注册该事件后，当该事件到达后，selector.select()会返回，如果该事件没有到达
        //selector.select()会一直阻塞
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    public static void start() throws IOException {
        RpcNioMultServer server = new RpcNioMultServer();
    }
}
