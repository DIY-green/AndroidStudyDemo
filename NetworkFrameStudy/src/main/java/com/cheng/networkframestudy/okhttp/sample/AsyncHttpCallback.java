package com.cheng.networkframestudy.okhttp.sample;

/**
 * 李旺成
 * 2016年2月20日09:43:00
 */
public interface AsyncHttpCallback {

    void onTaskComplete(int taskId, String response);
    void onTaskError(Exception e);

}
