package com.cheng.networkframestudy.okhttp.frame.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
/**
 * 李旺成
 * 2016年2月20日08:11:22
 */
public final class SimpleCookieJar implements CookieJar {

    private final List<Cookie> mAllCookieList = new ArrayList<>();

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        mAllCookieList.addAll(cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : mAllCookieList) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}
