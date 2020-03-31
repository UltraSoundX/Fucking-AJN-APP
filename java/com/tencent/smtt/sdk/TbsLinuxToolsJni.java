package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;

class TbsLinuxToolsJni {
    private static boolean a = false;
    private static boolean b = false;

    private native int ChmodInner(String str, String str2);

    public int a(String str, String str2) {
        if (a) {
            return ChmodInner(str, str2);
        }
        TbsLog.e("TbsLinuxToolsJni", "jni not loaded!", true);
        return -1;
    }

    public TbsLinuxToolsJni(Context context) {
        a(context);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.Class<com.tencent.smtt.sdk.TbsLinuxToolsJni> r2 = com.tencent.smtt.sdk.TbsLinuxToolsJni.class
            monitor-enter(r2)
            java.lang.String r0 = "TbsLinuxToolsJni"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c1 }
            r1.<init>()     // Catch:{ all -> 0x00c1 }
            java.lang.String r3 = "TbsLinuxToolsJni init mbIsInited is "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x00c1 }
            boolean r3 = b     // Catch:{ all -> 0x00c1 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x00c1 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00c1 }
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x00c1 }
            boolean r0 = b     // Catch:{ all -> 0x00c1 }
            if (r0 == 0) goto L_0x0023
            monitor-exit(r2)     // Catch:{ all -> 0x00c1 }
        L_0x0022:
            return
        L_0x0023:
            r0 = 1
            b = r0     // Catch:{ all -> 0x00c1 }
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)     // Catch:{ Throwable -> 0x00ce }
            if (r0 == 0) goto L_0x00c4
            java.lang.String r0 = com.tencent.smtt.sdk.TbsShareManager.a()     // Catch:{ Throwable -> 0x00ce }
            if (r0 != 0) goto L_0x0100
            java.lang.String r0 = com.tencent.smtt.sdk.TbsShareManager.c(r6)     // Catch:{ Throwable -> 0x00ce }
            r1 = r0
        L_0x0037:
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00ce }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00ce }
        L_0x003c:
            if (r0 == 0) goto L_0x00b7
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r3.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = java.io.File.separator     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = "liblinuxtoolsfortbssdk_jni.so"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ce }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x00ce }
            if (r1 == 0) goto L_0x0068
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x00ce }
            if (r1 != 0) goto L_0x0076
        L_0x0068:
            boolean r1 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)     // Catch:{ Throwable -> 0x00ce }
            if (r1 != 0) goto L_0x0076
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x00ce }
            java.io.File r0 = r0.p(r6)     // Catch:{ Throwable -> 0x00ce }
        L_0x0076:
            if (r0 == 0) goto L_0x00b7
            java.lang.String r1 = "TbsLinuxToolsJni"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r3.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = "TbsLinuxToolsJni init tbsSharePath is "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ce }
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r1.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = "liblinuxtoolsfortbssdk_jni.so"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00ce }
            java.lang.System.load(r0)     // Catch:{ Throwable -> 0x00ce }
            r0 = 1
            a = r0     // Catch:{ Throwable -> 0x00ce }
        L_0x00b7:
            java.lang.String r0 = "/checkChmodeExists"
            java.lang.String r1 = "700"
            r5.ChmodInner(r0, r1)     // Catch:{ Throwable -> 0x00ce }
        L_0x00be:
            monitor-exit(r2)     // Catch:{ all -> 0x00c1 }
            goto L_0x0022
        L_0x00c1:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00c1 }
            throw r0
        L_0x00c4:
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x00ce }
            java.io.File r0 = r0.q(r6)     // Catch:{ Throwable -> 0x00ce }
            goto L_0x003c
        L_0x00ce:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00c1 }
            r1 = 0
            a = r1     // Catch:{ all -> 0x00c1 }
            java.lang.String r1 = "TbsLinuxToolsJni"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c1 }
            r3.<init>()     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = "TbsLinuxToolsJni init error !!! "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x00c1 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = " ## "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.Throwable r0 = r0.getCause()     // Catch:{ all -> 0x00c1 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x00c1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c1 }
            com.tencent.smtt.utils.TbsLog.i(r1, r0)     // Catch:{ all -> 0x00c1 }
            goto L_0x00be
        L_0x0100:
            r1 = r0
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLinuxToolsJni.a(android.content.Context):void");
    }
}
