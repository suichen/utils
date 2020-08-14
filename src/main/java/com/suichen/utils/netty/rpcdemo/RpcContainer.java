package com.suichen.utils.netty.rpcdemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RpcContainer {
    private static ConcurrentHashMap<Long, byte[]> responseContainer
            = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, RpcResponseFuture> requestFuture =
            new ConcurrentHashMap<>();

    private volatile static AtomicLong requestId = new AtomicLong(0);

    public static Long getReuestId() {
        return requestId.getAndIncrement();
    }

    public static void addResponse(Long requestId, byte[] responseBytes) {
        responseContainer.put(requestId, responseBytes);
        RpcResponseFuture responseFuture = requestFuture.get(requestId);
        responseFuture.rpcIsDone();
    }

    public static byte[] getResponse(Long requestId) {
        return responseContainer.get(requestId);
    }

    public static void addRequestFuture(RpcResponseFuture rpcResponseFuture) {
        requestFuture.put(rpcResponseFuture.getRequestId(), rpcResponseFuture);
    }

    public static RpcResponseFuture getRpcRequestFuture(Long requestId) {
        return requestFuture.get(requestId);
    }

    public static void removeResponseAndFuture(Long requestId) {
        responseContainer.remove(requestId);
        requestFuture.remove(requestId);
    }























}
