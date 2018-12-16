package com.weddingcar.driver.function.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.network.library.bean.mine.request.DrawCashRequest;
import com.network.library.bean.mine.response.DrawCashEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.CheckUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawCashActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_money)
    EditText et_money;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.tv_allowed_money)
    TextView tv_allowed_money;
    @BindView(R.id.tv_all)
    TextView tv_all;

    //"ACCOUNT" | "BOND"
    String type;
    double allowedMoney;
    UserInfo userInfo;
    NetworkController networkController;
    @Override
    protected void init() {
        super.init();
        userInfo = SPController.getInstance().getUserInfo();
        type = getIntent().getStringExtra("TYPE");
        allowedMoney = getIntent().getDoubleExtra("ALLOWED_MONEY", 0);
        setContentView(R.layout.activity_draw_cash);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft(StringUtils.equals(type, "ACCOUNT") ? "余额提现" : "保证金提现");
    }

    @Override
    protected void initView() {
        super.initView();

        networkController = new NetworkController();
        networkController.attachView(drawCashView);

        btn_confirm.setOnClickListener(this);
        tv_all.setOnClickListener(this);

        tv_allowed_money.setText(getResources().getString(R.string.text_allowed_money, allowedMoney+""));
    }


    private NormalView<DrawCashEntity> drawCashView = new NormalView<DrawCashEntity>() {
        @Override
        public void onSuccess(DrawCashEntity entity) {
            DrawCashEntity.Data data = entity.getData().get(0);
            UIUtils.showToastSafe("提现成功！");

        }

        @Override
        public void showLoading() {
            showProcess("正在请求中...");
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

    private void drawCashEvent() {
        if (!checkInput()) {
            return;
        }
        String money = et_account.getText().toString().trim();
        double targetMoney = Double.parseDouble(money);
        if (targetMoney > allowedMoney) {
            UIUtils.showToastSafe("最多允许提现"+allowedMoney+"元");
            return;
        }

        DrawCashRequest request = new DrawCashRequest();
        DrawCashRequest.Query query = new DrawCashRequest.Query();
        query.setApiId("HC020107");
        query.setDEVICEID(userInfo.getDeviceId());
        query.setUserid(userInfo.getUserId());
        query.setCustomerID(userInfo.getUserId());
        query.setBankAccount(et_account.getText().toString().trim());
        query.setBankName(et_name.getText().toString().trim());
        query.setAmount(et_money.getText().toString().trim());
        query.setType(StringUtils.equals(type, "ACCOUNT") ? "余额" : "保证金");
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_DRAW_CASH, request);
    }

    private boolean checkInput() {
        String account = et_account.getText().toString().trim();
        String name = et_name.getText().toString().trim();
        String money = et_money.getText().toString().trim();

        if (StringUtils.isEmpty(account)) {
            UIUtils.showToastSafe("请输入支付宝账号！");
            return false;
        }

        if (StringUtils.isEmpty(name)) {
            UIUtils.showToastSafe("请输入支付宝姓名！");
            return false;
        }

        if (StringUtils.isEmpty(money)) {
            UIUtils.showToastSafe("请输入提现金额！");
            return false;
        }

        if (!CheckUtils.isNumber(money)) {
            UIUtils.showToastSafe("金额格式不正确，请输入数字金额！");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                drawCashEvent();
                break;
            case R.id.tv_all:
                et_money.setText(allowedMoney + "");
                break;
        }
    }
}
