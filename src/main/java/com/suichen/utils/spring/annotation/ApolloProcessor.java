package com.suichen.utils.spring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class ApolloProcessor implements BeanPostProcessor,PriorityOrdered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();

        for (Field field:getAllFields(clazz)) {
            processField(bean, beanName, field);
        }

        for (Method method:getAllMethods(clazz)) {
            processMethod(bean, beanName, method);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> res = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;
    }

    private List<Method> getAllMethods(Class<?> clazz) {
        List<Method> res = new ArrayList<>();

        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                res.add(method);
            }
        });

        return res;
    }

    protected abstract void processField(Object bean, String beanName, Field field);
    protected abstract void processMethod(Object bean, String beanName, Method method);
}
