package com.suichen.utils.netty.rpcdemo;

import java.io.IOException;

public class RpcNioProvider {

    public static void startMultRpcNioServer() {
        Runnable r = ()->{
            try {
                RpcNioMultServer.start();
            }catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }


    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        BeanContainer.addBean(HelloService.class, helloService);
        startMultRpcNioServer();
    }
}
