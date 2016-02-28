package com.cheng.bigtalkdesignpatterns.visitor;

/**
 * 人的抽象类
 */
public abstract class Person {
    // 接受
    public abstract void accept(Action visitor);
}
