package com.weddingcar.driver.function.mine.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alipay.sdk.app.PayTask;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.mine.request.PayRequest;
import com.network.library.bean.mine.request.UpdatePayResultRequest;
import com.network.library.bean.mine.response.WXPayOrderEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.payment.PayResult;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeActivity extends BaseActivity implements View.OnClickListener, IWXAPIEventHandler {

    @BindView(R.id.et_money)
    EditText et_money;
    @BindView(R.id.btn_zfb)
    Button btn_zfb;
    @BindView(R.id.btn_wx)
    Button btn_wx;

    private IWXAPI api;

    NetworkController getWXPayOrderController;
    NetworkController getALIPayOrderController;
    NetworkController updatePayResultController;

    UserInfo userInfo;
    @Override
    protected void init() {
        super.init();
        userInfo = SPController.getInstance().getUserInfo();

        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, Config.PAY_APP_ID_WX);
        api.handleIntent(getIntent(), this);
        api.registerApp(Config.PAY_APP_ID_WX);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("保证金充值");
    }

    @Override
    protected void initView() {
        super.initView();

        getWXPayOrderController = new NetworkController();
        getALIPayOrderController = new NetworkController();
        updatePayResultController = new NetworkController();
        getWXPayOrderController.attachView(getWXPAYOrderView);
        getALIPayOrderController.attachView(getALIPAYOrderView);
        updatePayResultController.attachView(updatePayResultView);

        btn_zfb.setOnClickListener(this);
        btn_wx.setOnClickListener(this);
    }

    private NormalView<WXPayOrderEntity> getWXPAYOrderView = new NormalView<WXPayOrderEntity>() {
        @Override
        public void onSuccess(WXPayOrderEntity entity) {
            WXPayOrderEntity.Data data = entity.getData().get(0);
            goToWXPay(data);
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    private NormalView<BaseEntity<String>> getALIPAYOrderView = new NormalView<BaseEntity<String>>() {
        @Override
        public void onSuccess(BaseEntity<String> entity) {
            String data = entity.getData();
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(RechargeActivity.this);
                    Map<String, String> result = alipay.payV2(data, true);
                    Log.i("msp", result.toString());

                    Message msg = new Message();
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    private NormalView<BaseEntity<String>> updatePayResultView = new NormalView<BaseEntity<String>>() {
        @Override
        public void onSuccess(BaseEntity<String> entity) {
            String data = entity.getData();
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                UIUtils.showToastSafe("支付成功");
                updatePayResult("成功");
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                UIUtils.showToastSafe("支付失败");
                updatePayResult("失败");
            }
        };
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zfb:
                getPayOrder("1");
                break;
            case R.id.btn_wx:
                getPayOrder("2");
                break;
        }
    }

    private boolean checkInput() {
        if (StringUtils.isEmpty(et_money.getText().toString().trim())) {
            UIUtils.showToastSafe("请输入充值金额!");
            return false;
        }

        return true;
    }

    private void goToWXPay(WXPayOrderEntity.Data data) {
        PayReq request = new PayReq();
        request.appId = data.getAppid();
        request.partnerId = data.getPartnerid();
        request.prepayId= data.getPrepayid();
        request.packageValue = data.getPackageX();
        request.nonceStr= data.getNoncestr();
        request.timeStamp= data.getTimestamp();
        request.sign= data.getSign();
        api.sendReq(request);
    }

    private void getPayOrder(String type) {
        if (!checkInput()) {
            return;
        }

        PayRequest request = new PayRequest();
        PayRequest.Query query = new PayRequest.Query();
        query.setApiId("HC020620");
        query.setUserid(userInfo.getUserId());
        query.setCustomerId(userInfo.getUserId());
        query.setDEVICEID(userInfo.getDeviceId());
        query.setType(type);
        query.setAmount(et_money.getText().toString().trim());
        request.setQuery(query);

        switch (type) {
            case "1":
                getALIPayOrderController.sendRequest(HttpAction.ACTION_GET_ALI_PAY_ORDER, request);
                break;
            case "2":
                getWXPayOrderController.sendRequest(HttpAction.ACTION_GET_WX_PAY_ORDER, request);
                break;
            default:
                UIUtils.showToastSafe("不支持该支付方式");
                break;
        }
    }

    private void updatePayResult(String result) {
        UpdatePayResultRequest request = new UpdatePayResultRequest();
        UpdatePayResultRequest.Query query = new UpdatePayResultRequest.Query();
        query.setApiId("HC020650");
        query.setUserid(userInfo.getUserId());
        query.setID(userInfo.getUserId());
        query.setDEVICEID(userInfo.getDeviceId());
        query.setReslut(result);
        request.setQuery(query);

        updatePayResultController.sendRequest(HttpAction.ACTION_UPDATE_PAY_RESULT, request);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d(TAG, "onPayFinish, errCode = " + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (baseResp.errCode) {
                case 0:
                    UIUtils.showToastSafe("支付成功");
                    updatePayResult("成功");
                    break;
                case -2:
                    UIUtils.showToastSafe("已取消支付");
                    updatePayResult("取消");
                    break;
                    default:
                        UIUtils.showToastSafe("支付失败");
                        updatePayResult("失败");
                        break;
            }
        }


    }
}
