package com.cheng.utils.view;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * View震动效果帮助类
 * 需要加上震动的权限
 * <uses-permission android:name="android.permission.VIBRATE" />
 */
public class SharkAnimHelper {

    private static final int kSHAKE_DURATION = 300;
    private static final int kCYCLE_NUM = 8;

    private Animation mSharkAnimation;                  // 震动动画
    private CycleInterpolator mCycleInterpolator;       // 插值器
    private Vibrator mShakeVibrator;                    // 振动器

    private static final SharkAnimHelper sInstance = new SharkAnimHelper();

    private SharkAnimHelper() {

    }

    public static SharkAnimHelper getInstance(Context context) {
        return sInstance;
    }

    public void shake(Context context, View view) {
        if (context==null || view==null) return;
        if (mShakeVibrator == null) {
            // 初始化振动器
            mShakeVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        }
        if (mSharkAnimation == null) {
            // 初始化震动动画
            mSharkAnimation = new TranslateAnimation(0, 10, 0, 0);
            mSharkAnimation.setDuration(kSHAKE_DURATION);
        }
        if (mCycleInterpolator == null) {
            mCycleInterpolator = new CycleInterpolator(kCYCLE_NUM);
            mSharkAnimation.setInterpolator(mCycleInterpolator);
        }
        view.startAnimation(mSharkAnimation);
        mShakeVibrator.vibrate(new long[]{0, 500}, -1);
    }

}
