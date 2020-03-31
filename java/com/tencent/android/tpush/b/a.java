package com.tencent.android.tpush.b;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public class a {
    public static boolean a = false;
    public static String b = ("tencent" + File.separator + Constants.LogTag + File.separator + "Logs");
    protected static volatile ExecutorService c = Executors.newSingleThreadExecutor(new C0057a());
    public static AtomicInteger d = new AtomicInteger();
    public static AtomicInteger e = new AtomicInteger();
    public static AtomicInteger f = new AtomicInteger();
    public static AtomicInteger g = new AtomicInteger();
    public static AtomicInteger h = new AtomicInteger();
    public static AtomicInteger i = new AtomicInteger();
    public static AtomicInteger j = new AtomicInteger();
    public static AtomicInteger k = new AtomicInteger();
    public static AtomicInteger l = new AtomicInteger();
    public static AtomicInteger m = new AtomicInteger();
    public static AtomicInteger n = new AtomicInteger();
    private static boolean o = false;
    private static final SimpleDateFormat p = new SimpleDateFormat("MM.dd_HH:mm:ss_SSS");

    /* renamed from: q reason: collision with root package name */
    private static List<String> f428q = Collections.synchronizedList(new ArrayList());
    private static boolean r = false;
    private static boolean s = false;
    private static String t = null;

    /* renamed from: com.tencent.android.tpush.b.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static class C0057a implements ThreadFactory {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(10);
            return thread;
        }
    }

    public static void a(int i2) {
        switch (i2) {
            case 0:
                a = true;
                return;
            case 1:
                o = true;
                return;
            case 2:
                o = true;
                a = true;
                return;
            case 3:
                o = false;
                a = false;
                return;
            default:
                Log.e("XGLogger", "TLogger ->setLogToFile unknown cmd " + i2);
                return;
        }
    }

    public static boolean a(Context context) {
        return true;
    }

    public static void a(String str, String str2) {
        if (a && b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2);
        }
        a("TRACE", str, str2, null);
    }

    public static void b(String str, String str2) {
        if (b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2);
        }
        a("TRACE", str, str2, null);
    }

    public static void c(String str, String str2) {
        if (a && b(3)) {
            Log.d("XINGE", "[" + str + "] " + str2);
        }
        a("DEBUG", str, str2, null);
    }

    public static void d(String str, String str2) {
        if (b(3)) {
            Log.d("XINGE", "[" + str + "] " + str2);
        }
        a("DEBUG", str, str2, null);
    }

    public static void e(String str, String str2) {
        if (a && b(4)) {
            Log.i("XINGE", "[" + str + "] " + str2);
        }
        a("INFO", str, str2, null);
    }

    public static void f(String str, String str2) {
        if (b(4)) {
            Log.i("XINGE", "[" + str + "] " + str2);
        }
        a("INFO", str, str2, null);
    }

    public static void g(String str, String str2) {
        if (a && b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2);
        }
        a("WARN", str, str2, null);
    }

    public static void h(String str, String str2) {
        if (b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2);
        }
        a("WARN", str, str2, null);
    }

    public static void i(String str, String str2) {
        if (a && b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2);
        }
        a("ERROR", str, str2, null);
    }

    public static void j(String str, String str2) {
        if (b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2);
        }
        a("ERROR", str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a && b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2, th);
        }
        a("TRACE", str, str2, th);
    }

    public static void b(String str, String str2, Throwable th) {
        if (a && b(3)) {
            Log.d("XINGE", "[" + str + "] " + str2, th);
        }
        a("DEBUG", str, str2, th);
    }

    public static void c(String str, String str2, Throwable th) {
        if (a && b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2, th);
        }
        a("WARN", str, str2, th);
    }

    public static void d(String str, String str2, Throwable th) {
        if (a && b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2, th);
        }
        a("ERROR", str, str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        if (b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2, th);
        }
        a("ERROR", str, str2, th);
    }

    private static boolean b(int i2) {
        return true;
    }

    private static void a(String str, String str2, String str3, Throwable th) {
        if (o || a(b.f())) {
            if (str2 == null || str2.trim().equals("")) {
                str2 = "XGLogger";
            }
            String format = p.format(new Date());
            if (str3 == null) {
                str3 = "";
            }
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str3), 256);
            String a2 = i.a("[" + str2 + "]", 24);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    a(format + " " + i.a(str, 5) + " " + a2 + " " + readLine);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                a(format + " " + str + stringWriter.toString());
            }
        }
    }

    private static void a(String str) {
        if (!s) {
            f428q.add(str);
            if (f428q.size() == 100) {
                List<String> list = f428q;
                f428q = Collections.synchronizedList(new ArrayList());
                r = DeviceInfos.isSDCardMounted();
                if (r) {
                    Log.v("XGLogger", "have writable external storage, write log file!");
                    a(list);
                    return;
                }
                Log.v("XGLogger", "no writable external storage");
            }
        }
    }

    /* access modifiers changed from: private */
    public static String c() {
        if (t != null) {
            return t;
        }
        try {
            t = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + b;
            return t;
        } catch (Throwable th) {
            Log.e("XGLogger", "TLogger ->getFileNamePre", th);
            return null;
        }
    }

    private static void a(final List<String> list) {
        if (a(b.f())) {
        }
        if (o) {
            try {
                c.execute(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:48:0x013b A[Catch:{ Exception -> 0x013f }] */
                    /* JADX WARNING: Removed duplicated region for block: B:55:0x0150 A[Catch:{ Exception -> 0x0154 }] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r7 = this;
                            r1 = 0
                            java.lang.String r0 = com.tencent.android.tpush.b.a.c()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            if (r0 != 0) goto L_0x001b
                            java.util.List r0 = r3     // Catch:{ Exception -> 0x0012 }
                            r0.clear()     // Catch:{ Exception -> 0x0012 }
                            if (r1 == 0) goto L_0x0011
                            r1.close()     // Catch:{ Exception -> 0x0012 }
                        L_0x0011:
                            return
                        L_0x0012:
                            r0 = move-exception
                            java.lang.String r1 = "XGLogger"
                            java.lang.String r2 = "close file stream error"
                            android.util.Log.e(r1, r2, r0)
                            goto L_0x0011
                        L_0x001b:
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r2.<init>()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = "log"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r4 = r0.toString()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0.<init>()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = "-"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = com.tencent.android.tpush.service.e.b.a()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = "_1.txt"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r3 = r0.toString()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.io.File r0 = r2.getParentFile()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0.mkdirs()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0 = 2
                            r6 = r0
                            r0 = r2
                            r2 = r3
                            r3 = r6
                        L_0x0066:
                            boolean r5 = r0.exists()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            if (r5 == 0) goto L_0x0165
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0.<init>()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = "-"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = com.tencent.android.tpush.service.e.b.a()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = "_"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = ".txt"
                            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r2 = r0.toString()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.io.File r0 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r5 = 10
                            if (r3 <= r5) goto L_0x010b
                            java.lang.String r3 = "XGLogger"
                            java.lang.String r4 = "Unexpected error here, so many existed error file."
                            android.util.Log.w(r3, r4)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r3 = r2
                        L_0x00a8:
                            java.lang.String r2 = "XGLogger"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r5 = "Write log file: "
                            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r0 = r0.getName()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.lang.String r0 = r0.toString()     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            android.util.Log.v(r2, r0)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.io.FileWriter r0 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0163, Exception -> 0x012c }
                            java.util.List r0 = r3     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.util.Iterator r1 = r0.iterator()     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                        L_0x00d4:
                            boolean r0 = r1.hasNext()     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            if (r0 == 0) goto L_0x010f
                            java.lang.Object r0 = r1.next()     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.lang.String r0 = (java.lang.String) r0     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            r3.<init>()     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.lang.String r3 = "\n"
                            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            java.lang.String r0 = r0.toString()     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            r2.write(r0)     // Catch:{ FileNotFoundException -> 0x00f7, Exception -> 0x0160, all -> 0x015d }
                            goto L_0x00d4
                        L_0x00f7:
                            r0 = move-exception
                            r1 = r2
                        L_0x00f9:
                            r0.printStackTrace()     // Catch:{ all -> 0x0148 }
                            java.util.List r0 = r3     // Catch:{ Exception -> 0x0123 }
                            r0.clear()     // Catch:{ Exception -> 0x0123 }
                            if (r1 == 0) goto L_0x0106
                            r1.close()     // Catch:{ Exception -> 0x0123 }
                        L_0x0106:
                            com.tencent.android.tpush.b.a.d()
                            goto L_0x0011
                        L_0x010b:
                            int r3 = r3 + 1
                            goto L_0x0066
                        L_0x010f:
                            java.util.List r0 = r3     // Catch:{ Exception -> 0x011a }
                            r0.clear()     // Catch:{ Exception -> 0x011a }
                            if (r2 == 0) goto L_0x0106
                            r2.close()     // Catch:{ Exception -> 0x011a }
                            goto L_0x0106
                        L_0x011a:
                            r0 = move-exception
                            java.lang.String r1 = "XGLogger"
                            java.lang.String r2 = "close file stream error"
                            android.util.Log.e(r1, r2, r0)
                            goto L_0x0106
                        L_0x0123:
                            r0 = move-exception
                            java.lang.String r1 = "XGLogger"
                            java.lang.String r2 = "close file stream error"
                            android.util.Log.e(r1, r2, r0)
                            goto L_0x0106
                        L_0x012c:
                            r0 = move-exception
                        L_0x012d:
                            java.lang.String r2 = "XGLogger"
                            java.lang.String r3 = "write logs to file error"
                            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x0148 }
                            java.util.List r0 = r3     // Catch:{ Exception -> 0x013f }
                            r0.clear()     // Catch:{ Exception -> 0x013f }
                            if (r1 == 0) goto L_0x0106
                            r1.close()     // Catch:{ Exception -> 0x013f }
                            goto L_0x0106
                        L_0x013f:
                            r0 = move-exception
                            java.lang.String r1 = "XGLogger"
                            java.lang.String r2 = "close file stream error"
                            android.util.Log.e(r1, r2, r0)
                            goto L_0x0106
                        L_0x0148:
                            r0 = move-exception
                        L_0x0149:
                            java.util.List r2 = r3     // Catch:{ Exception -> 0x0154 }
                            r2.clear()     // Catch:{ Exception -> 0x0154 }
                            if (r1 == 0) goto L_0x0153
                            r1.close()     // Catch:{ Exception -> 0x0154 }
                        L_0x0153:
                            throw r0
                        L_0x0154:
                            r1 = move-exception
                            java.lang.String r2 = "XGLogger"
                            java.lang.String r3 = "close file stream error"
                            android.util.Log.e(r2, r3, r1)
                            goto L_0x0153
                        L_0x015d:
                            r0 = move-exception
                            r1 = r2
                            goto L_0x0149
                        L_0x0160:
                            r0 = move-exception
                            r1 = r2
                            goto L_0x012d
                        L_0x0163:
                            r0 = move-exception
                            goto L_0x00f9
                        L_0x0165:
                            r3 = r2
                            goto L_0x00a8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.a.AnonymousClass1.run():void");
                    }
                });
            } catch (Exception e2) {
                Log.e("XGLogger", "savelog error", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d() {
        /*
            java.lang.String r0 = c()     // Catch:{ Throwable -> 0x008c }
            if (r0 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x008c }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x008c }
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x008c }
            if (r2 == 0) goto L_0x0006
            int r0 = r0.length()     // Catch:{ Throwable -> 0x008c }
            int r2 = r0 + 5
            java.lang.String r0 = com.tencent.android.tpush.service.e.b.a     // Catch:{ Throwable -> 0x008c }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x008c }
            int r3 = r2 + r0
            if (r1 == 0) goto L_0x0006
            java.io.File[] r0 = r1.listFiles()     // Catch:{ Throwable -> 0x008c }
            if (r0 == 0) goto L_0x0006
            java.io.File[] r0 = r1.listFiles()     // Catch:{ Throwable -> 0x008c }
            int r0 = r0.length     // Catch:{ Throwable -> 0x008c }
            if (r0 <= 0) goto L_0x0006
            java.io.File[] r4 = r1.listFiles()     // Catch:{ Throwable -> 0x008c }
            int r5 = r4.length     // Catch:{ Throwable -> 0x008c }
            r0 = 0
            r1 = r0
        L_0x0036:
            if (r1 >= r5) goto L_0x0006
            r0 = r4[r1]     // Catch:{ Throwable -> 0x008c }
            boolean r6 = r0.isFile()     // Catch:{ Throwable -> 0x0072 }
            if (r6 == 0) goto L_0x006e
            java.lang.String r6 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r7 = r6.substring(r2, r3)     // Catch:{ Throwable -> 0x0072 }
            java.util.Date r7 = com.tencent.android.tpush.service.e.b.a(r7)     // Catch:{ Throwable -> 0x0072 }
            r8 = 7
            boolean r7 = com.tencent.android.tpush.service.e.b.a(r7, r8)     // Catch:{ Throwable -> 0x0072 }
            if (r7 == 0) goto L_0x006e
            java.lang.String r7 = "XGLogger"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0072 }
            r8.<init>()     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r9 = "delete logs file "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x0072 }
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0072 }
            android.util.Log.d(r7, r6)     // Catch:{ Throwable -> 0x0072 }
            r0.delete()     // Catch:{ Throwable -> 0x0072 }
        L_0x006e:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0036
        L_0x0072:
            r0 = move-exception
            java.lang.String r6 = "XGLogger"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008c }
            r7.<init>()     // Catch:{ Throwable -> 0x008c }
            java.lang.String r8 = "removeOldDebugLogFiles"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x008c }
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch:{ Throwable -> 0x008c }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x008c }
            android.util.Log.e(r6, r0)     // Catch:{ Throwable -> 0x008c }
            goto L_0x006e
        L_0x008c:
            r0 = move-exception
            java.lang.String r1 = "XGLogger"
            java.lang.String r2 = "removeOldDebugLogFiles"
            android.util.Log.e(r1, r2, r0)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.a.d():void");
    }

    public static void a(int i2, List<TpnsPushClientReport> list) {
        if (o) {
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (TpnsPushClientReport tpnsPushClientReport : list) {
                    arrayList.add(Long.valueOf(tpnsPushClientReport.msgId));
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                c(i2, (List<Long>) arrayList);
            }
        }
    }

    public static void b(int i2, List<TpnsPushMsg> list) {
        if (o) {
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (TpnsPushMsg tpnsPushMsg : list) {
                    arrayList.add(Long.valueOf(tpnsPushMsg.msgId));
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                c(i2, (List<Long>) arrayList);
            }
        }
    }

    public static void a(int i2, long j2) {
        if (o) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(j2));
            if (arrayList != null && arrayList.size() > 0) {
                c(i2, (List<Long>) arrayList);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b8 A[SYNTHETIC, Splitter:B:32:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01f0 A[SYNTHETIC, Splitter:B:54:0x01f0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void c(int r8, java.util.List<java.lang.Long> r9) {
        /*
            java.lang.Class<com.tencent.android.tpush.b.a> r4 = com.tencent.android.tpush.b.a.class
            monitor-enter(r4)
            boolean r0 = o     // Catch:{ all -> 0x01f4 }
            if (r0 != 0) goto L_0x0009
        L_0x0007:
            monitor-exit(r4)
            return
        L_0x0009:
            r1 = 0
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = "yyyy-MM-dd HH:mm:ss"
            r5.<init>(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r0.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "/"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            switch(r8) {
                case 0: goto L_0x003a;
                case 1: goto L_0x00c0;
                case 2: goto L_0x00dd;
                case 3: goto L_0x00fa;
                case 4: goto L_0x0117;
                case 5: goto L_0x0134;
                case 6: goto L_0x0151;
                case 7: goto L_0x016e;
                case 8: goto L_0x018b;
                case 9: goto L_0x002b;
                case 10: goto L_0x002b;
                case 11: goto L_0x01a8;
                case 12: goto L_0x01c5;
                default: goto L_0x002b;
            }     // Catch:{ Throwable -> 0x01fc }
        L_0x002b:
            java.lang.String r0 = "XGLogger"
            java.lang.String r2 = "unknown case"
            i(r0, r2)     // Catch:{ Throwable -> 0x01fc }
            if (r1 == 0) goto L_0x0007
            r1.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0007
        L_0x0038:
            r0 = move-exception
            goto L_0x0007
        L_0x003a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_0ServerSendToService.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = d     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
        L_0x0055:
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r2 = com.tencent.android.tpush.common.f.a(r2)     // Catch:{ Throwable -> 0x01fc }
            if (r2 == 0) goto L_0x01e2
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ Throwable -> 0x01fc }
            r6 = 1
            r2.<init>(r0, r6)     // Catch:{ Throwable -> 0x01fc }
            java.util.Iterator r1 = r9.iterator()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
        L_0x0067:
            boolean r0 = r1.hasNext()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            if (r0 == 0) goto L_0x01e3
            java.lang.Object r0 = r1.next()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            r6.<init>()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r7 = ""
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r7 = "\t"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.util.Date r7 = new java.util.Date     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            r7.<init>()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r7 = r5.format(r7)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r7 = "\tmsgid: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r6 = "\n"
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            r2.write(r0)     // Catch:{ Throwable -> 0x00ad, all -> 0x01f9 }
            goto L_0x0067
        L_0x00ad:
            r0 = move-exception
            r1 = r2
        L_0x00af:
            java.lang.String r2 = "XGLogger"
            java.lang.String r3 = "writeMsgSession error"
            d(r2, r3, r0)     // Catch:{ all -> 0x01ed }
            if (r1 == 0) goto L_0x0007
            r1.close()     // Catch:{ IOException -> 0x00bd }
            goto L_0x0007
        L_0x00bd:
            r0 = move-exception
            goto L_0x0007
        L_0x00c0:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_1ServiceAckToServer.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = e     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x00dd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_2XgSdkReceiveFromXGService.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = f     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x00fa:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_3SdkSendAckToService.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = g     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x0117:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_4ServiceRecAckFromSdk1.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = h     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x0134:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_5ServiceRecAckFromSdk2.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = i     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x0151:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_6ServiceRecAckFromSdk3.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = j     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x016e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_7ServiceRecAckFromServer.txt"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = k     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x018b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_8ServiceRecAckFromServer_failed"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = l     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x01a8:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_11unequal"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = m     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x01c5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01fc }
            r2.<init>()     // Catch:{ Throwable -> 0x01fc }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = "_12notList"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Throwable -> 0x01fc }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01fc }
            java.util.concurrent.atomic.AtomicInteger r0 = n     // Catch:{ Throwable -> 0x01fc }
            int r0 = r0.getAndIncrement()     // Catch:{ Throwable -> 0x01fc }
            r3 = r0
            r0 = r2
            goto L_0x0055
        L_0x01e2:
            r2 = r1
        L_0x01e3:
            if (r2 == 0) goto L_0x0007
            r2.close()     // Catch:{ IOException -> 0x01ea }
            goto L_0x0007
        L_0x01ea:
            r0 = move-exception
            goto L_0x0007
        L_0x01ed:
            r0 = move-exception
        L_0x01ee:
            if (r1 == 0) goto L_0x01f3
            r1.close()     // Catch:{ IOException -> 0x01f7 }
        L_0x01f3:
            throw r0     // Catch:{ all -> 0x01f4 }
        L_0x01f4:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x01f7:
            r1 = move-exception
            goto L_0x01f3
        L_0x01f9:
            r0 = move-exception
            r1 = r2
            goto L_0x01ee
        L_0x01fc:
            r0 = move-exception
            goto L_0x00af
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.a.c(int, java.util.List):void");
    }
}
