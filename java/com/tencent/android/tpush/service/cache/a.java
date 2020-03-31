package com.tencent.android.tpush.service.cache;

import java.util.HashMap;

/* compiled from: ProGuard */
public class a {
    private static volatile HashMap<Object, Object> a = new HashMap<>(10);

    public static synchronized void a(Object obj, Object obj2) {
        synchronized (a.class) {
            a.put(obj, obj2);
        }
    }

    public static synchronized Object a(Object obj) {
        Object obj2;
        synchronized (a.class) {
            obj2 = a.get(obj);
        }
        return obj2;
    }
}
