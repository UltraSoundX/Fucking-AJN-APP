package com.e23.ajn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.litepal.crud.DataSupport;

public class CateListCache extends DataSupport implements Serializable {
    private List<CateBean> list = new ArrayList();
    private String updatetime;

    public List<CateBean> getList() {
        return this.list;
    }

    public void setList(List<CateBean> list2) {
        this.list = list2;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String str) {
        this.updatetime = str;
    }
}
