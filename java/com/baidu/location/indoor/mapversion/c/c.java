package com.baidu.location.indoor.mapversion.c;

import android.content.Context;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class c {
    private static c a = null;
    private a b = null;
    private String c = null;
    private boolean d = false;
    private boolean e = false;
    private String f = "slr";
    private String g = "";
    private double h = 7.0d;
    private Map<String, b> i = Collections.synchronizedMap(new HashMap());

    public interface a {
    }

    public static class b implements Serializable {
        public String a;
        public String b;
        public double c;
        public double d;
        public double e;
        public double f;
        public String g;
    }

    private c(Context context) {
        this.f = new File(context.getCacheDir(), this.f).getAbsolutePath();
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            cVar = a;
        }
        return cVar;
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context);
        }
        return a;
    }

    public boolean b() {
        return this.e;
    }

    public boolean c() {
        return this.g.equals("on");
    }

    public Map<String, b> d() {
        return this.i;
    }
}
