package com.suichen.utils.spring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ApolloJsonValueProcessor extends ApolloProcessor implements BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;
    @Override
    protected void processField(Object bean, String beanName, Field field) {

    }

    @Override
    protected void processMethod(Object bean, String beanName, Method method) {
        return;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }
}
