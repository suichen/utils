package com.suichen.utils.proxy;

import org.springframework.cglib.proxy.Enhancer;

public class CglibProxy implements CreateProxy{
    private Class<?> proxyClass;
    private Enhancer enhancer;


    @Override
    public Object newInstance() throws Exception {
        return null;
    }

    @Override
    public Class<?> getProxy() {
        return null;
    }
}
