package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class HotKeyWordsResponseModel implements Serializable {
    private int code;
    private List<String> data;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<String> getData() {
        return this.data;
    }

    public void setData(List<String> list) {
        this.data = list;
    }
}
