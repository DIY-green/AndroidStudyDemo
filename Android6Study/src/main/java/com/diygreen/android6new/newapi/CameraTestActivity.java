package com.diygreen.android6new.newapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.diygreen.android6new.R;

public class CameraTestActivity extends AppCompatActivity {

    private Switch mTorchModeSwitch;
    private TextView mShowTorchModeTV;

    private CameraManager mCameraManager;
    private CameraManager.TorchCallback mTorchCallback;
    private String[] mCameraIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameratest);

        initView();
        initData();
        initListener();

    }

    private void initView() {
        this.mTorchModeSwitch = (Switch) this.findViewById(R.id.switch_torchmode);
        this.mShowTorchModeTV = (TextView) this.findViewById(R.id.tv_showtorchmode);
    }

    @TargetApi(23)
    private void initData() {
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        mTorchCallback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeChanged(String cameraId, boolean enabled) {
                super.onTorchModeChanged(cameraId, enabled);
                mShowTorchModeTV.setText("Camera:" + cameraId + " TorchMode change :" + enabled);
            }

            @Override
            public void onTorchModeUnavailable(String cameraId) {
                super.onTorchModeUnavailable(cameraId);
            }
        };
        try {
            mCameraIdList = mCameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            mCameraIdList = null;
            e.printStackTrace();
        }
        // 注册回调监听
        mCameraManager.registerTorchCallback(mTorchCallback, new Handler());
    }

    private void initListener() {
        this.mTorchModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCameraIdList == null) return;
                changeTorchMode(isChecked);
            }
        });
    }

    @TargetApi(23)
    private void changeTorchMode(boolean isChecked) {
        try {
            mCameraManager.setTorchMode(mCameraIdList[0], isChecked);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(23)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraManager.unregisterTorchCallback(mTorchCallback);
    }
}
