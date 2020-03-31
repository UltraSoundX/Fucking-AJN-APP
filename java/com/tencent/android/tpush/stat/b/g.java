package com.tencent.android.tpush.stat.b;

import android.content.Context;
import com.tencent.android.tpush.service.channel.c.d;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.mid.core.Constants;

/* compiled from: ProGuard */
public class g extends h {
    public g(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return f.a(this.b, Constants.PERMISSION_WRITE_SETTINGS);
    }

    /* access modifiers changed from: protected */
    public String c() {
        String a;
        synchronized (this) {
            a = d.a(this.b).a(f());
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        synchronized (this) {
            this.a.b((Object) "write mid to Settings.System");
            d.a(this.b).a(f(), str);
        }
    }
}
