package com.weddingcar.driver.function.mine.activity;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

public class MessageActivity extends BaseActivity {

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_message);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("消息中心");
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
