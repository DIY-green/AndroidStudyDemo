package com.cheng.networkframestudy.okhttp.sample;

import com.cheng.networkframestudy.okhttp.frame.callback.Callback;
import com.google.gson.Gson;

import okhttp3.Response;

import java.io.IOException;
import java.util.List;

/**
 * 李旺成
 * 2016年2月20日08:30:45
 */
public abstract class ListUserCallback extends Callback<List<User>> {

    @Override
    public List<User> parseNetworkResponse(Response response) throws IOException {
        String string = response.body().string();
        List<User> user = new Gson().fromJson(string, List.class);
        return user;
    }


}
