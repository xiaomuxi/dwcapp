package com.weddingcar.driver.function.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.DeleteInvalidView;
import com.network.library.view.GetOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.MaterialDialog;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.activity.OrderInfoActivity;
import com.weddingcar.driver.function.main.adapter.OrderInvalidAdapter;
import com.weddingcar.driver.function.main.adapter.OrderWaitAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderInvalidFragment extends BaseFragment implements OnRecycleItemClick,
        GetOrderView, OrderInvalidAdapter.OnInvalidDeleteListener, DeleteInvalidView {

    private static final String state = "已失效";

    @BindView(R.id.order_running_recycle)
    RecyclerView mInvalidRecycleView;
    @BindView(R.id.empty_order_list_view)
    TextView mEmptyView;

    private String mFragmentTag;

    private Unbinder unbinder;

    private NetworkController<BaseNetView> mController;

    private OrderInvalidAdapter mOrderInvalidAdapter;
    private List<OrderWaitListEntity> mOrderInvalidList = new ArrayList<>();

    private boolean mRequestComplete = false;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mFragmentTag = getArguments().getString(TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_invalid, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new NetworkController<>();
        mController.attachView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == mOrderInvalidAdapter)
            mOrderInvalidAdapter = new OrderInvalidAdapter(mOrderInvalidList, this);
        mOrderInvalidAdapter.setOnDeleteOrderViewClickListener(this);
        mInvalidRecycleView.setAdapter(mOrderInvalidAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(UIUtils.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(UIUtils.getDrawable(R.drawable.recycleview_divider));
        mInvalidRecycleView.addItemDecoration(divider);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mController.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void visible(boolean isRefresh, SwipeRefreshLayout refreshLayout) {
        this.mRefreshLayout = refreshLayout;
        mRequestComplete = !isRefresh;
        if (mRequestComplete) return;
        String userId = SPController.getInstance().getUserInfo().getUserId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        mController.getWaitOrderList("HC010312", userId, state, true);
    }

    @Override
    public void onRecycleItemClick(int position) {
        OrderWaitListEntity orderWaitListEntity = mOrderInvalidList.get(position);
        if (null != orderWaitListEntity) {

            Intent intent = new Intent(getContext(), OrderInfoActivity.class);
            intent.putExtra("type", "invalid");
            intent.putExtra("invalid", orderWaitListEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestError(String errorMsg, String methodName) {
        super.onRequestError(errorMsg, methodName);
        mInvalidRecycleView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        if (null != mRefreshLayout)
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetOrderListSuccess(BaseEntity baseEntity) {
        if (null != mRefreshLayout)
            mRefreshLayout.setRefreshing(false);
        if (null != baseEntity) {
            mRequestComplete = true;
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                mInvalidRecycleView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                UIUtils.showToastSafe(msg);
                return;
            }
            mEmptyView.setVisibility(View.GONE);
            mInvalidRecycleView.setVisibility(View.VISIBLE);

            mOrderInvalidList.clear();

            List<OrderWaitListEntity> listEntities = (List<OrderWaitListEntity>) baseEntity.getData();
            mOrderInvalidList.addAll(listEntities);
            mOrderInvalidAdapter.notifyDataSetChanged();
            Logger.I("onGetInvalidOrderListSuccess : " + baseEntity.toString());
        }
    }

    @Override
    public void onInvalidOrderDelete(int position) {
        MaterialDialog materialDialog = new MaterialDialog(getContext());
        materialDialog.setMessage("确定要删除订单吗?");
        materialDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();
            }
        });
        materialDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();

                Logger.I("onInvalidOrderDelete position = " + position);
                OrderWaitListEntity orderWaitListEntity = mOrderInvalidList.get(position);
                String ID = orderWaitListEntity.getID();
                mController.deleteInvalidOrder("HC020312", ID, true);
            }
        });
        materialDialog.show();
    }

    @Override
    public void onDeleteInvalidOrderSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            mRequestComplete = false;
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                mInvalidRecycleView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                UIUtils.showToastSafe(msg);
                return;
            }
            visible(true, mRefreshLayout);
        }
    }
}
