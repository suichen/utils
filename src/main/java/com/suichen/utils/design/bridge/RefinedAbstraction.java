package com.suichen.utils.design.bridge;

public class RefinedAbstraction extends Abstraction{
    @Override
    protected void operation() {
        super.getImplementor().operation();
    }
}
