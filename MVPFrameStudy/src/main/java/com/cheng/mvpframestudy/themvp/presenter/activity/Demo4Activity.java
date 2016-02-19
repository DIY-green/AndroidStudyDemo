package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.presenter.fragment.Demo4Fragment;

public class Demo4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_themvp_demo4);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new Demo4Fragment())
                .commit();
    }

}
