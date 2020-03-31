package cn.sharesdk.framework.b.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sharesdk.framework.utils.SSDKLog;

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

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        SSDKLog.b().d("Query table: %s", str);
        try {
            return writableDatabase.query(str, strArr, str2, strArr2, null, null, str3);
        } catch (Exception e) {
            SSDKLog.b().w(e, "when query database occur error table:%s,", str);
            return null;
        }
    }

    public long a(String str, ContentValues contentValues) {
        long j = -1;
        try {
            return this.a.getWritableDatabase().replace(str, null, contentValues);
        } catch (Exception e) {
            SSDKLog.b().w(e, "when insert database occur error table:%s,", str);
            return j;
        }
    }

    public int a(String str, String str2, String[] strArr) {
        Exception e;
        int i;
        try {
            i = this.a.getWritableDatabase().delete(str, str2, strArr);
            try {
                SSDKLog.b().d("Deleted %d rows from table: %s", Integer.valueOf(i), str);
            } catch (Exception e2) {
                e = e2;
                SSDKLog.b().w(e, "when delete database occur error table:%s,", str);
                return i;
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
        }
        return i;
    }

    public int a(String str) {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.a.getWritableDatabase().rawQuery("select count(*) from " + str, null);
            if (cursor.moveToNext()) {
                i = cursor.getInt(0);
            }
        } catch (Exception e) {
            SSDKLog.b().w((Throwable) e);
        } finally {
            cursor.close();
        }
        return i;
    }
}
