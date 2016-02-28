package com.cheng.highqualitycodestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.highqualitycodestudy.exceptionhandle.HowToHandleExceptionActivity;
import com.cheng.highqualitycodestudy.memoryleakhandle.HowToUseHandlerActivity;
import com.cheng.improve151suggest.I151SuggestActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toI151Suggest(View v) {
        overlay(I151SuggestActivity.class);
    }

    public void toHowToHandleException(View v) {
        overlay(HowToHandleExceptionActivity.class);
    }

    public void toHowToUseHandler(View v) {
        overlay(HowToUseHandlerActivity.class);
    }

}
