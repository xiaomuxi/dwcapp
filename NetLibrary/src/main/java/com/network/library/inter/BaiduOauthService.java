package com.network.library.inter;


import com.network.library.bean.BaiduOauthEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface BaiduOauthService {

    @FormUrlEncoded
    @POST("token")
    Observable<BaiduOauthEntity> BaiduOauthToken(@Field("grant_type") String grant_type, @Field("client_id") String client_id, @Field("client_secret") String client_secret);
}
