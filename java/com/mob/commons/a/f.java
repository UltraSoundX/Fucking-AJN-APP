package com.mob.commons.a;

import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper.ReflectRunnable;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/* compiled from: DClt */
public class f extends d {
    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.lesd_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.F() > 0 || a.H() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(1);
        b(3);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 1) {
            if (a.F() > 0) {
                DeviceHelper.getInstance(MobSDK.getContext()).getBatteryState(new ReflectRunnable<HashMap<String, Object>, Void>() {
                    /* renamed from: a */
                    public Void run(HashMap<String, Object> hashMap) {
                        Message message = new Message();
                        message.obj = hashMap;
                        message.what = 2;
                        f.this.b(message);
                        return null;
                    }
                });
            }
        } else if (message.what == 2) {
            long a = a((HashMap) message.obj);
            if (a == 0) {
                a = a.F() * 1000;
            }
            a(1, a);
        } else if (message.what == 3) {
            long H = a.H();
            if (H > 0) {
                i();
                a(3, H * 1000);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006c A[SYNTHETIC, Splitter:B:35:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077 A[ADDED_TO_REGION, Catch:{ Throwable -> 0x0357 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x02df A[Catch:{ Throwable -> 0x0357 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0304  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long a(java.util.HashMap<java.lang.String, java.lang.Object> r19) {
        /*
            r18 = this;
            r12 = 0
            java.util.HashMap r14 = r18.j()     // Catch:{ Throwable -> 0x0357 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r10 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r15 = 0
            java.lang.String r2 = "time"
            java.lang.Object r2 = r14.get(r2)     // Catch:{ Throwable -> 0x0335 }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r3 = "light"
            java.lang.Object r3 = r14.get(r3)     // Catch:{ Throwable -> 0x0364 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ Throwable -> 0x0364 }
            java.lang.String r4 = "lightMode"
            java.lang.Object r4 = r14.get(r4)     // Catch:{ Throwable -> 0x0372 }
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ Throwable -> 0x0372 }
            java.lang.String r5 = "batteryState"
            java.lang.Object r5 = r14.get(r5)     // Catch:{ Throwable -> 0x0383 }
            java.util.HashMap r5 = (java.util.HashMap) r5     // Catch:{ Throwable -> 0x0383 }
            if (r5 != 0) goto L_0x03df
            java.util.HashMap r11 = new java.util.HashMap     // Catch:{ Throwable -> 0x0393 }
            r11.<init>()     // Catch:{ Throwable -> 0x0393 }
        L_0x0036:
            java.lang.String r5 = "temperature"
            java.lang.Object r5 = r11.get(r5)     // Catch:{ Throwable -> 0x03a0 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Throwable -> 0x03a0 }
            java.lang.String r6 = "voltage"
            java.lang.Object r6 = r11.get(r6)     // Catch:{ Throwable -> 0x03ad }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ Throwable -> 0x03ad }
            java.lang.String r7 = "status"
            java.lang.Object r7 = r11.get(r7)     // Catch:{ Throwable -> 0x03b6 }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ Throwable -> 0x03b6 }
            java.lang.String r8 = "plugged"
            java.lang.Object r8 = r11.get(r8)     // Catch:{ Throwable -> 0x03be }
            java.util.ArrayList r8 = (java.util.ArrayList) r8     // Catch:{ Throwable -> 0x03be }
            java.lang.String r9 = "level"
            java.lang.Object r9 = r11.get(r9)     // Catch:{ Throwable -> 0x03c6 }
            java.util.ArrayList r9 = (java.util.ArrayList) r9     // Catch:{ Throwable -> 0x03c6 }
            r10 = r2
            r2 = r9
            r9 = r3
            r3 = r8
            r8 = r4
            r4 = r7
            r7 = r11
            r16 = r5
            r5 = r6
            r6 = r16
        L_0x006a:
            if (r10 != 0) goto L_0x0071
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r10.<init>()     // Catch:{ Throwable -> 0x0357 }
        L_0x0071:
            int r11 = r10.size()     // Catch:{ Throwable -> 0x0357 }
            if (r9 == 0) goto L_0x00ad
            if (r8 == 0) goto L_0x00ad
            if (r6 == 0) goto L_0x00ad
            if (r5 == 0) goto L_0x00ad
            if (r4 == 0) goto L_0x00ad
            if (r3 == 0) goto L_0x00ad
            if (r2 == 0) goto L_0x00ad
            int r15 = r9.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r8.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r6.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r5.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r4.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r3.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 != r11) goto L_0x00ad
            int r15 = r2.size()     // Catch:{ Throwable -> 0x0357 }
            if (r15 == r11) goto L_0x03d3
        L_0x00ad:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r10.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r9.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r8.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x0357 }
            r7.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r6.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r5.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r4.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r3.<init>()     // Catch:{ Throwable -> 0x0357 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0357 }
            r2.<init>()     // Catch:{ Throwable -> 0x0357 }
            r16 = r2
            r2 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r16
        L_0x00e4:
            android.content.Context r11 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0357 }
            com.mob.tools.utils.DeviceHelper r11 = com.mob.tools.utils.DeviceHelper.getInstance(r11)     // Catch:{ Throwable -> 0x0357 }
            int r15 = r11.getScreenBrightness()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ Throwable -> 0x0357 }
            r9.add(r15)     // Catch:{ Throwable -> 0x0357 }
            int r11 = r11.getScreenBrightnessMode()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x0357 }
            r2.add(r11)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r11 = "light"
            r14.put(r11, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "lightMode"
            r14.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "present"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = 0
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Throwable -> 0x0357 }
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "present"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "technology"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "technology"
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "charge_type"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "charge_type"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "scale"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "scale"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "current_now"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "current_now"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "current_avg"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "current_avg"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "health"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "health"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "capacity"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r9 = "capacity"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r8.put(r9, r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "temperature"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r7.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "voltage"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r6.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "status"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r5.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "plugged"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r4.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "level"
            r0 = r19
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r9 = -1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r9)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0357 }
            int r2 = r2.intValue()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r3.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "temperature"
            r8.put(r2, r7)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "voltage"
            r8.put(r2, r6)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "status"
            r8.put(r2, r5)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "plugged"
            r8.put(r2, r4)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "level"
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "batteryState"
            r14.put(r2, r8)     // Catch:{ Throwable -> 0x0357 }
            long r6 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x0357 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x0357 }
            r10.add(r2)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "time"
            r14.put(r2, r10)     // Catch:{ Throwable -> 0x0357 }
            java.lang.String r2 = "nextUploadTime"
            java.lang.Object r2 = r14.get(r2)     // Catch:{ Throwable -> 0x0357 }
            r4 = 0
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r3)     // Catch:{ Throwable -> 0x0357 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ Throwable -> 0x0357 }
            long r2 = r2.longValue()     // Catch:{ Throwable -> 0x0357 }
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x02f0
            long r2 = com.mob.commons.a.G()     // Catch:{ Throwable -> 0x0357 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            long r2 = r2 + r6
            java.lang.String r4 = "nextUploadTime"
            java.lang.Long r5 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0357 }
            r14.put(r4, r5)     // Catch:{ Throwable -> 0x0357 }
        L_0x02f0:
            r4 = r2
            int r2 = r10.size()     // Catch:{ Throwable -> 0x0357 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 < r3) goto L_0x03d0
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x03d0
            long r12 = r4 - r6
            r2 = r12
        L_0x0300:
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x03cd
            java.lang.String r4 = "nextUploadTime"
            r14.remove(r4)     // Catch:{ Throwable -> 0x0362 }
            java.lang.String r4 = "cacheSize"
            r14.remove(r4)     // Catch:{ Throwable -> 0x0362 }
            java.lang.String r4 = "LIGHT_ELECTRIC_INFO"
            r0 = r18
            r0.a(r14, r4)     // Catch:{ Throwable -> 0x0362 }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x0362 }
            r4.<init>()     // Catch:{ Throwable -> 0x0362 }
            java.lang.String r5 = "nextUploadTime"
            long r6 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x0362 }
            long r8 = com.mob.commons.a.G()     // Catch:{ Throwable -> 0x0362 }
            r10 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r10
            long r6 = r6 + r8
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x0362 }
            r4.put(r5, r6)     // Catch:{ Throwable -> 0x0362 }
        L_0x032f:
            r0 = r18
            r0.b(r4)     // Catch:{ Throwable -> 0x0362 }
        L_0x0334:
            return r2
        L_0x0335:
            r2 = move-exception
            r16 = r9
            r9 = r4
            r4 = r5
            r5 = r10
            r10 = r3
            r3 = r6
            r6 = r7
            r7 = r8
            r8 = r16
        L_0x0341:
            com.mob.tools.log.NLog r11 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0357 }
            r11.d(r2)     // Catch:{ Throwable -> 0x0357 }
            r2 = r15
            r16 = r8
            r8 = r4
            r4 = r7
            r7 = r3
            r3 = r16
            r17 = r6
            r6 = r5
            r5 = r17
            goto L_0x006a
        L_0x0357:
            r2 = move-exception
            r4 = r2
            r2 = r12
        L_0x035a:
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()
            r5.d(r4)
            goto L_0x0334
        L_0x0362:
            r4 = move-exception
            goto L_0x035a
        L_0x0364:
            r3 = move-exception
            r16 = r3
            r3 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r4
            r4 = r5
            r5 = r10
            r10 = r2
            r2 = r16
            goto L_0x0341
        L_0x0372:
            r4 = move-exception
            r16 = r4
            r4 = r5
            r5 = r10
            r10 = r2
            r2 = r16
            r17 = r9
            r9 = r3
            r3 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            goto L_0x0341
        L_0x0383:
            r5 = move-exception
            r16 = r5
            r5 = r10
            r10 = r2
            r2 = r16
            r17 = r7
            r7 = r8
            r8 = r9
            r9 = r3
            r3 = r6
            r6 = r17
            goto L_0x0341
        L_0x0393:
            r6 = move-exception
            r16 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r3
            r3 = r5
            r5 = r10
            r10 = r2
            r2 = r16
            goto L_0x0341
        L_0x03a0:
            r5 = move-exception
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r3
            r3 = r11
            r16 = r10
            r10 = r2
            r2 = r5
            r5 = r16
            goto L_0x0341
        L_0x03ad:
            r6 = move-exception
            r10 = r2
            r2 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r3
            r3 = r11
            goto L_0x0341
        L_0x03b6:
            r7 = move-exception
            r10 = r2
            r2 = r7
            r7 = r8
            r8 = r9
            r9 = r3
            r3 = r11
            goto L_0x0341
        L_0x03be:
            r8 = move-exception
            r10 = r2
            r2 = r8
            r8 = r9
            r9 = r3
            r3 = r11
            goto L_0x0341
        L_0x03c6:
            r9 = move-exception
            r10 = r2
            r2 = r9
            r9 = r3
            r3 = r11
            goto L_0x0341
        L_0x03cd:
            r4 = r14
            goto L_0x032f
        L_0x03d0:
            r2 = r12
            goto L_0x0300
        L_0x03d3:
            r16 = r2
            r2 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r16
            goto L_0x00e4
        L_0x03df:
            r11 = r5
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.f.a(java.util.HashMap):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:127:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0079 A[SYNTHETIC, Splitter:B:27:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0089 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0094 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009f A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00aa A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b5 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c0 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cb A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00d6 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00e1 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ec A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00f7 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0102 A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x010d A[Catch:{ Throwable -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() {
        /*
            r10 = this;
            r2 = 1
            r1 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            android.content.Context r0 = com.mob.MobSDK.getContext()
            com.mob.tools.utils.DeviceHelper r7 = com.mob.tools.utils.DeviceHelper.getInstance(r0)
            java.lang.String r8 = r7.getQemuKernel()
            r6.append(r8)
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x015f }
            java.lang.String r3 = "/system/lib/libc_malloc_debug_qemu.so"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x015f }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x015f }
            java.lang.String r4 = "/sys/qemu_trace"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x015f }
            java.io.File r4 = new java.io.File     // Catch:{ Throwable -> 0x015f }
            java.lang.String r5 = "/system/bin/qemu-props"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x015f }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x015f }
            if (r0 != 0) goto L_0x003d
            boolean r0 = r3.exists()     // Catch:{ Throwable -> 0x015f }
            if (r0 != 0) goto L_0x003d
            boolean r0 = r4.exists()     // Catch:{ Throwable -> 0x015f }
            if (r0 == 0) goto L_0x015c
        L_0x003d:
            r0 = r2
        L_0x003e:
            r3 = r0
        L_0x003f:
            r6.append(r3)
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x016d }
            java.lang.String r4 = "/dev/qemu_pipe"
            r0.<init>(r4)     // Catch:{ Throwable -> 0x016d }
            java.io.File r4 = new java.io.File     // Catch:{ Throwable -> 0x016d }
            java.lang.String r5 = "/dev/socket/qemud"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x016d }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x016d }
            if (r0 != 0) goto L_0x005c
            boolean r0 = r4.exists()     // Catch:{ Throwable -> 0x016d }
            if (r0 == 0) goto L_0x016a
        L_0x005c:
            r0 = r2
        L_0x005d:
            r4 = r0
        L_0x005e:
            r6.append(r4)
            java.lang.String r0 = "com.bluestacks.appmart"
            boolean r5 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x01a5 }
            if (r5 != 0) goto L_0x0076
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x020b }
            java.lang.String r9 = "/data/bluestacks.prop"
            r0.<init>(r9)     // Catch:{ Throwable -> 0x020b }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x020b }
            if (r0 == 0) goto L_0x0178
        L_0x0076:
            r5 = r2
        L_0x0077:
            if (r5 != 0) goto L_0x0086
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x020e }
            java.lang.String r9 = "/mnt/prebundledapps/bluestacks.prop.orig"
            r0.<init>(r9)     // Catch:{ Throwable -> 0x020e }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x017b
        L_0x0086:
            r5 = r2
        L_0x0087:
            if (r5 != 0) goto L_0x0091
            java.lang.String r0 = "com.bluestacks.BstCommandProcessor"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x017e
        L_0x0091:
            r5 = r2
        L_0x0092:
            if (r5 != 0) goto L_0x009c
            java.lang.String r0 = "com.bluestacks.help"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0181
        L_0x009c:
            r5 = r2
        L_0x009d:
            if (r5 != 0) goto L_0x00a7
            java.lang.String r0 = "com.bluestacks.home"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0184
        L_0x00a7:
            r5 = r2
        L_0x00a8:
            if (r5 != 0) goto L_0x00b2
            java.lang.String r0 = "com.bluestacks.s2p"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0187
        L_0x00b2:
            r5 = r2
        L_0x00b3:
            if (r5 != 0) goto L_0x00bd
            java.lang.String r0 = "com.bluestacks.searchapp"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x018a
        L_0x00bd:
            r5 = r2
        L_0x00be:
            if (r5 != 0) goto L_0x00c8
            java.lang.String r0 = "com.bluestacks.accelerometerui"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x018d
        L_0x00c8:
            r5 = r2
        L_0x00c9:
            if (r5 != 0) goto L_0x00d3
            java.lang.String r0 = "com.bluestacks.appfinder"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0190
        L_0x00d3:
            r5 = r2
        L_0x00d4:
            if (r5 != 0) goto L_0x00de
            java.lang.String r0 = "com.bluestacks.appmart"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0193
        L_0x00de:
            r5 = r2
        L_0x00df:
            if (r5 != 0) goto L_0x00e9
            java.lang.String r0 = "com.bluestacks.appsettings"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0196
        L_0x00e9:
            r5 = r2
        L_0x00ea:
            if (r5 != 0) goto L_0x00f4
            java.lang.String r0 = "com.bluestacks.BstCommandProcessor"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0199
        L_0x00f4:
            r5 = r2
        L_0x00f5:
            if (r5 != 0) goto L_0x00ff
            java.lang.String r0 = "com.bluestacks.bstfolder"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x019c
        L_0x00ff:
            r5 = r2
        L_0x0100:
            if (r5 != 0) goto L_0x010a
            java.lang.String r0 = "com.bluestacks.setup"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x019f
        L_0x010a:
            r5 = r2
        L_0x010b:
            if (r5 != 0) goto L_0x0115
            java.lang.String r0 = "com.bluestacks.spotlight"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x01a2
        L_0x0115:
            r5 = r2
        L_0x0116:
            if (r5 != 0) goto L_0x0120
            java.lang.String r0 = "com.androVM.vmconfig"
            boolean r0 = r7.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x020e }
            if (r0 == 0) goto L_0x0121
        L_0x0120:
            r1 = r2
        L_0x0121:
            r6.append(r1)
            java.util.ArrayList r2 = r7.getTTYDriversInfo()
            if (r2 == 0) goto L_0x01af
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x01af
            java.util.Iterator r5 = r2.iterator()
        L_0x0134:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x01af
            java.lang.Object r0 = r5.next()
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            if (r0 == 0) goto L_0x0134
            boolean r9 = r0.isEmpty()
            if (r9 != 0) goto L_0x0134
            java.util.Iterator r9 = r0.iterator()
        L_0x014c:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x0134
            java.lang.Object r0 = r9.next()
            java.lang.String r0 = (java.lang.String) r0
            r6.append(r0)
            goto L_0x014c
        L_0x015c:
            r0 = r1
            goto L_0x003e
        L_0x015f:
            r0 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            r3.d(r0)
            r3 = r1
            goto L_0x003f
        L_0x016a:
            r0 = r1
            goto L_0x005d
        L_0x016d:
            r0 = move-exception
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            r4.d(r0)
            r4 = r1
            goto L_0x005e
        L_0x0178:
            r5 = r1
            goto L_0x0077
        L_0x017b:
            r5 = r1
            goto L_0x0087
        L_0x017e:
            r5 = r1
            goto L_0x0092
        L_0x0181:
            r5 = r1
            goto L_0x009d
        L_0x0184:
            r5 = r1
            goto L_0x00a8
        L_0x0187:
            r5 = r1
            goto L_0x00b3
        L_0x018a:
            r5 = r1
            goto L_0x00be
        L_0x018d:
            r5 = r1
            goto L_0x00c9
        L_0x0190:
            r5 = r1
            goto L_0x00d4
        L_0x0193:
            r5 = r1
            goto L_0x00df
        L_0x0196:
            r5 = r1
            goto L_0x00ea
        L_0x0199:
            r5 = r1
            goto L_0x00f5
        L_0x019c:
            r5 = r1
            goto L_0x0100
        L_0x019f:
            r5 = r1
            goto L_0x010b
        L_0x01a2:
            r5 = r1
            goto L_0x0116
        L_0x01a5:
            r0 = move-exception
        L_0x01a6:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
            goto L_0x0121
        L_0x01af:
            java.util.HashMap r0 = r7.getCPUInfo()
            com.mob.tools.utils.Hashon r5 = new com.mob.tools.utils.Hashon
            r5.<init>()
            java.lang.String r5 = r5.fromHashMap(r0)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.String r5 = com.mob.tools.utils.Data.MD5(r5)
            java.lang.String r6 = com.mob.commons.g.m()
            if (r5 == 0) goto L_0x01d3
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x020a
        L_0x01d3:
            com.mob.commons.g.h(r5)
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            java.lang.String r6 = "qemuKernel"
            r5.put(r6, r8)
            java.lang.String r6 = "qemuFileExist"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r5.put(r6, r3)
            java.lang.String r3 = "qemuDevExist"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r5.put(r3, r4)
            java.lang.String r3 = "blueStacksFileExist"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r5.put(r3, r1)
            java.lang.String r1 = "ttyDrivers"
            r5.put(r1, r2)
            java.lang.String r1 = "cpuInfo"
            r5.put(r1, r0)
            java.lang.String r0 = "SIMULATOR_DET_INFO"
            r10.a(r5, r0)
        L_0x020a:
            return
        L_0x020b:
            r0 = move-exception
            r1 = r5
            goto L_0x01a6
        L_0x020e:
            r0 = move-exception
            r1 = r5
            goto L_0x01a6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.f.i():void");
    }

    private void a(HashMap<String, Object> hashMap, String str) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put("data", hashMap);
        long a = a.a();
        hashMap2.put("datetime", Long.valueOf(a));
        b.a().a(a, hashMap2);
    }

    private void b(HashMap<String, Object> hashMap) {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.lecd");
        if (cacheRootFile == null || (hashMap != null && !hashMap.isEmpty())) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile));
                objectOutputStream.writeObject(hashMap);
                objectOutputStream.close();
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        } else {
            cacheRootFile.delete();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> j() {
        /*
            r4 = this;
            r2 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()
            java.lang.String r1 = "comm/dbs/.lecd"
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r1)
            if (r0 == 0) goto L_0x0019
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0019
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
        L_0x0018:
            return r0
        L_0x0019:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0034, all -> 0x0042 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0034, all -> 0x0042 }
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0034, all -> 0x0042 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0034, all -> 0x0042 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ Throwable -> 0x004a }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x004a }
            r4.a(r1)
        L_0x002c:
            if (r0 != 0) goto L_0x0018
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            goto L_0x0018
        L_0x0034:
            r0 = move-exception
            r1 = r2
        L_0x0036:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0048 }
            r3.d(r0)     // Catch:{ all -> 0x0048 }
            r4.a(r1)
            r0 = r2
            goto L_0x002c
        L_0x0042:
            r0 = move-exception
            r1 = r2
        L_0x0044:
            r4.a(r1)
            throw r0
        L_0x0048:
            r0 = move-exception
            goto L_0x0044
        L_0x004a:
            r0 = move-exception
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.f.j():java.util.HashMap");
    }
}
