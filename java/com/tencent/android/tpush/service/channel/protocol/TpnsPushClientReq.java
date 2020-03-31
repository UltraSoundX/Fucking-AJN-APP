package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: ProGuard */
public final class TpnsPushClientReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static ArrayList<TpnsPushMsg> cache_msgList = new ArrayList<>();
    public ArrayList<TpnsPushMsg> msgList = null;
    public long timeUs = 0;

    static {
        boolean z;
        if (!TpnsPushClientReq.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        cache_msgList.add(new TpnsPushMsg());
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsPushClientReq";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq";
    }

    public ArrayList<TpnsPushMsg> getMsgList() {
        return this.msgList;
    }

    public void setMsgList(ArrayList<TpnsPushMsg> arrayList) {
        this.msgList = arrayList;
    }

    public long getTimeUs() {
        return this.timeUs;
    }

    public void setTimeUs(long j) {
        this.timeUs = j;
    }

    public TpnsPushClientReq() {
    }

    public TpnsPushClientReq(ArrayList<TpnsPushMsg> arrayList, long j) {
        this.msgList = arrayList;
        this.timeUs = j;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsPushClientReq tpnsPushClientReq = (TpnsPushClientReq) obj;
        if (!JceUtil.equals((Object) this.msgList, (Object) tpnsPushClientReq.msgList) || !JceUtil.equals(this.timeUs, tpnsPushClientReq.timeUs)) {
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
        jceOutputStream.write((Collection<T>) this.msgList, 0);
        jceOutputStream.write(this.timeUs, 1);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.msgList = (ArrayList) jceInputStream.read(cache_msgList, 0, true);
        this.timeUs = jceInputStream.read(this.timeUs, 1, true);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection<T>) this.msgList, "msgList");
        jceDisplayer.display(this.timeUs, "timeUs");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple((Collection<T>) this.msgList, true);
        jceDisplayer.displaySimple(this.timeUs, false);
    }
}
