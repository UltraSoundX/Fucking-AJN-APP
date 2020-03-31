package com.baidu.location.e;

import com.baidu.location.a.k;
import com.baidu.location.a.t;
import com.baidu.location.a.w;
import com.baidu.location.indoor.g;
import com.baidu.mobstat.Config;

class j implements Runnable {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public void run() {
        i.this.s();
        k.c().i();
        if (g.a().e()) {
            g.a().a.obtainMessage(41).sendToTarget();
        }
        if (System.currentTimeMillis() - t.b() <= Config.BPLUS_DELAY_TIME) {
            w.a().c();
        }
    }
}
