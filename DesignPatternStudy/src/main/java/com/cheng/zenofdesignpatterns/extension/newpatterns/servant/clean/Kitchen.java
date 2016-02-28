package com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean;

/**
 * 厨房
 */
public class Kitchen implements Cleanable {

    @Override
    public void cleaned() {
        System.out.println("厨房被清洁干净");
    }
}
