package com.suichen.utils.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxy implements CreateProxy{
    private Class<?>[] interfaces;
    private Class<?> proxyClass;
    private InvocationHandler invocationHandler;
    private String proxyClassName;
    private Constructor<?> proxyConstructor;

    public JdkProxy(Class<?> clazz, InvocationHandler invocationHandler) throws Exception{
        this.invocationHandler = invocationHandler;
        this.interfaces = clazz.getInterfaces();
        this.proxyClass = Proxy.getProxyClass(this.getClass().getClassLoader(), this.interfaces);
        this.proxyClassName = this.proxyClass.getName();
        this.proxyConstructor = this.proxyClass.getConstructor(invocationHandler.getClass());
    }

    @Override
    public Object newInstance() throws Exception{
        return this.proxyConstructor.newInstance(this.invocationHandler);
    }

    @Override
    public Class<?> getProxy() {
        return this.proxyClass;
    }
}
