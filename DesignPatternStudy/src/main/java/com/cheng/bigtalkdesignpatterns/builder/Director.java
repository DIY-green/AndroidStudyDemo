package com.cheng.bigtalkdesignpatterns.builder;

/**
 * 指挥者类
 */
public class Director {

    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
