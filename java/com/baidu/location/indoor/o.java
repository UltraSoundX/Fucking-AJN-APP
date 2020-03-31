package com.baidu.location.indoor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.baidu.location.indoor.mapversion.a;

class o implements SensorEventListener {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                float[] fArr = (float[]) sensorEvent.values.clone();
                this.a.f385q = (float[]) fArr.clone();
                if (this.a.k && a.b()) {
                    a.a(1, fArr, sensorEvent.timestamp);
                }
                float[] a2 = this.a.a(fArr[0], fArr[1], fArr[2]);
                if (n.b(this.a) >= 20) {
                    double d = (double) ((a2[2] * a2[2]) + (a2[0] * a2[0]) + (a2[1] * a2[1]));
                    if (this.a.n == 0) {
                        if (d > 4.0d) {
                            this.a.n = 1;
                            return;
                        }
                        return;
                    } else if (d < 0.009999999776482582d) {
                        this.a.n = 0;
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            case 3:
                float[] fArr2 = (float[]) sensorEvent.values.clone();
                if (this.a.k && a.b()) {
                    a.a(5, fArr2, sensorEvent.timestamp);
                }
                this.a.P[this.a.O] = (double) fArr2[0];
                this.a.O = this.a.O + 1;
                if (this.a.O == this.a.N) {
                    this.a.O = 0;
                }
                if (n.h(this.a) >= 20) {
                    this.a.Q = this.a.i();
                    if (!this.a.Q) {
                        this.a.d.unregisterListener(this.a.b, this.a.h);
                    }
                    this.a.r[0] = this.a.a(this.a.r[0], (double) fArr2[0], 0.7d);
                    this.a.r[1] = (double) fArr2[1];
                    this.a.r[2] = (double) fArr2[2];
                    return;
                }
                return;
            case 4:
                float[] fArr3 = (float[]) sensorEvent.values.clone();
                if (this.a.k && a.b()) {
                    a.a(4, fArr3, sensorEvent.timestamp);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
