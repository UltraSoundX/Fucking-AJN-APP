package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.f;
import java.io.File;

/* compiled from: SqliteDataManager */
public class h {
    public static final String a = CookieManager.LOGTAG;
    static File b;

    public static File a(Context context) {
        if (b == null && context != null) {
            b = new File(context.getDir("webview", 0), "Cookies");
        }
        if (b == null) {
            b = new File("/data/data/" + context.getPackageName() + File.separator + "app_webview" + File.separator + "Cookies");
        }
        return b;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        f.a(a(context), false);
        return true;
    }

    public static SQLiteDatabase c(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        if (context != null) {
            File a2 = a(context);
            if (a2 != null) {
                try {
                    sQLiteDatabase = SQLiteDatabase.openDatabase(a2.getAbsolutePath(), null, 0);
                } catch (Exception e) {
                }
                if (sQLiteDatabase == null) {
                    TbsLog.i(a, "dbPath is not exist!");
                }
            }
        }
        return sQLiteDatabase;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.ArrayList<java.lang.String>] */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r0 != 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0050, code lost:
        r4 = r1;
        r1 = r0;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:3:0x000c] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> a(android.database.sqlite.SQLiteDatabase r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "select * from sqlite_master where type='table'"
            r3 = 0
            android.database.Cursor r0 = r5.rawQuery(r2, r3)     // Catch:{ Throwable -> 0x003d, all -> 0x004f }
            boolean r2 = r0.moveToFirst()     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            if (r2 == 0) goto L_0x002b
        L_0x0016:
            r2 = 1
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            r3 = 4
            r0.getString(r3)     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            r1.add(r2)     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            a(r5, r2)     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            boolean r2 = r0.moveToNext()     // Catch:{ Throwable -> 0x003d, all -> 0x0064 }
            if (r2 != 0) goto L_0x0016
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            if (r5 == 0) goto L_0x003b
            boolean r0 = r5.isOpen()
            if (r0 == 0) goto L_0x003b
            r5.close()
        L_0x003b:
            r0 = r1
            goto L_0x0003
        L_0x003d:
            r2 = move-exception
            if (r0 == 0) goto L_0x0043
            r0.close()
        L_0x0043:
            if (r5 == 0) goto L_0x003b
            boolean r0 = r5.isOpen()
            if (r0 == 0) goto L_0x003b
            r5.close()
            goto L_0x003b
        L_0x004f:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            if (r5 == 0) goto L_0x0063
            boolean r1 = r5.isOpen()
            if (r1 == 0) goto L_0x0063
            r5.close()
        L_0x0063:
            throw r0
        L_0x0064:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.a(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    private static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, null);
        int count = rawQuery.getCount();
        int columnCount = rawQuery.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("raws:" + count + ",columns:" + columnCount + "\n");
        if (count <= 0 || !rawQuery.moveToFirst()) {
            return sb.toString();
        }
        do {
            sb.append("\n");
            for (int i = 0; i < columnCount; i++) {
                try {
                    sb.append(rawQuery.getString(i)).append(StorageInterface.KEY_SPLITER);
                } catch (Exception e) {
                }
            }
            sb.append("\n");
        } while (rawQuery.moveToNext());
        return sb.toString();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v5
  assigns: []
  uses: []
  mth insns count: 65
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
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0078  */
    /* JADX WARNING: Unknown variable types count: 9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int d(android.content.Context r5) {
        /*
            r0 = 0
            r1 = 0
            java.lang.System.currentTimeMillis()
            android.database.sqlite.SQLiteDatabase r2 = c(r5)     // Catch:{ Throwable -> 0x0061, all -> 0x0074 }
            if (r2 != 0) goto L_0x001d
            r0 = -1
            if (r1 == 0) goto L_0x0011
            r1.close()
        L_0x0011:
            if (r2 == 0) goto L_0x001c
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x001c
            r2.close()
        L_0x001c:
            return r0
        L_0x001d:
            java.lang.String r3 = "select * from meta"
            r4 = 0
            android.database.Cursor r1 = r2.rawQuery(r3, r4)     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            int r3 = r1.getCount()     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            r1.getColumnCount()     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            if (r3 <= 0) goto L_0x0049
            boolean r3 = r1.moveToFirst()     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            if (r3 == 0) goto L_0x0049
        L_0x0033:
            r3 = 0
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            java.lang.String r4 = "version"
            boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            if (r3 == 0) goto L_0x005a
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            int r0 = java.lang.Integer.parseInt(r3)     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()
        L_0x004e:
            if (r2 == 0) goto L_0x001c
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x005a:
            boolean r3 = r1.moveToNext()     // Catch:{ Throwable -> 0x0089, all -> 0x0087 }
            if (r3 != 0) goto L_0x0033
            goto L_0x0049
        L_0x0061:
            r2 = move-exception
            r2 = r1
        L_0x0063:
            if (r1 == 0) goto L_0x0068
            r1.close()
        L_0x0068:
            if (r2 == 0) goto L_0x001c
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x0074:
            r0 = move-exception
            r2 = r1
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r1.close()
        L_0x007b:
            if (r2 == 0) goto L_0x0086
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0086
            r2.close()
        L_0x0086:
            throw r0
        L_0x0087:
            r0 = move-exception
            goto L_0x0076
        L_0x0089:
            r3 = move-exception
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.d(android.content.Context):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        if (r0 == false) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r10, com.tencent.smtt.sdk.CookieManager.a r11, java.lang.String r12, boolean r13, boolean r14) {
        /*
            r1 = 0
            r3 = 0
            r2 = 1
            if (r10 != 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            com.tencent.smtt.sdk.CookieManager$a r0 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS
            if (r11 != r0) goto L_0x0010
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L_0x0005
        L_0x0010:
            java.lang.String r0 = ","
            java.lang.String[] r4 = r12.split(r0)
            if (r4 == 0) goto L_0x0005
            int r0 = r4.length
            if (r0 < r2) goto L_0x0005
            android.database.sqlite.SQLiteDatabase r5 = c(r10)
            if (r5 == 0) goto L_0x0005
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            java.lang.String r0 = "select * from cookies"
            r7 = 0
            android.database.Cursor r1 = r5.rawQuery(r0, r7)     // Catch:{ Throwable -> 0x00f4 }
            int r0 = r1.getCount()     // Catch:{ Throwable -> 0x00f4 }
            if (r0 <= 0) goto L_0x005c
            boolean r0 = r1.moveToFirst()     // Catch:{ Throwable -> 0x00f4 }
            if (r0 == 0) goto L_0x005c
        L_0x0039:
            java.lang.String r0 = "host_key"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r7 = r1.getString(r0)     // Catch:{ Throwable -> 0x00f4 }
            com.tencent.smtt.sdk.CookieManager$a r0 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS     // Catch:{ Throwable -> 0x00f4 }
            if (r11 != r0) goto L_0x00a0
            int r8 = r4.length     // Catch:{ Throwable -> 0x00f4 }
            r0 = r3
        L_0x0049:
            if (r0 >= r8) goto L_0x0162
            r9 = r4[r0]     // Catch:{ Throwable -> 0x00f4 }
            boolean r9 = r7.equals(r9)     // Catch:{ Throwable -> 0x00f4 }
            if (r9 == 0) goto L_0x009d
            r0 = r2
        L_0x0054:
            if (r0 != 0) goto L_0x00a0
        L_0x0056:
            boolean r0 = r1.moveToNext()     // Catch:{ Throwable -> 0x00f4 }
            if (r0 != 0) goto L_0x0039
        L_0x005c:
            if (r1 == 0) goto L_0x0061
            r1.close()
        L_0x0061:
            if (r5 == 0) goto L_0x006c
            boolean r0 = r5.isOpen()
            if (r0 == 0) goto L_0x006c
            r5.close()
        L_0x006c:
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L_0x0005
            b(r10)
            java.util.Set r0 = r6.entrySet()
            java.util.Iterator r3 = r0.iterator()
        L_0x007d:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0135
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            com.tencent.smtt.sdk.CookieManager r4 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r4.setCookie(r1, r0, r2)
            goto L_0x007d
        L_0x009d:
            int r0 = r0 + 1
            goto L_0x0049
        L_0x00a0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00f4 }
            r0.<init>()     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r8 = "value"
            int r8 = r1.getColumnIndex(r8)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r8 = r1.getString(r8)     // Catch:{ Throwable -> 0x00f4 }
            r0.append(r8)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r8 = ";"
            java.lang.StringBuilder r8 = r0.append(r8)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r9 = "name"
            int r9 = r1.getColumnIndex(r9)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r9 = r1.getString(r9)     // Catch:{ Throwable -> 0x00f4 }
            r8.append(r9)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r8 = ";"
            java.lang.StringBuilder r8 = r0.append(r8)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r9 = "expires_utc"
            int r9 = r1.getColumnIndex(r9)     // Catch:{ Throwable -> 0x00f4 }
            int r9 = r1.getInt(r9)     // Catch:{ Throwable -> 0x00f4 }
            r8.append(r9)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r8 = ";"
            java.lang.StringBuilder r8 = r0.append(r8)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r9 = "priority"
            int r9 = r1.getColumnIndex(r9)     // Catch:{ Throwable -> 0x00f4 }
            int r9 = r1.getInt(r9)     // Catch:{ Throwable -> 0x00f4 }
            r8.append(r9)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00f4 }
            r6.put(r7, r0)     // Catch:{ Throwable -> 0x00f4 }
            goto L_0x0056
        L_0x00f4:
            r0 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r4.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r7 = "getCookieDBVersion exception:"
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ all -> 0x0123 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0123 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0123 }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x0123 }
            if (r1 == 0) goto L_0x0116
            r1.close()
        L_0x0116:
            if (r5 == 0) goto L_0x006c
            boolean r0 = r5.isOpen()
            if (r0 == 0) goto L_0x006c
            r5.close()
            goto L_0x006c
        L_0x0123:
            r0 = move-exception
            if (r1 == 0) goto L_0x0129
            r1.close()
        L_0x0129:
            if (r5 == 0) goto L_0x0134
            boolean r1 = r5.isOpen()
            if (r1 == 0) goto L_0x0134
            r5.close()
        L_0x0134:
            throw r0
        L_0x0135:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x015a
            com.tencent.smtt.sdk.CookieManager r0 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r0.flush()
        L_0x0142:
            if (r13 == 0) goto L_0x0005
            android.database.sqlite.SQLiteDatabase r0 = c(r10)
            a(r0)
            int r0 = d(r10)
            r1 = -1
            if (r0 == r1) goto L_0x0005
            com.tencent.smtt.sdk.CookieManager.getInstance()
            com.tencent.smtt.sdk.CookieManager.setROMCookieDBVersion(r10, r0)
            goto L_0x0005
        L_0x015a:
            com.tencent.smtt.sdk.CookieSyncManager r0 = com.tencent.smtt.sdk.CookieSyncManager.getInstance()
            r0.sync()
            goto L_0x0142
        L_0x0162:
            r0 = r3
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.a(android.content.Context, com.tencent.smtt.sdk.CookieManager$a, java.lang.String, boolean, boolean):void");
    }
}
