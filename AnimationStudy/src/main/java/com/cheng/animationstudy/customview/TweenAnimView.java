package com.cheng.animationstudy.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;

/**
 * 自定义用于演示Tween动画的View
 */
public class TweenAnimView extends View {

    private static final String TAG = "TweenAnimView";

    // Alpha动画 - 透明度渐变动画
    private Animation mAlphaAnimation = null;
    // Scale动画 - 缩放动画
    private Animation mScaleAnimation = null;
    // Translate动画 - 位移动画
    private Animation mTranslateAnimation = null;
    // Rotate动画 - 旋转动画
    private Animation mRotateAnimation = null;

    public TweenAnimView(Context context) {
        super(context);
    }

    public TweenAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Logger.TAG = TAG;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.i("onDraw");
        // 加载一个图片
        canvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(R.mipmap.sdd_simple)).getBitmap(), 0, 0, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Logger.e("onKeyDown");
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Logger.e("onKeyUp");
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                Logger.e("onKeyDown - KEYCODE_DPAD_UP");
                mAlphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                mAlphaAnimation.setDuration(C.Int.ANIM_DURATION * 2);
                this.startAnimation(mAlphaAnimation);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                Logger.e("onKeyDown - KEYCODE_DPAD_DOWN");
                mRotateAnimation = new RotateAnimation(0f, 360f);
                mRotateAnimation.setDuration(C.Int.ANIM_DURATION * 2);
                this.startAnimation(mRotateAnimation);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                Logger.e("onKeyDown - KEYCODE_DPAD_LEFT");
                mScaleAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f);
                mScaleAnimation.setDuration(C.Int.ANIM_DURATION * 2);
                this.startAnimation(mScaleAnimation);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Logger.e("onKeyDown - KEYCODE_DPAD_RIGHT");
                mTranslateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
                mTranslateAnimation.setDuration(C.Int.ANIM_DURATION * 2);
                this.startAnimation(mTranslateAnimation);
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                Logger.e("onKeyDown - KEYCODE_DPAD_CENTER");
                mTranslateAnimation = new TranslateAnimation(0.1f, 200.0f, 0.1f, 200.0f);
                mAlphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                AnimationSet set = new AnimationSet(true);
                set.setDuration(C.Int.ANIM_DURATION * 2);
                this.startAnimation(set);
                break;
        }
        return true;
    }

}
