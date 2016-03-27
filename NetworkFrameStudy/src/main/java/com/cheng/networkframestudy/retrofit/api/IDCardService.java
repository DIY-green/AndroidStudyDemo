package com.cheng.networkframestudy.retrofit.api;

import com.cheng.networkframestudy.C;
import com.cheng.networkframestudy.retrofit.model.IDCardInfo;
import com.cheng.networkframestudy.retrofit.model.TelphoneOwnershipOfLand;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 身份证查询服务
 */
public interface IDCardService {

    @Headers("apikey:" + C.apikey.BAIDU_API_STORE_KEY)
    @GET(C.apiurl.ID_CARD_SERVICE)
    Call<IDCardInfo> getIDCardInfo(@Query("id") String id);
}
