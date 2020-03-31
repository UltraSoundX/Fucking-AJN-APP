package com.chad.library.adapter.base.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView.v;

public interface OnItemSwipeListener {
    void clearView(v vVar, int i);

    void onItemSwipeMoving(Canvas canvas, v vVar, float f, float f2, boolean z);

    void onItemSwipeStart(v vVar, int i);

    void onItemSwiped(v vVar, int i);
}
