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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.activity.CarAuthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFragment extends BaseFragment implements View.OnClickListener{

    private String mFragmentTag;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.tv_wallet)
    TextView tv_wallet;
    @BindView(R.id.tv_vip)
    TextView tv_vip;
    @BindView(R.id.tv_car_auth)
    TextView tv_car_auth;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mFragmentTag = getArguments().getString(TAG);
        }
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

        initActionBar();
        tv_wallet.setOnClickListener(this);
        tv_vip.setOnClickListener(this);
        tv_car_auth.setOnClickListener(this);
        tv_car_life.setOnClickListener(this);
        tv_invite.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void goToCarAuthActivity() {
        Intent intent = new Intent(mContext, CarAuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wallet:
                UIUtils.showToastSafe("钱包");
                break;
            case R.id.tv_vip:
                UIUtils.showToastSafe("会员");
                break;
            case R.id.tv_car_auth:
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
                UIUtils.showToastSafe("设置");
                break;
        }
    }
}
