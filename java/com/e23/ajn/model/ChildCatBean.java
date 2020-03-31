package com.e23.ajn.model;

import java.io.Serializable;
import org.litepal.crud.DataSupport;

public class ChildCatBean extends DataSupport implements Serializable {
    private static final long serialVersionUID = 625167768153125561L;
    private String catid;
    private String catimg;
    private String cname;
    private NewsData newsData;
    private String opentype;
    private String url;

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

    public String getCatimg() {
        return this.catimg;
    }

    public void setCatimg(String str) {
        this.catimg = str;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String str) {
        this.cname = str;
    }

    public String getOpentype() {
        return this.opentype;
    }

    public void setOpentype(String str) {
        this.opentype = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
