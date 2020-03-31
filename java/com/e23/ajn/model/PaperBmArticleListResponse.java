package com.e23.ajn.model;

import java.util.List;

public class PaperBmArticleListResponse extends BaseResponse {
    private List<DataBean> data;

    public static class DataBean {
        private String title;
        private String url;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
