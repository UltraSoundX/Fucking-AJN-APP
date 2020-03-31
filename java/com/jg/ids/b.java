package com.jg.ids;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class b extends Handler {
    private /* synthetic */ a a;

    b(a aVar, Looper looper) {
        this.a = aVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        try {
            this.a.a(message);
        } catch (Throwable th) {
        }
    }
}
