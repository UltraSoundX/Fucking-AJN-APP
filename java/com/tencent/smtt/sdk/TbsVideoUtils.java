package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

public class TbsVideoUtils {
    private static p a = null;

    private static void a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (a == null) {
                d.a(true).a(context, false, false);
                r a2 = d.a(true).a();
                DexLoader dexLoader = null;
                if (a2 != null) {
                    dexLoader = a2.b();
                }
                if (dexLoader != null) {
                    a = new p(dexLoader);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        a(context);
        if (a != null) {
            a.a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        a(context);
        if (a != null) {
            return a.a(context);
        }
        return "";
    }
}
