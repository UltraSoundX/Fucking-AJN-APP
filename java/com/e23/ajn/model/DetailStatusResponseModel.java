package com.e23.ajn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailStatusResponseModel extends BaseResponse implements Serializable {
    private MoreBean more;
    private NewsBean news;

    public static class MoreBean {
        private String audiourl;
        private String catname;
        private String iscell;
        private String isding;
        private String pinglun;
        private String posteravatar;
        private String qianglou;
        private String sharedomain;
        private String username;
        private String views;
        private String writer;

        public String getSharedomain() {
            return this.sharedomain;
        }

        public void setSharedomain(String str) {
            this.sharedomain = str;
        }

        public String getPosteravatar() {
            return this.posteravatar;
        }

        public void setPosteravatar(String str) {
            this.posteravatar = str;
        }

        public String getCatname() {
            return this.catname;
        }

        public void setCatname(String str) {
            this.catname = str;
        }

        public String getPinglun() {
            return this.pinglun;
        }

        public void setPinglun(String str) {
            this.pinglun = str;
        }

        public String getQianglou() {
            return this.qianglou;
        }

        public void setQianglou(String str) {
            this.qianglou = str;
        }

        public String getViews() {
            return this.views;
        }

        public void setViews(String str) {
            this.views = str;
        }

        public String getWriter() {
            return this.writer;
        }

        public void setWriter(String str) {
            this.writer = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getAudiourl() {
            return this.audiourl;
        }

        public void setAudiourl(String str) {
            this.audiourl = str;
        }

        public String getIscell() {
            return this.iscell;
        }

        public void setIscell(String str) {
            this.iscell = str;
        }

        public String getIsding() {
            return this.isding;
        }

        public void setIsding(String str) {
            this.isding = str;
        }
    }

    public static class NewsBean {
        private List<AdvBean> adv;
        private String catid;
        private String content;
        private List<VideoBean> contentvideos;
        private String copyfrom;
        private String description;
        private String ding;
        private String id;
        private String inputtime;
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
        private List<String> videos;
        private String videourl;

        public static class AdvBean {
            private String href;
            private String thumb;

            public String getThumb() {
                return this.thumb;
            }

            public void setThumb(String str) {
                this.thumb = str;
            }

            public String getHref() {
                return this.href;
            }

            public void setHref(String str) {
                this.href = str;
            }
        }

        public static class VideoBean {
            private String poster;
            private String src;

            public String getSrc() {
                return this.src;
            }

            public void setSrc(String str) {
                this.src = str;
            }

            public String getPoster() {
                return this.poster;
            }

            public void setPoster(String str) {
                this.poster = str;
            }
        }

        public List<VideoBean> getContentvideos() {
            return this.contentvideos;
        }

        public void setContentvideos(List<VideoBean> list) {
            this.contentvideos = list;
        }

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

        public String getInputtime() {
            return this.inputtime;
        }

        public void setInputtime(String str) {
            this.inputtime = str;
        }

        public String getThumb() {
            return this.thumb;
        }

        public void setThumb(String str) {
            this.thumb = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getCopyfrom() {
            return this.copyfrom;
        }

        public void setCopyfrom(String str) {
            this.copyfrom = str;
        }

        public List<String> getVideos() {
            return this.videos;
        }

        public void setVideos(List<String> list) {
            this.videos = list;
        }

        public List<AdvBean> getAdv() {
            return this.adv;
        }

        public void setAdv(List<AdvBean> list) {
            this.adv = list;
        }
    }

    public NewsBean getNews() {
        return this.news;
    }

    public void setNews(NewsBean newsBean) {
        this.news = newsBean;
    }

    public MoreBean getMore() {
        return this.more;
    }

    public void setMore(MoreBean moreBean) {
        this.more = moreBean;
    }
}
