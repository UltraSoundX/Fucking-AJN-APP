package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.ay.b;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

class x {
    /* access modifiers changed from: private */
    public static volatile DexClassLoader a;
    /* access modifiers changed from: private */
    public static volatile boolean b = false;

    static class a extends Thread {
        private Context a;
        private a b;

        public a(Context context, a aVar) {
            this.a = context;
            this.b = aVar;
        }

        public void run() {
            try {
                int i = aa.a ? 3 : 10;
                al.c().a("start version check in " + i + "s");
                sleep((long) (i * 1000));
                a();
                a(this.a);
            } catch (Exception e) {
                al.c().a((Throwable) e);
            }
            x.b = false;
        }

        private void a(Context context) {
            this.b.a(context, System.currentTimeMillis());
        }

        private synchronized void a() throws Exception {
            String headerField;
            String headerField2;
            al.c().a("start get config and download jar");
            Context context = this.a;
            a aVar = this.b;
            String b2 = b(context);
            al.c().c("update req url is:" + b2);
            HttpURLConnection d = at.d(context, b2);
            try {
                d.connect();
                headerField = d.getHeaderField("X-CONFIG");
                al.c().a("config is: " + headerField);
                headerField2 = d.getHeaderField("X-SIGN");
                al.c().a("sign is: " + headerField2);
                int responseCode = d.getResponseCode();
                al.c().a("update response code is: " + responseCode);
                int contentLength = d.getContentLength();
                al.c().a("update response content length is: " + contentLength);
                if (responseCode == 200 && contentLength > 0) {
                    FileOutputStream openFileOutput = context.openFileOutput(".remote.jar", 0);
                    if (az.a(d.getInputStream(), openFileOutput)) {
                        al.c().a("save remote jar success");
                    }
                    az.a(openFileOutput);
                }
            } catch (IOException e) {
                al.c().b((Throwable) e);
                az.a(null);
            } catch (Throwable th) {
                d.disconnect();
                throw th;
            }
            x.a = null;
            u.a();
            if (!TextUtils.isEmpty(headerField)) {
                aVar.a(context, headerField);
            }
            if (!TextUtils.isEmpty(headerField2)) {
                aVar.b(context, headerField2);
            }
            d.disconnect();
            al.c().a("finish get config and download jar");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x003e, code lost:
            if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0040;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String b(android.content.Context r7) {
            /*
                r6 = this;
                java.lang.String r0 = ".remote.jar"
                java.io.File r0 = r7.getFileStreamPath(r0)
                java.lang.String r1 = "33"
                if (r0 == 0) goto L_0x0172
                boolean r0 = r0.exists()
                if (r0 == 0) goto L_0x0172
                java.lang.String r0 = ".remote.jar"
                java.io.File r0 = r7.getFileStreamPath(r0)
                if (r0 == 0) goto L_0x0172
                java.lang.String r0 = r0.getAbsolutePath()
                java.lang.String r0 = com.baidu.mobstat.x.b(r0)
                com.baidu.mobstat.al r2 = com.baidu.mobstat.al.c()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "startDownload remote jar file version = "
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.StringBuilder r3 = r3.append(r0)
                java.lang.String r3 = r3.toString()
                r2.a(r3)
                boolean r2 = android.text.TextUtils.isEmpty(r0)
                if (r2 != 0) goto L_0x0172
            L_0x0040:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                android.util.Pair r2 = new android.util.Pair
                java.lang.String r3 = "dynamicVersion"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = ""
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r0 = r4.append(r0)
                java.lang.String r0 = r0.toString()
                r2.<init>(r3, r0)
                r1.add(r2)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "packageName"
                java.lang.String r3 = com.baidu.mobstat.bb.r(r7)
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "appVersion"
                java.lang.String r3 = com.baidu.mobstat.bb.g(r7)
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "cuid"
                java.lang.String r3 = com.baidu.mobstat.bb.a(r7)
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "platform"
                java.lang.String r3 = "Android"
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "m"
                java.lang.String r3 = android.os.Build.MODEL
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "s"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                int r4 = android.os.Build.VERSION.SDK_INT
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r4 = ""
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "o"
                java.lang.String r3 = android.os.Build.VERSION.RELEASE
                r0.<init>(r2, r3)
                r1.add(r0)
                android.util.Pair r0 = new android.util.Pair
                java.lang.String r2 = "i"
                java.lang.String r3 = "33"
                r0.<init>(r2, r3)
                r1.add(r0)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.util.Iterator r3 = r1.iterator()
            L_0x00e4:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x0154
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                java.lang.Object r1 = r0.first     // Catch:{ Exception -> 0x0131 }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0131 }
                java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0131 }
                java.lang.String r4 = "UTF-8"
                java.lang.String r1 = java.net.URLEncoder.encode(r1, r4)     // Catch:{ Exception -> 0x0131 }
                java.lang.Object r0 = r0.second     // Catch:{ Exception -> 0x0131 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0131 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0131 }
                java.lang.String r4 = "UTF-8"
                java.lang.String r0 = java.net.URLEncoder.encode(r0, r4)     // Catch:{ Exception -> 0x0131 }
                java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0131 }
                boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0131 }
                if (r4 == 0) goto L_0x0133
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0131 }
                r4.<init>()     // Catch:{ Exception -> 0x0131 }
                java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ Exception -> 0x0131 }
                java.lang.String r4 = "="
                java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x0131 }
                java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Exception -> 0x0131 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0131 }
                r2.append(r0)     // Catch:{ Exception -> 0x0131 }
                goto L_0x00e4
            L_0x0131:
                r0 = move-exception
                goto L_0x00e4
            L_0x0133:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0131 }
                r4.<init>()     // Catch:{ Exception -> 0x0131 }
                java.lang.String r5 = "&"
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x0131 }
                java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ Exception -> 0x0131 }
                java.lang.String r4 = "="
                java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x0131 }
                java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Exception -> 0x0131 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0131 }
                r2.append(r0)     // Catch:{ Exception -> 0x0131 }
                goto L_0x00e4
            L_0x0154:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = com.baidu.mobstat.aa.c
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = "?"
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = r2.toString()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                return r0
            L_0x0172:
                r0 = r1
                goto L_0x0040
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.x.a.b(android.content.Context):java.lang.String");
        }
    }

    public static Class<?> a(Context context, String str) throws ClassNotFoundException {
        DexClassLoader a2 = a(context);
        if (a2 == null) {
            return null;
        }
        return a2.loadClass(str);
    }

    private static synchronized DexClassLoader a(Context context) {
        DexClassLoader dexClassLoader = null;
        synchronized (x.class) {
            if (a != null) {
                dexClassLoader = a;
            } else {
                File fileStreamPath = context.getFileStreamPath(".remote.jar");
                if (fileStreamPath == null || fileStreamPath.isFile()) {
                    if (!b(context, fileStreamPath.getAbsolutePath())) {
                        al.c().a("remote jar version lower than min limit, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    } else if (!c(context, fileStreamPath.getAbsolutePath())) {
                        al.c().a("remote jar md5 is not right, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    } else {
                        try {
                            a = new DexClassLoader(fileStreamPath.getAbsolutePath(), context.getDir("outdex", 0).getAbsolutePath(), null, context.getClassLoader());
                        } catch (Exception e) {
                            al.c().a((Throwable) e);
                        }
                        dexClassLoader = a;
                    }
                }
            }
        }
        return dexClassLoader;
    }

    private static boolean b(Context context, String str) {
        int i;
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            i = Integer.valueOf(b2).intValue();
        } catch (Exception e) {
            al.c().b((Throwable) e);
            i = 0;
        }
        if (i >= 4) {
            return true;
        }
        return false;
    }

    public static synchronized void a(Context context, a aVar) {
        synchronized (x.class) {
            if (!b) {
                if (!bb.p(context)) {
                    al.c().a("isWifiAvailable = false, will not to update");
                } else if (!aVar.a(context)) {
                    al.c().a("check time, will not to update");
                } else {
                    al.c().a("can start update config");
                    new a(context, aVar).start();
                    b = true;
                }
            }
        }
    }

    private static boolean c(Context context, String str) {
        String a2 = b.a(new File(str));
        al.c().a("remote.jar local file digest value digest = " + a2);
        if (TextUtils.isEmpty(a2)) {
            al.c().a("remote.jar local file digest value fail");
            return false;
        }
        String b2 = b(str);
        al.c().a("remote.jar local file digest value version = " + b2);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        String d = d(context, b2);
        al.c().a("remote.jar config digest value remoteJarMd5 = " + d);
        if (!TextUtils.isEmpty(d)) {
            return a2.equals(d);
        }
        al.c().a("remote.jar config digest value lost");
        return false;
    }

    private static String d(Context context, String str) {
        return y.a(context).c(str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070 A[SYNTHETIC, Splitter:B:20:0x0070] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r6) {
        /*
            r1 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0043 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0043 }
            boolean r2 = r0.exists()     // Catch:{ Exception -> 0x0043 }
            if (r2 == 0) goto L_0x002a
            com.baidu.mobstat.al r2 = com.baidu.mobstat.al.c()     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0043 }
            r3.<init>()     // Catch:{ Exception -> 0x0043 }
            java.lang.String r4 = "file size: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0043 }
            long r4 = r0.length()     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r0 = r3.append(r4)     // Catch:{ Exception -> 0x0043 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0043 }
            r2.b(r0)     // Catch:{ Exception -> 0x0043 }
        L_0x002a:
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0043 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0043 }
            java.util.jar.Manifest r0 = r2.getManifest()     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            java.util.jar.Attributes r0 = r0.getMainAttributes()     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            java.lang.String r1 = "Plugin-Version"
            java.lang.String r0 = r0.getValue(r1)     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0042:
            return r0
        L_0x0043:
            r0 = move-exception
        L_0x0044:
            com.baidu.mobstat.al r2 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x006d }
            r2.a(r0)     // Catch:{ all -> 0x006d }
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            r2.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r3 = "baidu remote sdk is not ready"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ all -> 0x006d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x006d }
            r0.a(r2)     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch:{ Exception -> 0x0076 }
        L_0x006a:
            java.lang.String r0 = ""
            goto L_0x0042
        L_0x006d:
            r0 = move-exception
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ Exception -> 0x0078 }
        L_0x0073:
            throw r0
        L_0x0074:
            r1 = move-exception
            goto L_0x0042
        L_0x0076:
            r0 = move-exception
            goto L_0x006a
        L_0x0078:
            r1 = move-exception
            goto L_0x0073
        L_0x007a:
            r0 = move-exception
            r1 = r2
            goto L_0x006e
        L_0x007d:
            r0 = move-exception
            r1 = r2
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.x.b(java.lang.String):java.lang.String");
    }
}
