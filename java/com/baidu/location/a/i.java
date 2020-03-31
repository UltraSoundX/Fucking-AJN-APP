package com.baidu.location.a;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.e.h;
import com.baidu.location.f;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import java.util.HashMap;
import java.util.Locale;

public abstract class i {
    public static String c = null;
    public h a = null;
    public com.baidu.location.e.a b = null;
    final Handler d = new a();
    private boolean e = true;
    private boolean f = true;
    private boolean g = false;
    /* access modifiers changed from: private */
    public String h = null;
    /* access modifiers changed from: private */
    public String i = null;

    public class a extends Handler {
        public a() {
        }

        public void handleMessage(Message message) {
            if (f.f) {
                switch (message.what) {
                    case 21:
                        i.this.a(message);
                        return;
                    case 62:
                    case 63:
                        i.this.a();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    class b extends e {
        String a;
        String b;

        public b() {
            this.a = null;
            this.b = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.d();
            if (!((!j.h && !j.j) || i.this.h == null || i.this.i == null)) {
                this.b += String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[]{i.this.h, i.this.i});
            }
            String f = Jni.f(this.b);
            this.b = null;
            if (this.a == null) {
                this.a = v.b();
            }
            this.k.put("bloc", f);
            if (this.a != null) {
                this.k.put("up", this.a);
            }
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        public void a(String str) {
            this.b = str;
            b(j.f);
        }

        public void a(boolean z) {
            BDLocation bDLocation;
            if (!z || this.j == null) {
                Message obtainMessage = i.this.d.obtainMessage(63);
                obtainMessage.obj = "HttpStatus error";
                obtainMessage.sendToTarget();
            } else {
                try {
                    String str = this.j;
                    i.c = str;
                    try {
                        bDLocation = new BDLocation(str);
                        if (bDLocation.o() == 161) {
                            h.a().a(str);
                        }
                        bDLocation.j(com.baidu.location.e.b.a().h());
                        if (n.a().d()) {
                            bDLocation.c(n.a().e());
                        }
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.e(0);
                    }
                    this.a = null;
                    if (bDLocation.o() == 0 && bDLocation.h() == Double.MIN_VALUE && bDLocation.i() == Double.MIN_VALUE) {
                        Message obtainMessage2 = i.this.d.obtainMessage(63);
                        obtainMessage2.obj = "HttpStatus error";
                        obtainMessage2.sendToTarget();
                    } else {
                        Message obtainMessage3 = i.this.d.obtainMessage(21);
                        obtainMessage3.obj = bDLocation;
                        obtainMessage3.sendToTarget();
                    }
                } catch (Exception e2) {
                    Message obtainMessage4 = i.this.d.obtainMessage(63);
                    obtainMessage4.obj = "HttpStatus error";
                    obtainMessage4.sendToTarget();
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            r4 = 0
            java.lang.String r1 = r6.h
            if (r1 != 0) goto L_0x0010
            android.content.Context r1 = com.baidu.location.f.c()
            java.lang.String r1 = com.baidu.location.a.j.b(r1)
            r6.h = r1
        L_0x0010:
            java.lang.String r1 = r6.i
            if (r1 != 0) goto L_0x001e
            android.content.Context r1 = com.baidu.location.f.c()
            java.lang.String r1 = com.baidu.location.a.j.c(r1)
            r6.i = r1
        L_0x001e:
            com.baidu.location.e.a r1 = r6.b
            if (r1 == 0) goto L_0x002a
            com.baidu.location.e.a r1 = r6.b
            boolean r1 = r1.a()
            if (r1 != 0) goto L_0x0034
        L_0x002a:
            com.baidu.location.e.b r1 = com.baidu.location.e.b.a()
            com.baidu.location.e.a r1 = r1.f()
            r6.b = r1
        L_0x0034:
            com.baidu.location.e.h r1 = r6.a
            if (r1 == 0) goto L_0x0040
            com.baidu.location.e.h r1 = r6.a
            boolean r1 = r1.j()
            if (r1 != 0) goto L_0x004a
        L_0x0040:
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()
            com.baidu.location.e.h r1 = r1.p()
            r6.a = r1
        L_0x004a:
            com.baidu.location.e.e r1 = com.baidu.location.e.e.a()
            boolean r1 = r1.j()
            if (r1 == 0) goto L_0x0106
            com.baidu.location.e.e r1 = com.baidu.location.e.e.a()
            android.location.Location r2 = r1.h()
        L_0x005c:
            com.baidu.location.e.a r1 = r6.b
            if (r1 == 0) goto L_0x0070
            com.baidu.location.e.a r1 = r6.b
            boolean r1 = r1.d()
            if (r1 != 0) goto L_0x0070
            com.baidu.location.e.a r1 = r6.b
            boolean r1 = r1.c()
            if (r1 == 0) goto L_0x007f
        L_0x0070:
            com.baidu.location.e.h r1 = r6.a
            if (r1 == 0) goto L_0x007c
            com.baidu.location.e.h r1 = r6.a
            int r1 = r1.a()
            if (r1 != 0) goto L_0x007f
        L_0x007c:
            if (r2 != 0) goto L_0x007f
        L_0x007e:
            return r0
        L_0x007f:
            java.lang.String r0 = r6.b()
            com.baidu.location.a.h r1 = com.baidu.location.a.h.a()
            int r1 = r1.d()
            r3 = -2
            if (r1 != r3) goto L_0x00a1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "&imo=1"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x00a1:
            android.content.Context r1 = com.baidu.location.f.c()
            int r1 = com.baidu.location.g.j.c(r1)
            if (r1 < 0) goto L_0x00c2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r3 = "&lmd="
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x00c2:
            com.baidu.location.e.h r1 = r6.a
            if (r1 == 0) goto L_0x00ce
            com.baidu.location.e.h r1 = r6.a
            int r1 = r1.a()
            if (r1 != 0) goto L_0x0104
        L_0x00ce:
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()
            java.lang.String r1 = r1.m()
            if (r1 == 0) goto L_0x0104
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            r3 = r0
        L_0x00ea:
            boolean r0 = r6.f
            if (r0 == 0) goto L_0x00fa
            r6.f = r4
            com.baidu.location.e.a r0 = r6.b
            com.baidu.location.e.h r1 = r6.a
            r5 = 1
            java.lang.String r0 = com.baidu.location.g.j.a(r0, r1, r2, r3, r4, r5)
            goto L_0x007e
        L_0x00fa:
            com.baidu.location.e.a r0 = r6.b
            com.baidu.location.e.h r1 = r6.a
            java.lang.String r0 = com.baidu.location.g.j.a(r0, r1, r2, r3, r4)
            goto L_0x007e
        L_0x0104:
            r3 = r0
            goto L_0x00ea
        L_0x0106:
            r2 = r0
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.i.a(java.lang.String):java.lang.String");
    }

    public abstract void a();

    public abstract void a(Message message);

    public String b() {
        String format;
        String d2 = a.a().d();
        String str = "";
        if (com.baidu.location.e.i.j()) {
            format = "&cn=32";
        } else {
            format = String.format(Locale.CHINA, "&cn=%d", new Object[]{Integer.valueOf(com.baidu.location.e.b.a().e())});
        }
        if (VERSION.SDK_INT >= 18) {
            String c2 = j.c();
            if (!TextUtils.isEmpty(c2)) {
                format = format + "&qcip6c=" + c2;
            }
        }
        if (this.e) {
            this.e = false;
            String r = com.baidu.location.e.i.a().r();
            if (!TextUtils.isEmpty(r) && !r.equals(Config.DEF_MAC_ID)) {
                format = String.format(Locale.CHINA, "%s&mac=%s", new Object[]{format, r.replace(Config.TRACE_TODAY_VISIT_SPLIT, "")});
            }
            if (VERSION.SDK_INT > 17) {
            }
        } else if (!this.g) {
            String e2 = v.e();
            if (e2 != null) {
                format = format + e2;
            }
            this.g = true;
        }
        return format + d2;
    }
}
