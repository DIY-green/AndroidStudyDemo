package com.cheng.mvpframestudy.themvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.presenter.activity.Demo4Activity;
import com.cheng.mvpframestudy.themvp.presenter.activity.Demo5Activity;
import com.cheng.mvpframestudy.themvp.presenter.activity.Demo6Activity;
import com.cheng.mvpframestudy.themvp.presenter.activity.DemoActivity;
import com.cheng.mvpframestudy.themvp.presenter.activity.LoginActivity;
import com.cheng.mvpframestudy.themvp.presenter.activity.ShellActivity;
import com.cheng.mvpframestudy.themvp.presenter.activity.SimpleActivity;

public class UiTheMVPDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_themvpdemo);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, DemoActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ShellActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, Demo4Activity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, Demo5Activity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, Demo6Activity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
