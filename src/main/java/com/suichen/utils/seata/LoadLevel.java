package com.suichen.utils.seata;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LoadLevel {
    String name();
    int order();
    Scope scope() default Scope.SINGLETON;
}
