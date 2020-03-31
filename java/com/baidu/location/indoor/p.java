package com.baidu.location.indoor;

import java.util.TimerTask;

class p extends TimerTask {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public void run() {
        try {
            this.a.l();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
