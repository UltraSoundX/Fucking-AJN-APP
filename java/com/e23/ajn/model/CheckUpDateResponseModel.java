package com.e23.ajn.model;

import java.io.Serializable;

public class CheckUpDateResponseModel implements Serializable {
    private static final long serialVersionUID = -6256712108153145564L;
    private int code;
    private UpdateModel data;
    private String msg;

    public UpdateModel getData() {
        return this.data;
    }

    public void setData(UpdateModel updateModel) {
        this.data = updateModel;
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
