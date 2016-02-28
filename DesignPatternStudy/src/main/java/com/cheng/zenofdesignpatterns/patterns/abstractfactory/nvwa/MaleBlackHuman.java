package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 黑色男性人种
 */
public class MaleBlackHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("黑人男性");
    }
}
