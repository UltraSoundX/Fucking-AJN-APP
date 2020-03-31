package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

class m extends SQLiteOpenHelper {
    private String a;
    private SQLiteDatabase b;

    public m(Context context, String str) throws SQLiteException {
        super(context, ".confd", null, 1);
        this.a = str;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.b = sQLiteDatabase;
    }

    public synchronized boolean a() {
        boolean z;
        boolean z2 = true;
        synchronized (this) {
            if (this.b == null) {
                z = true;
            } else if (!this.b.isOpen()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                try {
                    this.b = getWritableDatabase();
                } catch (NullPointerException e) {
                    throw new NullPointerException("db path is null");
                }
            }
            if (this.b == null || !this.b.isOpen()) {
                z2 = false;
            }
        }
        return z2;
    }

    public synchronized void close() {
        super.close();
        if (this.b != null) {
            this.b.close();
            this.b = null;
        }
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void a(String str) {
        getWritableDatabase().execSQL(str);
    }

    /* JADX INFO: finally extract failed */
    public final int b() {
        Cursor cursor = null;
        int i = 0;
        try {
            Cursor rawQuery = this.b.rawQuery("SELECT COUNT(*) FROM " + this.a, null);
            if (rawQuery != null && rawQuery.moveToNext()) {
                i = rawQuery.getInt(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return i;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.b.query(this.a, strArr, str, strArr2, str2, str3, str4, str5);
    }

    public long a(String str, ContentValues contentValues) {
        return this.b.insert(this.a, str, contentValues);
    }

    public int a(String str, String[] strArr) {
        return this.b.delete(this.a, str, strArr);
    }
}
