package com.cheng.mvpframestudy.rxjavamvp.ui.i;

import com.cheng.mvpframestudy.rxjavamvp.model.bean.User;

/**
 * Created by Android on 2015/12/4.
 */
public interface IUserView {
    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
