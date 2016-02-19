package com.cheng.multithreadstudy.sunframework.proxy.handler;

import android.content.Context;

import com.cheng.multithreadstudy.MultithreadStudyApp;
import com.cheng.multithreadstudy.sunframework.base.BaseAsyncListAdapter;

public class AsyncAdapterHandler extends BaseHandler<BaseAsyncListAdapter> {

    public AsyncAdapterHandler(BaseAsyncListAdapter baseAsyncListAdapter) {
        super(baseAsyncListAdapter);
    }

    @Override
    public Context getContext() {
        BaseAsyncListAdapter adapter = mReference.get();
        if (adapter == null) {
            return MultithreadStudyApp.getInstance();
        } else {
            return adapter.getContext();
        }
    }

    @Override
    protected BaseAsyncListAdapter checkAvailability() {
        BaseAsyncListAdapter mAdapter = mReference.get();
        if (mAdapter == null || mAdapter.getContext()==null) {
            return null;
        }
        return mAdapter;
    }

}
