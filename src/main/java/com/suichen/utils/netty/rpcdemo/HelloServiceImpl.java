package com.suichen.utils.netty.rpcdemo;

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
}
