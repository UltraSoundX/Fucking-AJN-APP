package com.baidu.location.d;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.g.e;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

final class j {
    private static final String d = String.format(Locale.US, "DELETE FROM LOG WHERE timestamp NOT IN (SELECT timestamp FROM LOG ORDER BY timestamp DESC LIMIT %d);", new Object[]{Integer.valueOf(3000)});
    private static final String e = String.format(Locale.US, "SELECT * FROM LOG ORDER BY timestamp DESC LIMIT %d;", new Object[]{Integer.valueOf(3)});
    private String a = null;
    private final SQLiteDatabase b;
    private final a c;

    private class a extends e {
        private int b;
        private long c;
        private String d;
        private boolean e;
        private boolean f;

        /* renamed from: q reason: collision with root package name */
        private j f375q;

        a(j jVar) {
            this.f375q = jVar;
            this.d = null;
            this.e = false;
            this.f = false;
            this.k = new HashMap();
            this.b = 0;
            this.c = -1;
        }

        /* access modifiers changed from: private */
        public void b() {
            if (!this.e) {
                this.d = this.f375q.b();
                if (this.c != -1 && this.c + 86400000 <= System.currentTimeMillis()) {
                    this.b = 0;
                    this.c = -1;
                }
                if (this.d != null && this.b < 2) {
                    this.e = true;
                    b("https://ofloc.map.baidu.com/offline_loc");
                }
            }
        }

        public void a() {
            this.k.clear();
            this.k.put("qt", "ofbh");
            this.k.put("req", this.d);
            this.h = g.b;
        }

        public void a(boolean z) {
            this.f = false;
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    if (jSONObject != null && jSONObject.has("error") && jSONObject.getInt("error") == 161) {
                        this.f = true;
                    }
                } catch (Exception e2) {
                }
            }
            if (!this.f) {
                this.b++;
                this.c = System.currentTimeMillis();
            }
            this.f375q.a(this.f);
            this.e = false;
        }
    }

    j(SQLiteDatabase sQLiteDatabase) {
        this.b = sQLiteDatabase;
        this.c = new a(this);
        if (this.b != null && this.b.isOpen()) {
            try {
                this.b.execSQL("CREATE TABLE IF NOT EXISTS LOG(timestamp LONG PRIMARY KEY, log VARCHAR(4000))");
            } catch (Exception e2) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z && this.a != null) {
            String format = String.format("DELETE FROM LOG WHERE timestamp in (%s);", new Object[]{this.a});
            try {
                if (this.a.length() > 0) {
                    this.b.execSQL(format);
                }
            } catch (Exception e2) {
            }
        }
        this.a = null;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v7
  assigns: []
  uses: []
  mth insns count: 55
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
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[SYNTHETIC, Splitter:B:31:0x006d] */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b() {
        /*
            r9 = this;
            r0 = 0
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r9.b     // Catch:{ Exception -> 0x0077, all -> 0x0067 }
            java.lang.String r4 = e     // Catch:{ Exception -> 0x0077, all -> 0x0067 }
            r5 = 0
            android.database.Cursor r1 = r1.rawQuery(r4, r5)     // Catch:{ Exception -> 0x0077, all -> 0x0067 }
            if (r1 == 0) goto L_0x005f
            int r4 = r1.getCount()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            if (r4 <= 0) goto L_0x005f
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r4.<init>()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r1.moveToFirst()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
        L_0x0024:
            boolean r5 = r1.isAfterLast()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            if (r5 != 0) goto L_0x0050
            r5 = 1
            java.lang.String r5 = r1.getString(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r2.put(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            int r5 = r4.length()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            if (r5 == 0) goto L_0x003d
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
        L_0x003d:
            r5 = 0
            long r6 = r1.getLong(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r4.append(r6)     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r1.moveToNext()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            goto L_0x0024
        L_0x0049:
            r2 = move-exception
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Exception -> 0x0071 }
        L_0x004f:
            return r0
        L_0x0050:
            java.lang.String r5 = "ofloc"
            r3.put(r5, r2)     // Catch:{ JSONException -> 0x007a }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x007a }
        L_0x0059:
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
            r9.a = r2     // Catch:{ Exception -> 0x0049, all -> 0x0075 }
        L_0x005f:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Exception -> 0x0065 }
            goto L_0x004f
        L_0x0065:
            r1 = move-exception
            goto L_0x004f
        L_0x0067:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x006b:
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ Exception -> 0x0073 }
        L_0x0070:
            throw r0
        L_0x0071:
            r1 = move-exception
            goto L_0x004f
        L_0x0073:
            r1 = move-exception
            goto L_0x0070
        L_0x0075:
            r0 = move-exception
            goto L_0x006b
        L_0x0077:
            r1 = move-exception
            r1 = r0
            goto L_0x004a
        L_0x007a:
            r2 = move-exception
            goto L_0x0059
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.j.b():java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.c.b();
    }

    /* access modifiers changed from: 0000 */
    public void a(String str) {
        try {
            this.b.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO LOG VALUES (%d,\"%s\");", new Object[]{Long.valueOf(System.currentTimeMillis()), Jni.e(str)}));
            this.b.execSQL(d);
        } catch (Exception e2) {
        }
    }
}
