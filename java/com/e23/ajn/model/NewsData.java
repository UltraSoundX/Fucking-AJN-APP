package com.e23.ajn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.litepal.crud.DataSupport;

public class NewsData extends DataSupport implements Serializable {
    private static final long serialVersionUID = 62567768153125561L;
    private List<BroadcastBean> abobao = new ArrayList();
    private String catid;
    private int lastUpDate;
    private String lastid;
    private List<NewsBean> news = new ArrayList();
    private List<DistrictBean> qxdata = new ArrayList();
    private List<ThumbBean> thumb = new ArrayList();
    private List<ChildCatBean> topcats = new ArrayList();

    public List<ThumbBean> getThumb() {
        return this.thumb;
    }

    public void setThumb(List<ThumbBean> list) {
        this.thumb = list;
    }

    public List<NewsBean> getNews() {
        return this.news;
    }

    public void setNews(List<NewsBean> list) {
        this.news = list;
    }

    public String getCatid() {
        return this.catid;
    }

    public void setCatid(String str) {
        this.catid = str;
    }

    public List<BroadcastBean> getAbobao() {
        return this.abobao;
    }

    public void setAbobao(List<BroadcastBean> list) {
        this.abobao = list;
    }

    public int getLastUpDate() {
        return this.lastUpDate;
    }

    public void setLastUpDate(int i) {
        this.lastUpDate = i;
    }

    public String getLastid() {
        return this.lastid;
    }

    public void setLastid(String str) {
        this.lastid = str;
    }

    public List<DistrictBean> getQxdata() {
        return this.qxdata;
    }

    public void setQxdata(List<DistrictBean> list) {
        this.qxdata = list;
    }

    public List<ChildCatBean> getTopcats() {
        return this.topcats;
    }

    public void setTopcats(List<ChildCatBean> list) {
        this.topcats = list;
    }
}
