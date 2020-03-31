package com.baidu.location.d;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.baidu.location.BDLocation;
import com.baidu.location.e.h;
import com.baidu.location.f;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class g {
    public static final String a = com.baidu.location.g.a.a;
    static final String b = "http://ofloc.map.baidu.com/offline_loc";
    /* access modifiers changed from: private */
    public static Context c;
    private static volatile g d;
    private static Object e = new Object();
    private final File f;
    private final j g;
    /* access modifiers changed from: private */
    public final c h;
    private final k i;
    /* access modifiers changed from: private */
    public final f j;

    public enum a {
        NEED_TO_LOG,
        NO_NEED_TO_LOG
    }

    public enum b {
        IS_MIX_MODE,
        IS_NOT_MIX_MODE
    }

    private enum c {
        NETWORK_UNKNOWN,
        NETWORK_WIFI,
        NETWORK_2G,
        NETWORK_3G,
        NETWORK_4G
    }

    private g() {
        File file;
        try {
            file = new File(c.getFilesDir(), "ofld");
            try {
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (Exception e2) {
            }
        } catch (Exception e3) {
            file = null;
        }
        this.f = file;
        this.h = new c(this);
        this.g = new j(this.h.a());
        this.j = new f(this, this.h.a());
        this.i = new k(this, this.h.a(), this.j.n());
    }

    private BDLocation a(String[] strArr) {
        new BDLocation();
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        FutureTask futureTask = (FutureTask) newSingleThreadExecutor.submit(new h(this, strArr));
        try {
            BDLocation bDLocation = (BDLocation) futureTask.get(2000, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdown();
            return bDLocation;
        } catch (InterruptedException e2) {
            futureTask.cancel(true);
            newSingleThreadExecutor.shutdown();
            return null;
        } catch (ExecutionException e3) {
            futureTask.cancel(true);
            newSingleThreadExecutor.shutdown();
            return null;
        } catch (TimeoutException e4) {
            futureTask.cancel(true);
            newSingleThreadExecutor.shutdown();
            return null;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdown();
            throw th;
        }
    }

    public static g a() {
        g gVar;
        synchronized (e) {
            if (d == null) {
                if (c == null) {
                    a(f.c());
                }
                d = new g();
            }
            d.q();
            gVar = d;
        }
        return gVar;
    }

    public static void a(Context context) {
        if (c == null) {
            c = context;
            com.baidu.location.g.b.a().a(c);
        }
    }

    /* access modifiers changed from: private */
    public static final Uri c(String str) {
        return Uri.parse(String.format("content://%s/", new Object[]{str}));
    }

    private void q() {
        this.j.g();
    }

    private boolean r() {
        ProviderInfo providerInfo;
        String packageName = c.getPackageName();
        if (0 == 0) {
            String[] o = this.j.o();
            providerInfo = null;
            for (int i2 = 0; i2 < o.length; i2++) {
                try {
                    providerInfo = c.getPackageManager().resolveContentProvider(o[i2], 0);
                } catch (Exception e2) {
                    providerInfo = null;
                }
                if (providerInfo != null) {
                    break;
                }
            }
        } else {
            providerInfo = null;
        }
        if (providerInfo == null) {
            return true;
        }
        return packageName.equals(providerInfo.packageName);
    }

    public long a(String str) {
        return this.j.a(str);
    }

    public BDLocation a(com.baidu.location.e.a aVar, h hVar, BDLocation bDLocation, b bVar, a aVar2) {
        String d2;
        int i2;
        if (bVar == b.IS_MIX_MODE) {
            i2 = this.j.a();
            d2 = com.baidu.location.g.b.a().d() + "&mixMode=1";
        } else {
            d2 = com.baidu.location.g.b.a().d();
            i2 = 0;
        }
        String[] a2 = i.a(aVar, hVar, bDLocation, d2, (aVar2 == a.NEED_TO_LOG ? Boolean.valueOf(true) : Boolean.valueOf(false)).booleanValue(), i2);
        BDLocation bDLocation2 = null;
        if (a2.length > 0) {
            bDLocation2 = a(a2);
            if (bDLocation2 == null || bDLocation2.o() != 67) {
            }
        }
        return bDLocation2;
    }

    public Context b() {
        return c;
    }

    /* access modifiers changed from: 0000 */
    public File c() {
        return this.f;
    }

    public boolean d() {
        return this.j.h();
    }

    public boolean e() {
        return this.j.i();
    }

    public boolean f() {
        return this.j.j();
    }

    public boolean g() {
        return this.j.k();
    }

    public boolean h() {
        return this.j.m();
    }

    public void i() {
        this.g.a();
    }

    /* access modifiers changed from: 0000 */
    public j j() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public k k() {
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public f l() {
        return this.j;
    }

    public void m() {
        if (r()) {
            this.h.b();
        }
    }

    public void n() {
    }

    public double o() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) c.getSystemService("connectivity")).getActiveNetworkInfo();
        c cVar = c.NETWORK_UNKNOWN;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                cVar = c.NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11) {
                    cVar = c.NETWORK_2G;
                } else if (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) {
                    cVar = c.NETWORK_3G;
                } else if (subtype == 13) {
                    cVar = c.NETWORK_4G;
                }
            }
        }
        if (cVar == c.NETWORK_UNKNOWN) {
            return this.j.b();
        }
        if (cVar == c.NETWORK_WIFI) {
            return this.j.c();
        }
        if (cVar == c.NETWORK_2G) {
            return this.j.d();
        }
        if (cVar == c.NETWORK_3G) {
            return this.j.e();
        }
        if (cVar == c.NETWORK_4G) {
            return this.j.f();
        }
        return 0.0d;
    }
}
