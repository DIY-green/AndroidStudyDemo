package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge;

/**
 *
 */
public class Singer extends AbstractStar {

    // 默认的歌星明星应该是唱歌
    public Singer() {
        super(new Sing());
    }

    // 也可以重新设置一个新职业
    public Singer(AbstractAction _action) {
        super(_action);
    }

    // 细化歌星明星的职责
    @Override
    public void doJob() {
        System.out.println("\n======歌星明星的主要工作=====");
        super.doJob();
    }

}
