package com.weddingcar.driver.function.mine.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.network.library.bean.mine.request.GetEvaluateListRequest;
import com.network.library.bean.mine.response.EvaluateEntity;
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
import com.weddingcar.driver.function.mine.adapter.EvaluateListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluateActivity extends BaseActivity implements LoadMoreListView.OnRefreshListener{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lv_evaluate)
    LoadMoreListView lv_evaluate;
    @BindView(R.id.tv_empty)
    TextView tv_empty;
    EvaluateListAdapter evaluateListAdapter;
    List<EvaluateEntity.Data> mDataList;

    NetworkController networkController;
    UserInfo userInfo;

    int pageNum = 1;
    int pageSize = 15;
    private boolean hasMore = true;

    @Override
    protected void init() {
        super.init();
        userInfo = SPController.getInstance().getUserInfo();
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("客户评价");
    }

    @Override
    protected void initView() {
        super.initView();

        networkController = new NetworkController();
        networkController.attachView(getEvaluateListView);

        evaluateListAdapter = new EvaluateListAdapter(this);
        lv_evaluate.setAdapter(evaluateListAdapter);

        initRefreshLayout();
        initData();
    }

    private void initRefreshLayout() {
        lv_evaluate.setOnRefreshListener(this);
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
        GetEvaluateListRequest request = new GetEvaluateListRequest();
        GetEvaluateListRequest.Query query = new GetEvaluateListRequest.Query();
        query.setApiId("HC010113");
        query.setCustomerId(userInfo.getUserId());
        query.setPageIndex(pageNum);
        query.setPageSize(pageSize);
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_GET_EVALUATE_LIST, request);
    }

    private void checkData() {
        tv_empty.setVisibility(mDataList.size() == 0 ? View.VISIBLE : View.GONE);
        lv_evaluate.setVisibility(mDataList.size() == 0 ? View.GONE : View.VISIBLE);
    }

    private NormalView<EvaluateEntity> getEvaluateListView = new NormalView<EvaluateEntity>() {
        @Override
        public void onSuccess(EvaluateEntity entity) {
            LogUtils.i(TAG, entity.toString());
            hasMore = entity.getData().size() == pageSize;
            if (1 == pageNum) {
                mDataList = entity.getData();
            } else {
                lv_evaluate.loadMoreComplete();
                mDataList.addAll(entity.getData());
            }
            if (swipeRefreshLayout.isRefreshing()) {
                //完成后调用完成的方法
                swipeRefreshLayout.setRefreshing(false);
            }
            evaluateListAdapter.setData(mDataList);
            checkData();
        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

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
            lv_evaluate.loadMoreComplete();
        }
    }
}
