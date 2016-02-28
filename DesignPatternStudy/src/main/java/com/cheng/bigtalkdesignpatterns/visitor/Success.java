package com.cheng.bigtalkdesignpatterns.visitor;

/**
 * 具体“状态”类
 * 成功
 */
public class Success extends Action {

    @Override
    public void getManConclusion(Man concreteElementA) {
        System.out.println("时，背后多半有一个伟大的女人。");
    }

    @Override
    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println("时，背后大多数有一个不成功的男人");
    }
}
