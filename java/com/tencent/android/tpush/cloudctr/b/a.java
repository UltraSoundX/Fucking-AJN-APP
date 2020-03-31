package com.tencent.android.tpush.cloudctr.b;

/* compiled from: ProGuard */
public class a {
    private static String a = "cloudcontrol file util";

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006e, code lost:
        r7 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0047 A[Catch:{ Exception -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004c A[Catch:{ Exception -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0058 A[Catch:{ Exception -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005d A[Catch:{ Exception -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0079 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:10:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r8, java.io.File r9) {
        /*
            r0 = 0
            r6 = 0
            java.io.File r1 = r9.getParentFile()     // Catch:{ Exception -> 0x0061 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x0061 }
            if (r1 != 0) goto L_0x0013
            java.io.File r1 = r9.getParentFile()     // Catch:{ Exception -> 0x0061 }
            r1.mkdirs()     // Catch:{ Exception -> 0x0061 }
        L_0x0013:
            boolean r1 = r9.exists()     // Catch:{ Exception -> 0x0061 }
            if (r1 != 0) goto L_0x001c
            r9.createNewFile()     // Catch:{ Exception -> 0x0061 }
        L_0x001c:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0043, all -> 0x0051 }
            r1.<init>(r8)     // Catch:{ IOException -> 0x0043, all -> 0x0051 }
            java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch:{ IOException -> 0x0043, all -> 0x0051 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0079, all -> 0x006d }
            r2.<init>(r9)     // Catch:{ IOException -> 0x0079, all -> 0x006d }
            java.nio.channels.FileChannel r0 = r2.getChannel()     // Catch:{ IOException -> 0x0079, all -> 0x006d }
            r2 = 0
            long r4 = r1.size()     // Catch:{ IOException -> 0x0079, all -> 0x0073 }
            r0.transferFrom(r1, r2, r4)     // Catch:{ IOException -> 0x0079, all -> 0x0073 }
            if (r1 == 0) goto L_0x003c
            r1.close()     // Catch:{ Exception -> 0x0061 }
        L_0x003c:
            if (r0 == 0) goto L_0x0041
            r0.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0041:
            r0 = 1
        L_0x0042:
            return r0
        L_0x0043:
            r1 = move-exception
            r1 = r0
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch:{ Exception -> 0x0061 }
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()     // Catch:{ Exception -> 0x0061 }
        L_0x004f:
            r0 = r6
            goto L_0x0042
        L_0x0051:
            r1 = move-exception
            r2 = r0
            r7 = r0
            r0 = r1
            r1 = r7
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            r2.close()     // Catch:{ Exception -> 0x0061 }
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0060:
            throw r0     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            r0 = move-exception
            java.lang.String r1 = a
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            r0 = r6
            goto L_0x0042
        L_0x006d:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r0
            r0 = r7
            goto L_0x0056
        L_0x0073:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r0
            r0 = r7
            goto L_0x0056
        L_0x0079:
            r2 = move-exception
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.cloudctr.b.a.a(java.io.File, java.io.File):boolean");
    }
}
