package com.cheng.zenofdesignpatterns.patterns.abstractfactory.common;

/**
 * 产品A
 */
public abstract class AbstractProductA {

    //每个产品共有的方法
    public void shareMethod(){
        System.out.println("AbstractProductA==>shareMethod()");
    }

    //每个产品相同方法，不同实现
    public abstract void doSomething();
}
