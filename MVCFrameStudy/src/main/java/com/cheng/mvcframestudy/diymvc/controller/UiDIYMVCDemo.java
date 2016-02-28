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

public class UiDIYMVCDemo extends BaseActivity implements OnWeatherListener {
 
    private WeatherModel weatherModel;
    private Dialog loadingDialog;
    private EditText cityNOInput;
    private TextView city;
    private TextView cityNO;
    private TextView temp;
    private TextView wd;
    private TextView ws;
    private TextView sd;
    private TextView wse;
    private TextView time;
    private TextView njd;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_diymvcdemo);
        weatherModel = new WeatherModelImpl();
        initView();
    }
 
    /**
     * 初始化View
     */
    private void initView() {
        cityNOInput = findViewByID(R.id.et_city_no);
        city = findViewByID(R.id.tv_city);
        cityNO = findViewByID(R.id.tv_city_no);
        temp = findViewByID(R.id.tv_temp);
        wd = findViewByID(R.id.tv_WD);
        ws = findViewByID(R.id.tv_WS);
        sd = findViewByID(R.id.tv_SD);
        wse = findViewByID(R.id.tv_WSE);
        time = findViewByID(R.id.tv_time);
        njd = findViewByID(R.id.tv_njd);

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("加载天气中...");
    }
 
    /**
     * 显示结果
     *
     * @param weather
     */
    public void displayResult(WeatherBean weather) {
        WeatherBean.WeatherInfo weatherInfo = weather.getWeatherinfo();
        city.setText(weatherInfo.getCity());
        cityNO.setText(weatherInfo.getCityid());
        temp.setText(weatherInfo.getTemp());
        wd.setText(weatherInfo.getWD());
        ws.setText(weatherInfo.getWS());
        sd.setText(weatherInfo.getSD());
        wse.setText(weatherInfo.getWSE());
        time.setText(weatherInfo.getTime());
        njd.setText(weatherInfo.getNjd());
    }
 
    /**
     * 隐藏进度对话框
     */
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                loadingDialog.show();
                weatherModel.getWeather(cityNOInput.getText().toString().trim(), this);
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