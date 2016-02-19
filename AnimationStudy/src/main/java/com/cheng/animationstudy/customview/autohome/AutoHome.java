package com.cheng.animationstudy.customview.autohome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.cheng.animationstudy.R;

/**
 * 汽车之家自定义View
 */
public class AutoHome extends View {

    private Bitmap mBackgroundBitmap;
    private Bitmap mPointerBitmap;
    private Bitmap mFinalBackgroundBitmap;
    private Bitmap mFinalPointerBitmap;

    private int x;
    private int y;
    private float mCurrentProgress;

    public AutoHome(Context context) {
        super(context);
        init(context);
    }

    public AutoHome(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoHome(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBackgroundBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sdd_autohome_load_icon_dial2x));
        mPointerBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sdd_autohome_load_icon_pointer2x));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
        x = getMeasuredWidth();
        y = getMeasuredHeight();
        mFinalBackgroundBitmap = Bitmap.createScaledBitmap(mBackgroundBitmap, x, y, true);
        mFinalPointerBitmap = Bitmap.createScaledBitmap(mPointerBitmap, x, y, true);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = mBackgroundBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mFinalBackgroundBitmap, 0, 0, null);
        canvas.rotate(mCurrentProgress*2.7f, x/2, y/2);
        canvas.drawBitmap(mFinalPointerBitmap, 0, 0, null);
    }

    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress * 100;
    }
}
