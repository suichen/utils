package com.suichen.utils.spring.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class DefaultLockingTaskExecutor implements LockingTaskExecutor{
    private static final Logger logger = LoggerFactory.getLogger(DefaultLockingTaskExecutor.class);
    private final LockProvider lockProvider;

    public DefaultLockingTaskExecutor(LockProvider lockProvider) {
        this.lockProvider = requireNonNull(lockProvider);
    }

    @Override
    public void executeWithLock(Runnable task, LockConfiguration lockConfig) {
        try {
            executeWithLock((Task) task::run, lockConfig);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            // Should not happen
            throw new IllegalStateException(throwable);
        }
    }

    @Override
    public void executeWithLock(Task task, LockConfiguration lockConfig) throws Throwable{
        Optional<SimpleLock> lock = lockProvider.lock(lockConfig);
        if (lock.isPresent()) {
            try {
                logger.debug("Locked {}.", lockConfig.getName());
                task.call();
            } finally {
                lock.get().unlock();
                logger.debug("Unlocked {}.", lockConfig.getName());
            }
        } else {
            logger.debug("Not executing {}. It's locked.", lockConfig.getName());
        }
    }
}
