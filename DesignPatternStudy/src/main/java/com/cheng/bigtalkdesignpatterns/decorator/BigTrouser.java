package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class BigTrouser extends Finery {

    private static final String TAG = "BigTrouser";

    @Override
    public void show() {
        Log.e(TAG, "垮裤" + stringBuilder);
        stringBuilder.append("\n垮裤");
        super.show();
    }
}
