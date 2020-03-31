package com.e23.ajn.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.v;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.model.CateBean;
import java.util.Collections;
import java.util.List;

public class ProgramManageAdapter extends BaseItemDraggableAdapter<CateBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public a a;
    private Context b;

    public interface a {
        void a(int i);
    }

    public ProgramManageAdapter(Context context, List list) {
        super(R.layout.f198usernameconfirm, list);
        this.b = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(final BaseViewHolder baseViewHolder, CateBean cateBean) {
        baseViewHolder.setText((int) R.id.program_management_tv_name, (CharSequence) cateBean.getCatname());
        if (cateBean.getSelect() != 1) {
            baseViewHolder.setTextColor(R.id.program_management_tv_name, this.b.getResources().getColor(R.color.colorBlack));
            baseViewHolder.getView(R.id.program_management_tv_name).setBackgroundResource(R.drawable.f59ssdk_title_div);
        } else if (cateBean.getFixed() == 0) {
            baseViewHolder.getView(R.id.program_management_tv_name).setBackgroundResource(R.drawable.f59ssdk_title_div);
            baseViewHolder.setTextColor(R.id.program_management_tv_name, this.b.getResources().getColor(R.color.colorBlack));
        } else {
            baseViewHolder.setTextColor(R.id.program_management_tv_name, this.b.getResources().getColor(R.color.program_manager_n_color));
            baseViewHolder.getView(R.id.program_management_tv_name).setBackgroundResource(R.drawable.f58ssdk_oks_ptr_ptr);
        }
        baseViewHolder.getView(R.id.program_management_btn_del).setVisibility((cateBean.getSelect() == 1 && cateBean.getFixed() == 0) ? 0 : 8);
        baseViewHolder.getView(R.id.program_management_btn_del).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ProgramManageAdapter.this.a != null) {
                    ProgramManageAdapter.this.a.a(baseViewHolder.getLayoutPosition());
                }
            }
        });
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void setToggleDragOnLongPress(boolean z) {
        this.mDragOnLongPress = z;
        if (this.mDragOnLongPress) {
            this.mOnToggleViewTouchListener = null;
            this.mOnToggleViewLongClickListener = new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (ProgramManageAdapter.this.mItemTouchHelper != null && ProgramManageAdapter.this.itemDragEnabled && ((CateBean) ProgramManageAdapter.this.mData.get(ProgramManageAdapter.this.getViewHolderPosition((v) view.getTag(2131820547)))).getFixed() == 0) {
                        ProgramManageAdapter.this.mItemTouchHelper.b((v) view.getTag(2131820547));
                    }
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0 || ProgramManageAdapter.this.mDragOnLongPress) {
                    return false;
                }
                if (ProgramManageAdapter.this.mItemTouchHelper != null && ProgramManageAdapter.this.itemDragEnabled && ((CateBean) ProgramManageAdapter.this.mData.get(ProgramManageAdapter.this.getViewHolderPosition((v) view.getTag(2131820547)))).getFixed() == 0) {
                    ProgramManageAdapter.this.mItemTouchHelper.b((v) view.getTag(2131820547));
                }
                return true;
            }
        };
        this.mOnToggleViewLongClickListener = null;
    }

    public void onItemDragMoving(v vVar, v vVar2) {
        int viewHolderPosition = getViewHolderPosition(vVar);
        int viewHolderPosition2 = getViewHolderPosition(vVar2);
        if (a(viewHolderPosition) && a(viewHolderPosition2) && ((CateBean) this.mData.get(viewHolderPosition2)).getFixed() == 0) {
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
        if (((CateBean) this.mData.get(viewHolderPosition2)).getFixed() == 0 && this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragMoving(vVar, viewHolderPosition, vVar2, viewHolderPosition2);
        }
    }

    private boolean a(int i) {
        return i >= 0 && i < this.mData.size();
    }
}
