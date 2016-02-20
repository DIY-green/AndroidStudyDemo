package com.cheng.networkframestudy.okhttp.frame.request;

import com.cheng.networkframestudy.okhttp.frame.OkHttpUtil;
import com.cheng.networkframestudy.okhttp.frame.builder.PostFormBuilder;
import com.cheng.networkframestudy.okhttp.frame.callback.Callback;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 李旺成
 * 2016年2月20日08:22:34
 */
public class PostFormRequest extends OkHttpRequest {

    private List<PostFormBuilder.FileInput> mFileList;

    public PostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<PostFormBuilder.FileInput> files) {
        super(url, tag, params, headers);
        this.mFileList = files;
    }

    @Override
    protected RequestBody buildRequestBody() {
        if (mFileList == null || mFileList.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder);
            int filesSize = mFileList.size();
            for (int i = 0; i < filesSize; i++) {
                PostFormBuilder.FileInput fileInput = mFileList.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        if (callback == null) return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {

                OkHttpUtil.getInstance().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten * 1.0f / contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private void addParams(MultipartBody.Builder builder) {
        if (mParamMap != null && !mParamMap.isEmpty()) {
            for (String key : mParamMap.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, mParamMap.get(key)));
            }
        }
    }

    private void addParams(FormBody.Builder builder) {
        if (mParamMap == null || mParamMap.isEmpty()) {
            builder.add("1", "1");
            return;
        }
        for (String key : mParamMap.keySet()) {
            builder.add(key, mParamMap.get(key));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (mFileList != null) {
            for (PostFormBuilder.FileInput file : mFileList) {
                sb.append(file.toString() + "  ");
            }
        }
        return sb.toString();
    }
}
