package com.jg.ids.c;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.jg.ids.g;

public final class d extends g {
    public d(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final Intent a() {
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        return intent;
    }

    /* access modifiers changed from: protected */
    public final void a(IBinder iBinder) {
        try {
            a a = b.a(iBinder);
            if (a != null) {
                String a2 = a.a(this.a.getPackageName());
                String b = a.b(this.a.getPackageName());
                String a3 = a.a();
                b(a2);
                a(b);
                c(a3);
            }
            b();
        } catch (Throwable th) {
        }
    }
}
