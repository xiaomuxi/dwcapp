package com.network.library.inter;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.LoginEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.RegisterEntity;
import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.bean.user.response.VerifyCodeEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface NetworkService {

    @POST("ljwy/JSON/HcPlApi01.aspx")
    Observable<BaseEntity<List<OrderRunningListEntity>>> getRunningOrderList(@Query("ApiId") String interfaceCode, @Query("id") String id);

    @POST("ljwy/JSON/HcPlApi01.aspx")
    Observable<BaseEntity<List<OrderWaitListEntity>>> getWaitOrderList(@Query("ApiId") String interfaceCode, @Query("customerId") String customerId, @Query("state") String state);

    @GET("ljwy/JSON/HcPlApi01.aspx")
    Observable<LoginEntity> login(@QueryMap Map<String, String> map);

    @GET("ljwy/JSON/HcPlApi01.aspx")
    Observable<LoginEntity> login(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String pwd);

    @GET("ljwy/JSON/HcPlApi02.aspx")
    Observable<VerifyCodeEntity> sendVerifyCode(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Type") String type);

    @GET("ljwy/JSON/HcPlApi02.aspx")
    Observable<VerifyCodeEntity> sendVerifyCode(@QueryMap Map<String, String> map);

    @GET("ljwy/JSON/HcPlApi02.aspx")
    Observable<RegisterEntity> register(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String pwd, @Query("NewPassword") String newPwd, @Query("VerificationCode") String VCode, @Query("ID") String id);

    @GET("ljwy/JSON/HcPlApi03.aspx")
    Observable<BaseEntity> modifyPassword(@Query("ApiId") String interfaceCode, @Query("Tel") String phone, @Query("Password") String Password, @Query("NewPassword") String newPwd, @Query("ID") String deviceType);

    @GET("ljwy/JSON/HcPlApi03.aspx")
    Observable<BaseEntity> modifyPassword(@QueryMap Map<String, String> map);

//    @POST("ljwy/JSON/HcPlApi03.aspx")
//    Observable<BaseEntity> modifyUserInfo(@Query("ApiId") String interfaceCode, @Field("ID") String phone, @Field("Sex") String sex, @Field("Name") String name);

    @FormUrlEncoded
    @POST("ljwy/JSON/HcPlApi03.aspx")
    Observable<BaseEntity> modifyUserInfo(@QueryMap Map<String, String> queryMap, @FieldMap Map<String, String> fieldMap);

    @POST("ljwy/JSON/HcPlApi01.aspx")
    Observable<BaseEntity<List<SignUpInfoEntity>>> getSignUpList(@Query("ApiId") String apiId, @Query("customerId") String customerId, @Query("orderId") String orderId);
}
