package com.jg.ids;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class h implements ServiceConnection {
    private /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.a(iBinder);
        } catch (Throwable th) {
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
