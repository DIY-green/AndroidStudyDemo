package com.cheng.zenofdesignpatterns.patterns.factorymethod.common;

/**
 * 具体产品类
 */
public class ConcreteProduct1 extends Product {

    @Override
    public void method2() {
        // 业务逻辑处理
        System.out.println("ConcreteProduct1==>method2()");
    }
}
