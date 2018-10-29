package com.weddingcar.driver.function.mine.activity;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.StringUtils;

public class DrawCashActivity extends BaseActivity{

    //"ACCOUNT" | "BOND"
    String type;
    @Override
    protected void init() {
        super.init();
        type = getIntent().getStringExtra("TYPE");
        setContentView(R.layout.activity_draw_cash);
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
    }
}
