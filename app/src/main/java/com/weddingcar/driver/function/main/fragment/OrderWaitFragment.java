package com.weddingcar.driver.function.main.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.controller.NetworkController;
import com.network.library.eventbus.CancelSignEvent;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.activity.LookSignUpCarActivity;
import com.weddingcar.driver.function.main.adapter.OrderWaitAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderWaitFragment extends BaseFragment implements OnRecycleItemClick, GetOrderView {

    private static final String state = "待通过";

    @BindView(R.id.order_running_recycle)
    RecyclerView mWaitRecycleView;
    @BindView(R.id.empty_order_list_view)
    TextView mEmptyView;

    private String mFragmentTag;

    private Unbinder unbinder;

    private NetworkController<BaseNetView> mController;

    private OrderWaitAdapter mOrderWaitAdapter;
    private List<OrderWaitListEntity> mOrderWaitList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_order_wait, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new NetworkController<>();
        mController.attachView(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == mOrderWaitAdapter)
            mOrderWaitAdapter = new OrderWaitAdapter(mOrderWaitList, this);
        mWaitRecycleView.setAdapter(mOrderWaitAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CancelSignEvent event) {
        mRequestComplete = false;
        visible();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mController.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    public void visible() {
        if (mRequestComplete) return;
        String userId = SPController.getInstance().getUserInfo().getUserId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        mController.getWaitOrderList("HC010312", userId, state, true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRecycleItemClick(int position) {
        Intent intent = new Intent(getActivity(), LookSignUpCarActivity.class);
        OrderWaitListEntity orderWaitEntity = mOrderWaitList.get(position);
        intent.putExtra("orderNumber", orderWaitEntity);
        startActivity(intent);
    }

    @Override
    public void onRequestError(String errorMsg, String methodName) {
        super.onRequestError(errorMsg, methodName);
        mWaitRecycleView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetOrderListSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            mRequestComplete = true;
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                mWaitRecycleView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                UIUtils.showToastSafe(msg);
                return;
            }
            mEmptyView.setVisibility(View.GONE);
            mWaitRecycleView.setVisibility(View.VISIBLE);

            mOrderWaitList.clear();

            List<OrderWaitListEntity> listEntities = (List<OrderWaitListEntity>) baseEntity.getData();
            mOrderWaitList.addAll(listEntities);
            if (mOrderWaitList.size() > 1) {
                DividerItemDecoration divider = new DividerItemDecoration(UIUtils.getContext(), DividerItemDecoration.VERTICAL);
                divider.setDrawable(UIUtils.getDrawable(R.drawable.recycleview_divider));
                mWaitRecycleView.addItemDecoration(divider);
            }
            mOrderWaitAdapter.notifyDataSetChanged();
            Logger.I("onGetWaitOrderListSuccess : " + baseEntity.toString());
        }
    }
}
