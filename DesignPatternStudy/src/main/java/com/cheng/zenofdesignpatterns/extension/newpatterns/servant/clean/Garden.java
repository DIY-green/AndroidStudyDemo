package com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean;

/**
 * 花园
 */
public class Garden implements Cleanable {

    @Override
    public void cleaned() {
        System.out.println("花园被清洁干净");
    }
}
