package com.cheng.networkframestudy.androidasynchttp.frame;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 李旺成
 * 2016年2月21日08:59:54
 */
public class AsyncHttpUtil {

    public final static int TIMEOUT_CONNECTION = 20000;// 连接超时时间
    public final static int TIMEOUT_SOCKET = 20000;// socket超时

    public static AsyncHttpClient getHttpClient() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(TIMEOUT_CONNECTION);
        client.setResponseTimeout(TIMEOUT_SOCKET);
        return client;
    }

    public static void get(String url, AsyncHttpResponseHandler handler) {
        getHttpClient().get(url, handler);
        log(new StringBuilder("GET ").append(url).toString());
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        getHttpClient().get(url, params, handler);
        log(new StringBuilder("GET ").append(url).append("?").append(params).toString());
    }

    public static void post(String url, AsyncHttpResponseHandler handler) {
        getHttpClient().post(url, handler);
        log(new StringBuilder("POST ").append(url).append("?").toString());
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        getHttpClient().post(url, params, handler);
        log(new StringBuilder("POST ").append(url).append("?").append(params).toString());
    }

    /**
     * 上传文件
     *
     * @return
     */
    public static void upLoadFile(String url, File file, AsyncHttpResponseHandler handler) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("username", "张鸿洋");
        params.put("password", "123");
        params.put("mFile", file);
        AsyncHttpClient client = getHttpClient();
        client.addHeader("Content-disposition", "mFile=\"" + file.getName() + "\"");
        client.addHeader("APP-Key", "APP-Secret222");
        client.addHeader("APP-Secret", "APP-Secret111");
        client.post(url, params, handler);
    }

    private static void log(String log) {
        Log.d("http", log);
    }
}
