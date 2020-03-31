package com.e23.ajn.model;

import java.util.List;

public class PaperMonthBzResponse extends BaseResponse {
    private List<DataBean> data;

    public static class DataBean {
        private String folder;
        private String id;
        private String publishdate;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getPublishdate() {
            return this.publishdate;
        }

        public void setPublishdate(String str) {
            this.publishdate = str;
        }

        public String getFolder() {
            return this.folder;
        }

        public void setFolder(String str) {
            this.folder = str;
        }
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
