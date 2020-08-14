package com.suichen.utils.spring.lock.aop;

import com.suichen.utils.spring.lock.DefaultLockManager;
import com.suichen.utils.spring.lock.LockProvider;
import com.suichen.utils.spring.lock.SpringLockConfigurationExtractor;
import org.springframework.aop.support.AbstractPointcutAdvisor;

public class SchedulerProxyScheduledLockAopBeanPostProcessor extends AbstractProxyScheduledLockAopBeanPostProcessor{
    public SchedulerProxyScheduledLockAopBeanPostProcessor(String defaultLockAtMostFor, String defaultLockAtLeastFor) {
        super(defaultLockAtMostFor, defaultLockAtLeastFor);
    }

    @Override
    protected AbstractPointcutAdvisor getAdvisor(LockProvider lockProvider) {
        SpringLockConfigurationExtractor lockConfigurationExtractor = new SpringLockConfigurationExtractor(getDefaultLockAtMostFor(), getDefaultLockAtLeastFor(), getResolver());
        return new SchedulerProxyScheduledLockAdvisor(new DefaultLockManager(lockProvider, lockConfigurationExtractor));

    }
}
