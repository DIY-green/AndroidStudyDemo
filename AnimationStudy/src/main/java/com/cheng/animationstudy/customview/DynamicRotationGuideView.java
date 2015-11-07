package com.cheng.animationstudy.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.cheng.animationstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 乐动力自定义布局
 */
public class DynamicRotationGuideView extends ViewGroup {

    /**
     * 左边的图片
     */
    private ImageView left;
    /**
     * 右边的图片
     */
    private ImageView right;
    /**
     * 上面的图片
     */
    private ImageView top;
    /**
     * 下面的图片
     */
    private ImageView bottom;
    /**
     * 中间的图片  m
     */
    private ImageView center;
    /**
     * 屏幕的宽度
     */
    private int screenW;
    /**
     * 屏幕的高度
     */
    private int screenH;

    private List<Bitmap> bitmaps = new ArrayList<Bitmap>();

    private LinearInterpolator interpolator = new LinearInterpolator();

    public DynamicRotationGuideView(Context context) {
        super(context);
        init(context);
    }

    public DynamicRotationGuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DynamicRotationGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        left = new ImageView(context);
        left.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.day));
        bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.mipmap.day));
        right = new ImageView(context);
        right.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.night));
        bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.mipmap.night));
        top = new ImageView(context);
        top.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.up));
        bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.mipmap.up));
        bottom = new ImageView(context);
        bottom.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.down));
        bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.mipmap.down));
        center = new ImageView(context);
        center.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.animation_battery));
        bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.mipmap.animation_battery));
        screenW = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
        screenH = ((Activity)context).getWindowManager().getDefaultDisplay().getHeight();
        this.addView(center);
        this.addView(left);
        this.addView(right);
        this.addView(top);
        this.addView(bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(screenW, screenH);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //五个控件的布局
        center.layout(screenW/2-bitmaps.get(4).getWidth()/2, screenH/2-bitmaps.get(4).getHeight()/2,
                screenW/2+bitmaps.get(4).getWidth()/2, screenH/2+bitmaps.get(4).getHeight()/2);
        top.layout(screenW/2-bitmaps.get(2).getWidth()/2,screenH/2-bitmaps.get(4).getHeight()/2-bitmaps.get(2).getHeight()+bitmaps.get(2).getWidth()/2/4,
                screenW/2+bitmaps.get(2).getWidth()/2, screenH/2-bitmaps.get(4).getHeight()/2+bitmaps.get(2).getWidth()/2/4);
        left.layout(screenW/2-bitmaps.get(0).getWidth()*2-bitmaps.get(0).getWidth()/4, screenH/2-bitmaps.get(0).getHeight()/2,
                screenW/2-bitmaps.get(0).getWidth()-bitmaps.get(0).getWidth()/4, screenH/2+bitmaps.get(0).getHeight()/2);
        right.layout(screenW/2+bitmaps.get(1).getWidth()+bitmaps.get(1).getHeight()/4, screenH/2-bitmaps.get(1).getHeight()/2,
                screenW/2+bitmaps.get(1).getWidth()*2+bitmaps.get(1).getHeight()/4, screenH/2+bitmaps.get(1).getHeight()/2);
        bottom.layout(screenW/2-bitmaps.get(3).getWidth()/2, screenH/2+bitmaps.get(4).getHeight()/2-bitmaps.get(3).getWidth()/2/4,
                screenW/2+bitmaps.get(3).getWidth()/2, screenH/2+bitmaps.get(4).getHeight()/2+bitmaps.get(3).getHeight()-bitmaps.get(3).getWidth()/2/4);
        playCenter();
        playTop();
        playBottom();
        playLeft();
        playRight();
    }

    /**
     * 右边的动画
     */
    private void playRight() {
        //混合动画
        AnimationSet animationSet = new AnimationSet(false);
        RotateAnimation rotateRight = new RotateAnimation(0, 359, Animation.ABSOLUTE, screenW/2-right.getLeft(), Animation.ABSOLUTE, (right.getBottom()-right.getTop())/2);
        RotateAnimation rotateSelf = new RotateAnimation(0, -359, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //播放时间
        rotateSelf.setDuration(10*1000);
        //播放加速的模式
        rotateSelf.setInterpolator(interpolator);
        //设置无限循环
        rotateSelf.setRepeatCount(-1);
        rotateRight.setDuration(10*1000);
        rotateRight.setRepeatCount(-1);
        rotateRight.setInterpolator(interpolator);
        animationSet.addAnimation(rotateSelf);
        animationSet.addAnimation(rotateRight);
        //播放混合动画
        right.startAnimation(animationSet);
    }
    /**
     * 左边的动画
     */
    private void playLeft() {
        AnimationSet animationSet = new AnimationSet(false);
        RotateAnimation rotateLeft = new RotateAnimation(0, 359, Animation.ABSOLUTE, screenW/2-left.getLeft(), Animation.ABSOLUTE, (left.getBottom()-left.getTop())/2);
        rotateLeft.setDuration(10*1000);
        rotateLeft.setInterpolator(interpolator);
        rotateLeft.setRepeatCount(-1);
        RotateAnimation rotateSelf = new RotateAnimation(0, -359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateSelf.setDuration(10*1000);
        rotateSelf.setRepeatCount(-1);
        rotateSelf.setInterpolator(interpolator);
        animationSet.addAnimation(rotateSelf);
        animationSet.addAnimation(rotateLeft);
        left.startAnimation(animationSet);
    }
    /**
     * 下面的动画
     */
    private void playBottom() {
        RotateAnimation rotateBottom = new RotateAnimation(0, 359,  Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE,screenH/2-bottom.getTop());
        rotateBottom.setDuration(10*1000);
        rotateBottom.setInterpolator(interpolator);
        rotateBottom.setRepeatCount(-1);
        bottom.startAnimation(rotateBottom);
    }
    /**
     * 上面的动画
     */
    private void playTop() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359, Animation.ABSOLUTE, screenW/2-top.getLeft(), Animation.ABSOLUTE, screenH/2-top.getTop());
        rotateAnimation.setDuration(10*1000);
        rotateAnimation.setInterpolator(interpolator);
        rotateAnimation.setRepeatCount(-1);
        top.startAnimation(rotateAnimation);
    }
    /**
     * 中间的View动画播放
     */
    private void playCenter() {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleSmall = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ScaleAnimation scaleBig = new ScaleAnimation(1.0f, 5.0f/3, 1.0f, 5.0f/3,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleBig.setDuration(2*1000);
        scaleBig.setInterpolator(interpolator);
        scaleSmall.setDuration(2*1000);
        scaleSmall.setStartOffset(2*1000);
        scaleSmall.setRepeatCount(-1);
        scaleSmall.setFillEnabled(true);
        scaleSmall.setFillAfter(true);
        scaleBig.setStartOffset(2*1000);
        scaleBig.setRepeatCount(-1);
        scaleBig.setFillEnabled(true);
        scaleBig.setFillAfter(true);
        scaleSmall.setInterpolator(interpolator);
        animationSet.addAnimation(scaleBig);
        animationSet.addAnimation(scaleSmall);
        center.startAnimation(animationSet);
    }
}
