package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.network.library.bean.mine.response.GetCarBrandsEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.utils.StringUtils;

public class BrandChildrenListAdapter extends MyBaseAdapter<GetCarBrandsEntity.Data.ModelsBean>{

    private Context mContext;
    private int currentIndex = 0;

    public BrandChildrenListAdapter(Context c) {
        super(c);
        mContext = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_car_brand_children_list;
    }

    public void setSelectedItem(int position) {
        currentIndex = position;
        notifyDataSetChanged();
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {

        GetCarBrandsEntity.Data.ModelsBean item = getItem(position);
        LinearLayout ll_model = (LinearLayout) convertView.findViewById(R.id.ll_model);
        TextView tv_model = (TextView) convertView.findViewById(R.id.tv_model);

        String bgColor = currentIndex == position ? "#FFFFFF" : "#F5F5F5";
        ll_model.setBackgroundColor(Color.parseColor(bgColor));
        tv_model.setText(StringUtils.isEmpty(item.getValue()) ? "--" : item.getValue());


        return convertView;
    }
}
