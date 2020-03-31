package com.e23.ajn.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.io.Serializable;

public class MessageBean implements MultiItemEntity, Serializable {
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_RIGHT = 0;
    private String content;
    private String del_type;
    private String iscom;
    private String isnew;
    private String message_time;
    private String messageid;
    private String send_from_avatar;
    private String send_from_id;
    private String send_from_uid;
    private String send_to_avatar;
    private String send_to_id;
    private String send_to_uid;
    private String status;
    private String subject;

    public String getSend_from_uid() {
        return this.send_from_uid;
    }

    public void setSend_from_uid(String str) {
        this.send_from_uid = str;
    }

    public String getSend_to_uid() {
        return this.send_to_uid;
    }

    public void setSend_to_uid(String str) {
        this.send_to_uid = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getIscom() {
        return this.iscom;
    }

    public void setIscom(String str) {
        this.iscom = str;
    }

    public String getSend_from_avatar() {
        return this.send_from_avatar;
    }

    public void setSend_from_avatar(String str) {
        this.send_from_avatar = str;
    }

    public String getSend_to_avatar() {
        return this.send_to_avatar;
    }

    public void setSend_to_avatar(String str) {
        this.send_to_avatar = str;
    }

    public String getIsnew() {
        return this.isnew;
    }

    public void setIsnew(String str) {
        this.isnew = str;
    }

    public String getMessageid() {
        return this.messageid;
    }

    public void setMessageid(String str) {
        this.messageid = str;
    }

    public String getSend_from_id() {
        return this.send_from_id;
    }

    public void setSend_from_id(String str) {
        this.send_from_id = str;
    }

    public String getSend_to_id() {
        return this.send_to_id;
    }

    public void setSend_to_id(String str) {
        this.send_to_id = str;
    }

    public String getMessage_time() {
        return this.message_time;
    }

    public void setMessage_time(String str) {
        this.message_time = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getDel_type() {
        return this.del_type;
    }

    public void setDel_type(String str) {
        this.del_type = str;
    }

    public int getItemType() {
        return Integer.parseInt(getIscom());
    }
}
