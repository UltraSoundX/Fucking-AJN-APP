package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class HistoryResponseModel implements Serializable {
    private int code;
    private DataBean data;

    public static class DataBean {
        private List<NewsBean> news;

        public List<NewsBean> getNews() {
            return this.news;
        }

        public void setNews(List<NewsBean> list) {
            this.news = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }
}
