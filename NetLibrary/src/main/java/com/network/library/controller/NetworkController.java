package com.network.library.controller;


import com.network.library.Model.NetworkModel;
import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;
import com.network.library.bean.RegisterEntity;
import com.network.library.bean.VerifyCodeEntity;
import com.network.library.bean.WeatherEntity;
import com.network.library.callback.CallBack;
import com.network.library.view.BaiduOauthView;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderView;
import com.network.library.view.GetWeatherView;
import com.network.library.view.NormalView;

public class NetworkController<V extends BaseNetView> {

    // 持有 MVP 中 View 的引用
    private V iMvpView;


    public NetworkController() {
    }

    public void attachView(V view) {
        this.iMvpView = view;
    }

    public void detachView() {
        this.iMvpView = null;
    }

    public boolean isViewAttached() {
        return iMvpView != null;
    }

    public V getView() {
        return iMvpView;
    }

    /**
     * 获取天气
     *
     * @param city 城市
     */

    public void getWeather(final String city) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getWeather(city, new CallBack<WeatherEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached())
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "getWeather");
            }

            @Override
            public void onSuccess(WeatherEntity data) {
                if (isViewAttached())
                    ((GetWeatherView) iMvpView).onGetWeatherSuccess(data);
            }
        });
    }

    public void baiduOauth(final String client_id, String client_secret, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.baiduOauth(client_id, client_secret, new CallBack<BaiduOauthEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached() && showLoading)
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "baiduOauth");
            }

            @Override
            public void onSuccess(BaiduOauthEntity data) {
                if (isViewAttached())
                    ((BaiduOauthView) iMvpView).onBaiduOauthSuccess(data);
            }
        });
    }

    public void getOrderList(final String apiId, String customId, String state, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getOrderList(apiId, customId, state, new CallBack<BaseEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached() && showLoading)
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "getOrderList");
            }

            @Override
            public void onSuccess(BaseEntity data) {
                if (isViewAttached())
                    ((GetOrderView) iMvpView).onGetOrderListSuccess(data);
            }
        });
    }

    public void login(String phone, String pwd) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.login(phone, pwd, new CallBack<LoginEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached())
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "login");
            }

            @Override
            public void onSuccess(LoginEntity data) {
                if (isViewAttached())
                    ((NormalView) iMvpView).onSuccess(data);
            }
        });
    }

    public void sendVerifyCode(String phone, String type) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.sendVerifyCode(phone, type, new CallBack<VerifyCodeEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached())
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "sendVerifyCode");
            }

            @Override
            public void onSuccess(VerifyCodeEntity data) {
                if (isViewAttached())
                    ((NormalView) iMvpView).onSuccess(data);
            }
        });
    }
    public void register(String phone, String pwd, String pwdAgain, String verifyCode) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.register(phone, pwd, pwdAgain, verifyCode, new CallBack<RegisterEntity>() {
            @Override
            public void onStart() {
                if (isViewAttached())
                    iMvpView.showLoading();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())
                    iMvpView.hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached())
                    iMvpView.onRequestError(msg, "sendVerifyCode");
            }

            @Override
            public void onSuccess(RegisterEntity data) {
                if (isViewAttached())
                    ((NormalView) iMvpView).onSuccess(data);
            }
        });
    }
}
