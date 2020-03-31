package com.e23.ajn.d;

import com.b.a.e;

/* compiled from: GsonUtil */
public class l {
    private static e a;

    static {
        a = null;
        if (a == null) {
            a = new e();
        }
    }

    public static String a(Object obj) {
        if (a != null) {
            return a.a(obj);
        }
        return null;
    }
}
