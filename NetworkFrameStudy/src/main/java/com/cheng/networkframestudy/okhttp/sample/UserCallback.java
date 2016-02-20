package com.cheng.networkframestudy.okhttp.sample;

import com.cheng.networkframestudy.okhttp.frame.callback.Callback;
import com.google.gson.Gson;

import okhttp3.Response;

import java.io.IOException;

/**
 * 李旺成
 * 2016年2月20日08:32:25
 */
public abstract class UserCallback extends Callback<User> {

    @Override
    public User parseNetworkResponse(Response response) throws IOException {
        String string = response.body().string();
        User user = new Gson().fromJson(string, User.class);
        return user;
    }


}
