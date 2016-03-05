package com.cheng.animationstudy.activity;

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

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

/**
 * 模仿浏览器缩放动画
 */
public class ImitateBrowserZoomViewActivity extends AppCompatActivity {

    private static final int ANIM_DURATION_150 = 150;
    private static final int ANIM_DURATION_200 = 200;
    private static final int ANIM_DURATION_350 = 350;

    private LinearLayout mFirstViewLL;
    private LinearLayout mSecondViewLL;
    private int mFHeight;
    private int mSHeight;
    private Button mShowBtn;
    private Button mHideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitatebrowserzoomview);

        initView();
        initListener();
    }

    private void initView() {
        this.mFirstViewLL = ViewFinder.findViewById(this, R.id.ll_firstview);
        this.mSecondViewLL = ViewFinder.findViewById(this, R.id.ll_secondview);
        this.mShowBtn = ViewFinder.findViewById(this, R.id.btn_showanim);
        this.mHideBtn = ViewFinder.findViewById(this, R.id.btn_hideanim);
    }

    private void initListener() {
        this.mFirstViewLL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFHeight = mFirstViewLL.getHeight();
                mFirstViewLL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.mSecondViewLL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSHeight = mSecondViewLL.getHeight();
                mSecondViewLL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
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
        ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(mFirstViewLL, "scaleX", 1.0f, 0.8f);
        fViewScaleXAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(mFirstViewLL, "scaleY", 1.0f, 0.8f);
        fViewScaleYAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(mFirstViewLL, "alpha", 1.0f, 0.5f);
        fViewAlphaAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mFirstViewLL, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(ANIM_DURATION_200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mFirstViewLL, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(ANIM_DURATION_150);
        fViewResumeAnim.setStartDelay(ANIM_DURATION_200);
        ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(mFirstViewLL, "translationY", 0,-0.1f * mFHeight);
        fViewTransYAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(mSecondViewLL, "translationY", mSHeight, 0f);
        sViewTransYAnim.setDuration(ANIM_DURATION_350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mSecondViewLL.setVisibility(View.VISIBLE);
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
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(mFirstViewLL, "scaleX", 0.8f, 1.0f);
        fViewScaleXAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(mFirstViewLL, "scaleY", 0.8f, 1.0f);
        fViewScaleYAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(mFirstViewLL, "alpha", 0.5f, 1.0f);
        fViewAlphaAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(mFirstViewLL, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(ANIM_DURATION_200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(mFirstViewLL, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(ANIM_DURATION_150);
        fViewResumeAnim.setStartDelay(ANIM_DURATION_200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(mFirstViewLL, "translationY", -0.1f * mFHeight, 0);
        fViewTransYAnim.setDuration(ANIM_DURATION_350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(mSecondViewLL, "translationY", 0, mSHeight);
        sViewTransYAnim.setDuration(ANIM_DURATION_350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSecondViewLL.setVisibility(View.INVISIBLE);
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
