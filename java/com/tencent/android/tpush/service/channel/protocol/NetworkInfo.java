package com.tencent.android.tpush.service.channel.protocol;

import com.baidu.mobstat.Config;
import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

/* compiled from: ProGuard */
public final class NetworkInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkInfo.class.desiredAssertionStatus());
    public int ip = 0;
    public byte network = 0;
    public byte op = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.NetworkInfo";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.NetworkInfo";
    }

    public int getIp() {
        return this.ip;
    }

    public void setIp(int i) {
        this.ip = i;
    }

    public byte getNetwork() {
        return this.network;
    }

    public void setNetwork(byte b) {
        this.network = b;
    }

    public byte getOp() {
        return this.op;
    }

    public void setOp(byte b) {
        this.op = b;
    }

    public NetworkInfo() {
    }

    public NetworkInfo(int i, byte b, byte b2) {
        this.ip = i;
        this.network = b;
        this.op = b2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        NetworkInfo networkInfo = (NetworkInfo) obj;
        if (!JceUtil.equals(this.ip, networkInfo.ip) || !JceUtil.equals(this.network, networkInfo.network) || !JceUtil.equals(this.op, networkInfo.op)) {
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
        jceOutputStream.write(this.network, 1);
        jceOutputStream.write(this.op, 2);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.ip = jceInputStream.read(this.ip, 0, true);
        this.network = jceInputStream.read(this.network, 1, true);
        this.op = jceInputStream.read(this.op, 2, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.ip, "ip");
        jceDisplayer.display(this.network, "network");
        jceDisplayer.display(this.op, Config.OPERATOR);
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.ip, true);
        jceDisplayer.displaySimple(this.network, true);
        jceDisplayer.displaySimple(this.op, false);
    }
}
