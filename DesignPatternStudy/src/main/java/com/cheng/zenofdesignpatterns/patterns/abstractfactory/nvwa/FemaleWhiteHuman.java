package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 白色女性人种
 */
public class FemaleWhiteHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("白人女性");
    }
}
