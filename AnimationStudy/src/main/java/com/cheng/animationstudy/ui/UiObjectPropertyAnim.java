package com.cheng.animationstudy.ui;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.RectEvaluator;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customevaluator.HsvEvaluator;
import com.cheng.animationstudy.customevaluator.MatrixEvaluator;
import com.cheng.utils.ViewFinder;

public class UiObjectPropertyAnim extends AppCompatActivity {

    private ImageView mShowAnimIV;
    private View mShowAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_objectpropertyanim);

        initView();
    }

    private void initView() {
        this.mShowAnimIV = ViewFinder.findViewById(this, R.id.sdi_showanim_iv);
        this.mShowAnimView = ViewFinder.findViewById(this, R.id.sdi_showanim_view);
    }

    public void startRectAnimation(View v) {
        Rect local = new Rect();
        mShowAnimIV.getLocalVisibleRect(local);
        Rect from = new Rect(local);
        Rect to = new Rect(local);

        from.right = from.left + local.width()/4;
        from.bottom = from.top + local.height()/2;

        to.left = to.right - local.width()/2;
        to.top = to.bottom - local.height()/4;

        if (Build.VERSION.SDK_INT >= 18) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mShowAnimIV, "clipBounds", new RectEvaluator(), from, to);
            objectAnimator.setDuration(C.Int.ANIM_DURATION * 4);
            objectAnimator.start();
        }
    }

    public void startRGBAnimation(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mShowAnimView, "backgroundColor", new ArgbEvaluator(), Color.RED, Color.BLUE);
        objectAnimator.setDuration(C.Int.ANIM_DURATION * 4);
        objectAnimator.start();
    }

    // HSV(Hue, Saturation, Value)是根据颜色的直观特性由A. R. Smith在1978年创建的一种颜色空间, 也称六角锥体模型
    public void startHsvAnimation(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mShowAnimView, "backgroundColor", new HsvEvaluator(), Color.RED, Color.BLUE);
        objectAnimator.setDuration(C.Int.ANIM_DURATION * 4);
        objectAnimator.start();
    }

    public void startSkewAnimation(View v) {
        float scale = (float)mShowAnimIV.getHeight()/(float)mShowAnimIV.getDrawable().getIntrinsicHeight();
        Matrix from = new Matrix();
        from.setScale(scale, scale);
        from.postSkew(-0.5f, 0.0f);
        Matrix to = new Matrix(mShowAnimIV.getMatrix());
        to.setScale(scale, scale);
        to.postSkew(0.5f, 0.0f);

        mShowAnimIV.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix start = new Matrix();
        start.setScale(scale, scale);
        mShowAnimIV.setImageMatrix(start);

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mShowAnimIV, "imageMatrix", new MatrixEvaluator(), from, to);
        objectAnimator.setDuration(C.Int.ANIM_DURATION);
        objectAnimator.setRepeatCount(5);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
    }

}
