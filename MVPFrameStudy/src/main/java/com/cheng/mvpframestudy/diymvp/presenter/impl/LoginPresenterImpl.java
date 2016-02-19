package com.cheng.mvpframestudy.diymvp.presenter.impl;

import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.diymvp.presenter.i.ILoginPresenter;
import com.cheng.mvpframestudy.diymvp.ui.i.ILoginView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private UserLoginTask mAuthTask = null;

    private ILoginView mLoginView;

    public LoginPresenterImpl(ILoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void login() {
        attemptLogin();
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mLoginView.setEmailError(null);
        mLoginView.setPasswordError(null);

        // Store values at the time of the login attempt.
        String email = mLoginView.getEmail();
        String password = mLoginView.getPassword();

        boolean cancel = false;
        int focusView = 0;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mLoginView.setPasswordError(mLoginView.getStringFromResource(R.string.error_invalid_password));
            focusView = 1;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mLoginView.setEmailError(mLoginView.getStringFromResource(R.string.error_field_required));
            focusView = 0;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mLoginView.setEmailError(mLoginView.getStringFromResource(R.string.error_invalid_email));
            focusView = 0;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            if (focusView == 0) {
                mLoginView.emailRequestFocus();
            } else {
                mLoginView.passwordRequestFocus();
            }
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginView.showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return mLoginView.getCursorLoader();
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        mLoginView.addEmailsToAutoComplete(emailAddressCollection);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    public interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            SystemClock.sleep(3000);
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mLoginView.showProgress(false);
            if (success) {
                mLoginView.loginSuccess();
            } else {
                mLoginView.setPasswordError(mLoginView.getStringFromResource(R.string.error_incorrect_password));
                mLoginView.passwordRequestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            mLoginView.showProgress(false);
        }
    }

}
