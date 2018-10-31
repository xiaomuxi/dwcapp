package com.network.library.controller;


import com.network.library.Model.NetworkModel;
import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.BaseRequest;
import com.network.library.bean.user.response.OrderInfoEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.RegisterEntity;
import com.network.library.bean.user.response.RobbingInfoEntity;
import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.bean.user.response.VerifyCodeEntity;
import com.network.library.bean.WeatherEntity;
import com.network.library.callback.CallBack;
import com.network.library.view.BaiduOauthView;
import com.network.library.view.BaseNetView;
import com.network.library.view.CancelSignUpView;
import com.network.library.view.DeleteInvalidView;
import com.network.library.view.GetOrderInfoView;
import com.network.library.view.GetOrderView;
import com.network.library.view.GetRobbingView;
import com.network.library.view.GetSignUpListView;
import com.network.library.view.GetWeatherView;
import com.network.library.view.NormalView;
import com.network.library.view.SignUpOrderView;

import java.util.List;

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

    public void getRunningOrderList(final String apiId, String id, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getRunningOrderList(apiId, id, new CallBack<BaseEntity<List<OrderRunningListEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getRunningOrderList");
            }

            @Override
            public void onSuccess(BaseEntity<List<OrderRunningListEntity>> data) {
                if (isViewAttached())
                    ((GetOrderView) iMvpView).onGetOrderListSuccess(data);
            }
        });
    }

    public void getWaitOrderList(final String apiId, String customerId, String state, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getWaitOrderList(apiId, customerId, state, new CallBack<BaseEntity<List<OrderWaitListEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getWaitOrderList");
            }

            @Override
            public void onSuccess(BaseEntity<List<OrderWaitListEntity>> data) {
                if (isViewAttached())
                    ((GetOrderView) iMvpView).onGetOrderListSuccess(data);
            }
        });
    }

    public void getCompleteOrderList(final String apiId, String customerId, String state, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getCompleteOrderList(apiId, customerId, state, new CallBack<BaseEntity<List<OrderWaitListEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getCompleteOrderList");
            }

            @Override
            public void onSuccess(BaseEntity<List<OrderWaitListEntity>> data) {
                if (isViewAttached())
                    ((GetOrderView) iMvpView).onGetOrderListSuccess(data);
            }
        });
    }

    public void login(String phone, String pwd) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.login(phone, pwd, new CallBack<BaseEntity>() {
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
            public void onSuccess(BaseEntity data) {
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

    public void sendRequest(String action, BaseRequest baseRequest) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.sendRequest(action, baseRequest.getQuery(), baseRequest.getBody(), new CallBack<BaseEntity>() {
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
                    iMvpView.onRequestError(msg, action);
            }

            @Override
            public void onSuccess(BaseEntity data) {
                if (isViewAttached())
                    ((NormalView) iMvpView).onSuccess(data);
            }
        });
    }

    public void getSignUpList(final String apiId, String customerId, String orderId) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getSignUpList(apiId, customerId, orderId, new CallBack<BaseEntity<List<SignUpInfoEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getSignUpList");
            }

            @Override
            public void onSuccess(BaseEntity<List<SignUpInfoEntity>> data) {
                if (isViewAttached())
                    ((GetSignUpListView) iMvpView).onGetSignUpListSuccess(data);
            }
        });
    }

    public void getRobbingList(String apiId, String customerId, String carBrandId, String carModelId, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getRobbingList(apiId, customerId, carBrandId, carModelId, new CallBack<BaseEntity<List<RobbingInfoEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getRobbingList");
            }

            @Override
            public void onSuccess(BaseEntity<List<RobbingInfoEntity>> data) {
                if (isViewAttached())
                    ((GetRobbingView) iMvpView).onGetRobbingSuccess(data);
            }
        });
    }

    public void getOrderInfo(String apiId, String orderId, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.getOrderInfo(apiId, orderId, new CallBack<BaseEntity<List<OrderInfoEntity>>>() {
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
                    iMvpView.onRequestError(msg, "getOrderInfo");
            }

            @Override
            public void onSuccess(BaseEntity<List<OrderInfoEntity>> data) {
                if (isViewAttached())
                    ((GetOrderInfoView) iMvpView).onGetOrderInfoSuccess(data);
            }
        });
    }

    public void signUpOrder(String apiId, String customerId, String OrderID, int Amount, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.signUpOrder(apiId, customerId, OrderID, Amount, new CallBack<BaseEntity>() {
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
                    iMvpView.onRequestError(msg, "signUpOrder");
            }

            @Override
            public void onSuccess(BaseEntity data) {
                if (isViewAttached())
                    ((SignUpOrderView) iMvpView).onSignUpOrderSuccess(data);
            }
        });
    }

    public void cancelSignUp(String apiId, String customerId, String orderId, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.cancelSignUp(apiId, customerId, orderId, new CallBack<BaseEntity>() {
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
                    iMvpView.onRequestError(msg, "cancelSignUp");
            }

            @Override
            public void onSuccess(BaseEntity data) {
                if (isViewAttached())
                    ((CancelSignUpView) iMvpView).onCancelSignUpSuccess(data);
            }
        });
    }

    public void deleteInvalidOrder(String apiId, String ID, boolean showLoading) {
        if (!isViewAttached()) {
            return;
        }
        NetworkModel.deleteInvalidOrder(apiId, ID, new CallBack<BaseEntity>() {
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
                    iMvpView.onRequestError(msg, "deleteInvalidOrder");
            }

            @Override
            public void onSuccess(BaseEntity data) {
                if (isViewAttached())
                    ((DeleteInvalidView) iMvpView).onDeleteInvalidOrderSuccess(data);
            }
        });
    }
}
