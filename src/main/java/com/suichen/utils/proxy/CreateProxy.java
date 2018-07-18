package com.suichen.utils.proxy;

public interface CreateProxy {
    Object newInstance() throws Exception;
    Class<?> getProxy();
}
