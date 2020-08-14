package com.suichen.utils.netty.rpcdemo;

import java.io.Serializable;

public class RequstMultObject implements Serializable {

    private static final long serialVersionUID = 3132836600205356306L;

    private long requestId;

    private Class<?> calzz;

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] args;

    public RequstMultObject(Class<?> calzz, String methodName, Class<?>[] paramTypes, Object[] args) {
        this.calzz = calzz;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.args = args;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Class<?> getCalzz() {
        return calzz;
    }

    public void setCalzz(Class<?> calzz) {
        this.calzz = calzz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
