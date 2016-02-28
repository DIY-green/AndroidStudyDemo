package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class Tie extends Finery {

    private static final String TAG = "Tie";

    @Override
    public void show() {
        Log.e(TAG, "领带" + stringBuilder);
        stringBuilder.append("\n领带");
        super.show();
    }
}
