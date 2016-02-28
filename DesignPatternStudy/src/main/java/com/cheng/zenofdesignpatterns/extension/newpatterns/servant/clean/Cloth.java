package com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean;

/**
 * 衣服
 */
public class Cloth implements Cleanable {

    @Override
    public void cleaned() {
        System.out.println("衣服被清洁干净");
    }
}
