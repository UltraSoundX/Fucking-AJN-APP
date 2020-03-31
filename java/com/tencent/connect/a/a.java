package com.tencent.connect.a;

import android.content.Context;
import com.tencent.connect.b.b;
import com.tencent.open.a.f;
import com.tencent.open.d.e;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class a {
    private static Class<?> a = null;
    private static Class<?> b = null;
    private static Method c = null;
    private static Method d = null;
    private static Method e = null;
    private static Method f = null;
    private static boolean g = false;

    public static boolean a(Context context, b bVar) {
        return e.a(context, bVar.b()).b("Common_ta_enable");
    }

    public static void b(Context context, b bVar) {
        try {
            if (a(context, bVar)) {
                f.invoke(a, new Object[]{Boolean.valueOf(true)});
                return;
            }
            f.invoke(a, new Object[]{Boolean.valueOf(false)});
        } catch (Exception e2) {
            f.e("OpenConfig", "checkStatStatus exception: " + e2.getStackTrace().toString());
        }
    }

    public static void c(Context context, b bVar) {
        String str = "Aqc" + bVar.b();
        try {
            a = Class.forName("com.tencent.stat.StatConfig");
            b = Class.forName("com.tencent.stat.StatService");
            c = b.getMethod("reportQQ", new Class[]{Context.class, String.class});
            d = b.getMethod("trackCustomEvent", new Class[]{Context.class, String.class, String[].class});
            e = b.getMethod("commitEvents", new Class[]{Context.class, Integer.TYPE});
            f = a.getMethod("setEnableStatService", new Class[]{Boolean.TYPE});
            b(context, bVar);
            a.getMethod("setAutoExceptionCaught", new Class[]{Boolean.TYPE}).invoke(a, new Object[]{Boolean.valueOf(false)});
            a.getMethod("setEnableSmartReporting", new Class[]{Boolean.TYPE}).invoke(a, new Object[]{Boolean.valueOf(true)});
            a.getMethod("setSendPeriodMinutes", new Class[]{Integer.TYPE}).invoke(a, new Object[]{Integer.valueOf(1440)});
            Class cls = Class.forName("com.tencent.stat.StatReportStrategy");
            a.getMethod("setStatSendStrategy", new Class[]{cls}).invoke(a, new Object[]{cls.getField("PERIOD").get(null)});
            b.getMethod("startStatService", new Class[]{Context.class, String.class, String.class}).invoke(b, new Object[]{context, str, Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get(null)});
            g = true;
        } catch (Exception e2) {
            f.e("OpenConfig", "start4QQConnect exception: " + e2.getStackTrace().toString());
        }
    }

    public static void a(Context context, b bVar, String str, String... strArr) {
        if (g) {
            b(context, bVar);
            try {
                d.invoke(b, new Object[]{context, str, strArr});
            } catch (Exception e2) {
                f.e("OpenConfig", "trackCustomEvent exception: " + e2.getStackTrace().toString());
            }
        }
    }
}
