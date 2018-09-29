package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.network.library.bean.mine.response.GetCarBrandsEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.utils.StringUtils;

public class CarBrandListAdapter extends MyBaseAdapter<GetCarBrandsEntity.Data>{

    private Context mContext;
    private int currentIndex = 0;

    public CarBrandListAdapter(Context c) {
        super(c);
        mContext = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_car_brand_list;
    }

    public void setSelectedItem(int position) {
        currentIndex = position;
        notifyDataSetChanged();
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        GetCarBrandsEntity.Data item = getItem(position);
        LinearLayout ll_brand = (LinearLayout) convertView.findViewById(R.id.ll_brand);
        ImageView iv_brand = (ImageView) convertView.findViewById(R.id.iv_brand);
        TextView tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);

        String bgColor = currentIndex == position ? "#FFFFFF" : "#F0F0F0";
        ll_brand.setBackgroundColor(Color.parseColor(bgColor));
        String imgUri = Config.getCarBrandsBaseUrl() + item.getLogo();
        RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_car);
        Glide.with(mContext).load(imgUri).apply(options).into(iv_brand);
        tv_brand.setText(StringUtils.isEmpty(item.getValue()) ? "--" : item.getValue());

        return convertView;
    }
}
