package com.weddingcar.driver.function.main.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.network.library.bean.BaseEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment implements GetOrderView {

    @BindView(R.id.order_tab_layout)
    TabLayout mOrderTabLayout;
    @BindView(R.id.order_view_pager)
    ViewPager mOrderViewPager;

    Unbinder unbinder;

    private String mFragmentTag;

    private List<Fragment> mOrderFragments = new ArrayList<>();
    private List<String> mPagerTitles = new ArrayList<>();
    private NetworkController<BaseNetView> mController;

    public OrderFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mFragmentTag = getArguments().getString(TAG);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new NetworkController<>();
        mController.attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mController.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTabLayout();
        initViewPager();
        mController.getOrderList("HC0103122", "18616270226", "待通过", true);
    }

    private void initTabLayout() {
        mOrderTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选择的时候回调此方法
                mOrderViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab未被选择的时候回调此方法
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab重新选择的时候回调此方法
            }
        });
    }

    private void initViewPager() {
        mOrderViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mOrderTabLayout));

        mOrderFragments.add(OrderRunningFragment.newInstance(RUNNING));
        mOrderFragments.add(OrderWaitFragment.newInstance(WAIT));
        mOrderFragments.add(OrderCompleteFragment.newInstance(COMPLETE));
        mOrderFragments.add(OrderInvalidFragment.newInstance(INVALID));

        mPagerTitles.add(UIUtils.getString(R.string.tab_running));
        mPagerTitles.add(UIUtils.getString(R.string.tab_wait));
        mPagerTitles.add(UIUtils.getString(R.string.tab_complete));
        mPagerTitles.add(UIUtils.getString(R.string.tab_invalid));

        OrderPagerAdapter orderPagerAdapter = new OrderPagerAdapter(getFragmentManager(), getActivity(), mOrderFragments, mPagerTitles);
        mOrderTabLayout.setTabsFromPagerAdapter(orderPagerAdapter);
        mOrderViewPager.setAdapter(orderPagerAdapter);

        mOrderViewPager.setCurrentItem(0);
        mOrderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                pageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void pageChanged(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGetOrderListSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();
            Logger.I("onGetOrderListSuccess status : " + status + " msg : " + msg + " count : " + count);
        }
    }
}
