package com.suichen.utils.seata;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

public class RpcServerBootstrap {

    private final ServerBootstrap serverBootstrap = new ServerBootstrap();
    private final EventLoopGroup eventLoopGroupWorker = null;
    private final EventLoopGroup eventLoopGroupBoss = null;
    private ChannelHandler[] channelHandlers;
    private int listenPort;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    public void start() {
        //boss用来接收进来的连接
        //用来处理已经接收的连接
        //boss这个EventLoopGroup作为一个acceptor负责接收来自客户端的请求,然后分发给worker这个
        //EventLoopGroup来处理所有的事件event和channel的IO。
//       this.serverBootstrap.group(this.eventLoopGroupBoss, this.eventLoopGroupWorker)
//               //传入的是一个Class对象,根据传入的不同的Class对象,实例化不同的Channel
//               .channel(NioServerSocketChannel.class)
//               //option主要是设置TCP连接中的一些可选项 而且这些属性是作用于每一个连接到服务器被
//               //创建的channel
//               .option(ChannelOption.SO_BACKLOG, 1024)
//               .option(ChannelOption.SO_REUSEADDR, true)
//               //这个函数的功能与option函数几乎一样，唯一的区别是该属性设定只作用于
//               //被acceptor(也就是boss EventLoopGroup)接收之后的channel
//               .childOption(ChannelOption.SO_KEEPALIVE, true)
//               .childOption(ChannelOption.TCP_NODELAY, true)
//               .childOption(ChannelOption.SO_SNDBUF, 1024)
//               .childOption(ChannelOption.SO_RCVBUF, 1024)
//               .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK,
//                       new WriteBufferWaterMark(1024, 1024))
//               .localAddress(new InetSocketAddress(listenPort))


    }
}
