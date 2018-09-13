package com.network.library.Model;


import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;
import com.network.library.bean.RegisterEntity;
import com.network.library.bean.VerifyCodeEntity;
import com.network.library.bean.WeatherEntity;
import com.network.library.callback.CallBack;
import com.network.library.utils.RetrofitUtil;

import rx.Subscriber;

public class NetworkModel {

    public static void getWeather(String city, CallBack<WeatherEntity> callBack) {
        //获取天气
        Subscriber<WeatherEntity> mEntitySubscriber = new Subscriber<WeatherEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(WeatherEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().getWeather(mEntitySubscriber, city);
    }

    public static void baiduOauth(String client_id, String client_secret, CallBack<BaiduOauthEntity> callBack) {
        //百度 api oauth
        Subscriber<BaiduOauthEntity> mEntitySubscriber = new Subscriber<BaiduOauthEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(BaiduOauthEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().baiduOauth(mEntitySubscriber, client_id, client_secret);
    }

    public static void getOrderList(String apiId, String customId, String state, CallBack<BaseEntity> callBack) {
        //订单例子
        Subscriber<BaseEntity> mEntitySubscriber = new Subscriber<BaseEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().getOrderList(mEntitySubscriber, apiId, customId, state);
    }

    public static void login(String phone, String pwd, CallBack<LoginEntity> callBack) {
        Subscriber<LoginEntity> mEntitySubscriber = new Subscriber<LoginEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(LoginEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().login(mEntitySubscriber, "HC020103", phone, pwd);
    }

    public static void sendVerifyCode(String phone, String type, CallBack<VerifyCodeEntity> callBack) {
        Subscriber<VerifyCodeEntity> mEntitySubscriber = new Subscriber<VerifyCodeEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(VerifyCodeEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().sendVerifyCode(mEntitySubscriber, "HC020501", phone, type);
    }

    public static void register(String phone, String pwd, String pwdAgain, String vCode, CallBack<RegisterEntity> callBack) {
        Subscriber<RegisterEntity> mEntitySubscriber = new Subscriber<RegisterEntity>() {

            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            public void onCompleted() {
                callBack.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
                callBack.onComplete();
            }

            @Override
            public void onNext(RegisterEntity baseEntity) {
                if (!baseEntity.getStatus().equals("1")) {
                    callBack.onError(baseEntity.getMsg());
                    callBack.onComplete();
                } else {
                    callBack.onSuccess(baseEntity);
                }
            }
        };
        RetrofitUtil.getInstance().register(mEntitySubscriber, "HC020101", phone, pwd, pwdAgain, vCode, "Android");
    }
}
