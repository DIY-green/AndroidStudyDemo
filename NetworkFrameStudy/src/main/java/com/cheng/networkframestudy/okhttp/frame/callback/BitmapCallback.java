package com.cheng.networkframestudy.okhttp.frame.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * 李旺成
 * 2016年2月20日08:14:13
 */
public abstract class BitmapCallback extends Callback<Bitmap> {

    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
