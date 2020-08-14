package com.suichen.utils.spring.openapi.framework;

/**
 * Created by Think on 2017/8/6.
 */
public @interface OpenApiParams {
    String value();
    boolean required() default true;
    ParamOrigin origin() default ParamOrigin.request;
}
