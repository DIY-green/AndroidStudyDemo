package com.cheng.networkframestudy.diy;

/**
 * 李旺成
 * 2016年2月21日18:36:35
 */
public interface DIYHttpCallback<T> {

    void onSuccess(int taskId, T result);
    void onError(int taskId, Exception exc);
    void onCanceled(int taskId);
    void onLoading(int taskId, long total, long current);

}
