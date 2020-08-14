package com.suichen.utils.spring.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class DefaultLockManager implements LockManager{
    private static final Logger logger = LoggerFactory.getLogger(DefaultLockManager.class);

    private final LockingTaskExecutor lockingTaskExecutor;
    private final LockConfigurationExtractor lockConfigurationExtractor;

    public DefaultLockManager(LockProvider lockProvider, LockConfigurationExtractor lockConfigurationExtractor) {
        this(new DefaultLockingTaskExecutor(lockProvider), lockConfigurationExtractor);
    }

    public DefaultLockManager(LockingTaskExecutor lockingTaskExecutor, LockConfigurationExtractor lockConfigurationExtractor) {
        this.lockingTaskExecutor = requireNonNull(lockingTaskExecutor);
        this.lockConfigurationExtractor = requireNonNull(lockConfigurationExtractor);
    }

    @Override
    public void executeWithLock(Runnable task) {
        Optional<LockConfiguration> lockConfigOptional = lockConfigurationExtractor.getLockConfiguration(task);
        if (!lockConfigOptional.isPresent()) {
            logger.debug("No lock configuration for {}. Executing without lock.", task);
            task.run();
        } else {
            lockingTaskExecutor.executeWithLock(task, lockConfigOptional.get());
        }
    }
}
