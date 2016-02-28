package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 黑色女性人种
 */
public class FemaleBlackHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("黑人女性");
    }
}
