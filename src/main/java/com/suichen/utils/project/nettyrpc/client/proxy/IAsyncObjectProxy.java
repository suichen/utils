package com.suichen.utils.project.nettyrpc.client.proxy;

import com.suichen.utils.project.nettyrpc.client.RPCFuture;

public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}
