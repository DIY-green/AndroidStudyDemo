package com.cheng.animationstudy.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.AnimationUtils;
import com.cheng.animationstudy.customview.LeafLoadingView;
import com.cheng.utils.ViewFinder;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * 叶子飞舞加载动画
 */
public class LeafFlyLoadingAnimActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        View.OnClickListener {

    private static final int REFRESH_PROGRESS = 0x10;

    private LeafLoadingView mLeafLoadingView;
    private SeekBar mAmpireSB;
    private SeekBar mDistanceSB;
    private TextView mMplitudeTV;
    private TextView mDisparityTV;
    private View mFanView;
    private Button mClearProgressBtn;
    private TextView mProgressTV;
    private View mAddProgressView;
    private SeekBar mFloatTimeSB;
    private SeekBar mRotateTimeSB;
    private TextView mFloatTimeTV;
    private TextView mRotateTimeTV;

    private int mProgress = 0;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leafflyloadinganim);
        initView();
        this.mHandler = new InnerHandler(this);
        this.mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 3000);
    }

    private void initView() {
        this.mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        this.mFanView.startAnimation(rotateAnimation);
        this.mClearProgressBtn = ViewFinder.findViewById(this, R.id.btn_clear);
        this.mClearProgressBtn.setOnClickListener(this);

        this.mLeafLoadingView = ViewFinder.findViewById(this, R.id.view_leaf_loading);
        this.mMplitudeTV = ViewFinder.findViewById(this, R.id.tv_ampair);
        this.mMplitudeTV.setText(getString(R.string.current_mplitude,
                mLeafLoadingView.getMiddleAmplitude()));

        this.mDisparityTV = ViewFinder.findViewById(this, R.id.tv_disparity);
        this.mDisparityTV.setText(getString(R.string.current_Disparity,
                mLeafLoadingView.getMplitudeDisparity()));

        this.mAmpireSB = ViewFinder.findViewById(this, R.id.sb_ampair);
        this.mAmpireSB.setOnSeekBarChangeListener(this);
        this.mAmpireSB.setProgress(mLeafLoadingView.getMiddleAmplitude());
        this.mAmpireSB.setMax(50);

        this.mDistanceSB = ViewFinder.findViewById(this, R.id.sb_distance);
        this.mDistanceSB.setOnSeekBarChangeListener(this);
        this.mDistanceSB.setProgress(mLeafLoadingView.getMplitudeDisparity());
        this.mDistanceSB.setMax(20);

        this.mAddProgressView = ViewFinder.findViewById(this, R.id.view_add_progress);
        this.mAddProgressView.setOnClickListener(this);
        this.mProgressTV = ViewFinder.findViewById(this, R.id.tv_progress);

        this.mFloatTimeTV = ViewFinder.findViewById(this, R.id.tv_float_time);
        this.mFloatTimeSB = ViewFinder.findViewById(this, R.id.sb_float_time);
        this.mFloatTimeSB.setOnSeekBarChangeListener(this);
        this.mFloatTimeSB.setMax(5000);
        this.mFloatTimeSB.setProgress((int) mLeafLoadingView.getLeafFloatTime());
        this.mFloatTimeTV.setText(getResources().getString(R.string.tv_current_float_time,
                mLeafLoadingView.getLeafFloatTime()));

        this.mRotateTimeTV = ViewFinder.findViewById(this, R.id.tv_rotate_time);
        this.mRotateTimeSB = ViewFinder.findViewById(this, R.id.sb_rotate_time);
        this.mRotateTimeSB.setOnSeekBarChangeListener(this);
        this.mRotateTimeSB.setMax(5000);
        this.mRotateTimeSB.setProgress((int) mLeafLoadingView.getLeafRotateTime());
        this.mRotateTimeTV.setText(getResources().getString(R.string.tv_current_float_time,
                mLeafLoadingView.getLeafRotateTime()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mAmpireSB) {
            mLeafLoadingView.setMiddleAmplitude(progress);
            mMplitudeTV.setText(getString(R.string.current_mplitude,
                    progress));
        } else if (seekBar == mDistanceSB) {
            mLeafLoadingView.setMplitudeDisparity(progress);
            mDisparityTV.setText(getString(R.string.current_Disparity,
                    progress));
        } else if (seekBar == mFloatTimeSB) {
            mLeafLoadingView.setLeafFloatTime(progress);
            mFloatTimeTV.setText(getResources().getString(R.string.tv_current_float_time,
                    progress));
        }
        else if (seekBar == mRotateTimeSB) {
            mLeafLoadingView.setLeafRotateTime(progress);
            mRotateTimeTV.setText(getResources().getString(R.string.current_rotate_time,
                    progress));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if (v == mClearProgressBtn) {
            clearProgress();
        } else if (v == mAddProgressView) {
            addProgress();
        }
    }

    private void clearProgress() {
        mLeafLoadingView.setProgress(0);
        mHandler.removeCallbacksAndMessages(null);
        mProgress = 0;
    }

    private void addProgress() {
        mProgress++;
        mLeafLoadingView.setProgress(mProgress);
        mProgressTV.setText(String.valueOf(mProgress));
    }

    private void refreshProgress() {
        if (mProgress < 40) {
            mProgress += 1;
            // 随机800ms以内刷新一次
            mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                    new Random().nextInt(800));
            mLeafLoadingView.setProgress(mProgress);
        } else {
            mProgress += 1;
            // 随机1200ms以内刷新一次
            mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                    new Random().nextInt(1200));
            mLeafLoadingView.setProgress(mProgress);
        }
    }

    private static final class InnerHandler extends Handler {

        private WeakReference<LeafFlyLoadingAnimActivity> mActivityWR;

        public InnerHandler(LeafFlyLoadingAnimActivity theActivity) {
            mActivityWR = new WeakReference<LeafFlyLoadingAnimActivity>(theActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    LeafFlyLoadingAnimActivity theActivity = mActivityWR.get();
                    theActivity.refreshProgress();
                    break;
            }
        }
    }

}
