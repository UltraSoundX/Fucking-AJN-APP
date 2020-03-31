package com.e23.ajn.model;

import java.io.Serializable;

public class UpdateModel implements Serializable {
    private static final long serialVersionUID = -6256718108153145564L;
    public String app_url;
    public String enforce_update;
    public String update_info;
    public String version_num;

    public String getVersion_num() {
        return this.version_num;
    }

    public void setVersion_num(String str) {
        this.version_num = str;
    }

    public String getApp_url() {
        return this.app_url;
    }

    public void setApp_url(String str) {
        this.app_url = str;
    }

    public String getEnforce_update() {
        return this.enforce_update;
    }

    public void setEnforce_update(String str) {
        this.enforce_update = str;
    }

    public String getUpdate_info() {
        return this.update_info;
    }

    public void setUpdate_info(String str) {
        this.update_info = str;
    }
}
