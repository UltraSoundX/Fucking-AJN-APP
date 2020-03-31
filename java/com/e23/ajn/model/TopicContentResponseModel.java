package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class TopicContentResponseModel implements Serializable {
    private int code;
    private DataBean data;

    public static class DataBean implements Serializable {
        private List<NewsBean> catContentList;
        private String catid;
        private String catname;
        private String description;
        private String sharedomain;
        private String thumb;

        public String getSharedomain() {
            return this.sharedomain;
        }

        public void setSharedomain(String str) {
            this.sharedomain = str;
        }

        public String getCatid() {
            return this.catid;
        }

        public void setCatid(String str) {
            this.catid = str;
        }

        public String getCatname() {
            return this.catname;
        }

        public void setCatname(String str) {
            this.catname = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getThumb() {
            return this.thumb;
        }

        public void setThumb(String str) {
            this.thumb = str;
        }

        public List<NewsBean> getCatContentList() {
            return this.catContentList;
        }

        public void setCatContentList(List<NewsBean> list) {
            this.catContentList = list;
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
