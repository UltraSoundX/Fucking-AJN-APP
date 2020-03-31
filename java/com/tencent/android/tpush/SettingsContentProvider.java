package com.tencent.android.tpush;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import android.os.Build.VERSION;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class SettingsContentProvider extends ContentProvider {
    public static Uri BASE_URI = null;
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String FLOAT_TYPE = "float";
    public static final String INT_TYPE = "integer";
    public static final String KEY = "key";
    public static final String LONG_TYPE = "long";
    public static final String PREFFERENCE_AUTHORITY = "TPUSH_PROVIDER";
    public static final String STRING_TYPE = "string";
    public static final String TYPE = "type";
    private static UriMatcher a;
    private static String b = null;
    private SharedPreferences c = null;

    private void a(Context context) {
        if (b == null) {
            b = context.getPackageName() + "." + PREFFERENCE_AUTHORITY;
        }
        if (a == null) {
            a = new UriMatcher(-1);
            a.addURI(b, "*/*", 65536);
        }
        if (BASE_URI == null) {
            BASE_URI = Uri.parse("content://" + b);
        }
        if (this.c == null) {
            this.c = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences(".tpns.settings.xml", 0);
        }
    }

    public boolean onCreate() {
        a(getContext());
        return true;
    }

    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd.TPUSH_PROVIDER.item";
    }

    public int delete(Uri uri, String str, String[] strArr) {
        switch (a.match(uri)) {
            case 65536:
                this.c.edit().clear().commit();
                break;
            default:
                a.i("SettingsContentProvider", "Unsupported uri " + uri);
                break;
        }
        return 0;
    }

    @SuppressLint({"NewApi"})
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (a.match(uri)) {
            case 65536:
                Editor edit = this.c.edit();
                for (Entry entry : contentValues.valueSet()) {
                    Object value = entry.getValue();
                    String str = (String) entry.getKey();
                    if (value == null) {
                        edit.remove(str);
                    } else if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else {
                        a.i("SettingsContentProvider", "Unsupported type " + uri);
                    }
                }
                if (VERSION.SDK_INT <= 8) {
                    edit.commit();
                    break;
                } else {
                    edit.apply();
                    break;
                }
            default:
                a.i("SettingsContentProvider", "Unsupported uri " + uri);
                break;
        }
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor;
        Object valueOf;
        switch (a.match(uri)) {
            case 65536:
                String str3 = (String) uri.getPathSegments().get(0);
                String str4 = (String) uri.getPathSegments().get(1);
                MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{str3});
                if (this.c.contains(str3)) {
                    RowBuilder newRow = matrixCursor2.newRow();
                    if (!"string".equals(str4)) {
                        if (!"boolean".equals(str4)) {
                            if (!LONG_TYPE.equals(str4)) {
                                if (!"integer".equals(str4)) {
                                    if (!"float".equals(str4)) {
                                        a.i("SettingsContentProvider", "Unsupported type " + uri);
                                        matrixCursor = matrixCursor2;
                                        break;
                                    } else {
                                        valueOf = Float.valueOf(this.c.getFloat(str3, 0.0f));
                                    }
                                } else {
                                    valueOf = Integer.valueOf(this.c.getInt(str3, 0));
                                }
                            } else {
                                valueOf = Long.valueOf(this.c.getLong(str3, 0));
                            }
                        } else {
                            valueOf = Integer.valueOf(this.c.getBoolean(str3, false) ? 1 : 0);
                        }
                    } else {
                        valueOf = this.c.getString(str3, null);
                    }
                    newRow.add(valueOf);
                    matrixCursor = matrixCursor2;
                    break;
                } else {
                    return matrixCursor2;
                }
            default:
                a.i("SettingsContentProvider", "Unsupported uri " + uri);
                matrixCursor = null;
                break;
        }
        return matrixCursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        a.i("SettingsContentProvider", "UnsupportedOperation: update!");
        return 0;
    }

    public static String getStringValue(Cursor cursor, String str) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
            cursor.close();
        }
        return str;
    }

    public static boolean getBooleanValue(Cursor cursor, boolean z) {
        boolean z2 = false;
        if (cursor == null) {
            return z;
        }
        if (!cursor.moveToFirst()) {
            z2 = z;
        } else if (cursor.getInt(0) > 0) {
            z2 = true;
        }
        cursor.close();
        return z2;
    }

    public static int getIntValue(Cursor cursor, int i) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
            cursor.close();
        }
        return i;
    }

    public static long getLongValue(Cursor cursor, long j) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
            }
            cursor.close();
        }
        return j;
    }

    public static float getFloatValue(Cursor cursor, float f) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                f = cursor.getFloat(0);
            }
            cursor.close();
        }
        return f;
    }

    public static final Uri getContentUri(Context context, String str, String str2) {
        if (BASE_URI == null) {
            if (b == null) {
                b = context.getPackageName() + "." + PREFFERENCE_AUTHORITY;
            }
            BASE_URI = Uri.parse("content://" + b);
        }
        return BASE_URI.buildUpon().appendPath(str).appendPath(str2).build();
    }
}
