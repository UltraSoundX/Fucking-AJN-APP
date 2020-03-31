package com.ccb.crypto.tp.tool;

import android.text.TextUtils;

class c extends Thread {
    final /* synthetic */ d a;

    c(d dVar) {
        this.a = dVar;
    }

    public void run() {
        synchronized (this) {
            try {
                wait(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (d.e != null && d.g != null && TextUtils.isEmpty(d.h)) {
            d.e.removeUpdates(d.g);
            d.g = null;
        }
    }
}
