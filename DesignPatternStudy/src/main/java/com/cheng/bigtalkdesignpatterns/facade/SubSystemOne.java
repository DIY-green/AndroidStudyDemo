package com.cheng.bigtalkdesignpatterns.facade;

import android.util.Log;

/**
 * 子系统类
 */
public class SubSystemOne {

    private static final String TAG = "SubSystemOne";

    public void methodOne() {
        Log.e(TAG, "子系统方法一");
    }

}
