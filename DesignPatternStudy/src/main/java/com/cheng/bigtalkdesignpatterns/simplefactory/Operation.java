package com.cheng.bigtalkdesignpatterns.simplefactory;

/**
 * Operation运算类
 */
public abstract class Operation {

    protected double numberA = 0;
    protected double numberB = 0;

    public abstract double getResult();

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }
}
