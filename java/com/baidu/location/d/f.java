package com.baidu.location.d;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Build.VERSION;
import com.baidu.location.Jni;
import com.baidu.location.g.b;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import org.json.JSONObject;

final class f {
    /* access modifiers changed from: private */
    public final g a;
    private final SQLiteDatabase b;
    private final a c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public String[] i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public double o;
    /* access modifiers changed from: private */
    public double p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public double f374q;
    /* access modifiers changed from: private */
    public double r;
    /* access modifiers changed from: private */
    public double s;
    /* access modifiers changed from: private */
    public int t;
    private boolean u = true;
    /* access modifiers changed from: private */
    public long v = 8000;
    /* access modifiers changed from: private */
    public long w = Config.BPLUS_DELAY_TIME;
    /* access modifiers changed from: private */
    public long x = Config.BPLUS_DELAY_TIME;
    /* access modifiers changed from: private */
    public long y = Config.BPLUS_DELAY_TIME;
    /* access modifiers changed from: private */
    public long z = Config.BPLUS_DELAY_TIME;

    private final class a extends e {
        private int b;
        private long c;
        private long d;
        private boolean e;
        private final String f;

        private a() {
            this.b = 0;
            this.e = false;
            this.c = -1;
            this.d = -1;
            this.k = new HashMap();
            this.f = Jni.e(String.format(Locale.US, "&ver=%s&cuid=%s&prod=%s:%s&sdk=%.2f&mb=%s&os=A%s", new Object[]{"1", b.a().b, b.e, b.d, Float.valueOf(7.62f), Build.MODEL, VERSION.SDK}));
        }

        /* access modifiers changed from: private */
        public void b() {
            if (!this.e) {
                boolean z = false;
                try {
                    File file = new File(f.this.a.c(), "ofl.config");
                    if (this.d == -1 && file.exists()) {
                        Scanner scanner = new Scanner(file);
                        String next = scanner.next();
                        scanner.close();
                        JSONObject jSONObject = new JSONObject(next);
                        f.this.d = jSONObject.getBoolean("ol");
                        f.this.e = jSONObject.getBoolean("fl");
                        f.this.f = jSONObject.getBoolean("on");
                        f.this.g = jSONObject.getBoolean("wn");
                        f.this.h = jSONObject.getBoolean("oc");
                        this.d = jSONObject.getLong("t");
                        if (jSONObject.has("ol")) {
                            f.this.k = jSONObject.getBoolean("olv2");
                        }
                        if (jSONObject.has("cplist")) {
                            f.this.i = jSONObject.getString("cplist").split(";");
                        }
                        if (jSONObject.has("rgcgp")) {
                            f.this.l = jSONObject.getInt("rgcgp");
                        }
                        if (jSONObject.has("rgcon")) {
                            f.this.j = jSONObject.getBoolean("rgcon");
                        }
                        if (jSONObject.has("addrup")) {
                            f.this.n = jSONObject.getInt("addrup");
                        }
                        if (jSONObject.has("poiup")) {
                            f.this.m = jSONObject.getInt("poiup");
                        }
                        if (jSONObject.has("oflp")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("oflp");
                            if (jSONObject2.has("0")) {
                                f.this.o = jSONObject2.getDouble("0");
                            }
                            if (jSONObject2.has("1")) {
                                f.this.p = jSONObject2.getDouble("1");
                            }
                            if (jSONObject2.has("2")) {
                                f.this.f374q = jSONObject2.getDouble("2");
                            }
                            if (jSONObject2.has("3")) {
                                f.this.r = jSONObject2.getDouble("3");
                            }
                            if (jSONObject2.has("4")) {
                                f.this.s = jSONObject2.getDouble("4");
                            }
                        }
                        if (jSONObject.has("onlt")) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("onlt");
                            if (jSONObject3.has("0")) {
                                f.this.z = jSONObject3.getLong("0");
                            }
                            if (jSONObject3.has("1")) {
                                f.this.y = jSONObject3.getLong("1");
                            }
                            if (jSONObject3.has("2")) {
                                f.this.v = jSONObject3.getLong("2");
                            }
                            if (jSONObject3.has("3")) {
                                f.this.w = jSONObject3.getLong("3");
                            }
                            if (jSONObject3.has("4")) {
                                f.this.x = jSONObject3.getLong("4");
                            }
                        }
                        if (jSONObject.has("minapn")) {
                            f.this.t = jSONObject.getInt("minapn");
                        }
                    }
                    if (this.d != -1 || !file.exists()) {
                    }
                    if (this.d != -1 && this.d + 86400000 <= System.currentTimeMillis()) {
                        z = true;
                    }
                } catch (Exception e2) {
                }
                if ((this.d == -1 || z) && c() && j.a(f.this.a.b())) {
                    this.e = true;
                    b("https://ofloc.map.baidu.com/offline_loc");
                }
            }
        }

