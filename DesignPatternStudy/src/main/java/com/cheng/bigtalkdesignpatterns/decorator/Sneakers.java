package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class Sneakers extends Finery {

    private static final String TAG = "Sneakers";

    @Override
    public void show() {
        Log.e(TAG, "破球鞋" + stringBuilder);
        stringBuilder.append("\n破球鞋");
        super.show();
    }
}
