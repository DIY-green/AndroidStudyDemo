package com.cheng.bigtalkdesignpatterns.bridge;

/**
 * （抽象）
 */
public class Abstraction {

    protected Implementor implementor;

    public void setImplementor(Implementor _implementor) {
        this.implementor = _implementor;
    }

    public void operation() {
        implementor.operation();
    }
}
