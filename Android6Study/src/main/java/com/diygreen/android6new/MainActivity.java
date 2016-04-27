package com.diygreen.android6new;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newfeatures:
                overlay(Android6NewFeatureActivity.class);
                break;
            case R.id.btn_newapis:
                overlay(Android6NewApiActivity.class);
                break;
            case R.id.btn_newwidgets:
                overlay(Android6NewWidgetActivity.class);
                break;
        }
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
