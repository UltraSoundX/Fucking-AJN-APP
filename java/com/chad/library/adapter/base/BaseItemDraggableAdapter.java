package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.v;
import android.support.v7.widget.a.a;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;
import java.util.List;

public abstract class BaseItemDraggableAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final String ERROR_NOT_SAME_ITEMTOUCHHELPER = "Item drag and item swipe should pass the same ItemTouchHelper";
    private static final int NO_TOGGLE_VIEW = 0;
    protected boolean itemDragEnabled = false;
    protected boolean itemSwipeEnabled = false;
    protected boolean mDragOnLongPress = true;
    protected a mItemTouchHelper;
    protected OnItemDragListener mOnItemDragListener;
    protected OnItemSwipeListener mOnItemSwipeListener;
    protected OnLongClickListener mOnToggleViewLongClickListener;
    protected OnTouchListener mOnToggleViewTouchListener;
    protected int mToggleViewId = 0;

    public BaseItemDraggableAdapter(List<T> list) {
        super(list);
    }

    public BaseItemDraggableAdapter(int i, List<T> list) {
        super(i, list);
    }

    public void onBindViewHolder(K k, int i) {
        super.onBindViewHolder(k, i);
        int itemViewType = k.getItemViewType();
        if (this.mItemTouchHelper != null && this.itemDragEnabled && itemViewType != 546 && itemViewType != 273 && itemViewType != 1365 && itemViewType != 819) {
            if (this.mToggleViewId != 0) {
                View view = k.getView(this.mToggleViewId);
                if (view != null) {
                    view.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                    if (this.mDragOnLongPress) {
                        view.setOnLongClickListener(this.mOnToggleViewLongClickListener);
                    } else {
                        view.setOnTouchListener(this.mOnToggleViewTouchListener);
                    }
                }
            } else {
                k.itemView.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                k.itemView.setOnLongClickListener(this.mOnToggleViewLongClickListener);
            }
        }
    }

    public void setToggleViewId(int i) {
        this.mToggleViewId = i;
    }

    public void setToggleDragOnLongPress(boolean z) {
        this.mDragOnLongPress = z;
        if (this.mDragOnLongPress) {
            this.mOnToggleViewTouchListener = null;
            this.mOnToggleViewLongClickListener = new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (BaseItemDraggableAdapter.this.mItemTouchHelper != null && BaseItemDraggableAdapter.this.itemDragEnabled) {
                        BaseItemDraggableAdapter.this.mItemTouchHelper.b((v) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                    }
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0 || BaseItemDraggableAdapter.this.mDragOnLongPress) {
                    return false;
                }
                if (BaseItemDraggableAdapter.this.mItemTouchHelper != null && BaseItemDraggableAdapter.this.itemDragEnabled) {
                    BaseItemDraggableAdapter.this.mItemTouchHelper.b((v) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                }
                return true;
            }
        };
        this.mOnToggleViewLongClickListener = null;
    }

    public void enableDragItem(a aVar) {
        enableDragItem(aVar, 0, true);
    }

    public void enableDragItem(a aVar, int i, boolean z) {
        this.itemDragEnabled = true;
        this.mItemTouchHelper = aVar;
        setToggleViewId(i);
        setToggleDragOnLongPress(z);
    }

    public void disableDragItem() {
        this.itemDragEnabled = false;
        this.mItemTouchHelper = null;
    }

    public boolean isItemDraggable() {
        return this.itemDragEnabled;
    }

    public void enableSwipeItem() {
        this.itemSwipeEnabled = true;
    }

    public void disableSwipeItem() {
        this.itemSwipeEnabled = false;
    }

    public boolean isItemSwipeEnable() {
        return this.itemSwipeEnabled;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
    }

    public int getViewHolderPosition(v vVar) {
        return vVar.getAdapterPosition() - getHeaderLayoutCount();
    }

    public void onItemDragStart(v vVar) {
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragStart(vVar, getViewHolderPosition(vVar));
        }
    }

    public void onItemDragMoving(v vVar, v vVar2) {
        int viewHolderPosition = getViewHolderPosition(vVar);
        int viewHolderPosition2 = getViewHolderPosition(vVar2);
        if (inRange(viewHolderPosition) && inRange(viewHolderPosition2)) {
            if (viewHolderPosition < viewHolderPosition2) {
                for (int i = viewHolderPosition; i < viewHolderPosition2; i++) {
                    Collections.swap(this.mData, i, i + 1);
                }
            } else {
                for (int i2 = viewHolderPosition; i2 > viewHolderPosition2; i2--) {
                    Collections.swap(this.mData, i2, i2 - 1);
                }
            }
            notifyItemMoved(vVar.getAdapterPosition(), vVar2.getAdapterPosition());
        }
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragMoving(vVar, viewHolderPosition, vVar2, viewHolderPosition2);
        }
    }

    public void onItemDragEnd(v vVar) {
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragEnd(vVar, getViewHolderPosition(vVar));
        }
    }

    public void setOnItemSwipeListener(OnItemSwipeListener onItemSwipeListener) {
        this.mOnItemSwipeListener = onItemSwipeListener;
    }

    public void onItemSwipeStart(v vVar) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwipeStart(vVar, getViewHolderPosition(vVar));
        }
    }

    public void onItemSwipeClear(v vVar) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.clearView(vVar, getViewHolderPosition(vVar));
        }
    }

    public void onItemSwiped(v vVar) {
        int viewHolderPosition = getViewHolderPosition(vVar);
        if (inRange(viewHolderPosition)) {
            this.mData.remove(viewHolderPosition);
            notifyItemRemoved(vVar.getAdapterPosition());
        }
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwiped(vVar, getViewHolderPosition(vVar));
        }
    }

    public void onItemSwiping(Canvas canvas, v vVar, float f, float f2, boolean z) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwipeMoving(canvas, vVar, f, f2, z);
        }
    }

    private boolean inRange(int i) {
        return i >= 0 && i < this.mData.size();
    }
}
