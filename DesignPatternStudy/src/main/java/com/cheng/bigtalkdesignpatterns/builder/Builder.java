package com.cheng.bigtalkdesignpatterns.builder;

/**
 * 抽象建造者，确定产品由partA和partB两个部件组成，并声明一个得到产品建造后结果的方法getReuslt
 */
public abstract class Builder {
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract Product getResult();
}
