package com.suichen.utils.spring.lock;

public interface LockManager {
    void executeWithLock(Runnable task);
}
