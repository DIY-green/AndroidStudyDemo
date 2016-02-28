package com.cheng.zenofdesignpatterns.patterns.mediator.common;

/**
 * 抽象中介者
 */
public abstract class Mediator {

    // 定义同事类
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;

    // 通过getter/setter方法把同事类注入进来
    public ConcreteColleague1 getC1() {
        return c1;
    }
    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }
    public ConcreteColleague2 getC2() {
        return c2;
    }
    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }

    // 中介者模式的业务逻辑
    public abstract void doSomething1();
    public abstract void doSomething2();

    /**
     * 为什么中介者使用getter/setter方法注入同事类？
     * 因为中介者可以只有部分同事类。
     */
}
