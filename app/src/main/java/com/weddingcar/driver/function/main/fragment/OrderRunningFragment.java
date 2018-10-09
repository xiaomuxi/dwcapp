package com.weddingcar.driver.function.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderRunningAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderRunningFragment extends BaseFragment implements OnRecycleItemClick, GetOrderView {

    @BindView(R.id.order_running_recycle)
    RecyclerView mRunningRecycleView;
    @BindView(R.id.empty_order_list_view)
    TextView mEmptyView;

    private String mFragmentTag;

    private Unbinder unbinder;
    private OrderRunningAdapter mOrderRunningAdapter;

    private NetworkController<BaseNetView> mController;

    private List<OrderRunningListEntity> mOrderRunningList = new ArrayList<>();

    private boolean mRequestComplete = false;

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
        View view = inflater.inflate(R.layout.fragment_order_running, container, false);
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
        if (isVisible()) {
            if (null == mOrderRunningAdapter)
                mOrderRunningAdapter = new OrderRunningAdapter(mOrderRunningList, this);
            mRunningRecycleView.setAdapter(mOrderRunningAdapter);
            DividerItemDecoration divider = new DividerItemDecoration(UIUtils.getContext(), DividerItemDecoration.VERTICAL);
            divider.setDrawable(UIUtils.getDrawable(R.drawable.recycleview_divider));
            mRunningRecycleView.addItemDecoration(divider);
        }
        requestDataFromNet();
    }

    private void requestDataFromNet() {
        String userId = SPController.getInstance().getUserInfo().getUserId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        mController.getRunningOrderList("HC010108", userId, true);
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

    @Override
    public void onRecycleItemClick(int position) {

    }

    @Override
    public void onGetOrderListSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            mRequestComplete = true;
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                mRunningRecycleView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                UIUtils.showToastSafe(msg);
                return;
            }
            mEmptyView.setVisibility(View.GONE);
            mRunningRecycleView.setVisibility(View.VISIBLE);

            mOrderRunningList.clear();

            List<OrderRunningListEntity> listEntities = (List<OrderRunningListEntity>) baseEntity.getData();
            mOrderRunningList.addAll(listEntities);
            mOrderRunningAdapter.notifyDataSetChanged();
            Logger.I("onGetRunningOrderListSuccess : " + baseEntity.toString());
        }
    }

    public void visible() {
        if (mRequestComplete) return;
    }
}
