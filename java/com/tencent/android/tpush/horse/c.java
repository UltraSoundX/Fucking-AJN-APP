package com.tencent.android.tpush.horse;

/* compiled from: ProGuard */
public class c extends a {
    private static c a;

    private c() {
    }

    public static synchronized c i() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public void e() {
        i().d().clear();
    }

    public void f() {
        i().a(-1);
    }
}
