package com.tencent.smtt.sdk;

import android.os.HandlerThread;

/* compiled from: TbsHandlerThread */
class k extends HandlerThread {
    private static k a;

    public k(String str) {
        super(str);
    }

    public static synchronized k a() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                a = new k("TbsHandlerThread");
                a.start();
            }
            kVar = a;
        }
        return kVar;
    }
}
