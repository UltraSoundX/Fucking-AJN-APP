package com.baidu.location.d;

import com.baidu.location.BDLocation;
import java.util.concurrent.Callable;

class h implements Callable<BDLocation> {
    final /* synthetic */ String[] a;
    final /* synthetic */ g b;

    h(g gVar, String[] strArr) {
        this.b = gVar;
        this.a = strArr;
    }

    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.content.pm.ProviderInfo] */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r6v5, types: [com.baidu.location.BDLocation] */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: type inference failed for: r0v19, types: [com.baidu.location.BDLocation] */
    /* JADX WARNING: type inference failed for: r6v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v25 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r0v26 */
    /* JADX WARNING: type inference failed for: r0v29, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11, types: [com.baidu.location.BDLocation] */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v24, types: [android.content.pm.ProviderInfo] */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r0v33 */
    /* JADX WARNING: type inference failed for: r0v34 */
    /* JADX WARNING: type inference failed for: r1v26 */
    /* JADX WARNING: type inference failed for: r1v27 */
    /* JADX WARNING: type inference failed for: r1v28 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v4
  assigns: []
  uses: []
  mth insns count: 92
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0071 A[SYNTHETIC, Splitter:B:32:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a3 A[SYNTHETIC, Splitter:B:56:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:74:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 13 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.baidu.location.BDLocation call() {
        /*
            r7 = this;
            r0 = 0
            r6 = 0
            com.baidu.location.BDLocation r1 = new com.baidu.location.BDLocation
            r1.<init>()
            java.lang.String[] r2 = r7.a
            int r2 = r2.length
            if (r2 <= 0) goto L_0x00bd
            com.baidu.location.d.g r1 = r7.b
            com.baidu.location.d.f r1 = r1.j
            java.lang.String[] r2 = r1.o()
            r1 = r6
        L_0x0017:
            int r3 = r2.length
            if (r0 >= r3) goto L_0x002b
            android.content.Context r1 = com.baidu.location.d.g.c     // Catch:{ Exception -> 0x005e }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ Exception -> 0x005e }
            r3 = r2[r0]     // Catch:{ Exception -> 0x005e }
            r4 = 0
            android.content.pm.ProviderInfo r1 = r1.resolveContentProvider(r3, r4)     // Catch:{ Exception -> 0x005e }
        L_0x0029:
            if (r1 == 0) goto L_0x0061
        L_0x002b:
            if (r1 == 0) goto L_0x0075
            android.content.Context r0 = com.baidu.location.d.g.c     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            java.lang.String r1 = r1.authority     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            android.net.Uri r1 = com.baidu.location.d.g.c(r1)     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            java.lang.String[] r2 = r7.a     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0064, all -> 0x006e }
            com.baidu.location.BDLocation r6 = com.baidu.location.d.i.a(r0)     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ Exception -> 0x00a7 }
        L_0x004d:
            r0 = r6
        L_0x004e:
            if (r0 == 0) goto L_0x005d
            int r1 = r0.o()
            r2 = 67
            if (r1 == r2) goto L_0x005d
            r1 = 66
            r0.e(r1)
        L_0x005d:
            return r0
        L_0x005e:
            r1 = move-exception
            r1 = r6
            goto L_0x0029
        L_0x0061:
            int r0 = r0 + 1
            goto L_0x0017
        L_0x0064:
            r0 = move-exception
            r0 = r6
        L_0x0066:
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ Exception -> 0x006c }
            goto L_0x004d
        L_0x006c:
            r0 = move-exception
            goto L_0x004d
        L_0x006e:
            r0 = move-exception
        L_0x006f:
            if (r6 == 0) goto L_0x0074
            r6.close()     // Catch:{ Exception -> 0x00a9 }
        L_0x0074:
            throw r0
        L_0x0075:
            com.baidu.location.d.i$a r0 = new com.baidu.location.d.i$a
            java.lang.String[] r1 = r7.a
            r0.<init>(r1)
            com.baidu.location.d.g r1 = r7.b     // Catch:{ Exception -> 0x0094, all -> 0x00a0 }
            com.baidu.location.d.c r1 = r1.h     // Catch:{ Exception -> 0x0094, all -> 0x00a0 }
            android.database.Cursor r0 = r1.a(r0)     // Catch:{ Exception -> 0x0094, all -> 0x00a0 }
            com.baidu.location.BDLocation r6 = com.baidu.location.d.i.a(r0)     // Catch:{ Exception -> 0x00b1, all -> 0x00ad }
            if (r0 == 0) goto L_0x00bb
            r0.close()     // Catch:{ Exception -> 0x0091 }
            r0 = r6
            goto L_0x004e
        L_0x0091:
            r0 = move-exception
            r0 = r6
            goto L_0x004e
        L_0x0094:
            r0 = move-exception
            r0 = r6
        L_0x0096:
            if (r0 == 0) goto L_0x00b9
            r0.close()     // Catch:{ Exception -> 0x009d }
            r0 = r6
            goto L_0x004e
        L_0x009d:
            r0 = move-exception
            r0 = r6
            goto L_0x004e
        L_0x00a0:
            r0 = move-exception
        L_0x00a1:
            if (r6 == 0) goto L_0x00a6
            r6.close()     // Catch:{ Exception -> 0x00ab }
        L_0x00a6:
            throw r0
        L_0x00a7:
            r0 = move-exception
            goto L_0x004d
        L_0x00a9:
            r1 = move-exception
            goto L_0x0074
        L_0x00ab:
            r1 = move-exception
            goto L_0x00a6
        L_0x00ad:
            r1 = move-exception
            r6 = r0
            r0 = r1
            goto L_0x00a1
        L_0x00b1:
            r1 = move-exception
            goto L_0x0096
        L_0x00b3:
            r1 = move-exception
            r6 = r0
            r0 = r1
            goto L_0x006f
        L_0x00b7:
            r1 = move-exception
            goto L_0x0066
        L_0x00b9:
            r0 = r6
            goto L_0x004e
        L_0x00bb:
            r0 = r6
            goto L_0x004e
        L_0x00bd:
            r0 = r1
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.h.call():com.baidu.location.BDLocation");
    }
}
