package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.service.a.a;

/* compiled from: ProGuard */
public class b {
    public static boolean a(long j) {
        if (j != 0 && (a() * 1000 * 60) + j > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public static long a() {
        return (long) a.a(com.tencent.android.tpush.service.b.f()).f430q;
    }

    public static int b() {
        return a.a(com.tencent.android.tpush.service.b.f()).o;
    }

    public static int c() {
        return a.a(com.tencent.android.tpush.service.b.f()).p;
    }
}
