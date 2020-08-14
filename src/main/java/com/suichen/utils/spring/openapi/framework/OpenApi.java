package com.suichen.utils.spring.openapi.framework;

import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

/**
 * Created by Think on 2017/7/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OpenApi {
    OpenApiType value();
    HttpMethod method() default HttpMethod.GET;
}
