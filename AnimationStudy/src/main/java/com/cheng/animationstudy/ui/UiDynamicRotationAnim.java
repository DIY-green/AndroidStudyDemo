package com.cheng.animationstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.DynamicRotationGuideView;

public class UiDynamicRotationAnim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ui_dynamicrotationanim);
        setContentView(new DynamicRotationGuideView(this));
    }

}
