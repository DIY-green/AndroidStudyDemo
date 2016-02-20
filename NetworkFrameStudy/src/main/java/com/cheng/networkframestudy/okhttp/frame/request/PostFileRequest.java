package com.cheng.networkframestudy.okhttp.frame.request;

import com.cheng.networkframestudy.okhttp.frame.util.ExceptionUtil;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 李旺成
 * 2016年2月20日08:20:44
 */
public class PostFileRequest extends OkHttpRequest {

    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    private File mFile;
    private MediaType mMediaType;

    public PostFileRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, File file, MediaType mediaType) {
        super(url, tag, params, headers);
        this.mFile = file;
        this.mMediaType = mediaType;

        if (this.mFile == null) {
            ExceptionUtil.illegalArgument("the mFile can not be null !");
        }
        if (this.mMediaType == null) {
            this.mMediaType = MEDIA_TYPE_STREAM;
        }
    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mMediaType, mFile);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    @Override
    public String toString() {
        return super.toString() + ", requestBody{uploadfilePath=" + mFile.getAbsolutePath() + "} ";
    }

}
