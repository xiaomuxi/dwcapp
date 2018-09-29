package com.weddingcar.driver.common.base.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.weddingcar.driver.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Riky on 2015/12/24.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected View mRootView;
    protected List<T> mList = new ArrayList<>();
    protected OnCheckedChangedListener mOnCheckedChangedListener;


    public MyBaseAdapter(Context c, View rootView) {
        this.mContext = c;
        this.mRootView = rootView;
    }
    public MyBaseAdapter(Context c) {
        this.mContext = c;
    }


    public void setData(List<T> list) {
        if (null == list) return;
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        try {
            return mList == null ? null : mList.get(position);
        } catch (Exception e) {
            LogUtils.e(TAG, "getItem", e);
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return mList == null ? 0 : position;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }

    public void remove(List<T> list) {
        if (list != null && mList.removeAll(list)) {
            notifyDataSetChanged();
        }
    }

    public void remove(T item) {
        if (mList != null && mList.remove(item)) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        remove(mList);
    }

    public void add(T item) {
        if (mList != null && mList.add(item)) {
            notifyDataSetChanged();
        }
    }
    public void add(int position, T item) {
        if (mList != null) {
            mList.add(position, item);
            notifyDataSetChanged();
        }
    }

    public void add(List<T> list) {
        if (list != null && mList.addAll(list)) {
            notifyDataSetChanged();
        }
    }

    public void add(int position, List<T> list) {
        if (list != null && mList.addAll(position, list)) {
            notifyDataSetChanged();
        }
    }

    public List<T> getData() {
        return mList;
    }

    /**
     * 布局资源
     * @return
     */
    protected abstract int setLayoutRes();




    /**
     * 每个控件的缓存
     */
    protected static class ViewHolder {
        public SparseArray<View> view = new SparseArray<>();

        /**
         * 指定resId和类型即可获取到相应的view
         *
         * @param convertView
         * @param resId
         * @param <T>
         * @return
         */
        public <T extends View> T findView(View convertView, int resId) {
            View v = view.get(resId);
            if (v == null) {
                v = convertView.findViewById(resId);
                view.put(resId, v);
            }
            return (T) v;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(setLayoutRes(), null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getView(position, convertView, parent, holder);
    }

    /**
     * 重写getView方法
     * @param position
     * @param convertView
     * @param parent
     * @param holder
     * @return
     */
    protected abstract View getView(int position, View convertView, ViewGroup parent, ViewHolder holder);

    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.mOnCheckedChangedListener = onCheckedChangedListener;
    }

    public interface OnCheckedChangedListener {
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position);
    }
}

