package com.weddingcar.driver.function.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.function.mine.listener.PlateNumberListener;

import java.util.ArrayList;
import java.util.List;

public class LetterListAdapter extends RecyclerView.Adapter<LetterListAdapter.ViewHolder>{

    private Context mContext;
    private List<String> data = new ArrayList<>();
    private int currentIndex = -1;
    private PlateNumberListener mPlateNumberListener;
    private int positionStartIndex = 0;

    public LetterListAdapter(Context c, PlateNumberListener plateNumberListener) {
        mContext = c;
        mPlateNumberListener = plateNumberListener;
    }

    public void setData(List<String> dataList, int positionStart) {
        positionStartIndex = positionStart;
        data = dataList;
        notifyDataSetChanged();
    }

    public void setSelectedIndex(int index) {
        int lastIndex = currentIndex;
        currentIndex = index;
        if (lastIndex != -1) {
            notifyItemChanged(lastIndex);
        }
        if (currentIndex != -1) {
            notifyItemChanged(currentIndex);
        }
    }

    @NonNull
    @Override
    public LetterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_letter_list, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i == data.size() - 1) {
            viewHolder.tv_letter.setText("");
            viewHolder.tv_letter.setBackground(mContext.getResources().getDrawable(R.drawable.ic_delete));
            viewHolder.tv_letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlateNumberListener.onButtonDeleteClicked();
                }
            });
            return;
        }

        String item = data.get(i);
        if (StringUtils.isEmpty(item)) {
            viewHolder.tv_letter.setVisibility(View.INVISIBLE);
            return;
        }
        viewHolder.tv_letter.setText(item);
        viewHolder.tv_letter.setSelected(currentIndex == i);
        viewHolder.tv_letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != currentIndex) {
                    if (currentIndex != -1){
                        notifyItemChanged(currentIndex);
                    }
                    currentIndex = i;
                    notifyItemChanged(i);
                    mPlateNumberListener.onSelectedItemChanged(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        System.out.println("YIN=======>"+positionStartIndex);
        System.out.println("YIN=======>"+position);
        return positionStartIndex + position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_letter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_letter = (TextView) itemView.findViewById(R.id.tv_letter);
        }
    }
}
