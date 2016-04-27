package com.diygreen.andoid4;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;

public class TextureViewDemoActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private static final String TAG = "TextureViewDemoActivity";

    private Spinner mSPSetAlpha;
    private Spinner mSPSetRotation;
    private TextureView mTTVTest;

    private Camera mCamera;
    private Float[] mAlphaValueArr = {0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
    private Float[] mRotationValueArr = {15.0f, 30.0f, 45.0f, 60.0f, 90.0f};
    private float mAlphaValue = 1.0f;
    private float mRotationValue = 90.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureviewdemo);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mSPSetAlpha = (Spinner) this.findViewById(R.id.sp_setalphavalue);
        this.mSPSetRotation = (Spinner) this.findViewById(R.id.sp_setrotationvalue);
        this.mTTVTest = (TextureView) this.findViewById(R.id.ttv_test);
    }

    private void initData() {
        ArrayAdapter<Float> setAlphaAdapter = new ArrayAdapter<Float>(this,android.R.layout.simple_list_item_1, mAlphaValueArr);
        setAlphaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSPSetAlpha.setAdapter(setAlphaAdapter);
        ArrayAdapter<Float> setRotationAdapter = new ArrayAdapter<Float>(this,android.R.layout.simple_list_item_1, mRotationValueArr);
        setRotationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSPSetRotation.setAdapter(setRotationAdapter);
    }

    private void initListener() {
        this.mTTVTest.setSurfaceTextureListener(this);
        this.mSPSetAlpha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAlphaValue = mAlphaValueArr[position];
                mTTVTest.setAlpha(mAlphaValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.mSPSetRotation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mRotationValue = mRotationValueArr[position];
                mTTVTest.setRotation(mRotationValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewTexture(surface);
        } catch (IOException t) {
            Log.e(TAG, t.getMessage());
        }
        mCamera.startPreview();
        mTTVTest.setAlpha(mAlphaValue);
        mTTVTest.setRotation(mRotationValue);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
