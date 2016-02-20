package com.cheng.networkframestudy.okhttp.frame.builder;

import com.cheng.networkframestudy.okhttp.frame.request.PostFormRequest;
import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 李旺成
 * 2016年2月20日08:12:17
 */
public class PostFormBuilder extends OkHttpRequestBuilder {

    private List<FileInput> mFiles = new ArrayList<>();

    public PostFormBuilder addFile(String name, String filename, File file) {
        mFiles.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    //
    @Override
    public PostFormBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public PostFormBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public PostFormBuilder params(Map<String, String> params) {
        this.mParamMap = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val) {
        if (this.mParamMap == null) {
            mParamMap = new LinkedHashMap<>();
        }
        mParamMap.put(key, val);
        return this;
    }

    @Override
    public PostFormBuilder headers(Map<String, String> headers) {
        this.mHeaderMap = headers;
        return this;
    }

    @Override
    public PostFormBuilder addHeader(String key, String val) {
        if (this.mHeaderMap == null) {
            mHeaderMap = new LinkedHashMap<>();
        }
        mHeaderMap.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFormRequest(mUrl, mTag, mParamMap, mHeaderMap, mFiles).build();
    }

}
