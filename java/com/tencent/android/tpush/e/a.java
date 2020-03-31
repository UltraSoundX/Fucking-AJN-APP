package com.tencent.android.tpush.e;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.e.i;
import java.net.URISyntaxException;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class a {
    private static long a = 0;
    private static long b = 172800000;
    private static com.tencent.android.tpush.e.a.a c;

    private static com.tencent.android.tpush.e.a.a d(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new com.tencent.android.tpush.e.a.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                }
            }
        }
        return c;
    }

    public static boolean a(Context context, Intent intent) {
        boolean z = true;
        long longExtra = intent.getLongExtra(MessageKey.MSG_ID, -1);
        ContentValues contentValues = new ContentValues();
        contentValues.put("msgid", Long.valueOf(longExtra));
        contentValues.put("message", Rijndael.encrypt(intent.toUri(1)));
        contentValues.put("time", Long.valueOf(i.b(intent)));
        contentValues.put("busiid", Long.valueOf(intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0)));
        contentValues.put("showedtime", Integer.valueOf(0));
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.insert("messagetoshow", null, contentValues) <= 0) {
                com.tencent.android.tpush.b.a.i("MessageInfoManager", "addCacheMessage Error! ");
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "addNewCacheMessage Error! " + th);
            return false;
        }
    }

    public static boolean a(Context context, long j) {
        e(context);
        return a(context, j, 1);
    }

    public static boolean b(Context context, long j) {
        e(context);
        return a(context, j, 2);
    }

    public static boolean c(Context context, long j) {
        return a(context, j, 3);
    }

    public static boolean d(Context context, long j) {
        return a(context, j, 4);
    }

    private static boolean a(Context context, long j, int i) {
        boolean z = true;
        ContentValues contentValues = new ContentValues();
        if (i == 1 || i == 2) {
            contentValues.put("showedtime", Long.valueOf(System.currentTimeMillis()));
        }
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (((long) writableDatabase.update("messagetoshow", contentValues, "msgid=?", new String[]{j + ""})) <= 0) {
                com.tencent.android.tpush.b.a.i("MessageInfoManager", "updateCacheMessage Error! msgId:" + j + ", status:" + i);
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "updateCacheMessage Error! " + th);
            return false;
        }
    }

    private static boolean e(Context context) {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (a != 0 && currentTimeMillis - a <= 86400000) {
            return true;
        }
        a = currentTimeMillis;
        long j = currentTimeMillis - b;
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.delete("messagetoshow", "status >= ? AND showedtime < ? ", new String[]{"1", j + ""}) <= 0) {
                com.tencent.android.tpush.b.a.c("MessageInfoManager", "delOldShowedCacheMessage Error! toDelTime: " + j);
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "delOldShowedCacheMessage Error! " + th);
            return false;
        }
    }

    public static boolean e(Context context, long j) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.delete("messagetoshow", "msgid=?", new String[]{j + ""}) <= 0) {
                com.tencent.android.tpush.b.a.i("MessageInfoManager", "delCacheMessage Error! msgid to delete: " + j);
                if (writableDatabase == null) {
                    return false;
                }
                try {
                    writableDatabase.close();
                    return false;
                } catch (Throwable th) {
                    return false;
                }
            } else {
                if (writableDatabase != null) {
                    try {
                        writableDatabase.close();
                    } catch (Throwable th2) {
                    }
                }
                return true;
            }
        } catch (Throwable th3) {
            return false;
        }
    }

    public static boolean a(Context context) {
        boolean z = true;
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.delete("messagetoshow", null, null) <= 0) {
                com.tencent.android.tpush.b.a.g("MessageInfoManager", "delAllCacheMessage but no mssgage in db");
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "delAllCacheMessage Error! " + th);
            return false;
        }
    }

    public static boolean f(Context context, long j) {
        boolean z = true;
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.delete("messagetoshow", "busiid=?", new String[]{j + ""}) <= 0) {
                com.tencent.android.tpush.b.a.i("MessageInfoManager", "delCacheMessageByBusiId Error! msgid to delete which busiId = : " + j);
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "delCacheMessageByBusiId Error! " + th);
            return false;
        }
    }

    public static boolean b(Context context) {
        boolean z = true;
        try {
            SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
            if (writableDatabase.delete("messagetoshow", "msgid < 0", null) <= 0) {
                com.tencent.android.tpush.b.a.i("MessageInfoManager", "deleteAllLocalCacheMsgIntent Error! ");
                z = false;
            }
            writableDatabase.close();
            return z;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "deleteAllLocalCacheMsgIntent Error! " + th);
            return false;
        }
    }

    public static ArrayList<Intent> c(Context context) {
        ArrayList<Intent> arrayList = new ArrayList<>();
        try {
            SQLiteDatabase readableDatabase = d(context).getReadableDatabase();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("messagetoshow");
            Cursor query = sQLiteQueryBuilder.query(readableDatabase, new String[]{"message"}, "status=0", null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    Intent parseUri = Intent.parseUri(Rijndael.decrypt(query.getString(0)), 1);
                    parseUri.addCategory("android.intent.category.BROWSABLE");
                    parseUri.setComponent(null);
                    if (VERSION.SDK_INT >= 15) {
                        try {
                            parseUri.getClass().getMethod("setSelector", new Class[]{Intent.class}).invoke(parseUri, new Object[]{null});
                        } catch (Exception e) {
                            com.tencent.android.tpush.b.a.c(Constants.LogTag, "invoke intent.setComponent error.", e);
                        }
                    }
                    arrayList.add(parseUri);
                }
                query.close();
            }
            readableDatabase.close();
        } catch (URISyntaxException e2) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "getCacheMessages Error: " + e2);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i("MessageInfoManager", "getNewCacheMessages Error! " + th);
        }
        return arrayList;
    }
}
