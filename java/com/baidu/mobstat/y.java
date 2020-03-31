package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.mid.api.MidEntity;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

public class y {
    private static y a;
    private Context b;
    private JSONObject c = new JSONObject();
    private long d = 24;
    private long e = 0;
    private long f = 0;
    private long g = 0;
    private long h = 5;
    private long i = 24;
    private long j = 15;
    private long k = 15;
    private long l = 30;
    private long m = 12;
    private long n = 1;
    private long o = 24;
    private String p = "";

    /* renamed from: q reason: collision with root package name */
    private String f387q = "";

    public static y a(Context context) {
        if (a == null) {
            synchronized (y.class) {
                if (a == null) {
                    a = new y(context);
                }
            }
        }
        return a;
    }

    private y(Context context) {
        this.b = context;
        m();
        j();
        k();
    }

    private void m() {
        String b2 = at.b("backups/system/.timestamp");
        try {
            if (!TextUtils.isEmpty(b2)) {
                this.c = new JSONObject(b2);
            }
        } catch (Exception e2) {
        }
    }

    public boolean a() {
        return this.e != 0;
    }

    public boolean b() {
        return this.f != 0;
    }

    public long c() {
        return this.d * 60 * 60 * 1000;
    }

    public long d() {
        return this.o * 60 * 60 * 1000;
    }

    public long e() {
        return this.h * 60 * 1000;
    }

    public long f() {
        return this.i * 60 * 60 * 1000;
    }

    public long g() {
        return this.j * 24 * 60 * 60 * 1000;
    }

    public long h() {
        return this.k * 24 * 60 * 60 * 1000;
    }

    public long i() {
        return this.m * 60 * 60 * 1000;
    }

    public void j() {
        try {
            String str = new String(ba.b(false, aw.a(), au.a(at.a(this.b, ".config2").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.e = jSONObject.getLong("c");
                } catch (JSONException e2) {
                    al.c().b((Throwable) e2);
                }
                try {
                    this.h = jSONObject.getLong("d");
                } catch (JSONException e3) {
                    al.c().b((Throwable) e3);
                }
                try {
                    this.i = jSONObject.getLong("e");
                } catch (JSONException e4) {
                    al.c().b((Throwable) e4);
                }
                try {
                    this.j = jSONObject.getLong("i");
                } catch (JSONException e5) {
                    al.c().b((Throwable) e5);
                }
                try {
                    this.d = jSONObject.getLong("f");
                } catch (JSONException e6) {
                    al.c().b((Throwable) e6);
                }
                try {
                    this.o = jSONObject.getLong("s");
                } catch (JSONException e7) {
                    al.c().b((Throwable) e7);
                }
                try {
                    this.k = jSONObject.getLong("pk");
                } catch (JSONException e8) {
                    al.c().b((Throwable) e8);
                }
                try {
                    this.l = jSONObject.getLong("at");
                } catch (JSONException e9) {
                    al.c().b((Throwable) e9);
                }
                try {
                    this.m = jSONObject.getLong("as");
                } catch (JSONException e10) {
                    al.c().b((Throwable) e10);
                }
                try {
                    this.n = jSONObject.getLong("ac");
                } catch (JSONException e11) {
                    al.c().b((Throwable) e11);
                }
                try {
                    this.f = jSONObject.getLong(Config.DEVICE_MAC_ID);
                } catch (JSONException e12) {
                    al.c().b((Throwable) e12);
                }
                try {
                    this.g = jSONObject.getLong("lsc");
                } catch (JSONException e13) {
                    al.c().b((Throwable) e13);
                }
            }
        } catch (Exception e14) {
            al.c().b((Throwable) e14);
        }
    }

    public void k() {
        try {
            String str = new String(ba.b(false, aw.a(), au.a(at.a(this.b, ".sign").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.f387q = jSONObject.getString("sign");
                } catch (Exception e2) {
                    al.c().b((Throwable) e2);
                }
                try {
                    this.p = jSONObject.getString(MidEntity.TAG_VER);
                } catch (Exception e3) {
                    al.c().b((Throwable) e3);
                }
            }
        } catch (Exception e4) {
            al.c().b((Throwable) e4);
        }
    }

    public void a(String str) {
        at.a(this.b, ".config2", str, false);
        j();
    }

    public void b(String str) {
        at.a(this.b, ".sign", str, false);
        k();
    }

    public String c(String str) {
        if (TextUtils.isEmpty(this.p) || !this.p.equals(str) || TextUtils.isEmpty(this.f387q)) {
            return "";
        }
        return this.f387q;
    }

    public long a(g gVar) {
        long j2 = gVar.j;
        try {
            String gVar2 = gVar.toString();
            if (this.c.has(gVar2)) {
                j2 = this.c.getLong(gVar2);
            }
        } catch (Exception e2) {
            al.c().a((Throwable) e2);
        }
        return b(j2);
    }

    public void a(g gVar, long j2) {
        gVar.j = j2;
        try {
            this.c.put(gVar.toString(), j2);
        } catch (Exception e2) {
            al.c().a((Throwable) e2);
        }
        try {
            at.a("backups/system/.timestamp", this.c.toString(), false);
        } catch (Exception e3) {
            al.c().a((Throwable) e3);
        }
    }

    public boolean l() {
        long currentTimeMillis = System.currentTimeMillis();
        long a2 = a(g.LAST_SEND);
        long d2 = d();
        al.c().a("canSend now=" + currentTimeMillis + ";lastSendTime=" + a2 + ";sendLogTimeInterval=" + d2);
        return currentTimeMillis - a2 > d2 || !a(a2);
    }

    public boolean a(long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(Long.valueOf(j2)).equals(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    }

    private long b(long j2) {
        if (j2 - System.currentTimeMillis() > 0) {
            return 0;
        }
        return j2;
    }
}
