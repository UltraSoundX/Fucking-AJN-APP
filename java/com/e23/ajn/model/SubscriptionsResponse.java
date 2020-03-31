package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class SubscriptionsResponse implements Serializable {
    private int code;
    private List<DyBean> dy;

    public static class DyBean {
        private String dingyue;
        private String headimg;
        private String intro;
        private String userid;
        private String username;

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

        public String getHeadimg() {
            return this.headimg;
        }

        public void setHeadimg(String str) {
            this.headimg = str;
        }

        public String getIntro() {
            return this.intro;
        }

        public void setIntro(String str) {
            this.intro = str;
        }

        public String getDingyue() {
            return this.dingyue;
        }

        public void setDingyue(String str) {
            this.dingyue = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<DyBean> getDy() {
        return this.dy;
    }

    public void setDy(List<DyBean> list) {
        this.dy = list;
    }
}
