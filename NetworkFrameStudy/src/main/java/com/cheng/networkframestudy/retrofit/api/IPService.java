package com.cheng.networkframestudy.retrofit.api;


import com.cheng.networkframestudy.retrofit.model.IPDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * IP地址查询服务
 */
public interface IPService {

    @GET("service/getIpInfo.php")
    Call<IPDetail> getIPDetail(@Query("ip") String ip);

}
