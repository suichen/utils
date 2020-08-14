package com.suichen.utils.spring.aop.log;

import org.aopalliance.intercept.MethodInvocation;

public interface LogProcessor {
    Object log(MethodInvocation invocation) throws Throwable;
}
