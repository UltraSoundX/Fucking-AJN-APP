package com.e23.ajn.model;

import java.io.Serializable;

public class CommentBean implements Serializable {
    private String avatar;
    private String commentid;
    private String content;
    private String creat_at;
    private String hot;
    private String id;
    private String newsid;
    private String newstype;
    private String qlimg;
    private String replyconmmentid;
    private String replyposterid;
    private String replypostername;
    private String replysum;
    private String thumb;
    private String title;
    private String userid;
    private String username;
    private String wzposterid;
    private String zan;

    public String getReplysum() {
        return this.replysum;
    }

    public void setReplysum(String str) {
        this.replysum = str;
    }

    public String getWzposterid() {
        return this.wzposterid;
    }

    public void setWzposterid(String str) {
        this.wzposterid = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCommentid() {
        return this.commentid;
    }

    public void setCommentid(String str) {
        this.commentid = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getCreat_at() {
        return this.creat_at;
    }

    public void setCreat_at(String str) {
        this.creat_at = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getReplyconmmentid() {
        return this.replyconmmentid;
    }

    public void setReplyconmmentid(String str) {
        this.replyconmmentid = str;
    }

    public String getReplyposterid() {
        return this.replyposterid;
    }

    public void setReplyposterid(String str) {
        this.replyposterid = str;
    }

    public String getReplypostername() {
        return this.replypostername;
    }

    public void setReplypostername(String str) {
        this.replypostername = str;
    }

    public String getZan() {
        return this.zan;
    }

    public void setZan(String str) {
        this.zan = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String str) {
        this.thumb = str;
    }

    public String getQlimg() {
        return this.qlimg;
    }

    public void setQlimg(String str) {
        this.qlimg = str;
    }

    public String getHot() {
        return this.hot;
    }

    public void setHot(String str) {
        this.hot = str;
    }

    public String getNewstype() {
        return this.newstype;
    }

    public void setNewstype(String str) {
        this.newstype = str;
    }

    public String getNewsid() {
        return this.newsid;
    }

    public void setNewsid(String str) {
        this.newsid = str;
    }
}
