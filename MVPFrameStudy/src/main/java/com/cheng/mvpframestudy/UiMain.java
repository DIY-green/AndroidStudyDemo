package com.cheng.mvpframestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.mvpframestudy.diymvp.ui.activity.UiLogin;
import com.cheng.mvpframestudy.rxjavamvp.UiRxJavaMVP;
import com.cheng.mvpframestudy.themvp.UiTheMVPDemo;

public class UiMain extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void toDIYMVPDemo(View v) {
        overlay(UiLogin.class);
    }

    public void toTheMVPDemo(View v) {
        overlay(UiTheMVPDemo.class);
    }

    public void toRxJavaMVP(View v) {
        overlay(UiRxJavaMVP.class);
    }
}
