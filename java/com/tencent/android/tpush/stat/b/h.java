package com.tencent.android.tpush.stat.b;

import android.content.Context;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.d;
import com.tencent.android.tpush.stat.a.f;

/* compiled from: ProGuard */
public abstract class h {
    protected d a = c.b();
    protected Context b = null;
    protected int c = 0;

    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public abstract boolean b();

    /* access modifiers changed from: protected */
    public abstract String c();

    public String d() {
        if (this.c == 0) {
            return f.a("6X8Y4XdM2Vhvn0I=");
        }
        return f.a("6X8Y4XdM2Vhvn0I=") + this.c;
    }

    public String e() {
        if (this.c == 0) {
            return f.a("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=");
        }
        return f.a("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=") + this.c;
    }

    /* access modifiers changed from: protected */
    public String f() {
        if (this.c == 0) {
            return f.a("4kU71lN96TJUomD1vOU9lgj9Tw==");
        }
        return f.a("4kU71lN96TJUomD1vOU9lgj9Tw==") + this.c;
    }

    protected h(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    private String h() {
        if (b()) {
            return d(c());
        }
        return null;
    }

    public d g() {
        String h = h();
        if (h != null) {
            return d.a(h);
        }
        return null;
    }

    private void b(String str) {
        if (b()) {
            a(c(str));
        }
    }

    public void a(d dVar) {
        if (dVar != null) {
            if (a() == 4) {
                e.a(this.b).a(dVar.e());
            }
            b(dVar.toString());
        }
    }

    /* access modifiers changed from: protected */
    public String c(String str) {
        return f.b(str);
    }

    /* access modifiers changed from: protected */
    public String d(String str) {
        return f.a(str);
    }
}
