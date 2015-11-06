package com.cheng.animationstudy;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class UiFrameAnim extends AppCompatActivity {

    private ImageView mShowFrameAnimIV;
    private CheckBox mLoadAnimByXmlCB;

    private AnimationDrawable mFrameAnimDrawable;
    private int [] mFramwAnimDrawableResIDs = new int[]{
            R.mipmap.sdd_frameanim_0,
            R.mipmap.sdd_frameanim_1,
            R.mipmap.sdd_frameanim_2,
            R.mipmap.sdd_frameanim_3,
            R.mipmap.sdd_frameanim_4,
            R.mipmap.sdd_frameanim_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_frameanim);

        initView();
        initListener();
    }

    private void initView() {
        this.mShowFrameAnimIV = (ImageView) this.findViewById(R.id.sdi_showframeanim_iv);
        this.mLoadAnimByXmlCB = (CheckBox) this.findViewById(R.id.sdi_loadanimbyxml_cb);

        initFrameAnimDrawable(false);
    }

    private void initListener() {
        this.mLoadAnimByXmlCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initFrameAnimDrawable(isChecked);
            }
        });
    }

    private void initFrameAnimDrawable(boolean isLoadFromXml) {
        if (isLoadFromXml) {
            this.mFrameAnimDrawable = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(), R.drawable.sdd_frameanim);
        } else {
            this.mFrameAnimDrawable = new AnimationDrawable();
            for (int i=0; i< mFramwAnimDrawableResIDs.length; i++) {
                this.mFrameAnimDrawable.addFrame(ContextCompat.getDrawable(getBaseContext(), mFramwAnimDrawableResIDs[i]), 100);
            }
        }
        this.mFrameAnimDrawable.setOneShot(false);
        this.mShowFrameAnimIV.setBackground(mFrameAnimDrawable);
    }

    public void startAnim(View v) {
        if (mFrameAnimDrawable!=null && !mFrameAnimDrawable.isRunning()) {
            mFrameAnimDrawable.start();
        }
    }

    public void stopAnim(View v) {
        if (mFrameAnimDrawable!=null && mFrameAnimDrawable.isRunning()) {
            mFrameAnimDrawable.stop();
        }
    }

}
