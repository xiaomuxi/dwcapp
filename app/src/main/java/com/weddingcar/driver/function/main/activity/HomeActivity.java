package com.weddingcar.driver.function.main.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.user.fragment.BaseFragment;
import com.weddingcar.driver.function.user.fragment.MyFragment;
import com.weddingcar.driver.function.user.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fragment_container)
    FrameLayout mContentView;
    @BindView(R.id.navigation)
    BottomNavigationViewEx mBtmNavigation;

    private Context context;
    private long exitTime = 0;

    private OrderFragment mOrderFragment;
    private MyFragment mMyFragment;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitle(UIUtils.getString(R.string.tab_title_order));
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mBtmNavigation.setOnNavigationItemSelectedListener(mOnNavListener);

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.text_main_gray),
                getResources().getColor(R.color.bg_main_red)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        mBtmNavigation.setItemTextColor(csl);
        mBtmNavigation.setItemIconTintList(csl);

        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        mOrderFragment = (OrderFragment) OrderFragment.newInstance(BaseFragment.ORDER);
        mMyFragment = (MyFragment) MyFragment.newInstance(BaseFragment.MY);

        transaction.add(R.id.fragment_container, mOrderFragment);
        transaction.add(R.id.fragment_container, mMyFragment);

        transaction.hide(mMyFragment);
        transaction.show(mOrderFragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UIUtils.showToastSafe("再按一次退出程序");
                exitTime = System.currentTimeMillis();
                return false;
            } else {
                this.finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_order:
                    switchFragment(menuItem.getItemId());
                    return true;
                case R.id.navigation_my:
                    switchFragment(menuItem.getItemId());
                    return true;
                case R.id.navigation_empty:
                    onPicOrderClicked();
                    break;
            }
            return false;
        }
    };

    private void switchFragment(int viewId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (viewId == R.id.navigation_order) {
            transaction.hide(mMyFragment);
            transaction.show(mOrderFragment).commitAllowingStateLoss();
        } else if (viewId == R.id.navigation_my) {
            transaction.hide(mOrderFragment);
            transaction.show(mMyFragment).commitAllowingStateLoss();
        }
    }

    @OnClick(R.id.btn_pic_order)
    public void onPicOrderClicked() {
        UIUtils.showToastSafe("抢单");
    }
}
