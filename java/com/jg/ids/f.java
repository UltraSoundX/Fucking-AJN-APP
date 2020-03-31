package com.jg.ids;

import android.content.Context;
import android.text.TextUtils;

public abstract class f implements k {
    protected Context a = null;
    private String b = "";
    private String c = "";
    private String d = "";

    public f(Context context) {
        this.a = context;
        try {
            this.b = l.a().b();
            this.c = l.a().c();
            this.d = l.a().d();
        } catch (Throwable th) {
        }
    }

    public final void a(String str) {
        try {
            this.b = str;
            l.a().a(this.b);
        } catch (Throwable th) {
        }
    }

    public final void b(String str) {
        try {
            this.c = str;
            l.a().b(this.c);
        } catch (Throwable th) {
        }
    }

    public final void c(String str) {
        try {
            this.d = str;
            l.a().c(this.d);
        } catch (Throwable th) {
        }
    }

    public String a(Context context) {
        return this.c;
    }

    public String b(Context context) {
        return this.d;
    }

    public String c(Context context) {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final boolean c() {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean d(Context context) {
        return true;
    }
}
