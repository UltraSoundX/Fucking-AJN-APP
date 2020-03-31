package com.e23.ajn.model;

import java.util.List;

public class BaoLiaoResponse extends BaseResponse {
    private DataBean data;

    public static class DataBean {
        private List<BaoLiaoBean> news;

        public List<BaoLiaoBean> getNews() {
            return this.news;
        }

        public void setNews(List<BaoLiaoBean> list) {
            this.news = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
