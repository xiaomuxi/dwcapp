package com.weddingcar.driver.function.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.et_money)
    EditText et_money;
    @BindView(R.id.btn_zfb)
    Button btn_zfb;
    @BindView(R.id.btn_wx)
    Button btn_wx;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
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

        btn_zfb.setOnClickListener(this);
        btn_wx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zfb:
                break;
            case R.id.btn_wx:
                break;
        }
    }
}
