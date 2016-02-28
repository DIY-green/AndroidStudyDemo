package com.cheng.mvvmstudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.mvvmstudy.albumrobobinding.ui.activity.UiAlbumHome;
import com.cheng.mvvmstudy.simplerobobinding.ui.activity.UiSimpleMVVMDemo;

public class UiMain extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void toFernadoCejsa(View v) {
        overlay(UiFernadoCejsaMain.class);
    }

    public void toSimpleMVVMDemo(View v) {
        overlay(UiSimpleMVVMDemo.class);
    }

    public void toRobobindingAlbum(View v) {
        overlay(UiAlbumHome.class);
    }

}
