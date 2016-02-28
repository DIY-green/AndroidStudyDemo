package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 生产女性的八卦炉
 */
public class FemaleFactory implements HumanFactory {

    // 生产黄人女性
    @Override
    public Human createYellowHuman() {
        return new FemaleYellowHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return new FemaleWhiteHuman();
    }

    @Override
    public Human createBlackHuman() {
        return new FemaleBlackHuman();
    }
}
