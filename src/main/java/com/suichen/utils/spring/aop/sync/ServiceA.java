package com.suichen.utils.spring.aop.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceA implements AInterface{
    @Autowired
    @Lazy
    private ServiceB b;
    @Async
    @Override
    public void doSomething() {
        System.out.println("service a do something....");
    }
}
