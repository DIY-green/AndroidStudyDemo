package com.cheng.networkframestudy.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheng.networkframestudy.C;
import com.cheng.networkframestudy.R;
import com.cheng.networkframestudy.retrofit.api.IDCardService;
import com.cheng.networkframestudy.retrofit.api.IPService;
import com.cheng.networkframestudy.retrofit.api.MobilePhoneService;
import com.cheng.networkframestudy.retrofit.convert.MyGsonConverter;
import com.cheng.networkframestudy.retrofit.model.IDCardInfo;
import com.cheng.networkframestudy.retrofit.model.IPDetail;
import com.cheng.networkframestudy.retrofit.model.TelphoneOwnershipOfLand;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemoActivity extends AppCompatActivity {

    private TextView mShowResultTV;
    private ProgressBar mLoadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofitdemo);

        initView();
    }

    private void initView() {
        this.mShowResultTV = (TextView) this.findViewById(R.id.tv_showresult);
        this.mLoadingPB = (ProgressBar) this.findViewById(R.id.pb_loading);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ipsearch:
                ipSearch();
                break;
            case R.id.btn_telsearch:
                telSearch();
                break;
            case R.id.btn_idsearch:
                idCardSearch();
                break;
        }
    }

    private void ipSearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C.baseurl.TAOBAO_IP_SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPService ipService = retrofit.create(IPService.class);
        showLoading();
        Call<IPDetail> ipDetailCall = ipService.getIPDetail("63.223.108.42");
        ipDetailCall.enqueue(new Callback<IPDetail>() {
            @Override
            public void onResponse(Call<IPDetail> call, Response<IPDetail> response) {
                hideLoading();
                IPDetail ipDetail = response.body();
                mShowResultTV.setText(ipDetail.toString());
            }

            @Override
            public void onFailure(Call<IPDetail> call, Throwable t) {
                hideLoading();
                mShowResultTV.setText(t.toString());
            }
        });
    }

    private void telSearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C.baseurl.BAIDU_API_STORE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MobilePhoneService mobilePhoneService = retrofit.create(MobilePhoneService.class);
        showLoading();
        Call<TelphoneOwnershipOfLand> telphoneOwnershipOfLandCall = mobilePhoneService.getTelphoneOwnershipOfLand("15111111111");
        telphoneOwnershipOfLandCall.enqueue(new Callback<TelphoneOwnershipOfLand>() {
            @Override
            public void onResponse(Call<TelphoneOwnershipOfLand> call, Response<TelphoneOwnershipOfLand> response) {
                hideLoading();
                TelphoneOwnershipOfLand telphoneOwnershipOfLand = response.body();
                mShowResultTV.setText(telphoneOwnershipOfLand.toString());
            }

            @Override
            public void onFailure(Call<TelphoneOwnershipOfLand> call, Throwable t) {
                hideLoading();
                mShowResultTV.setText("Error");
            }
        });
    }

    private void idCardSearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C.baseurl.BAIDU_API_STORE)
                .addConverterFactory(MyGsonConverter.create())
                .build();
        IDCardService idCardService = retrofit.create(IDCardService.class);
        showLoading();
        Call<IDCardInfo> idCardInfoCall = idCardService.getIDCardInfo("420984198704207896");
        idCardInfoCall.enqueue(new Callback<IDCardInfo>() {
            @Override
            public void onResponse(Call<IDCardInfo> call, Response<IDCardInfo> response) {
                hideLoading();
                IDCardInfo idCardInfo = response.body();
                mShowResultTV.setText(idCardInfo.toString());
            }

            @Override
            public void onFailure(Call<IDCardInfo> call, Throwable t) {
                hideLoading();
                mShowResultTV.setText("Error");
            }
        });
    }

    private void showLoading() {
        mLoadingPB.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mLoadingPB.setVisibility(View.GONE);
    }


}
