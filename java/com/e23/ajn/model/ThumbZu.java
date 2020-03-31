package com.e23.ajn.model;

import java.io.Serializable;

public class ThumbZu implements Serializable {
    private String alt;
    private String catId;
    private String url;

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String str) {
        this.alt = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getCatId() {
        return this.catId;
    }

    public void setCatId(String str) {
        this.catId = str;
    }
}
