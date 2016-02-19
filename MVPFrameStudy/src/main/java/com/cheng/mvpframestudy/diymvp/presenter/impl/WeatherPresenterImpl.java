package com.cheng.mvpframestudy.diymvp.presenter.impl;


import com.cheng.mvpframestudy.diymvp.model.bean.WeatherBean;
import com.cheng.mvpframestudy.diymvp.model.i.IWeatherModel;
import com.cheng.mvpframestudy.diymvp.model.impl.WeatherModelImpl;
import com.cheng.mvpframestudy.diymvp.presenter.i.OnWeatherListener;
import com.cheng.mvpframestudy.diymvp.presenter.i.IWeatherPresenter;
import com.cheng.mvpframestudy.diymvp.ui.i.IWeatherView;

/**
 * 天气 Prestener实现
 */
public class WeatherPresenterImpl implements IWeatherPresenter, OnWeatherListener {
    /*Presenter作为中间层，持有View和Model的引用*/
    private IWeatherView weatherView;
    private IWeatherModel weatherModel;

    public WeatherPresenterImpl(IWeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl();
    }

    @Override
    public void getWeather(String cityNO) {
        weatherView.showLoading();
        weatherModel.loadWeather(cityNO, this);
    }

    @Override
    public void onSuccess(WeatherBean weather) {
        weatherView.hideLoading();
        weatherView.setWeatherInfo(weather);
    }

    @Override
    public void onError() {
        weatherView.hideLoading();
        weatherView.showError();
    }
}
