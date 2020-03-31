package com.mob.commons.a;

import android.location.Location;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.mob.MobSDK;
import com.mob.commons.LockAction;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.c;
import com.mob.commons.i;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.io.Closeable;
import java.io.File;
import java.util.HashMap;

/* compiled from: BaseClt */
public class d implements Callback {
    /* access modifiers changed from: private */
    public static HashMap<String, d> c = new HashMap<>();
    private static HashMap<String, Object> d = new HashMap<>();
    private MobHandlerThread a;
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public Handler e;

    public static final synchronized void a(Class<? extends d>... clsArr) {
        synchronized (d.class) {
            if (clsArr != null) {
                if (clsArr.length != 0) {
                    for (Class<? extends d> cls : clsArr) {
                        if (cls != null) {
                            String simpleName = cls.getSimpleName();
                            if (((d) c.get(simpleName)) == null) {
                                try {
                                    d dVar = (d) cls.newInstance();
                                    c.put(simpleName, dVar);
                                    dVar.i();
                                } catch (Throwable th) {
                                    MobLog.getInstance().d(th);
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }

    private void i() {
        final File a2 = a();
        if (a2 != null) {
            this.a = new MobHandlerThread() {
                public void run() {
                    try {
                        if (!com.mob.commons.d.a(a2, new LockAction() {
                            public boolean run(FileLocker fileLocker) {
                                try {
                                    MobLog.getInstance().d("synchronizeProcess success clt: " + d.this.getClass().getSimpleName() + ", file: " + a2.getPath() + ", pid: " + Process.myPid() + ", isStop: " + d.this.b, new Object[0]);
                                    if (!d.this.b) {
                                        boolean ac = a.ac();
                                        boolean b_ = d.this.b_();
                                        MobLog.getInstance().d("Clt entrance. forb: " + ac + ", coll: " + b_, new Object[0]);
                                        if (!ac && b_) {
                                            AnonymousClass1.this.a();
                                        }
                                    }
                                } catch (Throwable th) {
                                    MobLog.getInstance().d(th);
                                }
                                return false;
                            }
                        })) {
                            MobLog.getInstance().w("synchronizeProcess failed clt: " + d.this.getClass().getSimpleName() + ", file: " + a2.getPath());
                            d.c.put(getClass().getSimpleName(), null);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }

                /* access modifiers changed from: private */
                public void a() {
                    super.run();
                }

                /* access modifiers changed from: protected */
                public void onLooperPrepared(Looper looper) {
                    try {
                        d.this.e = new Handler(looper, d.this);
                        d.this.d();
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }
            };
            this.a.start();
        }
    }

    public final boolean handleMessage(Message message) {
        if (a.J()) {
            f();
        } else {
            a(message);
        }
        return false;
    }

    public static void a(String str, File file, String str2, String str3) throws Throwable {
        Object obj;
        Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), i.a(8), new Object[0]);
        ReflectHelper.importClass(i.a(9), i.a(9));
        File parentFile = file.getParentFile();
        synchronized (d) {
            obj = d.get(str);
            if (obj == null) {
                obj = ReflectHelper.newInstance(i.a(9), file.getAbsolutePath(), parentFile.getAbsolutePath(), parentFile.getAbsolutePath(), invokeInstanceMethod);
                d.put(str, obj);
            }
        }
        ResHelper.deleteFileAndFolder(parentFile);
        final String authorize = DeviceAuthorizer.authorize(null);
        final Object invokeInstanceMethod2 = ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(obj, i.a(10), str2), i.a(11), str3, String.class);
        ReflectHelper.invokeInstanceMethod(invokeInstanceMethod2, i.a(12), Boolean.valueOf(true));
        c.a().a(15);
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                try {
                    c.a().a(16);
                    ReflectHelper.invokeInstanceMethod(invokeInstanceMethod2, i.a(13), 0, new Object[]{authorize});
                    c.a().a(17);
                } catch (Throwable th) {
                    c.a().a(7, th);
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final Handler e() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final void f() {
        try {
            if (this.e != null) {
                this.e.removeCallbacksAndMessages(null);
            }
            if (this.a != null) {
                this.a.quit();
            }
            this.e = null;
            this.a = null;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        b();
        this.b = true;
        c.put(getClass().getSimpleName(), null);
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        if (this.e != null) {
            this.e.removeMessages(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(int i) {
        if (this.e != null) {
            this.e.sendEmptyMessage(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i, long j) {
        if (this.e != null) {
            this.e.sendEmptyMessageDelayed(i, j);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(Message message) {
        if (this.e != null) {
            this.e.sendMessage(message);
        }
    }

    /* access modifiers changed from: protected */
    public File a() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> g() {
        Location location = DeviceHelper.getInstance(MobSDK.getContext()).getLocation(0, 0, true);
        if (location == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("accuracy", Float.valueOf(location.getAccuracy()));
        hashMap.put("latitude", Double.valueOf(location.getLatitude()));
        hashMap.put("longitude", Double.valueOf(location.getLongitude()));
        hashMap.put("ltime", Long.valueOf(location.getTime()));
        hashMap.put("provider", location.getProvider());
        hashMap.put("altitude", Double.valueOf(location.getAltitude()));
        hashMap.put("bearing", Float.valueOf(location.getBearing()));
        hashMap.put("speed", Float.valueOf(location.getSpeed()));
        return hashMap;
    }
}
