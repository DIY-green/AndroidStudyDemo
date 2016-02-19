package com.cheng.multithreadstudy.sunframework.proxy.common;

/**
 * Created by sunfusheng on 15/11/5.
 */
public interface IActivityLifecycle {

    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestory();

}
