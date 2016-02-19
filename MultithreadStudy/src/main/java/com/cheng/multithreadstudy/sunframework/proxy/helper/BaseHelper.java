package com.cheng.multithreadstudy.sunframework.proxy.helper;

import android.content.Context;

import com.cheng.multithreadstudy.sunframework.proxy.MessageProxy;
import com.cheng.multithreadstudy.sunframework.proxy.ModelMap;
import com.cheng.multithreadstudy.sunframework.proxy.handler.BaseHandler;
import com.cheng.multithreadstudy.sunframework.base.BaseControl;
import com.cheng.multithreadstudy.sunframework.proxy.ControlFactory;
import com.cheng.multithreadstudy.sunframework.proxy.common.IRefreshBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseHelper<T extends BaseControl, R extends IRefreshBack> {

    protected R mReferenceObj;
    protected T mControl;
    protected MessageProxy messageProxy;
    protected BaseHandler mHandler;
    protected ModelMap mModel;

    public BaseHelper(R referenceObj, BaseHandler handler) {
        this.mReferenceObj = referenceObj;
        this.mHandler = handler;
    }

    public T getControl() {
        return mControl;
    }

    public MessageProxy getMessageProxy() {
        return messageProxy;
    }

    public ModelMap getModelMap() {
        return mModel;
    }

    public Context getContext() {
        return mHandler.getContext();
    }

    public void onCreate() {
        controlInit();
    }

    public void onStart() {
        if (mControl != null) {
            mControl.onStart();
        }
    }

    public void onResume() {
        if (mControl == null || messageProxy == null) {
            controlInit();
        }
    }

    public void onPause() {
        if (mControl != null) {
            mControl.onPause();
        }
    }

    public void onStop() {
        if (mControl != null) {
            mControl.onStop();
        }
    }

    public void onDestroy() {
        if (mControl != null) {
            mControl.onDestroy();
        }
    }

    private void controlInit() {
        Class<?> clazz;
        clazz = mReferenceObj.getClass();
        generateControl(clazz);
        if (mControl == null) {
            generateControl(clazz.getSuperclass());
        }
    }

    private void generateControl(Class clazz) {
        Type type = clazz.getGenericSuperclass(); //获得带有泛型的父类
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) type; //获得参数化类型，即泛型
            Type[] arrayClasses = p.getActualTypeArguments(); //获取参数化类型的数组，泛型可能有多个

            for (Type item : arrayClasses) {
                if (item instanceof Class) {
                    Class<T> tClass = (Class<T>) item;

                    if (tClass.equals(BaseControl.class) || (tClass.getSuperclass() != null &&
                            tClass.getSuperclass().equals(BaseControl.class))) {
                        messageProxy = new MessageProxy(mHandler);
                        mControl = ControlFactory.getControlInstance(tClass, messageProxy);
                        mModel = new ModelMap();
                        mControl.setModel(mModel);
                        return;
                    }
                }
            }
        }
    }

}
