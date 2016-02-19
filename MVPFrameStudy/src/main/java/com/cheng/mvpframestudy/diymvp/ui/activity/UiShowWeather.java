package com.cheng.mvpframestudy.diymvp.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cheng.base.BaseUi;
import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.diymvp.model.bean.WeatherBean;
import com.cheng.mvpframestudy.diymvp.presenter.i.IWeatherPresenter;
import com.cheng.mvpframestudy.diymvp.presenter.impl.WeatherPresenterImpl;
import com.cheng.mvpframestudy.diymvp.ui.i.IWeatherView;

public class UiShowWeather extends BaseUi implements IWeatherView {

    private Dialog mLoadingDialog;
    private EditText mCityNOInputET;
    private TextView mCityTV;
    private TextView mCityNOTV;
    private TextView mTempTV;
    private TextView mWdTV;
    private TextView mWsTV;
    private TextView mSdTV;
    private TextView mWseTV;
    private TextView mTimeTV;
    private TextView mNjdTV;

    private IWeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_showweather);
        mWeatherPresenter = new WeatherPresenterImpl(this); // 传入WeatherView
        initView();
    }

    private void initView() {
        mCityNOInputET = findViewByID(R.id.et_city_no);
        mCityTV = findViewByID(R.id.tv_city);
        mCityNOTV = findViewByID(R.id.tv_city_no);
        mTempTV = findViewByID(R.id.tv_temp);
        mWdTV = findViewByID(R.id.tv_WD);
        mWsTV = findViewByID(R.id.tv_WS);
        mSdTV = findViewByID(R.id.tv_SD);
        mWseTV = findViewByID(R.id.tv_WSE);
        mTimeTV = findViewByID(R.id.tv_time);
        mNjdTV = findViewByID(R.id.tv_njd);
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setTitle("加载天气中...");
    }

    public void onClick(View v) {
        mWeatherPresenter.getWeather(mCityNOInputET.getText().toString().trim());
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showError() {
        toast("ERROR");
    }

    @Override
    public void setWeatherInfo(WeatherBean weather) {
        WeatherBean.WeatherInfo info = weather.getWeatherinfo();
        mCityTV.setText(info.getCity());
        mCityNOTV.setText(info.getCityid());
        mTempTV.setText(info.getTemp());
        mWdTV.setText(info.getWD());
        mWsTV.setText(info.getWS());
        mSdTV.setText(info.getSD());
        mWseTV.setText(info.getWS());
        mTimeTV.setText(info.getTemp());
        mNjdTV.setText(info.getNjd());
    }
}
