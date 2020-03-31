package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class UnregInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static AppInfo cache_appInfo = new AppInfo();
    public AppInfo appInfo = null;
    public byte isUninstall = 0;
    public long timestamp = 0;

    static {
        boolean z;
        if (!UnregInfo.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.UnregInfo";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.UnregInfo";
    }

    public AppInfo getAppInfo() {
        return this.appInfo;
    }

    public void setAppInfo(AppInfo appInfo2) {
        this.appInfo = appInfo2;
    }

    public byte getIsUninstall() {
        return this.isUninstall;
    }

    public void setIsUninstall(byte b) {
        this.isUninstall = b;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public UnregInfo() {
    }

    public UnregInfo(AppInfo appInfo2, byte b, long j) {
        this.appInfo = appInfo2;
        this.isUninstall = b;
        this.timestamp = j;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        UnregInfo unregInfo = (UnregInfo) obj;
        if (!JceUtil.equals((Object) this.appInfo, (Object) unregInfo.appInfo) || !JceUtil.equals(this.isUninstall, unregInfo.isUninstall) || !JceUtil.equals(this.timestamp, unregInfo.timestamp)) {
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
        jceOutputStream.write((JceStruct) this.appInfo, 0);
        jceOutputStream.write(this.isUninstall, 1);
        jceOutputStream.write(this.timestamp, 2);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.appInfo = (AppInfo) jceInputStream.read((JceStruct) cache_appInfo, 0, true);
        this.isUninstall = jceInputStream.read(this.isUninstall, 1, true);
        this.timestamp = jceInputStream.read(this.timestamp, 2, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.appInfo, "appInfo");
        jceDisplayer.display(this.isUninstall, "isUninstall");
        jceDisplayer.display(this.timestamp, "timestamp");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple((JceStruct) this.appInfo, true);
        jceDisplayer.displaySimple(this.isUninstall, true);
        jceDisplayer.displaySimple(this.timestamp, false);
    }
}
