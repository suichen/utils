package com.suichen.utils.spring.aop;

public class DmzService {
    @Override
    public String toString() {
        System.out.println("dmzService toString invoke");
        return "dmzService";
    }

    public void testAop() {
        System.out.println("testAop invoke");
    }
}
