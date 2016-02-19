package com.cheng.mvvmstudy;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.mvvmstudy.albumrobobinding.ViewBindingForView;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.api.impl.MemoryAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.model.TestData;
import com.cheng.mvvmstudy.albumrobobinding.ui.activity.UiAlbumHome;
import com.cheng.mvvmstudy.simplerobobinding.ui.activity.UiSimpleMVVMDemo;

import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

public class UiMain extends BaseUi {

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
