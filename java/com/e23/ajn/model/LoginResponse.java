package com.e23.ajn.model;

import java.io.Serializable;

public class LoginResponse extends BaseResponse implements Serializable {
    private DataBean data;

    public static class DataBean implements Serializable {
        private String area;
        private String avatar;
        private String dingyue;
        private String hasnewmess;
        private String intro;
        private String jifen;
        private String koufen;
        private String mobile;
        private String nickname;
        private String point;
        private String promocode;
        private String promofix;
        private String qiandao;
        private String reporter_type;
        private String sex;
        private String token;
        private String truejifen;
        private String userfrom;
        private String userid;
        private String username;

        public String getReporter_type() {
            return this.reporter_type;
        }

        public void setReporter_type(String str) {
            this.reporter_type = str;
        }

        public String getPromocode() {
            return this.promocode;
        }

        public void setPromocode(String str) {
            this.promocode = str;
        }

        public String getPromofix() {
            return this.promofix;
        }

        public void setPromofix(String str) {
            this.promofix = str;
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

        public String getSex() {
            return this.sex;
        }

        public void setSex(String str) {
            this.sex = str;
        }

        public String getArea() {
            return this.area;
        }

        public void setArea(String str) {
            this.area = str;
        }

        public String getPoint() {
            return this.point;
        }

        public void setPoint(String str) {
            this.point = str;
        }

        public String getUserfrom() {
            return this.userfrom;
        }

        public void setUserfrom(String str) {
            this.userfrom = str;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getJifen() {
            return this.jifen;
        }

        public void setJifen(String str) {
            this.jifen = str;
        }

        public String getHasnewmess() {
            return this.hasnewmess;
        }

        public void setHasnewmess(String str) {
            this.hasnewmess = str;
        }

        public String getQiandao() {
            return this.qiandao;
        }

        public void setQiandao(String str) {
            this.qiandao = str;
        }

        public String getDingyue() {
            return this.dingyue;
        }

        public void setDingyue(String str) {
            this.dingyue = str;
        }

        public String getIntro() {
            return this.intro;
        }

        public void setIntro(String str) {
            this.intro = str;
        }

        public String getTruejifen() {
            return this.truejifen;
        }

        public void setTruejifen(String str) {
            this.truejifen = str;
        }

        public String getKoufen() {
            return this.koufen;
        }

        public void setKoufen(String str) {
            this.koufen = str;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setMobile(String str) {
            this.mobile = str;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String str) {
            this.nickname = str;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
