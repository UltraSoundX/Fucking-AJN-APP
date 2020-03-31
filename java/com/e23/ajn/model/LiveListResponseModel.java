package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class LiveListResponseModel implements Serializable {
    private int code;
    private List<LiveBean> data;
    private String msg;

    public static class LiveBean implements Serializable {
        private String id;
        private String norb;
        private String status;
        private String thumb;
        private String time;
        private String title;
        private String url;
        private String views;

        public String getNorb() {
            return this.norb;
        }

        public void setNorb(String str) {
            this.norb = str;
        }

        public String getThumb() {
            return this.thumb;
        }

        public void setThumb(String str) {
            this.thumb = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

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

        public String getViews() {
            return this.views;
        }

        public void setViews(String str) {
            this.views = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public List<LiveBean> getData() {
        return this.data;
    }

    public void setData(List<LiveBean> list) {
        this.data = list;
    }
}
