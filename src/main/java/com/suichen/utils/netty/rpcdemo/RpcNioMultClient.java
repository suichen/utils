package com.suichen.utils.netty.rpcdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RpcNioMultClient {
    private static RpcNioMultClient rpcNioClient;
    private Selector selector;
    private SocketChannel channel;
    private String serverId = "localhost";
    private int port = 8080;

    public boolean sendMsg2Server(byte[] bytes) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length+4);

            //放入消息长度，然后放入消息体
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            channel.write(buffer);
        }catch (IOException e) {
            System.out.println("客户端写出消息失败!");
            e.printStackTrace();
        }

        return true;
    }
    public static RpcNioMultClient getInstance() {
        if (rpcNioClient == null) {
            synchronized (RpcNioMultClient.class) {
                if (rpcNioClient == null) {
                    rpcNioClient = new RpcNioMultClient();
                }
            }
        }
        return rpcNioClient;
    }
    public void readMsgFromServer() {
        ByteBuffer byteBuffer;

        try {
            //读取请求id
            byteBuffer = ByteBuffer.allocate(8);
            int readCount = channel.read(byteBuffer);
            if (readCount < 0) {
                return;
            }
            byteBuffer.flip();
            Long requestId = byteBuffer.getLong();

            //读取返回值长度
            byteBuffer = ByteBuffer.allocate(4);
            int readHeadCount = channel.read(byteBuffer);
            if (readCount < 0) {
                return;
            }
            //将buffer切换为待读取状态
            byteBuffer.flip();
            int length = byteBuffer.getInt();

            //读取消息体
            byteBuffer = ByteBuffer.allocate(length);
            int readBodyCount = channel.read(byteBuffer);
            if (readBodyCount < 0) {
                return;
            }
            byte[] bytes = byteBuffer.array();

            RpcContainer.addResponse(requestId, bytes);
        }catch (Exception e) {
            System.out.println("读取数据异常");
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                channel.register(selector, SelectionKey.OP_READ);
                selector.select();
                Iterator ite = selector.selectedKeys().iterator();

                while (ite.hasNext()) {
                    SelectionKey key = (SelectionKey) ite.next();
                    ite.remove();
                    if (key.isReadable()) {
                        readMsgFromServer();
                    }
                }
            }
        }catch (Exception e) {

        }
    }

    public void initClient() {
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.connect(new InetSocketAddress(serverId, port));
            if (channel.isConnectionPending()) {
                channel.finishConnect();
            }
            System.out.println("客户端初始化完成, 建立连接完成");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RpcNioMultClient() {

    }
}
