package com.cheng.mvpframestudy.diymvp.presenter.i;

import com.cheng.mvpframestudy.diymvp.model.bean.WeatherBean;

/**
 * Created by Administrator on 2015/2/6.
 * 天气 Presenter接口
 */
public interface IWeatherPresenter {
    /**
     * 获取天气的逻辑
     */
    void getWeather(String cityNO);

}
