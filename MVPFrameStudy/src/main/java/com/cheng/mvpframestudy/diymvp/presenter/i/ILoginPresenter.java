package com.cheng.mvpframestudy.diymvp.presenter.i;

import android.app.LoaderManager;
import android.database.Cursor;

/**
 *
 */
public interface ILoginPresenter extends LoaderManager.LoaderCallbacks<Cursor> {
    void login();
}
