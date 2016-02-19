package com.cheng.multithreadstudy.sunframework.proxy.helper;

import com.cheng.multithreadstudy.sunframework.base.BaseAsyncObject;
import com.cheng.multithreadstudy.sunframework.base.BaseControl;
import com.cheng.multithreadstudy.sunframework.proxy.handler.BaseHandler;

/**
 * Created by sunfusheng on 15/11/5.
 */
public class ObjectHelper<T extends BaseControl, R extends BaseAsyncObject> extends BaseHelper<T, R> {

    public ObjectHelper(R referenceObj, BaseHandler handler) {
        super(referenceObj, handler);
    }
}
