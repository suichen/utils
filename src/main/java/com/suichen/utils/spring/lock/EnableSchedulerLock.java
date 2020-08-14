package com.suichen.utils.spring.lock;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SchedulerLockConfigurationSelector.class)
public @interface EnableSchedulerLock {
    enum InterceptMode {
        PROXY_SCHEDULER,
        PROXY_METHOD
    }

    InterceptMode mode() default InterceptMode.PROXY_SCHEDULER;

    String defaultLockAtMostFor();

    String defaultLockAtLeastFor();
}
