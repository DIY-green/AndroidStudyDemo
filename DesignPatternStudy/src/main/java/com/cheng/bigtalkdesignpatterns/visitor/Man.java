package com.cheng.bigtalkdesignpatterns.visitor;


/**
 * "男人"类
 */
public class Man extends Person {

    @Override
    public void accept(Action visitor) {
        visitor.getManConclusion(this);
    }
}
