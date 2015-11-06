package com.cheng.animationstudy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cheng.utils.ViewFinder;

/**
 * 模仿浏览器缩放动画
 */
public class UiImitateBrowserZoomView extends AppCompatActivity {

    private static final int kANIM_DURATION_150 = 150;
    private static final int kANIM_DURATION_200 = 200;
    private static final int kANIM_DURATION_350 = 350;

    private LinearLayout mFirstView;
    private LinearLayout mSecondView;
    private int mFHeight;
    private int mSHeight;
    private Button mShowBtn;
    private Button mHideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imitatebrowserzoomview);

        initView();
        initListener();
    }

    private void initView() {
        this.mFirstView = ViewFinder.findViewById(this, R.id.sdi_firstview);
        this.mSecondView = ViewFinder.findViewById(this, R.id.sdi_secondview);
        this.mShowBtn = ViewFinder.findViewById(this, R.id.sdi_showanim_btn);
        this.mHideBtn = ViewFinder.findViewById(this, R.id.sdi_hideanim_btn);
    }

    private void initListener() {
        this.mFirstView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFHeight = mFirstView.getHeight();
                mFirstView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.mSecondView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSHeight = mSecondView.getHeight();
                mSecondView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShowAnim();
            }
        });
        this.mHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initHideAnim();
            }
        });
    }

    private void initShowAnim() {
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(mFirstView, "scaleX", 1.0f, 0.8f);
        fViewScaleXAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(mFirstView, "scaleY", 1.0f, 0.8f);
        fViewScaleYAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(mFirstView, "alpha", 1.0f, 0.5f);
        fViewAlphaAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mFirstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(kANIM_DURATION_200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mFirstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(kANIM_DURATION_150);
        fViewResumeAnim.setStartDelay(kANIM_DURATION_200);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(mFirstView, "translationY", 0,-0.1f * mFHeight);
        fViewTransYAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(mSecondView, "translationY", mSHeight, 0f);
        sViewTransYAnim.setDuration(kANIM_DURATION_350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mSecondView.setVisibility(View.VISIBLE);
            }
        });
        AnimatorSet showFirstViewAnim = new AnimatorSet();
        showFirstViewAnim.playTogether(
                fViewScaleXAnim,
                fViewRotationXAnim,
                fViewResumeAnim,
                fViewTransYAnim,
                fViewAlphaAnim,
                fViewScaleYAnim,
                sViewTransYAnim
        );
        showFirstViewAnim.start();
    }

    private void initHideAnim() {
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(mFirstView, "scaleX", 0.8f, 1.0f);
        fViewScaleXAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(mFirstView, "scaleY", 0.8f, 1.0f);
        fViewScaleYAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(mFirstView, "alpha", 0.5f, 1.0f);
        fViewAlphaAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mFirstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(kANIM_DURATION_200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mFirstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(kANIM_DURATION_150);
        fViewResumeAnim.setStartDelay(kANIM_DURATION_200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(mFirstView, "translationY", -0.1f * mFHeight, 0);
        fViewTransYAnim.setDuration(kANIM_DURATION_350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(mSecondView, "translationY", 0, mSHeight);
        sViewTransYAnim.setDuration(kANIM_DURATION_350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSecondView.setVisibility(View.INVISIBLE);
            }
        });
        AnimatorSet hideFirstViewAnim=new AnimatorSet();
        hideFirstViewAnim.playTogether(
                fViewScaleXAnim,
                fViewRotationXAnim,
                fViewResumeAnim,
                fViewTransYAnim,
                fViewAlphaAnim,
                fViewScaleYAnim,
                sViewTransYAnim);
        hideFirstViewAnim.start();
    }

}
