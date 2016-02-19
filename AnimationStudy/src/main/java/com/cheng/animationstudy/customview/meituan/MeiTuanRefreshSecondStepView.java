package com.cheng.animationstudy.customview.meituan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

import com.cheng.animationstudy.R;

/**
 * 第二个状态的实现：
 * 第二个状态是一个帧动画，为了保证View大小的统一，
 * 进行自定义View，这个自定义View很简单，
 * 只是为了和第一阶段View的宽高保证一致即可
 */
public class MeiTuanRefreshSecondStepView extends View {

    private Bitmap mEndBitmap;

    public MeiTuanRefreshSecondStepView(Context context) {
        super(context);
        init();
    }

    public MeiTuanRefreshSecondStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiTuanRefreshSecondStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mEndBitmap = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        getResources(),
                        R.mipmap.sdd_meituan_pull_end_image_frame_05));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureWidth(widthMeasureSpec)*mEndBitmap.getHeight()/mEndBitmap.getWidth());
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = mEndBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
