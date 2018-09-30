package com.weddingcar.driver.function.mine.adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int mTop;
    private int mColumn;

    public SpaceItemDecoration(int top, int column) {
        mTop = top;
        mColumn = column;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildLayoutPosition(view);
        if (position > mColumn) {
            outRect.top = mTop;
        }

        if (position < mColumn) {
            outRect.bottom = mTop;
        }
    }

}
