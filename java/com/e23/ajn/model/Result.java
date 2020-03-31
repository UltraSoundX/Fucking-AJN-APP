package com.e23.ajn.model;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 62591232153145561L;
    private int code;
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
}
