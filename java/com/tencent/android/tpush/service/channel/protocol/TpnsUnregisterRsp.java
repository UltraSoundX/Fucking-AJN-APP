package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class TpnsUnregisterRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TpnsUnregisterRsp.class.desiredAssertionStatus());
    public byte unregResult = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsUnregisterRsp";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterRsp";
    }

    public byte getUnregResult() {
        return this.unregResult;
    }

    public void setUnregResult(byte b) {
        this.unregResult = b;
    }

    public TpnsUnregisterRsp() {
    }

    public TpnsUnregisterRsp(byte b) {
        this.unregResult = b;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals(this.unregResult, ((TpnsUnregisterRsp) obj).unregResult);
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
        jceOutputStream.write(this.unregResult, 0);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.unregResult = jceInputStream.read(this.unregResult, 0, true);
    }

    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.unregResult, "unregResult");
    }

    public void displaySimple(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).displaySimple(this.unregResult, false);
    }
}
