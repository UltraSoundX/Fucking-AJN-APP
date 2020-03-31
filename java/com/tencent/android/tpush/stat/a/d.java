package com.tencent.android.tpush.stat.a;

import android.util.Log;
import com.baidu.mobstat.Config;

/* compiled from: ProGuard */
public final class d {
    private String a = "default";
    private boolean b = true;
    private int c = 2;

    public boolean a() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public d() {
    }

    public d(String str) {
        this.a = str;
    }

    private String b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + Config.TRACE_TODAY_VISIT_SPLIT + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    public void a(Object obj) {
        if (this.c <= 4) {
            String b2 = b();
            Log.i(this.a, b2 == null ? obj.toString() : b2 + " - " + obj);
        }
    }

    public void b(Object obj) {
        if (a()) {
            a(obj);
        }
    }

    public void c(Object obj) {
        if (this.c <= 5) {
            String b2 = b();
            Log.w(this.a, b2 == null ? obj.toString() : b2 + " - " + obj);
        }
    }

    public void d(Object obj) {
        if (a()) {
            c(obj);
        }
    }

    public void e(Object obj) {
        if (this.c <= 6) {
            String b2 = b();
            Log.e(this.a, b2 == null ? obj.toString() : b2 + " - " + obj);
        }
    }

    public void a(Throwable th) {
        if (this.c <= 6) {
            Log.e(this.a, "", th);
        }
    }

    public void f(Object obj) {
        if (a()) {
            e(obj);
        }
    }

    public void b(Throwable th) {
        if (a()) {
            a(th);
        }
    }

    public void g(Object obj) {
        if (this.c <= 3) {
            String b2 = b();
            Log.d(this.a, b2 == null ? obj.toString() : b2 + " - " + obj);
        }
    }

    public void h(Object obj) {
        if (a()) {
            g(obj);
        }
    }
}
