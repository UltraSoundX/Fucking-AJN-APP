package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: ProGuard */
public final class TpnsPushVerifyReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static ArrayList<TpnsPushClientReport> cache_msgReportList = new ArrayList<>();
    public ArrayList<TpnsPushClientReport> msgReportList = null;

    static {
        boolean z;
        if (!TpnsPushVerifyReq.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        cache_msgReportList.add(new TpnsPushClientReport());
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsPushVerifyReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq";
    }

    public ArrayList<TpnsPushClientReport> getMsgReportList() {
        return this.msgReportList;
    }

    public void setMsgReportList(ArrayList<TpnsPushClientReport> arrayList) {
        this.msgReportList = arrayList;
    }

    public TpnsPushVerifyReq() {
    }

    public TpnsPushVerifyReq(ArrayList<TpnsPushClientReport> arrayList) {
        this.msgReportList = arrayList;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals((Object) this.msgReportList, (Object) ((TpnsPushVerifyReq) obj).msgReportList);
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
        jceOutputStream.write((Collection<T>) this.msgReportList, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.msgReportList = (ArrayList) jceInputStream.read(cache_msgReportList, 1, true);
    }

    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection<T>) this.msgReportList, "msgReportList");
    }

    public void displaySimple(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).displaySimple((Collection<T>) this.msgReportList, false);
    }
}
