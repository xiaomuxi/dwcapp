package com.weddingcar.driver.function.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.network.library.bean.mine.response.RunningOrderEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;

import java.util.List;

public class GalleryExpandAdapter extends BaseExpandableListAdapter {

    private List<RunningOrderEntity> mList;
    private OnRecycleItemClick mCallback;
    private Context mContext;

    public GalleryExpandAdapter(OnRecycleItemClick listener, List<RunningOrderEntity> mData, Context context) {
        this.mCallback = listener;
        this.mList = mData;
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<OrderRunningListEntity> fileList = mList.get(groupPosition).getOrderList();
        return fileList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (null == convertView) {
            holder = new GroupViewHolder();
            convertView = View.inflate(mContext, R.layout.item_order_running_title, null);
            holder.mTitle = convertView.findViewById(R.id.order_status_tx_view);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        // 设置分组组名
        RunningOrderEntity comInfo = mList.get(groupPosition);
        holder.mTitle.setText(comInfo.getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (null == convertView) {
            holder = new ChildViewHolder();
            convertView = View.inflate(mContext, R.layout.item_order_running_content, null);
            holder.mGridView = (RecyclerView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        // 创建 GridView 适配器
        RunningOrderEntity comInfo = mList.get(groupPosition);
        List<OrderRunningListEntity> fileList = comInfo.getOrderList();
        OrderRunningAdapter gridViewAdapter = new OrderRunningAdapter(fileList, mCallback);
        holder.mGridView.setAdapter(gridViewAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class GroupViewHolder {
        TextView mTitle;
    }

    class ChildViewHolder {
        RecyclerView mGridView;
    }
}
