package com.cheng.networkframestudy.okhttp.sample;

import com.cheng.networkframestudy.okhttp.frame.OkHttpUtil;
import com.cheng.networkframestudy.okhttp.frame.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * 李旺成
 * 2016年2月20日09:28:42
 */
public class NetModel {

    private static final NetModel sInstance = new NetModel();

    private NetModel() {}

    public static NetModel getInstance() {
        return sInstance;
    }

    public void doTaskAsyncGet(final int taskId, String taskUrl, Map<String, String> params, final AsyncHttpCallback asyncHttpCallback) {
        OkHttpUtil
                .get()
                .url(taskUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        asyncHttpCallback.onTaskError(e);
                    }

                    @Override
                    public void onResponse(String response) {
                        asyncHttpCallback.onTaskComplete(taskId, response);
                    }
                });
    }

}
