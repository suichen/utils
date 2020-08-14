package com.suichen.utils.spring.openapi.framework;

import org.springframework.http.HttpMethod;

import java.lang.reflect.Method;

/**
 * Created by Think on 2017/7/29.
 */
public class OpenApiMethodInfo {
    private HttpMethod httpMethod;
    private Class<?> clazz;
    private Method method;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
