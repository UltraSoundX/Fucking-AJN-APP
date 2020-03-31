package com.baidu.location;

import android.util.Log;
import com.baidu.location.c.a;

public abstract class d {
    public double a = Double.MIN_VALUE;
    public double b = Double.MIN_VALUE;
    public float c = 0.0f;
    public float d = 0.0f;
    public String e = null;
    public double f = Double.MIN_VALUE;
    public double g = Double.MIN_VALUE;
    public int h = 0;
    public boolean i = false;
    public a j = null;

    public void a(BDLocation bDLocation, float f2) {
        Log.d("baidu_location_service", "new location, not far from the destination..." + f2);
    }
}
