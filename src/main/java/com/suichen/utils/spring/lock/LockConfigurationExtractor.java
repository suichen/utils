package com.suichen.utils.spring.lock;

import java.util.Optional;

public interface LockConfigurationExtractor {
    Optional<LockConfiguration> getLockConfiguration(Runnable task);
}
