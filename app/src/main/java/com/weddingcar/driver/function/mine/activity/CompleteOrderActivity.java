package com.weddingcar.driver.function.mine.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.network.library.bean.mine.request.CompleteOrderListRequest;
import com.network.library.bean.mine.response.CompleteOrderEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.LoadMoreListView;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompleteOrderActivity extends BaseActivity implements LoadMoreListView.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lv_order)
    LoadMoreListView lv_order;
    @BindView(R.id.tv_empty)
    TextView tv_empty;
//    BalanceListAdapter balanceListAdapter;
    List<OrderWaitListEntity> mDataList;

    NetworkController networkController;
    UserInfo userInfo;

    int pageNum = 1;
    int pageSize = 15;
    private boolean hasMore = true;

    @Override
    protected void init() {
        super.init();
        userInfo = SPController.getInstance().getUserInfo();
        setContentView(R.layout.activity_cumulative_income);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("已完成订单");
    }

    @Override
    protected void initView() {
        super.initView();

        networkController = new NetworkController();
        networkController.attachView(getOrderListView);

//        balanceListAdapter = new BalanceListAdapter(this);
//        lv_order.setAdapter(balanceListAdapter);
//        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                goToOrderDetailActivity(mDataList.get(position));
//            }
//        });
//
//        initRefreshLayout();
//        initData();
    }

    private void goToOrderDetailActivity(OrderWaitListEntity data) {
//        Intent intent = new Intent(this, IncomeDetailActivity.class);
//        intent.putExtra("DATA", data);
//        startActivity(intent);
    }

    private void initRefreshLayout() {
        lv_order.setOnRefreshListener(this);
        //设置刷新的颜色
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.background_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark);
        //拖动多长的时候开始刷新
        swipeRefreshLayout.setDistanceToTriggerSync(100);

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.bg_white));

        //刷新监听器
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                hasMore = true;
                initData();
            }

        });
    }

    private void initData() {
        CompleteOrderListRequest request = new CompleteOrderListRequest();
        CompleteOrderListRequest.Query query = new CompleteOrderListRequest.Query();
        query.setApiId("HC0103122");
        query.setDEVICEID(userInfo.getDeviceId());
        query.setUserid(userInfo.getUserId());
        query.setCustomerId(userInfo.getUserId());
        query.setPageIndex(pageNum);
        query.setPageSize(pageSize);
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_COMPLETE_ORDER_LIST, request);
    }

    private void checkData() {
        tv_empty.setVisibility(mDataList.size() == 0 ? View.VISIBLE : View.GONE);
        lv_order.setVisibility(mDataList.size() == 0 ? View.GONE : View.VISIBLE);
    }

    private NormalView<CompleteOrderEntity> getOrderListView = new NormalView<CompleteOrderEntity>() {
        @Override
        public void onSuccess(CompleteOrderEntity entity) {
            LogUtils.i(TAG, entity.toString());
            hasMore = entity.getData().size() == pageSize;
            if (1 == pageNum) {
                mDataList = entity.getData();
            } else {
                lv_order.loadMoreComplete();
                mDataList.addAll(entity.getData());
            }
            if (swipeRefreshLayout.isRefreshing()) {
                //完成后调用完成的方法
                swipeRefreshLayout.setRefreshing(false);
            }
//            balanceListAdapter.setData(mDataList);
            checkData();
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    @Override
    public void onLoadingMore() {
        if (hasMore) {
            pageNum++;
            initData();
        } else {
            lv_order.loadMoreComplete();
        }
    }
}

