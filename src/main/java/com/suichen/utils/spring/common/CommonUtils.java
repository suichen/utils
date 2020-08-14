package com.suichen.utils.spring.common;

import org.springframework.util.ClassUtils;

/**
 * Created by Think on 2017/8/6.
 */
public class CommonUtils {
    public static Class loadClass(String className) {
        try {
            return ClassUtils.forName(className, CommonUtils.class.getClassLoader());
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
