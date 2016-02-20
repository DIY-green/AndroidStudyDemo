package com.cheng.networkframestudy.okhttp.frame.request;

import com.cheng.networkframestudy.okhttp.frame.OkHttpUtil;
import com.cheng.networkframestudy.okhttp.frame.callback.Callback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 李旺成
 * 2016年2月20日08:23:31
 */
public class RequestCall {

    private OkHttpRequest mOkHttpRequest;
    private Request mRequest;
    private Call mCall;

    private long mReadTimeOut;
    private long mWriteTimeOut;
    private long mConnTimeOut;

    private OkHttpClient mClone;

    public RequestCall(OkHttpRequest request) {
        this.mOkHttpRequest = request;
    }

    public RequestCall readTimeOut(long readTimeOut) {
        this.mReadTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut) {
        this.mWriteTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connTimeOut(long connTimeOut) {
        this.mConnTimeOut = connTimeOut;
        return this;
    }


    public Call generateCall(Callback callback) {
        mRequest = generateRequest(callback);
        if (mReadTimeOut > 0 || mWriteTimeOut > 0 || mConnTimeOut > 0) {
            mReadTimeOut = mReadTimeOut > 0 ? mReadTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;
            mWriteTimeOut = mWriteTimeOut > 0 ? mWriteTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;
            mConnTimeOut = mConnTimeOut > 0 ? mConnTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;
            mClone = OkHttpUtil.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(mReadTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(mWriteTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(mConnTimeOut, TimeUnit.MILLISECONDS)
                    .build();
            mCall = mClone.newCall(mRequest);
        } else {
            mCall = OkHttpUtil.getInstance().getOkHttpClient().newCall(mRequest);
        }
        return mCall;
    }

    private Request generateRequest(Callback callback) {
        return mOkHttpRequest.generateRequest(callback);
    }

    public void execute(Callback callback) {
        generateCall(callback);
        if (callback != null) {
            callback.onBefore(mRequest);
        }

        OkHttpUtil.getInstance().execute(this, callback);
    }

    public Call getCall() {
        return mCall;
    }

    public Request getRequest() {
        return mRequest;
    }

    public OkHttpRequest getOkHttpRequest() {
        return mOkHttpRequest;
    }

    public Response execute() throws IOException {
        generateCall(null);
        return mCall.execute();
    }

    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

}
