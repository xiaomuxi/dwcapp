package com.weddingcar.driver.function.mine.activity;

import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

public class ChartActivity extends BaseActivity{

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_chart);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        String groupName = getIntent().getStringExtra("GROUP_NAME");
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft(groupName);
    }


    @Override
    protected void initView() {
        super.initView();
        String groupID = getIntent().getStringExtra("GROUP_ID");
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        args.putString(EaseConstant.EXTRA_USER_ID, groupID);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chatFragment).commit();
    }

}
