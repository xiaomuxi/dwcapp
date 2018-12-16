package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.map.LocationBean;

public class PoiListAdapter extends MyBaseAdapter<LocationBean>{

    private int selectIndex = -1;

    public PoiListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_poi_list;
    }

    public void setSelectedIndex(int index) {
        selectIndex = index;
        notifyDataSetChanged();
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        LocationBean item = getItem(position);

        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_description = (TextView) convertView.findViewById(R.id.tv_description);
        ImageView iv_check = (ImageView) convertView.findViewById(R.id.iv_check);
        tv_name.setText(item.getTitle());
        tv_description.setText(item.getContent());
        iv_check.setVisibility(selectIndex == position ? View.VISIBLE : View.GONE);

        return convertView;
    }
}
