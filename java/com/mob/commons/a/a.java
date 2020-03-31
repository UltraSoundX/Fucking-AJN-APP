package com.mob.commons.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.FBListener;
import com.mob.commons.b;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: ActClt */
public class a extends d implements Callback {
    private SharePrefrenceHelper a;
    private FBListener b = null;
    /* access modifiers changed from: private */
    public long c = 0;
    private HashMap<Long, Long> d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public Handler g;

    a() {
        new Thread() {
            public void run() {
                a.this.a_();
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public void a_() {
        this.e = com.mob.commons.a.B();
        this.f = com.mob.commons.a.w();
        if (this.e || this.f) {
            this.b = new FBListener() {
                public void onFBChanged(boolean z, boolean z2, long j) {
                    if (z2) {
                        a.this.c = com.mob.commons.a.a();
                        if (a.this.e) {
                            a.this.g = MobHandlerThread.newHandler(a.this);
                            a.this.g.sendEmptyMessage(0);
                        }
                    }
                    if (z) {
                        if (!z2) {
                            a.this.c = com.mob.commons.a.a();
                            a.this.g.sendEmptyMessage(1);
                        }
                        if (a.this.f) {
                            a.this.i();
                        }
                    } else if (a.this.e && j > 0) {
                        a.this.k();
                        a.this.l();
                        a.this.g.removeMessages(1);
                    }
                }
            };
            j.a().a(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 0:
                l();
                this.g.sendEmptyMessage(1);
                return;
            case 1:
                k();
                this.g.sendEmptyMessageDelayed(1, com.mob.commons.a.C() * 1000);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.b != null) {
            j.a().b(this.b);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "PV");
            hashMap.put("datetime", Long.valueOf(com.mob.commons.a.a()));
            b.a().a(com.mob.commons.a.a(), hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private synchronized void j() {
        if (this.a == null) {
            this.a = new SharePrefrenceHelper(MobSDK.getContext());
            this.a.open("top_time");
        }
    }

    public HashMap<Long, Long> c() {
        HashMap<Long, Long> hashMap;
        try {
            j();
            hashMap = (HashMap) this.a.get("key_active_log");
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            hashMap = null;
        }
        if (hashMap == null) {
            return new HashMap<>();
        }
        return hashMap;
    }

    public void a(HashMap<Long, Long> hashMap) {
        j();
        if (hashMap == null || hashMap.isEmpty()) {
            this.a.remove("key_active_log");
        } else {
            this.a.put("key_active_log", hashMap);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void k() {
        try {
            if (this.d == null) {
                this.d = new HashMap<>();
            }
            long a2 = com.mob.commons.a.a();
            MobLog.getInstance().d("[cache] foregndAt: " + this.c + ", duration: " + (a2 - this.c), new Object[0]);
            this.d.put(Long.valueOf(this.c), Long.valueOf(a2));
            a(this.d);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized void l() {
        try {
            j();
            HashMap c2 = c();
            if (c2 != null && !c2.isEmpty()) {
                for (Entry entry : c2.entrySet()) {
                    long longValue = ((Long) entry.getKey()).longValue();
                    long longValue2 = ((Long) entry.getValue()).longValue();
                    long j = longValue2 - longValue;
                    MobLog.getInstance().d("foregndAt: " + longValue + ", until: " + longValue2 + ", runtimes: " + j, new Object[0]);
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "BACK_INFO");
                    hashMap.put("datetime", Long.valueOf(com.mob.commons.a.a()));
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("until", Long.valueOf(longValue2));
                    hashMap2.put("runtimes", Long.valueOf(j));
                    hashMap.put("data", hashMap2);
                    b.a().a(com.mob.commons.a.a(), hashMap);
                }
                if (this.d != null) {
                    this.d.clear();
                }
                a(null);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return;
    }
}
