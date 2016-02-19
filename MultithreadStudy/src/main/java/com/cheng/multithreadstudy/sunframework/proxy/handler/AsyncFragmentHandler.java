package com.cheng.multithreadstudy.sunframework.proxy.handler;

import android.content.Context;
import android.support.v4.app.Fragment;

public class AsyncFragmentHandler extends BaseHandler<Fragment> {

    public AsyncFragmentHandler(Fragment o) {
        super(o);
    }

    @Override
    public Context getContext() {
        Fragment fragment = checkAvailability();
        if (fragment != null) {
            return fragment.getActivity();
        } else {
            return null;
        }
    }

    @Override
    protected Fragment checkAvailability() {
        Fragment fragment = mReference.get();
        if (fragment == null || fragment.isRemoving() || fragment.getActivity() == null || fragment.getActivity().isFinishing()) {
            return null;
        } else {
            return fragment;
        }
    }

}
