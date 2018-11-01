package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.utils.GlideUtils;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.function.main.activity.OrderInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteOrderListAdapter extends MyBaseAdapter<OrderWaitListEntity> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    public CompleteOrderListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_order_running;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        TextView orderStatusTxView = (TextView) convertView.findViewById(R.id.order_status_tx_view);
        TextView orderNumber = (TextView) convertView.findViewById(R.id.order_number);
        TextView orderUserName = (TextView) convertView.findViewById(R.id.order_user_name);
        CircleImageView orderUserIconView = (CircleImageView) convertView.findViewById(R.id.order_user_icon_view);
        TextView orderTimeTxView = (TextView) convertView.findViewById(R.id.order_time_tx_view);
        TextView orderMoneyTxView = (TextView) convertView.findViewById(R.id.order_money_tx_view);
        RelativeLayout orderLayoutRlView = (RelativeLayout) convertView.findViewById(R.id.order_layout_rl_view);
        TextView orderCarTypeTxView = (TextView) convertView.findViewById(R.id.order_car_type_tx_view);
        TextView orderDurationTxView = (TextView) convertView.findViewById(R.id.order_duration_tx_view);
        TextView orderRoadTxView = (TextView) convertView.findViewById(R.id.order_road_tx_view);
        TextView orderSpaceTxView = (TextView) convertView.findViewById(R.id.order_space_tx_view);
        TextView orderCatLocation = (TextView) convertView.findViewById(R.id.order_cat_location);

        OrderWaitListEntity orderWaitListEntity = getItem(position);
        String id = orderWaitListEntity.getID();            //订单号
        String carBrandName = orderWaitListEntity.getCarBrandName();
        String carModelName = orderWaitListEntity.getCarModelName();
        int journeyChoose = orderWaitListEntity.getJourneyChoose();
        int hourChoose = orderWaitListEntity.getHourChoose();
        String areaName = orderWaitListEntity.getAreaName();
        String idH = orderWaitListEntity.getIdH();
        int amount = orderWaitListEntity.getAmount();
        String iscar = orderWaitListEntity.getIscar();
        String orderOfferID = orderWaitListEntity.getOrderOfferID();
        String theWeddingDate = orderWaitListEntity.getTheWeddingDate();
        String theWeddingDateString = orderWaitListEntity.getTheWeddingDateString();
        String customerAvator = orderWaitListEntity.getCustomerAvator();
        String userHeadUrl = Config.getAppHtmlUrl() + "/LJTP/CATP/" + customerAvator;
        String customerName = orderWaitListEntity.getCustomerName();
        String customerSex = orderWaitListEntity.getCustomerSex();
        String code = "订单号:" + orderWaitListEntity.getID();
        orderStatusTxView.setVisibility(View.GONE);
        orderCatLocation.setText("查看评价");
        orderCatLocation.setTextColor(mContext.getResources().getColor(R.color.text_black));
        orderCatLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OrderInfoActivity.class);
                intent.putExtra("type", "complete");
                intent.putExtra("complete", orderWaitListEntity);
                mContext.startActivity(intent);
            }
        });
        orderNumber.setText(code);
        if (customerSex.equals("男")) {
            orderUserName.setText(customerName + "先生");
        } else if (customerSex.equals("女")) {
            orderUserName.setText(customerName + "女士");
        }
        GlideUtils.loadShow(mContext, userHeadUrl, orderUserIconView);
        orderMoneyTxView.setText("$" + amount);
        orderDurationTxView.setText(hourChoose + "小时");
        orderRoadTxView.setText(journeyChoose + "公里");
        orderSpaceTxView.setText(areaName);
        orderCarTypeTxView.setText(carBrandName + carModelName);
        orderTimeTxView.setText(sdf.format(new Date(Long.parseLong(theWeddingDate))));
        return convertView;
    }
}
