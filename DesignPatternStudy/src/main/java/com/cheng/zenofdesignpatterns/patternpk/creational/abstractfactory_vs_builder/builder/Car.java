package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder;

/**
 * 具体车辆
 */
public class Car implements ICarBuilder {

    // 汽车引擎
    private String engine;
    // 汽车轮子
    private String wheel;

    // 一次性传递汽车需要的信息
    public Car(String _engine, String _wheel) {
        this.engine = _engine;
        this.wheel = _wheel;
    }

    public String getEngine() {
        return engine;
    }

    public String getWheel() {
        return wheel;
    }

    @Override
    public String toString() {
        return "车的轮子是：" + wheel + "\n车的引擎是：" + engine;
    }

}