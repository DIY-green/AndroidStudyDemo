package com.cheng.bigtalkdesignpatterns.mediator;

/**
 * 抽象中介者类
 */
public abstract class Mediator {
    public abstract void send(String msg, Colleague _colleague);
}
