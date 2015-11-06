package com.cheng.animationstudy.customview.baiduread;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;

/**
 * 波纹
 */
public class RippleLayout extends RelativeLayout {

    private int FRAME_RATE = 10;

    private Paint mPaint;
    private Handler mCanvasHandler;
    private View mChildView;
    public RippleFinishListener mRippleFinishListener;

    private int mRippleColor;
    private boolean mAnimationRunning = false;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private int mTimer = 0;
    private float mInnerRadius;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public interface RippleFinishListener{
        void rippleFinish(int id);
    }

    public RippleLayout(Context context) {
        super(context);
    }

    public RippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        mRippleColor = typedArray.getColor(R.styleable.RippleView_rv_color, getResources().getColor(R.color.rippelColor3));
        mInnerRadius = typedArray.getDimension(R.styleable.RippleView_rv_inner_radius, 30.0f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mRippleColor);
        mPaint.setStrokeWidth(20);

        this.setWillNotDraw(false);
        this.setDrawingCacheEnabled(true);

        mCanvasHandler = new Handler();
        typedArray.recycle();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        ripple(canvas);
    }

    private void ripple(Canvas canvas) {
        if (mAnimationRunning) {
            if (mTimer <= 20) {
                play(canvas, mTimer);
                mCanvasHandler.postDelayed(mRunnable, FRAME_RATE);
                mTimer++;
            } else {
                mAnimationRunning = false;
                canvas.restore();
                invalidate();
                mTimer = 0;
                if (mRippleFinishListener != null) {
                    mRippleFinishListener.rippleFinish(this.getId());
                }
            }
            if (mTimer == 0) {
                canvas.save();
            }
        }
    }

    private void play(Canvas canvas, int mTimer) {
        int alpha = 5; // 透明度从5开始
        for (int i=0; i<=mTimer; i++) {
            alpha += 2; // 每绘制一个外圆，透明度加2
            mPaint.setAlpha(alpha);
            mPaint.setStrokeWidth(2);
            canvas.drawCircle(mBitmapWidth/2, mBitmapHeight/2, mInnerRadius+i, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmapWidth = w;
        mBitmapHeight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (!mAnimationRunning) {
                invalidate();
                this.performClick();
                mAnimationRunning = true;
            }
        }
        mChildView.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        mChildView = child;
        Logger.i("addView:" + child.getContentDescription());
        super.addView(child, index, params);
    }

    public void setRippleFinishListener(RippleFinishListener rippleFinishListener){
        this.mRippleFinishListener = rippleFinishListener;
    }
}
