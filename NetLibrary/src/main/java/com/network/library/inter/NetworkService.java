package com.network.library.inter;

import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.LoginEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscriber;

public interface NetworkService {

    @FormUrlEncoded
    @POST("methodName")
    Observable<LoginEntity> login(@Field("user") String user, @Field("password") String password);
}
