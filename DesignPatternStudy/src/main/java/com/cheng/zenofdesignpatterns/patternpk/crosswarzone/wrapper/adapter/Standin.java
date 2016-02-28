package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter;

/**
 * 替身
 */
public class Standin implements IStarAdapter {

    private IActor actor;

    // 替身是谁
    public Standin(IActor _actor) {
        this.actor = _actor;
    }

    public void act(String context) {
        actor.playact(context);
    }

}
