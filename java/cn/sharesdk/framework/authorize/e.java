package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* compiled from: SSOProcessor */
public abstract class e {
    protected d a;
    protected int b;
    protected SSOListener c;

    public abstract void a();

    public e(d dVar) {
        this.a = dVar;
        this.c = dVar.a().getSSOListener();
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
    }
}
