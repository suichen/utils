package com.suichen.utils.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before: "+method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("After: "+method.getName());
        return object;
    }
}
