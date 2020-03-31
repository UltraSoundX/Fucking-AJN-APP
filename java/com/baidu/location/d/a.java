package com.baidu.location.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.k;
import com.baidu.location.e.h;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.util.List;
import java.util.Locale;
import net.sf.json.util.JSONUtils;
import org.json.JSONObject;

public final class a {
    private static a b = null;
    private static final String l = (Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/");
    /* access modifiers changed from: private */
    public static final String m = (Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata" + "/ls.db");
    public boolean a = false;
    private String c = null;
    private boolean d = false;
    private boolean e = false;
    private double f = 0.0d;
    private double g = 0.0d;
    private double h = 0.0d;
    private double i = 0.0d;
    private double j = 0.0d;
    /* access modifiers changed from: private */
    public volatile boolean k = false;
    private Handler n = null;

    /* renamed from: com.baidu.location.d.a$a reason: collision with other inner class name */
    private class C0027a extends AsyncTask<Boolean, Void, Boolean> {
        private C0027a() {
        }

        /* synthetic */ C0027a(a aVar, b bVar) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Boolean... boolArr) {
            SQLiteDatabase sQLiteDatabase = null;
            if (boolArr.length != 4) {
                return Boolean.valueOf(false);
            }
            try {
                sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, null);
            } catch (Exception e) {
            }
            if (sQLiteDatabase == null) {
                return Boolean.valueOf(false);
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            try {
                sQLiteDatabase.beginTransaction();
                if (boolArr[0].booleanValue()) {
                    try {
                        sQLiteDatabase.execSQL("delete from wof where ac < " + (currentTimeMillis - 35));
                    } catch (Exception e2) {
                    }
                }
                if (boolArr[1].booleanValue()) {
                    try {
                        sQLiteDatabase.execSQL("delete from bdcltb09 where ac is NULL or ac < " + (currentTimeMillis - 130));
                    } catch (Exception e3) {
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
            } catch (Exception e4) {
            }
            return Boolean.valueOf(true);
        }
    }

    private class b extends AsyncTask<Object, Void, Boolean> {
        private b() {
        }

        /* synthetic */ b(a aVar, b bVar) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Object... objArr) {
            SQLiteDatabase sQLiteDatabase;
            SQLiteDatabase sQLiteDatabase2 = null;
            if (objArr.length != 4) {
                a.this.k = false;
                return Boolean.valueOf(false);
            }
            try {
                sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, null);
            } catch (Exception e) {
                sQLiteDatabase = sQLiteDatabase2;
            }
            if (sQLiteDatabase == null) {
                a.this.k = false;
                return Boolean.valueOf(false);
            }
            try {
                sQLiteDatabase.beginTransaction();
                a.this.a(objArr[0], objArr[1], sQLiteDatabase);
                a.this.a(objArr[2], objArr[3], sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
            } catch (Exception e2) {
            }
            a.this.k = false;
            return Boolean.valueOf(true);
        }
    }

    private a() {
        b();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    /* access modifiers changed from: private */
    public void a(h hVar, BDLocation bDLocation, SQLiteDatabase sQLiteDatabase) {
        int i2;
        int i3;
        double d2;
        boolean z;
        double d3;
        if (bDLocation != null && bDLocation.o() == 161) {
            if (("wf".equals(bDLocation.H()) || bDLocation.l() < 300.0f) && hVar.a != null) {
                int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
                System.currentTimeMillis();
                int i4 = 0;
                for (ScanResult scanResult : hVar.a) {
                    if (scanResult.level != 0) {
                        int i5 = i4 + 1;
                        if (i5 <= 6) {
                            ContentValues contentValues = new ContentValues();
                            String c2 = Jni.c(scanResult.BSSID.replace(Config.TRACE_TODAY_VISIT_SPLIT, ""));
                            try {
                                Cursor rawQuery = sQLiteDatabase.rawQuery("select * from wof where id = \"" + c2 + "\";", null);
                                if (rawQuery == null || !rawQuery.moveToFirst()) {
                                    i2 = 0;
                                    i3 = 0;
                                    d2 = 0.0d;
                                    z = false;
                                    d3 = 0.0d;
                                } else {
                                    double d4 = rawQuery.getDouble(1) - 113.2349d;
                                    double d5 = rawQuery.getDouble(2) - 432.1238d;
                                    int i6 = rawQuery.getInt(4);
                                    i2 = rawQuery.getInt(5);
                                    i3 = i6;
                                    d2 = d4;
                                    double d6 = d5;
                                    z = true;
                                    d3 = d6;
                                }
                                if (rawQuery != null) {
                                    rawQuery.close();
                                }
                                if (!z) {
                                    contentValues.put("mktime", Double.valueOf(bDLocation.i() + 113.2349d));
                                    contentValues.put("time", Double.valueOf(bDLocation.h() + 432.1238d));
                                    contentValues.put("bc", Integer.valueOf(1));
                                    contentValues.put("cc", Integer.valueOf(1));
                                    contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                    contentValues.put(Config.FEED_LIST_ITEM_CUSTOM_ID, c2);
                                    sQLiteDatabase.insert("wof", null, contentValues);
                                } else if (i2 == 0) {
                                    i4 = i5;
                                } else {
                                    float[] fArr = new float[1];
                                    Location.distanceBetween(d3, d2, bDLocation.h(), bDLocation.i(), fArr);
                                    if (fArr[0] > 1500.0f) {
                                        int i7 = i2 + 1;
                                        if (i7 <= 10 || i7 <= i3 * 3) {
                                            contentValues.put("cc", Integer.valueOf(i7));
                                        } else {
                                            contentValues.put("mktime", Double.valueOf(bDLocation.i() + 113.2349d));
                                            contentValues.put("time", Double.valueOf(bDLocation.h() + 432.1238d));
                                            contentValues.put("bc", Integer.valueOf(1));
                                            contentValues.put("cc", Integer.valueOf(1));
                                            contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                        }
                                    } else {
                                        double h2 = ((d3 * ((double) i3)) + bDLocation.h()) / ((double) (i3 + 1));
                                        ContentValues contentValues2 = contentValues;
                                        contentValues2.put("mktime", Double.valueOf((((d2 * ((double) i3)) + bDLocation.i()) / ((double) (i3 + 1))) + 113.2349d));
                                        contentValues.put("time", Double.valueOf(h2 + 432.1238d));
                                        contentValues.put("bc", Integer.valueOf(i3 + 1));
                                        contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                    }
                                    try {
                                        if (sQLiteDatabase.update("wof", contentValues, "id = \"" + c2 + JSONUtils.DOUBLE_QUOTE, null) <= 0) {
                                        }
                                    } catch (Exception e2) {
                                    }
                                }
                            } catch (Exception e3) {
                            }
                            i4 = i5;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0068, code lost:
        if (r0 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007d, code lost:
        r6 = r1;
        r1 = r0;
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0067 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:5:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076 A[SYNTHETIC, Splitter:B:23:0x0076] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r8, android.database.sqlite.SQLiteDatabase r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto L_0x000b
            java.lang.String r1 = r7.c
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x000c
        L_0x000b:
            return
        L_0x000c:
            r1 = 0
            r7.d = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            r1.<init>()     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            java.lang.String r2 = "select * from bdcltb09 where id = \""
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            java.lang.String r2 = "\";"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            r2 = 0
            android.database.Cursor r0 = r9.rawQuery(r1, r2)     // Catch:{ Exception -> 0x0067, all -> 0x0070 }
            r7.c = r8     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            boolean r1 = r0.moveToFirst()     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            if (r1 == 0) goto L_0x005f
            r1 = 1
            double r2 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r4 = 4653148304163072062(0x40934dbaacd9e83e, double:1235.4323)
            double r2 = r2 - r4
            r7.g = r2     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r1 = 2
            double r2 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r4 = 4661478502002851840(0x40b0e60000000000, double:4326.0)
            double r2 = r2 - r4
            r7.f = r2     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r1 = 3
            double r2 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r4 = 4657424210545395263(0x40a27ea4b5dcc63f, double:2367.3217)
            double r2 = r2 - r4
            r7.h = r2     // Catch:{ Exception -> 0x0067, all -> 0x007c }
            r1 = 1
            r7.d = r1     // Catch:{ Exception -> 0x0067, all -> 0x007c }
        L_0x005f:
            if (r0 == 0) goto L_0x000b
            r0.close()     // Catch:{ Exception -> 0x0065 }
            goto L_0x000b
        L_0x0065:
            r0 = move-exception
            goto L_0x000b
        L_0x0067:
            r1 = move-exception
            if (r0 == 0) goto L_0x000b
            r0.close()     // Catch:{ Exception -> 0x006e }
            goto L_0x000b
        L_0x006e:
            r0 = move-exception
            goto L_0x000b
        L_0x0070:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ Exception -> 0x007a }
        L_0x0079:
            throw r0
        L_0x007a:
            r1 = move-exception
            goto L_0x0079
        L_0x007c:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.a(java.lang.String, android.database.sqlite.SQLiteDatabase):void");
    }

    /* access modifiers changed from: private */
    public void a(String str, com.baidu.location.e.a aVar, SQLiteDatabase sQLiteDatabase) {
        float f2;
        double d2;
        boolean z = false;
        double d3 = 0.0d;
        if (aVar.b() && k.c().h()) {
            System.currentTimeMillis();
            int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            String g2 = aVar.g();
            try {
                JSONObject jSONObject = new JSONObject(str);
                int parseInt = Integer.parseInt(jSONObject.getJSONObject("result").getString("error"));
                if (parseInt == 161) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    if (jSONObject2.has("clf")) {
                        String string = jSONObject2.getString("clf");
                        if (string.equals("0")) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject(Config.EVENT_HEAT_POINT);
                            d3 = Double.parseDouble(jSONObject3.getString(Config.EVENT_HEAT_X));
                            d2 = Double.parseDouble(jSONObject3.getString("y"));
                            f2 = Float.parseFloat(jSONObject2.getString("radius"));
                        } else {
                            String[] split = string.split("\\|");
                            d3 = Double.parseDouble(split[0]);
                            d2 = Double.parseDouble(split[1]);
                            f2 = Float.parseFloat(split[2]);
                        }
                    }
                    z = true;
                    f2 = 0.0f;
                    d2 = 0.0d;
                } else {
                    if (parseInt == 167) {
                        sQLiteDatabase.delete("bdcltb09", "id = \"" + g2 + JSONUtils.DOUBLE_QUOTE, null);
                        return;
                    }
                    z = true;
                    f2 = 0.0f;
                    d2 = 0.0d;
                }
                if (!z) {
                    double d4 = d3 + 1235.4323d;
                    double d5 = d2 + 2367.3217d;
                    float f3 = 4326.0f + f2;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", Double.valueOf(d4));
                    contentValues.put("tag", Float.valueOf(f3));
                    contentValues.put("type", Double.valueOf(d5));
                    contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                    try {
                        if (sQLiteDatabase.update("bdcltb09", contentValues, "id = \"" + g2 + JSONUtils.DOUBLE_QUOTE, null) <= 0) {
                            contentValues.put(Config.FEED_LIST_ITEM_CUSTOM_ID, g2);
                            sQLiteDatabase.insert("bdcltb09", null, contentValues);
                        }
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
            }
        }
    }

    private void a(String str, List<ScanResult> list) {
        SQLiteDatabase sQLiteDatabase = null;
        this.d = false;
        this.e = false;
        try {
            sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(m, null);
        } catch (Throwable th) {
        }
        if (!(str == null || sQLiteDatabase == null)) {
            a(str, sQLiteDatabase);
        }
        if (!(list == null || sQLiteDatabase == null)) {
            a(list, sQLiteDatabase);
        }
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            sQLiteDatabase.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ff A[SYNTHETIC, Splitter:B:41:0x00ff] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.util.List<android.net.wifi.ScanResult> r25, android.database.sqlite.SQLiteDatabase r26) {
        /*
            r24 = this;
            java.lang.System.currentTimeMillis()
            r2 = 0
            r0 = r24
            r0.e = r2
            if (r25 == 0) goto L_0x0010
            int r2 = r25.size()
            if (r2 != 0) goto L_0x0011
        L_0x0010:
            return
        L_0x0011:
            if (r26 == 0) goto L_0x0010
            if (r25 == 0) goto L_0x0010
            r2 = 0
            r16 = 0
            r14 = 0
            r12 = 0
            r11 = 0
            r3 = 8
            double[] r0 = new double[r3]
            r21 = r0
            r19 = 0
            r18 = 0
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.util.Iterator r5 = r25.iterator()
            r3 = r2
        L_0x0030:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x0040
            java.lang.Object r2 = r5.next()
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            r6 = 10
            if (r3 <= r6) goto L_0x00a8
        L_0x0040:
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            r3.<init>()     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            java.lang.String r5 = "select * from wof where id in ("
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            java.lang.String r4 = ");"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            r4 = 0
            r0 = r26
            android.database.Cursor r13 = r0.rawQuery(r3, r4)     // Catch:{ Exception -> 0x01c3, all -> 0x01be }
            boolean r2 = r13.moveToFirst()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            if (r2 == 0) goto L_0x0125
        L_0x006b:
            boolean r2 = r13.isAfterLast()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            if (r2 != 0) goto L_0x0110
            r2 = 1
            double r2 = r13.getDouble(r2)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r4 = 4637668614646953253(0x405c4f089a027525, double:113.2349)
            double r4 = r2 - r4
            r2 = 2
            double r2 = r13.getDouble(r2)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r6 = 4646309618475430891(0x407b01fb15b573eb, double:432.1238)
            double r2 = r2 - r6
            r6 = 4
            int r6 = r13.getInt(r6)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r7 = 5
            int r7 = r13.getInt(r7)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r8 = 8
            if (r7 <= r8) goto L_0x00d0
            if (r7 <= r6) goto L_0x00d0
            r13.moveToNext()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            goto L_0x006b
        L_0x009c:
            r2 = move-exception
            r2 = r13
        L_0x009e:
            if (r2 == 0) goto L_0x0010
            r2.close()     // Catch:{ Exception -> 0x00a5 }
            goto L_0x0010
        L_0x00a5:
            r2 = move-exception
            goto L_0x0010
        L_0x00a8:
            if (r3 <= 0) goto L_0x00af
            java.lang.String r6 = ","
            r4.append(r6)
        L_0x00af:
            int r3 = r3 + 1
            java.lang.String r2 = r2.BSSID
            java.lang.String r6 = ":"
            java.lang.String r7 = ""
            java.lang.String r2 = r2.replace(r6, r7)
            java.lang.String r2 = com.baidu.location.Jni.c(r2)
            java.lang.String r6 = "\""
            java.lang.StringBuffer r6 = r4.append(r6)
            java.lang.StringBuffer r2 = r6.append(r2)
            java.lang.String r6 = "\""
            r2.append(r6)
            goto L_0x0030
        L_0x00d0:
            r0 = r24
            boolean r6 = r0.d     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            if (r6 == 0) goto L_0x012f
            r6 = 1
            float[] r10 = new float[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r0 = r24
            double r6 = r0.h     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r0 = r24
            double r8 = r0.g     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            android.location.Location.distanceBetween(r2, r4, r6, r8, r10)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r6 = 0
            r6 = r10[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r6 = (double) r6     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r0 = r24
            double r8 = r0.f     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r22 = 4656510908468559872(0x409f400000000000, double:2000.0)
            double r8 = r8 + r22
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x0103
            r13.moveToNext()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            goto L_0x006b
        L_0x00fc:
            r2 = move-exception
        L_0x00fd:
            if (r13 == 0) goto L_0x0102
            r13.close()     // Catch:{ Exception -> 0x01bb }
        L_0x0102:
            throw r2
        L_0x0103:
            r11 = 1
            double r16 = r16 + r4
            double r14 = r14 + r2
            int r12 = r12 + 1
            r2 = r18
            r3 = r19
        L_0x010d:
            r4 = 4
            if (r12 <= r4) goto L_0x01b2
        L_0x0110:
            if (r12 <= 0) goto L_0x0125
            r2 = 1
            r0 = r24
            r0.e = r2     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r2 = (double) r12     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r2 = r16 / r2
            r0 = r24
            r0.i = r2     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r2 = (double) r12     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r2 = r14 / r2
            r0 = r24
            r0.j = r2     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
        L_0x0125:
            if (r13 == 0) goto L_0x0010
            r13.close()     // Catch:{ Exception -> 0x012c }
            goto L_0x0010
        L_0x012c:
            r2 = move-exception
            goto L_0x0010
        L_0x012f:
            if (r11 == 0) goto L_0x0150
            r6 = 1
            float[] r10 = new float[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r6 = (double) r12     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r6 = r14 / r6
            double r8 = (double) r12     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r8 = r16 / r8
            android.location.Location.distanceBetween(r2, r4, r6, r8, r10)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r2 = 0
            r2 = r10[r2]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r3 = 1148846080(0x447a0000, float:1000.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x014b
            r13.moveToNext()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            goto L_0x006b
        L_0x014b:
            r2 = r18
            r3 = r19
            goto L_0x010d
        L_0x0150:
            if (r19 != 0) goto L_0x015e
            int r6 = r18 + 1
            r21[r18] = r4     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            int r4 = r6 + 1
            r21[r6] = r2     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r2 = 1
            r3 = r2
            r2 = r4
            goto L_0x010d
        L_0x015e:
            r6 = 0
            r20 = r6
        L_0x0161:
            r0 = r20
            r1 = r18
            if (r0 >= r1) goto L_0x0192
            r6 = 1
            float[] r10 = new float[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            int r6 = r20 + 1
            r6 = r21[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r8 = r21[r20]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            android.location.Location.distanceBetween(r2, r4, r6, r8, r10)     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r6 = 0
            r6 = r10[r6]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r7 = 1148846080(0x447a0000, float:1000.0)
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x01c6
            r6 = 1
            r8 = r21[r20]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r10 = r16 + r8
            int r7 = r20 + 1
            r8 = r21[r7]     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            double r8 = r8 + r14
            int r7 = r12 + 1
        L_0x0188:
            int r12 = r20 + 2
            r20 = r12
            r14 = r8
            r16 = r10
            r11 = r6
            r12 = r7
            goto L_0x0161
        L_0x0192:
            if (r11 == 0) goto L_0x019f
            double r16 = r16 + r4
            double r14 = r14 + r2
            int r12 = r12 + 1
            r2 = r18
            r3 = r19
            goto L_0x010d
        L_0x019f:
            r6 = 8
            r0 = r18
            if (r0 >= r6) goto L_0x0110
            int r6 = r18 + 1
            r21[r18] = r4     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            int r4 = r6 + 1
            r21[r6] = r2     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r2 = r4
            r3 = r19
            goto L_0x010d
        L_0x01b2:
            r13.moveToNext()     // Catch:{ Exception -> 0x009c, all -> 0x00fc }
            r18 = r2
            r19 = r3
            goto L_0x006b
        L_0x01bb:
            r3 = move-exception
            goto L_0x0102
        L_0x01be:
            r3 = move-exception
            r13 = r2
            r2 = r3
            goto L_0x00fd
        L_0x01c3:
            r3 = move-exception
            goto L_0x009e
        L_0x01c6:
            r6 = r11
            r7 = r12
            r8 = r14
            r10 = r16
            goto L_0x0188
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.a(java.util.List, android.database.sqlite.SQLiteDatabase):void");
    }

    private String b(boolean z) {
        boolean z2;
        boolean z3;
        double d2;
        double d3;
        double d4 = 0.0d;
        if (this.e) {
            d3 = this.i;
            d2 = this.j;
            d4 = 246.4d;
            z2 = true;
            z3 = true;
        } else if (this.d) {
            d3 = this.g;
            d2 = this.h;
            d4 = this.f;
            z2 = true;
            z3 = true;
        } else {
            z2 = false;
            z3 = false;
            d2 = 0.0d;
            d3 = 0.0d;
        }
        if (!z3) {
            return z ? "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"67\"}}" : "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"63\"}}";
        }
        if (z) {
            return String.format(Locale.CHINA, "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(d3), Double.valueOf(d2), Double.valueOf(d4), Boolean.valueOf(true)});
        }
        return String.format(Locale.CHINA, "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(d3), Double.valueOf(d2), Double.valueOf(d4), Boolean.valueOf(z2)});
    }

    /* access modifiers changed from: private */
    public void e() {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        boolean z = true;
        try {
            sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(m, null);
        } catch (Exception e2) {
            sQLiteDatabase = sQLiteDatabase2;
        }
        if (sQLiteDatabase != null) {
            try {
                long queryNumEntries = DatabaseUtils.queryNumEntries(sQLiteDatabase, "wof");
                long queryNumEntries2 = DatabaseUtils.queryNumEntries(sQLiteDatabase, "bdcltb09");
                boolean z2 = queryNumEntries > OkHttpUtils.DEFAULT_MILLISECONDS;
                if (queryNumEntries2 <= OkHttpUtils.DEFAULT_MILLISECONDS) {
                    z = false;
                }
                sQLiteDatabase.close();
                if (z2 || z) {
                    new C0027a(this, null).execute(new Boolean[]{Boolean.valueOf(z2), Boolean.valueOf(z)});
                }
            } catch (Exception e3) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x004c, code lost:
        if (r0 != null) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.baidu.location.BDLocation a(java.lang.String r3, java.util.List<android.net.wifi.ScanResult> r4, boolean r5) {
        /*
            r2 = this;
            boolean r0 = r2.a
            if (r0 != 0) goto L_0x0027
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "{\"result\":{\"time\":\""
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = com.baidu.location.g.j.a()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "\",\"error\":\"67\"}}"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r0.toString()
            com.baidu.location.BDLocation r0 = new com.baidu.location.BDLocation
            r0.<init>(r1)
        L_0x0026:
            return r0
        L_0x0027:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "{\"result\":{\"time\":\""
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = com.baidu.location.g.j.a()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "\",\"error\":\"67\"}}"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r0.toString()
            r2.a(r3, r4)     // Catch:{ Throwable -> 0x0055 }
            r0 = 1
            java.lang.String r0 = r2.b(r0)     // Catch:{ Throwable -> 0x0055 }
            if (r0 == 0) goto L_0x0059
        L_0x004e:
            com.baidu.location.BDLocation r1 = new com.baidu.location.BDLocation
            r1.<init>(r0)
            r0 = r1
            goto L_0x0026
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0059:
            r0 = r1
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.a(java.lang.String, java.util.List, boolean):com.baidu.location.BDLocation");
    }

    public BDLocation a(boolean z) {
        if (!this.a) {
            return new BDLocation("{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"67\"}}");
        }
        com.baidu.location.e.a f2 = com.baidu.location.e.b.a().f();
        String str = (f2 == null || !f2.e()) ? null : f2.g();
        h o = i.a().o();
        BDLocation bDLocation = o != null ? a(str, o.a, true) : null;
        if (bDLocation == null || bDLocation.o() != 66) {
            return bDLocation;
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append(String.format(Locale.CHINA, "&ofl=%f|%f|%f", new Object[]{Double.valueOf(bDLocation.h()), Double.valueOf(bDLocation.i()), Float.valueOf(bDLocation.l())}));
        if (o != null && o.a() > 0) {
            stringBuffer.append("&wf=");
            stringBuffer.append(o.c(15));
        }
        if (f2 != null) {
            stringBuffer.append(f2.h());
        }
        stringBuffer.append("&uptype=oldoff");
        stringBuffer.append(j.e(f.c()));
        stringBuffer.append(com.baidu.location.g.b.a().a(false));
        stringBuffer.append(com.baidu.location.a.a.a().d());
        stringBuffer.toString();
        return bDLocation;
    }

    public void a(String str, com.baidu.location.e.a aVar, h hVar, BDLocation bDLocation) {
        if (this.a) {
            boolean z = !aVar.b() || !k.c().h();
            boolean z2 = bDLocation == null || bDLocation.o() != 161 || (!"wf".equals(bDLocation.H()) && bDLocation.l() >= 300.0f);
            if (hVar.a == null) {
                z2 = true;
            }
            if ((!z || !z2) && !this.k) {
                this.k = true;
                new b(this, null).execute(new Object[]{str, aVar, hVar, bDLocation});
            }
        }
    }

    public void b() {
        try {
            File file = new File(l);
            File file2 = new File(m);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (file2.exists()) {
                SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file2, null);
                openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS bdcltb09(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
                openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS wof(id CHAR(15) PRIMARY KEY,mktime DOUBLE,time DOUBLE, ac INT, bc INT, cc INT);");
                openOrCreateDatabase.setVersion(1);
                openOrCreateDatabase.close();
            }
            this.a = true;
        } catch (Throwable th) {
            this.a = false;
        }
    }

    public void c() {
        if (this.n == null) {
            this.n = new Handler();
        }
        this.n.postDelayed(new b(this), 3000);
    }
}
