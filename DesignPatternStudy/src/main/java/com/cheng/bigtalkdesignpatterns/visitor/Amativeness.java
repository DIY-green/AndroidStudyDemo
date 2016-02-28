package com.cheng.bigtalkdesignpatterns.visitor;

/**
 * 具体“状态”类
 * 恋爱
 */
public class Amativeness extends Action {

    @Override
    public void getManConclusion(Man concreteElementA) {
        System.out.println("时，凡事不懂也要装懂。");
    }

    @Override
    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println("时，遇事懂也装作不懂");
    }
}
