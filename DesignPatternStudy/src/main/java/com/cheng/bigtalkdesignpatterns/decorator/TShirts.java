package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class TShirts extends Finery {

    private static final String TAG = "TShirts";

    @Override
    public void show() {
        Log.e(TAG, "大T恤" + stringBuilder);
        stringBuilder.append("\n大T恤");
        super.show();
    }
}
