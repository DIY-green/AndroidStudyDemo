package com.cheng.mvpframestudy.rxjavamvp;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.rxjavamvp.ui.activity.UiUser;

public class UiRxJavaMVP extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_rxjavamvp);
    }

    public void toShowUser(View v) {
        overlay(UiUser.class);
    }
}
