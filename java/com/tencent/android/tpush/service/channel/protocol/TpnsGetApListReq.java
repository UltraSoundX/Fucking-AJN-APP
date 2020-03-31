package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class TpnsGetApListReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static NetworkInfo cache_netInfo = new NetworkInfo();
    public NetworkInfo netInfo = null;

    static {
        boolean z;
        if (!TpnsGetApListReq.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsGetApListReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq";
    }

    public NetworkInfo getNetInfo() {
        return this.netInfo;
    }

    public void setNetInfo(NetworkInfo networkInfo) {
        this.netInfo = networkInfo;
    }

    public TpnsGetApListReq() {
    }

    public TpnsGetApListReq(NetworkInfo networkInfo) {
        this.netInfo = networkInfo;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals((Object) this.netInfo, (Object) ((TpnsGetApListReq) obj).netInfo);
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
        jceOutputStream.write((JceStruct) this.netInfo, 0);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.netInfo = (NetworkInfo) jceInputStream.read((JceStruct) cache_netInfo, 0, true);
    }

    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((JceStruct) this.netInfo, "netInfo");
    }

    public void displaySimple(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).displaySimple((JceStruct) this.netInfo, false);
    }
}
