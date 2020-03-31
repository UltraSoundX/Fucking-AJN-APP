package com.baidu.location.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import com.baidu.location.Jni;
import com.baidu.location.e.b;
import com.baidu.location.e.i;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.tencent.mid.api.MidEntity;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.File;
import java.util.HashMap;
import net.sf.json.util.JSONUtils;
import org.json.JSONObject;

public class h {
    private static Object c = new Object();
    private static h d = null;
    private static final String e = (j.i() + "/hst.db");
    a a = null;
    a b = null;
    /* access modifiers changed from: private */
    public SQLiteDatabase f = null;
    /* access modifiers changed from: private */
    public boolean g = false;
    private String h = null;
    private int i = -2;

    class a extends e {
        private String b;
        private String c;
        private boolean d;
        private boolean e;

        a() {
            this.b = null;
            this.c = null;
            this.d = true;
            this.e = false;
            this.k = new HashMap();
        }

        public void a() {
            this.i = 1;
            this.h = j.d();
            String f = Jni.f(this.c);
            this.c = null;
            this.k.put("bloc", f);
        }

        public void a(String str, String str2) {
            if (!h.this.g) {
                h.this.g = true;
                this.b = str;
                this.c = str2;
                b(j.f);
            }
        }

        public void a(boolean z) {
            JSONObject jSONObject = null;
            if (z && this.j != null) {
                try {
                    String str = this.j;
                    if (this.d) {
                        JSONObject jSONObject2 = new JSONObject(str);
                        if (jSONObject2.has("content")) {
                            jSONObject = jSONObject2.getJSONObject("content");
                        }
                        if (jSONObject != null && jSONObject.has("imo")) {
                            Long valueOf = Long.valueOf(jSONObject.getJSONObject("imo").getString(MidEntity.TAG_MAC));
                            int i = jSONObject.getJSONObject("imo").getInt("mv");
                            if (Jni.d(this.b).longValue() == valueOf.longValue()) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                                contentValues.put("hst", Integer.valueOf(i));
                                try {
                                    if (h.this.f.update("hstdata", contentValues, "id = \"" + valueOf + JSONUtils.DOUBLE_QUOTE, null) <= 0) {
                                        contentValues.put(Config.FEED_LIST_ITEM_CUSTOM_ID, valueOf);
                                        h.this.f.insert("hstdata", null, contentValues);
                                    }
                                } catch (Exception e2) {
                                }
                                Bundle bundle = new Bundle();
                                bundle.putByteArray(MidEntity.TAG_MAC, this.b.getBytes());
                                bundle.putInt("hotspot", i);
                                h.this.a(bundle);
                            }
                        }
                    }
                } catch (Exception e3) {
                }
            } else if (this.d) {
                h.this.f();
            }
            if (this.k != null) {
                this.k.clear();
            }
            h.this.g = false;
        }
    }

    public static h a() {
        h hVar;
        synchronized (c) {
            if (d == null) {
                d = new h();
            }
            hVar = d;
        }
        return hVar;
    }

    private String a(boolean z) {
        com.baidu.location.e.a f2 = b.a().f();
        com.baidu.location.e.h p = i.a().p();
        StringBuffer stringBuffer = new StringBuffer(1024);
        if (f2 != null && f2.b()) {
            stringBuffer.append(f2.h());
        }
        if (p != null && p.a() > 1) {
            stringBuffer.append(p.a(15));
        } else if (i.a().m() != null) {
            stringBuffer.append(i.a().m());
        }
        if (z) {
            stringBuffer.append("&imo=1");
        }
        stringBuffer.append(com.baidu.location.g.b.a().a(false));
        stringBuffer.append(a.a().d());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        a.a().a(bundle, (int) ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD);
    }

    /* access modifiers changed from: private */
    public void f() {
        Bundle bundle = new Bundle();
        bundle.putInt("hotspot", -1);
        a(bundle);
    }

    public void a(String str) {
        JSONObject jSONObject = null;
        if (!this.g) {
            try {
                JSONObject jSONObject2 = new JSONObject(str);
                if (jSONObject2.has("content")) {
                    jSONObject = jSONObject2.getJSONObject("content");
                }
                if (jSONObject != null && jSONObject.has("imo")) {
                    Long valueOf = Long.valueOf(jSONObject.getJSONObject("imo").getString(MidEntity.TAG_MAC));
                    int i2 = jSONObject.getJSONObject("imo").getInt("mv");
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                    contentValues.put("hst", Integer.valueOf(i2));
                    try {
                        if (this.f.update("hstdata", contentValues, "id = \"" + valueOf + JSONUtils.DOUBLE_QUOTE, null) <= 0) {
                            contentValues.put(Config.FEED_LIST_ITEM_CUSTOM_ID, valueOf);
                            this.f.insert("hstdata", null, contentValues);
                        }
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
            }
        }
    }

    public void b() {
        try {
            File file = new File(e);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.f = SQLiteDatabase.openOrCreateDatabase(file, null);
                this.f.execSQL("CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                this.f.setVersion(1);
            }
        } catch (Exception e2) {
            this.f = null;
        }
    }

    public void c() {
        if (this.f != null) {
            try {
                this.f.close();
            } catch (Exception e2) {
            } finally {
                this.f = null;
            }
        }
    }

    public synchronized int d() {
        int i2;
        Cursor cursor = null;
        synchronized (this) {
            i2 = -3;
            if (!this.g) {
                try {
                    if (i.j() && this.f != null) {
                        WifiInfo l = i.a().l();
                        if (!(l == null || l.getBSSID() == null)) {
                            String replace = l.getBSSID().replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
                            Long d2 = Jni.d(replace);
                            if (this.h == null || !replace.equals(this.h) || this.i <= -2) {
                                try {
                                    Cursor rawQuery = this.f.rawQuery("select * from hstdata where id = \"" + d2 + "\";", null);
                                    if (rawQuery == null || !rawQuery.moveToFirst()) {
                                        i2 = -2;
                                    } else {
                                        i2 = rawQuery.getInt(1);
                                        this.h = replace;
                                        this.i = i2;
                                    }
                                    if (rawQuery != null) {
                                        try {
                                            rawQuery.close();
                                        } catch (Exception e2) {
                                        }
                                    }
                                } catch (Exception e3) {
                                    if (cursor != null) {
                                        try {
                                            cursor.close();
                                        } catch (Exception e4) {
                                        }
                                    }
                                } catch (Exception e5) {
                                    i2 = -3;
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    if (cursor != null) {
                                        try {
                                            cursor.close();
                                        } catch (Exception e6) {
                                        }
                                    }
                                    throw th2;
                                }
                            } else {
                                i2 = this.i;
                            }
                        }
                    }
                } catch (Exception e7) {
                }
                this.i = i2;
            }
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bf, code lost:
        if (r2 != null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077 A[SYNTHETIC, Splitter:B:24:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c A[SYNTHETIC, Splitter:B:27:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00be A[ExcHandler: all (r0v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
            r10 = this;
            r2 = 0
            r0 = 1
            boolean r1 = r10.g
            if (r1 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            boolean r1 = com.baidu.location.e.i.j()     // Catch:{ Exception -> 0x0097 }
            if (r1 == 0) goto L_0x00ca
            android.database.sqlite.SQLiteDatabase r1 = r10.f     // Catch:{ Exception -> 0x0097 }
            if (r1 == 0) goto L_0x00ca
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()     // Catch:{ Exception -> 0x0097 }
            android.net.wifi.WifiInfo r1 = r1.l()     // Catch:{ Exception -> 0x0097 }
            if (r1 == 0) goto L_0x00c5
            java.lang.String r3 = r1.getBSSID()     // Catch:{ Exception -> 0x0097 }
            if (r3 == 0) goto L_0x00c5
            java.lang.String r1 = r1.getBSSID()     // Catch:{ Exception -> 0x0097 }
            java.lang.String r3 = ":"
            java.lang.String r4 = ""
            java.lang.String r3 = r1.replace(r3, r4)     // Catch:{ Exception -> 0x0097 }
            java.lang.Long r4 = com.baidu.location.Jni.d(r3)     // Catch:{ Exception -> 0x0097 }
            r1 = 0
            android.database.sqlite.SQLiteDatabase r5 = r10.f     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            r6.<init>()     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            java.lang.String r7 = "select * from hstdata where id = \""
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            java.lang.String r6 = "\";"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            r6 = 0
            android.database.Cursor r2 = r5.rawQuery(r4, r6)     // Catch:{ Exception -> 0x00b4, all -> 0x00be }
            if (r2 == 0) goto L_0x00b2
            boolean r4 = r2.moveToFirst()     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            if (r4 == 0) goto L_0x00b2
            r4 = 1
            int r4 = r2.getInt(r4)     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r5 = 2
            int r5 = r2.getInt(r5)     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r8 = (long) r5
            long r6 = r6 - r8
            r8 = 259200(0x3f480, double:1.28062E-318)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 <= 0) goto L_0x009a
        L_0x0074:
            r1 = r0
        L_0x0075:
            if (r2 == 0) goto L_0x007a
            r2.close()     // Catch:{ Exception -> 0x00cf }
        L_0x007a:
            if (r1 == 0) goto L_0x0006
            com.baidu.location.a.h$a r0 = r10.a     // Catch:{ Exception -> 0x0097 }
            if (r0 != 0) goto L_0x0087
            com.baidu.location.a.h$a r0 = new com.baidu.location.a.h$a     // Catch:{ Exception -> 0x0097 }
            r0.<init>()     // Catch:{ Exception -> 0x0097 }
            r10.a = r0     // Catch:{ Exception -> 0x0097 }
        L_0x0087:
            com.baidu.location.a.h$a r0 = r10.a     // Catch:{ Exception -> 0x0097 }
            if (r0 == 0) goto L_0x0006
            com.baidu.location.a.h$a r0 = r10.a     // Catch:{ Exception -> 0x0097 }
            r1 = 1
            java.lang.String r1 = r10.a(r1)     // Catch:{ Exception -> 0x0097 }
            r0.a(r3, r1)     // Catch:{ Exception -> 0x0097 }
            goto L_0x0006
        L_0x0097:
            r0 = move-exception
            goto L_0x0006
        L_0x009a:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r0.<init>()     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            java.lang.String r5 = "mac"
            byte[] r6 = r3.getBytes()     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r0.putByteArray(r5, r6)     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            java.lang.String r5 = "hotspot"
            r0.putInt(r5, r4)     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r10.a(r0)     // Catch:{ Exception -> 0x00d3, all -> 0x00be }
            r0 = r1
            goto L_0x0074
        L_0x00b2:
            r1 = r0
            goto L_0x0075
        L_0x00b4:
            r0 = move-exception
            r0 = r2
        L_0x00b6:
            if (r0 == 0) goto L_0x007a
            r0.close()     // Catch:{ Exception -> 0x00bc }
            goto L_0x007a
        L_0x00bc:
            r0 = move-exception
            goto L_0x007a
        L_0x00be:
            r0 = move-exception
            if (r2 == 0) goto L_0x00c4
            r2.close()     // Catch:{ Exception -> 0x00d1 }
        L_0x00c4:
            throw r0     // Catch:{ Exception -> 0x0097 }
        L_0x00c5:
            r10.f()     // Catch:{ Exception -> 0x0097 }
            goto L_0x0006
        L_0x00ca:
            r10.f()     // Catch:{ Exception -> 0x0097 }
            goto L_0x0006
        L_0x00cf:
            r0 = move-exception
            goto L_0x007a
        L_0x00d1:
            r1 = move-exception
            goto L_0x00c4
        L_0x00d3:
            r0 = move-exception
            r0 = r2
            goto L_0x00b6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.h.e():void");
    }
}
