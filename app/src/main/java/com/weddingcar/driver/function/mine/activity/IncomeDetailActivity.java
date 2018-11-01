package com.weddingcar.driver.function.mine.activity;

import android.widget.TextView;

import com.network.library.bean.mine.response.BalanceDetailEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncomeDetailActivity extends BaseActivity {

    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_order_num)
    TextView tv_order_num;
    @BindView(R.id.tv_surplus)
    TextView tv_surplus;
    @BindView(R.id.tv_note)
    TextView tv_note;

    BalanceDetailEntity.Data data;
    @Override
    protected void init() {
        super.init();
        data = (BalanceDetailEntity.Data) getIntent().getSerializableExtra("DATA");
        setContentView(R.layout.activity_income_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("交易详情");
    }


    @Override
    protected void initView() {
        super.initView();
        String amount = data.getAmount() + "";
        String integration = data.getIntegration() + "";
        tv_amount.setText(amount);
        tv_type.setText(data.getType());
        tv_note.setText(data.getName());
        tv_order_num.setText(data.getOrderID());
        tv_surplus.setText(integration);
        tv_time.setText(data.getCreateTimeString());
    }
}
