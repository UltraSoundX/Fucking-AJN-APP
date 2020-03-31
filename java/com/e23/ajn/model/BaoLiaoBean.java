package com.e23.ajn.model;

public class BaoLiaoBean extends NewsBean {
    private String avatar;
    private String ding;
    private String nickname;
    private String userid;
    private String username;

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getDing() {
        return this.ding;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public void setDing(String str) {
        this.ding = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }
}
