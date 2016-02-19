package com.cheng.multithreadstudy.sunframework.proxy.handler;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.cheng.multithreadstudy.sunframework.proxy.MessageArg;
import com.cheng.utils.UiUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseHandler<Re> extends Handler implements IContext {

    protected WeakReference<Re> mReference;
    private Dialog dialog;

    public BaseHandler(Re t) {
        mReference = new WeakReference<Re>(t);
        dialog = new Dialog(getContext());
    }

    @Override
    public void handleMessage(Message msg) {
        Re t = checkAvailability();
        if (t != null) {
            switch (msg.arg1) {
                case MessageArg.ARG1.TOAST_MESSAGE:
                    if (msg.obj instanceof String) {
                        UiUtil.toast(getContext(), msg.obj + "");
                    } else if (msg.obj instanceof Integer) {
                        UiUtil.toast(getContext(), getContext().getString((Integer) msg.obj) + "");
                    } else {
                        UiUtil.toast(getContext(), msg.obj + "");
                    }
                    break;
                case MessageArg.ARG1.CALL_BACK_METHOD:
                    invokeMethod(t, msg);
                    break;
                case MessageArg.ARG1.PROGRESSDIALOG_MESSAGE:
                    switch (msg.arg2) {
                        case 1:
                            dialog.show();
                            break;
                        case 2:
                            dialog.setCancelable(true);
                            dialog.show();
                            break;
                        default:
                            dialog.dismiss();
                            break;
                    }
                default:
                    break;
            }
        }
    }

    private void invokeMethod(Re callClazz, Message msg) {
        try {
            if (!(msg.obj instanceof String)) {
                UiUtil.toast(getContext(), "Method error:" + msg.obj);
                return;
            }
            Method method;
            try {
                method = callClazz.getClass().getMethod(msg.obj + "", Bundle.class);
            } catch (NoSuchMethodException e) {
                invokeNoArgMethod(callClazz, msg);
                return;
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(callClazz, msg.getData());
            }
        } catch (NoSuchMethodException e) {
            UiUtil.toast(getContext(), "NoSuchMethodException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            UiUtil.toast(getContext(), "IllegalAccessException: " + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void invokeNoArgMethod(Re acitivty, Message msg) throws NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Method method = acitivty.getClass().getMethod(msg.obj + "");
        if (method == null) {
            throw new NoSuchMethodException(msg.obj + "");
        }
        method.setAccessible(true);
        method.invoke(acitivty);
    }

    public void onDestroy() {
        dialog.dismiss();
    }

    protected abstract Re checkAvailability();

}
