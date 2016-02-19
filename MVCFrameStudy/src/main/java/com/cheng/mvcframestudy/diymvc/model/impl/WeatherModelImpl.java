package com.cheng.mvcframestudy.diymvc.model.impl;

import android.os.Handler;

import com.cheng.mvcframestudy.diymvc.controller.i.OnWeatherListener;
import com.cheng.mvcframestudy.diymvc.model.bean.WeatherBean;
import com.cheng.mvcframestudy.diymvc.model.i.WeatherModel;

/**
 * 天气Model实现
 */
public class WeatherModelImpl implements WeatherModel {

    @Override
    public void getWeather(String cityNumber, final OnWeatherListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WeatherBean weatherBean = new WeatherBean();
                WeatherBean.WeatherInfo weatherInfo = new WeatherBean().getWeatherinfo();
                weatherInfo.setCity("北京");
                weatherInfo.setCityid("11111111");
                weatherInfo.setNjd("NJD");
                weatherInfo.setSD("SD");
                weatherInfo.setTemp("TEMP");
                weatherInfo.setTime("2015年11月20日 17:00:31");
                weatherInfo.setWD("WD");
                weatherInfo.setWS("WS");
                weatherInfo.setWSE("WSE");
                weatherBean.setWeatherinfo(weatherInfo);
                listener.onSuccess(weatherBean);
            }
        }, 1500);
    }
}
