package com.cheng.animationstudy.customview.wandoujia;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

import com.cheng.animationstudy.R;

/**
 * 图片居中，并可以设置图片大小的TabItem
 */
public class IconTabItem extends RadioButton {

    private Drawable mButtonDrawable;

    private int mDrawableWidth;
    private int mDrawableHeight;

    public IconTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompoundButton);
        mButtonDrawable = typedArray.getDrawable(R.styleable.CompoundButton_android_button);
        mDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.CompoundButton_drawableWidth, 0);
        mDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.CompoundButton_drawableHeight, 0);
        setButtonDrawable(R.drawable.sdd_empty_drawable);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mButtonDrawable != null) {
            mButtonDrawable.setState(getDrawableState());
            final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
            final int buttonHeight = mDrawableWidth==0 ? mButtonDrawable.getIntrinsicHeight() : mDrawableWidth;
            int y = 0;
            switch (verticalGravity) {
                case Gravity.BOTTOM:
                    y = getHeight() - buttonHeight;
                    break;
                case Gravity.CENTER_VERTICAL:
                    y = (getHeight() - buttonHeight) / 2;
                    break;
            }
            int buttonWidth = mDrawableHeight==0 ? mButtonDrawable.getIntrinsicWidth() : mDrawableHeight;
            int buttonLeft = (getWidth() - buttonWidth) / 2;
            mButtonDrawable.setBounds(buttonLeft, y, buttonLeft + buttonWidth, y + buttonHeight);
            mButtonDrawable.draw(canvas);
        }
    }
}
