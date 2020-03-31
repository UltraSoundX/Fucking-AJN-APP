package com.tencent.bigdata.customdataacquisition.a;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import com.tencent.mid.core.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {

    private static class a {
        /* access modifiers changed from: private */
        public int a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public double f;
        /* access modifiers changed from: private */
        public double g;
        /* access modifiers changed from: private */
        public int h;

        private a() {
        }

        public JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("cid", this.a);
                jSONObject.put("mcc", this.b);
                jSONObject.put("lac", this.d);
                jSONObject.put("mnc", this.c);
                jSONObject.put("type", this.e);
                jSONObject.put("nt", this.h);
                if (this.f != 0.0d) {
                    jSONObject.put("lat", this.f);
                }
                if (this.g == 0.0d) {
                    return jSONObject;
                }
                jSONObject.put("lng", this.g);
                return jSONObject;
            } catch (JSONException e2) {
                com.tencent.bigdata.customdataacquisition.b.a.d("CellIDInfo toJSONObject error:" + e2.toString());
                return null;
            }
        }

        public String toString() {
            try {
                return String.format("{\"cellId\":%d,\"mcc\":%d,\"lac\":%d,\"mnc\":%d,\"type\":%s,\"lat\":%f,\"lng\":%f}", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.d), Integer.valueOf(this.c), this.e, Double.valueOf(this.f), Double.valueOf(this.g)});
            } catch (Exception e2) {
                return "";
            }
        }
    }

    /* renamed from: com.tencent.bigdata.customdataacquisition.a.b$b reason: collision with other inner class name */
    private static class C0073b implements Comparable<C0073b> {
        public final String a;
        public final int b;
        public final String c;

        public C0073b(ScanResult scanResult) {
            this.a = scanResult.BSSID;
            this.b = scanResult.level;
            this.c = scanResult.SSID;
        }

        public C0073b(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }

        /* renamed from: a */
        public int compareTo(C0073b bVar) {
            return bVar.b - this.b;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("bs", this.a);
                jSONObject.put("dBm", this.b);
                jSONObject.put("ss", this.c);
            } catch (Exception e) {
                com.tencent.bigdata.customdataacquisition.b.a.a("", (Throwable) e);
            }
            return jSONObject;
        }
    }

    public static JSONArray a(Context context) {
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            com.tencent.bigdata.customdataacquisition.b.a.b("getCellInfo Require the permissionandroid.permission.ACCESS_COARSE_LOCATION");
            return null;
        }
        com.tencent.bigdata.customdataacquisition.b.a.b("getCellInfo begin");
        JSONArray jSONArray = new JSONArray();
        try {
            ArrayList c = c(context);
            if (c != null) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((a) it.next()).a());
                }
            }
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.a("getCellInfo", (Throwable) e);
        }
        return jSONArray;
    }

    private static JSONObject a(Location location) {
        if (location == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("lat", location.getLatitude());
            jSONObject.put("lng", location.getLongitude());
            jSONObject.put("alt", location.getAltitude());
            jSONObject.put("bear", (double) location.getBearing());
            jSONObject.put("acc", (double) location.getAccuracy());
            jSONObject.put("time", location.getTime());
            jSONObject.put("sp", (double) location.getSpeed());
            jSONObject.put("pvd", location.getProvider());
            return jSONObject;
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.d("[loactionToJson]" + e.getMessage());
            return null;
        }
    }

    private static boolean a(LocationManager locationManager) {
        return locationManager.isProviderEnabled("gps");
    }

    public static JSONObject b(Context context) {
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            String str = "network";
            if (locationManager != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(str);
                if (lastKnownLocation != null) {
                    return a(lastKnownLocation);
                }
            }
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.d("getLastLocation:" + e.toString());
        }
        return null;
    }

    public static ArrayList<a> c(Context context) {
        int i = 0;
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            com.tencent.bigdata.customdataacquisition.b.a.a((Object) "getCellIDInfo: get TelephonyManager return null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        a aVar = new a();
        int networkType = telephonyManager.getNetworkType();
        int phoneType = telephonyManager.getPhoneType();
        com.tencent.bigdata.customdataacquisition.b.a.b("getCellIDInfo--> \t\tphoneType = " + phoneType);
        if (phoneType == 2) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
            if (cdmaCellLocation == null) {
                com.tencent.bigdata.customdataacquisition.b.a.d("CdmaCellLocation is null!!!");
                return null;
            }
            int networkId = cdmaCellLocation.getNetworkId();
            String substring = telephonyManager.getNetworkOperator().substring(0, 3);
            String valueOf = String.valueOf(cdmaCellLocation.getSystemId());
            aVar.a = cdmaCellLocation.getBaseStationId();
            aVar.b = Integer.valueOf(substring).intValue();
            aVar.c = Integer.valueOf(valueOf).intValue();
            aVar.d = networkId;
            aVar.e = "cdma";
            aVar.h = networkType;
            arrayList.add(aVar);
            List allCellInfo = telephonyManager.getAllCellInfo();
            int size = allCellInfo.size();
            while (i < size && i < 3) {
                CellInfo cellInfo = (CellInfo) allCellInfo.get(i);
                if (cellInfo instanceof CellInfoCdma) {
                    CellIdentityCdma cellIdentity = ((CellInfoCdma) cellInfo).getCellIdentity();
                    a aVar2 = new a();
                    aVar2.a = cellIdentity.getBasestationId();
                    aVar2.b = Integer.valueOf(substring).intValue();
                    aVar2.c = Integer.valueOf(valueOf).intValue();
                    aVar2.d = networkId;
                    aVar2.f = (double) cellIdentity.getLatitude();
                    aVar2.g = (double) cellIdentity.getLongitude();
                    aVar2.h = networkType;
                    aVar2.e = "cdma";
                    arrayList.add(aVar2);
                } else if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity2 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    a aVar3 = new a();
                    aVar3.a = cellIdentity2.getCid();
                    aVar3.b = cellIdentity2.getMcc();
                    aVar3.c = cellIdentity2.getMnc();
                    aVar3.d = cellIdentity2.getLac();
                    aVar3.d = networkId;
                    aVar3.e = "wcdma";
                    aVar3.h = networkType;
                    arrayList.add(aVar3);
                }
                i++;
            }
        } else {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            if (gsmCellLocation == null) {
                com.tencent.bigdata.customdataacquisition.b.a.d("GsmCellLocation is null!!!");
                return null;
            }
            int lac = gsmCellLocation.getLac();
            String substring2 = telephonyManager.getNetworkOperator().substring(0, 3);
            String substring3 = telephonyManager.getNetworkOperator().substring(3, 5);
            aVar.a = gsmCellLocation.getCid();
            aVar.b = Integer.valueOf(substring2).intValue();
            aVar.c = Integer.valueOf(substring3).intValue();
            aVar.d = lac;
            aVar.h = networkType;
            aVar.e = "gsm";
            arrayList.add(aVar);
            List allCellInfo2 = telephonyManager.getAllCellInfo();
            int size2 = allCellInfo2.size();
            int i2 = 0;
            while (i2 < size2 && i2 < 3) {
                CellInfo cellInfo2 = (CellInfo) allCellInfo2.get(i2);
                if (cellInfo2 instanceof CellInfoGsm) {
                    CellIdentityGsm cellIdentity3 = ((CellInfoGsm) cellInfo2).getCellIdentity();
                    a aVar4 = new a();
                    aVar4.a = cellIdentity3.getCid();
                    aVar4.b = cellIdentity3.getMcc();
                    aVar4.c = cellIdentity3.getMnc();
                    aVar4.d = cellIdentity3.getLac();
                    aVar4.e = "gsm";
                    aVar4.h = networkType;
                    arrayList.add(aVar4);
                } else if (cellInfo2 instanceof CellInfoLte) {
                    CellIdentityLte cellIdentity4 = ((CellInfoLte) cellInfo2).getCellIdentity();
                    a aVar5 = new a();
                    aVar5.a = cellIdentity4.getCi();
                    aVar5.b = cellIdentity4.getMcc();
                    aVar5.c = cellIdentity4.getMnc();
                    aVar5.d = cellIdentity4.getTac();
                    aVar5.e = "lte";
                    aVar5.h = networkType;
                    arrayList.add(aVar5);
                }
                i2++;
            }
        }
        return arrayList;
    }

    public static JSONObject d(Context context) {
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            com.tencent.bigdata.customdataacquisition.b.a.b("getGps Require the permissionandroid.permission.ACCESS_FINE_LOCATION");
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager == null) {
                return null;
            }
            if (!a(locationManager)) {
                return null;
            }
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(1);
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
            if (lastKnownLocation != null) {
                return a(lastKnownLocation);
            }
            return null;
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.d("[getGps]" + e.getMessage());
        }
    }

    public static JSONArray e(Context context) {
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
            com.tencent.bigdata.customdataacquisition.b.a.b("getWifiTowers Require the permissionandroid.permission.ACCESS_WIFI_STATE");
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            for (C0073b a2 : h(context)) {
                jSONArray.put(a2.a());
            }
            return jSONArray;
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.a("", (Throwable) e);
            return jSONArray;
        }
    }

    public static JSONObject f(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray a2 = a(context);
            if (!com.tencent.bigdata.customdataacquisition.b.a.a(a2)) {
                jSONObject.put("cell", a2);
            }
            JSONArray e = e(context);
            if (!com.tencent.bigdata.customdataacquisition.b.a.a(e)) {
                jSONObject.put("wflist", e);
            }
            JSONObject d = d(context);
            if (!com.tencent.bigdata.customdataacquisition.b.a.a(d)) {
                jSONObject.put("cgps", d);
            } else {
                JSONObject b = b(context);
                if (!com.tencent.bigdata.customdataacquisition.b.a.a(b)) {
                    jSONObject.put("lasgps", b);
                }
            }
            if (com.tencent.bigdata.customdataacquisition.b.a.a(jSONObject)) {
                com.tencent.bigdata.customdataacquisition.b.a.d("No location info, return..");
                return null;
            }
            jSONObject.put("fun", "xg");
            return jSONObject;
        } catch (Throwable th) {
            com.tencent.bigdata.customdataacquisition.b.a.a("getReportLocationJson: ", th);
            return null;
        }
    }

    private static boolean g(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
            return wifiManager != null && wifiManager.isWifiEnabled();
        } catch (Exception e) {
            com.tencent.bigdata.customdataacquisition.b.a.a("Wifi Error", (Throwable) e);
            return false;
        }
    }

    private static List<C0073b> h(Context context) {
        if (!com.tencent.bigdata.customdataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
            com.tencent.bigdata.customdataacquisition.b.a.b("dump Require the permissionandroid.permission.ACCESS_WIFI_STATE");
            return new ArrayList();
        } else if (!g(context)) {
            return new ArrayList();
        } else {
            WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
            if (wifiManager == null) {
                return new ArrayList();
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            C0073b bVar = null;
            if (connectionInfo != null) {
                bVar = new C0073b(connectionInfo.getBSSID(), connectionInfo.getRssi(), connectionInfo.getSSID());
            }
            ArrayList arrayList = new ArrayList();
            if (bVar != null) {
                arrayList.add(bVar);
            }
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (scanResults != null && scanResults.size() > 0) {
                int i = 0;
                for (ScanResult scanResult : scanResults) {
                    i++;
                    if (i >= 10) {
                        break;
                    }
                    C0073b bVar2 = new C0073b(scanResult);
                    if (bVar == null || !bVar2.c.equals(bVar.c)) {
                        arrayList.add(bVar2);
                    }
                }
            }
            return arrayList;
        }
    }
}
