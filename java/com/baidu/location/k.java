package com.baidu.location;

import com.baidu.location.a.c;

class k extends Thread {
    final /* synthetic */ g a;

    k(g gVar) {
        this.a = gVar;
    }

    public void run() {
        if (this.a.D == null) {
            this.a.D = new c(this.a.f, this.a.d, this.a);
        }
        if (this.a.D != null) {
            this.a.D.c();
        }
    }
}
