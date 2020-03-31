package com.tencent.open.b;

import com.tencent.open.d.d;

/* compiled from: ProGuard */
public class e {
    public static int a(String str) {
        if (d.a() == null) {
            return 100;
        }
        int a = com.tencent.open.d.e.a(d.a(), str).a("Common_BusinessReportFrequency");
        if (a != 0) {
            return a;
        }
        return 100;
    }

    public static int a() {
        int a = com.tencent.open.d.e.a(d.a(), (String) null).a("Common_HttpRetryCount");
        if (a == 0) {
            return 2;
        }
        return a;
    }
}
