package com.suichen.utils.design.bridge;

public class ConcreateImplementorA implements Implementor{
    @Override
    public void operation() {
        System.out.println("this is concreteImplementorA's operation....");
    }
}
