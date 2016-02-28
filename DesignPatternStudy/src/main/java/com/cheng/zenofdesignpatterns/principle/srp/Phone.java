package com.cheng.zenofdesignpatterns.principle.srp;

/**
 * 一个类实现了两个接口，把两个职责融合在一个类中
 */
public class Phone implements IConnectionManager, IDataTransfer {

    @Override
    public void dial(String phoneNumber) {

    }

    @Override
    public void hangup() {

    }

    @Override
    public void dataTransfer(IConnectionManager manager) {

    }
}
