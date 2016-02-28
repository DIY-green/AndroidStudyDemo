package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class Suit extends Finery {

    private static final String TAG = "Suit";

    @Override
    public void show() {
        Log.e(TAG, "西装" + stringBuilder);
        stringBuilder.append("\n西装");
        super.show();
    }
}
