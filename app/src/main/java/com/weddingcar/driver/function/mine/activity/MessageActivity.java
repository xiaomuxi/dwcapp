package com.weddingcar.driver.function.mine.activity;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.chart_list)
    EaseConversationList chart_list;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
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
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        LogUtils.i(TAG, "------------>");
        LogUtils.i(TAG, conversations.toString());

        List<EMConversation> dataList = new ArrayList<>();
        for(String key : conversations.keySet()){
            dataList.add(conversations.get(key));
        }
        LogUtils.i(TAG, dataList.toString());

        //初始化，参数为会话列表集合
        chart_list.init(dataList);
        //刷新列表
        chart_list.refresh();
    }
}
