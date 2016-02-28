package com.cheng.securitystudy.activity;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.securitystudy.R;

/**
 * APK保护方法之三：运行时验证
 */
public class RuntimeVerificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtimeverification);
    }

    public void toAndroidTechVerify(View v) {
        overlay(AndroidTechVerifyActivity.class);
    }

    public void toJavaTechVerify(View v) {
        overlay(JavaTechVerifyActivity.class);
    }

}
