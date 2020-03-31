package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class AppInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppInfo.class.desiredAssertionStatus());
    public long accessId = 0;
    public String accessKey = "";
    public String appCert = "";
    public byte keyEncrypted = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.AppInfo";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.AppInfo";
    }

    public long getAccessId() {
        return this.accessId;
    }

    public void setAccessId(long j) {
        this.accessId = j;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(String str) {
        this.accessKey = str;
    }

    public String getAppCert() {
        return this.appCert;
    }

    public void setAppCert(String str) {
        this.appCert = str;
    }

    public byte getKeyEncrypted() {
        return this.keyEncrypted;
    }

    public void setKeyEncrypted(byte b) {
        this.keyEncrypted = b;
    }

    public AppInfo() {
    }

    public AppInfo(long j, String str, String str2, byte b) {
        this.accessId = j;
        this.accessKey = str;
        this.appCert = str2;
        this.keyEncrypted = b;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AppInfo appInfo = (AppInfo) obj;
        if (!JceUtil.equals(this.accessId, appInfo.accessId) || !JceUtil.equals((Object) this.accessKey, (Object) appInfo.accessKey) || !JceUtil.equals((Object) this.appCert, (Object) appInfo.appCert) || !JceUtil.equals(this.keyEncrypted, appInfo.keyEncrypted)) {
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
        jceOutputStream.write(this.accessId, 0);
        if (this.accessKey == null) {
            this.accessKey = "";
        }
        jceOutputStream.write(this.accessKey, 1);
        jceOutputStream.write(this.appCert, 2);
        jceOutputStream.write(this.keyEncrypted, 3);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.accessId = jceInputStream.read(this.accessId, 0, true);
        this.accessKey = jceInputStream.readString(1, true);
        this.appCert = jceInputStream.readString(2, true);
        this.keyEncrypted = jceInputStream.read(this.keyEncrypted, 3, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.accessId, "accessId");
        jceDisplayer.display(this.accessKey, "accessKey");
        jceDisplayer.display(this.appCert, "appCert");
        jceDisplayer.display(this.keyEncrypted, "keyEncrypted");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.accessId, true);
        jceDisplayer.displaySimple(this.accessKey, true);
        jceDisplayer.displaySimple(this.appCert, true);
        jceDisplayer.displaySimple(this.keyEncrypted, false);
    }
}
