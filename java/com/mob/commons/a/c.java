package com.mob.commons.a;

import android.os.Message;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.commons.g;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: AtClt */
public class c extends d {
    private long a;
    private HashMap<Long, Long> b;

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.aa() > 0;
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.at_lock");
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.a = a.a();
        this.b = g.t();
        b(1);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                if (a.aa() > 0) {
                    i();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void i() {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        for (Entry entry : this.b.entrySet()) {
            if (!(entry == null || ((Long) entry.getKey()).longValue() == this.a)) {
                j();
            }
        }
        long a2 = a.a() - this.a;
        this.b.put(Long.valueOf(this.a), Long.valueOf(a2));
        g.b(this.b);
        MobLog.getInstance().d("[%s] %s", "AtClt", "Cache AT: {\"launchAt: " + this.a + ", \"duration\": " + a2 + "}");
        long s = g.s();
        if (a2 >= a.ab() * 1000 && s <= a.a()) {
            j();
        }
        a(1, a.aa() * 1000);
    }

    private void j() {
        try {
            HashMap hashMap = new HashMap();
            for (Entry entry : this.b.entrySet()) {
                if (entry != null) {
                    hashMap.put("launchAt", entry.getKey());
                    hashMap.put("duration", entry.getValue());
                }
            }
            long a2 = a.a();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "APP_RUNTIMES_STATS");
            hashMap2.put("data", hashMap);
            hashMap2.put("datetime", Long.valueOf(a2));
            b.a().a(a2, hashMap2);
            MobLog.getInstance().d("[%s] %s", "AtClt", "Push AT: " + new Hashon().fromHashMap(hashMap));
            g.h((a.ab() * 1000) + a2);
            if (this.b != null) {
                this.b.clear();
            }
            g.b(null);
        } catch (Throwable th) {
            MobLog.getInstance().w(th, "[%s] %s", "AtClt", "Push error");
        }
    }
}
