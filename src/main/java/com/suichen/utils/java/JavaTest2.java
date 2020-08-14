package com.suichen.utils.java;

import com.alibaba.dubbo.config.ApplicationConfig;

import java.lang.reflect.Method;

public class JavaTest2 {
    public static void main(String[] args) throws Exception{
        ApplicationConfig config = new ApplicationConfig();
        config.setName("demo-provider");
        config.setId("demo-provider");

        Class clazz = config.getClass();

        for (Method method:clazz.getMethods()) {
            if (method.getName().endsWith("getName")) {
                if (method.invoke(config, new Object[0]) == null) {
                    System.out.println(config.getName());
                }
            }
        }
    }
}
