package com.tencent.android.tpush.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
public class b {
    private static ConcurrentHashMap<String, List<Long>> a = new ConcurrentHashMap<>();

    public static Uri a(Context context, Uri uri, ContentValues contentValues) {
        long currentTimeMillis = System.currentTimeMillis() - System.currentTimeMillis();
        return context.getContentResolver().insert(uri, contentValues);
    }

    public static String a(Context context, Uri uri) {
        long currentTimeMillis = System.currentTimeMillis() - System.currentTimeMillis();
        return context.getContentResolver().getType(uri);
    }

    public static int a(Context context, Uri uri, ContentValues contentValues, String str, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis() - System.currentTimeMillis();
        return context.getContentResolver().update(uri, contentValues, str, strArr);
    }

    public static Cursor a(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        long currentTimeMillis = System.currentTimeMillis() - System.currentTimeMillis();
        return context.getContentResolver().query(uri, strArr, str, strArr2, str2);
    }
}
