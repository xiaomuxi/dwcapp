package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.network.library.bean.mine.request.GetBalanceInfoRequest;
import com.network.library.bean.mine.response.GetBalanceInfoEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.CircleImageView;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.user.activity.PersonalInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineInfoActivity extends BaseActivity implements View.OnClickListener{

    private static int REQUEST_CODE = 1001;

    @BindView(R.id.ll_avatar)
    LinearLayout ll_avatar;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.ll_sex)
    LinearLayout ll_sex;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_account)
    TextView tv_account;

    UserInfo userInfo;
    NetworkController networkController;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);

        userInfo = SPController.getInstance().getUserInfo();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("个人信息");
    }

    @Override
    protected void initView() {
        super.initView();
        networkController = new NetworkController();
        networkController.attachView(getBalanceInfoView);

        ll_avatar.setOnClickListener(this);
        ll_name.setOnClickListener(this);
        ll_sex.setOnClickListener(this);

        tv_account.setText(userInfo.getUserId());

        initData();
    }

    private void initData() {
        GetBalanceInfoRequest request = new GetBalanceInfoRequest();
        GetBalanceInfoRequest.Query query = new GetBalanceInfoRequest.Query();
        query.setApiId("HC010101");
        query.setDEVICEID(userInfo.getDeviceId());
        query.setUserid(userInfo.getUserId());
        query.setId(userInfo.getUserId());
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_GET_BALANCE_INFO, request);
    }

    private NormalView<GetBalanceInfoEntity> getBalanceInfoView = new NormalView<GetBalanceInfoEntity>() {
        @Override
        public void onSuccess(GetBalanceInfoEntity entity) {
            GetBalanceInfoEntity.Data data = entity.getData().get(0);
            String name = StringUtils.isEmpty(data.getName() + "") ? "--" : data.getName() + "";
            String sex = StringUtils.isEmpty(data.getSex() + "") ? "--" : data.getSex() + "";
            tv_name.setText(name);
            tv_sex.setText(sex);

            RequestOptions options = new RequestOptions().placeholder(R.drawable.my_head);
            Glide.with(mContext).load(Config.getUserAvatorBaseUrl() + data.getAvator()).apply(options).into(iv_avatar);

            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setUserId(userInfo.getUserId());
            newUserInfo.setSex(data.getSex());
            newUserInfo.setName(data.getName());
            SPController.getInstance().saveUserInfo(newUserInfo);
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

    private void goToPersonalInfoActivity() {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        intent.putExtra("TYPE", "MINE_ACTIVITY");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_avatar:
            case R.id.ll_name:
            case R.id.ll_sex:
                goToPersonalInfoActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == 0) {
            initData();
        }
    }
}
