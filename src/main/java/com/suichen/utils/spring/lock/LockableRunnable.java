package com.suichen.utils.spring.lock;

import static java.util.Objects.requireNonNull;

public class LockableRunnable implements Runnable{
    private final Runnable task;

    private final LockManager lockManager;

    public LockableRunnable(Runnable task, LockManager lockManager) {
        this.task = requireNonNull(task);
        this.lockManager = requireNonNull(lockManager);
    }

    @Override
    public void run() {
        lockManager.executeWithLock(task);
    }
}
