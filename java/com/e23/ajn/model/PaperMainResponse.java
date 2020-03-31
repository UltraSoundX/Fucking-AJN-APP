package com.e23.ajn.model;

import java.util.List;

public class PaperMainResponse extends BaseResponse {
    private List<DataBean> data;

    public static class DataBean {
        private String bm;
        private String paperdate;
        private String paperid;
        private String papername;
        private String papertype;
        private String title;

        public String getPapername() {
            return this.papername;
        }

        public void setPapername(String str) {
            this.papername = str;
        }

        public String getPapertype() {
            return this.papertype;
        }

        public void setPapertype(String str) {
            this.papertype = str;
        }

        public String getPaperdate() {
            return this.paperdate;
        }

        public void setPaperdate(String str) {
            this.paperdate = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getPaperid() {
            return this.paperid;
        }

        public void setPaperid(String str) {
            this.paperid = str;
        }

        public String getBm() {
            return this.bm;
        }

        public void setBm(String str) {
            this.bm = str;
        }
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
