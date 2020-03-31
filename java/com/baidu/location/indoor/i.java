package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.e.e;
import com.baidu.location.indoor.q.a;
import com.zhy.http.okhttp.OkHttpUtils;

class i implements a {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    public void a(BDLocation bDLocation) {
        if (this.a.f()) {
            if (this.a.ae != null && System.currentTimeMillis() - this.a.ae.c > 20000 && System.currentTimeMillis() - this.a.ae.e < OkHttpUtils.DEFAULT_MILLISECONDS) {
                bDLocation.e(61);
                bDLocation.h((String) null);
                bDLocation.i((String) null);
                bDLocation.j((String) null);
            }
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (e.a().j()) {
                String g = e.a().g();
                if (g != null) {
                    BDLocation bDLocation3 = new BDLocation(g);
                    bDLocation2.e(61);
                    bDLocation2.f(bDLocation3.q());
                    bDLocation2.a(bDLocation3.k());
                    bDLocation2.c(bDLocation3.j());
                    bDLocation2.c(bDLocation3.r());
                }
            }
            this.a.a(bDLocation2, 29);
            this.a.af.a(bDLocation);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a.ae != null && currentTimeMillis - this.a.ae.c > 30000 && currentTimeMillis - this.a.ae.e > 30000) {
            this.a.d();
        }
    }
}
