package com.baidu.location.e;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.f;

class g extends Handler {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public void handleMessage(Message message) {
        if (f.f) {
            switch (message.what) {
                case 1:
                    this.a.e((Location) message.obj);
                    return;
                case 3:
                    this.a.a("&og=1", (Location) message.obj);
                    return;
                case 4:
                    this.a.a("&og=2", (Location) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
