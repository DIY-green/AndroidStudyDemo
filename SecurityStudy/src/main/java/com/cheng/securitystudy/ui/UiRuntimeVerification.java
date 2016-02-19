package com.cheng.securitystudy.ui;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.securitystudy.R;

/**
 * APK保护方法之三：运行时验证
 */
public class UiRuntimeVerification extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_runtimeverification);
    }

    public void toAndroidTechVerify(View v) {
        overlay(UiAndroidTechVerify.class);
    }

    public void toJavaTechVerify(View v) {
        overlay(UiJavaTechVerify.class);
    }

}
