package com.weddingcar.driver.function.main.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.network.library.bean.BaseEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment {

    @BindView(R.id.order_tab_layout)
    TabLayout mOrderTabLayout;
    @BindView(R.id.order_view_pager)
    ViewPager mOrderViewPager;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.ll_order)
    LinearLayout ll_order;
    LinearLayout mStatusBarView;
    Unbinder unbinder;

    private String mFragmentTag;

    private List<Fragment> mOrderFragments = new ArrayList<>();
    private List<String> mPagerTitles = new ArrayList<>();

    private OrderRunningFragment mRunningFragment;
    private OrderWaitFragment mWaitFragment;
    private OrderCompleteFragment mCompleteFragment;
    private OrderInvalidFragment mInvalidFragment;

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
        initActionBar();
        initTabLayout();
        initViewPager();
    }

    private void initActionBar() {
        initStatusBar();
        ll_order.addView(mStatusBarView, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initStatusBar() {
        LogUtils.i(TAG, "init status bar");
        int mStatusHeight = UIUtils.getStatusBarHeight(mContext);
        mStatusBarView = new LinearLayout(mContext);
        mStatusBarView.setBackground(ContextCompat.getDrawable(mContext, R.color.bg_main_red));
        mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mStatusHeight));
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

        mRunningFragment = (OrderRunningFragment) OrderRunningFragment.newInstance(RUNNING);
        mWaitFragment = (OrderWaitFragment) OrderWaitFragment.newInstance(WAIT);
        mCompleteFragment = (OrderCompleteFragment) OrderCompleteFragment.newInstance(COMPLETE);
        mInvalidFragment = (OrderInvalidFragment) OrderInvalidFragment.newInstance(INVALID);
        mOrderFragments.add(mRunningFragment);
        mOrderFragments.add(mWaitFragment);
        mOrderFragments.add(mCompleteFragment);
        mOrderFragments.add(mInvalidFragment);

        mPagerTitles.add(UIUtils.getString(R.string.tab_running));
        mPagerTitles.add(UIUtils.getString(R.string.tab_wait));
        mPagerTitles.add(UIUtils.getString(R.string.tab_complete));
        mPagerTitles.add(UIUtils.getString(R.string.tab_invalid));

        OrderPagerAdapter orderPagerAdapter = new OrderPagerAdapter(getFragmentManager(), getActivity(), mOrderFragments, mPagerTitles);
        mOrderTabLayout.setTabsFromPagerAdapter(orderPagerAdapter);
        mOrderViewPager.setAdapter(orderPagerAdapter);

        mOrderViewPager.setCurrentItem(0);
        mOrderViewPager.setOffscreenPageLimit(4);
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
                if (null != mRunningFragment)
                    mRunningFragment.visible();
                break;
            case 1:
                if (null != mWaitFragment)
                    mWaitFragment.visible();
                break;
            case 2:
                if (null != mCompleteFragment)
                    mCompleteFragment.visible();
                break;
            case 3:
                if (null != mInvalidFragment)
                    mInvalidFragment.visible();
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
}
