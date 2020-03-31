package com.ccb.crypto.tp.tool;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.text.DecimalFormat;

class b implements LocationListener {
    final /* synthetic */ d a;

    b(d dVar) {
        this.a = dVar;
    }

    public void onLocationChanged(Location location) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000");
        String format = decimalFormat.format(location.getLatitude());
        d.h = format + "#" + decimalFormat.format(location.getLongitude());
        d.e.removeUpdates(this);
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        switch (i) {
        }
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }
}
