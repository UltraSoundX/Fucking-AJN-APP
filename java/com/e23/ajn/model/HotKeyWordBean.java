package com.e23.ajn.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.io.Serializable;

public class HotKeyWordBean implements MultiItemEntity, Serializable {
    public static final int TYPE_NORMAL = 1;
    private int itemType;
    private String keyword;
    private int spanSize;

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public int getItemType() {
        this.itemType = 1;
        setSpanSize(1);
        return this.itemType;
    }

    public int getSpanSize() {
        return this.spanSize;
    }

    public void setSpanSize(int i) {
        this.spanSize = i;
    }
}
