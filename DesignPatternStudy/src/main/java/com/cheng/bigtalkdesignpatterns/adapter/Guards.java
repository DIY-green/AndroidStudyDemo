package com.cheng.bigtalkdesignpatterns.adapter;

/**
 * 后卫
 */
public class Guards extends Player {

    public Guards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("后卫" + name + "进攻");
    }

    @Override
    public void defense() {
        System.out.println("后卫" + name + "防守");
    }
}
