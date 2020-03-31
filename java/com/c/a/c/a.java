package com.c.a.c;

import android.text.TextUtils;
import com.c.a.a.b;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: HttpCache */
public class a {
    private static long c = 60000;
    private static final ConcurrentHashMap<String, Boolean> d = new ConcurrentHashMap<>(10);
    private final b<String, String> a;
    private int b;

    static {
        d.put(com.c.a.c.b.b.a.GET.toString(), Boolean.valueOf(true));
    }

    public a() {
        this(102400, 60000);
    }

    public a(int i, long j) {
        this.b = 102400;
        this.b = i;
        c = j;
        this.a = new b<String, String>(this.b) {
            /* access modifiers changed from: protected */
            public int a(String str, String str2) {
                if (str2 == null) {
                    return 0;
                }
                return str2.length();
            }
        };
    }

    public static long a() {
        return c;
    }

    public void a(String str, String str2, long j) {
        if (str != null && str2 != null && j >= 1) {
            this.a.a(str, str2, System.currentTimeMillis() + j);
        }
    }

    public String a(String str) {
        if (str != null) {
            return (String) this.a.a(str);
        }
        return null;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Boolean bool = (Boolean) d.get(str.toUpperCase());
        return bool == null ? false : bool.booleanValue();
    }
}
