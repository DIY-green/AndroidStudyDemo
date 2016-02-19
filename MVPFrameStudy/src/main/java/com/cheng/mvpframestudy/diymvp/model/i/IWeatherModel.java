package com.cheng.mvpframestudy.diymvp.model.i;


import com.cheng.mvpframestudy.diymvp.presenter.i.OnWeatherListener;

/**
 * Created by Administrator on 2015/2/6.
 * 天气Model接口
 */
public interface IWeatherModel {
    void loadWeather(String cityNO, OnWeatherListener listener);
}
