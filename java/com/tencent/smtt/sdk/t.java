package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.f;
import java.nio.channels.FileLock;

/* compiled from: X5CoreEngine */
class t {
    private static t a;
    private static FileLock e = null;
    private u b;
    private boolean c;
    private boolean d;

    private t() {
    }

    public static t a() {
        if (a == null) {
            synchronized (t.class) {
                if (a == null) {
                    a = new t();
                }
            }
        }
        return a;
    }

    public boolean b() {
        if (QbSdk.a) {
            return false;
        }
        return this.c;
    }

    public u a(boolean z) {
        if (z) {
            return this.b;
        }
        return c();
    }

    public u c() {
        if (QbSdk.a) {
            return null;
        }
        return this.b;
    }

    public synchronized void a(Context context) {
        Object obj = null;
        synchronized (this) {
            TbsLog.i("X5CoreEngine", "init #1");
            d a2 = d.a(true);
            a2.a(context, false, false);
            StringBuilder sb = new StringBuilder();
            r a3 = a2.a();
            if (!a2.b() || a3 == null) {
                this.c = false;
                sb.append("can not use X5 by !tbs available");
                th = null;
            } else {
                if (!this.d) {
                    this.b = new u(a3.b());
                    try {
                        this.c = this.b.a();
                        if (!this.c) {
                            sb.append("can not use X5 by x5corewizard return false");
                        }
                        th = null;
                    } catch (NoSuchMethodException e2) {
                        this.c = true;
                        th = null;
                    } catch (Throwable th) {
                        th = th;
                        this.c = false;
                        sb.append("can not use x5 by throwable " + Log.getStackTraceString(th));
                    }
                    if (this.c) {
                        CookieManager.getInstance().a(context, true, true);
                        CookieManager.getInstance().a();
                    }
                }
                th = null;
            }
            TbsLog.i("X5CoreEngine", "init  mCanUseX5 is " + this.c);
            if (!this.c) {
                TbsLog.e("X5CoreEngine", "mCanUseX5 is false --> report");
                if (a2.b() && a3 != null && th == null) {
                    try {
                        DexLoader b2 = a3.b();
                        if (b2 != null) {
                            obj = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
                        }
                        if (obj instanceof Throwable) {
                            Throwable th2 = (Throwable) obj;
                            sb.append("#" + th2.getMessage() + "; cause: " + th2.getCause() + "; th: " + th2);
                        }
                        if (obj instanceof String) {
                            sb.append("failure detail:" + obj);
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    if (sb != null) {
                        if (sb.toString().contains("isPreloadX5Disabled:-10000")) {
                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_DISABLED_BY_CRASH, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + sb.toString()));
                        }
                    }
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_X5, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + sb.toString()));
                } else if (a2.b()) {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_USE_X5_TBS_AVAILABLE, new Throwable("mCanUseX5=false, available true, reason: " + th));
                } else {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_USE_X5_TBS_NOTAVAILABLE, new Throwable("mCanUseX5=false, available false, reason: " + th));
                }
            } else {
                TbsLog.i("X5CoreEngine", "init  sTbsCoreLoadFileLock is " + e);
                if (e == null) {
                    b(context);
                }
            }
            this.d = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return this.d;
    }

    public FileLock b(Context context) {
        TbsLog.i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
        if (e != null) {
            return e;
        }
        synchronized (t.class) {
            if (e == null) {
                e = f.e(context);
                if (e == null) {
                    TbsLog.i("X5CoreEngine", "init -- sTbsCoreLoadFileLock failed!");
                } else {
                    TbsLog.i("X5CoreEngine", "init -- sTbsCoreLoadFileLock succeeded: " + e);
                }
            }
        }
        return e;
    }
}
