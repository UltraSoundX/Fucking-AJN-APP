package com.bumptech.glide;

import android.content.Context;
import android.os.Build.VERSION;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.b.a.d;
import com.bumptech.glide.d.b.a.f;
import com.bumptech.glide.d.b.b.a.C0039a;
import com.bumptech.glide.d.b.b.g;
import com.bumptech.glide.d.b.b.h;
import com.bumptech.glide.d.b.b.i;
import com.bumptech.glide.d.b.c;
import com.stub.StubApp;
import java.util.concurrent.ExecutorService;

/* compiled from: GlideBuilder */
public class j {
    private final Context a;
    private c b;
    private com.bumptech.glide.d.b.a.c c;
    private h d;
    private ExecutorService e;
    private ExecutorService f;
    private a g;
    private C0039a h;

    public j(Context context) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    /* access modifiers changed from: 0000 */
    public i a() {
        if (this.e == null) {
            this.e = new com.bumptech.glide.d.b.c.a(Math.max(1, Runtime.getRuntime().availableProcessors()));
        }
        if (this.f == null) {
            this.f = new com.bumptech.glide.d.b.c.a(1);
        }
        i iVar = new i(this.a);
        if (this.c == null) {
            if (VERSION.SDK_INT >= 11) {
                this.c = new f(iVar.b());
            } else {
                this.c = new d();
            }
        }
        if (this.d == null) {
            this.d = new g(iVar.a());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.d.b.b.f(this.a);
        }
        if (this.b == null) {
            this.b = new c(this.d, this.h, this.f, this.e);
        }
        if (this.g == null) {
            this.g = a.d;
        }
        return new i(this.b, this.d, this.c, this.a, this.g);
    }
}
