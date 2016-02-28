package com.cheng.zenofdesignpatterns.principle.isp;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Searcher extends AbstractSearcher {

    public Searcher(IGoodBodyGirl _goodBodyGirl) {
        super(_goodBodyGirl);
    }

    public Searcher(IGreatTemperamentGirl _greatTemperamentGirl) {
        super(_greatTemperamentGirl);
    }

    @Override
    public void show() {
        System.out.println("----------美女的信息如下：----------");
        if (goodBodyGirl != null) {
            // 展示面容
            goodBodyGirl.goodLooking();
            // 展示身材
            goodBodyGirl.niceFigure();
        }
        if (greatTemperamentGirl != null) {
            // 展示气质
            greatTemperamentGirl.greatTemperament();
        }
    }
}
