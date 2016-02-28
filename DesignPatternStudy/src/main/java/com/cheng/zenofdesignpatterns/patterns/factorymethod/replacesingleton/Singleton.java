package com.cheng.zenofdesignpatterns.patterns.factorymethod.replacesingleton;

/**
 * 单例类
 */
public class Singleton {
    // 不允许通过new产生一个对象
    private Singleton() {
    }

    public void doSomething() {
        System.out.println("Singleton==>doSomething()");
    }
}
