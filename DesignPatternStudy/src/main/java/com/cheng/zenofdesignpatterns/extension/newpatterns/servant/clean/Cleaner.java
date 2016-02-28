package com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean;

/**
 * 抽象的清洁者
 */
public class Cleaner {

    // 清洁
    public void clean(Cleanable cleanable) {
        cleanable.cleaned();
    }

}
