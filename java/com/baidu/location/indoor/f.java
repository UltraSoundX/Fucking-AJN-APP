package com.baidu.location.indoor;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

class f extends ScanCallback {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public void onScanResult(int i, ScanResult scanResult) {
        if (this.a.l != null) {
            this.a.l.put(scanResult.getDevice().getAddress(), scanResult);
        }
    }
}
