package com.cheng.networkframestudy.okhttp.frame.builder;

import com.cheng.networkframestudy.okhttp.frame.request.PostStringRequest;
import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 李旺成
 * 2016年2月20日08:13:32
 */
public class PostStringBuilder extends OkHttpRequestBuilder {

    private String mContent;
    private MediaType mMediaType;

    public PostStringBuilder content(String content) {
        this.mContent = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(mUrl, mTag, mParamMap, mHeaderMap, mContent, mMediaType).build();
    }

    @Override
    public PostStringBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public PostStringBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public PostStringBuilder params(Map<String, String> params) {
        this.mParamMap = params;
        return this;
    }

    @Override
    public PostStringBuilder addParams(String key, String val) {
        if (this.mParamMap == null) {
            mParamMap = new LinkedHashMap<>();
        }
        mParamMap.put(key, val);
        return this;
    }

    @Override
    public PostStringBuilder headers(Map<String, String> headers) {
        this.mHeaderMap = headers;
        return this;
    }

    @Override
    public PostStringBuilder addHeader(String key, String val) {
        if (this.mHeaderMap == null) {
            mHeaderMap = new LinkedHashMap<>();
        }
        mHeaderMap.put(key, val);
        return this;
    }
}
