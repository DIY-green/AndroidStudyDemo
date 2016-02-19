package com.cheng.designpatternstudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.bigtalkdesignpatterns.UiBigTalkDesignPatterns;
import com.cheng.zenofdesignpatterns.UiZenofDesignPatterns;

public class UiMain extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void toBigTalkDesignPattern(View v) {
        overlay(UiBigTalkDesignPatterns.class);
    }

    public void toZenofDesignPattern(View v) {
        overlay(UiZenofDesignPatterns.class);
    }

}
