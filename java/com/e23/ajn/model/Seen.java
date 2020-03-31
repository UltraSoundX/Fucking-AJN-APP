package com.e23.ajn.model;

import java.io.Serializable;
import org.litepal.crud.DataSupport;

public class Seen extends DataSupport implements Serializable {
    private static final long serialVersionUID = 625912318649845561L;
    private String newsId;

    public String getNewsId() {
        return this.newsId;
    }

    public void setNewsId(String str) {
        this.newsId = str;
    }
}
