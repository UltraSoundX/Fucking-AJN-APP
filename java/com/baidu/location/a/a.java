package com.baidu.location.a;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.e.e;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.g.j;
import com.baidu.location.h;
import com.baidu.location.indoor.g;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    public static long c = 0;
    private static a e = null;
    public boolean a;
    boolean b;
    int d;
    private ArrayList<C0023a> f;
    private boolean g;
    private BDLocation h;
    private BDLocation i;
    private BDLocation j;
    private boolean k;
    /* access modifiers changed from: private */
    public boolean l;
    private b m;

    /* renamed from: com.baidu.location.a.a$a reason: collision with other inner class name */
    private class C0023a {
        public String a = null;
        public Messenger b = null;
        public h c = new h();
        public int d = 0;
        final /* synthetic */ a e;

        public C0023a(a aVar, Message message) {
            boolean z = false;
            this.e = aVar;
            this.b = message.replyTo;
            this.a = message.getData().getString(Constants.FLAG_PACK_NAME);
            this.c.f = message.getData().getString("prodName");
            com.baidu.location.g.b.a().a(this.c.f, this.a);
            this.c.a = message.getData().getString("coorType");
            this.c.b = message.getData().getString("addrType");
            this.c.j = message.getData().getBoolean("enableSimulateGps", false);
            j.m = j.m || this.c.j;
            if (!j.g.equals("all")) {
                j.g = this.c.b;
            }
            this.c.c = message.getData().getBoolean("openGPS");
            this.c.d = message.getData().getInt("scanSpan");
            this.c.e = message.getData().getInt("timeOut");
            this.c.g = message.getData().getInt("priority");
            this.c.h = message.getData().getBoolean("location_change_notify");
            this.c.n = message.getData().getBoolean("needDirect", false);
            this.c.s = message.getData().getBoolean("isneedaltitude", false);
            this.c.t = message.getData().getBoolean("isneednewrgc", false);
            j.i = j.i || this.c.t;
            j.h = j.h || message.getData().getBoolean("isneedaptag", false);
            j.j = j.j || message.getData().getBoolean("isneedaptagd", false);
            j.R = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
            if (i < j.af) {
                j.af = i;
            }
            int i2 = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i2 >= j.W) {
                j.W = i2;
            }
            int i3 = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i3 >= j.Y) {
                j.Y = i3;
            }
            int i4 = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i4 >= j.X) {
                j.X = i4;
            }
            if (this.c.n || this.c.s) {
                n.a().a(this.c.n);
                n.a().b();
            }
            if (aVar.b || this.c.s) {
                z = true;
            }
            aVar.b = z;
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            Message obtain = Message.obtain(null, i);
            try {
                if (this.b != null) {
                    this.b.send(obtain);
                }
                this.d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        /* access modifiers changed from: private */
        public void a(int i, Bundle bundle) {
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.b != null) {
                    this.b.send(obtain);
                }
                this.d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.d++;
                }
                e2.printStackTrace();
            }
        }

        private void a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.b != null) {
                    this.b.send(obtain);
                }
                this.d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        public void a() {
            a(111);
        }

        public void a(BDLocation bDLocation) {
            a(bDLocation, 21);
        }

        public void a(BDLocation bDLocation, int i) {
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (g.a().e()) {
                bDLocation2.a(true);
            }
            if (i == 21) {
                a(27, "locStr", bDLocation2);
            }
            if (this.c.a != null && !this.c.a.equals("gcj02")) {
                double i2 = bDLocation2.i();
                double h = bDLocation2.h();
                if (!(i2 == Double.MIN_VALUE || h == Double.MIN_VALUE)) {
                    if ((bDLocation2.m() != null && bDLocation2.m().equals("gcj02")) || bDLocation2.m() == null) {
                        double[] a2 = Jni.a(i2, h, this.c.a);
                        bDLocation2.b(a2[0]);
                        bDLocation2.a(a2[1]);
                        bDLocation2.d(this.c.a);
                    } else if (bDLocation2.m() != null && bDLocation2.m().equals("wgs84") && !this.c.a.equals("bd09ll")) {
                        double[] a3 = Jni.a(i2, h, "wgs842mc");
                        bDLocation2.b(a3[0]);
                        bDLocation2.a(a3[1]);
                        bDLocation2.d("wgs84mc");
                    }
                }
            }
            a(i, "locStr", bDLocation2);
        }

        public void b() {
            if (!this.c.h) {
                return;
            }
            if (j.b) {
                a(54);
            } else {
                a(55);
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ a a;
        private int b;
        private boolean c;

        public void run() {
            if (!this.c) {
                this.b++;
                this.a.l = false;
            }
        }
    }

    private a() {
        this.f = null;
        this.g = false;
        this.a = false;
        this.b = false;
        this.h = null;
        this.i = null;
        this.d = 0;
        this.j = null;
        this.k = false;
        this.l = false;
        this.m = null;
        this.f = new ArrayList<>();
    }

    private C0023a a(Messenger messenger) {
        if (this.f == null) {
            return null;
        }
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            C0023a aVar = (C0023a) it.next();
            if (aVar.b.equals(messenger)) {
                return aVar;
            }
        }
        return null;
    }

    public static a a() {
        if (e == null) {
            e = new a();
        }
        return e;
    }

    private void a(C0023a aVar) {
        if (aVar != null) {
            if (a(aVar.b) != null) {
                aVar.a(14);
                return;
            }
            this.f.add(aVar);
            aVar.a(13);
        }
    }

    private void b(String str) {
        Intent intent = new Intent("com.baidu.location.flp.log");
        intent.setPackage("com.baidu.baidulocationdemo");
        intent.putExtra("data", str);
        intent.putExtra("pack", com.baidu.location.g.b.d);
        intent.putExtra("tag", "state");
        f.c().sendBroadcast(intent);
    }

    private void f() {
        g();
        e();
    }

    private void g() {
        Iterator it = this.f.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            C0023a aVar = (C0023a) it.next();
            if (aVar.c.c) {
                z2 = true;
            }
            z = aVar.c.h ? true : z;
        }
        j.a = z;
        if (this.g != z2) {
            this.g = z2;
            e.a().a(this.g);
        }
    }

    public void a(Bundle bundle, int i2) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            try {
                C0023a aVar = (C0023a) it.next();
                aVar.a(i2, bundle);
                if (aVar.d > 4) {
                    it.remove();
                }
            } catch (Exception e2) {
                return;
            }
        }
    }

    public void a(Message message) {
        if (message != null && message.replyTo != null) {
            c = System.currentTimeMillis();
            this.a = true;
            i.a().b();
            a(new C0023a(this, message));
            f();
            if (this.k) {
                b(MessageKey.MSG_ACCEPT_TIME_START);
                this.d = 0;
            }
        }
    }

    public void a(BDLocation bDLocation) {
        b(bDLocation);
    }

    public void a(String str) {
        c(new BDLocation(str));
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b() {
        this.f.clear();
        this.h = null;
        f();
    }

    public void b(Message message) {
        C0023a a2 = a(message.replyTo);
        if (a2 != null) {
            this.f.remove(a2);
        }
        n.a().c();
        f();
        if (this.k) {
            b("stop");
            this.d = 0;
        }
    }

    public void b(BDLocation bDLocation) {
        if (bDLocation == null || bDLocation.o() != 161 || j.a().b()) {
            if (!bDLocation.n() && this.b && (bDLocation.o() == 161 || bDLocation.o() == 66)) {
                double d2 = com.baidu.location.b.a.a().a(bDLocation.i(), bDLocation.h())[0];
                com.baidu.location.b.a.a();
                if (d2 < 10000.0d - 1.0d) {
                    bDLocation.c(d2);
                }
            }
            if (bDLocation.o() == 61) {
                bDLocation.i(com.baidu.location.b.a.a().a(bDLocation));
            }
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                try {
                    C0023a aVar = (C0023a) it.next();
                    aVar.a(bDLocation);
                    if (aVar.d > 4) {
                        it.remove();
                    }
                } catch (Exception e2) {
                }
            }
        } else {
            if (this.i == null) {
                this.i = new BDLocation();
                this.i.e((int) ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_COUNTS);
            }
            Iterator it2 = this.f.iterator();
            while (it2.hasNext()) {
                try {
                    C0023a aVar2 = (C0023a) it2.next();
                    aVar2.a(this.i);
                    if (aVar2.d > 4) {
                        it2.remove();
                    }
                } catch (Exception e3) {
                }
            }
        }
        boolean z = k.g;
        if (z) {
            k.g = false;
        }
        if (j.W < 10000) {
            return;
        }
        if (bDLocation.o() != 61 && bDLocation.o() != 161 && bDLocation.o() != 66) {
            return;
        }
        if (this.h != null) {
            float[] fArr = new float[1];
            Location.distanceBetween(this.h.h(), this.h.i(), bDLocation.h(), bDLocation.i(), fArr);
            if (fArr[0] > ((float) j.Y) || z) {
                this.h = null;
                this.h = new BDLocation(bDLocation);
                return;
            }
            return;
        }
        this.h = new BDLocation(bDLocation);
    }

    public void c() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((C0023a) it.next()).a();
        }
    }

    public void c(BDLocation bDLocation) {
        com.baidu.location.a a2 = k.c().a(bDLocation);
        String f2 = k.c().f();
        List g2 = k.c().g();
        if (a2 != null) {
            bDLocation.a(a2);
        }
        if (f2 != null) {
            bDLocation.g(f2);
        }
        if (g2 != null) {
            bDLocation.a(g2);
        }
        if (g.a().f() && g.a().g() != null) {
            bDLocation.h(g.a().g());
            bDLocation.a(true);
            if (g.a().h() != null) {
                bDLocation.i(g.a().h());
            }
        }
        a(bDLocation);
        k.c().c(bDLocation);
    }

    public boolean c(Message message) {
        boolean z = false;
        C0023a a2 = a(message.replyTo);
        if (a2 != null) {
            int i2 = a2.c.d;
            a2.c.d = message.getData().getInt("scanSpan", a2.c.d);
            if (a2.c.d < 1000) {
                n.a().c();
                this.a = false;
            } else {
                this.a = true;
            }
            if (a2.c.d > 999 && i2 < 1000) {
                if (a2.c.n || a2.c.s) {
                    n.a().a(a2.c.n);
                    n.a().b();
                }
                if (this.b || a2.c.s) {
                    z = true;
                }
                this.b = z;
                z = true;
            }
            a2.c.c = message.getData().getBoolean("openGPS", a2.c.c);
            String string = message.getData().getString("coorType");
            h hVar = a2.c;
            if (string == null || string.equals("")) {
                string = a2.c.a;
            }
            hVar.a = string;
            String string2 = message.getData().getString("addrType");
            h hVar2 = a2.c;
            if (string2 == null || string2.equals("")) {
                string2 = a2.c.b;
            }
            hVar2.b = string2;
            if (!j.g.equals(a2.c.b)) {
                k.c().j();
            }
            a2.c.e = message.getData().getInt("timeOut", a2.c.e);
            a2.c.h = message.getData().getBoolean("location_change_notify", a2.c.h);
            a2.c.g = message.getData().getInt("priority", a2.c.g);
            int i3 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
            if (i3 < j.af) {
                j.af = i3;
            }
            f();
        }
        return z;
    }

    public int d(Message message) {
        if (message == null || message.replyTo == null) {
            return 1;
        }
        C0023a a2 = a(message.replyTo);
        if (a2 == null || a2.c == null) {
            return 1;
        }
        return a2.c.g;
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.f.isEmpty()) {
            return "&prod=" + com.baidu.location.g.b.e + Config.TRACE_TODAY_VISIT_SPLIT + com.baidu.location.g.b.d;
        }
        C0023a aVar = (C0023a) this.f.get(0);
        if (aVar.c.f != null) {
            stringBuffer.append(aVar.c.f);
        }
        if (aVar.a != null) {
            stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
            stringBuffer.append(aVar.a);
            stringBuffer.append("|");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2 == null || stringBuffer2.equals("")) {
            return null;
        }
        return "&prod=" + stringBuffer2;
    }

    public int e(Message message) {
        if (message == null || message.replyTo == null) {
            return 1000;
        }
        C0023a a2 = a(message.replyTo);
        if (a2 == null || a2.c == null) {
            return 1000;
        }
        return a2.c.d;
    }

    public void e() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((C0023a) it.next()).b();
        }
    }
}
