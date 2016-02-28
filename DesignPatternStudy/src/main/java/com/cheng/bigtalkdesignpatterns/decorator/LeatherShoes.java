package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * 具体服饰类（ConcreteDecorator）
 */
public class LeatherShoes extends Finery {

    private static final String TAG = "LeatherShoes";

    @Override
    public void show() {
        Log.e(TAG, "皮鞋" + stringBuilder);
        stringBuilder.append("\n皮鞋");
        super.show();
    }
}
