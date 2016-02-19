package com.cheng.animationstudy.customview.autohome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.cheng.animationstudy.R;

/**
 * 汽车之家下拉刷新指针
 */
public class PointerView extends View {

    private Bitmap mPointerBitmap;
    private Bitmap mFinalPointerBitmap;

    private int x;
    private int y;

    public PointerView(Context context) {
        super(context);
        init();
    }

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mPointerBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sdd_autohome_load_icon_pointer2x));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
        x = getMeasuredWidth();
        y = getMeasuredHeight();
        mFinalPointerBitmap = Bitmap.createScaledBitmap(mPointerBitmap, x, y, true);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = mPointerBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(270, x/2, y/2);
        canvas.drawBitmap(mFinalPointerBitmap, 0, 0, null);
    }
}
