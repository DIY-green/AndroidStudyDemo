package com.cheng.networkframestudy.androidasynchttp;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.networkframestudy.R;
import com.cheng.networkframestudy.androidasynchttp.sample.AndroidAsyncHttpDemoActivity;

public class AndroidAsyncHttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidasynchttp);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toandroidasynchttpdemo:
                toAndroidAsyncHttpDemo();
                break;
        }
    }

    private void toAndroidAsyncHttpDemo() {
        overlay(AndroidAsyncHttpDemoActivity.class);
    }
}
