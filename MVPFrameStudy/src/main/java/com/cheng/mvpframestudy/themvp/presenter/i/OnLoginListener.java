package com.cheng.mvpframestudy.themvp.presenter.i;

/**
 * Created by Administrator on 2015/11/24.
 */
public interface OnLoginListener {
    /**
     * 成功时回调
     */
    void onSuccess();
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();
}
