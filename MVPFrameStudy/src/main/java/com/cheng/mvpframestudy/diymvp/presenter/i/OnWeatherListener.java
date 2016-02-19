package com.cheng.mvpframestudy.diymvp.presenter.i;


import com.cheng.mvpframestudy.diymvp.model.bean.WeatherBean;

/**
 * Created by Administrator on 2015/2/7.
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */
public interface OnWeatherListener {
    /**
     * 成功时回调
     *
     * @param weather
     */
    void onSuccess(WeatherBean weather);
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();

}
