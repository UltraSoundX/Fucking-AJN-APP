package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.channel.security.d;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    private static b a = null;
    private static String f = null;
    private static long g = 0;
    private Context b = null;
    private String c = "182.254.116.117";
    private a d = null;
    private int e = ErrorCode.ERROR_CODE_LOAD_BASE;

    /* compiled from: ProGuard */
    static class a implements Runnable {
        private String a;
        private String b;

        public a(String str) {
            this.a = str;
        }

        public void run() {
            try {
                a(b.i(this.a));
            } catch (Exception e) {
            }
        }

        public synchronized void a(String str) {
            this.b = str;
        }

        public synchronized String a() {
            return this.b;
        }
    }

    private b(Context context) {
        this.b = context;
        this.d = new a(context, "tpns.qq.com");
    }

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    public String a(String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("azIoMLoU".getBytes("utf-8"), "DES");
            Cipher instance = Cipher.getInstance("DES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(d.b(str)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public synchronized String a() {
        String str;
        try {
            str = this.d.b();
            if (!b(str)) {
                str = c("tpns.qq.com");
            }
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        return str;
    }

    public static boolean b(String str) {
        if (str == null || str.length() < 7 || str.length() > 15 || "".equals(str)) {
            return false;
        }
        return Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}").matcher(str).find();
    }

    private String h(String str) {
        return "http://182.254.116.117/d?dn=99e2d153e4d0527186ebed5ac5608367&id=6&ttl=1";
    }

    public synchronized String c(String str) {
        return e(str);
    }

    public String d(String str) {
        String trim = str.trim();
        if (trim.length() < 8) {
            return null;
        }
        String a2 = a(trim);
        ArrayList arrayList = new ArrayList();
        if (b(a2)) {
            arrayList.add(a2);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ips", a2);
                jSONObject.put(MessageKey.MSG_TTL, ErrorCode.ERROR_CODE_LOAD_BASE);
                jSONObject.put("exp", System.currentTimeMillis() + 300000);
                this.d.a(jSONObject);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            int indexOf = a2.indexOf(44);
            if (indexOf > 8) {
                String substring = a2.substring(indexOf + 1, a2.length());
                if (substring != null && substring.trim().length() > 0) {
                    this.e = Integer.valueOf(substring).intValue();
                    if (this.e < 10) {
                        this.e = ErrorCode.ERROR_CODE_LOAD_BASE;
                    }
                }
                com.tencent.android.tpush.b.a.c("httpDns", "ttl:" + substring + StorageInterface.KEY_SPLITER + this.e);
            }
            try {
                String substring2 = a2.substring(0, indexOf);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("ips", substring2);
                jSONObject2.put(MessageKey.MSG_TTL, this.e);
                jSONObject2.put("exp", System.currentTimeMillis() + ((long) (this.e * 1000)));
                this.d.a(jSONObject2);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return this.d.b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[SYNTHETIC, Splitter:B:27:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0070 A[SYNTHETIC, Splitter:B:36:0x0070] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String e(java.lang.String r8) {
        /*
            r7 = this;
            r1 = 0
            monitor-enter(r7)
            java.lang.String r0 = r7.h(r8)     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            r2.<init>(r0)     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            java.net.URLConnection r0 = r2.openConnection()     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            r2 = 3000(0xbb8, float:4.204E-42)
            r0.setConnectTimeout(r2)     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            r3.<init>(r0)     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005b, all -> 0x006c }
            r0 = r1
        L_0x0023:
            java.lang.String r3 = r2.readLine()     // Catch:{ Exception -> 0x007b }
            if (r3 == 0) goto L_0x004c
            java.lang.String r4 = "httpDns"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007b }
            r5.<init>()     // Catch:{ Exception -> 0x007b }
            java.lang.String r6 = "getAddrByName line:"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x007b }
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch:{ Exception -> 0x007b }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x007b }
            com.tencent.android.tpush.b.a.c(r4, r5)     // Catch:{ Exception -> 0x007b }
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x007b }
            if (r4 == 0) goto L_0x0023
            java.lang.String r0 = r7.d(r3)     // Catch:{ Exception -> 0x007b }
            goto L_0x0023
        L_0x004c:
            if (r2 == 0) goto L_0x0051
            r2.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0051:
            monitor-exit(r7)
            return r0
        L_0x0053:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0058 }
            goto L_0x0051
        L_0x0058:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x005b:
            r0 = move-exception
            r2 = r1
        L_0x005d:
            r0.printStackTrace()     // Catch:{ all -> 0x0079 }
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0065:
            r0 = r1
            goto L_0x0051
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0058 }
            goto L_0x0065
        L_0x006c:
            r0 = move-exception
            r2 = r1
        L_0x006e:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ IOException -> 0x0074 }
        L_0x0073:
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0074:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0058 }
            goto L_0x0073
        L_0x0079:
            r0 = move-exception
            goto L_0x006e
        L_0x007b:
            r0 = move-exception
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.b.b.e(java.lang.String):java.lang.String");
    }

    public synchronized boolean b() {
        return f("tpns.qq.com");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a A[SYNTHETIC, Splitter:B:40:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0087 A[SYNTHETIC, Splitter:B:48:0x0087] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean f(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            monitor-enter(r6)
            r3 = 0
            java.lang.String r1 = r6.h(r7)     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.net.URLConnection r1 = r2.openConnection()     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            r2 = 3000(0xbb8, float:4.204E-42)
            r1.setConnectTimeout(r2)     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0073, all -> 0x0083 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Exception -> 0x0092 }
            if (r1 == 0) goto L_0x0068
            java.lang.String r3 = "httpDns"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0092 }
            r4.<init>()     // Catch:{ Exception -> 0x0092 }
            java.lang.String r5 = "getAddrByName line:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x0092 }
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ Exception -> 0x0092 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0092 }
            com.tencent.android.tpush.b.a.c(r3, r4)     // Catch:{ Exception -> 0x0092 }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0092 }
            int r1 = r1.length()     // Catch:{ Exception -> 0x0092 }
            r3 = 8
            if (r1 >= r3) goto L_0x005c
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0052:
            monitor-exit(r6)
            return r0
        L_0x0054:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x0052
        L_0x0059:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x005c:
            r0 = 1
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0052
        L_0x0063:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x0052
        L_0x0068:
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0052
        L_0x006e:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x0052
        L_0x0073:
            r1 = move-exception
            r2 = r3
        L_0x0075:
            r1.printStackTrace()     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0052
        L_0x007e:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x0052
        L_0x0083:
            r0 = move-exception
            r2 = r3
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ IOException -> 0x008b }
        L_0x008a:
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x008b:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x008a
        L_0x0090:
            r0 = move-exception
            goto L_0x0085
        L_0x0092:
            r1 = move-exception
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.b.b.f(java.lang.String):boolean");
    }

    public static synchronized String c() {
        String a2;
        synchronized (b.class) {
            if (Math.abs(System.currentTimeMillis() - g) >= 600000 || l.c(f)) {
                a aVar = new a("tpns.qq.com");
                Thread thread = new Thread(aVar);
                thread.start();
                try {
                    thread.join(4000);
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.d("httpDns", "t.join", e2);
                }
                a2 = aVar.a();
                com.tencent.android.tpush.b.a.i("httpDns", "DNS tpns.qq.com -> " + a2);
                if (l.c(a2)) {
                    a2 = d();
                } else {
                    f = a2;
                    g = System.currentTimeMillis();
                }
            } else {
                com.tencent.android.tpush.b.a.i("httpDns", "Use the cached DNS tpns.qq.com -> " + f);
                a2 = f;
            }
        }
        return a2;
    }

    /* access modifiers changed from: private */
    public static String i(String str) {
        InetAddress inetAddress;
        if (l.c(str)) {
            return null;
        }
        try {
            System.nanoTime();
            long nanoTime = System.nanoTime();
            InetAddress[] allByName = InetAddress.getAllByName(str);
            long nanoTime2 = System.nanoTime();
            if (allByName == null || allByName.length <= 0) {
                inetAddress = null;
            } else {
                inetAddress = allByName[0];
                com.tencent.android.tpush.b.a.i("httpDns", "DNS " + str + " -> " + inetAddress + " in " + ((nanoTime2 - nanoTime) / 1000000) + "ms");
            }
            if (inetAddress != null) {
                return inetAddress.getHostAddress();
            }
            return null;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("httpDns", "NSLookup error: ", th);
            return null;
        }
    }

    private static String d() {
        if (new Random().nextInt(1) == 0) {
            return "203.205.179.220";
        }
        return "203.205.179.210";
    }
}
