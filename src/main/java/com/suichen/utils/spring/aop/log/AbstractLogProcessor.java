package com.suichen.utils.spring.aop.log;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

public abstract class AbstractLogProcessor implements LogProcessor{
    /**
     * 获取代理的原始对象
     * @param invocation JDK代理或者CGLIB代理后重写的方法
     * @return 返回invocation方法被代理前的Class类
     */
    protected Class<?> getTargetClass(MethodInvocation invocation) {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()):null);
        return targetClass;
    }

    /**
     * 获取MethodInvocation 重写的原始方法(原始方法上有自定义注解)
     * @param invocation JDK代理或者CGLIB代理后重写的方法
     * @return 重写前的原始方法
     */
    protected Method getSpecificMethod(MethodInvocation invocation) {
        Class<?> clazz = getTargetClass(invocation);
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), clazz);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }

    protected Log getLog(Class<?> targetClass, Method specificMethod) {
        Log log = AnnotationUtils.getAnnotation(specificMethod, Log.class);
        if (log == null) {
            log = AnnotationUtils.findAnnotation(targetClass, Log.class);
        }
        return log;
    }
    protected String getLogDesc(Class<?> targetClass, Method specificMethod) {
        Log log = getLog(targetClass, specificMethod);
        if (log!=null) {
            return log.desc();
        }
        return specificMethod.getName();
    }












}
