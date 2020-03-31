package com.baidu.location.indoor.mapversion.b;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.baidu.location.indoor.mapversion.c.a.d;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class a {
    private static Lock a = new ReentrantLock();

    public static boolean a() {
        return IndoorJni.a;
    }

    public static synchronized boolean a(String str) {
        boolean z;
        synchronized (a.class) {
            if (!a()) {
                z = false;
            } else {
                d b = com.baidu.location.indoor.mapversion.c.a.a().b(str);
                double[][] c = com.baidu.location.indoor.mapversion.c.a.a().c(str);
                if (b == null) {
                    z = false;
                } else {
                    b.a("gcj02");
                    short[][] sArr = b.g;
                    double d = b.a().a;
                    double d2 = b.a().b;
                    d c2 = com.baidu.location.indoor.mapversion.c.a.a().c();
                    if (c2 == null) {
                        z = false;
                    } else {
                        double a2 = c2.a(-b.a().d);
                        double b2 = c2.b(-b.a().f);
                        a.lock();
                        try {
                            IndoorJni.setPfRdnt(str, sArr, d, d2, (int) b.f.g, (int) b.f.h, a2, b2);
                            IndoorJni.setPfGeoMap(c, str, (int) b.f.g, (int) b.f.h);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            a.unlock();
                        }
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* JADX INFO: finally extract failed */
    public static synchronized double[] a(double d, double d2, double d3, double d4, double d5) {
        double[] dArr;
        synchronized (a.class) {
            if (!a()) {
                dArr = null;
            } else {
                com.baidu.location.indoor.mapversion.c.a.a().a(d, d2);
                d c = com.baidu.location.indoor.mapversion.c.a.a().c();
                double a2 = c.a(d);
                double b = c.b(d2);
                double[] dArr2 = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
                a.lock();
                try {
                    dArr = IndoorJni.setPfGps(a2, b, d3, d4, d5, System.currentTimeMillis());
                    a.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                    a.unlock();
                    dArr = dArr2;
                } catch (Throwable th) {
                    a.unlock();
                    throw th;
                }
                if (dArr[0] == 0.0d) {
                    double c2 = c.c(dArr[1]);
                    double d6 = c.d(dArr[2]);
                    dArr[1] = c2;
                    dArr[2] = d6;
                }
            }
        }
        return dArr;
    }

    /* JADX INFO: finally extract failed */
    public static synchronized double[] a(BDLocation bDLocation) {
        double[] dArr;
        synchronized (a.class) {
            if (!a()) {
                dArr = null;
            } else {
                d c = com.baidu.location.indoor.mapversion.c.a.a().c();
                double[] dArr2 = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
                if (c != null) {
                    double a2 = c.a(bDLocation.i());
                    double b = c.b(bDLocation.h());
                    a.lock();
                    try {
                        dArr = IndoorJni.setPfWf(a2, b, 8.0d, System.currentTimeMillis());
                        a.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                        a.unlock();
                        dArr = dArr2;
                    } catch (Throwable th) {
                        a.unlock();
                        throw th;
                    }
                    if (dArr[0] == 0.0d) {
                        double c2 = c.c(dArr[1]);
                        double d = c.d(dArr[2]);
                        dArr[1] = c2;
                        dArr[2] = d;
                    }
                } else {
                    dArr = dArr2;
                }
            }
        }
        return dArr;
    }

    /* JADX INFO: finally extract failed */
    public static synchronized double[] a(String str, double d, double d2, double d3) {
        double[] dArr;
        synchronized (a.class) {
            if (!a()) {
                dArr = null;
            } else {
                d c = com.baidu.location.indoor.mapversion.c.a.a().c();
                double[] dArr2 = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
                if (c != null) {
                    a.lock();
                    try {
                        dArr = IndoorJni.setPfDr(d2, d3, System.currentTimeMillis());
                        a.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                        a.unlock();
                        dArr = dArr2;
                    } catch (Throwable th) {
                        a.unlock();
                        throw th;
                    }
                    if (dArr[0] == 0.0d) {
                        double c2 = c.c(dArr[1]);
                        double d4 = c.d(dArr[2]);
                        dArr[1] = c2;
                        dArr[2] = d4;
                    }
                } else {
                    dArr = dArr2;
                }
            }
        }
        return dArr;
    }

    public static void b() {
        if (a()) {
            a.lock();
            try {
                IndoorJni.initPf();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a.unlock();
            }
        }
    }

    public static void c() {
        if (a()) {
            a.lock();
            try {
                IndoorJni.resetPf();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a.unlock();
            }
        }
    }
}
