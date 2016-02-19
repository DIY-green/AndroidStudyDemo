package com.cheng.multithreadstudy.sunframework.proxy.handler;

import android.app.Activity;
import android.content.Context;

public class AsyncActivityHandler extends BaseHandler<Activity> {

    public AsyncActivityHandler(Activity activity) {
        super(activity);
    }

    @Override
    public Context getContext() {
        return mReference.get();
    }

    @Override
    protected Activity checkAvailability() {
        Activity activity = mReference.get();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        return activity;
    }
}