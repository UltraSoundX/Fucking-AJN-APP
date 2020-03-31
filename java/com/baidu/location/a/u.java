package com.baidu.location.a;

import android.os.HandlerThread;

public class u {
    private static HandlerThread a = null;

    public static synchronized HandlerThread a() {
        HandlerThread handlerThread;
        synchronized (u.class) {
            if (a == null) {
                a = new HandlerThread("ServiceStartArguments", 10);
                a.start();
            }
            handlerThread = a;
        }
        return handlerThread;
    }
}