        private boolean c() {
            boolean z = true;
            if (this.b >= 2) {
                if (this.c + 86400000 < System.currentTimeMillis()) {
                    this.b = 0;
                    this.c = -1;
                } else {
                    z = false;
                }
            }
            if (!z) {
            }
            return z;
        }

        public void a() {
            this.k.clear();
            this.k.put("qt", "conf");
            this.k.put("req", this.f);
            this.h = g.b;
        }

        public void a(boolean z) {
            if (!z || this.j == null) {
                this.b++;
                this.c = System.currentTimeMillis();
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    String str = "1";
                    long j = 0;
                    if (jSONObject.has("ofl")) {
                        j = jSONObject.getLong("ofl");
                    }
                    if (jSONObject.has(MidEntity.TAG_VER)) {
                        str = jSONObject.getString(MidEntity.TAG_VER);
                    }
                    if ((j & 1) == 1) {
                        f.this.d = true;
                    }
                    if ((j & 2) == 2) {
                        f.this.e = true;
                    }
                    if ((j & 4) == 4) {
                        f.this.f = true;
                    }
                    if ((j & 8) == 8) {
                        f.this.g = true;
                    }
                    if ((16 & j) == 16) {
                        f.this.h = true;
                    }
                    if ((32 & j) == 32) {
                        f.this.j = true;
                    }
                    if ((j & 64) == 64) {
                        f.this.k = true;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    if (jSONObject.has("cplist")) {
                        f.this.i = jSONObject.getString("cplist").split(";");
                        jSONObject2.put("cplist", jSONObject.getString("cplist"));
                    }
                    if (jSONObject.has("bklist")) {
                        f.this.a(jSONObject.getString("bklist").split(";"));
                    }
                    if (jSONObject.has("para")) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("para");
                        if (jSONObject3.has("rgcgp")) {
                            f.this.l = jSONObject3.getInt("rgcgp");
                        }
                        if (jSONObject3.has("addrup")) {
                            f.this.n = jSONObject3.getInt("addrup");
                        }
                        if (jSONObject3.has("poiup")) {
                            f.this.m = jSONObject3.getInt("poiup");
                        }
                        if (jSONObject3.has("oflp")) {
                            JSONObject jSONObject4 = jSONObject3.getJSONObject("oflp");
                            if (jSONObject4.has("0")) {
                                f.this.o = jSONObject4.getDouble("0");
                            }
                            if (jSONObject4.has("1")) {
                                f.this.p = jSONObject4.getDouble("1");
                            }
                            if (jSONObject4.has("2")) {
                                f.this.f374q = jSONObject4.getDouble("2");
                            }
                            if (jSONObject4.has("3")) {
                                f.this.r = jSONObject4.getDouble("3");
                            }
                            if (jSONObject4.has("4")) {
                                f.this.s = jSONObject4.getDouble("4");
                            }
                        }
                        if (jSONObject3.has("onlt")) {
                            JSONObject jSONObject5 = jSONObject3.getJSONObject("onlt");
                            if (jSONObject5.has("0")) {
                                f.this.z = jSONObject5.getLong("0");
                            }
                            if (jSONObject5.has("1")) {
                                f.this.y = jSONObject5.getLong("1");
                            }
                            if (jSONObject5.has("2")) {
                                f.this.v = jSONObject5.getLong("2");
                            }
                            if (jSONObject5.has("3")) {
                                f.this.w = jSONObject5.getLong("3");
                            }
                            if (jSONObject5.has("4")) {
                                f.this.x = jSONObject5.getLong("4");
                            }
                        }
                        if (jSONObject3.has("minapn")) {
                            f.this.t = jSONObject3.getInt("minapn");
                        }
                    }
                    jSONObject2.put("ol", f.this.d);
                    jSONObject2.put("olv2", f.this.k);
                    jSONObject2.put("fl", f.this.e);
                    jSONObject2.put("on", f.this.f);
                    jSONObject2.put("wn", f.this.g);
                    jSONObject2.put("oc", f.this.h);
                    this.d = System.currentTimeMillis();
                    jSONObject2.put("t", this.d);
                    jSONObject2.put(MidEntity.TAG_VER, str);
                    jSONObject2.put("rgcon", f.this.j);
                    jSONObject2.put("rgcgp", f.this.l);
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("0", f.this.o);
                    jSONObject6.put("1", f.this.p);
                    jSONObject6.put("2", f.this.f374q);
                    jSONObject6.put("3", f.this.r);
                    jSONObject6.put("4", f.this.s);
                    jSONObject2.put("oflp", jSONObject6);
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("0", f.this.z);
                    jSONObject7.put("1", f.this.y);
                    jSONObject7.put("2", f.this.v);
                    jSONObject7.put("3", f.this.w);
                    jSONObject7.put("4", f.this.x);
                    jSONObject2.put("onlt", jSONObject7);
                    jSONObject2.put("addrup", f.this.n);
                    jSONObject2.put("poiup", f.this.m);
                    jSONObject2.put("minapn", f.this.t);
                    File file = new File(f.this.a.c(), "ofl.config");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jSONObject2.toString());
                    fileWriter.close();
                } catch (Exception e2) {
                    this.b++;
                    this.c = System.currentTimeMillis();
                }
            }
            this.e = false;
        }
    }

    f(g gVar, SQLiteDatabase sQLiteDatabase) {
        this.a = gVar;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.j = false;
        this.k = false;
        this.l = 6;
        this.m = 30;
        this.n = 30;
        this.o = 0.0d;
        this.p = 0.0d;
        this.f374q = 0.0d;
        this.r = 0.0d;
        this.s = 0.0d;
        this.t = 8;
        this.i = new String[0];
        this.b = sQLiteDatabase;
        this.c = new a();
        if (this.b != null && this.b.isOpen()) {
            try {
                this.b.execSQL("CREATE TABLE IF NOT EXISTS BLACK (name VARCHAR(100) PRIMARY KEY);");
            } catch (Exception e2) {
            }
        }
        g();
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.t;
    }

    /* access modifiers changed from: 0000 */
    public long a(String str) {
        return str.equals(NetworkUtil.NETWORK_CLASS_2_G) ? this.v : str.equals(NetworkUtil.NETWORK_CLASS_3_G) ? this.w : str.equals(NetworkUtil.NETWORK_CLASS_4_G) ? this.x : str.equals("WIFI") ? this.y : str.equals("unknown") ? this.z : Config.BPLUS_DELAY_TIME;
    }

    /* access modifiers changed from: 0000 */
    public void a(String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(StorageInterface.KEY_SPLITER);
            }
            stringBuffer.append("(\"");
            stringBuffer.append(strArr[i2]);
            stringBuffer.append("\")");
        }
        if (this.b != null && this.b.isOpen() && stringBuffer.length() > 0) {
            try {
                this.b.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO BLACK VALUES %s;", new Object[]{stringBuffer.toString()}));
            } catch (Exception e2) {
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        return this.o;
    }

    /* access modifiers changed from: 0000 */
    public double c() {
        return this.p;
    }

    /* access modifiers changed from: 0000 */
    public double d() {
        return this.f374q;
    }

    /* access modifiers changed from: 0000 */
    public double e() {
        return this.r;
    }

    /* access modifiers changed from: 0000 */
    public double f() {
        return this.s;
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        this.c.b();
    }

    /* access modifiers changed from: 0000 */
    public boolean h() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public boolean i() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public boolean j() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public boolean k() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public boolean l() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public boolean m() {
        return this.u;
    }

    /* access modifiers changed from: 0000 */
    public int n() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public String[] o() {
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public int p() {
        return this.n;
    }

    /* access modifiers changed from: 0000 */
    public int q() {
        return this.m;
    }
}
