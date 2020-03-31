package com.e23.ajn.model;

import java.io.Serializable;

public class CateListResponseModel implements Serializable {
    private int code;
    private CateListData data;
    private String msg;

    public CateListData getData() {
        return this.data;
    }

    public void setData(CateListData cateListData) {
        this.data = cateListData;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }
}
