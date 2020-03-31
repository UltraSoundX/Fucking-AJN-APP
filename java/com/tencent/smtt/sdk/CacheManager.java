package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.k;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public final class CacheManager {
    @Deprecated
    public static File getCacheFileBaseDir() {
        t a = t.a();
        if (a == null || !a.b()) {
            return (File) k.a("android.webkit.CacheManager", "getCacheFileBaseDir");
        }
        return (File) a.c().g();
    }

    @Deprecated
    public static boolean cacheDisabled() {
        t a = t.a();
        if (a != null && a.b()) {
            return ((Boolean) a.c().c()).booleanValue();
        }
        Object a2 = k.a("android.webkit.CacheManager", "cacheDisabled");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        t a = t.a();
        if (a != null && a.b()) {
            return a.c().g();
        }
        try {
            return k.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream getCacheFile(String str, boolean z) {
        t a = t.a();
        if (a == null || !a.b()) {
            return null;
        }
        return a.c().a(str, z);
    }
}
