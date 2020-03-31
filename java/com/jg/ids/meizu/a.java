package com.jg.ids.meizu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;

final class a {
    private static final a b = new a();
    private BroadcastReceiver a = null;
    private b c = null;

    static a a() {
        return b;
    }

    private a() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.content.Context r10) {
        /*
            r9 = this;
            r7 = 0
            r6 = 0
            android.content.pm.PackageManager r0 = r10.getPackageManager()     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            java.lang.String r1 = "com.meizu.flyme.openidsdk"
            r2 = 0
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r1, r2)     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            if (r0 != 0) goto L_0x0011
            r0 = r6
        L_0x0010:
            return r0
        L_0x0011:
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            android.content.ContentResolver r0 = r10.getContentResolver()     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            r5 = 0
            java.lang.String r8 = "supported"
            r4[r5] = r8     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x004a, all -> 0x0052 }
            if (r1 == 0) goto L_0x0043
            r1.moveToFirst()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            com.jg.ids.meizu.d r0 = a(r1)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r0 == 0) goto L_0x0043
            java.lang.String r2 = "0"
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            boolean r0 = r2.equals(r0)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r1 == 0) goto L_0x0010
            r1.close()
            goto L_0x0010
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()
        L_0x0048:
            r0 = r6
            goto L_0x0010
        L_0x004a:
            r0 = move-exception
            r0 = r7
        L_0x004c:
            if (r0 == 0) goto L_0x0048
            r0.close()
            goto L_0x0048
        L_0x0052:
            r0 = move-exception
        L_0x0053:
            if (r7 == 0) goto L_0x0058
            r7.close()
        L_0x0058:
            throw r0
        L_0x0059:
            r0 = move-exception
            r7 = r1
            goto L_0x0053
        L_0x005c:
            r0 = move-exception
            r0 = r1
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jg.ids.meizu.a.a(android.content.Context):boolean");
    }

    private static d a(Cursor cursor) {
        d dVar = null;
        d dVar2 = new d(null, 0);
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex("value");
                    if (columnIndex >= 0) {
                        dVar2.a = cursor.getString(columnIndex);
                    }
                    int columnIndex2 = cursor.getColumnIndex("code");
                    if (columnIndex2 >= 0) {
                        dVar2.b = cursor.getInt(columnIndex2);
                    }
                    int columnIndex3 = cursor.getColumnIndex("expired");
                    if (columnIndex3 >= 0) {
                        dVar2.c = cursor.getLong(columnIndex3);
                        return dVar2;
                    }
                }
            } catch (Throwable th) {
            }
        }
        dVar = dVar2;
        return dVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String b(android.content.Context r10) {
        /*
            r9 = this;
            r7 = 0
            java.lang.String r6 = ""
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r1 = android.net.Uri.parse(r0)
            android.content.ContentResolver r0 = r10.getContentResolver()     // Catch:{ Throwable -> 0x003c, all -> 0x0044 }
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Throwable -> 0x003c, all -> 0x0044 }
            r5 = 0
            java.lang.String r8 = "aaid"
            r4[r5] = r8     // Catch:{ Throwable -> 0x003c, all -> 0x0044 }
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x003c, all -> 0x0044 }
            if (r1 == 0) goto L_0x0035
            com.jg.ids.meizu.d r0 = a(r1)     // Catch:{ Throwable -> 0x004e, all -> 0x004b }
            if (r0 == 0) goto L_0x0035
            int r2 = r0.b     // Catch:{ Throwable -> 0x004e, all -> 0x004b }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 == r3) goto L_0x002d
            r9.e(r10)     // Catch:{ Throwable -> 0x004e, all -> 0x004b }
        L_0x002d:
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x004e, all -> 0x004b }
            if (r1 == 0) goto L_0x0034
            r1.close()
        L_0x0034:
            return r0
        L_0x0035:
            if (r1 == 0) goto L_0x003a
            r1.close()
        L_0x003a:
            r0 = r6
            goto L_0x0034
        L_0x003c:
            r0 = move-exception
            r0 = r7
        L_0x003e:
            if (r0 == 0) goto L_0x003a
            r0.close()
            goto L_0x003a
        L_0x0044:
            r0 = move-exception
        L_0x0045:
            if (r7 == 0) goto L_0x004a
            r7.close()
        L_0x004a:
            throw r0
        L_0x004b:
            r0 = move-exception
            r7 = r1
            goto L_0x0045
        L_0x004e:
            r0 = move-exception
            r0 = r1
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jg.ids.meizu.a.b(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String c(android.content.Context r9) {
        /*
            r8 = this;
            r6 = 0
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r1 = android.net.Uri.parse(r0)
            android.content.ContentResolver r0 = r9.getContentResolver()     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r5 = 0
            java.lang.String r7 = "oaid"
            r4[r5] = r7     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            if (r1 == 0) goto L_0x0033
            com.jg.ids.meizu.d r0 = a(r1)     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            if (r0 == 0) goto L_0x0033
            int r2 = r0.b     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 == r3) goto L_0x002b
            r8.e(r9)     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
        L_0x002b:
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r0
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            java.lang.String r0 = ""
            goto L_0x0032
        L_0x003b:
            r0 = move-exception
            r0 = r6
        L_0x003d:
            if (r0 == 0) goto L_0x0038
            r0.close()
            goto L_0x0038
        L_0x0043:
            r0 = move-exception
        L_0x0044:
            if (r6 == 0) goto L_0x0049
            r6.close()
        L_0x0049:
            throw r0
        L_0x004a:
            r0 = move-exception
            r6 = r1
            goto L_0x0044
        L_0x004d:
            r0 = move-exception
            r0 = r1
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jg.ids.meizu.a.c(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String d(android.content.Context r9) {
        /*
            r8 = this;
            r6 = 0
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r1 = android.net.Uri.parse(r0)
            android.content.ContentResolver r0 = r9.getContentResolver()     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r5 = 0
            java.lang.String r7 = "vaid"
            r4[r5] = r7     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x003b, all -> 0x0043 }
            if (r1 == 0) goto L_0x0033
            com.jg.ids.meizu.d r0 = a(r1)     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            if (r0 == 0) goto L_0x0033
            int r2 = r0.b     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 == r3) goto L_0x002b
            r8.e(r9)     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
        L_0x002b:
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x004d, all -> 0x004a }
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r0
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            java.lang.String r0 = ""
            goto L_0x0032
        L_0x003b:
            r0 = move-exception
            r0 = r6
        L_0x003d:
            if (r0 == 0) goto L_0x0038
            r0.close()
            goto L_0x0038
        L_0x0043:
            r0 = move-exception
        L_0x0044:
            if (r6 == 0) goto L_0x0049
            r6.close()
        L_0x0049:
            throw r0
        L_0x004a:
            r0 = move-exception
            r6 = r1
            goto L_0x0044
        L_0x004d:
            r0 = move-exception
            r0 = r1
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jg.ids.meizu.a.d(android.content.Context):java.lang.String");
    }

    public final void a(b bVar) {
        this.c = bVar;
    }

    public final void a(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    private synchronized void e(Context context) {
        try {
            if (this.a == null) {
                this.a = new MeiZuReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.meizu.flyme.openid.ACTION_OPEN_ID_CHANGE");
                context.registerReceiver(this.a, intentFilter, "com.meizu.flyme.openid.permission.OPEN_ID_CHANGE", null);
            }
        } catch (Throwable th) {
        }
    }
}
