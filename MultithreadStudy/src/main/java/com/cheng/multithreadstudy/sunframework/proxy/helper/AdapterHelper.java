package com.cheng.multithreadstudy.sunframework.proxy.helper;

import com.cheng.multithreadstudy.sunframework.base.BaseAsyncListAdapter;
import com.cheng.multithreadstudy.sunframework.base.BaseControl;
import com.cheng.multithreadstudy.sunframework.proxy.handler.AsyncAdapterHandler;

/**
 * Created by sunfusheng on 15/11/5.
 */
public class AdapterHelper<T extends BaseControl, R extends BaseAsyncListAdapter> extends BaseHelper<T, R> {

    public AdapterHelper(R refrenceObj) {
        super(refrenceObj, new AsyncAdapterHandler(refrenceObj));
    }
}
