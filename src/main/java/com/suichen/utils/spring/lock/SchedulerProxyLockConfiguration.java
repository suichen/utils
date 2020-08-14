package com.suichen.utils.spring.lock;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class SchedulerProxyLockConfiguration extends AbstractSchedulerLockConfiguration{

}
