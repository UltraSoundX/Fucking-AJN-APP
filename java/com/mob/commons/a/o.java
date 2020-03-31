package com.mob.commons.a;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.FBListener;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: SsClt */
public class o extends d {
    /* access modifiers changed from: private */
    public a a;

    /* compiled from: SsClt */
    private static class a {
        private static HashMap<Long, ArrayList<Float>> c;
        private C0055a a;
        private SensorManager b;

        /* renamed from: com.mob.commons.a.o$a$a reason: collision with other inner class name */
        /* compiled from: SsClt */
        private static class C0055a implements SensorEventListener {
            private HashMap<Long, Integer> a;
            private HashMap<Long, ArrayList<Float>> b;
            private ArrayList<Float> c;

            private C0055a() {
            }

            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSensorChanged(android.hardware.SensorEvent r9) {
                /*
                    r8 = this;
                    r2 = 1
                    if (r9 == 0) goto L_0x0065
                    android.hardware.Sensor r0 = r9.sensor     // Catch:{ Throwable -> 0x006c }
                    int r0 = r0.getType()     // Catch:{ Throwable -> 0x006c }
                    r1 = 19
                    if (r0 != r1) goto L_0x0075
                    java.util.HashMap<java.lang.Long, java.lang.Integer> r0 = r8.a     // Catch:{ Throwable -> 0x006c }
                    if (r0 != 0) goto L_0x0066
                    java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x006c }
                    r0.<init>()     // Catch:{ Throwable -> 0x006c }
                    r8.a = r0     // Catch:{ Throwable -> 0x006c }
                L_0x0018:
                    java.util.HashMap<java.lang.Long, java.lang.Integer> r0 = r8.a     // Catch:{ Throwable -> 0x006c }
                    long r2 = r9.timestamp     // Catch:{ Throwable -> 0x006c }
                    java.lang.Long r1 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x006c }
                    float[] r2 = r9.values     // Catch:{ Throwable -> 0x006c }
                    r3 = 0
                    r2 = r2[r3]     // Catch:{ Throwable -> 0x006c }
                    int r2 = (int) r2     // Catch:{ Throwable -> 0x006c }
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x006c }
                    r0.put(r1, r2)     // Catch:{ Throwable -> 0x006c }
                    com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x006c }
                    java.lang.String r1 = "[%s] %s"
                    r2 = 2
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x006c }
                    r3 = 0
                    java.lang.String r4 = "SsClt"
                    r2[r3] = r4     // Catch:{ Throwable -> 0x006c }
                    r3 = 1
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006c }
                    r4.<init>()     // Catch:{ Throwable -> 0x006c }
                    java.lang.String r5 = "onSensorChanged. timestamp: "
                    java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x006c }
                    long r6 = r9.timestamp     // Catch:{ Throwable -> 0x006c }
                    java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Throwable -> 0x006c }
                    java.lang.String r5 = ", step: "
                    java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x006c }
                    float[] r5 = r9.values     // Catch:{ Throwable -> 0x006c }
                    r6 = 0
                    r5 = r5[r6]     // Catch:{ Throwable -> 0x006c }
                    java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x006c }
                    java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x006c }
                    r2[r3] = r4     // Catch:{ Throwable -> 0x006c }
                    r0.d(r1, r2)     // Catch:{ Throwable -> 0x006c }
                L_0x0065:
                    return
                L_0x0066:
                    java.util.HashMap<java.lang.Long, java.lang.Integer> r0 = r8.a     // Catch:{ Throwable -> 0x006c }
                    r0.clear()     // Catch:{ Throwable -> 0x006c }
                    goto L_0x0018
                L_0x006c:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
                    r1.d(r0)
                    goto L_0x0065
                L_0x0075:
                    if (r0 != r2) goto L_0x0065
                    monitor-enter(r8)     // Catch:{ Throwable -> 0x006c }
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r8.b     // Catch:{ all -> 0x00c7 }
                    if (r0 != 0) goto L_0x00ca
                    java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x00c7 }
                    r0.<init>()     // Catch:{ all -> 0x00c7 }
                    r8.b = r0     // Catch:{ all -> 0x00c7 }
                L_0x0083:
                    java.util.ArrayList<java.lang.Float> r0 = r8.c     // Catch:{ all -> 0x00c7 }
                    if (r0 != 0) goto L_0x00d0
                    java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00c7 }
                    r0.<init>()     // Catch:{ all -> 0x00c7 }
                    r8.c = r0     // Catch:{ all -> 0x00c7 }
                L_0x008e:
                    java.util.ArrayList<java.lang.Float> r0 = r8.c     // Catch:{ all -> 0x00c7 }
                    float[] r1 = r9.values     // Catch:{ all -> 0x00c7 }
                    r2 = 0
                    r1 = r1[r2]     // Catch:{ all -> 0x00c7 }
                    java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x00c7 }
                    r0.add(r1)     // Catch:{ all -> 0x00c7 }
                    java.util.ArrayList<java.lang.Float> r0 = r8.c     // Catch:{ all -> 0x00c7 }
                    float[] r1 = r9.values     // Catch:{ all -> 0x00c7 }
                    r2 = 1
                    r1 = r1[r2]     // Catch:{ all -> 0x00c7 }
                    java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x00c7 }
                    r0.add(r1)     // Catch:{ all -> 0x00c7 }
                    java.util.ArrayList<java.lang.Float> r0 = r8.c     // Catch:{ all -> 0x00c7 }
                    float[] r1 = r9.values     // Catch:{ all -> 0x00c7 }
                    r2 = 2
                    r1 = r1[r2]     // Catch:{ all -> 0x00c7 }
                    java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x00c7 }
                    r0.add(r1)     // Catch:{ all -> 0x00c7 }
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r8.b     // Catch:{ all -> 0x00c7 }
                    long r2 = r9.timestamp     // Catch:{ all -> 0x00c7 }
                    java.lang.Long r1 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00c7 }
                    java.util.ArrayList<java.lang.Float> r2 = r8.c     // Catch:{ all -> 0x00c7 }
                    r0.put(r1, r2)     // Catch:{ all -> 0x00c7 }
                    monitor-exit(r8)     // Catch:{ all -> 0x00c7 }
                    goto L_0x0065
                L_0x00c7:
                    r0 = move-exception
                    monitor-exit(r8)     // Catch:{ all -> 0x00c7 }
                    throw r0     // Catch:{ Throwable -> 0x006c }
                L_0x00ca:
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r8.b     // Catch:{ all -> 0x00c7 }
                    r0.clear()     // Catch:{ all -> 0x00c7 }
                    goto L_0x0083
                L_0x00d0:
                    java.util.ArrayList<java.lang.Float> r0 = r8.c     // Catch:{ all -> 0x00c7 }
                    r0.clear()     // Catch:{ all -> 0x00c7 }
                    goto L_0x008e
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.o.a.C0055a.onSensorChanged(android.hardware.SensorEvent):void");
            }

            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public HashMap<Long, Integer> a() {
                if (this.a == null || this.a.isEmpty()) {
                    return null;
                }
                return this.a;
            }

            /* JADX WARNING: Unknown top exception splitter block from list: {B:10:0x0020=Splitter:B:10:0x0020, B:20:0x0033=Splitter:B:20:0x0033} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> b() {
                /*
                    r2 = this;
                    monitor-enter(r2)
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r2.b     // Catch:{ all -> 0x0030 }
                    if (r0 == 0) goto L_0x0033
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r2.b     // Catch:{ all -> 0x0030 }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0030 }
                    if (r0 != 0) goto L_0x0033
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r0 = r2.b     // Catch:{ Throwable -> 0x0022 }
                    java.lang.Object r0 = r2.a(r0)     // Catch:{ Throwable -> 0x0022 }
                    java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0022 }
                    java.util.HashMap<java.lang.Long, java.util.ArrayList<java.lang.Float>> r1 = r2.b     // Catch:{ Throwable -> 0x0022 }
                    r1.clear()     // Catch:{ Throwable -> 0x0022 }
                    if (r0 != 0) goto L_0x0020
                    java.util.HashMap r0 = com.mob.commons.a.o.a.e()     // Catch:{ Throwable -> 0x0022 }
                L_0x0020:
                    monitor-exit(r2)     // Catch:{ all -> 0x0030 }
                L_0x0021:
                    return r0
                L_0x0022:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0030 }
                    r1.w(r0)     // Catch:{ all -> 0x0030 }
                    java.util.HashMap r0 = com.mob.commons.a.o.a.e()     // Catch:{ all -> 0x0030 }
                    monitor-exit(r2)     // Catch:{ all -> 0x0030 }
                    goto L_0x0021
                L_0x0030:
                    r0 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0030 }
                    throw r0
                L_0x0033:
                    java.util.HashMap r0 = com.mob.commons.a.o.a.e()     // Catch:{ all -> 0x0030 }
                    monitor-exit(r2)     // Catch:{ all -> 0x0030 }
                    goto L_0x0021
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.o.a.C0055a.b():java.util.HashMap");
            }

            private Object a(Object obj) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.close();
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                    Object readObject = objectInputStream.readObject();
                    objectInputStream.close();
                    return readObject;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        public a() {
            try {
                this.b = (SensorManager) DeviceHelper.getInstance(MobSDK.getContext()).getSystemServiceSafe("sensor");
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }

        public boolean a(int i) {
            switch (i) {
                case 1:
                    try {
                        if (!MobSDK.getContext().getPackageManager().hasSystemFeature("android.hardware.sensor.stepcounter") || this.b.getDefaultSensor(19) == null) {
                            return false;
                        }
                        return true;
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                        break;
                    }
                    break;
                case 2:
                    if (!MobSDK.getContext().getPackageManager().hasSystemFeature("android.hardware.sensor.accelerometer") || this.b.getDefaultSensor(1) == null) {
                        return false;
                    }
                    return true;
            }
            return false;
        }

        public void a() {
            try {
                if (this.a != null) {
                    MobLog.getInstance().d("[%s] %s", "SsClt", "Listener unregistered.");
                    this.b.unregisterListener(this.a);
                }
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }

        public void b(int i) {
            Sensor sensor;
            int i2 = 3000000;
            if (i == 1) {
                try {
                    sensor = this.b.getDefaultSensor(19);
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                    return;
                }
            } else if (i == 2) {
                sensor = this.b.getDefaultSensor(1);
            } else {
                sensor = null;
            }
            if (sensor != null) {
                if (this.a == null) {
                    this.a = new C0055a();
                }
                if (VERSION.SDK_INT <= 9) {
                    i2 = 3;
                }
                if (VERSION.SDK_INT >= 19) {
                    this.b.registerListener(this.a, sensor, i2, 3000000);
                } else {
                    this.b.registerListener(this.a, sensor, i2);
                }
                MobLog.getInstance().d("[%s] %s", "SsClt", "Listener registered. type: " + i + ", delay: " + i2 + "us.");
                return;
            }
            MobLog.getInstance().d("[%s] %s", "SsClt", "Listener NOT registered since can not obtain instance. type: " + i);
        }

        public HashMap<Long, Integer> b() {
            if (this.a != null) {
                return this.a.a();
            }
            return null;
        }

        public HashMap<Long, ArrayList<Float>> c() {
            return this.a != null ? this.a.b() : e();
        }

        /* access modifiers changed from: private */
        public static HashMap<Long, ArrayList<Float>> e() {
            MobLog.getInstance().d("Write a initial ac data", new Object[0]);
            if (c == null) {
                c = new HashMap<>();
                ArrayList arrayList = new ArrayList();
                arrayList.add(Float.valueOf(0.0f));
                arrayList.add(Float.valueOf(0.0f));
                arrayList.add(Float.valueOf(9.81f));
                c.put(Long.valueOf(com.mob.commons.a.a()), arrayList);
            }
            return c;
        }
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.ss_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return com.mob.commons.a.M() > 0 || com.mob.commons.a.O() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        j.a().a((FBListener) new FBListener() {
            public void onFBChanged(boolean z, boolean z2, long j) {
                if (z) {
                    MobLog.getInstance().d("[%s] %s", "SsClt", "App become fg, restart SsClt if necessary.");
                    o.this.i();
                    return;
                }
                MobLog.getInstance().d("[%s] %s", "SsClt", "App become bg, stop SsClt later");
                o.this.l();
                if (o.this.a != null) {
                    o.this.a.a();
                    o.this.a = null;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.a == null) {
            this.a = new a();
        }
        if (this.a == null ? false : this.a.a(1)) {
            MobLog.getInstance().d("[%s] %s", "SsClt", "PE supported");
            this.a.b(1);
            b(1);
        } else {
            MobLog.getInstance().d("[%s] %s", "SsClt", "PE NOT supported");
        }
        if (this.a == null ? false : this.a.a(2)) {
            MobLog.getInstance().d("[%s] %s", "SsClt", "AC supported");
            this.a.b(2);
            b(2);
            return;
        }
        MobLog.getInstance().d("[%s] %s", "SsClt", "AC NOT supported");
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 1) {
            if (com.mob.commons.a.M() > 0) {
                j();
            }
        } else if (message.what == 2 && com.mob.commons.a.O() > 0) {
            k();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[SYNTHETIC, Splitter:B:12:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030 A[Catch:{ Throwable -> 0x00a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060 A[Catch:{ Throwable -> 0x00a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00da A[Catch:{ Throwable -> 0x00a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f3 A[Catch:{ Throwable -> 0x00a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void j() {
        /*
            r14 = this;
            com.mob.commons.a.o$a r0 = r14.a     // Catch:{ Throwable -> 0x00a5 }
            java.util.HashMap r4 = r0.b()     // Catch:{ Throwable -> 0x00a5 }
            if (r4 == 0) goto L_0x0170
            java.lang.String r0 = "comm/dbs/.pecd"
            java.util.HashMap r2 = r14.a(r0)     // Catch:{ Throwable -> 0x00a5 }
            r1 = 0
            r3 = 0
            java.lang.String r0 = "time"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00ae }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r1 = "step"
            java.lang.Object r1 = r2.get(r1)     // Catch:{ Throwable -> 0x0187 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x0187 }
            r13 = r1
            r1 = r0
            r0 = r13
        L_0x0023:
            if (r1 != 0) goto L_0x002a
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00a5 }
            r1.<init>()     // Catch:{ Throwable -> 0x00a5 }
        L_0x002a:
            int r3 = r1.size()     // Catch:{ Throwable -> 0x00a5 }
            if (r0 == 0) goto L_0x0036
            int r5 = r0.size()     // Catch:{ Throwable -> 0x00a5 }
            if (r5 == r3) goto L_0x018f
        L_0x0036:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00a5 }
            r1.<init>()     // Catch:{ Throwable -> 0x00a5 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00a5 }
            r0.<init>()     // Catch:{ Throwable -> 0x00a5 }
            r3 = r1
            r1 = r0
        L_0x0042:
            long r6 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Long r0 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x00a5 }
            r3.add(r0)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = "time"
            r2.put(r0, r3)     // Catch:{ Throwable -> 0x00a5 }
            java.util.Set r0 = r4.entrySet()     // Catch:{ Throwable -> 0x00a5 }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ Throwable -> 0x00a5 }
        L_0x005a:
            boolean r0 = r4.hasNext()     // Catch:{ Throwable -> 0x00a5 }
            if (r0 == 0) goto L_0x00b9
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x00a5 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Throwable -> 0x00a5 }
            if (r0 == 0) goto L_0x006f
            java.lang.Object r5 = r0.getValue()     // Catch:{ Throwable -> 0x00a5 }
            r1.add(r5)     // Catch:{ Throwable -> 0x00a5 }
        L_0x006f:
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r8 = "[%s] %s"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x00a5 }
            r10 = 0
            java.lang.String r11 = "SsClt"
            r9[r10] = r11     // Catch:{ Throwable -> 0x00a5 }
            r10 = 1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a5 }
            r11.<init>()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r12 = "PE got. time: "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r11 = r11.append(r6)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r12 = ", data: "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r0 = r11.append(r0)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00a5 }
            r9[r10] = r0     // Catch:{ Throwable -> 0x00a5 }
            r5.d(r8, r9)     // Catch:{ Throwable -> 0x00a5 }
            goto L_0x005a
        L_0x00a5:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
        L_0x00ad:
            return
        L_0x00ae:
            r0 = move-exception
        L_0x00af:
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00a5 }
            r5.d(r0)     // Catch:{ Throwable -> 0x00a5 }
            r0 = r3
            goto L_0x0023
        L_0x00b9:
            java.lang.String r0 = "step"
            r2.put(r0, r1)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = "nextUploadTime"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00a5 }
            r4 = 0
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r1)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Throwable -> 0x00a5 }
            long r0 = r0.longValue()     // Catch:{ Throwable -> 0x00a5 }
            r4 = 0
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x00eb
            long r0 = com.mob.commons.a.N()     // Catch:{ Throwable -> 0x00a5 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r4
            long r0 = r0 + r6
            java.lang.String r4 = "nextUploadTime"
            java.lang.Long r5 = java.lang.Long.valueOf(r0)     // Catch:{ Throwable -> 0x00a5 }
            r2.put(r4, r5)     // Catch:{ Throwable -> 0x00a5 }
        L_0x00eb:
            int r4 = r3.size()     // Catch:{ Throwable -> 0x00a5 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 >= r5) goto L_0x00f7
            int r4 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r4 < 0) goto L_0x018d
        L_0x00f7:
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r5 = "[%s] %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00a5 }
            r9 = 0
            java.lang.String r10 = "SsClt"
            r8[r9] = r10     // Catch:{ Throwable -> 0x00a5 }
            r9 = 1
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a5 }
            r10.<init>()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r11 = "PE push. dataSize: "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ Throwable -> 0x00a5 }
            int r3 = r3.size()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r3 = r10.append(r3)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r10 = ", curTime: "
            java.lang.StringBuilder r3 = r3.append(r10)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r6 = ", cacheTime: "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00a5 }
            r8[r9] = r0     // Catch:{ Throwable -> 0x00a5 }
            r4.d(r5, r8)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = "nextUploadTime"
            r2.remove(r0)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r0 = "PEDOMETER"
            r14.b(r2, r0)     // Catch:{ Throwable -> 0x00a5 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00a5 }
            r0.<init>()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r1 = "nextUploadTime"
            long r2 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x00a5 }
            long r4 = com.mob.commons.a.N()     // Catch:{ Throwable -> 0x00a5 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r6
            long r2 = r2 + r4
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00a5 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x00a5 }
        L_0x015a:
            java.lang.String r1 = "comm/dbs/.pecd"
            r14.a(r0, r1)     // Catch:{ Throwable -> 0x00a5 }
        L_0x015f:
            r0 = 1
            r14.a(r0)     // Catch:{ Throwable -> 0x00a5 }
            r0 = 1
            long r2 = com.mob.commons.a.M()     // Catch:{ Throwable -> 0x00a5 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            r14.a(r0, r2)     // Catch:{ Throwable -> 0x00a5 }
            goto L_0x00ad
        L_0x0170:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r1 = "[%s] %s"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00a5 }
            r3 = 0
            java.lang.String r4 = "SsClt"
            r2[r3] = r4     // Catch:{ Throwable -> 0x00a5 }
            r3 = 1
            java.lang.String r4 = "No PE data"
            r2[r3] = r4     // Catch:{ Throwable -> 0x00a5 }
            r0.d(r1, r2)     // Catch:{ Throwable -> 0x00a5 }
            goto L_0x015f
        L_0x0187:
            r1 = move-exception
            r13 = r1
            r1 = r0
            r0 = r13
            goto L_0x00af
        L_0x018d:
            r0 = r2
            goto L_0x015a
        L_0x018f:
            r3 = r1
            r1 = r0
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.o.j():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[SYNTHETIC, Splitter:B:12:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030 A[Catch:{ Throwable -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060 A[Catch:{ Throwable -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0112 A[Catch:{ Throwable -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x012b A[Catch:{ Throwable -> 0x00dd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void k() {
        /*
            r15 = this;
            com.mob.commons.a.o$a r0 = r15.a     // Catch:{ Throwable -> 0x00dd }
            java.util.HashMap r5 = r0.c()     // Catch:{ Throwable -> 0x00dd }
            if (r5 == 0) goto L_0x01a8
            java.lang.String r0 = "comm/dbs/.accd"
            java.util.HashMap r2 = r15.a(r0)     // Catch:{ Throwable -> 0x00dd }
            r1 = 0
            r3 = 0
            java.lang.String r0 = "time"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00e6 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r1 = "acceleration"
            java.lang.Object r1 = r2.get(r1)     // Catch:{ Throwable -> 0x01bf }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x01bf }
            r14 = r1
            r1 = r0
            r0 = r14
        L_0x0023:
            if (r1 != 0) goto L_0x002a
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00dd }
            r1.<init>()     // Catch:{ Throwable -> 0x00dd }
        L_0x002a:
            int r3 = r1.size()     // Catch:{ Throwable -> 0x00dd }
            if (r0 == 0) goto L_0x0036
            int r4 = r0.size()     // Catch:{ Throwable -> 0x00dd }
            if (r4 == r3) goto L_0x01c7
        L_0x0036:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00dd }
            r1.<init>()     // Catch:{ Throwable -> 0x00dd }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00dd }
            r0.<init>()     // Catch:{ Throwable -> 0x00dd }
            r3 = r0
            r4 = r1
        L_0x0042:
            long r6 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x00dd }
            java.lang.Long r0 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x00dd }
            r4.add(r0)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = "time"
            r2.put(r0, r4)     // Catch:{ Throwable -> 0x00dd }
            java.util.Set r0 = r5.entrySet()     // Catch:{ Throwable -> 0x00dd }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ Throwable -> 0x00dd }
        L_0x005a:
            boolean r0 = r5.hasNext()     // Catch:{ Throwable -> 0x00dd }
            if (r0 == 0) goto L_0x00f1
            java.lang.Object r0 = r5.next()     // Catch:{ Throwable -> 0x00dd }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Throwable -> 0x00dd }
            if (r0 == 0) goto L_0x006f
            java.lang.Object r1 = r0.getValue()     // Catch:{ Throwable -> 0x00dd }
            r3.add(r1)     // Catch:{ Throwable -> 0x00dd }
        L_0x006f:
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r9 = "[%s] %s"
            r1 = 2
            java.lang.Object[] r10 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00dd }
            r1 = 0
            java.lang.String r11 = "SsClt"
            r10[r1] = r11     // Catch:{ Throwable -> 0x00dd }
            r11 = 1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00dd }
            r1.<init>()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r12 = "AC got. time: "
            java.lang.StringBuilder r1 = r1.append(r12)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r12 = ", data: ["
            java.lang.StringBuilder r12 = r1.append(r12)     // Catch:{ Throwable -> 0x00dd }
            java.lang.Object r1 = r0.getValue()     // Catch:{ Throwable -> 0x00dd }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x00dd }
            r13 = 0
            java.lang.Object r1 = r1.get(r13)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r1 = r12.append(r1)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r12 = ","
            java.lang.StringBuilder r12 = r1.append(r12)     // Catch:{ Throwable -> 0x00dd }
            java.lang.Object r1 = r0.getValue()     // Catch:{ Throwable -> 0x00dd }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x00dd }
            r13 = 1
            java.lang.Object r1 = r1.get(r13)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r1 = r12.append(r1)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r12 = ","
            java.lang.StringBuilder r1 = r1.append(r12)     // Catch:{ Throwable -> 0x00dd }
            java.lang.Object r0 = r0.getValue()     // Catch:{ Throwable -> 0x00dd }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x00dd }
            r12 = 2
            java.lang.Object r0 = r0.get(r12)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r1 = "]"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00dd }
            r10[r11] = r0     // Catch:{ Throwable -> 0x00dd }
            r8.d(r9, r10)     // Catch:{ Throwable -> 0x00dd }
            goto L_0x005a
        L_0x00dd:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
        L_0x00e5:
            return
        L_0x00e6:
            r0 = move-exception
        L_0x00e7:
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00dd }
            r4.d(r0)     // Catch:{ Throwable -> 0x00dd }
            r0 = r3
            goto L_0x0023
        L_0x00f1:
            java.lang.String r0 = "acceleration"
            r2.put(r0, r3)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = "nextUploadTime"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00dd }
            r8 = 0
            java.lang.Long r1 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x00dd }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r1)     // Catch:{ Throwable -> 0x00dd }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Throwable -> 0x00dd }
            long r0 = r0.longValue()     // Catch:{ Throwable -> 0x00dd }
            r8 = 0
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x0123
            long r0 = com.mob.commons.a.P()     // Catch:{ Throwable -> 0x00dd }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r8
            long r0 = r0 + r6
            java.lang.String r3 = "nextUploadTime"
            java.lang.Long r5 = java.lang.Long.valueOf(r0)     // Catch:{ Throwable -> 0x00dd }
            r2.put(r3, r5)     // Catch:{ Throwable -> 0x00dd }
        L_0x0123:
            int r3 = r4.size()     // Catch:{ Throwable -> 0x00dd }
            r5 = 200(0xc8, float:2.8E-43)
            if (r3 >= r5) goto L_0x012f
            int r3 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r3 < 0) goto L_0x01c5
        L_0x012f:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r5 = "[%s] %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00dd }
            r9 = 0
            java.lang.String r10 = "SsClt"
            r8[r9] = r10     // Catch:{ Throwable -> 0x00dd }
            r9 = 1
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00dd }
            r10.<init>()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r11 = "AC push. dataSize: "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ Throwable -> 0x00dd }
            int r4 = r4.size()     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r4 = r10.append(r4)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r10 = ", curTime: "
            java.lang.StringBuilder r4 = r4.append(r10)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r6 = ", cacheTime: "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Throwable -> 0x00dd }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00dd }
            r8[r9] = r0     // Catch:{ Throwable -> 0x00dd }
            r3.d(r5, r8)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = "nextUploadTime"
            r2.remove(r0)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r0 = "ACCELEROMETER"
            r15.b(r2, r0)     // Catch:{ Throwable -> 0x00dd }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00dd }
            r0.<init>()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r1 = "nextUploadTime"
            long r2 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x00dd }
            long r4 = com.mob.commons.a.P()     // Catch:{ Throwable -> 0x00dd }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r6
            long r2 = r2 + r4
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00dd }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x00dd }
        L_0x0192:
            java.lang.String r1 = "comm/dbs/.accd"
            r15.a(r0, r1)     // Catch:{ Throwable -> 0x00dd }
        L_0x0197:
            r0 = 2
            r15.a(r0)     // Catch:{ Throwable -> 0x00dd }
            r0 = 2
            long r2 = com.mob.commons.a.O()     // Catch:{ Throwable -> 0x00dd }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            r15.a(r0, r2)     // Catch:{ Throwable -> 0x00dd }
            goto L_0x00e5
        L_0x01a8:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r1 = "[%s] %s"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00dd }
            r3 = 0
            java.lang.String r4 = "SsClt"
            r2[r3] = r4     // Catch:{ Throwable -> 0x00dd }
            r3 = 1
            java.lang.String r4 = "No AC data"
            r2[r3] = r4     // Catch:{ Throwable -> 0x00dd }
            r0.d(r1, r2)     // Catch:{ Throwable -> 0x00dd }
            goto L_0x0197
        L_0x01bf:
            r1 = move-exception
            r14 = r1
            r1 = r0
            r0 = r14
            goto L_0x00e7
        L_0x01c5:
            r0 = r2
            goto L_0x0192
        L_0x01c7:
            r3 = r0
            r4 = r1
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.o.k():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:? A[Catch:{ Throwable -> 0x0030 }, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> a(java.lang.String r6) {
        /*
            r5 = this;
            r1 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0030 }
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r6)     // Catch:{ Throwable -> 0x0030 }
            boolean r2 = r0.exists()     // Catch:{ Throwable -> 0x0030 }
            if (r2 != 0) goto L_0x0015
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0030 }
            r0.<init>()     // Catch:{ Throwable -> 0x0030 }
        L_0x0014:
            return r0
        L_0x0015:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0030 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0030 }
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0030 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0030 }
            java.lang.Object r0 = r3.readObject()     // Catch:{ Throwable -> 0x0030 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0030 }
            r3.close()     // Catch:{ Throwable -> 0x003a }
        L_0x0028:
            if (r0 != 0) goto L_0x0014
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            goto L_0x0014
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
            r0 = r1
            goto L_0x0028
        L_0x003a:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.o.a(java.lang.String):java.util.HashMap");
    }

    private void a(HashMap<String, Object> hashMap, String str) {
        try {
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), str);
            if (hashMap == null || hashMap.isEmpty()) {
                cacheRootFile.delete();
                return;
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile));
            objectOutputStream.writeObject(hashMap);
            objectOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private void b(HashMap<String, Object> hashMap, String str) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put("data", hashMap);
        long a2 = com.mob.commons.a.a();
        hashMap2.put("datetime", Long.valueOf(a2));
        b.a().a(a2, hashMap2);
    }

    /* access modifiers changed from: private */
    public void l() {
        a(1);
        a(2);
    }
}
