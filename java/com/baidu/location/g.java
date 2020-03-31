package com.baidu.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.baidu.location.a.c;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.api.MidEntity;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public final class g implements com.baidu.location.a.c.a {
    private Boolean A = Boolean.valueOf(false);
    /* access modifiers changed from: private */
    public Boolean B = Boolean.valueOf(true);
    private boolean C;
    /* access modifiers changed from: private */
    public c D = null;
    /* access modifiers changed from: private */
    public boolean E = false;
    /* access modifiers changed from: private */
    public boolean F = false;
    private boolean G = false;
    private ServiceConnection H = new j(this);
    private long a = 0;
    private String b = null;
    /* access modifiers changed from: private */
    public h c = new h();
    /* access modifiers changed from: private */
    public h d = new h();
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public Context f = null;
    /* access modifiers changed from: private */
    public Messenger g = null;
    /* access modifiers changed from: private */
    public a h;
    /* access modifiers changed from: private */
    public final Messenger i;
    /* access modifiers changed from: private */
    public ArrayList<c> j = null;
    /* access modifiers changed from: private */
    public ArrayList<b> k = null;
    private BDLocation l = null;
    private boolean m = false;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public b p = null;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public boolean f377q = false;
    /* access modifiers changed from: private */
    public final Object r = new Object();
    private long s = 0;
    private long t = 0;
    private com.baidu.location.c.a u = null;
    private c v = null;
    private String w = null;
    private boolean x = false;
    /* access modifiers changed from: private */
    public boolean y = true;
    private Boolean z = Boolean.valueOf(false);

    private static class a extends Handler {
        private final WeakReference<g> a;

        a(Looper looper, g gVar) {
            super(looper);
            this.a = new WeakReference<>(gVar);
        }

        public void handleMessage(Message message) {
            g gVar = (g) this.a.get();
            if (gVar != null) {
                switch (message.what) {
                    case 1:
                        gVar.e();
                        return;
                    case 2:
                        gVar.f();
                        return;
                    case 3:
                        gVar.c(message);
                        return;
                    case 4:
                        gVar.i();
                        return;
                    case 5:
                        gVar.e(message);
                        return;
                    case 6:
                        gVar.h(message);
                        return;
                    case 7:
                        return;
                    case 8:
                        gVar.d(message);
                        return;
                    case 9:
                        gVar.a(message);
                        return;
                    case 10:
                        gVar.b(message);
                        return;
                    case 11:
                        gVar.h();
                        return;
                    case 12:
                        gVar.d();
                        return;
                    case 21:
                        Bundle data = message.getData();
                        data.setClassLoader(BDLocation.class.getClassLoader());
                        BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
                        if (gVar.F || !gVar.E || bDLocation.o() != 66) {
                            if (gVar.F || !gVar.E) {
                                if (!gVar.F) {
                                    gVar.F = true;
                                }
                                gVar.a(message, 21);
                                return;
                            }
                            gVar.F = true;
                            return;
                        }
                        return;
                    case 26:
                        gVar.a(message, 26);
                        return;
                    case 27:
                        gVar.i(message);
                        return;
                    case 54:
                        if (gVar.c.h) {
                            gVar.f377q = true;
                            return;
                        }
                        return;
                    case 55:
                        if (gVar.c.h) {
                            gVar.f377q = false;
                            return;
                        }
                        return;
                    case ErrorCode.ERROR_UNMATCH_TBSCORE_VER /*303*/:
                        try {
                            Bundle data2 = message.getData();
                            int i = data2.getInt("loctype");
                            int i2 = data2.getInt("diagtype");
                            byte[] byteArray = data2.getByteArray("diagmessage");
                            if (i > 0 && i2 > 0 && byteArray != null && gVar.k != null) {
                                Iterator it = gVar.k.iterator();
                                while (it.hasNext()) {
                                    ((b) it.next()).a(i, i2, new String(byteArray, "UTF-8"));
                                }
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    case ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD /*406*/:
                        try {
                            Bundle data3 = message.getData();
                            byte[] byteArray2 = data3.getByteArray(MidEntity.TAG_MAC);
                            String str = null;
                            if (byteArray2 != null) {
                                str = new String(byteArray2, "UTF-8");
                            }
                            int i3 = data3.getInt("hotspot", -1);
                            if (gVar.k != null) {
                                Iterator it2 = gVar.k.iterator();
                                while (it2.hasNext()) {
                                    ((b) it2.next()).a(str, i3);
                                }
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            return;
                        }
                    case 701:
                        gVar.b((BDLocation) message.obj);
                        return;
                    case 703:
                        try {
                            Bundle data4 = message.getData();
                            int i4 = data4.getInt(Config.FEED_LIST_ITEM_CUSTOM_ID, 0);
                            if (i4 > 0) {
                                gVar.a(i4, (Notification) data4.getParcelable("notification"));
                                return;
                            }
                            return;
                        } catch (Exception e3) {
                            return;
                        }
                    case ErrorCode.INFO_COOKIE_SWITCH_TRANSFER /*704*/:
                        try {
                            gVar.a(message.getData().getBoolean("removenotify"));
                            return;
                        } catch (Exception e4) {
                            return;
                        }
                    case 1300:
                        gVar.f(message);
                        return;
                    case 1400:
                        gVar.g(message);
                        return;
                    default:
                        super.handleMessage(message);
                        return;
                }
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(g gVar, j jVar) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r3 = 1
                com.baidu.location.g r0 = com.baidu.location.g.this
                java.lang.Object r1 = r0.r
                monitor-enter(r1)
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                r2 = 0
                r0.o = r2     // Catch:{ all -> 0x004a }
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                android.os.Messenger r0 = r0.g     // Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x001e
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                android.os.Messenger r0 = r0.i     // Catch:{ all -> 0x004a }
                if (r0 != 0) goto L_0x0020
            L_0x001e:
                monitor-exit(r1)     // Catch:{ all -> 0x004a }
            L_0x001f:
                return
            L_0x0020:
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                java.util.ArrayList r0 = r0.j     // Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x0034
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                java.util.ArrayList r0 = r0.j     // Catch:{ all -> 0x004a }
                int r0 = r0.size()     // Catch:{ all -> 0x004a }
                if (r0 >= r3) goto L_0x004d
            L_0x0034:
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                java.util.ArrayList r0 = r0.k     // Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x0048
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                java.util.ArrayList r0 = r0.k     // Catch:{ all -> 0x004a }
                int r0 = r0.size()     // Catch:{ all -> 0x004a }
                if (r0 >= r3) goto L_0x004d
            L_0x0048:
                monitor-exit(r1)     // Catch:{ all -> 0x004a }
                goto L_0x001f
            L_0x004a:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x004a }
                throw r0
            L_0x004d:
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                boolean r0 = r0.n     // Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x0083
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.g$b r0 = r0.p     // Catch:{ all -> 0x004a }
                if (r0 != 0) goto L_0x0069
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.g$b r2 = new com.baidu.location.g$b     // Catch:{ all -> 0x004a }
                com.baidu.location.g r3 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                r2.<init>()     // Catch:{ all -> 0x004a }
                r0.p = r2     // Catch:{ all -> 0x004a }
            L_0x0069:
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.g$a r0 = r0.h     // Catch:{ all -> 0x004a }
                com.baidu.location.g r2 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.g$b r2 = r2.p     // Catch:{ all -> 0x004a }
                com.baidu.location.g r3 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.h r3 = r3.c     // Catch:{ all -> 0x004a }
                int r3 = r3.d     // Catch:{ all -> 0x004a }
                long r4 = (long) r3     // Catch:{ all -> 0x004a }
                r0.postDelayed(r2, r4)     // Catch:{ all -> 0x004a }
                monitor-exit(r1)     // Catch:{ all -> 0x004a }
                goto L_0x001f
            L_0x0083:
                com.baidu.location.g r0 = com.baidu.location.g.this     // Catch:{ all -> 0x004a }
                com.baidu.location.g$a r0 = r0.h     // Catch:{ all -> 0x004a }
                r2 = 4
                android.os.Message r0 = r0.obtainMessage(r2)     // Catch:{ all -> 0x004a }
                r0.sendToTarget()     // Catch:{ all -> 0x004a }
                monitor-exit(r1)     // Catch:{ all -> 0x004a }
                goto L_0x001f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.b.run():void");
        }
    }

    public g(Context context) {
        this.f = context;
        this.c = new h();
        this.h = new a(Looper.getMainLooper(), this);
        this.i = new Messenger(this.h);
    }

    private void a(int i2) {
        if (this.l.m() == null) {
            this.l.d(this.c.a);
        }
        if (this.m || ((this.c.h && this.l.o() == 61) || this.l.o() == 66 || this.l.o() == 67 || this.x || this.l.o() == 161)) {
            if (this.j != null) {
                Iterator it = this.j.iterator();
                while (it.hasNext()) {
                    ((c) it.next()).a(this.l);
                }
            }
            if (this.k != null) {
                Iterator it2 = this.k.iterator();
                while (it2.hasNext()) {
                    ((b) it2.next()).a(this.l);
                }
            }
            if (this.l.o() != 66 && this.l.o() != 67) {
                this.m = false;
                this.t = System.currentTimeMillis();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Notification notification) {
        try {
            Intent intent = new Intent(this.f, f.class);
            intent.putExtra("notification", notification);
            intent.putExtra(Config.FEED_LIST_ITEM_CUSTOM_ID, i2);
            intent.putExtra("command", 1);
            if (VERSION.SDK_INT >= 26) {
                this.f.startForegroundService(intent);
            } else {
                this.f.startService(intent);
            }
            this.G = true;
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (message != null && message.obj != null) {
            d dVar = (d) message.obj;
            if (this.u == null) {
                this.u = new com.baidu.location.c.a(this.f, this);
            }
            this.u.a(dVar);
        }
    }

    /* access modifiers changed from: private */
    public void a(Message message, int i2) {
        if (this.e) {
            try {
                Bundle data = message.getData();
                data.setClassLoader(BDLocation.class.getClassLoader());
                this.l = (BDLocation) data.getParcelable("locStr");
                if (this.l.o() == 61) {
                    this.s = System.currentTimeMillis();
                }
                a(i2);
            } catch (Exception e2) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        try {
            Intent intent = new Intent(this.f, f.class);
            intent.putExtra("removenotify", z2);
            intent.putExtra("command", 2);
            this.f.startService(intent);
            this.G = true;
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public void b(Message message) {
        if (message != null && message.obj != null) {
            d dVar = (d) message.obj;
            if (this.u != null) {
                this.u.b(dVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(BDLocation bDLocation) {
        if (!this.y) {
            this.l = bDLocation;
            if (!this.F && bDLocation.o() == 161) {
                this.E = true;
            }
            if (this.j != null) {
                Iterator it = this.j.iterator();
                while (it.hasNext()) {
                    ((c) it.next()).a(bDLocation);
                }
            }
            if (this.k != null) {
                Iterator it2 = this.k.iterator();
                while (it2.hasNext()) {
                    ((b) it2.next()).a(bDLocation);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(Message message) {
        this.n = false;
        if (message != null && message.obj != null) {
            h hVar = (h) message.obj;
            if (!this.c.a(hVar)) {
                if (this.c.d != hVar.d) {
                    try {
                        synchronized (this.r) {
                            if (this.o) {
                                this.h.removeCallbacks(this.p);
                                this.o = false;
                            }
                            if (hVar.d >= 1000 && !this.o) {
                                if (this.p == null) {
                                    this.p = new b(this, null);
                                }
                                this.h.postDelayed(this.p, (long) hVar.d);
                                this.o = true;
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
                this.c = new h(hVar);
                if (this.g != null) {
                    try {
                        Message obtain = Message.obtain(null, 15);
                        obtain.replyTo = this.i;
                        obtain.setData(g());
                        this.g.send(obtain);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        Message obtain = Message.obtain(null, 28);
        try {
            obtain.replyTo = this.i;
            this.g.send(obtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void d(Message message) {
        if (message != null && message.obj != null) {
            this.v = (c) message.obj;
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.e) {
            if (this.B.booleanValue()) {
                try {
                    new k(this).start();
                } catch (Throwable th) {
                }
                this.B = Boolean.valueOf(false);
            }
            this.b = this.f.getPackageName();
            this.w = this.b + "_bdls_v2.9";
            Intent intent = new Intent(this.f, f.class);
            try {
                intent.putExtra("debug_dev", this.C);
            } catch (Exception e2) {
            }
            if (this.c == null) {
                this.c = new h();
            }
            intent.putExtra("cache_exception", this.c.l);
            intent.putExtra("kill_process", this.c.m);
            try {
                this.f.bindService(intent, this.H, 1);
            } catch (Exception e3) {
                e3.printStackTrace();
                this.e = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public void e(Message message) {
        if (message != null && message.obj != null) {
            c cVar = (c) message.obj;
            if (this.j == null) {
                this.j = new ArrayList<>();
            }
            if (!this.j.contains(cVar)) {
                this.j.add(cVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.e && this.g != null) {
            Message obtain = Message.obtain(null, 12);
            obtain.replyTo = this.i;
            try {
                this.g.send(obtain);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                this.f.unbindService(this.H);
                if (this.G) {
                    try {
                        this.f.stopService(new Intent(this.f, f.class));
                    } catch (Exception e3) {
                    }
                    this.G = false;
                }
            } catch (Exception e4) {
            }
            synchronized (this.r) {
                try {
                    if (this.o) {
                        this.h.removeCallbacks(this.p);
                        this.o = false;
                    }
                } catch (Exception e5) {
                }
            }
            if (this.u != null) {
                this.u.a();
            }
            this.g = null;
            this.n = false;
            this.x = false;
            this.e = false;
            this.E = false;
            this.F = false;
        }
    }

    /* access modifiers changed from: private */
    public void f(Message message) {
        if (message != null && message.obj != null) {
            b bVar = (b) message.obj;
            if (this.k == null) {
                this.k = new ArrayList<>();
            }
            if (!this.k.contains(bVar)) {
                this.k.add(bVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public Bundle g() {
        if (this.c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FLAG_PACK_NAME, this.b);
        bundle.putString("prodName", this.c.f);
        bundle.putString("coorType", this.c.a);
        bundle.putString("addrType", this.c.b);
        bundle.putBoolean("openGPS", this.c.c);
        bundle.putBoolean("location_change_notify", this.c.h);
        bundle.putInt("scanSpan", this.c.d);
        bundle.putBoolean("enableSimulateGps", this.c.j);
        bundle.putInt("timeOut", this.c.e);
        bundle.putInt("priority", this.c.g);
        bundle.putBoolean("map", this.z.booleanValue());
        bundle.putBoolean("import", this.A.booleanValue());
        bundle.putBoolean("needDirect", this.c.n);
        bundle.putBoolean("isneedaptag", this.c.o);
        bundle.putBoolean("isneedpoiregion", this.c.f380q);
        bundle.putBoolean("isneedregular", this.c.r);
        bundle.putBoolean("isneedaptagd", this.c.p);
        bundle.putBoolean("isneedaltitude", this.c.s);
        bundle.putBoolean("isneednewrgc", this.c.t);
        bundle.putInt("autoNotifyMaxInterval", this.c.c());
        bundle.putInt("autoNotifyMinTimeInterval", this.c.d());
        bundle.putInt("autoNotifyMinDistance", this.c.e());
        bundle.putFloat("autoNotifyLocSensitivity", this.c.f());
        bundle.putInt("wifitimeout", this.c.z);
        return bundle;
    }

    /* access modifiers changed from: private */
    public void g(Message message) {
        if (message != null && message.obj != null) {
            b bVar = (b) message.obj;
            if (this.k != null && this.k.contains(bVar)) {
                this.k.remove(bVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.g != null) {
            Message obtain = Message.obtain(null, 22);
            try {
                obtain.replyTo = this.i;
                this.g.send(obtain);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void h(Message message) {
        if (message != null && message.obj != null) {
            c cVar = (c) message.obj;
            if (this.j != null && this.j.contains(cVar)) {
                this.j.remove(cVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.g != null) {
            if ((System.currentTimeMillis() - this.s > 3000 || ((this.c != null && !this.c.h) || this.n)) && (!this.x || System.currentTimeMillis() - this.t > 20000 || this.n)) {
                Message obtain = Message.obtain(null, 22);
                if (this.n) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isWaitingLocTag", this.n);
                    this.n = false;
                    obtain.setData(bundle);
                }
                try {
                    obtain.replyTo = this.i;
                    this.g.send(obtain);
                    this.a = System.currentTimeMillis();
                    this.m = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            synchronized (this.r) {
                if (this.c != null && this.c.d >= 1000 && !this.o) {
                    if (this.p == null) {
                        this.p = new b(this, null);
                    }
                    this.h.postDelayed(this.p, (long) this.c.d);
                    this.o = true;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void i(Message message) {
        try {
            Bundle data = message.getData();
            data.setClassLoader(BDLocation.class.getClassLoader());
            BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
            if (this.v == null) {
                return;
            }
            if (this.c == null || !this.c.g() || bDLocation.o() != 65) {
                this.v.a(bDLocation);
            }
        } catch (Exception e2) {
        }
    }

    public void a() {
        this.h.obtainMessage(11).sendToTarget();
    }

    public void a(BDLocation bDLocation) {
        if ((!this.F || this.E) && bDLocation != null) {
            Message obtainMessage = this.h.obtainMessage(701);
            obtainMessage.obj = bDLocation;
            obtainMessage.sendToTarget();
        }
    }

    public void a(b bVar) {
        if (bVar == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.h.obtainMessage(1300);
        obtainMessage.obj = bVar;
        obtainMessage.sendToTarget();
    }

    public void a(c cVar) {
        Message obtainMessage = this.h.obtainMessage(8);
        obtainMessage.obj = cVar;
        obtainMessage.sendToTarget();
    }

    public void a(h hVar) {
        if (hVar == null) {
            hVar = new h();
        }
        if (hVar.c() > 0) {
            hVar.a(0);
            hVar.b(true);
        }
        this.d = new h(hVar);
        Message obtainMessage = this.h.obtainMessage(3);
        obtainMessage.obj = hVar;
        obtainMessage.sendToTarget();
    }

    public void b() {
        this.y = false;
        this.h.obtainMessage(1).sendToTarget();
    }

    public void c() {
        this.y = true;
        this.h.obtainMessage(2).sendToTarget();
        this.D = null;
    }
}
