package com.cheng.mvvmstudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.mvvmstudy.albumrobobinding.ui.activity.AlbumHomeActivity;
import com.cheng.mvvmstudy.simplerobobinding.ui.activity.SimpleMVVMDemoActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toFernadoCejsa(View v) {
        overlay(FernadoCejsaMainActivity.class);
    }

    public void toSimpleMVVMDemo(View v) {
        overlay(SimpleMVVMDemoActivity.class);
    }

    public void toRobobindingAlbum(View v) {
        overlay(AlbumHomeActivity.class);
    }

}
