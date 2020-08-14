package com.suichen.utils.spring.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.util.StringValueResolver;

import java.time.temporal.TemporalAmount;
import java.util.Optional;

public class SpringLockConfigurationExtractor implements LockConfigurationExtractor{
    private final TemporalAmount defaultLockAtMostFor;
    private final TemporalAmount defaultLockAtLeastFor;
    private final StringValueResolver embeddedValueResolver;

    private final Logger logger = LoggerFactory.getLogger(SpringLockConfigurationExtractor.class);

    public SpringLockConfigurationExtractor(TemporalAmount defaultLockAtMostFor, TemporalAmount defaultLockAtLeastFor, StringValueResolver embeddedValueResolver) {
        this.defaultLockAtMostFor = defaultLockAtMostFor;
        this.defaultLockAtLeastFor = defaultLockAtLeastFor;
        this.embeddedValueResolver = embeddedValueResolver;
    }


    @Override
    public Optional<LockConfiguration> getLockConfiguration(Runnable task) {

        if (task instanceof ScheduledMethodRunnable) {
            ScheduledMethodRunnable runnable = (ScheduledMethodRunnable)task;

        }
        return Optional.empty();
    }


























}
