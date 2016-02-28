package com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa;

/**
 * 黄色人种
 */
public class YellowHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("黄色人种皮肤颜色是黄色的！");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节。");
    }
}
