package com.baidu.location.indoor;

import android.os.Message;
import com.baidu.location.BDLocation;
import com.baidu.location.a.a;
import com.baidu.location.c;

class h implements c {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void a(BDLocation bDLocation) {
        if (this.a.f()) {
            if (!(bDLocation == null || this.a.Y == null)) {
                if (bDLocation.u() == null && this.a.Y.u() != null) {
                    bDLocation.a(this.a.Y.t());
                    bDLocation.e(this.a.Y.u());
                }
                if (bDLocation.a() == null && this.a.Y.a() != null) {
                    bDLocation.a(this.a.Y.a());
                }
                if (bDLocation.B() == null && this.a.Y.B() != null) {
                    bDLocation.g(this.a.Y.B());
                }
            }
            if (bDLocation != null) {
                bDLocation.a(1);
                bDLocation.c(this.a.X);
                a.a().a(bDLocation);
            }
            if (bDLocation != null && bDLocation.H().equals("ml")) {
                Message obtainMessage = this.a.a.obtainMessage(801);
                obtainMessage.obj = bDLocation;
                obtainMessage.sendToTarget();
            }
        }
    }
}
