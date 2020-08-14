package com.suichen.utils.spring.lock;

import java.util.Optional;

public interface LockProvider {
    Optional<SimpleLock> lock(LockConfiguration lockConfiguration);

}
