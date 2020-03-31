package com.mob.tools.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public abstract class PullToRequestListAdapter extends PullToRequestBaseListAdapter {
    /* access modifiers changed from: private */
    public PullToRequestBaseAdapter adapter;
    /* access modifiers changed from: private */
    public boolean fling;
    /* access modifiers changed from: private */
    public ScrollableListView listView = onNewListView(getContext());
    /* access modifiers changed from: private */
    public OnListStopScrollListener osListener;
    /* access modifiers changed from: private */
    public boolean pullUpReady;

    public PullToRequestListAdapter(PullToRequestView pullToRequestView) {
        super(pullToRequestView);
        this.listView.setOnScrollListener(new OnScrollListener() {
            private int firstVisibleItem;
            private int visibleItemCount;

            public void onScrollStateChanged(AbsListView absListView, int i) {
                PullToRequestListAdapter.this.fling = i == 2;
                if (i != 0) {
                    return;
                }
                if (PullToRequestListAdapter.this.osListener != null) {
                    PullToRequestListAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
                } else if (PullToRequestListAdapter.this.adapter != null) {
                    PullToRequestListAdapter.this.adapter.notifyDataSetChanged();
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.firstVisibleItem = i;
                this.visibleItemCount = i2;
                View childAt = absListView.getChildAt(i2 - 1);
                PullToRequestListAdapter.this.pullUpReady = i + i2 == i3 && childAt != null && childAt.getBottom() <= absListView.getBottom();
                PullToRequestListAdapter.this.onScroll(PullToRequestListAdapter.this.listView, i, i2, i3);
            }
        });
        this.adapter = new PullToRequestBaseAdapter(this);
        this.listView.setAdapter(this.adapter);
    }

    /* access modifiers changed from: protected */
    public ScrollableListView onNewListView(Context context) {
        return new ScrollableListView(context);
    }

    public Scrollable getBodyView() {
        return this.listView;
    }

    public ListView getListView() {
        return this.listView;
    }

    public boolean isFling() {
        return this.fling;
    }

    public void onScroll(Scrollable scrollable, int i, int i2, int i3) {
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.adapter.notifyDataSetChanged();
    }

    public void setDivider(Drawable drawable) {
        this.listView.setDivider(drawable);
    }

    public void setDividerHeight(int i) {
        this.listView.setDividerHeight(i);
    }

    public boolean isPullDownReady() {
        return this.listView.isReadyToPull();
    }

    public boolean isPullUpReady() {
        return this.pullUpReady;
    }
}
