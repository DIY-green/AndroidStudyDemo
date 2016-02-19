package com.cheng.animationstudy.customview.meituan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

import com.cheng.animationstudy.R;

/**
 * 第三个状态的实现：
 * 和第二个状态同理，我们也通过自定义View来确保三个状态的View的宽高保持一致
 */
public class MeiTuanRefreshThirdStepView extends View {

    private Bitmap mEndBitmap;

    public MeiTuanRefreshThirdStepView(Context context) {
        super(context);
        init();
    }

    public MeiTuanRefreshThirdStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiTuanRefreshThirdStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mEndBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sdd_meituan_pull_end_image_frame_05));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(widthMeasureSpec)*mEndBitmap.getHeight()/mEndBitmap.getWidth());
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
