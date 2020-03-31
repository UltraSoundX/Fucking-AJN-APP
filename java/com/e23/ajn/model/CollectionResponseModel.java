package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class CollectionResponseModel implements Serializable {
    private int code;
    private List<DataBean> data;

    public static class DataBean implements Serializable {
        private String atype;
        private String catid;
        private String catname;
        private long celltime;
        private String description;
        private String id;
        private String isdell;
        private Object modelid;
        private String newsid;
        private String thumb;
        private String title;
        private String url;
        private String userid;
        private String wytime;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getUserid() {
            return this.userid;
        }

        public void setUserid(String str) {
            this.userid = str;
        }

        public String getNewsid() {
            return this.newsid;
        }

        public void setNewsid(String str) {
            this.newsid = str;
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

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getThumb() {
            return this.thumb;
        }

        public void setThumb(String str) {
            this.thumb = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public long getCelltime() {
            return this.celltime;
        }

        public void setCelltime(long j) {
            this.celltime = j;
        }

        public Object getModelid() {
            return this.modelid;
        }

        public void setModelid(Object obj) {
            this.modelid = obj;
        }

        public String getAtype() {
            return this.atype;
        }

        public void setAtype(String str) {
            this.atype = str;
        }

        public String getIsdell() {
            return this.isdell;
        }

        public void setIsdell(String str) {
            this.isdell = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getWytime() {
            return this.wytime;
        }

        public void setWytime(String str) {
            this.wytime = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
