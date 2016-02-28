package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator;

/**
 *
 */
public abstract class Decorator implements IStarDecorator {

    // 粉饰的是谁
    private IStarDecorator star;

    public Decorator(IStarDecorator _star) {
        this.star = _star;
    }

    public void act() {
        this.star.act();
    }

}
