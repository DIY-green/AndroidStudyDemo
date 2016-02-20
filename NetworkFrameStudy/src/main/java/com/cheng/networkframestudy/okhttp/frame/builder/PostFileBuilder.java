package com.cheng.networkframestudy.okhttp.frame.builder;

import com.cheng.networkframestudy.okhttp.frame.request.PostFileRequest;
import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 李旺成
 * 2016年2月20日08:11:52
 */
public class PostFileBuilder extends OkHttpRequestBuilder {

    private File mFile;
    private MediaType mMediaType;

    public OkHttpRequestBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFileRequest(mUrl, mTag, mParamMap, mHeaderMap, mFile, mMediaType).build();
    }

    @Override
    public PostFileBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public PostFileBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public PostFileBuilder params(Map<String, String> params) {
        this.mParamMap = params;
        return this;
    }

    @Override
    public PostFileBuilder addParams(String key, String val) {
        if (this.mParamMap == null) {
            mParamMap = new LinkedHashMap<>();
        }
        mParamMap.put(key, val);
        return this;
    }

    @Override
    public PostFileBuilder headers(Map<String, String> headers) {
        this.mHeaderMap = headers;
        return this;
    }

    @Override
    public PostFileBuilder addHeader(String key, String val) {
        if (this.mHeaderMap == null) {
            mHeaderMap = new LinkedHashMap<>();
        }
        mHeaderMap.put(key, val);
        return this;
    }
}
