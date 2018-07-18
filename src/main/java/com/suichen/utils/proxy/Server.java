package com.suichen.utils.proxy;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Server {
    String value() default "";
    boolean proxyClass() default false;
}
