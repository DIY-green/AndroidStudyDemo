package com.cheng.zenofdesignpatterns.patterns.templatemethod.common;

/**
 * 具体模板类
 */
public class ConcreteClass1 extends AbstractClass {

    @Override
    protected void doSomething() {
        System.out.println("ConcreteClass1==>doSomething()");
    }

    @Override
    protected void doAnything() {
        System.out.println("ConcreteClass1==>doAnything()");
    }
}
