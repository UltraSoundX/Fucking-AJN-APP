package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;
import java.util.Set;

public class GeolocationPermissions {
    private static GeolocationPermissions a;

    public static GeolocationPermissions getInstance() {
        return a();
    }

    private static synchronized GeolocationPermissions a() {
        GeolocationPermissions geolocationPermissions;
        synchronized (GeolocationPermissions.class) {
            if (a == null) {
                a = new GeolocationPermissions();
            }
            geolocationPermissions = a;
        }
        return geolocationPermissions;
    }

    public void getOrigins(ValueCallback<Set<String>> valueCallback) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().getOrigins(valueCallback);
        } else {
            a2.c().b((ValueCallback<Set<String>>) valueCallback);
        }
    }

    public void getAllowed(String str, ValueCallback<Boolean> valueCallback) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().getAllowed(str, valueCallback);
        } else {
            a2.c().c(str, valueCallback);
        }
    }

    public void clear(String str) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().clear(str);
        } else {
            a2.c().f(str);
        }
    }

    public void allow(String str) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().allow(str);
        } else {
            a2.c().g(str);
        }
    }

    public void clearAll() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.GeolocationPermissions.getInstance().clearAll();
        } else {
            a2.c().o();
        }
    }
}
