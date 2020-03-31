package com.baidu.location.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.g.b;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import net.sf.json.util.JSONUtils;
import org.json.JSONObject;

public class a {
    private static Object b = new Object();
    private static a c = null;
    private static final String d = (j.i() + "/gal.db");
    C0025a a = null;
    /* access modifiers changed from: private */
    public SQLiteDatabase e = null;
    /* access modifiers changed from: private */
    public boolean f = false;
    private String g = null;
    private double h = Double.MAX_VALUE;
    private double i = Double.MAX_VALUE;

    /* renamed from: com.baidu.location.b.a$a reason: collision with other inner class name */
    class C0025a extends e {
        int a;
        int b;
        int c;
        int d;
        double e;

        C0025a() {
            this.k = new HashMap();
        }

        public void a() {
            String str;
            this.h = "http://loc.map.baidu.com/gpsz";
            String format = String.format(Locale.CHINESE, "&x=%d&y=%d%s", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), b.a().c()});
            String a2 = Jni.a(format);
            if (a2.contains("err!")) {
                try {
                    str = com.baidu.a.a.a.a.b.a(format.toString().getBytes(), "UTF-8");
                } catch (Exception e2) {
                    str = "err2!";
                }
                this.k.put("gpszb", str);
                return;
            }
            this.k.put("gpsz", a2);
        }

        public void a(double d2, double d3, double d4) {
            if (!a.this.f) {
                double[] a2 = Jni.a(d2, d3, "gcj2wgs");
                this.a = (int) Math.floor(a2[0] * 100.0d);
                this.b = (int) Math.floor(a2[1] * 100.0d);
                this.c = (int) Math.floor(d2 * 100.0d);
                this.d = (int) Math.floor(d3 * 100.0d);
                this.e = d4;
                a.this.f = true;
                e();
            }
        }

        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    if (jSONObject != null && jSONObject.has("height")) {
                        String string = jSONObject.getString("height");
                        if (string.contains(StorageInterface.KEY_SPLITER)) {
                            String[] split = string.trim().split(StorageInterface.KEY_SPLITER);
                            int length = split.length;
                            int sqrt = (int) Math.sqrt((double) length);
                            if (sqrt * sqrt == length) {
                                int i = this.c - ((sqrt - 1) / 2);
                                int i2 = this.d - ((sqrt - 1) / 2);
                                for (int i3 = 0; i3 < sqrt; i3++) {
                                    for (int i4 = 0; i4 < sqrt; i4++) {
                                        ContentValues contentValues = new ContentValues();
                                        if (split[(i3 * sqrt) + i4].contains("E")) {
                                            contentValues.put("aldata", Double.valueOf(10000.0d));
                                            contentValues.put("sigma", Double.valueOf(10000.0d));
                                        } else if (!split[(i3 * sqrt) + i4].contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
                                            contentValues.put("aldata", Double.valueOf(split[(i3 * sqrt) + i4]));
                                            contentValues.put("sigma", Double.valueOf(10000.0d));
                                        } else {
                                            String[] split2 = split[(i3 * sqrt) + i4].split(Config.TRACE_TODAY_VISIT_SPLIT);
                                            if (split2.length == 2) {
                                                contentValues.put("aldata", Double.valueOf(split2[0]));
                                                contentValues.put("sigma", split2[1]);
                                            } else {
                                                contentValues.put("aldata", Double.valueOf(10000.0d));
                                                contentValues.put("sigma", Double.valueOf(10000.0d));
                                            }
                                        }
                                        contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                                        String format = String.format(Locale.CHINESE, "%d,%d", new Object[]{Integer.valueOf(i + i4), Integer.valueOf(i2 + i3)});
                                        try {
                                            if (a.this.e.update("galdata_new", contentValues, "id = \"" + format + JSONUtils.DOUBLE_QUOTE, null) <= 0) {
                                                contentValues.put(Config.FEED_LIST_ITEM_CUSTOM_ID, format);
                                                a.this.e.insert("galdata_new", null, contentValues);
                                            }
                                        } catch (Exception e2) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e3) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
            a.this.f = false;
        }
    }

    public static a a() {
        a aVar;
        synchronized (b) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private void a(double d2, double d3, double d4) {
        if (this.a == null) {
            this.a = new C0025a();
        }
        this.a.a(d2, d3, d4);
    }

    public int a(BDLocation bDLocation) {
        double d2;
        float f2;
        if (bDLocation != null) {
            f2 = bDLocation.l();
            d2 = bDLocation.j();
        } else {
            d2 = 0.0d;
            f2 = 0.0f;
        }
        if (this.e != null && f2 > 0.0f && d2 > 0.0d && bDLocation != null) {
            double d3 = a(bDLocation.i(), bDLocation.h())[0];
            if (d3 != Double.MAX_VALUE) {
                double a2 = Jni.a(f2, d2, d3);
                if (a2 > 50.0d) {
                    return 3;
                }
                return a2 > 20.0d ? 2 : 1;
            }
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011a, code lost:
        r2 = r8;
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0126, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0127, code lost:
        r14 = r1;
        r1 = r0;
        r0 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012a, code lost:
        if (r1 != null) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x013c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013d, code lost:
        r14 = r1;
        r1 = r0;
        r0 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fd A[SYNTHETIC, Splitter:B:46:0x00fd] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0119 A[ExcHandler: Exception (e java.lang.Exception), PHI: r8 r10 
  PHI: (r8v1 double) = (r8v0 double), (r8v0 double), (r8v0 double), (r8v0 double), (r8v3 double) binds: [B:18:0x0082, B:19:?, B:49:0x0105, B:21:0x00a4, B:37:0x00d2] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r10v1 double) = (r10v0 double), (r10v0 double), (r10v0 double), (r10v0 double), (r10v3 double) binds: [B:18:0x0082, B:19:?, B:49:0x0105, B:21:0x00a4, B:37:0x00d2] A[DONT_GENERATE, DONT_INLINE], Splitter:B:18:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011e A[SYNTHETIC, Splitter:B:57:0x011e] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012c A[SYNTHETIC, Splitter:B:63:0x012c] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x013c A[ExcHandler: all (r1v8 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:21:0x00a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double[] a(double r16, double r18) {
        /*
            r15 = this;
            r10 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r8 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r0 = 2
            double[] r12 = new double[r0]
            android.database.sqlite.SQLiteDatabase r0 = r15.e
            if (r0 == 0) goto L_0x014b
            r0 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            int r0 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x014b
            r0 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            int r0 = (r18 > r0 ? 1 : (r18 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x014b
            r0 = 0
            java.util.Locale r1 = java.util.Locale.CHINESE
            java.lang.String r2 = "%d,%d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r6 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r6 = r6 * r16
            double r6 = java.lang.Math.floor(r6)
            int r5 = (int) r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r3[r4] = r5
            r4 = 1
            r6 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r6 = r6 * r18
            double r6 = java.lang.Math.floor(r6)
            int r5 = (int) r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r3[r4] = r5
            java.lang.String r13 = java.lang.String.format(r1, r2, r3)
            java.lang.String r1 = r15.g
            if (r1 == 0) goto L_0x0082
            java.lang.String r1 = r15.g
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x0082
            double r4 = r15.h
            double r2 = r15.i
        L_0x005f:
            r0 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0130
            r0 = 0
            r4 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            r12[r0] = r4
        L_0x0070:
            r0 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0135
            r0 = 1
            r2 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            r12[r0] = r2
        L_0x0081:
            return r12
        L_0x0082:
            android.database.sqlite.SQLiteDatabase r1 = r15.e     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            r2.<init>()     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            java.lang.String r3 = "select * from galdata_new where id = \""
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            java.lang.StringBuilder r2 = r2.append(r13)     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            java.lang.String r3 = "\";"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            r3 = 0
            android.database.Cursor r0 = r1.rawQuery(r2, r3)     // Catch:{ Exception -> 0x0119, all -> 0x0126 }
            if (r0 == 0) goto L_0x0105
            boolean r1 = r0.moveToFirst()     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            if (r1 == 0) goto L_0x0105
            r1 = 1
            double r4 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            r1 = 2
            double r2 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0141, all -> 0x013c }
            r1 = 3
            int r1 = r0.getInt(r1)     // Catch:{ Exception -> 0x0144, all -> 0x013c }
            r6 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x0148
            r10 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
        L_0x00c7:
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0146
            r8 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
        L_0x00d2:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            long r4 = (long) r1     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            long r2 = r2 - r4
            boolean r1 = r15.f     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            if (r1 != 0) goto L_0x00f3
            r4 = 604800(0x93a80, double:2.98811E-318)
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x00f3
            r6 = 4620265375588679680(0x401e7ae140000000, double:7.619999885559082)
            r1 = r15
            r2 = r16
            r4 = r18
            r1.a(r2, r4, r6)     // Catch:{ Exception -> 0x0119, all -> 0x013c }
        L_0x00f3:
            r15.g = r13     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            r15.h = r10     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            r15.i = r8     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            r2 = r8
            r4 = r10
        L_0x00fb:
            if (r0 == 0) goto L_0x005f
            r0.close()     // Catch:{ Exception -> 0x0102 }
            goto L_0x005f
        L_0x0102:
            r0 = move-exception
            goto L_0x005f
        L_0x0105:
            boolean r1 = r15.f     // Catch:{ Exception -> 0x0119, all -> 0x013c }
            if (r1 != 0) goto L_0x0116
            r6 = 4620265375588679680(0x401e7ae140000000, double:7.619999885559082)
            r1 = r15
            r2 = r16
            r4 = r18
            r1.a(r2, r4, r6)     // Catch:{ Exception -> 0x0119, all -> 0x013c }
        L_0x0116:
            r2 = r8
            r4 = r10
            goto L_0x00fb
        L_0x0119:
            r1 = move-exception
            r2 = r8
            r4 = r10
        L_0x011c:
            if (r0 == 0) goto L_0x005f
            r0.close()     // Catch:{ Exception -> 0x0123 }
            goto L_0x005f
        L_0x0123:
            r0 = move-exception
            goto L_0x005f
        L_0x0126:
            r1 = move-exception
            r14 = r1
            r1 = r0
            r0 = r14
        L_0x012a:
            if (r1 == 0) goto L_0x012f
            r1.close()     // Catch:{ Exception -> 0x013a }
        L_0x012f:
            throw r0
        L_0x0130:
            r0 = 0
            r12[r0] = r4
            goto L_0x0070
        L_0x0135:
            r0 = 1
            r12[r0] = r2
            goto L_0x0081
        L_0x013a:
            r1 = move-exception
            goto L_0x012f
        L_0x013c:
            r1 = move-exception
            r14 = r1
            r1 = r0
            r0 = r14
            goto L_0x012a
        L_0x0141:
            r1 = move-exception
            r2 = r8
            goto L_0x011c
        L_0x0144:
            r1 = move-exception
            goto L_0x011c
        L_0x0146:
            r8 = r2
            goto L_0x00d2
        L_0x0148:
            r10 = r4
            goto L_0x00c7
        L_0x014b:
            r2 = r8
            r4 = r10
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.a.a(double, double):double[]");
    }

    public void b() {
        try {
            File file = new File(d);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.e = SQLiteDatabase.openOrCreateDatabase(file, null);
                Cursor rawQuery = this.e.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='galdata'", null);
                if (rawQuery.moveToFirst()) {
                    if (rawQuery.getInt(0) == 0) {
                        this.e.execSQL("CREATE TABLE IF NOT EXISTS galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    } else {
                        this.e.execSQL("DROP TABLE galdata");
                        this.e.execSQL("CREATE TABLE galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    }
                }
                this.e.setVersion(1);
                rawQuery.close();
            }
        } catch (Exception e2) {
            this.e = null;
        }
    }

    public void c() {
        if (this.e != null) {
            try {
                this.e.close();
            } catch (Exception e2) {
            } finally {
                this.e = null;
            }
        }
    }
}
