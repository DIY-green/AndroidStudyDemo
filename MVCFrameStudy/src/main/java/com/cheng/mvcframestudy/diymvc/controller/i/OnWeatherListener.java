package com.cheng.mvcframestudy.diymvc.controller.i;

import com.cheng.mvcframestudy.diymvc.model.bean.WeatherBean;

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
