package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class ReporterListResponse extends BaseResponse implements Serializable {
    private List<DataBean> data;

    public static class DataBean {
        private String mobile;
        private String proreporter_avatar;
        private String reporter_intro;
        private String reporter_type;
        private String truename;
        private String userid;

        public String getUserid() {
            return this.userid;
        }

        public void setUserid(String str) {
            this.userid = str;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setMobile(String str) {
            this.mobile = str;
        }

        public String getTruename() {
            return this.truename;
        }

        public void setTruename(String str) {
            this.truename = str;
        }

        public String getReporter_intro() {
            return this.reporter_intro;
        }

        public void setReporter_intro(String str) {
            this.reporter_intro = str;
        }

        public String getReporter_type() {
            return this.reporter_type;
        }

        public void setReporter_type(String str) {
            this.reporter_type = str;
        }

        public String getProreporter_avatar() {
            return this.proreporter_avatar;
        }

        public void setProreporter_avatar(String str) {
            this.proreporter_avatar = str;
        }
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
