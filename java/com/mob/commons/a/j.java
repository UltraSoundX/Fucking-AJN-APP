package com.mob.commons.a;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import com.mob.MobSDK;
import com.mob.commons.FBListener;
import com.mob.commons.a;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ActivityTracker;
import com.mob.tools.utils.ActivityTracker.Tracker;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: FBManager */
public class j {
    private static j a;
    /* access modifiers changed from: private */
    public final HashSet<FBListener> b = new HashSet<>();
    /* access modifiers changed from: private */
    public Handler c = MobHandlerThread.newHandler(new Callback() {
        public boolean handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 0:
                    if (DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground()) {
                        j.this.e = SystemClock.elapsedRealtime();
                        j.this.a(false);
                    } else {
                        j.this.a(0, false);
                    }
                    j.this.b();
                    break;
                case 1:
                    j.this.a(true);
                    break;
                case 2:
                    j.this.a(((Long) message.obj).longValue(), true);
                    break;
                case 3:
                    try {
                        FBListener fBListener = (FBListener) message.obj;
                        if (fBListener != null) {
                            j.this.b.add(fBListener);
                            if (j.this.e <= 0) {
                                z = false;
                            }
                            fBListener.onFBChanged(z, true, 0);
                            break;
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                        break;
                    }
                    break;
            }
            return false;
        }
    });
    /* access modifiers changed from: private */
    public String d = null;
    /* access modifiers changed from: private */
    public long e = 0;

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            jVar = a;
        }
        return jVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.mob.commons.FBListener r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.util.HashSet<com.mob.commons.FBListener> r1 = r3.b
            monitor-enter(r1)
            java.util.HashSet<com.mob.commons.FBListener> r0 = r3.b     // Catch:{ all -> 0x0010 }
            boolean r0 = r0.contains(r4)     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0013
            monitor-exit(r1)     // Catch:{ all -> 0x0010 }
            goto L_0x0002
        L_0x0010:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0010 }
            throw r0
        L_0x0013:
            android.os.Handler r0 = r3.c     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0026
            android.os.Message r0 = new android.os.Message     // Catch:{ all -> 0x0010 }
            r0.<init>()     // Catch:{ all -> 0x0010 }
            r2 = 3
            r0.what = r2     // Catch:{ all -> 0x0010 }
            r0.obj = r4     // Catch:{ all -> 0x0010 }
            android.os.Handler r2 = r3.c     // Catch:{ all -> 0x0010 }
            r2.sendMessage(r0)     // Catch:{ all -> 0x0010 }
        L_0x0026:
            monitor-exit(r1)     // Catch:{ all -> 0x0010 }
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.j.a(com.mob.commons.FBListener):void");
    }

    public void b(FBListener fBListener) {
        if (fBListener != null) {
            synchronized (this.b) {
                this.b.remove(fBListener);
            }
        }
    }

    private j() {
        this.c.sendEmptyMessage(0);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        a.a(0, true);
        d.a((Class<? extends d>[]) new Class[]{i.class, l.class, n.class, p.class, f.class, o.class, m.class, k.class, h.class, g.class, c.class});
        if (z) {
            a(true, false, 0);
        }
    }

    /* access modifiers changed from: private */
    public void a(long j, boolean z) {
        a.a(SystemClock.elapsedRealtime(), true);
        if (z) {
            a(false, false, j);
        }
    }

    private void a(boolean z, boolean z2, long j) {
        synchronized (this.b) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((FBListener) it.next()).onFBChanged(z, z2, j);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        ActivityTracker.getInstance(MobSDK.getContext()).addTracker(new Tracker() {
            public void onCreated(Activity activity, Bundle bundle) {
            }

            public void onStarted(Activity activity) {
            }

            public void onResumed(Activity activity) {
                if (j.this.e == 0) {
                    j.this.e = SystemClock.elapsedRealtime();
                    if (j.this.c != null) {
                        j.this.c.sendEmptyMessage(1);
                    }
                }
                j.this.d = activity.toString();
            }

            public void onPaused(Activity activity) {
            }

            public void onStopped(Activity activity) {
                long j;
                if (j.this.d == null || activity.toString().equals(j.this.d.toString())) {
                    if (j.this.c != null) {
                        if (j.this.e > 0) {
                            j = SystemClock.elapsedRealtime() - j.this.e;
                        } else {
                            j = 0;
                        }
                        Message message = new Message();
                        message.what = 2;
                        message.obj = Long.valueOf(j);
                        j.this.c.sendMessage(message);
                    }
                    j.this.e = 0;
                    j.this.d = null;
                }
            }

            public void onDestroyed(Activity activity) {
                if (j.this.e > 0) {
                    onStopped(activity);
                }
            }

            public void onSaveInstanceState(Activity activity, Bundle bundle) {
            }
        });
    }
}
