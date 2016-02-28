package com.cheng.bigtalkdesignpatterns.visitor;


/**
 * "女人"类
 */
public class Woman extends Person {

    @Override
    public void accept(Action visitor) {
        visitor.getWomanConclusion(this);
    }
}
