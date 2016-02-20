package com.cheng.networkframestudy.okhttp.frame.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * 李旺成
 * 2016年2月20日08:16:09
 */
public abstract class StringCallback extends Callback<String> {

    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

}
