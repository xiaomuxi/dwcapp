package com.weddingcar.driver.function.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.RobbingInfoEntity;
import com.network.library.utils.GlideUtils;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.FilterListener;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.config.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderMyCarTypeAdapter extends RecyclerView.Adapter<OrderMyCarTypeAdapter.OrderRunningViewHolder> implements Filterable {

    private Context mContext;
    private List<RobbingInfoEntity> mData;
    private FilterListener listener;

    private OnRecycleItemClick callback;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    private OrderFilter filter;

    public OrderMyCarTypeAdapter(List<RobbingInfoEntity> list, OnRecycleItemClick listener, FilterListener filterListener) {
        this.mData = list;
        this.callback = listener;
        this.listener = filterListener;
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
        RobbingInfoEntity mycarTypeEntity = mData.get(position);
        String state = mycarTypeEntity.getState();
        String carBrandName = mycarTypeEntity.getCarBrandName();
        String carModelName = mycarTypeEntity.getCarModelName();
        int journeyChoose = mycarTypeEntity.getJourneyChoose();
        int hourChoose = mycarTypeEntity.getHourChoose();
        String areaName = mycarTypeEntity.getAreaName();
        String theWeddingDate = mycarTypeEntity.getTheWeddingDate();
        String customerAvator = mycarTypeEntity.getCustomerAvator();
        String userHeadUrl = Config.getAppHtmlUrl() + "/LJTP/CATP/" + customerAvator;
        String customerName = mycarTypeEntity.getCustomerName();
        String customerSex = mycarTypeEntity.getCustomerSex();
        String code = mycarTypeEntity.getCode();

        holder.orderNumber.setText("订单号:" + code);
        if (customerSex.equals("男")) {
            holder.orderUserName.setText(customerName + "先生");
        } else if (customerSex.equals("女")) {
            holder.orderUserName.setText(customerName + "女士");
        }
        GlideUtils.loadShow(mContext, userHeadUrl, holder.orderUserIconView);
        holder.orderMoneyTxView.setVisibility(View.GONE);
        holder.orderDurationTxView.setText(hourChoose + "小时");
        holder.orderRoadTxView.setText(journeyChoose + "公里");
        holder.orderSpaceTxView.setText(areaName);
        holder.orderCarTypeTxView.setText(carBrandName + carModelName);
        holder.orderCarModel.setText(carBrandName + carModelName);

        String carLogoUrl = Config.getAppHtmlUrl() + "/LJTP/Logo/" + mycarTypeEntity.getCarlogo();
        holder.orderCarModel.setVisibility(View.VISIBLE);
        GlideUtils.getNetDrawable(mContext, carLogoUrl, new GlideUtils.onGetNetImgCallback() {
            @Override
            public void onGetDrawable(Drawable drawable) {
                if (null != drawable) {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    holder.orderCarModel.setCompoundDrawables(drawable, null, null, null);
                }
            }
        });

        if (state.equals("已结束")) {
            holder.orderCatLocation.setText("该订单已经召齐");
        } else {
            holder.orderCatLocation.setText("已报名:" + mycarTypeEntity.getCarCount() + "辆");
        }

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

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new OrderFilter(mData);
        }
        return filter;
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
        TextView orderCarModel;

        public OrderRunningViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class OrderFilter extends Filter {
        private List<RobbingInfoEntity> mInfos;

        public OrderFilter(List<RobbingInfoEntity> data) {
            this.mInfos = data;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            /**
             * 没有搜索内容的话就还是给results赋值原始数据的值和大小
             * 执行了搜索的话，根据搜索的规则过滤即可，最后把过滤后的数据的值和大小赋值给results
             *
             */
            if (TextUtils.isEmpty(constraint)) {
                results.values = mInfos;
                results.count = mInfos.size();
            } else {
                // 创建集合保存过滤后的数据
                List<RobbingInfoEntity> mList = new ArrayList<RobbingInfoEntity>();
                // 遍历原始数据集合，根据搜索的规则过滤数据
                for (RobbingInfoEntity info : mInfos) {
                    // 这里就是过滤规则的具体实现【规则有很多，大家可以自己决定怎么实现】
                    if (info.getID().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                        // 规则匹配的话就往集合中添加该数据
                        mList.add(info);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            mData = (List<RobbingInfoEntity>) results.values;
            if (listener != null) {
                listener.getFilterData(mData);
            }
            notifyDataSetChanged();
        }
    }
}
