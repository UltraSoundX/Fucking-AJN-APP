package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.database.Cursor;
import com.mob.tools.MobLog;

/* compiled from: DBProvider */
public class b {
    private static b b = null;
    private a a = new a();

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    public long a(String str, ContentValues contentValues) {
        long j = -1;
        try {
            return this.a.getWritableDatabase().replace(str, null, contentValues);
        } catch (Exception e) {
            MobLog.getInstance().w(e, "when insert database occur error table:%s,", str);
            return j;
        }
    }

    public int a(String str, String str2, String[] strArr) {
        Exception e;
        int i;
        try {
            i = this.a.getWritableDatabase().delete(str, str2, strArr);
            try {
                MobLog.getInstance().d("Deleted %d rows from table: %s", Integer.valueOf(i), str);
            } catch (Exception e2) {
                e = e2;
                MobLog.getInstance().w(e, "when delete database occur error table:%s,", str);
                return i;
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r11) {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            com.mob.commons.logcollector.a r0 = r10.a
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r1 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x0021, all -> 0x0031 }
            if (r1 != 0) goto L_0x001c
            r0 = r8
        L_0x0016:
            if (r1 == 0) goto L_0x001b
            r1.close()
        L_0x001b:
            return r0
        L_0x001c:
            int r0 = r1.getCount()     // Catch:{ Throwable -> 0x003b }
            goto L_0x0016
        L_0x0021:
            r0 = move-exception
            r1 = r9
        L_0x0023:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0039 }
            r2.w(r0)     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x003d
            r1.close()
            r0 = r8
            goto L_0x001b
        L_0x0031:
            r0 = move-exception
            r1 = r9
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            throw r0
        L_0x0039:
            r0 = move-exception
            goto L_0x0033
        L_0x003b:
            r0 = move-exception
            goto L_0x0023
        L_0x003d:
            r0 = r8
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.logcollector.b.a(java.lang.String):int");
    }

    public Cursor a(String str, String[] strArr) {
        boolean z = false;
        try {
            return this.a.getWritableDatabase().rawQuery(str, strArr);
        } catch (Exception e) {
            MobLog.getInstance().w((Throwable) e);
            return z;
        }
    }
}
