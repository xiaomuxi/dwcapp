package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.network.library.bean.mine.response.EvaluateEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.ui.CircleImageView;
import com.weddingcar.driver.common.utils.StringUtils;

public class EvaluateListAdapter extends MyBaseAdapter<EvaluateEntity.Data> {

    public EvaluateListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_evaluate_list;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {

        EvaluateEntity.Data item = getItem(position);

        CircleImageView iv_header = (CircleImageView) convertView.findViewById(R.id.iv_header);
        TextView tv_order = (TextView) convertView.findViewById(R.id.tv_order);
        TextView tv_customer = (TextView) convertView.findViewById(R.id.tv_customer);
        RatingBar rb_start = (RatingBar) convertView.findViewById(R.id.rb_start);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_evaluate = (TextView) convertView.findViewById(R.id.tv_evaluate);

        RequestOptions options = new RequestOptions().placeholder(R.drawable.my_head);
        Glide.with(mContext).load(Config.getUserAvatorBaseUrl() + item.getCustomerAvator()).apply(options).into(iv_header);
        tv_order.setText(mContext.getResources().getString(R.string.text_order_id, item.getOrderId()));
        tv_customer.setText(StringUtils.isEmpty(item.getCustomerName()) ? "--" : item.getCustomerName());
        rb_start.setRating(Float.parseFloat(item.getStar()));
        String time = (String) DateFormat.format("yyyy年MM月dd日 kk:mm", Long.parseLong(item.getDateString()));
        tv_time.setText(time);
        tv_evaluate.setText(StringUtils.isEmpty(item.getContent()) ? "--" : item.getContent());

        return convertView;
    }
}
