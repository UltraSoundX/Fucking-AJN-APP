package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class TpnsTriggerReportRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TpnsTriggerReportRsp.class.desiredAssertionStatus());
    public byte padding = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsTriggerReportRsp";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportRsp";
    }

    public byte getPadding() {
        return this.padding;
    }

    public void setPadding(byte b) {
        this.padding = b;
    }

    public TpnsTriggerReportRsp() {
    }

    public TpnsTriggerReportRsp(byte b) {
        this.padding = b;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals(this.padding, ((TpnsTriggerReportRsp) obj).padding);
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
        jceOutputStream.write(this.padding, 0);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.padding = jceInputStream.read(this.padding, 0, true);
    }

    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.padding, "padding");
    }

    public void displaySimple(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).displaySimple(this.padding, false);
    }
}
