package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 黑色人种
 */
public abstract class AbstractBlackHuman implements Human {

    // 黑色人种的皮肤颜色是黑色的
    @Override
    public void getColor() {
        System.out.println("黑色人种的皮肤颜色是黑色的！");
    }

    // 黑色人种讲话
    @Override
    public void talk() {
        System.out.println("黑色人种会讲话，一般人听不懂！");
    }
}
