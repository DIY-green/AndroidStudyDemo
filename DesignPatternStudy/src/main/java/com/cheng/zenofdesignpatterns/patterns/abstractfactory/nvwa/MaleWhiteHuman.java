package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 白色男性人种
 */
public class MaleWhiteHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("白人男性");
    }
}
