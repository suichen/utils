package com.suichen.utils.project.nettyrpc.client;

public interface AsyncRPCCallback {
    void success(Object result);
    void fail(Exception e);
}
