package com.e23.ajn.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.io.Serializable;

public class NewsBean extends NewsFoundationBean implements MultiItemEntity, Serializable {
    public static final int SIZE_NORMAL = 2;
    private static final long serialVersionUID = 62516776833125561L;
    private int itemType;
    private NewsData newsData;
    private int spanSize;

    public NewsData getNewsData() {
        return this.newsData;
    }

    public void setNewsData(NewsData newsData2) {
        this.newsData = newsData2;
    }

    public int getSpanSize() {
        return this.spanSize;
    }

    public void setSpanSize(int i) {
        this.spanSize = i;
    }

    public int getItemType() {
        this.itemType = this.newstype;
        setSpanSize(2);
        return this.itemType;
    }
}
