package com.cheng.animationstudy.customview.viewnatruetransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 *
 */
public class LayerEnablingAnimatorListener extends AnimatorListenerAdapter {

    private final View mTargetView;
    private int mLayerType;

    public LayerEnablingAnimatorListener(View targetView) {
        if (targetView == null) {
            throw new NullPointerException("Target view cannot be null");
        }
        mTargetView = targetView;
    }

    public View getTargetView() {
        return mTargetView;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        mLayerType = mTargetView.getLayerType();
        mTargetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        mTargetView.setLayerType(mLayerType, null);
    }
}
