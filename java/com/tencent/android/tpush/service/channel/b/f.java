package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

/* compiled from: ProGuard */
public class f implements c {
    private long a = Long.MAX_VALUE;
    private boolean b = false;
    protected TpnsSecurity j;

    public void a(TpnsSecurity tpnsSecurity) {
        this.j = tpnsSecurity;
    }

    public void c() {
        if (this.a == Long.MAX_VALUE) {
            this.a = System.currentTimeMillis();
        }
    }

    public long a() {
        long currentTimeMillis = (this.a + ((long) a.a(b.f()).b)) - System.currentTimeMillis();
        if (currentTimeMillis < 0) {
            return 0;
        }
        return currentTimeMillis;
    }

    public synchronized void d() {
        this.b = true;
    }

    public synchronized boolean b() {
        return this.b;
    }
}
