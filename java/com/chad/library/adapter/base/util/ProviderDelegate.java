package com.chad.library.adapter.base.util;

import android.util.SparseArray;
import com.chad.library.adapter.base.provider.BaseItemProvider;

public class ProviderDelegate {
    private SparseArray<BaseItemProvider> mItemProviders = new SparseArray<>();

    public void registerProvider(BaseItemProvider baseItemProvider) {
        if (baseItemProvider == null) {
            throw new ItemProviderException("ItemProvider can not be null");
        }
        int viewType = baseItemProvider.viewType();
        if (this.mItemProviders.get(viewType) == null) {
            this.mItemProviders.put(viewType, baseItemProvider);
        }
    }

    public SparseArray<BaseItemProvider> getItemProviders() {
        return this.mItemProviders;
    }
}
