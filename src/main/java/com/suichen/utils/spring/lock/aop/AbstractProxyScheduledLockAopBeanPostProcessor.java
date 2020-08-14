package com.suichen.utils.spring.lock.aop;

import com.suichen.utils.spring.lock.LockProvider;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.time.Duration;

abstract class AbstractProxyScheduledLockAopBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor implements BeanPostProcessor, EmbeddedValueResolverAware, InitializingBean {
    private final String defaultLockAtMostFor;
    private final String defaultLockAtLeastFor;
    private BeanFactory beanFactory;
    private StringValueResolver resolver;
    private LockProvider lockProvider;

    public AbstractProxyScheduledLockAopBeanPostProcessor(String defaultLockAtMostFor, String defaultLockAtLeastFor) {
        this.defaultLockAtMostFor = defaultLockAtMostFor;
        this.defaultLockAtLeastFor = defaultLockAtLeastFor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        this.advisor = getAdvisor(lockProvider);
        return super.postProcessAfterInitialization(bean, beanName);
    }

    protected abstract AbstractPointcutAdvisor getAdvisor(LockProvider lockProvider);
    @Override
    public void afterPropertiesSet() throws Exception {
        if (lockProvider == null) {
            lockProvider = beanFactory.getBean(LockProvider.class);
        }
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private Duration toDuration(String value) {
        return Duration.parse(resolver.resolveStringValue(value));
    }

    public Duration getDefaultLockAtMostFor() {
        return toDuration(defaultLockAtMostFor);
    }

    public Duration getDefaultLockAtLeastFor() {
        return toDuration(defaultLockAtLeastFor);
    }

    public LockProvider getLockProvider() {
        return lockProvider;
    }

    public void setLockProvider(LockProvider lockProvider) {
        this.lockProvider = lockProvider;
    }

    public StringValueResolver getResolver() {
        return resolver;
    }
}
