package com.cheng.networkframestudy.androidasynchttp.sample;

import com.cheng.networkframestudy.androidasynchttp.frame.AsyncHttpUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * 李旺成
 * 2016年2月21日09:11:29
 */
public class AAHTestApi {

    public static void getHtml(String url, AsyncHttpResponseHandler handler) {
        AsyncHttpUtil.get(url, handler);
    }

    public static void postString(String url, String content, AsyncHttpResponseHandler handler) {
        AsyncHttpUtil.post(url + "?" + content, null, handler);
    }

    public static void postFile(String url, File file, AsyncHttpResponseHandler handler) {
        try {
            AsyncHttpUtil.upLoadFile(url, file, handler);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getUser(String url, Map<String, String> params, AsyncHttpResponseHandler handler) {
        RequestParams requestParams = new RequestParams();
        if (params != null) {
            for (String key : params.keySet()) {
                requestParams.put(key, params.get(key));
            }
        }
        AsyncHttpUtil.post(url, requestParams, handler);
    }

    public static void uploadFile(String url, File file, AsyncHttpResponseHandler handler) {
        try {
            AsyncHttpUtil.upLoadFile(url, file, handler);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void downloadFile(String url, AsyncHttpResponseHandler handler) {
        AsyncHttpUtil.get(url, handler);
    }
}
