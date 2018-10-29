package com.weddingcar.driver.function.main.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.network.library.bean.mine.request.GetDriverCarInfoRequest;
import com.network.library.bean.mine.request.GetMineInfoRequest;
import com.network.library.bean.mine.response.GetDriverCarInfoEntity;
import com.network.library.bean.mine.response.GetMineInfoEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.CircleImageView;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.activity.CarAuthActivity;
import com.weddingcar.driver.function.mine.activity.CumulativeIncomeActivity;
import com.weddingcar.driver.function.mine.activity.EvaluateActivity;
import com.weddingcar.driver.function.mine.activity.MessageActivity;
import com.weddingcar.driver.function.mine.activity.MineInfoActivity;
import com.weddingcar.driver.function.mine.activity.SettingActivity;
import com.weddingcar.driver.function.mine.activity.UploadCarInfoActivity;
import com.weddingcar.driver.function.mine.activity.WalletActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFragment extends BaseFragment implements View.OnClickListener{

    private String mFragmentTag;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_car_model)
    TextView tv_car_model;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    @BindView(R.id.iv_qr_code)
    ImageView iv_qr_code;
    @BindView(R.id.tv_order_num)
    TextView tv_order_num;
    @BindView(R.id.tv_evaluate)
    TextView tv_evaluate;
    @BindView(R.id.tv_income)
    TextView tv_income;
    @BindView(R.id.tv_complete_order)
    TextView tv_complete_order;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.tv_wallet)
    TextView tv_wallet;
    @BindView(R.id.tv_vip)
    TextView tv_vip;
    @BindView(R.id.tv_car_status)
    TextView tv_car_status;
    @BindView(R.id.ll_car_auth)
    LinearLayout ll_car_auth;
    @BindView(R.id.tv_car_life)
    TextView tv_car_life;
    @BindView(R.id.tv_invite)
    TextView tv_invite;
    @BindView(R.id.tv_help)
    TextView tv_help;
    @BindView(R.id.tv_setting)
    TextView tv_setting;

    LinearLayout mStatusBarView;
    private Unbinder unbinder;

    NetworkController mController;
    NetworkController getDriverCarInfoController;
    UserInfo userInfo;

    private String carAuthStatus = "车辆待认证";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mFragmentTag = getArguments().getString(TAG);
        }
        userInfo = SPController.getInstance().getUserInfo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mController = new NetworkController();
        mController.attachView(getMinInfoView);
        getDriverCarInfoController = new NetworkController();
        getDriverCarInfoController.attachView(getDriverCarInfoView);

        tv_name.setText(userInfo.getName());
        tv_car_model.setText(getResources().getString(R.string.text_mine_car_model, "--", "--"));
        tv_order_num.setText(getResources().getString(R.string.text_order_num, "--"));
        tv_evaluate.setText(getResources().getString(R.string.text_evaluate, "--"));
        tv_income.setText(getResources().getString(R.string.text_income, "--"));
        tv_complete_order.setText(getResources().getString(R.string.text_complete_order, "--"));

        initActionBar();
        tv_wallet.setOnClickListener(this);
        tv_vip.setOnClickListener(this);
        ll_car_auth.setOnClickListener(this);
        tv_car_life.setOnClickListener(this);
        tv_invite.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        tv_income.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        tv_evaluate.setOnClickListener(this);

        initMineInfo();
    }

    @Override
    public void onResume() {
        super.onResume();

        initMineInfo();
    }

    private void initActionBar() {
        initStatusBar();
        ll_mine.addView(mStatusBarView, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initStatusBar() {
        LogUtils.i(TAG, "init status bar");
        int mStatusHeight = UIUtils.getStatusBarHeight(mContext);
        mStatusBarView = new LinearLayout(mContext);
        mStatusBarView.setBackground(ContextCompat.getDrawable(mContext, R.color.bg_main_red));
        mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mStatusHeight));
    }

    public void initMineInfo() {

        // Get mine info
        GetMineInfoRequest request = new GetMineInfoRequest();
        GetMineInfoRequest.Query query = new GetMineInfoRequest.Query();
        query.setApiId("HC010108");
        query.setId(userInfo.getUserId());
        request.setQuery(query);

        mController.sendRequest(HttpAction.ACTION_GET_MINE_INFO, request);


        // Get driver car info
        GetDriverCarInfoRequest request1 = new GetDriverCarInfoRequest();
        GetDriverCarInfoRequest.Query query1 = new GetDriverCarInfoRequest.Query();
        query1.setApiId("HC010305");
        query1.setId(userInfo.getUserId());
        request1.setQuery(query1);

        getDriverCarInfoController.sendRequest(HttpAction.ACTION_GET_DRIVER_CAR_INFO, request1);
    }

    private NormalView<GetMineInfoEntity> getMinInfoView = new NormalView<GetMineInfoEntity>() {
        @Override
        public void onSuccess(GetMineInfoEntity entity) {
            LogUtils.i(TAG, entity.toString());
            GetMineInfoEntity.Data data = entity.getData().get(0);
            String name = StringUtils.isEmpty(data.getName()) ? "--" : data.getName();
            String carModel = StringUtils.checkString(data.getCarBrandName()) + StringUtils.checkString(data.getCarModelName());
            String carColor = StringUtils.isEmpty(data.getCarColorName()) ? "--" : data.getCarColorName();
            String orderNumber = data.getOrderCountOnGoing() + "";
            String evaluate = data.getAppraise() + "";
            String income = data.getIncome() + "";
            String completeOrder = data.getOrderCountHasEnded() + "";
            tv_name.setText(name);
            tv_car_model.setText(getResources().getString(R.string.text_mine_car_model, carModel, carColor));
            tv_order_num.setText(getResources().getString(R.string.text_order_num, orderNumber));
            tv_evaluate.setText(getResources().getString(R.string.text_evaluate, evaluate));
            tv_income.setText(getResources().getString(R.string.text_income, income));
            tv_complete_order.setText(getResources().getString(R.string.text_complete_order, completeOrder));
            RequestOptions options = new RequestOptions().placeholder(R.drawable.my_head);
            Glide.with(mContext).load(Config.getUserAvatorBaseUrl() + data.getAvator()).apply(options).into(iv_head);
            SPController.getInstance().putString(SPController.USER_INFO_AVATAR, data.getAvator());
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

    private NormalView<GetDriverCarInfoEntity> getDriverCarInfoView = new NormalView<GetDriverCarInfoEntity>() {
        @Override
        public void onSuccess(GetDriverCarInfoEntity entity) {
            LogUtils.i("YIN-----", entity.toString());

            GetDriverCarInfoEntity.Data data = entity.getData().get(0);
            carAuthStatus = data.getIsCertification();
            switch (data.getIsCertification()) {
                case "已通过":
                    tv_car_status.setText(data.getCarBrandName()+data.getCarModelName());
                    break;
                case "待审核":
                    tv_car_status.setText("车辆审核中");
                    break;
                case "未通过":
                    tv_car_status.setText("车辆审核未通过");
                    break;
            }
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
            carAuthStatus = "车辆待认证";
            tv_car_status.setText(carAuthStatus);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void goToCarAuthActivity() {
        if (StringUtils.equals(carAuthStatus, "车辆待认证")) {
            Intent uploadIntent = new Intent(mContext, UploadCarInfoActivity.class);
            uploadIntent.putExtra("FROM", "MY_FRAGMENT");
            startActivity(uploadIntent);
            return;
        }

        Intent intent = new Intent(mContext, CarAuthActivity.class);
        intent.putExtra("STATUS", carAuthStatus);
        startActivity(intent);
    }

    private void goToSettingActivity() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        startActivity(intent);
    }

    private void goToWalletActivity() {
        Intent intent = new Intent(mContext, WalletActivity.class);
        startActivity(intent);
    }

    private void goToMineInfoActivity() {
        Intent intent = new Intent(mContext, MineInfoActivity.class);
        startActivity(intent);
    }

    private void goToCumulativeIncomeActivity() {
        Intent intent = new Intent(mContext, CumulativeIncomeActivity.class);
        startActivity(intent);
    }

    private void goToMessageActivity() {
        Intent intent = new Intent(mContext, MessageActivity.class);
        startActivity(intent);
    }

    private void goToEvaluateActivity() {
        Intent intent = new Intent(mContext, EvaluateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wallet:
                goToWalletActivity();
                break;
            case R.id.tv_vip:
                UIUtils.showToastSafe("会员");
                break;
            case R.id.ll_car_auth:
                goToCarAuthActivity();
                break;
            case R.id.tv_car_life:
                UIUtils.showToastSafe("车生活");
                break;
            case R.id.tv_invite:
                UIUtils.showToastSafe("邀请好友");
                break;
            case R.id.tv_help:
                UIUtils.showToastSafe("帮助中心");
                break;
            case R.id.tv_setting:
                goToSettingActivity();
                break;
            case R.id.iv_head:
                goToMineInfoActivity();
                break;
            case R.id.tv_income:
                goToCumulativeIncomeActivity();
                break;
            case R.id.iv_message:
                goToMessageActivity();
                break;
            case R.id.tv_evaluate:
                goToEvaluateActivity();
                break;
        }
    }
}
