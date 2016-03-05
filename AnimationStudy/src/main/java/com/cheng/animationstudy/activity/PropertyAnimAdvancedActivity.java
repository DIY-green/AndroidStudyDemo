package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.animationstudy.R;

public class PropertyAnimAdvancedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyanimadvanced);
    }

    public void toLayoutAnim(View v) {
        overlay(LayoutAnimActivity.class);
    }

    public void toLayoutAnimDefault(View v) {
        overlay(LayoutAnimDefaultActivity.class);
    }

    public void toLayoutHideShow(View v) {
        overlay(LayoutAnimHideShowActivity.class);
    }

    public void toAnimStreamControl(View v) {
        overlay(AnimStreamControlActivity.class);
    }

    public void toCommonPropertyAnim(View v) {
        overlay(CommonPropertyAnimActivity.class);
    }

    public void toObjectPropertyAnim(View v) {
        overlay(ObjectPropertyAnimActivity.class);
    }

    public void toAnimByPath(View v) {
        overlay(AnimByPathActivity.class);
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(PropertyAnimAdvancedActivity.this, clazz);
        startActivity(intent);
    }

}
