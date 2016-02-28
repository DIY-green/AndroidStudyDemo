package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter;

/**
 * 电影明星
 */
public class FilmStar implements IStarAdapter {

    public void act(String context) {
        System.out.println("明星演戏：" + context);
    }

}
