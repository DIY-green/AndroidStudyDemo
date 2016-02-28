package com.cheng.designpatternstudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.bigtalkdesignpatterns.BigTalkDesignPatternsActivity;
import com.cheng.zenofdesignpatterns.ZenofDesignPatternsActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toBigTalkDesignPattern(View v) {
        overlay(BigTalkDesignPatternsActivity.class);
    }

    public void toZenofDesignPattern(View v) {
        overlay(ZenofDesignPatternsActivity.class);
    }

}
