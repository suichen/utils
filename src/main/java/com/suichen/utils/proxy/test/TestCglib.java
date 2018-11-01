package com.suichen.utils.proxy.test;

public class TestCglib {
    public static void main(String[] args) {
        Object proxy = ProxyFactory.create().getProxy(new SayHello());
        proxy.toString();
    }

    static class SayHello {
        @Override
        public String toString() {
            return "hello cglib";
        }
    }
}
