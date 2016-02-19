package com.cheng.multithreadstudy;

import android.app.Application;

/**
 * Created by Administrator on 2015/11/21.
 */
public class MultithreadStudyApp extends Application {

    private static MultithreadStudyApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MultithreadStudyApp getInstance() {
        return sInstance;
    }
}
