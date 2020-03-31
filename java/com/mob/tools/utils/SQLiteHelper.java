package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SQLiteHelper {

    public static class SingleTableDB {
        /* access modifiers changed from: private */
        public SQLiteDatabase db;
        private HashMap<String, Boolean> fieldLimits;
        private LinkedHashMap<String, String> fieldTypes;
        private String name;
        private String path;
        private String primary;
        private boolean primaryAutoincrement;

        private SingleTableDB(String str, String str2) {
            this.path = str;
            this.name = str2;
            this.fieldTypes = new LinkedHashMap<>();
            this.fieldLimits = new HashMap<>();
        }

        public void addField(String str, String str2, boolean z) {
            if (this.db == null) {
                this.fieldTypes.put(str, str2);
                this.fieldLimits.put(str, Boolean.valueOf(z));
            }
        }

        public void setPrimary(String str, boolean z) {
            this.primary = str;
            this.primaryAutoincrement = z;
        }

        /* access modifiers changed from: private */
        public void open() {
            boolean z;
            boolean z2;
            File file = new File(this.path);
            if (this.db != null && !file.exists()) {
                this.db.close();
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                this.db = null;
            }
            if (this.db == null) {
                this.db = SQLiteDatabase.openOrCreateDatabase(file, null);
                Cursor query = this.db.query("sqlite_master", null, "type=? and name=?", new String[]{"table", this.name}, null, null, null);
                if (query != null) {
                    if (query.getCount() > 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    query.close();
                } else {
                    z = true;
                }
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("create table  ").append(this.name).append("(");
                    for (Entry entry : this.fieldTypes.entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        boolean booleanValue = ((Boolean) this.fieldLimits.get(str)).booleanValue();
                        boolean equals = str.equals(this.primary);
                        if (equals) {
                            z2 = this.primaryAutoincrement;
                        } else {
                            z2 = false;
                        }
                        sb.append(str).append(" ").append(str2);
                        sb.append(booleanValue ? " not null" : "");
                        sb.append(equals ? " primary key" : "");
                        sb.append(z2 ? " autoincrement," : StorageInterface.KEY_SPLITER);
                    }
                    sb.replace(sb.length() - 1, sb.length(), ");");
                    this.db.execSQL(sb.toString());
                }
            }
        }

        /* access modifiers changed from: private */
        public void close() {
            if (this.db != null) {
                this.db.close();
                this.db = null;
            }
        }

        /* access modifiers changed from: private */
        public String getName() {
            return this.name;
        }
    }

    public static SingleTableDB getDatabase(Context context, String str) {
        return getDatabase(context.getDatabasePath(str).getPath(), str);
    }

    public static SingleTableDB getDatabase(String str, String str2) {
        return new SingleTableDB(str, str2);
    }

    public static long insert(SingleTableDB singleTableDB, ContentValues contentValues) throws Throwable {
        singleTableDB.open();
        return singleTableDB.db.replace(singleTableDB.getName(), null, contentValues);
    }

    public static int delete(SingleTableDB singleTableDB, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.db.delete(singleTableDB.getName(), str, strArr);
    }

    public static int update(SingleTableDB singleTableDB, ContentValues contentValues, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.db.update(singleTableDB.getName(), contentValues, str, strArr);
    }

    public static Cursor query(SingleTableDB singleTableDB, String[] strArr, String str, String[] strArr2, String str2) throws Throwable {
        singleTableDB.open();
        return singleTableDB.db.query(singleTableDB.getName(), strArr, str, strArr2, null, null, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        com.mob.tools.utils.SQLiteHelper.SingleTableDB.access$300(r2).endTransaction();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void execSQL(com.mob.tools.utils.SQLiteHelper.SingleTableDB r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            r2.open()
            android.database.sqlite.SQLiteDatabase r0 = r2.db
            r0.beginTransaction()
            android.database.sqlite.SQLiteDatabase r0 = r2.db     // Catch:{ Throwable -> 0x0020 }
            r0.execSQL(r3)     // Catch:{ Throwable -> 0x0020 }
            android.database.sqlite.SQLiteDatabase r0 = r2.db     // Catch:{ Throwable -> 0x0020 }
            r0.setTransactionSuccessful()     // Catch:{ Throwable -> 0x0020 }
            android.database.sqlite.SQLiteDatabase r0 = r2.db
            r0.endTransaction()
            return
        L_0x0020:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r0 = move-exception
            android.database.sqlite.SQLiteDatabase r1 = r2.db
            r1.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.execSQL(com.mob.tools.utils.SQLiteHelper$SingleTableDB, java.lang.String):void");
    }

    public static Cursor rawQuery(SingleTableDB singleTableDB, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.db.rawQuery(str, strArr);
    }

    public static int getSize(SingleTableDB singleTableDB) throws Throwable {
        Cursor cursor;
        int count;
        Cursor cursor2 = null;
        singleTableDB.open();
        try {
            cursor = singleTableDB.db.query(singleTableDB.getName(), null, null, null, null, null, null);
            if (cursor == null) {
                count = 0;
            } else {
                try {
                    count = cursor.getCount();
                } catch (Throwable th) {
                    th = th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return count;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            throw th;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
        }
    }

    public static void close(SingleTableDB singleTableDB) {
        singleTableDB.close();
    }
}
