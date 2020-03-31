package com.bumptech.glide.d.b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.i.h;

/* compiled from: ResourceRecycler */
class l {
    private boolean a;
    private final Handler b = new Handler(Looper.getMainLooper(), new a());

    /* compiled from: ResourceRecycler */
    private static class a implements Callback {
        private a() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((k) message.obj).d();
            return true;
        }
    }

    l() {
    }

    public void a(k<?> kVar) {
        h.a();
        if (this.a) {
            this.b.obtainMessage(1, kVar).sendToTarget();
            return;
        }
        this.a = true;
        kVar.d();
        this.a = false;
    }
}
