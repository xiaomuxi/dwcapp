package com.network.library.Model;


import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.WeatherEntity;
import com.network.library.bean.user.response.LoginEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.RegisterEntity;
import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.bean.user.response.VerifyCodeEntity;
import com.network.library.callback.CallBack;
import com.network.library.utils.GsonUtils;
import com.network.library.utils.RetrofitUtil;

import java.net.ConnectException;

import java.util.List;

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

    public static void getRunningOrderList(String apiId, String id, CallBack<BaseEntity<List<OrderRunningListEntity>>> callBack) {
        //订单例子
        Subscriber<BaseEntity<List<OrderRunningListEntity>>> mEntitySubscriber = new Subscriber<BaseEntity<List<OrderRunningListEntity>>>() {

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
        RetrofitUtil.getInstance().getRunningOrderList(mEntitySubscriber, apiId, id);
    }

    public static void getWaitOrderList(String apiId, String customerId, String state, CallBack<BaseEntity<List<OrderWaitListEntity>>> callBack) {
        //订单例子
        Subscriber<BaseEntity<List<OrderWaitListEntity>>> mEntitySubscriber = new Subscriber<BaseEntity<List<OrderWaitListEntity>>>() {

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
        RetrofitUtil.getInstance().getWaitOrderList(mEntitySubscriber, apiId, customerId, state);
    }

    public static void login(String phone, String pwd, CallBack callBack) {
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

    public static void sendRequest(String action, Object queryObj, Object body, CallBack callBack) {
        Subscriber mEntitySubscriber = new Subscriber<BaseEntity>() {

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
                String errorMsg = e.getMessage();
                if (e instanceof ConnectException) {
                    errorMsg = "网络链接失败，请检查网络!";
                }
                callBack.onError(errorMsg);
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
        RetrofitUtil.getInstance().sendRequest(action, mEntitySubscriber, GsonUtils.toJson(queryObj), body);
    }

    public static void getSignUpList(String apiId, String customerId, String orderId, CallBack<BaseEntity<List<SignUpInfoEntity>>> callBack) {
        Subscriber<BaseEntity<List<SignUpInfoEntity>>> mEntitySubscriber = new Subscriber<BaseEntity<List<SignUpInfoEntity>>>() {

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
        RetrofitUtil.getInstance().getSignUpList(mEntitySubscriber, apiId, customerId, orderId);
    }
}
