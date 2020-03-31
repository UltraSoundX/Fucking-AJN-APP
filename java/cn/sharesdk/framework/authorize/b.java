package cn.sharesdk.framework.authorize;

import android.app.Activity;

/* compiled from: AuthorizeParams */
public class b {
    private static volatile b a = null;
    private Activity b;

    public Activity a() {
        return this.b;
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public static b b() {
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
        }
        return a;
    }
}
