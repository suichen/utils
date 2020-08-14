package com.suichen.utils.java;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaTest {
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);
    private static List<ListenableFuture<Boolean>> futures = Lists.newArrayList();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            ListenableFuture<Boolean> result = service.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        Thread.sleep(10000);
                        int index = new Random().nextInt();
                        System.out.println(index);
                        if (index > 10) return Boolean.FALSE;
                    }catch (Exception e) {

                    }
                    return Boolean.TRUE;
                }
            });

            Futures.addCallback(result, new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(@Nullable Boolean result) {

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

            futures.add(result);
        }

        final ListenableFuture<List<Boolean>> listenableFuture = Futures.allAsList(futures);

        try {
            List<Boolean> result = listenableFuture.get();
            if (result.contains(Boolean.FALSE)) {
                System.out.println(false);
            }else {
                System.out.println(true);
            }
        }catch (Exception e) {

        }
    }
}
