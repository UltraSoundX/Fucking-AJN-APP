package com.chad.library.adapter.base.listener;

import android.os.Build.VERSION;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.l;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleClickListener implements l {
    public static String TAG = "SimpleClickListener";
    protected BaseQuickAdapter baseQuickAdapter;
    private GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public boolean mIsPrepressed = false;
    /* access modifiers changed from: private */
    public boolean mIsShowPress = false;
    /* access modifiers changed from: private */
    public View mPressedView = null;
    private RecyclerView recyclerView;

    private class ItemTouchHelperGestureListener implements OnGestureListener {
        private RecyclerView recyclerView;

        public boolean onDown(MotionEvent motionEvent) {
            SimpleClickListener.this.mIsPrepressed = true;
            SimpleClickListener.this.mPressedView = this.recyclerView.a(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                SimpleClickListener.this.mIsShowPress = true;
            }
        }

        ItemTouchHelperGestureListener(RecyclerView recyclerView2) {
            this.recyclerView = recyclerView2;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                if (this.recyclerView.getScrollState() != 0) {
                    return false;
                }
                View access$100 = SimpleClickListener.this.mPressedView;
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.b(access$100);
                if (SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                    return false;
                }
                HashSet<Integer> childClickViewIds = baseViewHolder.getChildClickViewIds();
                Set nestViews = baseViewHolder.getNestViews();
                if (childClickViewIds == null || childClickViewIds.size() <= 0) {
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, access$100);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    if (childClickViewIds != null && childClickViewIds.size() > 0) {
                        for (Integer intValue : childClickViewIds) {
                            View findViewById = access$100.findViewById(intValue.intValue());
                            if (findViewById != null) {
                                findViewById.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener.this.onItemClick(SimpleClickListener.this.baseQuickAdapter, access$100, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                } else {
                    for (Integer num : childClickViewIds) {
                        View findViewById2 = access$100.findViewById(num.intValue());
                        if (findViewById2 != null) {
                            if (!SimpleClickListener.this.inRangeOfView(findViewById2, motionEvent) || !findViewById2.isEnabled()) {
                                findViewById2.setPressed(false);
                            } else if (nestViews != null && nestViews.contains(num)) {
                                return false;
                            } else {
                                SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById2);
                                findViewById2.setPressed(true);
                                SimpleClickListener.this.onItemChildClick(SimpleClickListener.this.baseQuickAdapter, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                resetPressedView(findViewById2);
                                return true;
                            }
                        }
                    }
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, access$100);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    for (Integer intValue2 : childClickViewIds) {
                        View findViewById3 = access$100.findViewById(intValue2.intValue());
                        if (findViewById3 != null) {
                            findViewById3.setPressed(false);
                        }
                    }
                    SimpleClickListener.this.onItemClick(SimpleClickListener.this.baseQuickAdapter, access$100, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                }
                resetPressedView(access$100);
            }
            return true;
        }

        private void resetPressedView(final View view) {
            if (view != null) {
                view.postDelayed(new Runnable() {
                    public void run() {
                        if (view != null) {
                            view.setPressed(false);
                        }
                    }
                }, 50);
            }
            SimpleClickListener.this.mIsPrepressed = false;
            SimpleClickListener.this.mPressedView = null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0085  */
        /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onLongPress(android.view.MotionEvent r10) {
            /*
                r9 = this;
                r3 = 0
                r2 = 1
                android.support.v7.widget.RecyclerView r0 = r9.recyclerView
                int r0 = r0.getScrollState()
                if (r0 == 0) goto L_0x000b
            L_0x000a:
                return
            L_0x000b:
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r0 = r0.mIsPrepressed
                if (r0 == 0) goto L_0x000a
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = r0.mPressedView
                if (r0 == 0) goto L_0x000a
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = r0.mPressedView
                r0.performHapticFeedback(r3)
                android.support.v7.widget.RecyclerView r0 = r9.recyclerView
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r1 = r1.mPressedView
                android.support.v7.widget.RecyclerView$v r0 = r0.b(r1)
                com.chad.library.adapter.base.BaseViewHolder r0 = (com.chad.library.adapter.base.BaseViewHolder) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                int r4 = r0.getLayoutPosition()
                boolean r1 = r1.isHeaderOrFooterPosition(r4)
                if (r1 != 0) goto L_0x000a
                java.util.HashSet r4 = r0.getItemChildLongClickViewIds()
                java.util.Set r5 = r0.getNestViews()
                if (r4 == 0) goto L_0x0107
                int r1 = r4.size()
                if (r1 <= 0) goto L_0x0107
                java.util.Iterator r6 = r4.iterator()
            L_0x0052:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L_0x0107
                java.lang.Object r1 = r6.next()
                java.lang.Integer r1 = (java.lang.Integer) r1
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r7 = r7.mPressedView
                int r8 = r1.intValue()
                android.view.View r7 = r7.findViewById(r8)
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r8 = r8.inRangeOfView(r7, r10)
                if (r8 == 0) goto L_0x0052
                boolean r8 = r7.isEnabled()
                if (r8 == 0) goto L_0x0052
                if (r5 == 0) goto L_0x00db
                boolean r1 = r5.contains(r1)
                if (r1 == 0) goto L_0x00db
                r1 = r2
            L_0x0083:
                if (r1 != 0) goto L_0x000a
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r5 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r5.baseQuickAdapter
                com.chad.library.adapter.base.listener.SimpleClickListener r6 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r6 = r6.mPressedView
                int r0 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r7 = r7.baseQuickAdapter
                int r7 = r7.getHeaderLayoutCount()
                int r0 = r0 - r7
                r1.onItemLongClick(r5, r6, r0)
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r1 = r1.mPressedView
                r0.setPressViewHotSpot(r10, r1)
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = r0.mPressedView
                r0.setPressed(r2)
                if (r4 == 0) goto L_0x0100
                java.util.Iterator r1 = r4.iterator()
            L_0x00bb:
                boolean r0 = r1.hasNext()
                if (r0 == 0) goto L_0x0100
                java.lang.Object r0 = r1.next()
                java.lang.Integer r0 = (java.lang.Integer) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r4 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r4 = r4.mPressedView
                int r0 = r0.intValue()
                android.view.View r0 = r4.findViewById(r0)
                if (r0 == 0) goto L_0x00bb
                r0.setPressed(r3)
                goto L_0x00bb
            L_0x00db:
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                r1.setPressViewHotSpot(r10, r7)
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r5 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r5.baseQuickAdapter
                int r6 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r8 = r8.baseQuickAdapter
                int r8 = r8.getHeaderLayoutCount()
                int r6 = r6 - r8
                r1.onItemChildLongClick(r5, r7, r6)
                r7.setPressed(r2)
                com.chad.library.adapter.base.listener.SimpleClickListener r1 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                r1.mIsShowPress = r2
                r1 = r2
                goto L_0x0083
            L_0x0100:
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                r0.mIsShowPress = r2
                goto L_0x000a
            L_0x0107:
                r1 = r3
                goto L_0x0083
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chad.library.adapter.base.listener.SimpleClickListener.ItemTouchHelperGestureListener.onLongPress(android.view.MotionEvent):void");
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }
    }

    public abstract void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemLongClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public boolean onInterceptTouchEvent(RecyclerView recyclerView2, MotionEvent motionEvent) {
        if (this.recyclerView == null) {
            this.recyclerView = recyclerView2;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        } else if (this.recyclerView != recyclerView2) {
            this.recyclerView = recyclerView2;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        }
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && motionEvent.getActionMasked() == 1 && this.mIsShowPress) {
            if (this.mPressedView != null) {
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.b(this.mPressedView);
                if (baseViewHolder == null || !isHeaderOrFooterView(baseViewHolder.getItemViewType())) {
                    this.mPressedView.setPressed(false);
                }
            }
            this.mIsShowPress = false;
            this.mIsPrepressed = false;
        }
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView2, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    /* access modifiers changed from: private */
    public void setPressViewHotSpot(MotionEvent motionEvent, View view) {
        if (VERSION.SDK_INT >= 21 && view != null && view.getBackground() != null) {
            view.getBackground().setHotspot(motionEvent.getRawX(), motionEvent.getY() - view.getY());
        }
    }

    public boolean inRangeOfView(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        if (view == null || !view.isShown()) {
            return false;
        }
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        if (motionEvent.getRawX() < ((float) i) || motionEvent.getRawX() > ((float) (i + view.getWidth())) || motionEvent.getRawY() < ((float) i2) || motionEvent.getRawY() > ((float) (i2 + view.getHeight()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isHeaderOrFooterPosition(int i) {
        if (this.baseQuickAdapter == null) {
            if (this.recyclerView == null) {
                return false;
            }
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
        }
        int itemViewType = this.baseQuickAdapter.getItemViewType(i);
        return itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546;
    }

    private boolean isHeaderOrFooterView(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }
}
