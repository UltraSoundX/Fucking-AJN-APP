package com.e23.ajn.model;

import java.io.Serializable;

public class AdImageInfo implements Serializable {
    private String fximgurl;
    private String imgurl;
    private int isRed;
    private String linkurl;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String str) {
        this.imgurl = str;
    }

    public String getFximgurl() {
        return this.fximgurl;
    }

    public void setFximgurl(String str) {
        this.fximgurl = str;
    }

    public String getLinkurl() {
        return this.linkurl;
    }

    public void setLinkurl(String str) {
        this.linkurl = str;
    }

    public int getIsRed() {
        return this.isRed;
    }

    public void setIsRed(int i) {
        this.isRed = i;
    }
}
