package com.tencent.android.tpush.data;

import java.io.Serializable;

/* compiled from: ProGuard */
public class MessageId implements Serializable {
    public static final short FLAG_ACK = 1;
    public static final short FLAG_UNACK = 0;
    private static final long serialVersionUID = 8708157897391765794L;
    public long accessId;
    public byte apn;
    public long busiMsgId = 0;
    public long channelId = -1;
    public String date = "";
    public String groupKeys = "";
    public long host;
    public long id;
    public short isAck;
    public byte isp;
    public long msgType = -1;
    public long multiPkg = 0;
    public byte pact;
    public String pkgName;
    public int port;
    public long pushTime;
    public long receivedTime;
    public long serverTime;
    public String serviceHost;
    public String statTag = "";
    public long timestamp = 0;
    public long ttl;

    public MessageId() {
    }

    public MessageId(long j, long j2, long j3, long j4, byte b, byte b2, byte b3, long j5, String str, String str2, int i, long j6, String str3, int i2, int i3) {
        this.id = j;
        this.accessId = j2;
        this.pushTime = j3;
        this.receivedTime = j4;
        this.isp = b;
        this.apn = b2;
        this.pact = b3;
        this.host = j5;
        this.serviceHost = str;
        this.pkgName = str2;
        this.msgType = (long) i;
        this.multiPkg = j6;
        this.date = str3;
        this.serverTime = (long) i2;
        this.ttl = (long) i3;
    }

    public boolean isMsgAcked() {
        return this.isAck == 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageId [id=").append(this.id).append(", isAck=").append(this.isAck).append(", isp=").append(this.isp).append(", apn=").append(this.apn).append(", accessId=").append(this.accessId).append(", pushTime=").append(this.pushTime).append(", receivedTime=").append(this.receivedTime).append(", pact=").append(this.pact).append(", host=").append(this.host).append(", port=").append(this.port).append(", serviceHost=").append(this.serviceHost).append(", pkgName=").append(this.pkgName).append(", busiMsgId=").append(this.busiMsgId).append(", timestamp=").append(this.timestamp).append(", msgType=").append(this.msgType).append(", multiPkg=").append(this.multiPkg).append(", date=").append(this.date).append(", serverTime=").append(this.serverTime).append(", ttl=").append(this.ttl).append("]");
        return sb.toString();
    }
}
