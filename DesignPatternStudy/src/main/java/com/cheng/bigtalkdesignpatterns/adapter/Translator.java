package com.cheng.bigtalkdesignpatterns.adapter;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Translator extends Player {

    private ForeignCenter wjzf = new ForeignCenter();

    public Translator(String name) {
        super(name);
        wjzf.setName(name);
    }

    @Override
    public void attack() {
        wjzf.cAttack();
    }

    @Override
    public void defense() {
        wjzf.cDefense();
    }
}
