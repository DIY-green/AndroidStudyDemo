package com.cheng.animationstudy.customview.meituan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.cheng.animationstudy.R;

/**
 * 第一个状态的实现：
 * 思路是：当前这个椭圆形有一个进度值，这个进度值从0变为1，
 * 然后对这个椭圆形进行缩放，可以使用自定义View来实现这个效果
 */
public class MeiTuanRefreshFirstStepView extends View {

    private Bitmap mInitialBitmap;
    private Bitmap mEndBitmap;
    private Bitmap mScaledBitmap;

    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private float mCurrentProgress;

    public MeiTuanRefreshFirstStepView(Context context) {
        super(context);
        init(context);
    }

    public MeiTuanRefreshFirstStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeiTuanRefreshFirstStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // 初始化椭圆形图片
        mInitialBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sdd_meituan_pull_image));
        // 这个是第二个状态娃娃的图片，之所以要这张图片，是因为第二个状态和第三个状态的图片的大小是一致的，而第一阶段
        // 椭圆形图片的大小与第二阶段和第三阶段不一致，因此我们需要根据这张图片来决定第一张图片的宽高，来保证
        // 第一阶段和第二、三阶段的View的宽高一致
        mEndBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sdd_meituan_pull_end_image_frame_05));
    }

    /**
     * 重写onMeasure方法主要是设置wrap_content时 View的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 根据设置的宽度来计算高度 设置为符合第二阶段娃娃图片的宽高比例
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(widthMeasureSpec)*mEndBitmap.getHeight()/mEndBitmap.getWidth());
    }

    /**
     * 当wrap_content的时候，宽度即为第二阶段娃娃图片的宽度
     * @param widthMeasureSpec
     * @return
     */
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

    /**
     * 在onLayout里面获得测量后View的宽高
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        // 根据第二阶段娃娃宽高 给椭圆形图片进行等比例的缩放
        mScaledBitmap = Bitmap.createScaledBitmap(
                mInitialBitmap,
                mMeasuredWidth,
                mMeasuredWidth*mInitialBitmap.getHeight()/mInitialBitmap.getWidth(),
                true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 这个方法是对画布进行缩放，从而达到椭圆形图片的缩放，
        // 第一个参数为宽度缩放比例，第二个参数为高度缩放比例，
        canvas.scale(mCurrentProgress, mCurrentProgress, mMeasuredWidth/2, mMeasuredHeight/2);
        // 将等比例缩放后的椭圆形画在画布上面
        canvas.drawBitmap(mScaledBitmap, 0, mMeasuredHeight/4, null);
    }

    /**
     * 设置缩放比例，从0到1  0为最小 1为最大
     * @param currentProgress
     */
    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
    }
}
