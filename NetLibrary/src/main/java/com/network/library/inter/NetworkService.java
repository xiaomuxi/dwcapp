package com.network.library.inter;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;
import com.network.library.bean.RegisterEntity;
import com.network.library.bean.VerifyCodeEntity;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {

    @POST("ljwy/JSON/HcPlApi01.aspx")
    Observable<BaseEntity> getOrderList(@Query("ApiId") String interfaceCode, @Query("customerId") String customerId, @Query("state") String state);

    @GET("ljwy/JSON/HcPlApi01.aspx")
    Observable<LoginEntity> login(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String pwd);

    @GET("ljwy/JSON/HcPlApi02.aspx")
    Observable<VerifyCodeEntity> sendVerifyCode(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Type") String type);

    @GET("ljwy/JSON/HcPlApi02.aspx")
    Observable<RegisterEntity> register(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String pwd, @Query("NewPassword") String newPwd, @Query("VerificationCode") String VCode, @Query("ID") String id);

    @GET("ljwy/JSON/HcPlApi03.aspx")
    Observable<BaseEntity> modifyPassword(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String Password, @Query("NewPassword") String newPwd, @Query("ID") String deviceType);

    @POST("ljwy/JSON/HcPlApi03.aspx")
    Observable<BaseEntity> modifyUserInfo(@Query("ApiId") String interfaceCode, @Field("ID") String phone, @Field("Sex") String sex, @Field("Name") String name);
}
