package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.network.library.bean.mine.response.BalanceDetailEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.utils.StringUtils;

public class BalanceListAdapter extends MyBaseAdapter <BalanceDetailEntity.Data>{

    public BalanceListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_balance_list;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_title_order = (TextView) convertView.findViewById(R.id.tv_title_order);
        TextView tv_money = (TextView) convertView.findViewById(R.id.tv_money);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);

        BalanceDetailEntity.Data item = getItem(position);

        tv_title.setText(item.getName() + item.getType());
        tv_title_order.setText(mContext.getResources().getString(R.string.text_balance_detail_order, item.getOrderID()));
        tv_title_order.setVisibility(StringUtils.isEmpty(item.getOrderID())?View.INVISIBLE:View.VISIBLE);
        tv_money.setText(mContext.getResources().getString(R.string.text_balance_detail_money, item.getAmount()+""));
        tv_time.setText(item.getCreateTimeString());

        return convertView;
    }
}
