package com.cheng.multithreadstudy.sunframework.base;

import android.content.Context;
import android.os.Bundle;

import com.cheng.multithreadstudy.sunframework.proxy.MessageProxy;
import com.cheng.multithreadstudy.sunframework.proxy.ModelMap;
import com.cheng.multithreadstudy.sunframework.proxy.common.IActivityLifecycle;
import com.cheng.multithreadstudy.sunframework.proxy.handler.AsyncObjectHandler;
import com.cheng.multithreadstudy.sunframework.proxy.helper.ObjectHelper;
import com.cheng.multithreadstudy.sunframework.proxy.common.IRefreshBack;

/**
 * Created by sunfusheng on 15/11/5.
 */
public class BaseAsyncObject<T extends BaseControl> implements IActivityLifecycle, IRefreshBack {

    protected T mControl;
    protected MessageProxy messageProxy;
    protected ModelMap mModel;
    private ObjectHelper mHelper;

    public void initParams() {
        mModel = mHelper.getModelMap();
        messageProxy = mHelper.getMessageProxy();
        mControl = (T) mHelper.getControl();
    }

    public void initParamManually() {
        mHelper = new ObjectHelper(this, new AsyncObjectHandler(this));
        mHelper.onCreate();
        initParams();
    }

    public void onCreate(Context context) {
        initParamManually();
    }

    @Override
    public void onCreate() {
        initParamManually();
    }

    @Override
    public void onStart() {
        mHelper.onStart();
    }

    @Override
    public void onResume() {
        mHelper.onResume();
    }

    @Override
    public void onPause() {
        mHelper.onPause();
    }

    @Override
    public void onStop() {
        mHelper.onStop();
    }

    @Override
    public void onDestory() {
        mHelper.onDestroy();
    }

    public Context getContext() {
        return mHelper.getContext();
    }

    @Override
    public void onRefresh(int requestCode, Bundle bundle) {

    }
}
