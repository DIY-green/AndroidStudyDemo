package com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa;

/**
 * 黑人
 */
public class BlackHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("黑色人种皮肤颜色是黑色的！");
    }

    @Override
    public void talk() {
        System.out.println("黑色人种会说话，一般人听不懂。");
    }
}
