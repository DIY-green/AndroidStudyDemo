package com.diygreen.android6new.newwidget2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diygreen.android6new.R;

public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                overlay(TabLayoutDemo1Activity.class);
                break;
            case R.id.btn_demo2:
                overlay(TabLayoutDemo2Activity.class);
                break;
            case R.id.btn_demo3:
                overlay(TabLayoutDemo3Activity.class);
                break;
            case R.id.btn_demo4:
                overlay(TabLayoutDemo4Activity.class);
                break;
            case R.id.btn_demo5:
                overlay(TabLayoutDemo5Activity.class);
                break;
        }
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
