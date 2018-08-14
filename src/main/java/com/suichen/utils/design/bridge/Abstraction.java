package com.suichen.utils.design.bridge;

public abstract class Abstraction {
    private Implementor implementor;

    public Implementor getImplementor() {
        return implementor;
    }

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    protected void operation() {
        implementor.operation();
    }
}
