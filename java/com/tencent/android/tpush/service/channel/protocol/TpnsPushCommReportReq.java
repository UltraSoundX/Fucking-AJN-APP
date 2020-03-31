package com.tencent.android.tpush.service.channel.protocol;

import android.support.v4.app.NotificationCompat;
import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.android.tpush.common.MessageKey;

/* compiled from: ProGuard */
public final class TpnsPushCommReportReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TpnsPushCommReportReq.class.desiredAssertionStatus());
    public long accessId = 0;
    public long broadcastId = 0;
    public long clientTimestamp = 0;
    public String ext = "";
    public String msg = "";
    public long msgId = 0;
    public long msgTimestamp = 0;
    public String pkgName = "";
    public long type = 0;

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsPushCommReportReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsPushCommReportReq";
    }

    public long getType() {
        return this.type;
    }

    public void setType(long j) {
        this.type = j;
    }

    public long getAccessId() {
        return this.accessId;
    }

    public void setAccessId(long j) {
        this.accessId = j;
    }

    public long getMsgId() {
        return this.msgId;
    }

    public void setMsgId(long j) {
        this.msgId = j;
    }

    public long getBroadcastId() {
        return this.broadcastId;
    }

    public void setBroadcastId(long j) {
        this.broadcastId = j;
    }

    public long getMsgTimestamp() {
        return this.msgTimestamp;
    }

    public void setMsgTimestamp(long j) {
        this.msgTimestamp = j;
    }

    public long getClientTimestamp() {
        return this.clientTimestamp;
    }

    public void setClientTimestamp(long j) {
        this.clientTimestamp = j;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String str) {
        this.ext = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public TpnsPushCommReportReq() {
    }

    public TpnsPushCommReportReq(long j, long j2, long j3, long j4, long j5, long j6, String str, String str2, String str3) {
        this.type = j;
        this.accessId = j2;
        this.msgId = j3;
        this.broadcastId = j4;
        this.msgTimestamp = j5;
        this.clientTimestamp = j6;
        this.msg = str;
        this.ext = str2;
        this.pkgName = str3;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsPushCommReportReq tpnsPushCommReportReq = (TpnsPushCommReportReq) obj;
        if (!JceUtil.equals(this.type, tpnsPushCommReportReq.type) || !JceUtil.equals(this.accessId, tpnsPushCommReportReq.accessId) || !JceUtil.equals(this.msgId, tpnsPushCommReportReq.msgId) || !JceUtil.equals(this.broadcastId, tpnsPushCommReportReq.broadcastId) || !JceUtil.equals(this.msgTimestamp, tpnsPushCommReportReq.msgTimestamp) || !JceUtil.equals(this.clientTimestamp, tpnsPushCommReportReq.clientTimestamp) || !JceUtil.equals((Object) this.msg, (Object) tpnsPushCommReportReq.msg) || !JceUtil.equals((Object) this.ext, (Object) tpnsPushCommReportReq.ext) || !JceUtil.equals((Object) this.pkgName, (Object) tpnsPushCommReportReq.pkgName)) {
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
        jceOutputStream.write(this.type, 0);
        jceOutputStream.write(this.accessId, 1);
        jceOutputStream.write(this.msgId, 2);
        jceOutputStream.write(this.broadcastId, 3);
        jceOutputStream.write(this.msgTimestamp, 4);
        jceOutputStream.write(this.clientTimestamp, 5);
        if (this.msg != null) {
            jceOutputStream.write(this.msg, 6);
        }
        if (this.ext != null) {
            jceOutputStream.write(this.ext, 7);
        }
        if (this.pkgName != null) {
            jceOutputStream.write(this.pkgName, 8);
        }
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.type = jceInputStream.read(this.type, 0, true);
        this.accessId = jceInputStream.read(this.accessId, 1, false);
        this.msgId = jceInputStream.read(this.msgId, 2, false);
        this.broadcastId = jceInputStream.read(this.broadcastId, 3, false);
        this.msgTimestamp = jceInputStream.read(this.msgTimestamp, 4, false);
        this.clientTimestamp = jceInputStream.read(this.clientTimestamp, 5, false);
        this.msg = jceInputStream.readString(6, false);
        this.ext = jceInputStream.readString(7, false);
        this.pkgName = jceInputStream.readString(8, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.type, "type");
        jceDisplayer.display(this.accessId, "accessId");
        jceDisplayer.display(this.msgId, MessageKey.MSG_ID);
        jceDisplayer.display(this.broadcastId, "broadcastId");
        jceDisplayer.display(this.msgTimestamp, "msgTimestamp");
        jceDisplayer.display(this.clientTimestamp, "clientTimestamp");
        jceDisplayer.display(this.msg, NotificationCompat.CATEGORY_MESSAGE);
        jceDisplayer.display(this.ext, "ext");
        jceDisplayer.display(this.pkgName, "pkgName");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.type, true);
        jceDisplayer.displaySimple(this.accessId, true);
        jceDisplayer.displaySimple(this.msgId, true);
        jceDisplayer.displaySimple(this.broadcastId, true);
        jceDisplayer.displaySimple(this.msgTimestamp, true);
        jceDisplayer.displaySimple(this.clientTimestamp, true);
        jceDisplayer.displaySimple(this.msg, true);
        jceDisplayer.displaySimple(this.ext, true);
        jceDisplayer.displaySimple(this.pkgName, false);
    }
}
