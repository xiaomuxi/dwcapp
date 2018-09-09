package com.weddingcar.driver.function.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import java.util.List;

public class OrderRunningAdapter extends RecyclerView.Adapter<OrderRunningAdapter.OrderRunningViewHolder> {

    private List mData;
    private OnRecycleItemClick callback;

    public OrderRunningAdapter(List list, OnRecycleItemClick listener) {
        this.mData = list;
        this.callback = listener;
    }

    @NonNull
    @Override
    public OrderRunningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_running, parent, false);
        return new OrderRunningViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRunningViewHolder holder, int position) {
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

    public static class OrderRunningViewHolder extends RecyclerView.ViewHolder {

        public OrderRunningViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
