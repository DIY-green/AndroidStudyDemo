package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder;

/**
 * 生产蓝图
 */
public class Blueprint {

    // 车轮的要求
    private String wheel;
    // 引擎的要求
    private String engine;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

}
