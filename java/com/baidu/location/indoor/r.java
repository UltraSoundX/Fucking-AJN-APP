package com.baidu.location.indoor;

import com.baidu.location.BDLocation;

class r implements Runnable {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public void run() {
        b a2 = this.a.a(this.a.e);
        if (!(a2 == null || this.a.a == null)) {
            this.a.e = this.a.e.b(a2);
            long currentTimeMillis = System.currentTimeMillis();
            if (!a2.b(2.0E-6d) && currentTimeMillis - this.a.k > this.a.b) {
                BDLocation bDLocation = new BDLocation(this.a.c);
                bDLocation.a(this.a.e.a);
                bDLocation.b(this.a.e.b);
                this.a.a.a(bDLocation);
                this.a.k = currentTimeMillis;
            }
        }
        this.a.m.postDelayed(this.a.o, 450);
    }
}
