package com.cheng.mvpframestudy.themvp.model.i;

import com.cheng.mvpframestudy.themvp.frame.model.IModel;
import com.cheng.mvpframestudy.themvp.presenter.i.OnLoginListener;

/**
 *
 */
public interface ILoginModel extends IModel {
    void login(String email, String password, OnLoginListener listener);
    void login(String email, String password);
}
