package com.e23.ajn.model;

public class PostCommentResponse extends BaseResponse {
    private CommentBean data;

    public CommentBean getData() {
        return this.data;
    }

    public void setData(CommentBean commentBean) {
        this.data = commentBean;
    }
}
