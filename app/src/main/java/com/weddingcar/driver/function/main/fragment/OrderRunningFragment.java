package com.weddingcar.driver.function.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderRunningAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderRunningFragment extends BaseFragment implements OnRecycleItemClick {

    @BindView(R.id.order_running_recycle)
    RecyclerView mRunningRecycleView;

    private String mFragmentTag;

    private Unbinder unbinder;
    private OrderRunningAdapter mOrderRunningAdapter;

    private List<String> mOrderRunningList = new ArrayList<>();

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
        for (int i = 0; i < 10; i++) {
            mOrderRunningList.add("" + i);
        }
        mOrderRunningAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRecycleItemClick(int position) {

    }
}
