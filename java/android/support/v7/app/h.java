package android.support.v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import java.util.Calendar;

/* compiled from: TwilightManager */
class h {
    private static h a;
    private final Context b;
    private final LocationManager c;
    private final a d = new a();

    /* compiled from: TwilightManager */
    private static class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        a() {
        }
    }

    static h a(Context context) {
        if (a == null) {
            Context applicationContext = context.getApplicationContext();
            a = new h(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return a;
    }

    h(Context context, LocationManager locationManager) {
        this.b = context;
        this.c = locationManager;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        a aVar = this.d;
        if (c()) {
            return aVar.a;
        }
        Location b2 = b();
        if (b2 != null) {
            a(b2);
            return aVar.a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location location;
        Location location2 = null;
        if (PermissionChecker.checkSelfPermission(this.b, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            location = a("network");
        } else {
            location = null;
        }
        if (PermissionChecker.checkSelfPermission(this.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location2 = a("gps");
        }
        if (location2 == null || location == null) {
            if (location2 == null) {
                location2 = location;
            }
            return location2;
        } else if (location2.getTime() > location.getTime()) {
            return location2;
        } else {
            return location;
        }
    }

    private Location a(String str) {
        try {
            if (this.c.isProviderEnabled(str)) {
                return this.c.getLastKnownLocation(str);
            }
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
        }
        return null;
    }

    private boolean c() {
        return this.d.f > System.currentTimeMillis();
    }

    private void a(Location location) {
        long j;
        long j2;
        a aVar = this.d;
        long currentTimeMillis = System.currentTimeMillis();
        g a2 = g.a();
        a2.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j3 = a2.a;
        a2.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = a2.c == 1;
        long j4 = a2.b;
        long j5 = a2.a;
        a2.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j6 = a2.b;
        if (j4 == -1 || j5 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            if (currentTimeMillis > j5) {
                j2 = 0 + j6;
            } else if (currentTimeMillis > j4) {
                j2 = 0 + j5;
            } else {
                j2 = 0 + j4;
            }
            j = j2 + 60000;
        }
        aVar.a = z;
        aVar.b = j3;
        aVar.c = j4;
        aVar.d = j5;
        aVar.e = j6;
        aVar.f = j;
    }
}
