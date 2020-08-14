package com.suichen.utils.netty.rpcdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RpcResponseFuture {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Long requestId;

    public RpcResponseFuture(long requestId) {
        this.requestId = requestId;
    }

    public byte[] get() {
        byte[] bytes = RpcContainer.getResponse(requestId);
        if (bytes == null || bytes.length < 0) {
            lock.lock();

            try {
                System.out.println("请求id:"+requestId+",请求结果尚未返回, 线程挂起");
                condition.await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        System.out.println("请求id:"+requestId+", 请求结果返回, 线程挂起结束");
        return RpcContainer.getResponse(requestId);
    }

    public void rpcIsDone() {
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
