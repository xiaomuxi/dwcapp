package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.network.library.bean.mine.response.CarColorsEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.utils.StringUtils;

public class CarColorListAdapter extends MyBaseAdapter<CarColorsEntity.Data> {

    private int currentIndex = -1;

    public CarColorListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_car_color_list;
    }

    public void setCheckedIndex(int position) {
        currentIndex = position;
        notifyDataSetChanged();
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {

        CarColorsEntity.Data item = getItem(position);

        TextView tv_color = (TextView) convertView.findViewById(R.id.tv_color);
        CheckBox cb_color = (CheckBox) convertView.findViewById(R.id.cb_color);

        tv_color.setText(StringUtils.isEmpty(item.getValue()) ? "--" : item.getValue());
        cb_color.setChecked(currentIndex == position);

        return convertView;
    }
}
