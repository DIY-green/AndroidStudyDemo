package com.cheng.bigtalkdesignpatterns.visitor;

/**
 * 结婚状态类
 */
public class Marriage extends Action {

    @Override
    public void getManConclusion(Man concreteElementA) {
        System.out.println("时，感慨道，恋爱游戏终结时。‘有妻徒刑’遥无期");
    }

    @Override
    public void getWomanConclusion(Woman concreteElementB) {
        System.out.println("时，欣慰曰，爱情长跑路漫漫，婚姻保险保平安。");
    }
}
