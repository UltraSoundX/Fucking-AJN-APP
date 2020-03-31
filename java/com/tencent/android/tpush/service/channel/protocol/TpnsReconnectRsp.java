package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: ProGuard */
public final class TpnsReconnectRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled;
    static ArrayList<TpnsPushMsg> cache_appOfflinePushMsgList = new ArrayList<>();
    public ArrayList<TpnsPushMsg> appOfflinePushMsgList = null;
    public long confVersion = 0;
    public long timeUs = 0;

    static {
        boolean z;
        if (!TpnsReconnectRsp.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        cache_appOfflinePushMsgList.add(new TpnsPushMsg());
    }

    public String className() {
        return "TPNS_CLIENT_PROTOCOL.TpnsReconnectRsp";
    }

    public String fullClassName() {
        return "com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp";
    }

    public long getConfVersion() {
        return this.confVersion;
    }

    public void setConfVersion(long j) {
        this.confVersion = j;
    }

    public ArrayList<TpnsPushMsg> getAppOfflinePushMsgList() {
        return this.appOfflinePushMsgList;
    }

    public void setAppOfflinePushMsgList(ArrayList<TpnsPushMsg> arrayList) {
        this.appOfflinePushMsgList = arrayList;
    }

    public long getTimeUs() {
        return this.timeUs;
    }

    public void setTimeUs(long j) {
        this.timeUs = j;
    }

    public TpnsReconnectRsp() {
    }

    public TpnsReconnectRsp(long j, ArrayList<TpnsPushMsg> arrayList, long j2) {
        this.confVersion = j;
        this.appOfflinePushMsgList = arrayList;
        this.timeUs = j2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        TpnsReconnectRsp tpnsReconnectRsp = (TpnsReconnectRsp) obj;
        if (!JceUtil.equals(this.confVersion, tpnsReconnectRsp.confVersion) || !JceUtil.equals((Object) this.appOfflinePushMsgList, (Object) tpnsReconnectRsp.appOfflinePushMsgList) || !JceUtil.equals(this.timeUs, tpnsReconnectRsp.timeUs)) {
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
        jceOutputStream.write(this.confVersion, 0);
        if (this.appOfflinePushMsgList != null) {
            jceOutputStream.write((Collection<T>) this.appOfflinePushMsgList, 1);
        }
        jceOutputStream.write(this.timeUs, 2);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this.confVersion = jceInputStream.read(this.confVersion, 0, true);
        this.appOfflinePushMsgList = (ArrayList) jceInputStream.read(cache_appOfflinePushMsgList, 1, false);
        this.timeUs = jceInputStream.read(this.timeUs, 2, false);
    }

    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.confVersion, "confVersion");
        jceDisplayer.display((Collection<T>) this.appOfflinePushMsgList, "appOfflinePushMsgList");
        jceDisplayer.display(this.timeUs, "timeUs");
    }

    public void displaySimple(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.displaySimple(this.confVersion, true);
        jceDisplayer.displaySimple((Collection<T>) this.appOfflinePushMsgList, true);
        jceDisplayer.displaySimple(this.timeUs, false);
    }
}
