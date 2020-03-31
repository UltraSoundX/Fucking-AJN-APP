package com.baidu.location.a;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.location.b.d;
import com.baidu.location.d.g;
import com.baidu.location.e.i;
import com.baidu.location.g.j;

class x extends Handler {
    final /* synthetic */ w a;

    x(w wVar, Looper looper) {
        this.a = wVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        boolean z = true;
        switch (message.what) {
            case 1:
                Bundle data = message.getData();
                try {
                    Location location = (Location) data.getParcelable("loc");
                    data.getInt("satnum");
                    if (location != null) {
                        d.a().a(location);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                v.a(t.c(), i.a().o(), t.d(), t.a());
                return;
            case 3:
                v.a(t.c(), null, t.d(), a.a().d());
                return;
            case 4:
                boolean j = i.j();
                if (!j) {
                    z = j;
                } else if (h.a().d() == 1) {
                    z = false;
                }
                if (z) {
                    if (d.a().e()) {
                        g.a().m();
                        g.a().i();
                    }
                    com.baidu.location.b.g.a().c();
                    if (d.a().e()) {
                        v.a().c();
                    }
                }
                try {
                    if (this.a.d != null) {
                        this.a.d.sendEmptyMessageDelayed(4, (long) j.Q);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 5:
                com.baidu.location.b.g.a().b();
                return;
            case 7:
                v.a().c();
                com.baidu.location.b.g.a().c();
                return;
            case 8:
                message.getData();
                return;
            default:
                return;
        }
    }
}
