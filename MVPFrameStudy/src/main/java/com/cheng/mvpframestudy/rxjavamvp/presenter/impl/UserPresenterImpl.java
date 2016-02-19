package com.cheng.mvpframestudy.rxjavamvp.presenter.impl;

import android.util.Log;

import com.cheng.mvpframestudy.rxjavamvp.model.bean.User;
import com.cheng.mvpframestudy.rxjavamvp.model.i.IUserModel;
import com.cheng.mvpframestudy.rxjavamvp.model.impl.UserModleImpl;
import com.cheng.mvpframestudy.rxjavamvp.presenter.i.IUserPresenter;
import com.cheng.mvpframestudy.rxjavamvp.ui.i.IUserView;
import com.cheng.utils.Logger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Android on 2015/12/4.
 */
public class UserPresenterImpl implements IUserPresenter {

    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenterImpl(IUserView userView) {
        Logger.TAG = "UserPresenterImpl";
        this.mUserView = userView;
        this.mUserModel = new UserModleImpl();
    }

    @Override
    public void getUser() {
        mUserView.showProgressDialog();
        mUserModel.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserView.hideProgressDialog();
                        mUserView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        mUserView.hideProgressDialog();
                        mUserView.updateView(user);
                    }
                });
    }
}
