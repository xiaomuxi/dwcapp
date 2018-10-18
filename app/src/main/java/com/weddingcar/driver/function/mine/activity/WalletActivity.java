package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.network.library.bean.mine.request.GetBalanceInfoRequest;
import com.network.library.bean.mine.response.GetBalanceInfoEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity implements View.OnClickListener{

    private static int REQUEST_CODE_BALANCE_DETAIL = 1001;

    @BindView(R.id.tv_account_balance)
    TextView tv_account_balance;
    //明细
    @BindView(R.id.tv_account_detail)
    TextView tv_account_detail;
    //提现
    @BindView(R.id.tv_account_cash)
    TextView tv_account_cash;
    @BindView(R.id.tv_bond_detail)
    TextView tv_bond_detail;
    @BindView(R.id.tv_bond_balance)
    TextView tv_bond_balance;
    @BindView(R.id.tv_bond_cash)
    TextView tv_bond_cash;
    @BindView(R.id.tv_bond_recharge)
    TextView tv_bond_recharge;

    UserInfo userInfo;
    NetworkController networkController;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);

        userInfo = SPController.getInstance().getUserInfo();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("钱包");
    }

    @Override
    protected void initView() {
        super.initView();
        networkController = new NetworkController();
        networkController.attachView(getBalanceInfoView);

        tv_account_balance.setText("--");
        tv_bond_balance.setText("--");
        tv_account_detail.setOnClickListener(this);
        tv_account_cash.setOnClickListener(this);
        tv_bond_detail.setOnClickListener(this);
        tv_bond_cash.setOnClickListener(this);
        tv_bond_recharge.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        GetBalanceInfoRequest request = new GetBalanceInfoRequest();
        GetBalanceInfoRequest.Query query = new GetBalanceInfoRequest.Query();
        query.setApiId("HC010101");
        query.setId(userInfo.getUserId());
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_GET_BALANCE_INFO, request);
    }

    private NormalView<GetBalanceInfoEntity> getBalanceInfoView = new NormalView<GetBalanceInfoEntity>() {
        @Override
        public void onSuccess(GetBalanceInfoEntity entity) {
            GetBalanceInfoEntity.Data data = entity.getData().get(0);
            String accountBalance = StringUtils.isEmpty(data.getIntegration() + "") ? "--" : data.getIntegration() + "";
            String bondBalance = StringUtils.isEmpty(data.getCashDeposit() + "") ? "--" : data.getCashDeposit() + "";
            tv_account_balance.setText(accountBalance);
            tv_bond_balance.setText(bondBalance);
        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_account_detail:
                //账户余额明细
                goToBalanceDetailActivity("ACCOUNT");
                break;
            case R.id.tv_account_cash:
                //账户余额提现
                break;
            case R.id.tv_bond_detail:
                //保证金余额明细
                goToBalanceDetailActivity("BOND");
                break;
            case R.id.tv_bond_cash:
                //保证金余额提现
                break;
            case R.id.tv_bond_recharge:
                //保证金余额充值
                goToRechargeActivity();
                break;
        }
    }

    private void goToRechargeActivity() {
        startActivity(new Intent(this, RechargeActivity.class));
    }

    private void goToBalanceDetailActivity(String type) {
        Intent intent = new Intent(this, BalanceDetailActivity.class);
        intent.putExtra("TYPE", type);
        startActivityForResult(intent, REQUEST_CODE_BALANCE_DETAIL);
    }
}
