package com.cheng.networkframestudy.okhttp.frame.builder;

import com.cheng.networkframestudy.okhttp.frame.request.GetRequest;
import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 李旺成
 * 2016年2月20日08:26:31
 */
public class GetBuilder extends OkHttpRequestBuilder {

    @Override
    public RequestCall build() {
        if (mParamMap != null) {
            mUrl = appendParams(mUrl, mParamMap);
        }
        return new GetRequest(mUrl, mTag, mParamMap, mHeaderMap).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public GetBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public GetBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        this.mParamMap = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.mParamMap == null) {
            mParamMap = new LinkedHashMap<>();
        }
        mParamMap.put(key, val);
        return this;
    }

    @Override
    public GetBuilder headers(Map<String, String> headers) {
        this.mHeaderMap = headers;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String val) {
        if (this.mHeaderMap == null) {
            mHeaderMap = new LinkedHashMap<>();
        }
        mHeaderMap.put(key, val);
        return this;
    }
}
