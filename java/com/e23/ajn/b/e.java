package com.e23.ajn.b;

import android.app.Activity;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.c;

/* compiled from: EventBusActivityScope */
public class e {
    private static final String a = e.class.getSimpleName();
    private static AtomicBoolean b = new AtomicBoolean(false);
    private static volatile c c;
    private static final Map<Activity, a> d = new ConcurrentHashMap();

    /* compiled from: EventBusActivityScope */
    static class a {
        private volatile c a;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public c a() {
            if (this.a == null) {
                synchronized (this) {
                    if (this.a == null) {
                        this.a = new c();
                    }
                }
            }
            return this.a;
        }
    }

    public static c a(Activity activity) {
        if (activity == null) {
            Log.e(a, "Can't find the Activity, the Activity is null!");
            return a();
        }
        a aVar = (a) d.get(activity);
        if (aVar != null) {
            return aVar.a();
        }
        Log.e(a, "Can't find the Activity, it has been removed!");
        return a();
    }

    private static c a() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new c();
                }
            }
        }
        return c;
    }
}
