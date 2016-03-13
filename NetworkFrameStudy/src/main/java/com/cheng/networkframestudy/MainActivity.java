package com.cheng.networkframestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.networkframestudy.androidasynchttp.AndroidAsyncHttpActivity;
import com.cheng.networkframestudy.okhttp.OkHttpActivity;
import com.cheng.networkframestudy.retrofit.RetrofitDemoActivity;
import com.cheng.networkframestudy.xutils.XUtils3DemoActivity;

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
            case R.id.btn_retrofit:
                toRetrofit();
                break;
            case R.id.btn_xutils3:
                toXUtils3();
                break;
        }
    }

    private void toOkHttpActivity() {
        overlay(OkHttpActivity.class);
    }

    private void toAndroidAsyncHttp() {
        overlay(AndroidAsyncHttpActivity.class);
    }

    private void toRetrofit() {
        overlay(RetrofitDemoActivity.class);
    }

    private void toXUtils3() {
        overlay(XUtils3DemoActivity.class);
    }

}
