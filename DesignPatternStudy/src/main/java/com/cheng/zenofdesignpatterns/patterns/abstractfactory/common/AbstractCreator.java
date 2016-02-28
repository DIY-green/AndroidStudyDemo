package com.cheng.zenofdesignpatterns.patterns.abstractfactory.common;

/**
 * 抽象的产品类
 */
public abstract class AbstractCreator {

    //创建A产品家族
    public abstract AbstractProductA createProductA();

    //创建B产品家族
    public abstract AbstractProductB createProductB();

    /**
     * 注意
     * 有N个产品族，在抽象工厂类中就应该有N个创建方法
     */

}
