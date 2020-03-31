package com.mob.commons;

import android.os.Handler;
import android.os.Handler.Callback;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

/* compiled from: DyLogUploader */
public class c implements Callback {
    private static final String a = h.a("dfe.mic.mob.com/drl");
    private static c b;
    private MobCommunicator c;
    private SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
    private HashMap<String, Object> e = new HashMap<>();
    private Handler f;
    private String g = null;
    private int h = -1;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    private c() {
        try {
            this.g = UUID.randomUUID().toString();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        this.f = MobHandlerThread.newHandler("d", (Callback) this);
    }

    private synchronized int b() {
        return this.h;
    }

    public synchronized void a(int i) {
        this.h = i;
        if (i == 1 || i == 4 || i == 17 || i == 18 || i == 19 || i == 20) {
            a(8, null, "ld vr " + i);
        } else if (i == 10 || i == 11 || i == 12) {
        }
    }

    public synchronized void a(int i, Throwable th) {
        a(i, th, null);
    }

    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r9v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(int r7, java.lang.Throwable r8, java.lang.String r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r8 != 0) goto L_0x0015
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x001d }
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x001d }
            r0.d(r9, r1)     // Catch:{ all -> 0x001d }
        L_0x000d:
            boolean r0 = com.mob.commons.a.ad()     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x0020
        L_0x0013:
            monitor-exit(r6)
            return
        L_0x0015:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x001d }
            r0.d(r8)     // Catch:{ all -> 0x001d }
            goto L_0x000d
        L_0x001d:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x0020:
            android.os.Message r0 = new android.os.Message     // Catch:{ all -> 0x001d }
            r0.<init>()     // Catch:{ all -> 0x001d }
            r1 = 1
            r0.what = r1     // Catch:{ all -> 0x001d }
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x001d }
            r2 = 0
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x001d }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x001d }
            r1[r2] = r3     // Catch:{ all -> 0x001d }
            r2 = 1
            if (r8 != 0) goto L_0x0055
        L_0x0039:
            r1[r2] = r9     // Catch:{ all -> 0x001d }
            r2 = 2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x001d }
            r1[r2] = r3     // Catch:{ all -> 0x001d }
            r2 = 3
            int r3 = r6.b()     // Catch:{ all -> 0x001d }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x001d }
            r1[r2] = r3     // Catch:{ all -> 0x001d }
            r0.obj = r1     // Catch:{ all -> 0x001d }
            android.os.Handler r1 = r6.f     // Catch:{ all -> 0x001d }
            r1.sendMessage(r0)     // Catch:{ all -> 0x001d }
            goto L_0x0013
        L_0x0055:
            r9 = r8
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.c.a(int, java.lang.Throwable, java.lang.String):void");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleMessage(android.os.Message r10) {
        /*
            r9 = this;
            r8 = 0
            int r0 = r10.what     // Catch:{ Throwable -> 0x0011 }
            switch(r0) {
                case 1: goto L_0x001a;
                case 2: goto L_0x0007;
                default: goto L_0x0006;
            }     // Catch:{ Throwable -> 0x0011 }
        L_0x0006:
            return r8
        L_0x0007:
            android.os.Handler r0 = r9.f     // Catch:{ Throwable -> 0x0011 }
            r1 = 2
            r0.removeMessages(r1)     // Catch:{ Throwable -> 0x0011 }
            r9.d()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0006
        L_0x0011:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
            goto L_0x0006
        L_0x001a:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r9.e     // Catch:{ Throwable -> 0x0011 }
            int r0 = r0.size()     // Catch:{ Throwable -> 0x0011 }
            r1 = 10
            if (r0 <= r1) goto L_0x002e
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r9.e     // Catch:{ Throwable -> 0x0011 }
            r9.c(r0)     // Catch:{ Throwable -> 0x0011 }
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r9.e     // Catch:{ Throwable -> 0x0011 }
            r0.clear()     // Catch:{ Throwable -> 0x0011 }
        L_0x002e:
            java.lang.Object r0 = r10.obj     // Catch:{ Throwable -> 0x0011 }
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ Throwable -> 0x0011 }
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ Throwable -> 0x0011 }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r9.e     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r2 = "sd"
            java.lang.String r3 = r9.g     // Catch:{ Throwable -> 0x0011 }
            r1.put(r2, r3)     // Catch:{ Throwable -> 0x0011 }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r9.e     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r2 = "list"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ Throwable -> 0x0011 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x0011 }
            if (r1 != 0) goto L_0x00d8
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0011 }
            r1.<init>()     // Catch:{ Throwable -> 0x0011 }
            r2 = r1
        L_0x004f:
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Throwable -> 0x0011 }
            r3.<init>()     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = "ct"
            r4 = 0
            r4 = r0[r4]     // Catch:{ Throwable -> 0x0011 }
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x0011 }
            r1 = 1
            r1 = r0[r1]     // Catch:{ Throwable -> 0x0011 }
            boolean r1 = r1 instanceof java.lang.Throwable     // Catch:{ Throwable -> 0x0011 }
            if (r1 == 0) goto L_0x00d0
            r1 = 1
            r1 = r0[r1]     // Catch:{ Throwable -> 0x0011 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = r9.a(r1)     // Catch:{ Throwable -> 0x0011 }
        L_0x006c:
            java.lang.String r4 = "mg"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0011 }
            r5.<init>()     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r6 = "["
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            java.text.SimpleDateFormat r6 = r9.d     // Catch:{ Throwable -> 0x0011 }
            r7 = 0
            r7 = r0[r7]     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r6 = r6.format(r7)     // Catch:{ Throwable -> 0x0011 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r6 = "]["
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            r6 = 2
            r6 = r0[r6]     // Catch:{ Throwable -> 0x0011 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r6 = "]["
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            r6 = 3
            r6 = r0[r6]     // Catch:{ Throwable -> 0x0011 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r6 = "] "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0011 }
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0011 }
            r3.put(r4, r1)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = "et"
            r4 = 2
            r4 = r0[r4]     // Catch:{ Throwable -> 0x0011 }
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = "po"
            r4 = 3
            r0 = r0[r4]     // Catch:{ Throwable -> 0x0011 }
            r3.put(r1, r0)     // Catch:{ Throwable -> 0x0011 }
            r2.add(r3)     // Catch:{ Throwable -> 0x0011 }
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r9.e     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = "list"
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0011 }
            r9.c()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0006
        L_0x00d0:
            r1 = 1
            r1 = r0[r1]     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0011 }
            goto L_0x006c
        L_0x00d8:
            r2 = r1
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.c.handleMessage(android.os.Message):boolean");
    }

    private void c() {
        long j;
        if (!a.ad()) {
            if (!f()) {
                j = 120000;
            } else {
                j = 0;
            }
            if (j > 0) {
                this.f.sendEmptyMessageDelayed(2, j);
            } else {
                this.f.sendEmptyMessageDelayed(2, OkHttpUtils.DEFAULT_MILLISECONDS);
            }
        }
    }

    private void d() {
        boolean z = true;
        if (this.e.size() > 0) {
            z = a(this.e);
            if (!z) {
                c(this.e);
            }
            this.e.clear();
        }
        if (z) {
            File g2 = g();
            if (g2.exists() && g2.isDirectory()) {
                File[] listFiles = g2.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        if (a((HashMap) ResHelper.readObjectFromFile(file.getAbsolutePath())) && !file.delete()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    private boolean a(HashMap<String, Object> hashMap) {
        try {
            return b(hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }

    private boolean b(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap == null || hashMap.isEmpty()) {
            return true;
        }
        HashMap af = a.af();
        af.put("errors", hashMap);
        e();
        HashMap hashMap2 = (HashMap) this.c.requestSynchronized(af, a, false);
        return hashMap2 == null || hashMap2.isEmpty();
    }

    private void e() {
        if (this.c == null) {
            this.c = new MobCommunicator(1024, "ab0a0a6473d1891d388773574764b239d4ad80cb2fd3a83d81d03901c1548c13fee7c9692c326e6682b239d4c5d0021d1b607642c47ec29f10b0602908c3e6c9", "23c3c8cb41c47dd288cc7f4c218fbc7c839a34e0a0d1b2130e87b7914936b120a2d6570ee7ac66282328d50f2acfd82f2259957c89baea32547758db05de9cd7c6822304c8e45742f24bbbe41c1e12f09e18c6fab4d078065f2e5aaed94c900c66e8bbf8a120eefa7bd1fb52114d529250084f5f6f369ed4ce9645978dd30c51");
        }
    }

    private boolean f() {
        String networkType = DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType();
        return networkType != null && !"none".equals(networkType);
    }

    private String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = null;
        Throwable th2 = th;
        while (th2 != null) {
            try {
                if (th2 instanceof UnknownHostException) {
                    String str = "";
                    if (stringWriter == null) {
                        return str;
                    }
                    try {
                        stringWriter.close();
                        return str;
                    } catch (Throwable th3) {
                        return str;
                    }
                } else {
                    th2 = th2.getCause();
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    if (th instanceof OutOfMemoryError) {
                        String str2 = "getStackTraceString oom";
                        if (stringWriter == null) {
                            return str2;
                        }
                        try {
                            stringWriter.close();
                            return str2;
                        } catch (Throwable th5) {
                            return str2;
                        }
                    } else if (th != null) {
                        String message = th.getMessage();
                        if (stringWriter == null) {
                            return message;
                        }
                        try {
                            stringWriter.close();
                            return message;
                        } catch (Throwable th6) {
                            return message;
                        }
                    } else {
                        if (stringWriter != null) {
                            try {
                                stringWriter.close();
                            } catch (Throwable th7) {
                            }
                        }
                        return "";
                    }
                } catch (Throwable th8) {
                    th = th8;
                }
            }
        }
        StringWriter stringWriter2 = new StringWriter();
        try {
            PrintWriter printWriter = new PrintWriter(stringWriter2);
            th.printStackTrace(printWriter);
            printWriter.flush();
            String stringWriter3 = stringWriter2.toString();
            if (stringWriter2 == null) {
                return stringWriter3;
            }
            try {
                stringWriter2.close();
                return stringWriter3;
            } catch (Throwable th9) {
                return stringWriter3;
            }
        } catch (Throwable th10) {
            th = th10;
            stringWriter = stringWriter2;
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (Throwable th11) {
                }
            }
            throw th;
        }
    }

    private File g() {
        return new File(ResHelper.getDataCache(MobSDK.getContext()), ".dyl");
    }

    private void c(HashMap<String, Object> hashMap) {
        try {
            d(hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private void d(HashMap<String, Object> hashMap) throws Throwable {
        File g2 = g();
        if (!g2.exists() || !g2.isDirectory()) {
            g2.mkdirs();
        }
        File file = new File(g2, ".dyl_0");
        if (file.exists()) {
            File[] listFiles = g2.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                int i = 0;
                file = new File(g2, ".dyl_" + 0);
                while (file.exists()) {
                    i++;
                    file = new File(g2, ".dyl_" + i);
                }
            }
        }
        ResHelper.saveObjectToFile(file.getPath(), hashMap);
    }
}
