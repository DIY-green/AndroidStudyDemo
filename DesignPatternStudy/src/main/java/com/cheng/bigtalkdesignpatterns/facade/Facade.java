package com.cheng.bigtalkdesignpatterns.facade;

import android.util.Log;

/**
 * 外观类
 */
public class Facade {

    private static final String TAG = "Facade";
    private SubSystemOne one;
    private SubSystemTwo two;
    private SubSystemThree three;
    private SubSystemFour four;

    public Facade() {
        this.one = new SubSystemOne();
        this.two = new SubSystemTwo();
        this.three = new SubSystemThree();
        this.four = new SubSystemFour();
    }

    public void methodA() {
        Log.e(TAG, "方法组A（）");
        one.methodOne();
        two.methodTwo();
        four.methodFour();
    }

    public void methodB() {
        Log.e(TAG, "方法组B（）");
        two.methodTwo();
        three.methodThree();
    }
}
