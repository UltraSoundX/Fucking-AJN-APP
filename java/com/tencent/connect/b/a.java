package com.tencent.connect.b;

import android.os.Build;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
public class a extends com.tencent.connect.common.a {
    public static String a;
    public static String b;

    static {
        a = "libwbsafeedit";
        b = a + ".so";
        String str = Build.CPU_ABI;
        if (str == null || str.equals("")) {
            a = "libwbsafeedit";
            b = a + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        } else if (str.equalsIgnoreCase("arm64-v8a")) {
            a = "libwbsafeedit_64";
            b = a + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
        } else if (str.equalsIgnoreCase("x86")) {
            a = "libwbsafeedit_x86";
            b = a + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86 architecture");
        } else if (str.equalsIgnoreCase("x86_64")) {
            a = "libwbsafeedit_x86_64";
            b = a + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
        } else {
            a = "libwbsafeedit";
            b = a + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        }
    }

    public a(b bVar) {
        super(bVar);
    }
}
