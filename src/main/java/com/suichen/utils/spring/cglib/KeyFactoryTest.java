package com.suichen.utils.spring.cglib;

import org.springframework.cglib.core.KeyFactory;

public class KeyFactoryTest {
    public static void main(String[] args) {

        IntStringKey factory = (IntStringKey) KeyFactory.create(IntStringKey.class);
        Object key1 = factory.newInstance(4, "hello");
        Object key2 = factory.newInstance(4, "World");
    }

    private interface IntStringKey {
        public Object newInstance(int i, String s);
    }
}
