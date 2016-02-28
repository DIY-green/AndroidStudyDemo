package com.cheng.bigtalkdesignpatterns.visitor;

/**
 * 具体“状态”类
 * 失败
 */
public class Failing extends Action {

    @Override
    public void getManConclusion(Man concreteElementA) {
        System.out.println("时，闷头喝酒，谁也不用劝。");
    }

    @Override
    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println("时，眼泪汪汪，谁也劝不了。");
    }
}
