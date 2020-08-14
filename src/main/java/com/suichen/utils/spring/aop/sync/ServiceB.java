package com.suichen.utils.spring.aop.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceB implements BInterface{
    @Autowired
    private ServiceA a;

    @Async
    @Override
    public void doSomething() {
        System.out.println("serviceB do something....");
    }
}
