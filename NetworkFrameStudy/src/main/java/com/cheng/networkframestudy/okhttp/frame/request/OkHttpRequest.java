package com.cheng.networkframestudy.okhttp.frame.request;

import com.cheng.networkframestudy.okhttp.frame.callback.Callback;
import com.cheng.networkframestudy.okhttp.frame.util.ExceptionUtil;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 李旺成
 * 2016年2月20日08:19:20
 */
public abstract class OkHttpRequest {

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mParamMap;
    protected Map<String, String> mHeaderMap;
    protected Request.Builder mBuilder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers) {
        this.mUrl = url;
        this.mTag = tag;
        this.mParamMap = params;
        this.mHeaderMap = headers;

        if (url == null) {
            ExceptionUtil.illegalArgument("mUrl can not be null.");
        }
    }

    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(mBuilder, requestBody);
    }

    private void prepareBuilder() {
        mBuilder.url(mUrl).tag(mTag);
        appendHeaders();
    }

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (mHeaderMap == null || mHeaderMap.isEmpty()) return;
        for (String key : mHeaderMap.keySet()) {
            String value = mHeaderMap.get(key);
            if (value == null) continue;
            headerBuilder.add(key, value);
        }
        mBuilder.headers(headerBuilder.build());
    }

    @Override
    public String toString() {
        return "OkHttpRequest{" +
                "mUrl='" + mUrl + '\'' +
                ", mTag=" + mTag +
                ", mParamMap=" + mParamMap +
                ", mHeaderMap=" + mHeaderMap +
                '}';
    }
}
