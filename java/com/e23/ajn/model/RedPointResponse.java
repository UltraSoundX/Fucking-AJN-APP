package com.e23.ajn.model;

public class RedPointResponse extends BaseResponse {
    private DataBean data;

    public static class DataBean {
        private int atsum;
        private int messagesum;
        private int pltomesum;

        public int getAtsum() {
            return this.atsum;
        }

        public void setAtsum(int i) {
            this.atsum = i;
        }

        public int getMessagesum() {
            return this.messagesum;
        }

        public void setMessagesum(int i) {
            this.messagesum = i;
        }

        public int getPltomesum() {
            return this.pltomesum;
        }

        public void setPltomesum(int i) {
            this.pltomesum = i;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
