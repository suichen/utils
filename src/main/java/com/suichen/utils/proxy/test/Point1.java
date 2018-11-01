package com.suichen.utils.proxy.test;

public class Point1 implements Chain.Point{
    @Override
    public Object proceed(Chain chain) {
        System.out.println("point 1 before");
        try {
            Thread.sleep(20);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = chain.proceed();
        try {
            Thread.sleep(20);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("point 1 after");
        return result;
    }
}
