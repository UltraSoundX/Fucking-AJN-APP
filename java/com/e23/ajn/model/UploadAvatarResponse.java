package com.e23.ajn.model;

public class UploadAvatarResponse extends BaseResponse {
    private DataBean data;

    public static class DataBean {
        private String headimg;

        public String getHeadimg() {
            return this.headimg;
        }

        public void setHeadimg(String str) {
            this.headimg = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
