package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;

/* compiled from: ForbSwitchFunction */
public class c {
    private static volatile c b = null;
    /* access modifiers changed from: private */
    public boolean a = false;

    private c() {
        new Thread() {
            public void run() {
                try {
                    c.this.a = MobSDK.isForb();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
    }

    public static c a() {
        synchronized (c.class) {
            if (b == null) {
                synchronized (c.class) {
                    if (b == null) {
                        b = new c();
                    }
                }
            }
        }
        return b;
    }

    public boolean b() {
        return this.a;
    }
}
