package com.suichen.utils.project.nettyrpc.client;

import com.suichen.utils.project.nettyrpc.protocol.RpcRequest;
import com.suichen.utils.project.nettyrpc.protocol.RpcResponse;
import io.netty.handler.timeout.TimeoutException;
//import org.apache.ibatis.executor.ExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class RPCFuture implements Future<Object> {
    private static final Logger logger = LoggerFactory.getLogger(RPCFuture.class);

    private Sync sync;
    private RpcRequest rpcRequest;
    private RpcResponse rpcResponse;
    private long startTime;
    private long responseTimeThreshold = 5000;

    private List<AsyncRPCCallback> pendingCallbacks = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    public RPCFuture(RpcRequest rpcRequest) {
        this.sync = new Sync();
        this.rpcRequest = rpcRequest;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isDone() {
        return sync.isDone();
    }

    @Override
    public Object get() {
        sync.acquire(-1);
        if (this.rpcResponse!=null) {
            return this.rpcResponse.getResult();
        }else {
            return null;
        }
    }

    @Override
    public Object get(long timeout, TimeUnit unit){
        try {
            boolean success = sync.tryAcquireNanos(-1, unit.toNanos(timeout));
            if (success) {
                if (this.rpcResponse != null) {
                    return this.rpcResponse.getResult();
                } else {
                    return null;
                }
            } else {
                throw new RuntimeException("Timeout exception. Request id: "+this.rpcRequest.getRequestId()
                        +". Request class name: "+this.rpcRequest.getClassName()
                        +".  Request method: "+this.rpcRequest.getMethodName());
            }
        }catch (InterruptedException| TimeoutException e) {
            logger.error("", e);
        }
        return null;
    }

    public void done(RpcResponse response) {

    }
    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public RPCFuture addCallback(AsyncRPCCallback callback) {
        lock.lock();

        try {
            if (isDone()) {
                runCallback(callback);
            }else {
                this.pendingCallbacks.add(callback);
            }
        }finally {
            lock.unlock();
        }

        return this;
    }

    private void runCallback(final AsyncRPCCallback callback) {

    }

    static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1L;
        private final int done = 1;
        private final int pending = 0;

        @Override
        protected boolean tryAcquire(int arg) {
            return getState() == done;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == pending) {
                if (compareAndSetState(pending, done)) {
                    return true;
                } else {
                    return false;
                }
            }else {
                return true;
            }
        }

        public boolean isDone() {
            getState();
            return getState() == done;
        }
    }
}
