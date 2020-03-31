package cn.sharesdk.framework.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.f;
import cn.sharesdk.framework.b.b.c;
import cn.sharesdk.framework.b.b.e;
import cn.sharesdk.framework.b.b.g;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import java.io.File;
import java.util.Calendar;

/* compiled from: StatisticsLogger */
public class d extends cn.sharesdk.framework.utils.d {
    private static d b;
    private DeviceHelper c = DeviceHelper.getInstance(MobSDK.getContext());
    /* access modifiers changed from: private */
    public a d = a.a();
    private Handler e;
    private boolean f;
    private long g;
    private boolean h;
    private File i = new File(MobSDK.getContext().getFilesDir(), ".statistics");
    private FileLocker j = new FileLocker();

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    private d() {
        if (!this.i.exists()) {
            try {
                this.i.createNewFile();
            } catch (Exception e2) {
                SSDKLog.b().d(e2);
            }
        }
    }

    public void a(Handler handler) {
        this.e = handler;
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (!this.f) {
            this.f = true;
            try {
                this.j.setLockFile(this.i.getAbsolutePath());
                if (this.j.lock(false)) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                d.this.d.a(DeviceAuthorizer.authorize(new SHARESDK()));
                            } catch (Exception e) {
                                SSDKLog.b().d(e);
                            }
                        }
                    }).start();
                    this.d.b();
                    this.d.c();
                    ShareSDK.setEnableAuthTag(true);
                    e();
                    this.a.sendEmptyMessageDelayed(4, 3600000);
                    this.a.sendEmptyMessage(1);
                    this.a.sendEmptyMessage(2);
                }
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void c(Message message) {
        if (this.f) {
            long currentTimeMillis = System.currentTimeMillis() - this.g;
            e eVar = new e();
            eVar.a = currentTimeMillis;
            a((c) eVar);
            this.f = false;
            try {
                this.e.sendEmptyMessage(1);
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
            b = null;
            this.a.getLooper().quit();
        }
    }

    public void a(final c cVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() {
                public void run() {
                    d.this.b(cVar);
                }
            }.start();
        } else {
            b(cVar);
        }
    }

    public void b(c cVar) {
        if (MobSDK.isMob() && this.f) {
            c(cVar);
            if (cVar.g()) {
                Message message = new Message();
                message.what = 3;
                message.obj = cVar;
                try {
                    this.a.sendMessage(message);
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                }
            } else {
                SSDKLog.b().d("Drop event: " + cVar.toString(), new Object[0]);
            }
        }
    }

    private void c(c cVar) {
        cVar.f = this.c.getDeviceKey();
        cVar.g = this.c.getPackageName();
        cVar.h = this.c.getAppVersion();
        cVar.i = String.valueOf(ShareSDK.SDK_VERSION_CODE);
        cVar.j = this.c.getPlatformCode();
        cVar.k = this.c.getDetailNetworkTypeForStatic();
        if (TextUtils.isEmpty(MobSDK.getAppkey())) {
            Log.w("ShareSDKCore", "Your appKey of ShareSDK is null , this will cause its data won't be count!");
        } else if (!"cn.sharesdk.demo".equals(cVar.g) && ("api20".equals(MobSDK.getAppkey()) || "androidv1101".equals(MobSDK.getAppkey()))) {
            Log.w("ShareSDKCore", "Your app is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
        }
        cVar.l = this.c.getDeviceData();
    }

    /* access modifiers changed from: protected */
    public void b(Message message) {
        switch (message.what) {
            case 1:
                b();
                try {
                    this.a.sendEmptyMessageDelayed(1, Config.BPLUS_DELAY_TIME);
                    return;
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                    return;
                }
            case 2:
                try {
                    this.d.d();
                    return;
                } catch (Throwable th2) {
                    SSDKLog.b().d(th2);
                    return;
                }
            case 3:
                if (message.obj != null) {
                    d((c) message.obj);
                    this.a.removeMessages(2);
                    this.a.sendEmptyMessageDelayed(2, 2000);
                    return;
                }
                return;
            case 4:
                long longValue = cn.sharesdk.framework.b.a.e.a().j().longValue();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i2 = instance.get(1);
                int i3 = instance.get(2);
                int i4 = instance.get(5);
                instance.setTimeInMillis(System.currentTimeMillis());
                int i5 = instance.get(1);
                int i6 = instance.get(2);
                int i7 = instance.get(5);
                if (!(i2 == i5 && i3 == i6 && i4 == i7)) {
                    this.d.c();
                }
                this.a.sendEmptyMessageDelayed(4, 3600000);
                return;
            default:
                return;
        }
    }

    private void d(c cVar) {
        try {
            this.d.a(cVar);
            cVar.h();
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            SSDKLog.b().d(cVar.toString(), new Object[0]);
        }
    }

    private void b() {
        boolean c2 = c();
        if (c2) {
            if (!this.h) {
                this.h = c2;
                this.g = System.currentTimeMillis();
                a((c) new g());
            }
        } else if (this.h) {
            this.h = c2;
            long currentTimeMillis = System.currentTimeMillis() - this.g;
            e eVar = new e();
            eVar.a = currentTimeMillis;
            a((c) eVar);
        }
    }

    private boolean c() {
        return DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground();
    }

    private void e() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    f.c().d();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }
}
