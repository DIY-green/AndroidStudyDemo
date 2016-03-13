package com.cheng.networkframestudy;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Android on 2016/3/13.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // xUtils3初始化
        initxUtils3();
    }

    private void initxUtils3() {
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }
}
