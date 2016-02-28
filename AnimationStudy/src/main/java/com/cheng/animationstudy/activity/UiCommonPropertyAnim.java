package com.cheng.animationstudy.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

public class UiCommonPropertyAnim extends AppCompatActivity {

    private ImageView mShowAnimIV;
    private ImageView mShowTransAnimIV;
    private ImageView mPoliceBoxIV;
    private ImageView mPoliceBoxLightIV;
    private RelativeLayout mPoliceBoxRL;
    private CheckBox mShowPoliceBoxCB;
    private CheckBox mShowTransAnimImgCB;

    private boolean mTestSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_commonpropertyanim);

        initView();
        initListener();
    }

    private void initView() {
        this.mShowAnimIV = ViewFinder.findViewById(this, R.id.sdi_showanim_iv);
        this.mShowTransAnimIV = ViewFinder.findViewById(this, R.id.sdi_showtrananim_iv);
        this.mPoliceBoxRL = ViewFinder.findViewById(this, R.id.sdi_policebox_rl);
        this.mPoliceBoxIV = ViewFinder.findViewById(this, R.id.sdi_policebox_iv);
        this.mPoliceBoxLightIV = ViewFinder.findViewById(this, R.id.sdi_policeboxlight_iv);
        this.mShowPoliceBoxCB = ViewFinder.findViewById(this, R.id.sdi_changeimg_cb);
        this.mShowTransAnimImgCB = ViewFinder.findViewById(this, R.id.sdi_showtrananimimg_cb);
    }

    public void showFlipOnVertical(View v) {
        switch (v.getId()) {
            case R.id.sdi_policebox_iv:
            case R.id.sdi_policeboxlight_iv:
                if (mTestSwitch) {
                    ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mShowAnimIV, "alpha", 1.0f, 0.25f, 0.75f, 0.15f, 0.5f, 0.0f);
                    objectAnimator3.setDuration(C.Int.ANIM_DURATION * 10);
                    objectAnimator3.setInterpolator(new LinearInterpolator());
                    objectAnimator3.start();
                } else {
                    ObjectAnimator objectAnimator3_1 = ObjectAnimator.ofFloat(mPoliceBoxIV, "alpha", 1.0f, 1.0f, 0.25f, 0.75f, 0.15f, 0.5f, 0.0f);
                    ObjectAnimator objectAnimator3_2 = ObjectAnimator.ofFloat(mPoliceBoxLightIV, "alpha", 0.0f, 1.0f, 0.0f, 0.75f, 0.0f, 0.5f, 0.0f);
                    AnimatorSet animatorSet3 = new AnimatorSet();
                    animatorSet3.playTogether(
                            objectAnimator3_1,
                            objectAnimator3_2
                    );
                    animatorSet3.setDuration(C.Int.ANIM_DURATION * 5);
                    animatorSet3.setInterpolator(new LinearInterpolator());
                    animatorSet3.start();
                }
                mTestSwitch = !mTestSwitch;
                break;
            case R.id.sdi_filponvertical_btn:
                if (mTestSwitch) {
                    Animator animator4 = AnimatorInflater.loadAnimator(this, R.animator.sda_flip_on_vertical);
                    animator4.setTarget(mShowAnimIV);
                    animator4.start();
                } else {
                    ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mShowAnimIV, "rotationY", 0.0f, 360.0f);
                    objectAnimator4.setDuration(C.Int.ANIM_DURATION);
                    objectAnimator4.start();
                }
                mTestSwitch = !mTestSwitch;
                break;
        }
    }

    public void startTransAnimation(View v) {
        float scale = getResources().getDisplayMetrics().density;

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mShowTransAnimIV, "x", 20.0f*scale, 220.0f*scale);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mShowTransAnimIV, "y", 20.0f*scale, 220.0f*scale);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mShowTransAnimIV, "x", 220.0f*scale, 20.0f*scale);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mShowTransAnimIV, "y", 220.0f*scale, 20.0f*scale);

        objectAnimator1.setDuration(C.Int.ANIM_DURATION);
        objectAnimator2.setDuration(C.Int.ANIM_DURATION);
        objectAnimator3.setDuration(C.Int.ANIM_DURATION * 2);
        objectAnimator4.setDuration(C.Int.ANIM_DURATION * 2);

        objectAnimator1.setInterpolator(new AccelerateInterpolator());
        objectAnimator2.setInterpolator(new DecelerateInterpolator());
        objectAnimator3.setInterpolator(new LinearInterpolator());
        objectAnimator4.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();

        //  Long version to set up animation set
        //    animSet.play(anim1).before(anim2);
        //    animSet.play(anim3).after(anim2);
        //    animSet.play(anim3).with(anim4);
        //    animSet.play(anim1).after(500);

        animatorSet.play(objectAnimator1).before(objectAnimator2).after(C.Int.ANIM_DURATION);
        animatorSet.play(objectAnimator3).after(objectAnimator2).with(objectAnimator4);

        animatorSet.start();
    }

    private void initListener() {
        mShowPoliceBoxCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mShowAnimIV.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                mPoliceBoxRL.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            }
        });
        mShowTransAnimImgCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mShowAnimIV.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                mPoliceBoxRL.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                mShowTransAnimIV.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            }
        });
    }

}
