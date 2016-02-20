package com.cheng.networkframestudy.okhttp.frame;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.cheng.networkframestudy.okhttp.frame.builder.GetBuilder;
import com.cheng.networkframestudy.okhttp.frame.builder.PostFileBuilder;
import com.cheng.networkframestudy.okhttp.frame.builder.PostFormBuilder;
import com.cheng.networkframestudy.okhttp.frame.builder.PostStringBuilder;
import com.cheng.networkframestudy.okhttp.frame.callback.Callback;
import com.cheng.networkframestudy.okhttp.frame.cookie.SimpleCookieJar;
import com.cheng.networkframestudy.okhttp.frame.https.HttpsUtil;
import com.cheng.networkframestudy.okhttp.frame.request.RequestCall;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 李旺成
 * 2016年2月20日08:09:34
 */
public class OkHttpUtil {

    public static final String TAG = "OkHttpUtil";
    public static final long DEFAULT_MILLISECONDS = 10000;

    private static OkHttpUtil sInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;                                  // 分发器，将子线程返回结果分发到主线程
    private boolean mIsDebug;                                   // 是否为Debug模式
    private String mTag;                                        // 日志输入的Tag

    private OkHttpUtil() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        // cookie enabled
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        this.mDelivery = new Handler(Looper.getMainLooper());

        if (true) {
            okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }
        this.mOkHttpClient = okHttpClientBuilder.build();
    }

    public OkHttpUtil debug(String tag) {
        this.mIsDebug = true;
        this.mTag = tag;
        return this;
    }

    public static OkHttpUtil getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpUtil();
                }
            }
        }
        return sInstance;
    }

    public Handler getDelivery() {
        return mDelivery;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }


    public void execute(final RequestCall requestCall, Callback callback) {
        if (mIsDebug) {
            if (TextUtils.isEmpty(mTag)) {
                this.mTag = TAG;
            }
            Log.d(mTag, "{method:" + requestCall.getRequest().method() + ", detail:" + requestCall.getOkHttpRequest().toString() + "}");
        }

        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                if (response.code() >= 400 && response.code() <= 599) {
                    try {
                        sendFailResultCallback(call, new RuntimeException(response.body().string()), finalCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                try {
                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback);
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback) {
        if (callback == null) return;
        this.mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e);
                callback.onAfter();
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback) {
        if (callback == null) return;
        this.mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }


    public void setCertificates(InputStream... certificates) {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .sslSocketFactory(HttpsUtil.getSslSocketFactory(certificates, null, null))
                .build();
    }


    public void setConnectTimeout(int timeout, TimeUnit units) {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .connectTimeout(timeout, units)
                .build();
    }
}
