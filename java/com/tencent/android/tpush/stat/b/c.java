package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.net.LocalServerSocket;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import java.io.IOException;

/* compiled from: ProGuard */
public class c {
    static LocalServerSocket a = null;
    private static String b = null;
    private static long c = 0;
    private static String d = null;

    public static boolean a() {
        String str = "com.tencent.teg.mid.sock.lock";
        try {
            a = new LocalServerSocket(str);
            return true;
        } catch (IOException e) {
            a.c(Constants.LogTag, "socket Name:" + str + " is in use.");
            return false;
        } catch (Throwable th) {
            a.c(Constants.LogTag, "something wrong while create LocalServerSocket.");
            return false;
        }
    }

    public static void b() {
        if (a != null) {
            try {
                a.close();
                a.c(Constants.LogTag, "close socket  mLocalServerSocket:" + a);
                a = null;
            } catch (Throwable th) {
            }
        }
    }

    public static long a(Context context) {
        if (c == 0 || c < 0) {
            synchronized (c.class) {
                if (c == 0 || c < 0) {
                    c = i.a(context).b().a();
                }
            }
        }
        return c;
    }

    public static String b(Context context) {
        if (!a(b)) {
            synchronized (c.class) {
                if (!a(b)) {
                    b = i.a(context).b().e();
                }
            }
        }
        return b;
    }

    public static void a(Context context, String str) {
        if (a(str)) {
            b = str;
            a.c(Constants.LogTag, "updateLocalMid:" + str);
            d dVar = new d();
            dVar.c(CustomDeviceInfos.getDeviceId(context));
            dVar.e(CustomDeviceInfos.getMacAddress(context));
            dVar.b(str);
            dVar.b(System.currentTimeMillis());
            i.a(context).d(dVar);
        }
    }

    public static void a(Context context, long j, String str) {
        a.c(Constants.LogTag, "updateLocalGuid:" + j);
        if (a(str)) {
            b = str;
            c = j;
            d dVar = new d();
            dVar.c(CustomDeviceInfos.getDeviceId(context));
            dVar.e(CustomDeviceInfos.getMacAddress(context));
            dVar.b(str);
            dVar.a(j);
            dVar.b(System.currentTimeMillis());
            i.a(context).d(dVar);
        }
    }

    public static boolean a(String str) {
        if (str == null || str.trim().length() != 40) {
            return false;
        }
        return true;
    }
}
