package com.suichen.utils.guava.concurrency;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestListenFuture {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        ListeningExecutorService service = MoreExecutors
                                            .listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> explosion = service.submit(()->"finished");
        ExecutorService callBackService = Executors.newFixedThreadPool(1);

        Futures.addCallback(explosion, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("success! result = "+result);
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failed! t = "+t);
                latch.countDown();
            }
        }, callBackService);


        try {
            latch.await();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
            callBackService.shutdown();
        }
    }
}
