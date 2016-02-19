package com.cheng.mvpframestudy.rxjavamvp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.cheng.base.BaseUi;
import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.rxjavamvp.model.bean.User;
import com.cheng.mvpframestudy.rxjavamvp.presenter.i.IUserPresenter;
import com.cheng.mvpframestudy.rxjavamvp.presenter.impl.UserPresenterImpl;
import com.cheng.mvpframestudy.rxjavamvp.ui.i.IUserView;
import com.cheng.utils.UiUtil;

public class UiUser extends BaseUi implements IUserView {

    private Button mUserNameBtn;
    private ProgressDialog mLoadingPB;

    private IUserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_user);
        initData();
        initView();
    }

    private void initData() {
        this.mUserPresenter = new UserPresenterImpl(this);
    }

    private void initView() {
        this.mUserNameBtn = findViewByID(R.id.sdi_username_btn);
        this.mLoadingPB = new ProgressDialog(this);
        this.mLoadingPB.setMessage("正在加载，请稍后..");
    }

    public void onClick(View v) {
        mUserPresenter.getUser();
    }

    @Override
    public void updateView(User user) {
        if (user==null || TextUtils.isEmpty(user.getName())) return;
        this.mUserNameBtn.setText(user.getName());
    }

    @Override
    public void showProgressDialog() {
        mLoadingPB.show();
    }

    @Override
    public void hideProgressDialog() {
        mLoadingPB.hide();
    }

    @Override
    public void showError(String msg) {
        UiUtil.toast(this, msg);
    }
}
