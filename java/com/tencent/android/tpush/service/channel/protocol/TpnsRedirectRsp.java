package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class TpnsRedirectRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TpnsRedirectRsp.class.desiredAssertionStatus());
    public long ip = 0;
    public int port = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsRedirectRsp";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp";
    }

    public long getIp() {
        return this.ip;
    }

    public void setIp(long j) {
        this.ip = j;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public TpnsRedirectRsp() {
    }

    public TpnsRedirectRsp(long j, int i) {
        this.ip = j;
        this.port = i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsRedirectRsp tpnsRedirectRsp = (TpnsRedirectRsp) obj;
        if (!JceUtil.equals(this.ip, tpnsRedirectRsp.ip) || !JceUtil.equals(this.port, tpnsRedirectRsp.port)) {
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
        jceOutputStream.write(this.ip, 0);
        jceOutputStream.write(this.port, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.ip = jceInputStream.read(this.ip, 0, false);
        this.port = jceInputStream.read(this.port, 1, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.ip, "ip");
        jceDisplayer.display(this.port, "port");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.ip, true);
        jceDisplayer.displaySimple(this.port, false);
    }
}
