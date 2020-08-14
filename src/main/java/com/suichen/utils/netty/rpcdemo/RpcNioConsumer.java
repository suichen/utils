package com.suichen.utils.netty.rpcdemo;

public class RpcNioConsumer {

    public static void multipartRpcNio() {
        HelloService proxy = RpcProxyFactory.getMultService(HelloService.class);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = proxy.sayHello("word!");
                }
            };

            Thread t = new Thread(runnable);
            t.start();
        }
    }
    public static void main(String[] args) {

    }
}
