package com.chad.library.adapter.base.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.v;
import android.support.v7.widget.a.a.C0015a;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;

public class ItemDragAndSwipeCallback extends C0015a {
    private BaseItemDraggableAdapter mAdapter;
    private int mDragMoveFlags = 15;
    private float mMoveThreshold = 0.1f;
    private int mSwipeMoveFlags = 32;
    private float mSwipeThreshold = 0.7f;

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter baseItemDraggableAdapter) {
        this.mAdapter = baseItemDraggableAdapter;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public boolean isItemViewSwipeEnabled() {
        return this.mAdapter.isItemSwipeEnable();
    }

    public void onSelectedChanged(v vVar, int i) {
        if (i == 2 && !isViewCreateByAdapter(vVar)) {
            this.mAdapter.onItemDragStart(vVar);
            vVar.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, Boolean.valueOf(true));
        } else if (i == 1 && !isViewCreateByAdapter(vVar)) {
            this.mAdapter.onItemSwipeStart(vVar);
            vVar.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, Boolean.valueOf(true));
        }
        super.onSelectedChanged(vVar, i);
    }

    public void clearView(RecyclerView recyclerView, v vVar) {
        super.clearView(recyclerView, vVar);
        if (!isViewCreateByAdapter(vVar)) {
            if (vVar.itemView.getTag(R.id.BaseQuickAdapter_dragging_support) != null && ((Boolean) vVar.itemView.getTag(R.id.BaseQuickAdapter_dragging_support)).booleanValue()) {
                this.mAdapter.onItemDragEnd(vVar);
                vVar.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, Boolean.valueOf(false));
            }
            if (vVar.itemView.getTag(R.id.BaseQuickAdapter_swiping_support) != null && ((Boolean) vVar.itemView.getTag(R.id.BaseQuickAdapter_swiping_support)).booleanValue()) {
                this.mAdapter.onItemSwipeClear(vVar);
                vVar.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, Boolean.valueOf(false));
            }
        }
    }

    public int getMovementFlags(RecyclerView recyclerView, v vVar) {
        if (isViewCreateByAdapter(vVar)) {
            return makeMovementFlags(0, 0);
        }
        return makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    public boolean onMove(RecyclerView recyclerView, v vVar, v vVar2) {
        return vVar.getItemViewType() == vVar2.getItemViewType();
    }

    public void onMoved(RecyclerView recyclerView, v vVar, int i, v vVar2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, vVar, i, vVar2, i2, i3, i4);
        this.mAdapter.onItemDragMoving(vVar, vVar2);
    }

    public void onSwiped(v vVar, int i) {
        if (!isViewCreateByAdapter(vVar)) {
            this.mAdapter.onItemSwiped(vVar);
        }
    }

    public float getMoveThreshold(v vVar) {
        return this.mMoveThreshold;
    }

    public float getSwipeThreshold(v vVar) {
        return this.mSwipeThreshold;
    }

    public void setSwipeThreshold(float f) {
        this.mSwipeThreshold = f;
    }

    public void setMoveThreshold(float f) {
        this.mMoveThreshold = f;
    }

    public void setDragMoveFlags(int i) {
        this.mDragMoveFlags = i;
    }

    public void setSwipeMoveFlags(int i) {
        this.mSwipeMoveFlags = i;
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, v vVar, float f, float f2, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, vVar, f, f2, i, z);
        if (i == 1 && !isViewCreateByAdapter(vVar)) {
            View view = vVar.itemView;
            canvas.save();
            if (f > 0.0f) {
                canvas.clipRect((float) view.getLeft(), (float) view.getTop(), ((float) view.getLeft()) + f, (float) view.getBottom());
                canvas.translate((float) view.getLeft(), (float) view.getTop());
            } else {
                canvas.clipRect(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
                canvas.translate(((float) view.getRight()) + f, (float) view.getTop());
            }
            this.mAdapter.onItemSwiping(canvas, vVar, f, f2, z);
            canvas.restore();
        }
    }

    private boolean isViewCreateByAdapter(v vVar) {
        int itemViewType = vVar.getItemViewType();
        return itemViewType == 273 || itemViewType == 546 || itemViewType == 819 || itemViewType == 1365;
    }
}
