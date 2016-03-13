package com.cheng.networkframestudy.retrofit.api;

import com.cheng.networkframestudy.C;
import com.cheng.networkframestudy.retrofit.model.TelphoneOwnershipOfLand;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 手机号归属地查询服务
 */
public interface MobilePhoneService {

    @Headers("apikey:" + C.apikey.BAIDU_API_STORE_KEY)
    @GET(C.apiurl.MOBILE_PHONE_SERVICE)
    Call<TelphoneOwnershipOfLand> getTelphoneOwnershipOfLand(@Query("tel") String tel);
}
