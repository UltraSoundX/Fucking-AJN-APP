package com.e23.ajn.model;

import java.io.Serializable;

public class ThumbBean extends NewsFoundationBean implements Serializable {
    private static final long serialVersionUID = 625167768153125561L;
    private NewsData newsData;

    public NewsData getNewsData() {
        return this.newsData;
    }

    public void setNewsData(NewsData newsData2) {
        this.newsData = newsData2;
    }
}
