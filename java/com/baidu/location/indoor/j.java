package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.n.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.Date;

class j implements a {
    final /* synthetic */ g a;

    j(g gVar) {
        this.a = gVar;
    }

    public synchronized void a(double d, double d2, double d3, long j) {
        if (this.a.o) {
            this.a.J = 0.4d;
            this.a.ae.a(d, d2, d3, j);
            double[] a2 = com.baidu.location.indoor.mapversion.b.a.a(this.a.w, d, d2, d3);
            if (!(a2 == null || a2[0] == -1.0d || a2[0] != 0.0d)) {
                this.a.I = a2[2];
                this.a.H = a2[1];
                if (this.a.M.size() > 50) {
                    this.a.M.clear();
                }
                this.a.M.add(new h(this.a.k.d(), d, d3, d2));
                this.a.u = this.a.u + 1;
                try {
                    BDLocation bDLocation = new BDLocation();
                    bDLocation.e((int) ErrorCode.STARTDOWNLOAD_2);
                    bDLocation.a(a2[2]);
                    bDLocation.b(a2[1]);
                    bDLocation.c((float) d3);
                    bDLocation.b(this.a.b.format(new Date()));
                    bDLocation.h(this.a.w);
                    bDLocation.i(this.a.x);
                    bDLocation.j(this.a.z);
                    bDLocation.g(this.a.C);
                    bDLocation.a(true);
                    if (this.a.T) {
                        bDLocation.b(8.0f);
                    } else {
                        bDLocation.b(15.0f);
                    }
                    bDLocation.a("res", a2);
                    bDLocation.b((float) a2[5]);
                    bDLocation.c((float) a2[6]);
                    bDLocation.a((float) a2[8]);
                    bDLocation.k("dr");
                    BDLocation bDLocation2 = new BDLocation(bDLocation);
                    bDLocation2.k("dr2");
                    if (this.a.U == null || !this.a.U.c()) {
                        this.a.a(bDLocation2, 21);
                    } else {
                        this.a.U.a(bDLocation2);
                    }
                    if (!this.a.ae.a(bDLocation, a2[5], "dr")) {
                        this.a.d();
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
