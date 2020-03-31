package com.tencent.android.tpush.horse;

/* compiled from: ProGuard */
public class g extends a {
    private static g a;

    private g() {
    }

    public static synchronized g i() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    public void e() {
        c.i().d().clear();
    }

    public void f() {
        c.i().a(-1);
        c.i().a();
    }
}
