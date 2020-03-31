package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: ProGuard */
public final class TpnsClientReportReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static ArrayList<TpnsClientReport> cache_reportMsgs = new ArrayList<>();
    public ArrayList<TpnsClientReport> reportMsgs = null;

    static {
        boolean z;
        if (!TpnsClientReportReq.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        cache_reportMsgs.add(new TpnsClientReport());
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsClientReportReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq";
    }

    public ArrayList<TpnsClientReport> getReportMsgs() {
        return this.reportMsgs;
    }

    public void setReportMsgs(ArrayList<TpnsClientReport> arrayList) {
        this.reportMsgs = arrayList;
    }

    public TpnsClientReportReq() {
    }

    public TpnsClientReportReq(ArrayList<TpnsClientReport> arrayList) {
        this.reportMsgs = arrayList;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals((Object) this.reportMsgs, (Object) ((TpnsClientReportReq) obj).reportMsgs);
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
        if (this.reportMsgs != null) {
            jceOutputStream.write((Collection<T>) this.reportMsgs, 1);
        }
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.reportMsgs = (ArrayList) jceInputStream.read(cache_reportMsgs, 1, false);
    }

    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection<T>) this.reportMsgs, "reportMsgs");
    }

    public void displaySimple(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).displaySimple((Collection<T>) this.reportMsgs, false);
    }
}
