package com.cheng.mvpframestudy.rxjavamvp.model.impl;

import android.util.Log;

import com.cheng.mvpframestudy.rxjavamvp.model.bean.User;
import com.cheng.mvpframestudy.rxjavamvp.model.i.IUserModel;
import com.cheng.utils.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Android on 2015/12/4.
 */
public class UserModleImpl implements IUserModel {

    static {
        Logger.TAG = "UserModleImpl";
    }

    @Override
    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    // 设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Logger.e(e);
                }
                final User user = new User("Java");
                if (user == null) {
                    subscriber.onError(new Exception("User = null"));
                } else {
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
