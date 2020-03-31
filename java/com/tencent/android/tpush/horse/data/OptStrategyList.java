package com.tencent.android.tpush.horse.data;

import android.text.format.Time;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class OptStrategyList implements Serializable {
    private static final long serialVersionUID = 4532907276158395575L;
    private StrategyItem httpItem;
    private StrategyItem httpRedirectItem;
    private StrategyItem tcpItem;
    private StrategyItem tcpRedirectItem;
    private long timestamp;

    public StrategyItem getTcpRedirectItem() {
        return this.tcpRedirectItem;
    }

    public void setTcpRedirectItem(StrategyItem strategyItem) {
        this.tcpRedirectItem = strategyItem;
    }

    public StrategyItem getTcpItem() {
        return this.tcpItem;
    }

    public void setTcpItem(StrategyItem strategyItem) {
        this.tcpItem = strategyItem;
    }

    public StrategyItem getHttpRedirectItem() {
        return this.httpRedirectItem;
    }

    public void setHttpRedirectItem(StrategyItem strategyItem) {
        this.httpRedirectItem = strategyItem;
    }

    public StrategyItem getHttpItem() {
        return this.httpItem;
    }

    public void setHttpItem(StrategyItem strategyItem) {
        this.httpItem = strategyItem;
    }

    public StrategyItem getOptStrategyItem() {
        if (this.tcpRedirectItem != null) {
            return this.tcpRedirectItem;
        }
        if (this.tcpItem != null) {
            return this.tcpItem;
        }
        if (this.httpRedirectItem != null) {
            return this.httpRedirectItem;
        }
        if (this.httpItem != null) {
            return this.httpItem;
        }
        throw new NullReturnException("getOptStrategyItem return null,because the optstragegylist is empty");
    }

    public List<StrategyItem> getAllStrategyItems() {
        ArrayList arrayList = new ArrayList();
        if (this.tcpRedirectItem != null) {
            arrayList.add(this.tcpRedirectItem);
        }
        if (this.tcpItem != null) {
            arrayList.add(this.tcpItem);
        }
        if (this.httpRedirectItem != null) {
            arrayList.add(this.httpRedirectItem);
        }
        if (this.httpItem != null) {
            arrayList.add(this.httpItem);
        }
        return arrayList;
    }

    public List<StrategyItem> getOptStrategyItems(short s) {
        ArrayList arrayList = new ArrayList();
        if (s == 1) {
            if (this.httpRedirectItem != null) {
                arrayList.add(this.httpRedirectItem);
            }
            if (this.httpItem != null) {
                arrayList.add(this.httpItem);
            }
        } else {
            if (this.tcpRedirectItem != null) {
                arrayList.add(this.tcpRedirectItem);
            }
            if (this.tcpItem != null) {
                arrayList.add(this.tcpItem);
            }
        }
        return arrayList;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------list start-----\n");
        sb.append("[TcpRedirectStrategyItem]:" + (this.tcpRedirectItem == null ? " tcpRedirect item is null" : this.tcpRedirectItem.toString()) + "\n");
        sb.append("[TCPStrategyItem]:" + (this.tcpItem == null ? " tcp item is null" : this.tcpItem.toString()) + "\n");
        sb.append("[HttpRedirectStrategyItem]:" + (this.httpRedirectItem == null ? " httpRedirect item is null" : this.httpRedirectItem.toString()) + "\n");
        sb.append("[HttpStrategyItem]:" + (this.httpItem == null ? " http item is null" : this.httpItem.toString()) + "\n");
        Time time = new Time();
        time.set(this.timestamp);
        sb.append(">>>saveTime=" + time.format2445() + ">>>\n");
        sb.append("------list end-----\n");
        return sb.toString();
    }
}
