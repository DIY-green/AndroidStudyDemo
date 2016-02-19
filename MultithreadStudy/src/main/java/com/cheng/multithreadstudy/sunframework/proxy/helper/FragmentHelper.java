package com.cheng.multithreadstudy.sunframework.proxy.helper;

import com.cheng.multithreadstudy.sunframework.base.BaseControl;
import com.cheng.multithreadstudy.sunframework.proxy.handler.BaseHandler;
import com.cheng.multithreadstudy.sunframework.base.BaseAsyncFragment;

public class FragmentHelper<T extends BaseControl, R extends BaseAsyncFragment> extends BaseHelper<T, R> {

    public FragmentHelper(R refrenceObj, BaseHandler handler) {
        super(refrenceObj, handler);
    }

    public void onDestoryView() {
        if (mControl != null) {
            mControl.onDestroyView();
        }
    }

    public void onAttachView() {}
}
