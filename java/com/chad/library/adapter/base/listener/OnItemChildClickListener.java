package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemChildClickListener extends SimpleClickListener {
    public abstract void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        onSimpleItemChildClick(baseQuickAdapter, view, i);
    }

    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }
}
