package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.mid.api.MidEntity;

/* compiled from: ProGuard */
public final class MutableInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!MutableInfo.class.desiredAssertionStatus());
    public String bssid = "";
    public String mac = "";
    public String ssid = "";
    public String wflist = "";

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.MutableInfo";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.MutableInfo";
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getWflist() {
        return this.wflist;
    }

    public void setWflist(String str) {
        this.wflist = str;
    }

    public MutableInfo() {
    }

    public MutableInfo(String str, String str2, String str3, String str4) {
        this.ssid = str;
        this.bssid = str2;
        this.mac = str3;
        this.wflist = str4;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        MutableInfo mutableInfo = (MutableInfo) obj;
        if (!JceUtil.equals((Object) this.ssid, (Object) mutableInfo.ssid) || !JceUtil.equals((Object) this.bssid, (Object) mutableInfo.bssid) || !JceUtil.equals((Object) this.mac, (Object) mutableInfo.mac) || !JceUtil.equals((Object) this.wflist, (Object) mutableInfo.wflist)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        boolean z = false;
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            if ($assertionsDisabled) {
                return z;
            }
            throw new AssertionError();
        }
    }

    public void writeTo(JceOutputStream jceOutputStream) {
        if (this.ssid != null) {
            jceOutputStream.write(this.ssid, 0);
        }
        if (this.bssid != null) {
            jceOutputStream.write(this.bssid, 1);
        }
        if (this.mac != null) {
            jceOutputStream.write(this.mac, 2);
        }
        if (this.wflist != null) {
            jceOutputStream.write(this.wflist, 3);
        }
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.ssid = jceInputStream.readString(0, false);
        this.bssid = jceInputStream.readString(1, false);
        this.mac = jceInputStream.readString(2, false);
        this.wflist = jceInputStream.readString(3, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.ssid, "ssid");
        jceDisplayer.display(this.bssid, "bssid");
        jceDisplayer.display(this.mac, MidEntity.TAG_MAC);
        jceDisplayer.display(this.wflist, "wflist");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.ssid, true);
        jceDisplayer.displaySimple(this.bssid, true);
        jceDisplayer.displaySimple(this.mac, true);
        jceDisplayer.displaySimple(this.wflist, false);
    }
}
