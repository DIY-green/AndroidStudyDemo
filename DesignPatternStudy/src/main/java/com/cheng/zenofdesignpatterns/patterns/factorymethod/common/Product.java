package com.cheng.zenofdesignpatterns.patterns.factorymethod.common;

/**
 * 抽象产品类
 */
public abstract class Product {
    // 产品类的公共方法
    public void method1() {
        // 业务逻辑处理
        System.out.println("Product==>method1()");
    }
    // 抽象方法
    public abstract void method2();
}
