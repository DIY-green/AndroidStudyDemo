package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.presenter.ActivityPresenter;
import com.cheng.mvpframestudy.themvp.model.i.ILoginModel;
import com.cheng.mvpframestudy.themvp.model.impl.LoginModelImpl;
import com.cheng.mvpframestudy.themvp.presenter.i.OnLoginListener;
import com.cheng.mvpframestudy.themvp.ui.delegate.LoginDelegate;
import com.cheng.utils.Logger;

import de.greenrobot.event.EventBus;

public class LoginActivity extends ActivityPresenter<LoginDelegate> implements View
        .OnClickListener, OnLoginListener {

    private ILoginModel mLoginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initEventBus();
    }

    private void initData() {
        Logger.TAG = LoginActivity.class.getSimpleName();
        mLoginModel = new LoginModelImpl();
    }

    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        viewDelegate.setOnClickListener(this, R.id.email_sign_in_button);
    }

    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                login();
                break;
        }
    }

    private void login() {
        // Reset errors.
        viewDelegate.setEmailError(null);
        viewDelegate.setPasswordError(null);

        // Store values at the time of the login attempt.
        String email = viewDelegate.getEmail();
        String password = viewDelegate.getPassword();

        boolean cancel = false;
        int focusView = 0;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            viewDelegate.setPasswordError(viewDelegate.getStringFromResource(R.string.error_invalid_password));
            focusView = 1;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            viewDelegate.setEmailError(viewDelegate.getStringFromResource(R.string.error_field_required));
            focusView = 0;
            cancel = true;
        } else if (!isEmailValid(email)) {
            viewDelegate.setEmailError(viewDelegate.getStringFromResource(R.string.error_invalid_email));
            focusView = 0;
            cancel = true;
        }

        if (cancel) {
            if (focusView == 0) {
                viewDelegate.emailRequestFocus();
            } else {
                viewDelegate.passwordRequestFocus();
            }
        } else {
            viewDelegate.showProgress(true);
            mLoginModel.login(email, password, this);
            mLoginModel.login(email, password);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void onEventMainThread(LoginModelImpl loginModel) {
        Logger.e(loginModel.toString());
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this.getApplicationContext(), "Login Success", 1).show();
    }

    @Override
    public void onError() {
        Logger.e("Error");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
