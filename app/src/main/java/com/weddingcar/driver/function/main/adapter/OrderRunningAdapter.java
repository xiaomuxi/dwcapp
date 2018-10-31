package com.weddingcar.driver.function.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.utils.GlideUtils;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.config.Config;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderRunningAdapter extends RecyclerView.Adapter<OrderRunningAdapter.OrderRunningViewHolder> {

    private List<OrderRunningListEntity> mData;
    private OnRecycleItemClick callback;
    private Context mContext;

    public OrderRunningAdapter(List<OrderRunningListEntity> list, OnRecycleItemClick listener) {
        this.mData = list;
        this.callback = listener;
    }

    @NonNull
    @Override
    public OrderRunningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_running, parent, false);
        return new OrderRunningViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRunningViewHolder holder, int position) {
        OrderRunningListEntity orderListEntity = mData.get(position);
        String name = orderListEntity.getName();
        String sex = orderListEntity.getSex();
        int income = orderListEntity.getIncome();
        String carBrandName = orderListEntity.getCarBrandName();
        String carModelID = orderListEntity.getCarModelID();
        String userHeadUrl = Config.getAppHtmlUrl() + "/LJTP/CATP/" + orderListEntity.getAvator();
        if (sex.equals("男")) {
            holder.orderUserName.setText(name + "先生");
        } else if (sex.equals("女")) {
            holder.orderUserName.setText(name + "女士");
        }
        GlideUtils.loadShow(mContext, userHeadUrl, holder.orderUserIconView);
        holder.orderMoneyTxView.setText("$" + income);
        holder.orderCarTypeTxView.setText(carBrandName + carModelID);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != callback) callback.onRecycleItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    public static class OrderRunningViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_status_tx_view)
        TextView orderStatusTxView;
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.order_user_name)
        TextView orderUserName;
        @BindView(R.id.order_user_icon_view)
        CircleImageView orderUserIconView;
        @BindView(R.id.order_time_tx_view)
        TextView orderTimeTxView;
        @BindView(R.id.order_money_tx_view)
        TextView orderMoneyTxView;
        @BindView(R.id.order_layout_rl_view)
        RelativeLayout orderLayoutRlView;
        @BindView(R.id.order_car_type_tx_view)
        TextView orderCarTypeTxView;
        @BindView(R.id.order_duration_tx_view)
        TextView orderDurationTxView;
        @BindView(R.id.order_road_tx_view)
        TextView orderRoadTxView;
        @BindView(R.id.order_space_tx_view)
        TextView orderSpaceTxView;
        @BindView(R.id.order_cat_location)
        TextView orderCatLocation;

        public OrderRunningViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
