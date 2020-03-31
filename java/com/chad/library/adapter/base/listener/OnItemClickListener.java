package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemClickListener extends SimpleClickListener {
    public abstract void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        onSimpleItemClick(baseQuickAdapter, view, i);
    }

    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }
}
