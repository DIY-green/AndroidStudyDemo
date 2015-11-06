package com.cheng.animationstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cheng.animationstudy.R;

public class UiActivityJumpAim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activityjumpaim);
    }

    public void doFinish(View v) {
        finish();
    }

}
