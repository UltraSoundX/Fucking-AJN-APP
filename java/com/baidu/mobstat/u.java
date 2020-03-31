package com.baidu.mobstat;

public class u {
    private static a a;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c A[SYNTHETIC, Splitter:B:13:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.baidu.mobstat.a a(android.content.Context r5) {
        /*
            java.lang.Class<com.baidu.mobstat.u> r2 = com.baidu.mobstat.u.class
            monitor-enter(r2)
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = "getBPStretegyController begin"
            r0.a(r1)     // Catch:{ all -> 0x0054 }
            com.baidu.mobstat.a r1 = a     // Catch:{ all -> 0x0054 }
            if (r1 != 0) goto L_0x005c
            java.lang.String r0 = "com.baidu.bottom.remote.BPStretegyController2"
            java.lang.Class r0 = com.baidu.mobstat.x.a(r5, r0)     // Catch:{ Exception -> 0x004a }
            if (r0 == 0) goto L_0x005c
            java.lang.Object r3 = r0.newInstance()     // Catch:{ Exception -> 0x004a }
            com.baidu.mobstat.w r0 = new com.baidu.mobstat.w     // Catch:{ Exception -> 0x004a }
            r0.<init>(r3)     // Catch:{ Exception -> 0x004a }
            com.baidu.mobstat.al r1 = com.baidu.mobstat.al.c()     // Catch:{ Exception -> 0x0057 }
            java.lang.String r3 = "Get BPStretegyController load remote class v2"
            r1.a(r3)     // Catch:{ Exception -> 0x0057 }
        L_0x002a:
            if (r0 != 0) goto L_0x003a
            com.baidu.mobstat.v r0 = new com.baidu.mobstat.v     // Catch:{ all -> 0x0054 }
            r0.<init>()     // Catch:{ all -> 0x0054 }
            com.baidu.mobstat.al r1 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "Get BPStretegyController load local class"
            r1.a(r3)     // Catch:{ all -> 0x0054 }
        L_0x003a:
            a = r0     // Catch:{ all -> 0x0054 }
            com.baidu.mobstat.x.a(r5, r0)     // Catch:{ all -> 0x0054 }
            com.baidu.mobstat.al r1 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "getBPStretegyController end"
            r1.a(r3)     // Catch:{ all -> 0x0054 }
            monitor-exit(r2)
            return r0
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()     // Catch:{ all -> 0x0054 }
            r3.a(r0)     // Catch:{ all -> 0x0054 }
            r0 = r1
            goto L_0x002a
        L_0x0054:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0057:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x004b
        L_0x005c:
            r0 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.u.a(android.content.Context):com.baidu.mobstat.a");
    }

    public static synchronized void a() {
        synchronized (u.class) {
            a = null;
        }
    }
}
