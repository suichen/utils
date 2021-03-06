package com.suichen.utils.proxy.test;

public class Point2 implements Chain.Point{
    @Override
    public Object proceed(Chain chain) {
        System.out.println("point 2 before");
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
        System.out.println("point 2 after");
        return result;
    }
}
