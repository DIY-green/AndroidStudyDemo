package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 黄色人种
 */
public abstract class AbstractYellowHuman implements Human {

    // 黄色人种的皮肤颜色是黄色的
    @Override
    public void getColor() {
        System.out.println("黄色人种的皮肤颜色是黄色的！");
    }

    // 黄色人种讲话
    @Override
    public void talk() {
        System.out.println("黄色人种会讲话，一般说的都是双字节！");
    }
}
