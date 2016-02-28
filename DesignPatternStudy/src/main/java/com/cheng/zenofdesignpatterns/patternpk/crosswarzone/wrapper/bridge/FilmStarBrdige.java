package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge;

/**
 *
 */
public class FilmStarBrdige extends AbstractStar {

    // 默认的电影明星应该是拍电影
    public FilmStarBrdige() {
        super(new ActFilm());
    }

    // 也可以重新设置一个新职业，君不见明星跑穴的情况时有发生吗
    public FilmStarBrdige(AbstractAction _action) {
        super(_action);
    }

    // 细化电影明星的职责
    @Override
    public void doJob() {
        System.out.println("\n======电影明星的主要工作=====");
        super.doJob();
    }
}
