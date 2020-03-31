package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ProGuard */
public final class TpnsCheckMsgReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TpnsCheckMsgReq.class.desiredAssertionStatus());
    public long guid = 0;
    public String token = "";

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsCheckMsgReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsCheckMsgReq";
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public long getGuid() {
        return this.guid;
    }

    public void setGuid(long j) {
        this.guid = j;
    }

    public TpnsCheckMsgReq() {
    }

    public TpnsCheckMsgReq(String str, long j) {
        this.token = str;
        this.guid = j;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsCheckMsgReq tpnsCheckMsgReq = (TpnsCheckMsgReq) obj;
        if (!JceUtil.equals((Object) this.token, (Object) tpnsCheckMsgReq.token) || !JceUtil.equals(this.guid, tpnsCheckMsgReq.guid)) {
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
        jceOutputStream.write(this.token, 0);
        jceOutputStream.write(this.guid, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.token = jceInputStream.readString(0, true);
        this.guid = jceInputStream.read(this.guid, 1, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.token, Constants.FLAG_TOKEN);
        jceDisplayer.display(this.guid, "guid");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.token, true);
        jceDisplayer.displaySimple(this.guid, false);
    }
}
