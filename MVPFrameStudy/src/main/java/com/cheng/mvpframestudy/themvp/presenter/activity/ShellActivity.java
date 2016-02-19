package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.presenter.fragment.MVPFragment;

/**
 * 为了说明MVPFragment用法而存在，一个普通Activity外壳，
 *
 * @author kymjs (http://www.kymjs.com/) on 10/26/15.
 */
public class ShellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_themvp_shell);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new MVPFragment())
                .commit();
    }
}