package com.network.library.Model;


import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;
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
}
