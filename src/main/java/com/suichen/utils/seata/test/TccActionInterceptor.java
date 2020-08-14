package com.suichen.utils.seata.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TccActionInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        process(methodInvocation::proceed);
        return null;
    }

    private void process(Callback<Object> targetCallBack) {

    }
}
