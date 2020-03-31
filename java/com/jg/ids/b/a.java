package com.jg.ids.b;

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
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        return intent;
    }

    /* access modifiers changed from: protected */
    public final void a(IBinder iBinder) {
        try {
            c cVar = new c(iBinder);
            b(new b(cVar.a(), cVar.b()).a());
            b();
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean d(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.huawei.hwid", 0) != null) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }
}
