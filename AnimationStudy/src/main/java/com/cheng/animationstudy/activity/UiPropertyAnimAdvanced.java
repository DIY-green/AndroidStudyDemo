package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.animationstudy.R;

public class UiPropertyAnimAdvanced extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_propertyanimadvanced);
    }

    public void toLayoutAnim(View v) {
        overlay(UiLayoutAnim.class);
    }

    public void toLayoutAnimDefault(View v) {
        overlay(UiLayoutAnimDefault.class);
    }

    public void toLayoutHideShow(View v) {
        overlay(UiLayoutAnimHideShow.class);
    }

    public void toAnimStreamControl(View v) {
        overlay(UiAnimStreamControl.class);
    }

    public void toCommonPropertyAnim(View v) {
        overlay(UiCommonPropertyAnim.class);
    }

    public void toObjectPropertyAnim(View v) {
        overlay(UiObjectPropertyAnim.class);
    }

    public void toAnimByPath(View v) {
        overlay(UiAnimByPath.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(UiPropertyAnimAdvanced.this, clazz);
        startActivity(intent);
    }

}
