package com.cheng.animationstudy.customview.zrclistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

public class SimpleHeader implements Headable {
    private float PI = (float) Math.PI;
    private int mState = STATE_REST;
    private int mPice = 6;
    private Paint mPaint;
    private int mHeight = 0;
    private int mTime = 0;
    private int mTextColor;
    private int mPointColor;
    private float mPointRadius = 0;
    private float mCircleRadius = 0;
    private float mFontOffset;
    private String mMsg;
    private boolean isClipCanvas = true;

    public SimpleHeader(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Style.FILL);
        int fontSize = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources()
                        .getDisplayMetrics());
        mPaint.setTextSize(fontSize);
        mPaint.setTextAlign(Align.CENTER);
        mTextColor = 0xffffffff;
        mPointColor = 0xffffffff;
        mFontOffset = -(mPaint.getFontMetrics().top + mPaint.getFontMetrics().bottom) / 2;
        mHeight = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, context.getResources()
                        .getDisplayMetrics());
        mPointRadius =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, context.getResources()
                        .getDisplayMetrics());
        mCircleRadius = mPointRadius * 3.5f;
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setCircleColor(int color) {
        mPointColor = color;
    }

    public void setIsClipCanvas(boolean bool) {
        isClipCanvas = bool;
    }

    @Override
    public void stateChange(int state, String msg) {
        if (mState != state) {
            mTime = 0;
        }
        mState = state;
        this.mMsg = msg;
        Log.d("zzSTATE", state+"");
    }

    @Override
    public int getState() {
        return mState;
    }

    @Override
    public boolean draw(Canvas canvas, int left, int top, int right, int bottom) {
        boolean more = false;
        final int width = right - left;
        final int height = mHeight;
        final int offset = bottom - top;
        canvas.save();
        if (isClipCanvas) {
            canvas.clipRect(left + 5, 1, right + 5, bottom - 1);
        }
        switch (mState) {
        case STATE_REST:
            break;
        case STATE_PULL:
        case STATE_RELEASE:
            if (offset < 10) {
                break;
            }
            mPaint.setColor(mPointColor);
            for (int i = 0; i < mPice; i++) {
                int angleParam;
                if (offset < height * 3 / 4) {
                    angleParam = offset * 16 / height - 3;// 每1%转0.16度;
                } else {
                    angleParam = offset * 300 / height - 217;// 每1%转3度;
                }
                float angle = -(i * (360 / mPice) - angleParam) * PI / 180;
                float radiusParam;
                if (offset <= height) {
                    radiusParam = offset / (float) height;
                    radiusParam = 1 - radiusParam;
                    radiusParam *= radiusParam;
                    radiusParam = 1 - radiusParam;
                } else {
                    radiusParam = 1;
                }
                float radius = width / 2 - radiusParam * (width / 2 - mCircleRadius);
                float x = (float) (width / 2 + radius * Math.cos(angle));
                float y = (float) (offset / 2 + radius * Math.sin(angle));
                canvas.drawCircle(x, y + top, mPointRadius, mPaint);
            }
            break;
        case STATE_LOADING:
            more = true;
            mPaint.setColor(mPointColor);
            for (int i = 0; i < mPice; i++) {
                int angleParam = mTime * 5;
                float angle = -(i * (360 / mPice) - angleParam) * PI / 180;
                float radius = mCircleRadius;
                float x = (float) (width / 2 + radius * Math.cos(angle));
                float y;
                if (offset < height) {
                    y = (float) (offset - height / 2 + radius * Math.sin(angle));
                } else {
                    y = (float) (offset / 2 + radius * Math.sin(angle));
                }
                canvas.drawCircle(x, y + top, mPointRadius, mPaint);
            }
            mTime++;
            break;
        case STATE_SUCCESS:
        case STATE_FAIL:
            more = true;
            final int time = mTime;
            if (time < 30) {
                mPaint.setColor(mPointColor);
                for (int i = 0; i < mPice; i++) {
                    int angleParam = mTime * 10;
                    float angle = -(i * (360 / mPice) - angleParam) * PI / 180;
                    float radius = mCircleRadius + time * mCircleRadius;
                    float x = (float) (width / 2 + radius * Math.cos(angle));
                    float y;
                    if (offset < height) {
                        y = (float) (offset - height / 2 + radius * Math.sin(angle));
                    } else {
                        y = (float) (offset / 2 + radius * Math.sin(angle));
                    }
                    canvas.drawCircle(x, y + top, mPointRadius, mPaint);
                }
                mPaint.setColor(mTextColor);
                mPaint.setAlpha(time * 255 / 30);
                String text = mMsg != null ? mMsg : mState == STATE_SUCCESS ? "加载成功" : "加载失败";
                float y;
                if (offset < height) {
                    y = offset - height / 2;
                } else {
                    y = offset / 2;
                }
                canvas.drawText(text, width / 2, y + top + mFontOffset, mPaint);
            } else {
                mPaint.setColor(mTextColor);
                String text = mMsg != null ? mMsg : mState == STATE_SUCCESS ? "加载成功" : "加载失败";
                float y;
                if (offset < height) {
                    y = offset - height / 2;
                    mPaint.setAlpha(offset * 255 / height);
                } else {
                    y = offset / 2;
                }
                canvas.drawText(text, width / 2, y + top + mFontOffset, mPaint);
            }
            mTime++;
            break;
        }
        canvas.restore();
        return more;
    }

    @Override
    public void toastResultInOtherWay(Context context, int state) {
        if (state == Headable.STATE_SUCCESS) {
        } else if (state == Headable.STATE_FAIL) {
            Toast.makeText(context, mMsg != null ? mMsg : "加载失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getHeight() {
        return mHeight;
    }
}
