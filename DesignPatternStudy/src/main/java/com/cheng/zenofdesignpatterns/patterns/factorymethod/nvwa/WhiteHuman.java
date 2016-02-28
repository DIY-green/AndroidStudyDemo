package com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa;

/**
 * 白色人种
 */
public class WhiteHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("白色人种皮肤颜色是白色的！");
    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话，一般说的都是单字节。");
    }
}
