package com.tencent.android.tpush.service.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.qq.taf.jce.JceStruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

/* compiled from: ProGuard */
public class a {
    public static int a = 0;
    private static final String b = a.class.getSimpleName();
    private static Uri c = Uri.parse("content://telephony/carriers/preferapn");

    public static byte a(Context context) {
        switch (c(context)) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 4:
                return 1;
            case 8:
                return 4;
            case 16:
                return 5;
            case 32:
                return 6;
            case 64:
                return 7;
            case 256:
                return 8;
            case 512:
                return 9;
            case 1024:
                return 10;
            case 2048:
                return JceStruct.STRUCT_END;
            default:
                return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A[SYNTHETIC, Splitter:B:31:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r7) {
        /*
            r6 = 0
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x003a, all -> 0x0045 }
            android.net.Uri r1 = c     // Catch:{ Exception -> 0x003a, all -> 0x0045 }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x003a, all -> 0x0045 }
            if (r1 != 0) goto L_0x0018
            if (r1 == 0) goto L_0x0016
            r1.close()     // Catch:{ Exception -> 0x004d }
        L_0x0016:
            r0 = r6
        L_0x0017:
            return r0
        L_0x0018:
            r1.moveToFirst()     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            boolean r0 = r1.isAfterLast()     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            if (r0 == 0) goto L_0x0028
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ Exception -> 0x004f }
        L_0x0026:
            r0 = r6
            goto L_0x0017
        L_0x0028:
            java.lang.String r0 = "proxy"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            if (r1 == 0) goto L_0x0017
            r1.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x0017
        L_0x0038:
            r1 = move-exception
            goto L_0x0017
        L_0x003a:
            r0 = move-exception
        L_0x003b:
            java.lang.String r0 = ""
            if (r6 == 0) goto L_0x0017
            r6.close()     // Catch:{ Exception -> 0x0043 }
            goto L_0x0017
        L_0x0043:
            r1 = move-exception
            goto L_0x0017
        L_0x0045:
            r0 = move-exception
            r1 = r6
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ Exception -> 0x0051 }
        L_0x004c:
            throw r0
        L_0x004d:
            r0 = move-exception
            goto L_0x0016
        L_0x004f:
            r0 = move-exception
            goto L_0x0026
        L_0x0051:
            r1 = move-exception
            goto L_0x004c
        L_0x0053:
            r0 = move-exception
            goto L_0x0047
        L_0x0055:
            r0 = move-exception
            r1 = r6
            goto L_0x0047
        L_0x0058:
            r0 = move-exception
            r6 = r1
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.e.a.b(android.content.Context):java.lang.String");
    }

    public static int c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 128;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase(Locale.US).equals("WIFI")) {
                return 2;
            }
            if (activeNetworkInfo.getExtraInfo() == null) {
                return 128;
            }
            String lowerCase = activeNetworkInfo.getExtraInfo().toLowerCase(Locale.US);
            if (lowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (lowerCase.startsWith("cmnet") || lowerCase.startsWith("epc.tmobile.com")) {
                return 4;
            }
            if (lowerCase.startsWith("uniwap")) {
                return 16;
            }
            if (lowerCase.startsWith("uninet")) {
                return 8;
            }
            if (lowerCase.startsWith("wap")) {
                return 64;
            }
            if (lowerCase.startsWith("net")) {
                return 32;
            }
            if (lowerCase.startsWith("ctwap")) {
                return 512;
            }
            if (lowerCase.startsWith("ctnet")) {
                return 256;
            }
            if (lowerCase.startsWith("3gwap")) {
                return 1024;
            }
            if (lowerCase.startsWith("3gnet")) {
                return 2048;
            }
            if (lowerCase.startsWith("#777")) {
                String b2 = b(context);
                if (b2 == null || b2.length() <= 0) {
                    return 256;
                }
                return 512;
            }
            return 128;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d(b, "getMProxyType>>> ", e);
        }
    }

    public static boolean a() {
        try {
            Process exec = Runtime.getRuntime().exec("ping -c 1 -w 10 www.qq.com");
            int waitFor = exec.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String str = "";
            do {
            } while (bufferedReader.readLine() != null);
            bufferedReader.close();
            exec.destroy();
            if (waitFor == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean d(Context context) {
        boolean z;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                if (connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isConnected()) {
                    z = false;
                } else {
                    z = true;
                }
                return z;
            }
        } catch (Exception e) {
            if (a()) {
                return true;
            }
            com.tencent.android.tpush.b.a.d(b, "APNUtil -> checkNetWork", e);
            a++;
            if (a >= 5) {
                a = 0;
                return true;
            }
        }
        return false;
    }
}
