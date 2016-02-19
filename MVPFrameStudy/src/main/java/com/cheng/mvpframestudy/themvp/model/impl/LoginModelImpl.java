package com.cheng.mvpframestudy.themvp.model.impl;

import android.os.Handler;

import com.cheng.mvpframestudy.themvp.model.i.ILoginModel;
import com.cheng.mvpframestudy.themvp.presenter.i.OnLoginListener;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/11/24.
 */
public class LoginModelImpl implements ILoginModel {

    @Override
    public void login(String email, String password, final OnLoginListener listener) {
        // 模拟网络请求返回
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        }, 1000);
    }

    @Override
    public void login(String email, String password) {
            // 模拟网络请求返回
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(LoginModelImpl.this);
                }
            }, 1000);
    }

    @Override
    public String toString() {
        return "I am LoginModelImpl";
    }
}
