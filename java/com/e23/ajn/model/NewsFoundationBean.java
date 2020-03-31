package com.e23.ajn.model;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import org.litepal.crud.DataSupport;

public class NewsFoundationBean extends DataSupport implements Serializable {
    public static final String BBS = "bbs";
    public static final String MALL = "mall";
    public static final String NEWS = "news";
    public static final int TYPE_BIG_PIC = 4;
    public static final int TYPE_BREAK_NEWS = 6;
    public static final int TYPE_CHILD_TOPIC = 7;
    public static final int TYPE_LOVEJINAN_PLUS = 8;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_ONE_PIC = 3;
    public static final int TYPE_PICS = 2;
    public static final int TYPE_THEMATIC_RECOMMENDATIONS = 5;
    public static final String VOTE = "vote";
    public static final String WAI_LIAN = "wailian";
    public static final String ZHI_BO = "zhibo";
    public static final String ZHUAN_TI = "zhuanti";
    public static final String ZTTJ = "zttj";
    protected String avatar;
    protected String catid;
    protected String catname;
    protected String copyfrom;
    protected String description;
    protected String ding;
    protected long inputtime;
    protected int isPraised;
    protected int islink;
    @c(a = "id")
    protected String newsId;
    protected String newsclass;
    protected int newstype;
    protected String norb;
    protected int opentype;
    protected String plsum;
    protected String posterid;
    protected String postername;
    protected String thumb;
    protected ArrayList<ThumbZu> thumbs;
    protected String title;
    protected String url;
    protected String videourl;
    protected String zu;

    public String getCopyfrom() {
        return this.copyfrom;
    }

    public void setCopyfrom(String str) {
        this.copyfrom = str;
    }

    public String getCatname() {
        return this.catname;
    }

    public void setCatname(String str) {
        this.catname = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getNewsId() {
        return this.newsId;
    }

    public void setNewsId(String str) {
        this.newsId = str;
    }

    public String getCatid() {
        return this.catid;
    }

    public void setCatid(String str) {
        this.catid = str;
    }

    public String getDing() {
        return this.ding;
    }

    public void setDing(String str) {
        this.ding = str;
    }

    public long getInputtime() {
        return this.inputtime;
    }

    public void setInputtime(long j) {
        this.inputtime = j;
    }

    public int getIslink() {
        return this.islink;
    }

    public void setIslink(int i) {
        this.islink = i;
    }

    public String getNewsclass() {
        return this.newsclass;
    }

    public void setNewsclass(String str) {
        this.newsclass = str;
    }

    public int getNewstype() {
        return this.newstype;
    }

    public void setNewstype(int i) {
        this.newstype = i;
    }

    public String getNorb() {
        return this.norb;
    }

    public void setNorb(String str) {
        this.norb = str;
    }

    public int getOpentype() {
        return this.opentype;
    }

    public void setOpentype(int i) {
        this.opentype = i;
    }

    public String getPlsum() {
        return this.plsum;
    }

    public void setPlsum(String str) {
        this.plsum = str;
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

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String str) {
        this.thumb = str;
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

    public String getVideourl() {
        return this.videourl;
    }

    public void setVideourl(String str) {
        this.videourl = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public ArrayList<ThumbZu> getThumbs() {
        return this.thumbs;
    }

    public void setThumbs(ArrayList<ThumbZu> arrayList) {
        this.thumbs = arrayList;
    }

    public String getZu() {
        return this.zu;
    }

    public void setZu(String str) {
        this.zu = str;
    }

    public int getIsPraised() {
        return this.isPraised;
    }

    public void setIsPraised(int i) {
        this.isPraised = i;
    }
}
