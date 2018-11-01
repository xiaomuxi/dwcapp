package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMGroup;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;

public class ChartGroupAdapter extends MyBaseAdapter<EMGroup> {

    public ChartGroupAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_chart_group;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        EMGroup item = getItem(position);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_description = convertView.findViewById(R.id.tv_description);
        tv_name.setText(item.getGroupName());
        tv_description.setText(item.getDescription());

        return convertView;
    }
}
