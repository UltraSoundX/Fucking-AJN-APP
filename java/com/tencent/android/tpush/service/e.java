package com.tencent.android.tpush.service;

import android.os.PowerManager.WakeLock;

/* compiled from: ProGuard */
public class e {
    private static e a = null;
    private WakeLock b = null;

    private e() {
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public WakeLock b() {
        return this.b;
    }
}
