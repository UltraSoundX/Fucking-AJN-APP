package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class CommentListResponseModel implements Serializable {
    private int code;
    private List<CommentBean> data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<CommentBean> getData() {
        return this.data;
    }

    public void setData(List<CommentBean> list) {
        this.data = list;
    }
}
