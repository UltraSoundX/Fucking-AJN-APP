package com.baidu.location.g;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.baidu.a.a.a.b.a;
import com.baidu.a.a.a.b.c;
import com.baidu.location.a.m;
import com.baidu.location.e.e;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import java.util.Locale;

public class b {
    public static String d = null;
    public static String e = null;
    public static String f = null;
    public static String g = null;
    public static int h = 0;
    private static b i = null;
    public String a = null;
    public String b = null;
    public String c = null;
    private boolean j = false;

    private b() {
        if (f.c() != null) {
            a(f.c());
        }
    }

    public static b a() {
        if (i == null) {
            i = new b();
        }
        return i;
    }

    public String a(boolean z) {
        return a(z, (String) null);
    }

    public String a(boolean z, String str) {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.62f);
        if (z) {
            if (j.g.equals("all")) {
                stringBuffer.append("&addr=allj");
            }
            if (j.i) {
                stringBuffer.append("&adtp=n2");
            }
            if (j.h || j.k || j.l || j.j) {
                stringBuffer.append("&sema=");
                if (j.h) {
                    stringBuffer.append("aptag|");
                }
                if (j.j) {
                    stringBuffer.append("aptagd|");
                }
                if (j.k) {
                    stringBuffer.append("poiregion|");
                }
                if (j.l) {
                    stringBuffer.append("regular");
                }
            }
        }
        if (z) {
            if (str == null) {
                stringBuffer.append("&coor=gcj02");
            } else {
                stringBuffer.append("&coor=");
                stringBuffer.append(str);
            }
            String k = e.k();
            if (k != null) {
                stringBuffer.append(k);
            }
        }
        if (this.b == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(this.a);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(this.b);
            if (this.a != null && !this.a.equals("NULL") && !this.b.contains(new StringBuffer(this.a).reverse().toString())) {
                stringBuffer.append("&Aim=");
                stringBuffer.append(this.a);
            }
        }
        if (this.c != null) {
            stringBuffer.append("&Aid=");
            stringBuffer.append(this.c);
        }
        stringBuffer.append("&fw=");
        stringBuffer.append(f.a());
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        String b2 = j.b();
        if (b2 != null) {
            stringBuffer.append("&laip=");
            stringBuffer.append(b2);
        }
        float d2 = m.a().d();
        if (d2 != 0.0f) {
            stringBuffer.append("&altv=");
            stringBuffer.append(String.format(Locale.US, "%.5f", new Object[]{Float.valueOf(d2)}));
        }
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        stringBuffer.append("&os=A");
        stringBuffer.append(VERSION.SDK);
        if (z) {
            stringBuffer.append("&sv=");
            String str2 = VERSION.RELEASE;
            if (str2 != null && str2.length() > 6) {
                str2 = str2.substring(0, 6);
            }
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public void a(Context context) {
        if (context != null && !this.j) {
            try {
                this.a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Exception e2) {
                this.a = "NULL";
            }
            try {
                this.b = a.a(context);
            } catch (Exception e3) {
                this.b = null;
            }
            try {
                this.c = c.b(context);
            } catch (Exception e4) {
                this.c = null;
            }
            try {
                d = context.getPackageName();
            } catch (Exception e5) {
                d = null;
            }
            j.o = "" + this.b;
            this.j = true;
        }
    }

    public void a(String str, String str2) {
        e = str;
        d = str2;
    }

    public String b() {
        return this.b != null ? "v7.62|" + this.b + "|" + Build.MODEL : "v7.62|" + this.a + "|" + Build.MODEL;
    }

    public String c() {
        StringBuffer stringBuffer = new StringBuffer(200);
        if (this.b != null) {
            stringBuffer.append("&cu=");
            stringBuffer.append(this.b);
        } else {
            stringBuffer.append("&im=");
            stringBuffer.append(this.a);
        }
        try {
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
        } catch (Exception e2) {
        }
        stringBuffer.append("&pack=");
        try {
            stringBuffer.append(d);
        } catch (Exception e3) {
        }
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.62f);
        return stringBuffer.toString();
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.b == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(this.a);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(this.b);
        }
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.62f);
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&stp=1");
        stringBuffer.append("&os=A");
        stringBuffer.append(VERSION.SDK);
        stringBuffer.append("&prod=");
        stringBuffer.append(e + Config.TRACE_TODAY_VISIT_SPLIT + d);
        stringBuffer.append(j.e(f.c()));
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        return stringBuffer.toString();
    }
}
