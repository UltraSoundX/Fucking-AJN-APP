package com.baidu.location.b;

import android.os.Handler;
import android.os.Message;

class h extends Handler {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.d();
                return;
            default:
                return;
        }
    }
}
