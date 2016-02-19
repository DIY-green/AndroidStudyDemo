package com.cheng.multithreadstudy.sunframework.base;

import android.database.SQLException;
import android.os.Bundle;
import android.os.Message;

import com.cheng.multithreadstudy.sunframework.proxy.MessageArg;
import com.cheng.multithreadstudy.sunframework.proxy.MessageProxy;
import com.cheng.multithreadstudy.sunframework.proxy.ModelMap;
import com.cheng.utils.Logger;

import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class BaseControl {

    protected ModelMap mModel;
    protected MessageProxy mMessageCallBack; //消息分发代理类型

    public BaseControl(MessageProxy mMessageCallBack) {
        this.mMessageCallBack = mMessageCallBack;
    }

    public void setModel(ModelMap model) {
        this.mModel = model;
    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    /**
     * 界面不可见的时候，清除界面相关的消息发送传递
     */
    public void onStop() {
        mMessageCallBack.clearAllMessage();
    }

    public void onDestroyView() {
        mMessageCallBack.clearAllMessage();
    }

    /**
     * 清除数据,比如model中的数据变量什么的。
     */
    public void onDestroy() {
        if (mMessageCallBack != null) {
            mMessageCallBack.clearAllMessage();
        }
    }

    protected void dealWithException(Exception e) {
        if (e instanceof InterruptedIOException) {
            sendToastMessage("下载内容超时");
        } else if (e instanceof UnknownHostException) { //  || e instanceof HttpHostConnectException
            sendToastMessage("无法连接到服务器");
        } else if (e instanceof SQLException) {
            Logger.e("error: ", e);
        } else {
            Logger.e("error: ", e);
        }
    }

    protected void dealWithExceptionMessage(String s) {
        Logger.e("error: ", s + "");
    }

    /**
     * 直接发送toast消息
     *
     * @param toast
     */
    protected void sendToastMessage(String toast) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.TOAST_MESSAGE;
        msg.obj = toast;
        mMessageCallBack.sendMessage(msg);
    }

    protected void sendToastMessage(int toast) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.TOAST_MESSAGE;
        msg.obj = toast;
        mMessageCallBack.sendMessage(msg);
    }

    /**
     * 直接向指定方法发送消息
     *
     * @param method 指定的方法名称（方法可以有参数Bundle 也可以没有回调参数Bundle）
     */
    protected void sendMessage(String method) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        mMessageCallBack.sendMessage(msg);
    }

    /**
     * 直接向指定方法发送消息
     *
     * @param method 指定的方法名称（方法可以有参数Bundle 也可以没有回调参数Bundle）
     * @param delayMillis
     */
    protected void sendMessage(String method, long delayMillis) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        mMessageCallBack.sendMessageDelay(msg, delayMillis);
    }

    /**
     * 直接向指定方法发送消息, 同时将Bundle数据传递过去
     *
     * @param method
     * @param bundle
     */
    protected void sendMessage(String method, Bundle bundle) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        msg.setData(bundle);
        mMessageCallBack.sendMessage(msg);
    }

    /**
     * 直接向指定方法发送消息,同时将Bundle数据传递过去
     *
     * @param method
     * @param bundle
     * @param delayMillis
     */
    protected void sendMessage(String method, Bundle bundle, long delayMillis) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        msg.setData(bundle);
        mMessageCallBack.sendMessageDelay(msg, delayMillis);
    }

    /**
     * 回调参数的方法
     *
     * @param bundle
     * @return
     */
    protected Message getDataMessage(Bundle bundle) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.setData(bundle);
        return msg;
    }

    /**
     * 回调参数的方法
     *
     * @param method
     * @param bundle
     * @return
     */
    protected Message getDataMessage(String method, Bundle bundle) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        msg.setData(bundle);
        return msg;
    }

    /**
     * 回调参数的方法
     *
     * @return
     */
    protected Message getMessage() {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        return msg;
    }

    /**
     * 返回method消息
     *
     * @param method 方法名字
     * @return Message 返回类型
     */
    protected Message getMethodMessage(String method) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.CALL_BACK_METHOD;
        msg.obj = method;
        return msg;
    }

    /**
     * 返回toast消息
     *
     * @param toastMsg
     * @return
     */
    protected Message getToastMessage(String toastMsg) {
        Message msg = mMessageCallBack.obtionMessage(MessageArg.WHAT.UI_MESSAGE);
        msg.arg1 = MessageArg.ARG1.TOAST_MESSAGE;
        msg.obj = toastMsg;
        return msg;
    }
}
