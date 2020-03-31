package com.jg.ids;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public abstract class g extends f {
    private ServiceConnection b = new h(this);

    /* access modifiers changed from: protected */
    public abstract Intent a();

    /* access modifiers changed from: protected */
    public abstract void a(IBinder iBinder);

    public g(Context context) {
        super(context);
        if (c() && d(context)) {
            try {
                this.a.bindService(a(), this.b, 1);
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        try {
            this.a.unbindService(this.b);
        } catch (Throwable th) {
        }
    }
}
