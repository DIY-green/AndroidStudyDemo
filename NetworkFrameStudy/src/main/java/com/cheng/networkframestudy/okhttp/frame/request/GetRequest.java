package com.cheng.networkframestudy.okhttp.frame.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 李旺成
 * 2016年2月20日08:19:09
 */
public class GetRequest extends OkHttpRequest {

    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        super(url, tag, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.get().build();
    }


}
