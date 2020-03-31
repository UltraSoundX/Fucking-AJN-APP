package com.e23.ajn.model;

public class SlidingImgModel {
    private int code;
    private DataBean data;
    private String msg;

    public static class DataBean {
        private String logo;
        private String name;
        private String url;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setLogo(String str) {
            this.logo = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
