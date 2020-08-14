package com.suichen.utils.spring.aop.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String key() default "";
    String desc() default "";
    String logProcessor() default "";
}
