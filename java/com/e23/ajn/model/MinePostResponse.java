package com.e23.ajn.model;

import java.util.ArrayList;
import java.util.List;

public class MinePostResponse extends BaseResponse {
    private DataBean data;

    public static class DataBean {
        private List<NewsBean> news;

        public static class NewsBean {
            private String catid;
            private String description;
            private String ding;
            private String id;
            private long inputtime;
            private String islink;
            private String newsclass;
            private String newstype;
            private String norb;
            private String opentype;
            private String plsum;
            private String posterid;
            private String postername;
            private String thumb;
            protected ArrayList<ThumbZu> thumbs;
            private String title;
            private String url;
            private String videourl;

            public ArrayList<ThumbZu> getThumbs() {
                return this.thumbs;
            }

            public void setThumbs(ArrayList<ThumbZu> arrayList) {
                this.thumbs = arrayList;
            }

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }

            public String getCatid() {
                return this.catid;
            }

            public void setCatid(String str) {
                this.catid = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDescription() {
                return this.description;
            }

            public void setDescription(String str) {
                this.description = str;
            }

            public String getIslink() {
                return this.islink;
            }

            public void setIslink(String str) {
                this.islink = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public String getNewstype() {
                return this.newstype;
            }

            public void setNewstype(String str) {
                this.newstype = str;
            }

            public String getNewsclass() {
                return this.newsclass;
            }

            public void setNewsclass(String str) {
                this.newsclass = str;
            }

            public String getNorb() {
                return this.norb;
            }

            public void setNorb(String str) {
                this.norb = str;
            }

            public String getOpentype() {
                return this.opentype;
            }

            public void setOpentype(String str) {
                this.opentype = str;
            }

            public String getPosterid() {
                return this.posterid;
            }

            public void setPosterid(String str) {
                this.posterid = str;
            }

            public String getPostername() {
                return this.postername;
            }

            public void setPostername(String str) {
                this.postername = str;
            }

            public String getPlsum() {
                return this.plsum;
            }

            public void setPlsum(String str) {
                this.plsum = str;
            }

            public String getDing() {
                return this.ding;
            }

            public void setDing(String str) {
                this.ding = str;
            }

            public String getVideourl() {
                return this.videourl;
            }

            public void setVideourl(String str) {
                this.videourl = str;
            }

            public long getInputtime() {
                return this.inputtime;
            }

            public void setInputtime(long j) {
                this.inputtime = j;
            }

            public String getThumb() {
                return this.thumb;
            }

            public void setThumb(String str) {
                this.thumb = str;
            }
        }

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
}
