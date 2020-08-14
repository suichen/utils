package com.suichen.utils.spring.annotation;


import com.suichen.utils.spring.xmlRules.RequestMethod;

import java.lang.annotation.*;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";

    RequestMethod[] method() default {};

}


