package com.cheng.securitystudy.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.cheng.base.BaseUi;
import com.cheng.securitystudy.R;
import com.cheng.securitystudy.model.GameControl;
import com.cheng.utils.Logger;

/**
 * apk自我保护的一种实现方式——运行时自篡改dalvik指令
 * 参考
 * http://blog.csdn.net/freshui/article/details/13620647
 * http://bluebox.com/labs/android-security-challenge/
 */
public class UiDexBytecodeTamper extends BaseUi {

    static {
        System.loadLibrary("dexparser");
    }

    private TextView mTestTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_dexbytecodetamper);

        Logger.TAG = "UiDexBytecodeTamper";
        initView();
        initData();
    }

    private void initView() {
        this.mTestTV = findViewByID(R.id.sdi_test_tv);
    }

    private void initData() {
        GameControl res = new GameControl();
        res.setScore(1.0);
        String text = String.format("setScore(1.0), but getScore is %f", res.getScore());
        this.mTestTV.setText(text);
        Logger.e("result="+res.getScore());
    }

}
