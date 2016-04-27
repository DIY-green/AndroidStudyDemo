package com.diygreen.andoid4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switchdemo:
                startSwitchDemo();
                break;
            case R.id.btn_popupmenudemo:
                startPopupMenuDemo();
                break;
            case R.id.btn_spacedemo:
                startSpaceDemo();
                break;
            case R.id.btn_gridlayoutdemo:
                startGridLayoutDemo();
                break;
            case R.id.btn_textureviewdemo:
                startTextureViewDemo();
                break;
        }
    }

    private void startSwitchDemo() {
        overlay(SwicthDemoActivity.class);
    }

    private void startPopupMenuDemo() {
        overlay(PopupMenuDemoActivity.class);
    }

    private void startSpaceDemo() {
        overlay(SpaceDemoActivity.class);
    }

    private void startGridLayoutDemo() {
        overlay(GridLayoutDemoActivity.class);
    }

    private void startTextureViewDemo() {
        overlay(TextureViewDemoActivity.class);
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
