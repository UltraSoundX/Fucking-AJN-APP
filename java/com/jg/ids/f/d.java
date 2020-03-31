package com.jg.ids.f;

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
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        return intent;
    }

    /* access modifiers changed from: protected */
    public final void a(IBinder iBinder) {
        try {
            a a = b.a(iBinder);
            if (a != null) {
                String b = a.b(this.a.getPackageName());
                String a2 = a.a(this.a.getPackageName());
                String a3 = a.a();
                a(b);
                b(a2);
                c(a3);
            }
            b();
        } catch (Throwable th) {
        }
    }
}
