package com.cheng.mvpframestudy.themvp.ui.delegate;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.view.AppDelegate;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * View视图层
 */
public class LoginDelegate extends AppDelegate {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    public int getRootLayoutId() {
        return R.layout.ui_themvp_login;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mEmailView = get(R.id.email);
        mPasswordView = get(R.id.password);
        mLoginFormView = get(R.id.login_form);
        mProgressView = get(R.id.login_progress);
        populateAutoComplete();
    }

    private void populateAutoComplete() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) return;
    }

    public String getEmail() {
        return mEmailView.getText().toString();
    }

    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    public void setEmailError(String errorStr) {
        mEmailView.setError(errorStr);
    }

    public void setPasswordError(String errorStr) {
        mPasswordView.setError(errorStr);
    }

    public void addEmailsToAutoComplete(ArrayAdapter<String> adapter) {
        mEmailView.setAdapter(adapter);
    }

    public void emailRequestFocus() {
        mEmailView.requestFocus();
    }

    public void passwordRequestFocus() {
        mPasswordView.requestFocus();
    }

    public String getStringFromResource(int resId) {
        return mEmailView.getContext().getString(resId);
    }


    public void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

}
