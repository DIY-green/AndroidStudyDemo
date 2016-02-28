package com.cheng.bigtalkdesignpatterns.proxy;

import android.util.Log;

/**
 * 追求者类
 */
public class Pursuit implements GiveGift {

    private static final String TAG = "Pursuit";

    private SchoolGirl mm;

    public Pursuit(SchoolGirl _mm) {
        this.mm = _mm;
    }

    @Override
    public String giveDools() {
        Log.e(TAG, mm.getName() + " 送你洋娃娃");
        return mm.getName() + " 送你洋娃娃";
    }

    @Override
    public String giveFlowers() {
        Log.e(TAG, mm.getName() + " 送你鲜花");
        return mm.getName() + " 送你鲜花";
    }

    @Override
    public String giveChocolate() {
        Log.e(TAG, mm.getName() + " 送你巧克力");
        return mm.getName() + " 送你巧克力";
    }
}
