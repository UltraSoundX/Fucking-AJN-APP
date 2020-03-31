package com.mob.commons.authorize;

import android.text.TextUtils;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.a;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashSet;

public final class DeviceAuthorizer implements PublicMemberKeeper {
    static String a = null;
    private static HashSet<String> b = new HashSet<>();
    private static Object c = new Object();

    public static String authorizeForOnce() {
        if (a.ad()) {
            return null;
        }
        if (a != null) {
            return a;
        }
        return new a().a(true, true);
    }

    public static synchronized String authorize(final MobProduct mobProduct) {
        String str;
        boolean z = true;
        boolean z2 = false;
        synchronized (DeviceAuthorizer.class) {
            if (a.ac()) {
                str = null;
            } else {
                if (mobProduct != null) {
                    MobProductCollector.registerProduct(mobProduct);
                    if (!b.contains(mobProduct.getProductTag())) {
                        z2 = true;
                    }
                    if (z2) {
                        b.add(mobProduct.getProductTag());
                    }
                }
                if (TextUtils.isEmpty(a)) {
                    a = new a().a(true, false);
                } else {
                    z = z2;
                }
                if (TextUtils.isEmpty(a)) {
                    a = b(mobProduct);
                    if (TextUtils.isEmpty(a)) {
                        str = new a().a();
                    } else {
                        str = a;
                    }
                } else {
                    if (z) {
                        new Thread() {
                            public void run() {
                                try {
                                    DeviceAuthorizer.b(mobProduct);
                                } catch (Throwable th) {
                                    MobLog.getInstance().d(th);
                                }
                            }
                        }.start();
                    }
                    str = a;
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static String b(MobProduct mobProduct) {
        String a2;
        synchronized (c) {
            a aVar = new a();
            if (a.i()) {
                a2 = aVar.a(mobProduct);
            } else {
                a2 = aVar.a(false, true);
            }
        }
        return a2;
    }
}
