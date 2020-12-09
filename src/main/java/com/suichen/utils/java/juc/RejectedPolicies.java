package com.suichen.utils.java.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * seata 线程池自定义的拒绝策略
 */
public final class RejectedPolicies {

    public static RejectedExecutionHandler runOldestTaskPolicy() {
        return (r, executor) -> {
            if (executor.isShutdown()) {
                return;
            }

            BlockingQueue<Runnable> workQueue = executor.getQueue();

            Runnable firstWork = workQueue.poll();
            boolean newAdded = workQueue.offer(r);

            if (firstWork!=null) {
                firstWork.run();
            }

            if (!newAdded) {
                executor.execute(r);
            }
        };
    }
}
