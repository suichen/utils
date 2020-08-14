package com.suichen.utils.netty;

import io.netty.util.concurrent.*;

public class PromiseTest {
    public static void main(String[] args) {
        EventExecutor executor = new DefaultEventExecutor();
        Promise promise = new DefaultPromise(executor);

        promise.addListener(new GenericFutureListener<Future<Integer>>() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("任务结束，结果: "+future.get());
                } else {
                    System.out.println("任务失败，结果: "+future.cause());
                }
            }
        }).addListener(new GenericFutureListener<Future<Integer>>() {
            @Override
            public void operationComplete(Future future) throws Exception {
                System.out.println("任务结束, balabala....");
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                //promise.setSuccess(123456);
                promise.setFailure(new RuntimeException());
            }
        });

        try {
            promise.sync();
        }catch (InterruptedException e) {

        }
    }
}
