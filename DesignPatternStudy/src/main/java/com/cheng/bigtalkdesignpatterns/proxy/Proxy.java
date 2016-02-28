package com.cheng.bigtalkdesignpatterns.proxy;

/**
 * 代理类
 */
public class Proxy implements GiveGift {

    private Pursuit gg;

    public Proxy(SchoolGirl _mm) {
        gg = new Pursuit(_mm);
    }

    @Override
    public String giveDools() {
        return gg.giveDools();
    }

    @Override
    public String giveFlowers() {
        return gg.giveFlowers();
    }

    @Override
    public String giveChocolate() {
        return gg.giveChocolate();
    }
}
