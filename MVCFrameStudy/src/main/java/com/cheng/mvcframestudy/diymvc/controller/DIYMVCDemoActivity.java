package com.cheng.mvcframestudy.diymvc.controller;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cheng.base.BaseActivity;
import com.cheng.mvcframestudy.R;
import com.cheng.mvcframestudy.diymvc.controller.i.OnWeatherListener;
import com.cheng.mvcframestudy.diymvc.model.bean.WeatherBean;
import com.cheng.mvcframestudy.diymvc.model.i.WeatherModel;
import com.cheng.mvcframestudy.diymvc.model.impl.WeatherModelImpl;
import com.cheng.utils.UiUtil;

public class DIYMVCDemoActivity extends BaseActivity implements OnWeatherListener {
 
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

    private WeatherModel weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diymvcdemo);
        weatherModel = new WeatherModelImpl();
        initView();
    }

    /**
     * 初始化View
     */
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
 
    /**
     * 显示结果
     *
     * @param weather
     */
    public void displayResult(WeatherBean weather) {
        WeatherBean.WeatherInfo weatherInfo = weather.getWeatherinfo();
        mCityTV.setText(weatherInfo.getCity());
        mCityNOTV.setText(weatherInfo.getCityid());
        mTempTV.setText(weatherInfo.getTemp());
        mWdTV.setText(weatherInfo.getWD());
        mWsTV.setText(weatherInfo.getWS());
        mSdTV.setText(weatherInfo.getSD());
        mWseTV.setText(weatherInfo.getWSE());
        mTimeTV.setText(weatherInfo.getTime());
        mNjdTV.setText(weatherInfo.getNjd());
    }
 
    /**
     * 隐藏进度对话框
     */
    public void hideLoadingDialog() {
        mLoadingDialog.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                mLoadingDialog.show();
                weatherModel.getWeather(mCityNOInputET.getText().toString().trim(), this);
                break;
        }
    }
 
    @Override
    public void onSuccess(WeatherBean weather) {
        hideLoadingDialog();
        displayResult(weather);
    }
 
    @Override
    public void onError() {
        hideLoadingDialog();
        UiUtil.toast(this, "获取天气信息失败");
    }
 
}