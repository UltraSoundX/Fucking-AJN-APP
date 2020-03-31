package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class AppServerAuthInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!AppServerAuthInfo.class.desiredAssertionStatus());
    public long accessId = 0;
    public String secretKey = "";

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.AppServerAuthInfo";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.AppServerAuthInfo";
    }

    public long getAccessId() {
        return this.accessId;
    }

    public void setAccessId(long j) {
        this.accessId = j;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public AppServerAuthInfo() {
    }

    public AppServerAuthInfo(long j, String str) {
        this.accessId = j;
        this.secretKey = str;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AppServerAuthInfo appServerAuthInfo = (AppServerAuthInfo) obj;
        if (!JceUtil.equals(this.accessId, appServerAuthInfo.accessId) || !JceUtil.equals((Object) this.secretKey, (Object) appServerAuthInfo.secretKey)) {
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
        jceOutputStream.write(this.secretKey, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.accessId = jceInputStream.read(this.accessId, 0, true);
        this.secretKey = jceInputStream.readString(1, true);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.accessId, "accessId");
        jceDisplayer.display(this.secretKey, "secretKey");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.accessId, true);
        jceDisplayer.displaySimple(this.secretKey, false);
    }
}
