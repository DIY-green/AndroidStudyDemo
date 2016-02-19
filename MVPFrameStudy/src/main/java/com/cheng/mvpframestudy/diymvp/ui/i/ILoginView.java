package com.cheng.mvpframestudy.diymvp.ui.i;

import android.content.Loader;
import android.database.Cursor;
import android.widget.EditText;

import java.util.List;

/**
 *
 */
public interface ILoginView {
    String getEmail();
    String getPassword();
    void setEmailError(String errorStr);
    void addEmailsToAutoComplete(List<String> emailAddressCollection);
    void setPasswordError(String errorStr);
    void emailRequestFocus();
    void passwordRequestFocus();
    String getStringFromResource(int resId);
    void showProgress(boolean flag);
    void loginSuccess();
    Loader<Cursor> getCursorLoader();
}
