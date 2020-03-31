package com.jg.ids.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.jg.ids.g;

public final class a extends g {
    public a(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final Intent a() {
        Intent intent = new Intent("com.asus.msa.action.ACCESS_DID");
        new Intent(intent).setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }

    /* access modifiers changed from: protected */
    public final void a(IBinder iBinder) {
        try {
            b a = c.a(iBinder);
            if (a != null) {
                String e = a.e();
                String d = a.d();
                String c = a.c();
                a(e);
                b(d);
                c(c);
            }
            b();
        } catch (Throwable th) {
        }
    }
}
