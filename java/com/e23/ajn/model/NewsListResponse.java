package com.e23.ajn.model;

public class NewsListResponse {
    private int code;
    private NewsData data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public NewsData getData() {
        return this.data;
    }

    public void setData(NewsData newsData) {
        this.data = newsData;
    }
}
