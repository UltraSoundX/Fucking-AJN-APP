package com.baidu.b.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class l extends Handler {
    final /* synthetic */ b a;

    l(b bVar, Looper looper) {
        this.a = bVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (d.a) {
            d.a("handleMessage !!");
        }
        c cVar = (c) b.f.get(message.getData().getString("listenerKey"));
        if (d.a) {
            d.a("handleMessage listener = " + cVar);
        }
        if (cVar != null) {
            cVar.a(message.what, message.obj.toString());
        }
    }
}
