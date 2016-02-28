package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter;

/**
 *
 */
public class UnknownActor implements IActor {

    //无名演员演戏
    public void playact(String context) {
        System.out.println("无名演员：" + context);
    }

}
