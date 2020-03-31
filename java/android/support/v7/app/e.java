package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

/* compiled from: ResourcesFlusher */
class e {
    private static Field a;
    private static boolean b;
    private static Class c;
    private static boolean d;
    private static Field e;
    private static boolean f;
    private static Field g;
    private static boolean h;

    static void a(Resources resources) {
        if (VERSION.SDK_INT < 28) {
            if (VERSION.SDK_INT >= 24) {
                d(resources);
            } else if (VERSION.SDK_INT >= 23) {
                c(resources);
            } else if (VERSION.SDK_INT >= 21) {
                b(resources);
            }
        }
    }

    private static void b(Resources resources) {
        Map map;
        if (!b) {
            try {
                a = Resources.class.getDeclaredField("mDrawableCache");
                a.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e2);
            }
            b = true;
        }
        if (a != null) {
            try {
                map = (Map) a.get(resources);
            } catch (IllegalAccessException e3) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e3);
                map = null;
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.res.Resources r4) {
        /*
            r3 = 1
            boolean r0 = b
            if (r0 != 0) goto L_0x0017
            java.lang.Class<android.content.res.Resources> r0 = android.content.res.Resources.class
            java.lang.String r1 = "mDrawableCache"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r1)     // Catch:{ NoSuchFieldException -> 0x0025 }
            a = r0     // Catch:{ NoSuchFieldException -> 0x0025 }
            java.lang.reflect.Field r0 = a     // Catch:{ NoSuchFieldException -> 0x0025 }
            r1 = 1
            r0.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x0025 }
        L_0x0015:
            b = r3
        L_0x0017:
            r1 = 0
            java.lang.reflect.Field r0 = a
            if (r0 == 0) goto L_0x0036
            java.lang.reflect.Field r0 = a     // Catch:{ IllegalAccessException -> 0x002e }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ IllegalAccessException -> 0x002e }
        L_0x0022:
            if (r0 != 0) goto L_0x0038
        L_0x0024:
            return
        L_0x0025:
            r0 = move-exception
            java.lang.String r1 = "ResourcesFlusher"
            java.lang.String r2 = "Could not retrieve Resources#mDrawableCache field"
            android.util.Log.e(r1, r2, r0)
            goto L_0x0015
        L_0x002e:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve value from Resources#mDrawableCache"
            android.util.Log.e(r2, r3, r0)
        L_0x0036:
            r0 = r1
            goto L_0x0022
        L_0x0038:
            a(r0)
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.e.c(android.content.res.Resources):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(android.content.res.Resources r6) {
        /*
            r1 = 0
            r5 = 1
            boolean r0 = h
            if (r0 != 0) goto L_0x0018
            java.lang.Class<android.content.res.Resources> r0 = android.content.res.Resources.class
            java.lang.String r2 = "mResourcesImpl"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x001d }
            g = r0     // Catch:{ NoSuchFieldException -> 0x001d }
            java.lang.reflect.Field r0 = g     // Catch:{ NoSuchFieldException -> 0x001d }
            r2 = 1
            r0.setAccessible(r2)     // Catch:{ NoSuchFieldException -> 0x001d }
        L_0x0016:
            h = r5
        L_0x0018:
            java.lang.reflect.Field r0 = g
            if (r0 != 0) goto L_0x0026
        L_0x001c:
            return
        L_0x001d:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve Resources#mResourcesImpl field"
            android.util.Log.e(r2, r3, r0)
            goto L_0x0016
        L_0x0026:
            java.lang.reflect.Field r0 = g     // Catch:{ IllegalAccessException -> 0x0057 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ IllegalAccessException -> 0x0057 }
            r2 = r0
        L_0x002d:
            if (r2 == 0) goto L_0x001c
            boolean r0 = b
            if (r0 != 0) goto L_0x0047
            java.lang.Class r0 = r2.getClass()     // Catch:{ NoSuchFieldException -> 0x0061 }
            java.lang.String r3 = "mDrawableCache"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0061 }
            a = r0     // Catch:{ NoSuchFieldException -> 0x0061 }
            java.lang.reflect.Field r0 = a     // Catch:{ NoSuchFieldException -> 0x0061 }
            r3 = 1
            r0.setAccessible(r3)     // Catch:{ NoSuchFieldException -> 0x0061 }
        L_0x0045:
            b = r5
        L_0x0047:
            java.lang.reflect.Field r0 = a
            if (r0 == 0) goto L_0x0072
            java.lang.reflect.Field r0 = a     // Catch:{ IllegalAccessException -> 0x006a }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ IllegalAccessException -> 0x006a }
        L_0x0051:
            if (r0 == 0) goto L_0x001c
            a(r0)
            goto L_0x001c
        L_0x0057:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve value from Resources#mResourcesImpl"
            android.util.Log.e(r2, r3, r0)
            r2 = r1
            goto L_0x002d
        L_0x0061:
            r0 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve ResourcesImpl#mDrawableCache field"
            android.util.Log.e(r3, r4, r0)
            goto L_0x0045
        L_0x006a:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve value from ResourcesImpl#mDrawableCache"
            android.util.Log.e(r2, r3, r0)
        L_0x0072:
            r0 = r1
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.e.d(android.content.res.Resources):void");
    }

    private static void a(Object obj) {
        LongSparseArray longSparseArray;
        if (!d) {
            try {
                c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e2) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e2);
            }
            d = true;
        }
        if (c != null) {
            if (!f) {
                try {
                    e = c.getDeclaredField("mUnthemedEntries");
                    e.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e3);
                }
                f = true;
            }
            if (e != null) {
                try {
                    longSparseArray = (LongSparseArray) e.get(obj);
                } catch (IllegalAccessException e4) {
                    Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e4);
                    longSparseArray = null;
                }
                if (longSparseArray != null) {
                    longSparseArray.clear();
                }
            }
        }
    }
}
