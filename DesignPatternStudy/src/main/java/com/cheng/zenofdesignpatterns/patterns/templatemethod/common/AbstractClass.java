package com.cheng.zenofdesignpatterns.patterns.templatemethod.common;

/**
 * 抽象模板类
 */
public abstract class AbstractClass {
    // 基本方法
    protected abstract void doSomething();
    // 基本方法
    protected abstract void doAnything();
    // 模板方法
    public final void templateMethod() {
        /**
         * 调用基本方法，完成相关的逻辑
         */
        this.doSomething();
        this.doAnything();
        System.out.println("AbstractClass==>templateMethod()");
    }

    /**
     * 基本方法
     * 也叫做基本操作，是由子类实现的方法，并且在模板方法中被调用
     * 模板方法
     * 可以有一个或几个，一般是一个具体方法，也就是一个框架，实现对基本方法的调度，
     * 完成固定的逻辑
     *
     * 注意
     * 为了防止恶意的操作，一般模板方法都加上final关键字，不允许被覆写
     * 抽象模板中的基本方法尽量设计为protected类型，符合迪米特法则，不需要暴露的
     * 属性或方法尽量不要设置为protected类型，实现类若非必要，尽量不要扩大父类中
     * 的访问权限
     */
}
