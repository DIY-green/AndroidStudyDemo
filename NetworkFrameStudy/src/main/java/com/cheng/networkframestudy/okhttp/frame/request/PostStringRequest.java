package com.cheng.networkframestudy.okhttp.frame.request;

import com.cheng.networkframestudy.okhttp.frame.util.ExceptionUtil;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 李旺成
 * 2016年2月20日08:22:58
 */
public class PostStringRequest extends OkHttpRequest {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String mContent;
    private MediaType mMediaType;

    public PostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType) {
        super(url, tag, params, headers);
        this.mContent = content;
        this.mMediaType = mediaType;
        if (this.mContent == null) {
            ExceptionUtil.illegalArgument("the mContent can not be null !");
        }
        if (this.mMediaType == null) {
            this.mMediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mMediaType, mContent);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    @Override
    public String toString() {
        return super.toString() + ", requestBody{mContent=" + mContent + "} ";
    }

}
