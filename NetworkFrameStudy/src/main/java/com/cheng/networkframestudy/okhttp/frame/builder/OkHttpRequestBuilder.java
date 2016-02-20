package com.cheng.networkframestudy.okhttp.frame.builder;

import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.util.Map;

/**
 * 李旺成
 * 2016年2月20日08:11:22
 */
public abstract class OkHttpRequestBuilder {

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mParamMap;
    protected Map<String, String> mHeaderMap;

    public abstract OkHttpRequestBuilder url(String url);

    public abstract OkHttpRequestBuilder tag(Object tag);

    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

    public abstract OkHttpRequestBuilder headers(Map<String, String> headers);

    public abstract OkHttpRequestBuilder addHeader(String key, String val);

    public abstract RequestCall build();

}
