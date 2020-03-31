package com.tencent.android.tpush.horse.data;

import com.baidu.mobstat.Config;
import com.tencent.android.tpush.service.e.i;
import java.io.Serializable;

/* compiled from: ProGuard */
public class ServerItem implements Serializable {
    private static final long serialVersionUID = -6609283086276910655L;
    private long serverIpLong;
    private String serverIpStr;
    private int serverPort;
    private int spType;

    public ServerItem(String str, int i, int i2) {
        this.serverIpStr = str;
        this.serverIpLong = i.c(this.serverIpStr);
        this.serverPort = i;
        this.spType = i2;
    }

    public ServerItem(long j, int i, int i2) {
        this.serverIpLong = j;
        this.serverIpStr = i.a(j);
        this.serverPort = i;
        this.spType = i2;
    }

    public String getServerIp() {
        return this.serverIpStr;
    }

    public void setServerIp(String str) {
        this.serverIpStr = str;
        this.serverIpLong = i.c(this.serverIpStr);
    }

    public void setServerIp(long j) {
        this.serverIpLong = j;
        this.serverIpStr = i.a(j);
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int i) {
        this.serverPort = i;
    }

    private boolean mEquals(ServerItem serverItem) {
        return serverItem != null && serverItem.getServerIp().equals(this.serverIpStr) && serverItem.getServerPort() == this.serverPort && serverItem.getSpType() == this.spType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ServerItem) {
            return mEquals((ServerItem) obj);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return ((((((this.serverIpStr != null ? this.serverIpStr.hashCode() : 0) + 31) * 31) + this.serverPort) * 31) + this.spType) & Integer.MAX_VALUE;
    }

    public String toString() {
        return this.serverIpStr + Config.TRACE_TODAY_VISIT_SPLIT + this.serverPort;
    }

    public int getSpType() {
        return this.spType;
    }

    public void setSpType(int i) {
        this.spType = i;
    }

    public String getUrl() {
        return "http://" + this.serverIpStr + Config.TRACE_TODAY_VISIT_SPLIT + this.serverPort;
    }

    public long getServerIpLong() {
        return this.serverIpLong;
    }
}
