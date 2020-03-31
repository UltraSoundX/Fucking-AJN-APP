package com.mob.commons.a;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: DcClt */
public class h extends d {
    /* access modifiers changed from: private */
    public final float[] a = new float[3];
    /* access modifiers changed from: private */
    public final float[] b = new float[3];
    /* access modifiers changed from: private */
    public final float[] c = new float[3];
    /* access modifiers changed from: private */
    public final float[] d = new float[3];
    /* access modifiers changed from: private */
    public final float[] e = new float[9];
    /* access modifiers changed from: private */
    public final float[] f = new float[9];
    /* access modifiers changed from: private */
    public final float[] g = new float[3];
    /* access modifiers changed from: private */
    public List<HashMap<String, Object>> h = new ArrayList();
    /* access modifiers changed from: private */
    public SensorManager i;
    private Sensor j;
    private Sensor k;
    private Sensor l;
    private Sensor m;
    /* access modifiers changed from: private */
    public int n = 0;
    /* access modifiers changed from: private */
    public int o = 0;
    private long p = 0;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public SensorEventListener f425q = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                if (h.this.n == 5) {
                    h.this.i.unregisterListener(h.this.f425q);
                    return;
                }
                int type = sensorEvent.sensor.getType();
                if (type == 1) {
                    System.arraycopy(sensorEvent.values, 0, h.this.a, 0, h.this.a.length);
                } else if (type == 2) {
                    System.arraycopy(sensorEvent.values, 0, h.this.b, 0, h.this.b.length);
                } else if (type == 9) {
                    System.arraycopy(sensorEvent.values, 0, h.this.c, 0, h.this.c.length);
                    return;
                } else if (type == 4) {
                    System.arraycopy(sensorEvent.values, 0, h.this.d, 0, h.this.d.length);
                    return;
                }
                SensorManager.getRotationMatrix(h.this.e, h.this.f, h.this.a, h.this.b);
                SensorManager.getOrientation(h.this.e, h.this.g);
                float inclination = SensorManager.getInclination(h.this.f);
                if (h.this.o = h.this.o + 1 > 8) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("gm", h.this.b);
                    hashMap.put("ac", h.this.a);
                    hashMap.put("rm", h.this.e);
                    hashMap.put("gs", h.this.c);
                    hashMap.put("gyro", h.this.d);
                    hashMap.put("incl", Float.valueOf(inclination));
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("azimuth", Float.valueOf(h.this.g[0]));
                    hashMap2.put("pitch", Float.valueOf(h.this.g[1]));
                    hashMap2.put("roll", Float.valueOf(h.this.g[2]));
                    hashMap.put("orientation", hashMap2);
                    float[] fArr = (float[]) h.this.b.clone();
                    float[] fArr2 = (float[]) h.this.e.clone();
                    hashMap.put("computeDC", new float[]{(fArr2[0] * fArr[0]) + (fArr2[1] * fArr[1]) + (fArr2[2] * fArr[2]), (fArr2[3] * fArr[0]) + (fArr2[4] * fArr[1]) + (fArr2[5] * fArr[2]), (fArr[2] * fArr2[8]) + (fArr2[6] * fArr[0]) + (fArr2[7] * fArr[1])});
                    hashMap.put("timestamp", Long.valueOf(sensorEvent.timestamp));
                    h.this.h.add(hashMap);
                    h.this.n = h.this.n + 1;
                    if (h.this.n == 5) {
                        h.this.i.unregisterListener(h.this.f425q);
                        h.this.k();
                        return;
                    }
                    Thread.sleep(2000);
                }
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.gm_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.U();
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(1);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 1) {
            long V = a.V() * 1000;
            if (this.p == 0 || SystemClock.elapsedRealtime() - this.p > V) {
                j();
                this.p = SystemClock.elapsedRealtime();
                a(1, V);
            }
        }
    }

    private void i() {
        this.i = (SensorManager) MobSDK.getContext().getSystemService("sensor");
        this.j = this.i.getDefaultSensor(2);
        this.k = this.i.getDefaultSensor(1);
        this.l = this.i.getDefaultSensor(9);
        this.m = this.i.getDefaultSensor(4);
    }

    private void j() {
        if (this.i == null) {
            i();
        }
        this.n = 0;
        this.o = 0;
        this.i.registerListener(this.f425q, this.j, 3, e());
        this.i.registerListener(this.f425q, this.k, 3, e());
        this.i.registerListener(this.f425q, this.l, 3, e());
        this.i.registerListener(this.f425q, this.m, 3, e());
    }

    /* access modifiers changed from: private */
    public void k() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.h);
        hashMap.put("gms", arrayList);
        this.h.clear();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        HashMap currentWifiInfo = instance.getCurrentWifiInfo();
        if (currentWifiInfo != null) {
            hashMap.put("wi", currentWifiInfo);
        }
        try {
            HashMap a2 = a(instance);
            if (a2 != null) {
                hashMap.put("bl", a2);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", "GMINFO");
        hashMap2.put(Config.CELL_LOCATION, g());
        hashMap2.put("data", hashMap);
        hashMap2.put("datetime", Long.valueOf(a.a()));
        b.a().a(a.a(), hashMap2);
    }

    private HashMap<String, Object> a(DeviceHelper deviceHelper) throws Throwable {
        int i2;
        try {
            i2 = Integer.parseInt(deviceHelper.getCarrier());
        } catch (Throwable th) {
            i2 = -1;
        }
        int cellLac = deviceHelper.getCellLac();
        int cellId = deviceHelper.getCellId();
        int psc = deviceHelper.getPsc();
        HashMap<String, Object> hashMap = null;
        if (!(i2 == -1 || cellLac == -1 || cellId == -1)) {
            hashMap = new HashMap<>();
            hashMap.put("lac", Integer.valueOf(cellLac));
            hashMap.put("cell", Integer.valueOf(cellId));
            if (psc != -1) {
                hashMap.put("psc", Integer.valueOf(psc));
            }
        }
        int cdmaBid = deviceHelper.getCdmaBid();
        int cdmaSid = deviceHelper.getCdmaSid();
        int cdmaNid = deviceHelper.getCdmaNid();
        int cdmaLat = deviceHelper.getCdmaLat();
        int cdmaLon = deviceHelper.getCdmaLon();
        if (!(i2 == -1 || cdmaBid == -1 || cdmaSid == -1 || cdmaNid == -1)) {
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            hashMap.put("bid", Integer.valueOf(cdmaBid));
            hashMap.put("sid", Integer.valueOf(cdmaSid));
            hashMap.put("nid", Integer.valueOf(cdmaNid));
            if (cdmaLat != -1) {
                hashMap.put("lat", Integer.valueOf(cdmaLat));
            }
            if (cdmaLon != -1) {
                hashMap.put("lon", Integer.valueOf(cdmaLon));
            }
        }
        if (hashMap != null) {
            hashMap.put("carrier", Integer.valueOf(i2));
            hashMap.put("simopname", deviceHelper.getCarrierName());
            ArrayList neighboringCellInfo = deviceHelper.getNeighboringCellInfo();
            if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
                hashMap.put("nearby", neighboringCellInfo);
            }
        }
        return hashMap;
    }
}
