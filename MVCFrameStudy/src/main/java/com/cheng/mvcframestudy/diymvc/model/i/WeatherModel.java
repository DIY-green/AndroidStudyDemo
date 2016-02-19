package com.cheng.mvcframestudy.diymvc.model.i;

import com.cheng.mvcframestudy.diymvc.controller.i.OnWeatherListener;

/**
 * Description:请求网络数据接口
 */
public interface WeatherModel {
    void getWeather(String cityNumber, OnWeatherListener listener);
}
