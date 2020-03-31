package com.chad.library.adapter.base.entity;

import java.io.Serializable;

public abstract class SectionMultiEntity<T> implements MultiItemEntity, Serializable {
    public String header;
    public boolean isHeader;
    public T t;

    public SectionMultiEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.t = null;
    }

    public SectionMultiEntity(T t2) {
        this.isHeader = false;
        this.header = null;
        this.t = t2;
    }
}
