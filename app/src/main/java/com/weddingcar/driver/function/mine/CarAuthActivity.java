package com.weddingcar.driver.function.mine;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

public class CarAuthActivity extends BaseActivity{

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_car_auth);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("上传车辆信息");
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
