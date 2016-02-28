package com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa;

/**
 * 生产男性的八卦炉
 */
public class MaleFactory implements HumanFactory {

    // 生产黄人男性
    @Override
    public Human createYellowHuman() {
        return new MaleYellowHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return new MaleWhiteHuman();
    }

    @Override
    public Human createBlackHuman() {
        return new MaleBlackHuman();
    }
}
