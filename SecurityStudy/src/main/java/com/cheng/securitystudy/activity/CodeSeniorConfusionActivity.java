package com.cheng.securitystudy.activity;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.securitystudy.R;

/**
 * APK保护方法之二：代码高级混淆
 */
public class CodeSeniorConfusionActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_codeseniorconfusion);
    }

    public void toJunkCode(View v) {
        overlay(JunkCodeActivity.class);
    }

    public void toFolderConfusion(View v) {
        overlay(FolderConfusionActivity.class);
    }

    public void toVersusJD_GUI(View v) {
        overlay(VersusJD_GUIActivity.class);
    }

}
