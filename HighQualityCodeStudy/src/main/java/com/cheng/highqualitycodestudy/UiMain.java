package com.cheng.highqualitycodestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.highqualitycodestudy.exceptionhandle.UiHowToHandleException;
import com.cheng.highqualitycodestudy.memoryleakhandle.UiHowToUseHandler;
import com.cheng.improve151suggest.UiI151Suggest;

public class UiMain extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void toI151Suggest(View v) {
        overlay(UiI151Suggest.class);
    }

    public void toHowToHandleException(View v) {
        overlay(UiHowToHandleException.class);
    }

    public void toHowToUseHandler(View v) {
        overlay(UiHowToUseHandler.class);
    }

}
