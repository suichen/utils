package com.suichen.utils.java.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    semaphore.acquireUninterruptibly(1);

                    try {
                        System.out.println("begin work...");
                    }finally {
                       semaphore.release();
                    }
                }
            });
        }
    }
}
