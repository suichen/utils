package com.suichen.utils.spring.lock;

public interface LockingTaskExecutor {
    void executeWithLock(Runnable task, LockConfiguration lockConfig);

    void executeWithLock(Task task, LockConfiguration lockConfig) throws Throwable;

    @FunctionalInterface
    interface Task {
        void call() throws Throwable;
    }
}
