package com.tencent.android.tpush.service.channel;

import android.util.SparseArray;

/* compiled from: ProGuard */
public class a {
    private static a b = new a();
    private SparseArray<Object> a = new SparseArray<>();

    public static a a() {
        return b;
    }

    public a() {
    }

    public a(Object... objArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < objArr.length) {
                this.a.put(objArr[i2].intValue(), objArr[i2 + 1]);
                i = i2 + 2;
            } else {
                return;
            }
        }
    }

    public void a(int i, Object obj) {
        this.a.put(i, obj);
    }

    public boolean b() {
        return ((Boolean) this.a.get(2, Boolean.valueOf(false))).booleanValue();
    }

    public long c() {
        return ((Long) this.a.get(3, Long.valueOf(0))).longValue();
    }

    public String d() {
        return (String) this.a.get(0, "");
    }

    public int e() {
        return ((Integer) this.a.get(1, Integer.valueOf(0))).intValue();
    }
}
