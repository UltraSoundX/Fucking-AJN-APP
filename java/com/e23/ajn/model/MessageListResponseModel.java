package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class MessageListResponseModel implements Serializable {
    private int code;
    private List<MessageBean> data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<MessageBean> getData() {
        return this.data;
    }

    public void setData(List<MessageBean> list) {
        this.data = list;
    }
}
