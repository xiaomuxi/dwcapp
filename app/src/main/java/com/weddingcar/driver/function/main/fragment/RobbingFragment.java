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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.RobbingInfoEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetRobbingView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RobbingFragment extends BaseFragment implements GetRobbingView {

    @BindView(R.id.search_order_id)
    EditText mSearchEditView;
    @BindView(R.id.order_tab_layout)
    TabLayout mRobbingTabLayout;
    @BindView(R.id.order_view_pager)
    ViewPager mRobbingViewPager;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_action_bar)
    LinearLayout llActionBar;
    @BindView(R.id.ll_order)
    LinearLayout ll_order;
    private LinearLayout mStatusBarView;

    private String mFragmentTag;
    private Unbinder unbinder;

    private List<Fragment> mRobbingFragments = new ArrayList<>();
    private List<String> mPagerTitles = new ArrayList<>();

    private NetworkController<BaseNetView> mController;

    private MyCarTypeFragment mMyCarTypeFragment;
    private AllCarTypeFragment mAllCarTypeFragment;
    private String mSearchString;

    public RobbingFragment() {
    }

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
        View view = inflater.inflate(R.layout.fragment_robbing_view, container, false);
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
        initActionBar();
        initEditView();
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

    private void initEditView() {
        mSearchEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSearchString = mSearchEditView.getText().toString();
            }
        });

        mSearchEditView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Logger.I("mSearchEditView onKey keyCode = " + keyCode);
                if ((keyEvent.getAction() == KeyEvent.ACTION_UP) && keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    if (null == mSearchString || mSearchString.trim().isEmpty()) {
                        Toast.makeText(mContext, "请输入正确的单号", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        //TODO search orderNumber
                        search();
                    }
                }
                return false;
            }
        });
    }

    private void search() {
        int item = mRobbingViewPager.getCurrentItem();
        if (item == 0) {
            mMyCarTypeFragment.filter(mSearchString);
        } else {
            mAllCarTypeFragment.filter(mSearchString);
        }
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

    public void visible() {
        mRobbingViewPager.setCurrentItem(0);
    }

    private void initTabLayout() {
        mRobbingTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选择的时候回调此方法
                mRobbingViewPager.setCurrentItem(tab.getPosition(), true);
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
        mRobbingViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mRobbingTabLayout));

        mMyCarTypeFragment = (MyCarTypeFragment) MyCarTypeFragment.newInstance(MY_TYPE);
        mAllCarTypeFragment = (AllCarTypeFragment) AllCarTypeFragment.newInstance(ALL_TYPE);
        mRobbingFragments.add(mMyCarTypeFragment);
        mRobbingFragments.add(mAllCarTypeFragment);

        mPagerTitles.add(UIUtils.getString(R.string.robbing_my_type));
        mPagerTitles.add(UIUtils.getString(R.string.robbing_all_type));

        OrderPagerAdapter orderPagerAdapter = new OrderPagerAdapter(getChildFragmentManager(), getActivity(), mRobbingFragments, mPagerTitles);
        mRobbingTabLayout.setTabsFromPagerAdapter(orderPagerAdapter);
        mRobbingViewPager.setAdapter(orderPagerAdapter);

        mRobbingViewPager.setCurrentItem(0);
        mRobbingViewPager.setOffscreenPageLimit(2);
        mRobbingViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                if (null != mMyCarTypeFragment)
                    mMyCarTypeFragment.visible();
                break;
            case 1:
                if (null != mAllCarTypeFragment)
                    mAllCarTypeFragment.visible();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestError(String errorMsg, String methodName) {
        super.onRequestError(errorMsg, methodName);
        int item = mRobbingViewPager.getCurrentItem();
        if (item == 0) {
            mMyCarTypeFragment.dateClear();
        } else {
            mAllCarTypeFragment.dateClear();
        }
    }

    @Override
    public void onGetRobbingSuccess(BaseEntity baseEntity) {

    }
}
