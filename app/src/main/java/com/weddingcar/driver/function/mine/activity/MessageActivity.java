package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.function.mine.adapter.ChartGroupAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.tv_empty)
    TextView tv_empty;
    @BindView(R.id.lv_message)
    ListView lv_message;

    List<EMGroup> mDataList = new ArrayList<>();
    Thread thread;
    ChartGroupAdapter chartGroupAdapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {      //判断标志位
                case 1:
                    chartGroupAdapter.setData(mDataList);
                    checkData();
                    break;
            }
        }
    };

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
        chartGroupAdapter = new ChartGroupAdapter(this);
        lv_message.setAdapter(chartGroupAdapter);
        lv_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MessageActivity.this, ChartActivity.class);
                intent.putExtra("GROUP_ID", mDataList.get(position).getGroupId());
                intent.putExtra("GROUP_NAME", mDataList.get(position).getGroupName());
                startActivity(intent);
            }
        });
        initData();
    }

    private void initData() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mDataList = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();//需异步处理
                    LogUtils.i(TAG, "--------");
                    LogUtils.i(TAG, mDataList.toString());
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void checkData() {
        tv_empty.setVisibility(mDataList.size() == 0 ? View.VISIBLE : View.GONE);
        lv_message.setVisibility(mDataList.size() == 0 ? View.GONE : View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
        thread = null;
    }
}
