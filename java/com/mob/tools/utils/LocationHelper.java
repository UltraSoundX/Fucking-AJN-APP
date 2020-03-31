package com.mob.tools.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;

public class LocationHelper {
    /* access modifiers changed from: private */
    public DeviceHelper deviceHelper;
    /* access modifiers changed from: private */
    public boolean gpsRequesting;
    private int gpsTimeoutSec;
    /* access modifiers changed from: private */
    public Handler handler = MobHandlerThread.newHandler("h", newCallback());
    /* access modifiers changed from: private */
    public LocationListener listener;
    /* access modifiers changed from: private */
    public LocationManager lm;
    /* access modifiers changed from: private */
    public boolean networkRequesting;
    private int networkTimeoutSec;
    /* access modifiers changed from: private */
    public Location res;

    public LocationHelper() {
        newLocationListener();
    }

    private Callback newCallback() {
        return new Callback() {
            public boolean handleMessage(Message message) {
                try {
                    if (!LocationHelper.this.deviceHelper.checkPermission("android.permission.ACCESS_FINE_LOCATION")) {
                        LocationHelper.this.quit();
                    } else if (message.what == 0) {
                        LocationHelper.this.onRequest();
                    } else if (LocationHelper.this.gpsRequesting) {
                        LocationHelper.this.onGPSTimeout();
                    } else if (LocationHelper.this.networkRequesting) {
                        if (LocationHelper.this.lm != null) {
                            LocationHelper.this.lm.removeUpdates(LocationHelper.this.listener);
                        }
                        LocationHelper.this.quit();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().d(th);
                    LocationHelper.this.quit();
                }
                return false;
            }
        };
    }

    /* access modifiers changed from: private */
    public void quit() {
        try {
            synchronized (this) {
                notifyAll();
            }
            this.handler.getLooper().quit();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private void newLocationListener() {
        this.listener = new LocationListener() {
            public void onLocationChanged(Location location) {
                try {
                    synchronized (LocationHelper.this) {
                        try {
                            LocationHelper.this.lm.removeUpdates(this);
                        } catch (Throwable th) {
                            MobLog.getInstance().d(th);
                        }
                        LocationHelper.this.res = location;
                        LocationHelper.this.notifyAll();
                    }
                    LocationHelper.this.handler.getLooper().quit();
                } catch (Throwable th2) {
                    MobLog.getInstance().d(th2);
                }
            }

            public void onStatusChanged(String str, int i, Bundle bundle) {
            }

            public void onProviderEnabled(String str) {
            }

            public void onProviderDisabled(String str) {
            }
        };
    }

    public Location getLocation(Context context) throws Throwable {
        return getLocation(context, 0);
    }

    public Location getLocation(Context context, int i) throws Throwable {
        return getLocation(context, i, 0);
    }

    public Location getLocation(Context context, int i, int i2) throws Throwable {
        return getLocation(context, i, i2, true);
    }

    public Location getLocation(Context context, int i, int i2, boolean z) throws Throwable {
        this.deviceHelper = DeviceHelper.getInstance(context);
        this.gpsTimeoutSec = i;
        this.networkTimeoutSec = i2;
        this.lm = (LocationManager) this.deviceHelper.getSystemServiceSafe("location");
        if (this.lm == null) {
            return null;
        }
        synchronized (this) {
            this.handler.sendEmptyMessageDelayed(0, 50);
            wait();
        }
        if (this.res == null && z) {
            this.res = this.lm.getLastKnownLocation("gps");
            if (this.res == null) {
                this.res = this.lm.getLastKnownLocation("network");
            }
        }
        return this.res;
    }

    /* access modifiers changed from: private */
    public void onRequest() {
        boolean z = false;
        boolean z2 = this.gpsTimeoutSec != 0;
        if (this.networkTimeoutSec != 0) {
            z = true;
        }
        if (this.lm != null) {
            if (z2 && this.lm.isProviderEnabled("gps")) {
                this.gpsRequesting = true;
                try {
                    this.lm.requestLocationUpdates("gps", 1000, 0.0f, this.listener);
                    if (this.gpsTimeoutSec > 0) {
                        this.handler.sendEmptyMessageDelayed(1, (long) (this.gpsTimeoutSec * 1000));
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                    this.handler.sendEmptyMessage(1);
                    return;
                }
            } else if (z && this.lm.isProviderEnabled("network")) {
                this.networkRequesting = true;
                try {
                    this.lm.requestLocationUpdates("network", 1000, 0.0f, this.listener);
                    if (this.networkTimeoutSec > 0) {
                        this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * 1000));
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    MobLog.getInstance().w(th2);
                    this.handler.sendEmptyMessage(1);
                    return;
                }
            }
        }
        quit();
    }

    /* access modifiers changed from: private */
    public void onGPSTimeout() {
        boolean z = false;
        this.lm.removeUpdates(this.listener);
        this.gpsRequesting = false;
        if (this.networkTimeoutSec != 0) {
            z = true;
        }
        if (!z || !this.lm.isProviderEnabled("network")) {
            quit();
            return;
        }
        this.networkRequesting = true;
        this.lm.requestLocationUpdates("network", 1000, 0.0f, this.listener);
        if (this.networkTimeoutSec > 0) {
            this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * 1000));
        }
    }
}
