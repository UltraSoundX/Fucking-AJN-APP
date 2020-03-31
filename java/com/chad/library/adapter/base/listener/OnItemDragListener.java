package com.chad.library.adapter.base.listener;

import android.support.v7.widget.RecyclerView.v;

public interface OnItemDragListener {
    void onItemDragEnd(v vVar, int i);

    void onItemDragMoving(v vVar, int i, v vVar2, int i2);

    void onItemDragStart(v vVar, int i);
}
