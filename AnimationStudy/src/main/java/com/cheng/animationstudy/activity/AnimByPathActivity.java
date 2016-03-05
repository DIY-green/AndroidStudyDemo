package com.cheng.animationstudy.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.DynamicHeartView;
import com.cheng.animationstudy.customview.FllowerAnimation;
import com.cheng.utils.ViewFinder;

import java.lang.ref.WeakReference;

public class AnimByPathActivity extends AppCompatActivity {

    private static final int START_ANIM = 0;

    private DynamicHeartView mDynamicHeartView;
    private Button mRunBtn;
    private RelativeLayout mAnimationRL;
    private CheckBox mChangeCB;

    private FllowerAnimation mFllowerAnimation;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animbypath);
        initData();
        initView();
        initListener();
        mHandler.sendEmptyMessage(START_ANIM);
    }

    private void initData() {
        mHandler = new InnerHandler(this);
    }

    private void initView() {
        this.mDynamicHeartView = ViewFinder.findViewById(this, R.id.dhv_heart);
        this.mRunBtn = ViewFinder.findViewById(this, R.id.btn_run);
        this.mAnimationRL = ViewFinder.findViewById(this, R.id.rl_animation);
        this.mChangeCB = ViewFinder.findViewById(this, R.id.cb_changepathview);

        this.mFllowerAnimation = new FllowerAnimation(this);

        RelativeLayout.LayoutParams rlLayoutParams
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        this.mFllowerAnimation.setLayoutParams(rlLayoutParams);
        mAnimationRL.addView(mFllowerAnimation);
    }

    private void initListener() {
        this.mChangeCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDynamicHeartView.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                mRunBtn.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mAnimationRL.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (!isChecked) {
                    mHandler.sendEmptyMessage(START_ANIM);
                }
            }
        });
    }

    private void startPathAnim() {
        mDynamicHeartView.startPathAnim(C.Int.ANIM_DURATION * 4);
        mHandler.sendEmptyMessageDelayed(START_ANIM, C.Int.ANIM_DURATION * 6);
    }

    public void showPathAnim(View v) {
        mFllowerAnimation.startAnimation();
    }

    private static class InnerHandler extends Handler {

        private WeakReference<AnimByPathActivity> mTheActivity;

        public InnerHandler(AnimByPathActivity theActivity) {
            mTheActivity = new WeakReference<AnimByPathActivity>(theActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_ANIM:
                    AnimByPathActivity activity = mTheActivity.get();
                    if (activity == null) return;
                    activity.startPathAnim();
                    break;
            }
        }
    }

}
