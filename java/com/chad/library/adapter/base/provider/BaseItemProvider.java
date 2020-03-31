package com.chad.library.adapter.base.provider;

import android.content.Context;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public abstract class BaseItemProvider<T, V extends BaseViewHolder> {
    public Context mContext;
    public List<T> mData;

    public abstract void convert(V v, T t, int i);

    public abstract int layout();

    public abstract int viewType();

    public void onClick(V v, T t, int i) {
    }

    public boolean onLongClick(V v, T t, int i) {
        return false;
    }
}
