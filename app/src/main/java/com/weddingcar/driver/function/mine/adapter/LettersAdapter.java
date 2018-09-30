package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.adapter.MyBaseAdapter;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.function.mine.listener.PlateNumberListener;

import java.util.List;

public class LettersAdapter extends MyBaseAdapter<String>{
    private Context mContext;
    private int currentIndex = -1;
    private PlateNumberListener mPlateNumberListener;

    public LettersAdapter(Context c, PlateNumberListener plateNumberListener) {
        super(c);
        mContext = c;
        mPlateNumberListener = plateNumberListener;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_letter_list;
    }

    @Override
    public void setData(List<String> list) {
        if (null == list) return;
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public int getSelectedIndex() {
        return currentIndex;
    }

    public String getSelectItem() {
        if (currentIndex == -1) {
            return null;
        }
        return getItem(currentIndex);
    }

    public void setSelectedIndex(int index) {
        currentIndex = index;
        notifyDataSetChanged();
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        String item = getItem(position);
        TextView tv_letter = convertView.findViewById(R.id.tv_letter);
        if (position == mList.size() - 1) {
            tv_letter.setText("");
            tv_letter.setBackground(mContext.getResources().getDrawable(R.drawable.ic_delete));
            tv_letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlateNumberListener.onButtonDeleteClicked();
                }
            });
            return convertView;
        }

        if (StringUtils.isEmpty(item)) {
            tv_letter.setVisibility(View.INVISIBLE);
            return convertView;
        }

        tv_letter.setText(item);
        tv_letter.setSelected(currentIndex == position);
        tv_letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != currentIndex) {
                    currentIndex = position;
                    mPlateNumberListener.onSelectedItemChanged(position);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }
}
