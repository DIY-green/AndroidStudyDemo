package com.cheng.animationstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UiMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public void toDrawableAnim(View v) {
        overlay(UiFrameAnim.class);
    }

    public void toTweenAnim(View v) {
        overlay(UiTweenAnim.class);
    }

    public void toPropertyAnim(View v) {
        overlay(UiPropertyAnim.class);
    }

    public void toAdvanced(View v) {
        overlay(UiAdvanced.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(UiMain.this, clazz);
        startActivity(intent);
    }

}
