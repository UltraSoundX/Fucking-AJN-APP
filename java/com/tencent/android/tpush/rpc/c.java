package com.tencent.android.tpush.rpc;

import android.content.ServiceConnection;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.rpc.b.a;
import com.tencent.android.tpush.service.b;

/* compiled from: ProGuard */
public class c extends a {
    private ServiceConnection a;

    public void a(ServiceConnection serviceConnection) {
        this.a = serviceConnection;
    }

    public void a() {
        try {
            if (b.f() != null) {
                b.f().unbindService(this.a);
                this.a = null;
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "unBind", th);
        }
    }
}
