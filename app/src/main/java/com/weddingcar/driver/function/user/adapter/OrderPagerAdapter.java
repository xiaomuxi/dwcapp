package com.weddingcar.driver.function.user.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private Context mContext;

    public OrderPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
