package com.suichen.utils.spring.annotation;

import java.lang.annotation.*;

/**
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Documented
public @interface ApolloJsonValue {

    String value();
}
