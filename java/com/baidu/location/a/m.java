package com.baidu.location.a;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class m implements SensorEventListener {
    private static Object a = new Object();
    private static m b = null;
    private float[] c;
    private int d = 0;
    private List<Float> e = new ArrayList();
    private List<Float> f = new ArrayList();
    private boolean g = false;
    private boolean h = false;
    private SensorManager i;

    public m() {
        try {
            if (this.i == null) {
                this.i = (SensorManager) f.c().getSystemService("sensor");
            }
            if (this.i.getDefaultSensor(6) != null) {
                this.h = true;
            }
        } catch (Exception e2) {
            this.h = false;
        }
    }

    public static m a() {
        m mVar;
        synchronized (a) {
            if (b == null) {
                b = new m();
            }
            mVar = b;
        }
        return mVar;
    }

    public void b() {
        if (this.h && !this.g) {
            try {
                this.d = 0;
                this.e.clear();
                this.f.clear();
                if (this.i == null) {
                    this.i = (SensorManager) f.c().getSystemService("sensor");
                }
                if (this.i != null) {
                    Sensor defaultSensor = this.i.getDefaultSensor(6);
                    if (defaultSensor != null) {
                        this.i.registerListener(this, defaultSensor, 2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.g = true;
        }
    }

    public void c() {
        if (this.g) {
            try {
                if (this.i != null) {
                    this.i.unregisterListener(this);
                    this.i = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.g = false;
        }
    }

    public float d() {
        float f2;
        synchronized (this.f) {
            if (Math.abs(((int) (System.currentTimeMillis() / 1000)) - this.d) > 5 || this.f.size() <= 0) {
                f2 = 0.0f;
            } else {
                try {
                    f2 = ((Float) this.f.get(this.f.size() - 1)).floatValue();
                } catch (Throwable th) {
                    f2 = 0.0f;
                }
            }
        }
        return f2;
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @SuppressLint({"NewApi"})
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 6:
                if (this.g) {
                    this.c = (float[]) sensorEvent.values.clone();
                    int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                    if (currentTimeMillis != this.d) {
                        this.d = currentTimeMillis;
                        if (this.e.size() > 0) {
                            int size = this.e.size();
                            float f2 = 0.0f;
                            Iterator it = this.e.iterator();
                            while (true) {
                                float f3 = f2;
                                if (it.hasNext()) {
                                    f2 = ((Float) it.next()).floatValue() + f3;
                                } else {
                                    float f4 = f3 / ((float) size);
                                    synchronized (this.f) {
                                        try {
                                            this.f.add(Float.valueOf(f4));
                                            if (this.f.size() >= 4) {
                                                this.f.remove(0);
                                            }
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                            this.f.clear();
                                        }
                                    }
                                    this.e.clear();
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        this.e.add(Float.valueOf(this.c[0]));
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
