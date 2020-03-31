package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.d;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class i {
    private static i h = null;
    Map<Integer, h> a = null;
    d b = null;
    Map<Integer, h> c = null;
    boolean d = true;
    private Map<Integer, h> e = null;
    private Context f = null;
    private d g = c.b();
    private d i = null;

    private i(Context context) {
        this.f = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.e = new HashMap(3);
        this.e.put(Integer.valueOf(1), new g(context, 3));
        this.e.put(Integer.valueOf(2), new b(context, 3));
        this.e.put(Integer.valueOf(4), new f(context, 3));
    }

    public static synchronized i a(Context context) {
        i iVar;
        synchronized (i.class) {
            if (h == null) {
                h = new i(context);
            }
            iVar = h;
        }
        return iVar;
    }

    private Map<Integer, h> e() {
        if (this.a == null) {
            this.a = new HashMap(3);
            this.a.put(Integer.valueOf(1), new g(this.f, StorageInterface.VER_NEW));
            this.a.put(Integer.valueOf(2), new b(this.f, StorageInterface.VER_NEW));
            this.a.put(Integer.valueOf(4), new f(this.f, StorageInterface.VER_NEW));
        }
        return this.a;
    }

    public void a(d dVar) {
        a(dVar, true);
    }

    public void a(d dVar, boolean z) {
        if (dVar.b() <= 0) {
            dVar.b(System.currentTimeMillis());
        }
        a.c(Constants.LogTag, "writeNewVersionMidEntity midEntity:" + dVar);
        for (Entry entry : e().entrySet()) {
            a.c(Constants.LogTag, "writeMidEntity new ver:" + dVar);
            ((h) entry.getValue()).a(dVar);
        }
        if (z) {
            com.tencent.android.tpush.a.b(this.f, this.f.getPackageName(), dVar.toString());
        }
    }

    public void b(d dVar) {
        b(dVar, true);
    }

    public void b(d dVar, boolean z) {
        if (dVar.b() <= 0) {
            dVar.b(System.currentTimeMillis());
        }
        a.c(Constants.LogTag, "writeOldVersionMidEntity midEntity:" + dVar);
        for (Entry entry : f().entrySet()) {
            a.c(Constants.LogTag, "writeMidEntity old ver:" + dVar);
            ((h) entry.getValue()).a(dVar);
        }
        if (z) {
            com.tencent.android.tpush.a.c(this.f, this.f.getPackageName(), dVar.toString());
        }
    }

    public d a() {
        return a(4, e());
    }

    private Map<Integer, h> f() {
        if (this.c == null) {
            this.c = new HashMap(3);
            this.c.put(Integer.valueOf(1), new g(this.f, 3));
            this.c.put(Integer.valueOf(2), new b(this.f, 3));
            this.c.put(Integer.valueOf(4), new f(this.f, 3));
        }
        return this.c;
    }

    public d b() {
        if (!l.a(this.i)) {
            this.i = a();
            if (this.i == null || !this.i.c()) {
                this.i = d();
            }
        }
        if (!l.a(this.i)) {
            String d2 = com.tencent.android.tpush.a.d(this.f);
            if (c.a(d2)) {
                this.i = new d();
                this.i.b(d2);
            }
        }
        if (this.d) {
            this.g.h("firstRead");
            d d3 = d();
            if (d3 == null || !d3.c()) {
                c(this.i);
            }
            this.d = false;
        }
        return this.i != null ? this.i : new d();
    }

    public void c(d dVar) {
        h hVar = (h) this.e.get(Integer.valueOf(4));
        if (hVar != null) {
            hVar.a(dVar);
        }
    }

    public String c() {
        d a2 = a();
        if (a2 == null || !a2.c()) {
            a2 = d();
        }
        if (a2 == null || !a2.c()) {
            return "";
        }
        return a2.e();
    }

    public d d() {
        return a(4, this.e);
    }

    private d a(int i2, Map<Integer, h> map) {
        if (this.e != null) {
            h hVar = (h) map.get(Integer.valueOf(i2));
            if (hVar != null) {
                return hVar.g();
            }
        }
        return null;
    }

    public void d(d dVar) {
        Log.d(Constants.LogTag, "writeMidEntity:" + dVar);
        a(dVar);
        b(dVar);
    }
}
