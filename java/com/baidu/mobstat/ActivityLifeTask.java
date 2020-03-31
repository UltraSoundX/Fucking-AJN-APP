package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback;
import com.baidu.mobstat.AutoTrack.MyActivityLifeCallback;
import com.baidu.mobstat.af.a;

public class ActivityLifeTask {
    private static boolean a = false;
    private static IActivityLifeCallback b;
    private static IActivityLifeCallback c;
    private static IActivityLifeCallback d;
    private static IActivityLifeCallback e;

    public static synchronized void registerActivityLifeCallback(Context context) {
        synchronized (ActivityLifeTask.class) {
            if (!a) {
                a(context);
                ActivityLifeObserver.instance().clearObservers();
                ActivityLifeObserver.instance().addObserver(b);
                ActivityLifeObserver.instance().addObserver(e);
                ActivityLifeObserver.instance().registerActivityLifeCallback(context);
                a = true;
            }
        }
    }

    private static synchronized void a(Context context) {
        synchronized (ActivityLifeTask.class) {
            b = new MyActivityLifeCallback(1);
            d = new a();
            c = new ah.a();
            e = new MyActivityLifeCallback(2);
        }
    }
}
