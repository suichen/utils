package com.suichen.utils.project.nettyrpc.client;

import com.suichen.utils.project.nettyrpc.client.proxy.IAsyncObjectProxy;
import com.suichen.utils.project.nettyrpc.client.proxy.ObjectProxy;
import com.suichen.utils.project.nettyrpc.registry.ServiceDiscovery;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcClient {
    private String serviceAddress;
    private ServiceDiscovery serviceDiscovery;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16,16,600L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

    public RpcClient(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public void stop() {
        threadPoolExecutor.shutdown();
        ConnectMessage.getInstance().stop();
    }
    public static void submit(Runnable task) {
        threadPoolExecutor.submit(task);
    }
    public static <T>IAsyncObjectProxy createAsync(Class<T> interfaceClass) {
        return new ObjectProxy<T>(interfaceClass);
    }

    public static <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                                    new Class<?>[]{interfaceClass},
                                        new ObjectProxy<T>(interfaceClass));
    }
}
