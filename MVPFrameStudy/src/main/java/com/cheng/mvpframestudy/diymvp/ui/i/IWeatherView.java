package com.cheng.mvpframestudy.diymvp.ui.i;

import com.cheng.mvpframestudy.diymvp.model.bean.WeatherBean;

public interface IWeatherView {
    void showLoading();

    void hideLoading();

    void showError();

    void setWeatherInfo(WeatherBean weather);
}