package com.cheng.securitystudy.ui;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.securitystudy.R;

/**
 * APK保护方法之二：代码高级混淆
 */
public class UiCodeSeniorConfusion extends BaseUi {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_codeseniorconfusion);
    }

    public void toJunkCode(View v) {
        overlay(UiJunkCode.class);
    }

    public void toFolderConfusion(View v) {
        overlay(UiFolderConfusion.class);
    }

    public void toVersusJD_GUI(View v) {
        overlay(UiVersusJD_GUI.class);
    }

}
