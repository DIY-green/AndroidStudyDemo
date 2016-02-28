package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 黄色女性人种
 */
public class FemaleYellowHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("黄人女性");
    }
}
