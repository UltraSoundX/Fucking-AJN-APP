package com.tencent.android.tpush.rpc;

import android.content.Intent;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.c.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.rpc.a.C0062a;
import com.tencent.android.tpush.service.b;

/* compiled from: ProGuard */
public class d extends C0062a {
    public void a(String str, b bVar) {
        try {
            f.a(b.f()).a(Intent.parseUri(str, 0));
            bVar.a();
        } catch (Throwable th) {
            a.d(Constants.ServiceLogTag, "Show", th);
        }
    }

    public void a() {
        try {
            b.a(b.f());
        } catch (Throwable th) {
            a.d(Constants.ServiceLogTag, "startService", th);
        }
    }

    public void b() {
    }
}
