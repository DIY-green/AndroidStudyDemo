package com.cheng.bigtalkdesignpatterns.factorymethod;

import android.util.Log;

/**
 * 雷锋
 */
public class LeiFeng {

    private static final String TAG = "LeiFeng";

    public void sweep() {
        Log.e(TAG, "扫地");
    }

    public void wash() {
        Log.e(TAG, "洗衣");
    }

    public void buyRice() {
        Log.e(TAG, "买米");
    }
}
