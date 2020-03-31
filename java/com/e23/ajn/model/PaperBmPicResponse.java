package com.e23.ajn.model;

import java.util.List;

public class PaperBmPicResponse extends BaseResponse {
    private List<DataBean> data;

    public static class DataBean {
        private String bm;
        private String paperid;
        private String verName;
        private String verOrder;

        public String getVerOrder() {
            return this.verOrder;
        }

        public void setVerOrder(String str) {
            this.verOrder = str;
        }

        public String getVerName() {
            return this.verName;
        }

        public void setVerName(String str) {
            this.verName = str;
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
