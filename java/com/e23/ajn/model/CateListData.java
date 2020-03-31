package com.e23.ajn.model;

import java.io.Serializable;
import java.util.List;

public class CateListData implements Serializable {
    private List<CateBean> addlist;
    private List<CateBean> dellist;
    private List<CateBean> list;
    private String updatetime;
    private List<CateBean> uplist;

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String str) {
        this.updatetime = str;
    }

    public List<CateBean> getAddlist() {
        return this.addlist;
    }

    public void setAddlist(List<CateBean> list2) {
        this.addlist = list2;
    }

    public List<CateBean> getDellist() {
        return this.dellist;
    }

    public void setDellist(List<CateBean> list2) {
        this.dellist = list2;
    }

    public List<CateBean> getList() {
        return this.list;
    }

    public void setList(List<CateBean> list2) {
        this.list = list2;
    }

    public List<CateBean> getUplist() {
        return this.uplist;
    }

    public void setUplist(List<CateBean> list2) {
        this.uplist = list2;
    }
}
