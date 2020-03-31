package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.WebView;

/* compiled from: SysCoreQUA2Utils */
public class l {
    private static String a = null;
    private static String b = "GA";
    private static String c = "GE";
    private static String d = "9422";
    private static String e = "0";
    private static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), "0", b, c, d, e, f, g);
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19) {
        /*
            java.lang.String r1 = ""
            java.lang.String r4 = ""
            java.lang.String r1 = ""
            java.lang.String r3 = "PHONE"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r6 = b(r11)
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r6 = "*"
            java.lang.StringBuilder r2 = r2.append(r6)
            int r6 = c(r11)
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r6 = r2.toString()
            android.content.Context r2 = r11.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x0127 }
            android.content.Context r2 = com.stub.StubApp.getOrigApplicationContext(r2)     // Catch:{ NameNotFoundException -> 0x0127 }
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()     // Catch:{ NameNotFoundException -> 0x0127 }
            android.content.pm.PackageManager r7 = r11.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0127 }
            java.lang.String r8 = r2.packageName     // Catch:{ NameNotFoundException -> 0x0127 }
            r9 = 0
            android.content.pm.PackageInfo r7 = r7.getPackageInfo(r8, r9)     // Catch:{ NameNotFoundException -> 0x0127 }
            java.lang.String r2 = r2.packageName     // Catch:{ NameNotFoundException -> 0x0127 }
            boolean r4 = android.text.TextUtils.isEmpty(r18)     // Catch:{ NameNotFoundException -> 0x0143 }
            if (r4 != 0) goto L_0x0121
        L_0x004b:
            r1 = r2
        L_0x004c:
            java.lang.String r4 = a(r1)
            java.lang.String r2 = "QB"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0133
            if (r19 == 0) goto L_0x0145
            java.lang.String r2 = "PAD"
        L_0x005c:
            java.lang.String r3 = "QV"
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String r7 = "="
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r7 = "3"
            r3.append(r7)
            java.lang.String r3 = "PL"
            java.lang.String r7 = "ADR"
            a(r5, r3, r7)
            java.lang.String r3 = "PR"
            a(r5, r3, r4)
            java.lang.String r3 = "PP"
            a(r5, r3, r1)
            java.lang.String r1 = "PPVN"
            r0 = r18
            a(r5, r1, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r12)
            if (r1 != 0) goto L_0x0090
            java.lang.String r1 = "TBSVC"
            a(r5, r1, r12)
        L_0x0090:
            java.lang.String r1 = "CO"
            java.lang.String r3 = "SYS"
            a(r5, r1, r3)
            boolean r1 = android.text.TextUtils.isEmpty(r13)
            if (r1 != 0) goto L_0x00a2
            java.lang.String r1 = "COVC"
            a(r5, r1, r13)
        L_0x00a2:
            java.lang.String r1 = "PB"
            a(r5, r1, r15)
            java.lang.String r1 = "VE"
            a(r5, r1, r14)
            java.lang.String r1 = "DE"
            a(r5, r1, r2)
            java.lang.String r1 = "CHID"
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 == 0) goto L_0x00bb
            java.lang.String r17 = "0"
        L_0x00bb:
            r0 = r17
            a(r5, r1, r0)
            java.lang.String r1 = "LCID"
            r0 = r16
            a(r5, r1, r0)
            java.lang.String r2 = a()
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x013d }
            java.lang.String r3 = "UTF-8"
            byte[] r3 = r2.getBytes(r3)     // Catch:{ Exception -> 0x013d }
            java.lang.String r4 = "ISO8859-1"
            r1.<init>(r3, r4)     // Catch:{ Exception -> 0x013d }
        L_0x00d8:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x00e3
            java.lang.String r2 = "MO"
            a(r5, r2, r1)
        L_0x00e3:
            java.lang.String r1 = "RL"
            a(r5, r1, r6)
            java.lang.String r2 = android.os.Build.VERSION.RELEASE
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0140 }
            java.lang.String r3 = "UTF-8"
            byte[] r3 = r2.getBytes(r3)     // Catch:{ Exception -> 0x0140 }
            java.lang.String r4 = "ISO8859-1"
            r1.<init>(r3, r4)     // Catch:{ Exception -> 0x0140 }
        L_0x00f7:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0102
            java.lang.String r2 = "OS"
            a(r5, r2, r1)
        L_0x0102:
            java.lang.String r1 = "API"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = android.os.Build.VERSION.SDK_INT
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ""
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            a(r5, r1, r2)
            java.lang.String r1 = r5.toString()
            return r1
        L_0x0121:
            java.lang.String r0 = r7.versionName     // Catch:{ NameNotFoundException -> 0x0143 }
            r18 = r0
            goto L_0x004b
        L_0x0127:
            r2 = move-exception
            r10 = r2
            r2 = r4
            r4 = r10
        L_0x012b:
            r4.printStackTrace()
            r18 = r1
            r1 = r2
            goto L_0x004c
        L_0x0133:
            boolean r2 = d(r11)
            if (r2 == 0) goto L_0x0145
            java.lang.String r2 = "PAD"
            goto L_0x005c
        L_0x013d:
            r1 = move-exception
            r1 = r2
            goto L_0x00d8
        L_0x0140:
            r1 = move-exception
            r1 = r2
            goto L_0x00f7
        L_0x0143:
            r4 = move-exception
            goto L_0x012b
        L_0x0145:
            r2 = r3
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.l.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append("&").append(str).append("=").append(str2);
    }

    private static String a(String str) {
        if (TbsConfig.APP_WX.equals(str)) {
            return "WX";
        }
        if (TbsConfig.APP_QQ.equals(str)) {
            return "QQ";
        }
        if (TbsConfig.APP_QZONE.equals(str)) {
            return "QZ";
        }
        if (TbsConfig.APP_QB.equals(str)) {
            return "QB";
        }
        return "TRD";
    }

    private static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    private static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    private static String a() {
        return " " + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + " ";
    }

    private static boolean d(Context context) {
        boolean z = true;
        if (h) {
            return i;
        }
        try {
            if ((Math.min(b(context), c(context)) * ErrorCode.STARTDOWNLOAD_1) / e(context) < 700) {
                z = false;
            }
            i = z;
            h = true;
            return i;
        } catch (Throwable th) {
            return false;
        }
    }

    private static int e(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (defaultDisplay == null) {
            return ErrorCode.STARTDOWNLOAD_1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}
