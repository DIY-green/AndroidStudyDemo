package com.cheng.networkframestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.networkframestudy.androidasynchttp.AndroidAsyncHttpActivity;
import com.cheng.networkframestudy.okhttp.OkHttpActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okhttp:
                toOkHttpActivity();
                break;
            case R.id.btn_androidasynchttp:
                toAndroidAsyncHttp();
                break;
        }
    }

    private void toOkHttpActivity() {
        overlay(OkHttpActivity.class);
    }

    private void toAndroidAsyncHttp() {
        overlay(AndroidAsyncHttpActivity.class);
    }

}
