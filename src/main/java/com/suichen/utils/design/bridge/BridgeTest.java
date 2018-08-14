package com.suichen.utils.design.bridge;

public class BridgeTest {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction();

        abstraction.setImplementor(new ConcreateImplementorA());
        abstraction.operation();

        abstraction.setImplementor(new ConcreateImplementorB());
        abstraction.operation();
    }
}
