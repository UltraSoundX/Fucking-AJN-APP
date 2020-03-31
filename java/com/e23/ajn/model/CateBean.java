package com.e23.ajn.model;

import java.io.Serializable;
import org.litepal.crud.DataSupport;

public class CateBean extends DataSupport implements Serializable {
    private String catid;
    private String catname;
    private CateListData data;
    private int fixed;
    private int ftype;
    private int index;
    private int select;

    public CateListData getData() {
        return this.data;
    }

    public void setData(CateListData cateListData) {
        this.data = cateListData;
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

    public int getFtype() {
        return this.ftype;
    }

    public void setFtype(int i) {
        this.ftype = i;
    }

    public int getSelect() {
        return this.select;
    }

    public void setSelect(int i) {
        this.select = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getFixed() {
        return this.fixed;
    }

    public void setFixed(int i) {
        this.fixed = i;
    }
}
