package com.baidu.location.b;

import android.os.Handler;
import android.os.Message;

class c extends Handler {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    this.a.f();
                    return;
                } catch (Exception e) {
                    return;
                }
            case 2:
                try {
                    this.a.g();
                    return;
                } catch (Exception e2) {
                    return;
                }
            default:
                return;
        }
    }
}
