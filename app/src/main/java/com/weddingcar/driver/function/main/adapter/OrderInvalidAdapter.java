package com.weddingcar.driver.function.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.utils.GlideUtils;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.function.main.fragment.OrderInvalidFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderInvalidAdapter extends RecyclerView.Adapter<OrderInvalidAdapter.OrderRunningViewHolder> {

    private List<OrderWaitListEntity> mData;
    private OnRecycleItemClick callback;
    private Context mContext;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    private OnInvalidDeleteListener mCallback;

    public OrderInvalidAdapter(List<OrderWaitListEntity> list, OnRecycleItemClick listener) {
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
        holder.mOrderStatusView.setVisibility(View.GONE);
        OrderWaitListEntity orderWaitListEntity = mData.get(position);
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
        String code = orderWaitListEntity.getID();
        holder.orderInvalidView.setVisibility(View.VISIBLE);
        holder.orderInvalidView.setText("已失效");
        holder.orderInvalidView.setTextColor(mContext.getResources().getColor(R.color.text_black));
        holder.orderCatLocation.setText("删除");
        holder.orderCatLocation.setTextColor(mContext.getResources().getColor(R.color.text_black));
        holder.orderCatLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mCallback) mCallback.onInvalidOrderDelete(position);
            }
        });
        holder.orderNumber.setText("订单号:" + code);
        holder.orderUserName.setText(customerName);
        GlideUtils.loadShow(mContext, userHeadUrl, holder.orderUserIconView);
        holder.orderMoneyTxView.setText("￥" + amount);
        holder.orderDurationTxView.setText(hourChoose + "小时");
        holder.orderRoadTxView.setText(journeyChoose + "公里");
        holder.orderSpaceTxView.setText(areaName);
        holder.orderCarTypeTxView.setText(carBrandName + carModelName);
        holder.orderTimeTxView.setText(sdf.format(new Date(Long.parseLong(theWeddingDate))));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != callback) callback.onRecycleItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnDeleteOrderViewClickListener(OnInvalidDeleteListener listener) {
        this.mCallback = listener;
    }

    public static class OrderRunningViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_status_view)
        LinearLayout mOrderStatusView;
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
        @BindView(R.id.order_left_view)
        TextView orderInvalidView;

        public OrderRunningViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnInvalidDeleteListener {
        void onInvalidOrderDelete(int position);
    }
}
