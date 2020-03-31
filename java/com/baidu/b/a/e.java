package com.baidu.b.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.tencent.mid.api.MidEntity;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

class e {

    static class a {
        public static String a(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }
    }

    static String a() {
        return Locale.getDefault().getLanguage();
    }

    protected static String a(Context context) {
        String packageName = context.getPackageName();
        return a(context, packageName) + ";" + packageName;
    }

    private static String a(Context context, String str) {
        String str2;
        String str3 = "";
        try {
            str2 = a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (NameNotFoundException e) {
            str2 = str3;
        } catch (CertificateException e2) {
            str2 = str3;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str2.length(); i++) {
            stringBuffer.append(str2.charAt(i));
            if (i > 0 && i % 2 == 1 && i < str2.length() - 1) {
                stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
            }
        }
        return stringBuffer.toString();
    }

    static String a(X509Certificate x509Certificate) {
        try {
            return a.a(a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            return null;
        }
    }

    static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    protected static String[] b(Context context) {
        String packageName = context.getPackageName();
        String[] b = b(context, packageName);
        if (b == null || b.length <= 0) {
            return null;
        }
        String[] strArr = new String[b.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = b[i] + ";" + packageName;
            if (d.a) {
                d.a("mcode" + strArr[i]);
            }
        }
        return strArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] b(android.content.Context r8, java.lang.String r9) {
        /*
            r2 = 0
            r3 = 0
            android.content.pm.PackageManager r0 = r8.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            r1 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r9, r1)     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            android.content.pm.Signature[] r5 = r0.signatures     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            if (r5 == 0) goto L_0x008f
            int r0 = r5.length     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            if (r0 <= 0) goto L_0x008f
            int r0 = r5.length     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            java.lang.String[] r1 = new java.lang.String[r0]     // Catch:{ NameNotFoundException -> 0x0079, CertificateException -> 0x007d }
            r4 = r3
        L_0x0017:
            int r0 = r5.length     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            if (r4 >= r0) goto L_0x003b
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            r7 = r5[r4]     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            byte[] r7 = r7.toByteArray()     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            r6.<init>(r7)     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            java.security.cert.Certificate r0 = r0.generateCertificate(r6)     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            java.lang.String r0 = a(r0)     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            r1[r4] = r0     // Catch:{ NameNotFoundException -> 0x008d, CertificateException -> 0x008b }
            int r0 = r4 + 1
            r4 = r0
            goto L_0x0017
        L_0x003b:
            r0 = r1
        L_0x003c:
            r4 = r0
        L_0x003d:
            if (r4 == 0) goto L_0x008a
            int r0 = r4.length
            if (r0 <= 0) goto L_0x008a
            int r0 = r4.length
            java.lang.String[] r2 = new java.lang.String[r0]
            r0 = r3
        L_0x0046:
            int r1 = r4.length
            if (r0 >= r1) goto L_0x008a
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            r1 = r3
        L_0x004f:
            r6 = r4[r0]
            int r6 = r6.length()
            if (r1 >= r6) goto L_0x0081
            r6 = r4[r0]
            char r6 = r6.charAt(r1)
            r5.append(r6)
            if (r1 <= 0) goto L_0x0076
            int r6 = r1 % 2
            r7 = 1
            if (r6 != r7) goto L_0x0076
            r6 = r4[r0]
            int r6 = r6.length()
            int r6 = r6 + -1
            if (r1 >= r6) goto L_0x0076
            java.lang.String r6 = ":"
            r5.append(r6)
        L_0x0076:
            int r1 = r1 + 1
            goto L_0x004f
        L_0x0079:
            r0 = move-exception
            r1 = r2
        L_0x007b:
            r4 = r1
            goto L_0x003d
        L_0x007d:
            r0 = move-exception
            r1 = r2
        L_0x007f:
            r4 = r1
            goto L_0x003d
        L_0x0081:
            java.lang.String r1 = r5.toString()
            r2[r0] = r1
            int r0 = r0 + 1
            goto L_0x0046
        L_0x008a:
            return r2
        L_0x008b:
            r0 = move-exception
            goto L_0x007f
        L_0x008d:
            r0 = move-exception
            goto L_0x007b
        L_0x008f:
            r0 = r2
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.b.a.e.b(android.content.Context, java.lang.String):java.lang.String[]");
    }

    static String c(Context context) {
        String str = null;
        if (0 == 0 || "".equals(null)) {
            str = context.getSharedPreferences(MidEntity.TAG_MAC, 0).getString("macaddr", null);
            if (str == null) {
                String d = d(context);
                if (d != null) {
                    str = Base64.encodeToString(d.getBytes(), 0);
                    if (!TextUtils.isEmpty(str)) {
                        context.getSharedPreferences(MidEntity.TAG_MAC, 0).edit().putString("macaddr", str).commit();
                    }
                } else {
                    str = "";
                }
            }
        }
        if (d.a) {
            d.a("getMacID mac_adress: " + str);
        }
        return str;
    }

    private static boolean c(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) != -1;
        if (d.a) {
            d.a("hasPermission " + z + " | " + str);
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String d(android.content.Context r7) {
        /*
            r1 = 0
            java.lang.String r0 = "android.permission.ACCESS_WIFI_STATE"
            boolean r0 = c(r7, r0)     // Catch:{ Exception -> 0x0051 }
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r7.getSystemService(r0)     // Catch:{ Exception -> 0x0051 }
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch:{ Exception -> 0x0051 }
            android.net.wifi.WifiInfo r2 = r0.getConnectionInfo()     // Catch:{ Exception -> 0x0051 }
            java.lang.String r0 = r2.getMacAddress()     // Catch:{ Exception -> 0x0051 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0061 }
            if (r1 != 0) goto L_0x0027
            byte[] r1 = r0.getBytes()     // Catch:{ Exception -> 0x0061 }
            r3 = 0
            android.util.Base64.encode(r1, r3)     // Catch:{ Exception -> 0x0061 }
        L_0x0027:
            boolean r1 = com.baidu.b.a.d.a     // Catch:{ Exception -> 0x0061 }
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = "ssid=%s mac=%s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0061 }
            r4 = 0
            java.lang.String r5 = r2.getSSID()     // Catch:{ Exception -> 0x0061 }
            r3[r4] = r5     // Catch:{ Exception -> 0x0061 }
            r4 = 1
            java.lang.String r2 = r2.getMacAddress()     // Catch:{ Exception -> 0x0061 }
            r3[r4] = r2     // Catch:{ Exception -> 0x0061 }
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch:{ Exception -> 0x0061 }
            com.baidu.b.a.d.a(r1)     // Catch:{ Exception -> 0x0061 }
        L_0x0045:
            return r0
        L_0x0046:
            boolean r0 = com.baidu.b.a.d.a     // Catch:{ Exception -> 0x0051 }
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = "You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE"
            com.baidu.b.a.d.a(r0)     // Catch:{ Exception -> 0x0051 }
        L_0x004f:
            r0 = r1
            goto L_0x0045
        L_0x0051:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x0055:
            boolean r2 = com.baidu.b.a.d.a
            if (r2 == 0) goto L_0x0045
            java.lang.String r1 = r1.toString()
            com.baidu.b.a.d.a(r1)
            goto L_0x0045
        L_0x0061:
            r1 = move-exception
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.b.a.e.d(android.content.Context):java.lang.String");
    }
}
