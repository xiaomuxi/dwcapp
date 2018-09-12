package com.network.library.inter;

import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;

public interface NetworkService {

    @POST("ljwy/JSON/HcPlApi01.aspx")
    Observable<BaseEntity> getOrderList(@Query("ApiId") String interfaceCode, @Query("customerId") String customerId, @Query("state") String state);
}
