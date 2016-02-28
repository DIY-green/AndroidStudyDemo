package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 生产双性的八卦炉
 */
public class AndrogyneFactory implements HumanFactory {

    // 生产黄人双性
    @Override
    public Human createYellowHuman() {
        return new AndrogyneYellowHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return new AndrogyneWhiteHuman();
    }

    @Override
    public Human createBlackHuman() {
        return new AndrogyneBlackHuman();
    }
}
