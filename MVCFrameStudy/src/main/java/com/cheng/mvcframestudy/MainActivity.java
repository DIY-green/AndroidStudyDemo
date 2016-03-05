package com.cheng.mvcframestudy;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.mvcframestudy.asyncmvc.view.AsyncMVCDemoActivity;
import com.cheng.mvcframestudy.diymvc.controller.DIYMVCDemoActivity;
import com.cheng.mvcframestudy.simplemvc.view.SimpleMVCDemoActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toAsyncMVCDemo(View v) {
        overlay(AsyncMVCDemoActivity.class);
    }

    public void toSimpleMCVDemo(View v) {
        overlay(SimpleMVCDemoActivity.class);
    }

    public void toDIYMCVDemo(View v) {
        overlay(DIYMVCDemoActivity.class);
    }

    /**
     * 说明
     * M层：适合做一些业务逻辑处理，比如数据库存取操作，网络操作，
     * 复杂的算法，耗时的任务等都在model层处理。
     * V层：应用层中处理数据显示的部分，XML布局可以视为V层，
     * 显示Model层的数据结果。
     * C层：在Android中，Activity处理用户交互问题，因此可以认为
     * Activity是控制器，Activity读取V视图层的数据（eg.读取当前
     * EditText控件的数据），控制用户输入（eg.EditText控件数据的输入），
     * 并向Model发送数据请求（eg.发起网络请求等）。
     */

    /**
     * 参考
     * http://www.2cto.com/kf/201506/405766.html
     */
}
