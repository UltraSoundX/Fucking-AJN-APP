package com.e23.ajn.model;

public class MessageSendResponse extends BaseResponse {
    public MessageBean data;

    public MessageBean getData() {
        return this.data;
    }

    public void setData(MessageBean messageBean) {
        this.data = messageBean;
    }
}
