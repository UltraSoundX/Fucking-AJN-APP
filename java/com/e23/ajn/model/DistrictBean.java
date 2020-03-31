package com.e23.ajn.model;

import java.io.Serializable;
import org.litepal.crud.DataSupport;

public class DistrictBean extends DataSupport implements Serializable {
    private static final long serialVersionUID = 625167768153125561L;
    private String catid;
    private String catimgn;
    private String catimgs;
    private String catname;
    private NewsData newsData;
    private String views;

    public NewsData getNewsData() {
        return this.newsData;
    }

    public void setNewsData(NewsData newsData2) {
        this.newsData = newsData2;
    }

    public String getCatid() {
        return this.catid;
    }

    public void setCatid(String str) {
        this.catid = str;
    }

    public String getCatname() {
        return this.catname;
    }

    public void setCatname(String str) {
        this.catname = str;
    }

    public String getCatimgn() {
        return this.catimgn;
    }

    public void setCatimgn(String str) {
        this.catimgn = str;
    }

    public String getCatimgs() {
        return this.catimgs;
    }

    public void setCatimgs(String str) {
        this.catimgs = str;
    }

    public String getViews() {
        return this.views;
    }

    public void setViews(String str) {
        this.views = str;
    }
}
