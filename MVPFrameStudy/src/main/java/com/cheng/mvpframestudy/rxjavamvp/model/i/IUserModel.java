package com.cheng.mvpframestudy.rxjavamvp.model.i;

import com.cheng.mvpframestudy.rxjavamvp.model.bean.User;

import rx.Observable;

/**
 * Created by Android on 2015/12/4.
 */
public interface IUserModel {
    Observable<User> getUser();
}
