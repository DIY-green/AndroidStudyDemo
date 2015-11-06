package com.cheng.animationstudy.customview.ucbrowser;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 *
 */
public class PullLayout extends ScrollView {

    private View mTopRL;
    private View mWeaterLL;
    private View mContentLL;
    private TextView mTV;
    private EyeView mEV;
    private ObjectAnimator mObjectAnimator;
    private float mLastY = -1;
    private float mDetalY = -1;
    private int mRange;
    private int mTVHeight;
    private int mTVWidth;
    private boolean mIsTouchOrRunning;
    private boolean mIsActionCancel;

    public PullLayout(Context context) {
        super(context);
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setVerticalScrollBarEnabled(false);
        mTopRL = ViewFinder.findViewById(this, R.id.rl_top);
        mContentLL = ViewFinder.findViewById(this, R.id.ll_content);
        mTV = ViewFinder.findViewById(this, R.id.tv);
        mEV = ViewFinder.findViewById(this, R.id.ev);
        mWeaterLL = ViewFinder.findViewById(this, R.id.ll_weather);

        mTopRL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                mTopRL.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mRange = mTopRL.getHeight();
                scrollTo(0, mRange);
                mTopRL.getLayoutParams().height = mRange;
            }
        });
        mTV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                mTV.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mTVHeight = mTV.getHeight();
                mTVWidth = mTV.getWidth();
                ViewHelper.setTranslationY(mContentLL, mTVHeight);
            }
        });
        mEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
        mTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsActionCancel = false;
                mIsTouchOrRunning = true;
                mLastY = ev.getY();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            ev.setAction(MotionEvent.ACTION_UP);
            mIsActionCancel = true;
        }
        if (mIsActionCancel && ev.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        if (ev.getActionIndex() != 0 && getScrollY() < mRange) {
            ev.setAction(MotionEvent.ACTION_UP);
            mIsActionCancel = true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mIsTouchOrRunning = true;
                if (getScrollY() != 0) {
                    mDetalY = 0;
                    mLastY = ev.getY();
                } else {
                    mDetalY = ev.getY() - mLastY;
                    if (mDetalY > 0) {
                        setT((int) -mDetalY / 5);
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mIsTouchOrRunning = false;
                if (getScrollY() < mRange) {
                    if (mDetalY != 0) {
                        reset();
                    } else {
                        toggle();
                    }
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t > mRange) {
            return;
        } else if (!mIsTouchOrRunning && t != mRange) {
            scrollTo(0, mRange);
        } else {
            animateScroll(t);
        }
    }

    public void setT(int t) {
        scrollTo(0, t);
        if (t < 0) {
            animatePull(t);
        }
    }

    private void animateScroll(int t) {
        float percent = (float) t / mRange;
        ViewHelper.setTranslationY(mTopRL, t);
        ViewHelper.setTranslationY(mContentLL, mTVHeight * percent);
        ViewHelper.setScaleX(mTV, 2 - percent);
        ViewHelper.setScaleY(mTV, 2 - percent);
        ViewHelper.setTranslationX(mTV, mTVWidth * (1 - percent) / 2f);
        ViewHelper.setTranslationY(mTV, t + mTVHeight * (1 - percent) / 2f);
        ViewHelper.setTranslationY(mEV, -t / 2);
        ViewHelper.setTranslationY(mWeaterLL, -t / 2);
        mEV.setRadius((int) (mRange * 0.25f * (1 - percent)));
        mTV.setTextColor(evaluate(percent, Color.WHITE, Color.BLACK));
    }

    private void animatePull(int t) {
        mTopRL.getLayoutParams().height = mRange - t;
        mTopRL.requestLayout();
        float percent = (float) t / mRange;
        ViewHelper.setScaleX(mEV, 1 - percent);
        ViewHelper.setScaleY(mEV, 1 - percent);
        ViewHelper.setScaleX(mTV, 2 - percent);
        ViewHelper.setScaleY(mTV, 2 - percent);
        ViewHelper.setTranslationX(mTV, mTVWidth * (1 - percent) / 2f);
        ViewHelper.setTranslationY(mWeaterLL, t / 2);
    }

    private Integer evaluate(float fraction, Object startValue, Integer endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    public void toggle() {
        if (isOpen()) {
            close();
        } else {
            open();
        }
    }

    private Status status;

    public enum Status {
        Open, Close;
    }

    public boolean isOpen() {
        return status == Status.Open;
    }

    private void reset() {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            return;
        }
        mObjectAnimator = ObjectAnimator.ofInt(this, "t", (int) -mDetalY / 5, 0);
        mObjectAnimator.setDuration(150);
        mObjectAnimator.start();
    }

    public void close() {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            return;
        }
        mObjectAnimator = ObjectAnimator.ofInt(this, "t", getScrollY(), mRange);
        mObjectAnimator.setInterpolator(new DecelerateInterpolator());
        mObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                mIsTouchOrRunning = true;
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                mIsTouchOrRunning = false;
                status = Status.Close;
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });
        mObjectAnimator.setDuration(250);
        mObjectAnimator.start();
    }

    public void open() {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            return;
        }
        mObjectAnimator = ObjectAnimator.ofInt(this, "t", getScrollY(), (int) (-getScrollY() / 2.2f), 0);
        mObjectAnimator.setInterpolator(new DecelerateInterpolator());
        mObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                mIsTouchOrRunning = true;
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                mIsTouchOrRunning = false;
                status = Status.Open;
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });
        mObjectAnimator.setDuration(400);
        mObjectAnimator.start();
    }
}
