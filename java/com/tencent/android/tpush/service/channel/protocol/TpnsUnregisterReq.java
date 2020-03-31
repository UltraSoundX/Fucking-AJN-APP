package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class TpnsUnregisterReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static UnregInfo cache_unregInfo = new UnregInfo();
    public short deviceType = 0;
    public UnregInfo unregInfo = null;

    static {
        boolean z;
        if (!TpnsUnregisterReq.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsUnregisterReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq";
    }

    public UnregInfo getUnregInfo() {
        return this.unregInfo;
    }

    public void setUnregInfo(UnregInfo unregInfo2) {
        this.unregInfo = unregInfo2;
    }

    public short getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(short s) {
        this.deviceType = s;
    }

    public TpnsUnregisterReq() {
    }

    public TpnsUnregisterReq(UnregInfo unregInfo2, short s) {
        this.unregInfo = unregInfo2;
        this.deviceType = s;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsUnregisterReq tpnsUnregisterReq = (TpnsUnregisterReq) obj;
        if (!JceUtil.equals((Object) this.unregInfo, (Object) tpnsUnregisterReq.unregInfo) || !JceUtil.equals(this.deviceType, tpnsUnregisterReq.deviceType)) {
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
        jceOutputStream.write((JceStruct) this.unregInfo, 0);
        jceOutputStream.write(this.deviceType, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.unregInfo = (UnregInfo) jceInputStream.read((JceStruct) cache_unregInfo, 0, true);
        this.deviceType = jceInputStream.read(this.deviceType, 1, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.unregInfo, "unregInfo");
        jceDisplayer.display(this.deviceType, "deviceType");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple((JceStruct) this.unregInfo, true);
        jceDisplayer.displaySimple(this.deviceType, false);
    }
}
