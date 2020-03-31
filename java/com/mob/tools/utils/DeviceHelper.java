package com.mob.tools.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.baidu.mobstat.Config;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ReflectHelper.ReflectRunnable;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.core.Constants;
import com.tencent.mid.core.Constants.ERROR;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.sf.json.util.JSONUtils;

public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    private String advertiseID;
    private Context context;
    private String fixedString = null;
    private String imei;
    private String[] invalidMacList;
    private String serialno;
    private String wifimac;

    private class GSConnection implements ServiceConnection {
        boolean got;
        private final BlockingQueue<IBinder> iBinders;

        private GSConnection() {
            this.got = false;
            this.iBinders = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.iBinders.put(iBinder);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder takeBinder() throws InterruptedException {
            if (this.got) {
                throw new IllegalStateException();
            }
            this.got = true;
            return (IBinder) this.iBinders.poll(1500, TimeUnit.MILLISECONDS);
        }
    }

    public static synchronized DeviceHelper getInstance(Context context2) {
        DeviceHelper deviceHelper2;
        synchronized (DeviceHelper.class) {
            if (deviceHelper == null && context2 != null) {
                deviceHelper = new DeviceHelper(context2);
            }
            deviceHelper2 = deviceHelper;
        }
        return deviceHelper2;
    }

    private DeviceHelper(Context context2) {
        this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
    }

    public boolean isRooted() {
        try {
            File file = new File(Strings.getString(0));
            if (file.exists() && file.canExecute()) {
                return true;
            }
            File file2 = new File(Strings.getString(1));
            if (!file2.exists() || !file2.canExecute()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    public String getSSID() {
        try {
            if (!checkPermission(Constants.PERMISSION_ACCESS_WIFI_STATE)) {
                return null;
            }
            Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
            if (systemServiceSafe == null) {
                return null;
            }
            Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
            if (invokeInstanceMethod == null) {
                return null;
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(3), new Object[0]);
            return str == null ? null : str.replace(JSONUtils.DOUBLE_QUOTE, "");
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getBssid() {
        try {
            if (!checkPermission(Constants.PERMISSION_ACCESS_WIFI_STATE)) {
                return null;
            }
            Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
            if (systemServiceSafe == null) {
                return null;
            }
            Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
            if (invokeInstanceMethod == null) {
                return null;
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(4), new Object[0]);
            if (str == null) {
                str = null;
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    private String[] getInvalidMacList() {
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(ResHelper.getCacheRootFile(this.context, ".mcli").getPath()));
            if (!TextUtils.isEmpty(AES128Decode)) {
                ArrayList arrayList = (ArrayList) new Hashon().fromJson(AES128Decode).get("list");
                if (arrayList != null && arrayList.size() > 0) {
                    return (String[]) arrayList.toArray(new String[0]);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    private String getLocalWifiMac() {
        String str;
        try {
            File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".mcw");
            if (cacheRootFile.exists()) {
                str = Data.AES128Decode("1234567890abcdfg", (byte[]) ResHelper.readObjectFromFile(cacheRootFile.getPath()));
            } else {
                str = null;
            }
            if (TextUtils.isEmpty(str)) {
                str = getWAbcd(2);
            }
            if (!TextUtils.isEmpty(str) && str.trim().matches("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$")) {
                return str.trim();
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    private void saveLocalWifiMac(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.trim().matches("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$")) {
                File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".mcw");
                ResHelper.saveObjectToFile(cacheRootFile.getPath(), Data.AES128Encode("1234567890abcdfg", str.trim()));
                saveWabcd(str.trim(), 2);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private String getLocalSerial() {
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(ResHelper.getCacheRootFile(this.context, ".slw").getPath()));
            if (!TextUtils.isEmpty(AES128Decode)) {
                return AES128Decode.trim();
            }
            return getWAbcd(3);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    private void saveLocalSerial(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !str.trim().equals("")) {
                File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".slw");
                ResHelper.saveObjectToFile(cacheRootFile.getPath(), Data.AES128Encode(Strings.getString(76), str.trim()));
                saveWabcd(str.trim(), 3);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    public String getMacAddress() {
        if (!TextUtils.isEmpty(this.wifimac)) {
            return this.wifimac;
        }
        String localWifiMac = getLocalWifiMac();
        if (!TextUtils.isEmpty(localWifiMac) && checkMacIsValid(localWifiMac)) {
            this.wifimac = localWifiMac;
            return localWifiMac;
        } else if (VERSION.SDK_INT >= 23) {
            String wlanMac = getWlanMac();
            if (wlanMac == null || TextUtils.isEmpty(wlanMac.trim())) {
                return getWifiMac();
            }
            return wlanMac.trim();
        } else {
            String wifiMac = getWifiMac();
            if (wifiMac == null || !checkMacIsValid(wifiMac)) {
                return getWlanMac();
            }
            this.wifimac = wifiMac.trim();
            saveLocalWifiMac(this.wifimac);
            return this.wifimac;
        }
    }

    private String getWlanMac() {
        try {
            String hardwareAddressFromShell = getHardwareAddressFromShell("wlan0");
            if (hardwareAddressFromShell != null && checkMacIsValid(hardwareAddressFromShell)) {
                this.wifimac = hardwareAddressFromShell.trim();
                saveLocalWifiMac(this.wifimac);
                return this.wifimac;
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        try {
            return getValidNetworkHardwareAddress();
        } catch (Throwable th2) {
            MobLog.getInstance().d(th2);
            return null;
        }
    }

    private boolean checkMacIsValid(String str) {
        String[] strArr;
        if (str == null) {
            return false;
        }
        try {
            if (TextUtils.isEmpty(str.trim())) {
                return false;
            }
            if (this.invalidMacList == null) {
                this.invalidMacList = getInvalidMacList();
            }
            String[] strArr2 = this.invalidMacList;
            if (strArr2 == null) {
                strArr = new String[]{Strings.getString(70), Strings.getString(71)};
            } else {
                strArr = strArr2;
            }
            for (String str2 : strArr) {
                if (str2 != null && str.trim().startsWith(str2.trim())) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private String getWifiMac() {
        try {
            Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
            if (systemServiceSafe == null) {
                return null;
            }
            Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
            if (invokeInstanceMethod == null) {
                return null;
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(5), new Object[0]);
            return str == null ? null : str.trim();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private String getCurrentNetworkHardwareAddress() throws Throwable {
        byte[] bArr;
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
            Enumeration inetAddresses = networkInterface.getInetAddresses();
            if (inetAddresses != null) {
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        if (VERSION.SDK_INT >= 9) {
                            bArr = networkInterface.getHardwareAddress();
                        } else {
                            bArr = null;
                        }
                        if (bArr != null) {
                            StringBuilder sb = new StringBuilder();
                            for (byte valueOf : bArr) {
                                sb.append(String.format("%02x:", new Object[]{Byte.valueOf(valueOf)}));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            return sb.toString();
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private String getValidNetworkHardwareAddress() throws Throwable {
        CharSequence charSequence;
        HashMap listNetworkHardware = listNetworkHardware();
        if (listNetworkHardware == null || listNetworkHardware.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(listNetworkHardware.keySet());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        CharSequence charSequence2 = null;
        while (arrayList.size() > 0) {
            String trim = ((String) arrayList.remove(0)).trim();
            if (trim.equals("wlan0")) {
                charSequence = "wlan0";
            } else if (trim.startsWith("wlan")) {
                arrayList2.add(trim);
                charSequence = charSequence2;
            } else if (trim.startsWith("eth")) {
                arrayList3.add(trim);
                charSequence = charSequence2;
            } else if (trim.startsWith("rev_rmnet")) {
                arrayList4.add(trim);
                charSequence = charSequence2;
            } else if (trim.startsWith("dummy")) {
                arrayList5.add(trim);
                charSequence = charSequence2;
            } else if (trim.startsWith("usbnet")) {
                arrayList6.add(trim);
                charSequence = charSequence2;
            } else if (trim.startsWith("rmnet_usb")) {
                arrayList7.add(trim);
                charSequence = charSequence2;
            } else {
                arrayList8.add(trim);
                charSequence = charSequence2;
            }
            charSequence2 = charSequence;
        }
        Collections.sort(arrayList2);
        Collections.sort(arrayList3);
        Collections.sort(arrayList4);
        Collections.sort(arrayList5);
        Collections.sort(arrayList6);
        Collections.sort(arrayList7);
        Collections.sort(arrayList8);
        if (!TextUtils.isEmpty(charSequence2)) {
            arrayList.add(charSequence2);
        }
        arrayList.addAll(arrayList2);
        if (NetworkUtil.NETWORK_WIFI.equals(getNetworkType())) {
            try {
                String currentNetworkHardwareAddress = getCurrentNetworkHardwareAddress();
                if (!TextUtils.isEmpty(currentNetworkHardwareAddress)) {
                    arrayList.add(currentNetworkHardwareAddress);
                }
            } catch (Throwable th) {
            }
        }
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) listNetworkHardware.get((String) it.next());
                if (str != null && checkMacIsValid(str)) {
                    this.wifimac = str.trim();
                    saveLocalWifiMac(this.wifimac);
                    return this.wifimac;
                }
            }
        }
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        arrayList.addAll(arrayList5);
        arrayList.addAll(arrayList6);
        arrayList.addAll(arrayList7);
        arrayList.addAll(arrayList8);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String str2 = (String) listNetworkHardware.get((String) it2.next());
            if (str2 != null && checkMacIsValid(str2)) {
                return str2.trim();
            }
        }
        return null;
    }

    public HashMap<String, String> listNetworkHardware() throws Throwable {
        byte[] bArr;
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        ArrayList<NetworkInterface> list = Collections.list(networkInterfaces);
        HashMap hashMap = new HashMap();
        for (NetworkInterface networkInterface : list) {
            if (VERSION.SDK_INT >= 9) {
                bArr = networkInterface.getHardwareAddress();
            } else {
                bArr = null;
            }
            if (bArr != null) {
                StringBuilder sb = new StringBuilder();
                for (byte valueOf : bArr) {
                    sb.append(String.format("%02x:", new Object[]{Byte.valueOf(valueOf)}));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                hashMap.put(networkInterface.getName(), sb.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0086 A[SYNTHETIC, Splitter:B:22:0x0086] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getHardwareAddressFromShell(java.lang.String r7) {
        /*
            r6 = this;
            r1 = 0
            r0 = 42
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.String r0 = com.mob.tools.utils.ReflectHelper.importClass(r0)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r2 = 43
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r2.<init>()     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3 = 6
            java.lang.String r3 = com.mob.tools.utils.Strings.getString(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3 = 7
            java.lang.String r3 = com.mob.tools.utils.Strings.getString(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3 = 44
            java.lang.String r3 = com.mob.tools.utils.Strings.getString(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r5 = 0
            r4[r5] = r2     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r3, r4)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r2 = 45
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0083 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x0093 }
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ Throwable -> 0x008c }
        L_0x0069:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x008a
        L_0x006f:
            return r1
        L_0x0070:
            r0 = move-exception
            r2 = r1
        L_0x0072:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0090 }
            r3.d(r0)     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0095
            r2.close()     // Catch:{ Throwable -> 0x0080 }
            r0 = r1
            goto L_0x0069
        L_0x0080:
            r0 = move-exception
            r0 = r1
            goto L_0x0069
        L_0x0083:
            r0 = move-exception
        L_0x0084:
            if (r1 == 0) goto L_0x0089
            r1.close()     // Catch:{ Throwable -> 0x008e }
        L_0x0089:
            throw r0
        L_0x008a:
            r1 = r0
            goto L_0x006f
        L_0x008c:
            r2 = move-exception
            goto L_0x0069
        L_0x008e:
            r1 = move-exception
            goto L_0x0089
        L_0x0090:
            r0 = move-exception
            r1 = r2
            goto L_0x0084
        L_0x0093:
            r0 = move-exception
            goto L_0x0072
        L_0x0095:
            r0 = r1
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getHardwareAddressFromShell(java.lang.String):java.lang.String");
    }

    public String getBTMacFromProvider() {
        return Secure.getString(this.context.getContentResolver(), "bluetooth_address");
    }

    public String getBTMac() {
        String str;
        if (VERSION.SDK_INT >= 27) {
            return null;
        }
        try {
            if (!checkPermission("android.permission.BLUETOOTH")) {
                return null;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (VERSION.SDK_INT < 23) {
                return defaultAdapter.getAddress();
            }
            Object instanceField = ReflectHelper.getInstanceField(defaultAdapter, "mService");
            if (instanceField != null) {
                str = (String) ReflectHelper.invokeInstanceMethod(instanceField, "getAddress", new Object[0]);
            } else {
                str = null;
            }
            return str;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean isBT() {
        try {
            if (checkPermission("android.permission.BLUETOOTH")) {
                return BluetoothAdapter.getDefaultAdapter().isEnabled();
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public String getModel() {
        String str = Build.MODEL;
        if (!TextUtils.isEmpty(str)) {
            return str.trim();
        }
        return str;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getDeviceId() {
        String imei2 = getIMEI();
        if (!TextUtils.isEmpty(imei2) || VERSION.SDK_INT < 9) {
            return imei2;
        }
        return getSerialno();
    }

    public String getIMEI() {
        Throwable th;
        String str;
        String str2;
        String str3;
        if (!TextUtils.isEmpty(this.imei)) {
            return this.imei;
        }
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return null;
        }
        try {
            if (checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                str2 = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(8), new Object[0]);
            } else {
                str2 = null;
            }
            try {
                if (TextUtils.isEmpty(str2)) {
                    File cacheRootFile = ResHelper.getCacheRootFile(this.context, "comm/.di");
                    if (cacheRootFile == null || !cacheRootFile.exists()) {
                        return getWAbcd(1);
                    }
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cacheRootFile));
                    Object readObject = objectInputStream.readObject();
                    if (readObject == null || !(readObject instanceof char[])) {
                        str3 = null;
                    } else {
                        str3 = String.valueOf((char[]) readObject);
                    }
                    objectInputStream.close();
                    return str3;
                }
                this.imei = str2;
                File cacheRootFile2 = ResHelper.getCacheRootFile(this.context, "comm/.di");
                if (cacheRootFile2 != null && cacheRootFile2.exists()) {
                    cacheRootFile2.delete();
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile2));
                objectOutputStream.writeObject(str2.toCharArray());
                objectOutputStream.flush();
                objectOutputStream.close();
                saveWabcd(str2, 1);
                return str2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = str2;
                th = th3;
                MobLog.getInstance().w(th);
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            str = null;
            MobLog.getInstance().w(th);
            return str;
        }
    }

    public String[] queryIMEI() {
        ArrayList arrayList;
        String[] split;
        String str;
        try {
            arrayList = new ArrayList();
            if (checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    return null;
                }
                String str2 = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, "getDeviceId", new Object[0]);
                if (TextUtils.isEmpty(str2)) {
                    str2 = ERROR.CMD_FORMAT_ERROR;
                }
                arrayList.add(str2);
                for (int i = 0; i <= 5; i++) {
                    try {
                        str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(8), Integer.valueOf(i));
                    } catch (Throwable th) {
                        str = null;
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = ERROR.CMD_FORMAT_ERROR;
                    }
                    arrayList.add(str);
                }
            }
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
        }
        String[] split2 = Strings.getString(54).split(";");
        String[][] strArr = new String[split2.length][];
        for (int i2 = 0; i2 < split2.length; i2++) {
            strArr[i2] = split2[i2].split(StorageInterface.KEY_SPLITER);
        }
        for (String[] strArr2 : strArr) {
            for (String systemProperties : strArr[r4]) {
                for (String str3 : getSystemProperties(systemProperties).split(StorageInterface.KEY_SPLITER)) {
                    if (!TextUtils.isEmpty(str3) && !arrayList.contains(str3)) {
                        arrayList.add(str3);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    private String getSystemProperties(String str) {
        try {
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(9)), Strings.getString(10), str);
            if (invokeStaticMethod != null) {
                return String.valueOf(invokeStaticMethod);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return "";
    }

    public String getSerialno() {
        String str;
        String str2 = null;
        if (!TextUtils.isEmpty(this.serialno)) {
            return this.serialno;
        }
        if (VERSION.SDK_INT < 9 || VERSION.SDK_INT >= 26) {
            str = null;
        } else {
            try {
                str = (String) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(9)), Strings.getString(10), Strings.getString(11), "unknown");
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                str = null;
            }
        }
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str)) {
            str = Build.SERIAL;
        }
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str)) {
            try {
                if (checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                    str = (String) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass("android.os.Build"), "getSerial", new Object[0]);
                }
            } catch (Throwable th2) {
                MobLog.getInstance().w(th2);
                str = null;
            }
        }
        if (!"unknown".equalsIgnoreCase(str)) {
            str2 = str;
        }
        if (TextUtils.isEmpty(str2)) {
            String localSerial = getLocalSerial();
            if (!TextUtils.isEmpty(localSerial)) {
                return localSerial;
            }
            return str2;
        }
        this.serialno = str2;
        String trim = str2.trim();
        saveLocalSerial(trim);
        return trim;
    }

    public String getDeviceData() {
        try {
            String str = getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
            String deviceKey = getDeviceKey();
            if (deviceKey == null) {
                deviceKey = "";
            } else if (deviceKey.length() > 16) {
                deviceKey = deviceKey.substring(0, 16);
            }
            return Base64AES(str, deviceKey);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "";
        }
    }

    public String getDeviceDataNotAES() {
        return getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
    }

    public String Base64AES(String str, String str2) {
        Throwable th;
        String str3;
        try {
            str3 = Base64.encodeToString(Data.AES128Encode(str2, str), 0);
            try {
                if (str3.contains("\n")) {
                    return str3.replace("\n", "");
                }
                return str3;
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().w(th);
                return str3;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str3 = null;
            th = th4;
            MobLog.getInstance().w(th);
            return str3;
        }
    }

    public int getOSVersionInt() {
        return VERSION.SDK_INT;
    }

    public String getOSVersionName() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            return null;
        }
    }

    public String getOSLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getAppLanguage() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    public String getOSCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getScreenSize() {
        int[] screenSize = ResHelper.getScreenSize(this.context);
        if (this.context.getResources().getConfiguration().orientation == 1) {
            return screenSize[0] + Config.EVENT_HEAT_X + screenSize[1];
        }
        return screenSize[1] + Config.EVENT_HEAT_X + screenSize[0];
    }

    public String getCarrier() {
        try {
            Object systemServiceSafe = getSystemServiceSafe("phone");
            if (systemServiceSafe == null) {
                return ERROR.CMD_FORMAT_ERROR;
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(12), new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return ERROR.CMD_FORMAT_ERROR;
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return ERROR.CMD_FORMAT_ERROR;
        }
    }

    public String getCarrierName() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return null;
        }
        try {
            if (!checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                return null;
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(13), new Object[0]);
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public String getMCC() {
        String imsi = getIMSI();
        if (imsi == null || imsi.length() < 3) {
            return null;
        }
        return imsi.substring(0, 3);
    }

    public String getMNC() {
        String imsi = getIMSI();
        if (imsi == null || imsi.length() < 5) {
            return null;
        }
        return imsi.substring(3, 5);
    }

    public String getSimSerialNumber() {
        try {
            if (checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    return ERROR.CMD_FORMAT_ERROR;
                }
                return (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(14), new Object[0]);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return ERROR.CMD_FORMAT_ERROR;
    }

    public String getLN() {
        try {
            if (checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    return ERROR.CMD_FORMAT_ERROR;
                }
                return (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(15), new Object[0]);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return ERROR.CMD_FORMAT_ERROR;
    }

    public String getBluetoothName() {
        try {
            if (checkPermission("android.permission.BLUETOOTH")) {
                Object invokeStaticMethod = ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(16)), Strings.getString(17), new Object[0]);
                if (invokeStaticMethod != null) {
                    return (String) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, Strings.getString(18), new Object[0]);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    public String getSignMD5() {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().w((Throwable) e);
            return null;
        }
    }

    public String getSignMD5(String str) {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().w((Throwable) e);
            return null;
        }
    }

    public Object getSystemServiceSafe(String str) {
        try {
            return this.context.getSystemService(str);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public String getNetworkType() {
        try {
            if (checkPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe("connectivity");
                if (systemServiceSafe != null) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemServiceSafe).getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        int type = activeNetworkInfo.getType();
                        switch (type) {
                            case 0:
                                if (is4GMobileNetwork()) {
                                    return NetworkUtil.NETWORK_CLASS_4_G;
                                }
                                return isFastMobileNetwork() ? NetworkUtil.NETWORK_CLASS_3_G : NetworkUtil.NETWORK_CLASS_2_G;
                            case 1:
                                return NetworkUtil.NETWORK_WIFI;
                            case 6:
                                return "wimax";
                            case 7:
                                return "bluetooth";
                            case 8:
                                return "dummy";
                            case 9:
                                return "ethernet";
                            default:
                                return String.valueOf(type);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return "none";
    }

    public String getNetworkTypeForStatic() {
        String lowerCase = getNetworkType().toLowerCase();
        if (TextUtils.isEmpty(lowerCase) || "none".equals(lowerCase)) {
            return "none";
        }
        if (lowerCase.startsWith("4g") || lowerCase.startsWith("3g") || lowerCase.startsWith("2g")) {
            return "cell";
        }
        if (lowerCase.startsWith(NetworkUtil.NETWORK_WIFI)) {
            return NetworkUtil.NETWORK_WIFI;
        }
        return "other";
    }

    public String getDetailNetworkTypeForStatic() {
        try {
            String lowerCase = getNetworkType().toLowerCase();
            if (TextUtils.isEmpty(lowerCase) || "none".equals(lowerCase)) {
                return "none";
            }
            if (lowerCase.startsWith(NetworkUtil.NETWORK_WIFI)) {
                return NetworkUtil.NETWORK_WIFI;
            }
            if (lowerCase.startsWith("4g")) {
                return "4g";
            }
            if (lowerCase.startsWith("3g")) {
                return "3g";
            }
            if (lowerCase.startsWith("2g")) {
                return "2g";
            }
            if (lowerCase.startsWith("bluetooth")) {
                return "bluetooth";
            }
            return lowerCase;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "none";
        }
    }

    public int getPlatformCode() {
        return 1;
    }

    private boolean is4GMobileNetwork() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        try {
            return ((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue() == 13;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    private boolean isFastMobileNetwork() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        try {
            switch (((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue()) {
                case 0:
                    return false;
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                    return true;
                case 4:
                    return false;
                case 5:
                    return true;
                case 6:
                    return true;
                case 7:
                    return false;
                case 8:
                    return true;
                case 9:
                    return true;
                case 10:
                    return true;
                case 11:
                    return false;
                case 12:
                    return true;
                case 13:
                    return true;
                case 14:
                    return true;
                case 15:
                    return true;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return false;
    }

    public String getDeviceKey() {
        String str;
        String str2;
        String str3 = null;
        try {
            str = getDeviceKeyWithDuid("comm/dbs/.duid");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            str = str3;
        }
        if (TextUtils.isEmpty(str) || str.length() < 40) {
            str = genDeviceKey();
        }
        if (!TextUtils.isEmpty(str) && str.length() >= 40) {
            return str.trim();
        }
        try {
            str3 = getLocalDeviceKey();
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
        }
        if (!TextUtils.isEmpty(str3) && str3.length() >= 40) {
            return str3.trim();
        }
        if (TextUtils.isEmpty(str3) || str3.length() < 40) {
            str2 = getCharAndNumr(40);
        } else {
            str2 = str3;
        }
        if (str2 == null) {
            return str2;
        }
        try {
            str2 = str2.trim();
            saveLocalDeviceKey(str2);
            return str2;
        } catch (Throwable th3) {
            MobLog.getInstance().w(th3);
            return str2;
        }
    }

    private String genDeviceKey() {
        try {
            String macAddress = getMacAddress();
            String deviceId = getDeviceId();
            return Data.byteToHex(Data.SHA1(macAddress + Config.TRACE_TODAY_VISIT_SPLIT + deviceId + Config.TRACE_TODAY_VISIT_SPLIT + getModel()));
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getCharAndNumr(int i) {
        long currentTimeMillis = System.currentTimeMillis() ^ SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                stringBuffer.insert(i2 + 1, (char) (random.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0038 A[SYNTHETIC, Splitter:B:21:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0044 A[SYNTHETIC, Splitter:B:29:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getDeviceKeyWithDuid(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r3 = 0
            android.content.Context r0 = r6.context     // Catch:{ Throwable -> 0x0048 }
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r7)     // Catch:{ Throwable -> 0x0048 }
            if (r0 == 0) goto L_0x0050
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x0048 }
            if (r1 == 0) goto L_0x0050
            boolean r1 = r0.isFile()     // Catch:{ Throwable -> 0x0048 }
            if (r1 == 0) goto L_0x0050
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x002d, all -> 0x0040 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x002d, all -> 0x0040 }
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x002d, all -> 0x0040 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x002d, all -> 0x0040 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ Throwable -> 0x00c7 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x00c7 }
            if (r1 == 0) goto L_0x002a
            r1.close()     // Catch:{ Throwable -> 0x00bf }
        L_0x002a:
            if (r0 != 0) goto L_0x0052
        L_0x002c:
            return r3
        L_0x002d:
            r0 = move-exception
            r1 = r3
        L_0x002f:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00c4 }
            r2.w(r0)     // Catch:{ all -> 0x00c4 }
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Throwable -> 0x003d }
            r0 = r3
            goto L_0x002a
        L_0x003d:
            r0 = move-exception
            r0 = r3
            goto L_0x002a
        L_0x0040:
            r0 = move-exception
            r1 = r3
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ Throwable -> 0x00c2 }
        L_0x0047:
            throw r0     // Catch:{ Throwable -> 0x0048 }
        L_0x0048:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
        L_0x0050:
            r0 = r3
            goto L_0x002a
        L_0x0052:
            java.lang.String r1 = "deviceInfo"
            java.lang.Object r0 = r0.get(r1)
            java.util.HashMap r0 = (java.util.HashMap) r0
            if (r0 == 0) goto L_0x002c
            java.lang.String r1 = ""
            java.lang.String r1 = "mac"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r2 = "imei"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x00b5 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x00b5 }
            if (r4 == 0) goto L_0x0082
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00b5 }
            r5 = 9
            if (r4 < r5) goto L_0x0082
            java.lang.String r2 = "serialno"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x00b5 }
        L_0x0082:
            java.lang.String r4 = "model"
            java.lang.Object r0 = r0.get(r4)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x00b5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b5 }
            r4.<init>()     // Catch:{ Throwable -> 0x00b5 }
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r4 = ":"
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r2 = ":"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00b5 }
            byte[] r0 = com.mob.tools.utils.Data.SHA1(r0)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r3 = com.mob.tools.utils.Data.byteToHex(r0)     // Catch:{ Throwable -> 0x00b5 }
            goto L_0x002c
        L_0x00b5:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
            goto L_0x002c
        L_0x00bf:
            r1 = move-exception
            goto L_0x002a
        L_0x00c2:
            r1 = move-exception
            goto L_0x0047
        L_0x00c4:
            r0 = move-exception
            goto L_0x0042
        L_0x00c7:
            r0 = move-exception
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getDeviceKeyWithDuid(java.lang.String):java.lang.String");
    }

    private String getLocalDeviceKey() throws Throwable {
        String str;
        if (!getSdcardState()) {
            return null;
        }
        File file = new File(getSdcardPath(), "ShareSDK");
        if (file.exists()) {
            File file2 = new File(file, ".dk");
            if (file2.exists()) {
                File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".dk");
                if (cacheRootFile != null && file2.renameTo(cacheRootFile)) {
                    file2.delete();
                }
            }
        }
        File cacheRootFile2 = ResHelper.getCacheRootFile(this.context, ".dk");
        if (cacheRootFile2 != null && !cacheRootFile2.exists()) {
            return null;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cacheRootFile2));
        Object readObject = objectInputStream.readObject();
        if (readObject == null || !(readObject instanceof char[])) {
            str = null;
        } else {
            str = String.valueOf((char[]) readObject);
        }
        objectInputStream.close();
        return str;
    }

    private void saveLocalDeviceKey(String str) throws Throwable {
        if (getSdcardState()) {
            File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".dk");
            if (cacheRootFile != null && cacheRootFile.exists()) {
                cacheRootFile.delete();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile));
            objectOutputStream.writeObject(str.toCharArray());
            objectOutputStream.flush();
            objectOutputStream.close();
        }
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    public String getAppName() {
        String str;
        try {
            ApplicationInfo applicationInfo = this.context.getApplicationInfo();
            str = applicationInfo.name;
            if (str != null) {
                if (VERSION.SDK_INT < 25 || str.endsWith(".*")) {
                    return str;
                }
                try {
                    ReflectHelper.importClass(str);
                    str = null;
                } catch (Throwable th) {
                }
            }
            int i = applicationInfo.labelRes;
            if (i > 0) {
                return this.context.getString(i);
            }
            return String.valueOf(applicationInfo.nonLocalizedLabel);
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
            return "";
        }
    }

    public int getAppVersion() {
        boolean z = false;
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return z;
        }
    }

    public String getAppVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return "1.0";
        }
    }

    public ArrayList<HashMap<String, String>> getIA(boolean z) {
        return getAL(false, z);
    }

    public ArrayList<HashMap<String, String>> getSA() {
        return getAL(true, true);
    }

    public ArrayList<HashMap<String, String>> getAA() {
        return getAL(false, true);
    }

    public ArrayList<HashMap<String, String>> getAL(boolean z, boolean z2) {
        PackageInfo packageInfo;
        CharSequence charSequence;
        try {
            ArrayList pl = getPL();
            ArrayList arrayList = new ArrayList();
            PackageManager packageManager = this.context.getPackageManager();
            Iterator it = pl.iterator();
            while (it.hasNext()) {
                packageInfo = packageManager.getPackageInfo((String) it.next(), 0);
                if (packageInfo != null) {
                    if (z) {
                        if (!isSystemApp(packageInfo)) {
                        }
                    } else if (!z2 && isSystemApp(packageInfo)) {
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put(Config.INPUT_DEF_PKG, packageInfo.packageName);
                    try {
                        charSequence = packageInfo.applicationInfo.loadLabel(packageManager);
                    } catch (Throwable th) {
                        charSequence = null;
                    }
                    hashMap.put("name", charSequence == null ? packageInfo.packageName : charSequence.toString());
                    hashMap.put(Config.INPUT_DEF_VERSION, packageInfo.versionName);
                    hashMap.put("visible", packageManager.getLaunchIntentForPackage(packageInfo.packageName) == null ? "0" : "1");
                    hashMap.put("enable", packageInfo.applicationInfo.enabled ? "1" : "0");
                    hashMap.put("firstInstallTime", String.valueOf(packageInfo.firstInstallTime));
                    hashMap.put("lastUpdateTime", String.valueOf(packageInfo.lastUpdateTime));
                    arrayList.add(hashMap);
                }
            }
            return arrayList;
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
            return new ArrayList<>();
        }
    }

    private ArrayList<String> getPL() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(42)), Strings.getString(43), new Object[0]), Strings.getString(44), Strings.getString(20));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(45), new Object[0]), "utf-8"));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                String trim = readLine.trim();
                if (trim.length() > 8 && trim.substring(0, 8).equalsIgnoreCase("package:")) {
                    String trim2 = trim.substring(8).trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        arrayList.add(trim2);
                    }
                }
            }
            bufferedReader.close();
            ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(51), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        if (arrayList.isEmpty()) {
            try {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory(Strings.getString(74));
                for (ResolveInfo resolveInfo : this.context.getPackageManager().queryIntentActivities(intent, 0)) {
                    if (!(resolveInfo == null || resolveInfo.activityInfo == null || TextUtils.isEmpty(resolveInfo.activityInfo.packageName))) {
                        arrayList.add(resolveInfo.activityInfo.packageName);
                    }
                }
            } catch (Throwable th2) {
                MobLog.getInstance().w(th2);
            }
        }
        return arrayList;
    }

    private boolean isSystemApp(PackageInfo packageInfo) {
        boolean z;
        boolean z2;
        if ((packageInfo.applicationInfo.flags & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        if ((packageInfo.applicationInfo.flags & 128) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    public String getNetworkOperator() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return null;
        }
        try {
            return (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(21), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public boolean checkPermission(String str) throws Throwable {
        int checkPermission;
        if (VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.importClass("android.content.Context");
                Integer num = (Integer) ReflectHelper.invokeInstanceMethod(this.context, Strings.getString(22), str);
                checkPermission = num == null ? -1 : num.intValue();
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                checkPermission = -1;
            }
        } else {
            checkPermission = this.context.getPackageManager().checkPermission(str, getPackageName());
        }
        if (checkPermission == 0) {
            return true;
        }
        return false;
    }

    public boolean amIOnForeground() {
        try {
            if (VERSION.SDK_INT <= 27) {
                for (Object instanceField : ((Map) ReflectHelper.getInstanceField(currentActivityThread(), Strings.getString(23))).values()) {
                    if (!((Boolean) ReflectHelper.getInstanceField(instanceField, Strings.getString(24))).booleanValue()) {
                        return true;
                    }
                }
                return false;
            } else if (!isBackground(this.context)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private static boolean isBackground(Context context2) {
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) StubApp.getOrigApplicationContext(context2.getApplicationContext()).getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                String packageName = context2.getPackageName();
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.processName.equals(packageName)) {
                        return runningAppProcessInfo.importance == 400;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return false;
    }

    public boolean getSdcardState() {
        try {
            if (!checkPermission(Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) || !"mounted".equals(Environment.getExternalStorageState())) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    public String getSdcardPath() {
        try {
            if (VERSION.SDK_INT < 29 || this.context.getApplicationInfo().targetSdkVersion < 29) {
                return Environment.getExternalStorageDirectory().getAbsolutePath();
            }
            return this.context.getExternalFilesDir(null).getAbsolutePath();
        } catch (Throwable th) {
            return null;
        }
    }

    public String getAndroidID() {
        try {
            String string = Secure.getString(this.context.getContentResolver(), "android_id");
            MobLog.getInstance().i("getAndroidID === " + string, new Object[0]);
            return string;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "";
        }
    }

    public String getAdvertisingID() throws Throwable {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Throwable("Do not call this function from the main thread !");
        } else if (!TextUtils.isEmpty(this.advertiseID)) {
            return this.advertiseID;
        } else {
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            GSConnection gSConnection = new GSConnection();
            try {
                this.context.bindService(intent, gSConnection, 1);
                IBinder takeBinder = gSConnection.takeBinder();
                if (takeBinder == null) {
                    return this.advertiseID;
                }
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                takeBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                this.advertiseID = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                String str = this.advertiseID;
                this.context.unbindService(gSConnection);
                return str;
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                return this.advertiseID;
            } finally {
                this.context.unbindService(gSConnection);
            }
        }
    }

    public void hideSoftInput(View view) {
        Object systemServiceSafe = getSystemServiceSafe("input_method");
        if (systemServiceSafe != null) {
            ((InputMethodManager) systemServiceSafe).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftInput(View view) {
        Object systemServiceSafe = getSystemServiceSafe("input_method");
        if (systemServiceSafe != null) {
            ((InputMethodManager) systemServiceSafe).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getIMSI() {
        /*
            r6 = this;
            r5 = 15
            r1 = 0
            r4 = 0
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r6.getSystemServiceSafe(r0)
            if (r0 != 0) goto L_0x000d
        L_0x000c:
            return r1
        L_0x000d:
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = r6.checkPermission(r2)     // Catch:{ Throwable -> 0x0047 }
            if (r2 == 0) goto L_0x004f
            r2 = 25
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0047 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0047 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0047 }
        L_0x0024:
            if (r0 == 0) goto L_0x002c
            int r2 = r0.length()
            if (r2 >= r5) goto L_0x003f
        L_0x002c:
            java.lang.String[] r2 = r6.queryIMSI()
            if (r2 == 0) goto L_0x003f
            int r3 = r2.length
            if (r3 <= 0) goto L_0x003f
            r3 = r2[r4]
            int r3 = r3.length()
            if (r3 < r5) goto L_0x003f
            r0 = r2[r4]
        L_0x003f:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x000c
            r1 = r0
            goto L_0x000c
        L_0x0047:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.w(r0)
        L_0x004f:
            r0 = r1
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getIMSI():java.lang.String");
    }

    public String[] queryIMSI() {
        String[] split;
        try {
            String systemProperties = getSystemProperties(Strings.getString(55));
            ArrayList arrayList = new ArrayList();
            for (String str : systemProperties.split(StorageInterface.KEY_SPLITER)) {
                if (!TextUtils.isEmpty(str) && !arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    public String getIPAddress() {
        try {
            if (checkPermission(Constants.PERMISSION_INTERNET)) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                            if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return "0.0.0.0";
    }

    public Location getLocation(int i, int i2, boolean z) {
        try {
            if (checkPermission("android.permission.ACCESS_FINE_LOCATION") || (VERSION.SDK_INT >= 29 && checkPermission("android.permission.ACCESS_BACKGROUND_LOCATION"))) {
                return new LocationHelper().getLocation(this.context, i, i2, z);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    public HashMap<String, String> ping(String str, int i, int i2) {
        float f;
        float f2;
        float f3;
        float f4 = 0.0f;
        ArrayList arrayList = new ArrayList();
        try {
            int i3 = i2 + 8;
            Process exec = Runtime.getRuntime().exec("ping -c " + i + " -s " + i2 + " " + str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if (readLine.startsWith(i3 + " bytes from")) {
                    if (readLine.endsWith("ms")) {
                        readLine = readLine.substring(0, readLine.length() - 2).trim();
                    } else if (readLine.endsWith("s")) {
                        readLine = readLine.substring(0, readLine.length() - 1).trim() + "000";
                    }
                    int indexOf = readLine.indexOf("time=");
                    if (indexOf > 0) {
                        arrayList.add(Float.valueOf(Float.parseFloat(readLine.substring(indexOf + 5).trim())));
                    }
                }
                readLine = bufferedReader.readLine();
            }
            exec.waitFor();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        int size = arrayList.size();
        int size2 = i - arrayList.size();
        if (size > 0) {
            float f5 = Float.MAX_VALUE;
            int i4 = 0;
            float f6 = 0.0f;
            f = 0.0f;
            while (i4 < size) {
                float floatValue = ((Float) arrayList.get(i4)).floatValue();
                if (floatValue < f5) {
                    f5 = floatValue;
                }
                if (floatValue > f) {
                    f3 = floatValue;
                } else {
                    f3 = f;
                }
                i4++;
                f6 += floatValue;
                f = f3;
            }
            f4 = f6 / ((float) size);
            f2 = f5;
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("address", str);
        hashMap.put("transmitted", String.valueOf(i));
        hashMap.put("received", String.valueOf(size));
        hashMap.put("loss", String.valueOf(size2));
        hashMap.put(MessageKey.MSG_ACCEPT_TIME_MIN, String.valueOf(f2));
        hashMap.put("max", String.valueOf(f));
        hashMap.put("avg", String.valueOf(f4));
        return hashMap;
    }

    public int getCellId() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(27), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public int getCellLac() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(28), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public int getPsc() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(63), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCdmaLat() {
        /*
            r4 = this;
            r1 = -1
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r0 = r4.checkPermission(r0)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r4.getSystemServiceSafe(r0)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            r2 = 26
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0052 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            java.lang.String r2 = "CdmaCellLocation"
            java.lang.Class r3 = r0.getClass()     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r3 = r3.getSimpleName()     // Catch:{ Throwable -> 0x0052 }
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x0052 }
            if (r2 == 0) goto L_0x005a
            r2 = 56
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0052 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0052 }
            r2 = -1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r2)     // Catch:{ Throwable -> 0x0052 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0052 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0052 }
        L_0x004c:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r0 != r2) goto L_0x005c
        L_0x0051:
            return r1
        L_0x0052:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
        L_0x005a:
            r0 = r1
            goto L_0x004c
        L_0x005c:
            r1 = r0
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getCdmaLat():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCdmaLon() {
        /*
            r4 = this;
            r1 = -1
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r0 = r4.checkPermission(r0)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r4.getSystemServiceSafe(r0)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            r2 = 26
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0052 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x005a
            java.lang.String r2 = "CdmaCellLocation"
            java.lang.Class r3 = r0.getClass()     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r3 = r3.getSimpleName()     // Catch:{ Throwable -> 0x0052 }
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x0052 }
            if (r2 == 0) goto L_0x005a
            r2 = 57
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0052 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0052 }
            r2 = -1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0052 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r2)     // Catch:{ Throwable -> 0x0052 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0052 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0052 }
        L_0x004c:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r0 != r2) goto L_0x005c
        L_0x0051:
            return r1
        L_0x0052:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
        L_0x005a:
            r0 = r1
            goto L_0x004c
        L_0x005c:
            r1 = r0
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getCdmaLon():int");
    }

    public int getCdmaBid() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(58), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public int getCdmaSid() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(59), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public int getCdmaNid() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0]);
                    if (invokeInstanceMethod != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                        return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(60), new Object[0]), Integer.valueOf(-1))).intValue();
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public ArrayList<HashMap<String, Object>> getNeighboringCellInfo() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe != null) {
                    List list = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(61), new Object[0]);
                    if (list != null && list.size() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (Object next : list) {
                            int intValue = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(27), new Object[0]), Integer.valueOf(-1))).intValue();
                            int intValue2 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(28), new Object[0]), Integer.valueOf(-1))).intValue();
                            int intValue3 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(62), new Object[0]), Integer.valueOf(-1))).intValue();
                            int intValue4 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(63), new Object[0]), Integer.valueOf(-1))).intValue();
                            int intValue5 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(19), new Object[0]), Integer.valueOf(-1))).intValue();
                            if (!(intValue == -1 || intValue2 == -1)) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("cell", Integer.valueOf(intValue));
                                hashMap.put("lac", Integer.valueOf(intValue2));
                                hashMap.put("rssi", Integer.valueOf(intValue3));
                                hashMap.put("psc", Integer.valueOf(intValue4));
                                hashMap.put("networkType", Integer.valueOf(intValue5));
                                arrayList.add(hashMap);
                            }
                        }
                        if (arrayList.size() > 0) {
                            return arrayList;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    public String getDeviceType() {
        try {
            UiModeManager uiModeManager = (UiModeManager) getSystemServiceSafe("uimode");
            if (uiModeManager != null) {
                switch (uiModeManager.getCurrentModeType()) {
                    case 1:
                        return "NO_UI";
                    case 2:
                        return "DESK";
                    case 3:
                        return "CAR";
                    case 4:
                        return "TELEVISION";
                    case 5:
                        return "APPLIANCE";
                    case 6:
                        return "WATCH";
                    case 7:
                        return "VRHEADSET";
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return "UNDEFINED";
    }

    public int cscreen() {
        if (!((PowerManager) this.context.getSystemService("power")).isScreenOn()) {
            return 0;
        }
        if (((KeyguardManager) this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return 2;
        }
        return 1;
    }

    public List<Object[]> getIntentA(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        Uri parse = Uri.parse(str);
        intent.setData(parse);
        List<ResolveInfo> queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setFlags(276824064);
            intent2.setData(parse);
            String signMD5 = getSignMD5(resolveInfo.activityInfo.packageName);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            arrayList.add(new Object[]{resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name, intent2, signMD5, Boolean.valueOf(resolveInfo.activityInfo.exported)});
        }
        return arrayList;
    }

    public String getDefaultResolvePkg(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        ResolveInfo resolveActivity = this.context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    public List<String> getResolvePkgs(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        List<ResolveInfo> queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.packageName == null || arrayList.contains(resolveInfo.activityInfo.packageName))) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }

    public List<Object[]> getIntentSP(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setPackage(str);
        List<ResolveInfo> queryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentServices) {
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
            arrayList.add(new Object[]{resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name, intent2, getSignMD5(resolveInfo.serviceInfo.packageName)});
        }
        return arrayList;
    }

    public String gb(Context context2) {
        try {
            if (VERSION.SDK_INT < 11) {
                return (String) ((ClipboardManager) context2.getSystemService("clipboard")).getText();
            }
            ClipData primaryClip = ((ClipboardManager) context2.getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                return null;
            }
            return primaryClip.getItemAt(0).getText().toString();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public int cb(Context context2, String str) {
        try {
            if (VERSION.SDK_INT >= 11) {
                ((ClipboardManager) context2.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", str));
            } else {
                ((ClipboardManager) context2.getSystemService("clipboard")).setText(str);
            }
            return 1;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return 2;
        }
    }

    public int sap(Context context2, String str) throws Throwable {
        try {
            Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return 0;
            }
            launchIntentForPackage.addFlags(276824064);
            context2.startActivity(launchIntentForPackage);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().d(e);
            return 0;
        }
    }

    public int ca(Context context2, String str) {
        try {
            Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                return cs(context2, launchIntentForPackage);
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return 3;
        }
    }

    public int sa(Context context2, Intent intent) throws Throwable {
        try {
            context2.startActivity(intent);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().d(e);
            return 0;
        }
    }

    public int saInUI(final Context context2, final Intent intent) {
        final int[] iArr = new int[1];
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                synchronized (iArr) {
                    try {
                        iArr[0] = DeviceHelper.this.sa(context2, intent);
                        iArr.notifyAll();
                    } catch (Throwable th) {
                        iArr.notifyAll();
                        throw th;
                    }
                }
                return false;
            }
        });
        synchronized (iArr) {
            try {
                iArr.wait();
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        return iArr[0];
    }

    public int sh(Context context2) throws Throwable {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            intent.addCategory("android.intent.category.DEFAULT");
            context2.startActivity(intent);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().d(e);
            return 0;
        }
    }

    public int ih(Context context2) throws Throwable {
        String topApp = getTopApp(context2);
        if (topApp == null || !getLauncherPackageNames(context2).contains(topApp)) {
            return 0;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A[SYNTHETIC, Splitter:B:30:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00af A[SYNTHETIC, Splitter:B:33:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4 A[SYNTHETIC, Splitter:B:36:0x00b4] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0112 A[SYNTHETIC, Splitter:B:68:0x0112] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0117 A[SYNTHETIC, Splitter:B:71:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x011c A[SYNTHETIC, Splitter:B:74:0x011c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int ir(android.content.Context r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            r9 = this;
            r5 = 0
            r3 = 2
            r1 = 0
            r2 = 1
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 21
            if (r0 >= r4) goto L_0x0039
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r10.getSystemService(r0)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.util.List r0 = r0.getRunningAppProcesses()
            if (r0 == 0) goto L_0x0037
            int r3 = r0.size()
            if (r3 <= 0) goto L_0x0037
            java.util.Iterator r3 = r0.iterator()
        L_0x0022:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0037
            java.lang.Object r0 = r3.next()
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0
            java.lang.String r0 = r0.processName
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x0022
        L_0x0036:
            return r2
        L_0x0037:
            r2 = r1
            goto L_0x0036
        L_0x0039:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r0 > r4) goto L_0x00b7
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x014a, all -> 0x010c }
            java.lang.String r4 = "sh"
            java.lang.Process r7 = r0.exec(r4)     // Catch:{ Throwable -> 0x014a, all -> 0x010c }
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x014f, all -> 0x013d }
            java.io.OutputStream r0 = r7.getOutputStream()     // Catch:{ Throwable -> 0x014f, all -> 0x013d }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x014f, all -> 0x013d }
            java.io.DataInputStream r6 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0154, all -> 0x0141 }
            java.io.InputStream r0 = r7.getInputStream()     // Catch:{ Throwable -> 0x0154, all -> 0x0141 }
            r6.<init>(r0)     // Catch:{ Throwable -> 0x0154, all -> 0x0141 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r0.<init>()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r5 = "ps | grep "
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.StringBuilder r0 = r0.append(r11)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r4.write(r0)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r0 = "\n"
            r4.writeBytes(r0)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r4.flush()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r0 = "exit\n"
            r4.writeBytes(r0)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r4.flush()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r0.<init>()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r8.<init>(r6)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            r5.<init>(r8)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
        L_0x0094:
            java.lang.String r8 = r5.readLine()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            if (r8 == 0) goto L_0x00e6
            r0.append(r8)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            goto L_0x0094
        L_0x009e:
            r0 = move-exception
            r5 = r6
            r6 = r7
        L_0x00a1:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0146 }
            r7.d(r0)     // Catch:{ all -> 0x0146 }
            if (r6 == 0) goto L_0x00ad
            r6.destroy()     // Catch:{ Throwable -> 0x012f }
        L_0x00ad:
            if (r4 == 0) goto L_0x00b2
            r4.close()     // Catch:{ Throwable -> 0x0132 }
        L_0x00b2:
            if (r5 == 0) goto L_0x00b7
            r5.close()     // Catch:{ Throwable -> 0x0135 }
        L_0x00b7:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 27
            if (r0 >= r4) goto L_0x0126
            java.util.List r0 = r9.getIntentSP(r11)
            if (r0 == 0) goto L_0x0126
            int r4 = r0.size()
            if (r4 <= 0) goto L_0x0126
            java.util.Iterator r4 = r0.iterator()
        L_0x00cd:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0126
            java.lang.Object r0 = r4.next()
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r0 = r0[r3]
            android.content.Intent r0 = (android.content.Intent) r0
            int r0 = r9.cs(r10, r0)
            if (r0 != 0) goto L_0x0120
            r2 = r1
            goto L_0x0036
        L_0x00e6:
            r7.waitFor()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x009e, all -> 0x0144 }
            if (r0 == 0) goto L_0x010a
            r0 = r1
        L_0x00f8:
            if (r7 == 0) goto L_0x00fd
            r7.destroy()     // Catch:{ Throwable -> 0x0129 }
        L_0x00fd:
            if (r4 == 0) goto L_0x0102
            r4.close()     // Catch:{ Throwable -> 0x012b }
        L_0x0102:
            if (r6 == 0) goto L_0x0107
            r6.close()     // Catch:{ Throwable -> 0x012d }
        L_0x0107:
            r2 = r0
            goto L_0x0036
        L_0x010a:
            r0 = r2
            goto L_0x00f8
        L_0x010c:
            r0 = move-exception
            r4 = r5
            r6 = r5
            r7 = r5
        L_0x0110:
            if (r7 == 0) goto L_0x0115
            r7.destroy()     // Catch:{ Throwable -> 0x0137 }
        L_0x0115:
            if (r4 == 0) goto L_0x011a
            r4.close()     // Catch:{ Throwable -> 0x0139 }
        L_0x011a:
            if (r6 == 0) goto L_0x011f
            r6.close()     // Catch:{ Throwable -> 0x013b }
        L_0x011f:
            throw r0
        L_0x0120:
            if (r0 == r2) goto L_0x0036
            if (r0 != r3) goto L_0x00cd
            goto L_0x0036
        L_0x0126:
            r2 = r3
            goto L_0x0036
        L_0x0129:
            r1 = move-exception
            goto L_0x00fd
        L_0x012b:
            r1 = move-exception
            goto L_0x0102
        L_0x012d:
            r1 = move-exception
            goto L_0x0107
        L_0x012f:
            r0 = move-exception
            goto L_0x00ad
        L_0x0132:
            r0 = move-exception
            goto L_0x00b2
        L_0x0135:
            r0 = move-exception
            goto L_0x00b7
        L_0x0137:
            r1 = move-exception
            goto L_0x0115
        L_0x0139:
            r1 = move-exception
            goto L_0x011a
        L_0x013b:
            r1 = move-exception
            goto L_0x011f
        L_0x013d:
            r0 = move-exception
            r4 = r5
            r6 = r5
            goto L_0x0110
        L_0x0141:
            r0 = move-exception
            r6 = r5
            goto L_0x0110
        L_0x0144:
            r0 = move-exception
            goto L_0x0110
        L_0x0146:
            r0 = move-exception
            r7 = r6
            r6 = r5
            goto L_0x0110
        L_0x014a:
            r0 = move-exception
            r4 = r5
            r6 = r5
            goto L_0x00a1
        L_0x014f:
            r0 = move-exception
            r4 = r5
            r6 = r7
            goto L_0x00a1
        L_0x0154:
            r0 = move-exception
            r6 = r7
            goto L_0x00a1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.ir(android.content.Context, java.lang.String):int");
    }

    private static List<String> getLauncherPackageNames(Context context2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context2.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addCategory("android.intent.category.DEFAULT");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
            arrayList.add(resolveInfo.activityInfo.packageName);
        }
        return arrayList;
    }

    public static String getTopApp(Context context2) throws Throwable {
        int i = 0;
        if (VERSION.SDK_INT < 21) {
            return ((RunningTaskInfo) ((ActivityManager) context2.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1).get(0)).topActivity.getPackageName();
        }
        UsageStatsManager usageStatsManager = (UsageStatsManager) context2.getSystemService("usagestats");
        if (usageStatsManager == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List queryUsageStats = usageStatsManager.queryUsageStats(4, currentTimeMillis - 3600000, currentTimeMillis);
        String str = "";
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            return str;
        }
        for (int i2 = 0; i2 < queryUsageStats.size(); i2++) {
            if (((UsageStats) queryUsageStats.get(i2)).getLastTimeUsed() > ((UsageStats) queryUsageStats.get(i)).getLastTimeUsed()) {
                i = i2;
            }
        }
        return ((UsageStats) queryUsageStats.get(i)).getPackageName();
    }

    public Activity getTopActivity() {
        try {
            if (VERSION.SDK_INT > 27) {
                return null;
            }
            Map map = (Map) ReflectHelper.getInstanceField(currentActivityThread(), Strings.getString(23));
            for (Object next : map.values()) {
                if (!((Boolean) ReflectHelper.getInstanceField(next, Strings.getString(29))).booleanValue()) {
                    return (Activity) ReflectHelper.getInstanceField(next, Strings.getString(30));
                }
            }
            for (Object next2 : map.values()) {
                if (!((Boolean) ReflectHelper.getInstanceField(next2, Strings.getString(24))).booleanValue()) {
                    return (Activity) ReflectHelper.getInstanceField(next2, Strings.getString(30));
                }
            }
            return null;
        } catch (Throwable th) {
        }
    }

    public static Object currentActivityThread() {
        final AnonymousClass2 r0 = new ReflectRunnable<Void, Object>() {
            public Object run(Void voidR) {
                try {
                    return ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(31)), Strings.getString(32), new Object[0]);
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                    return null;
                }
            }
        };
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            return r0.run(null);
        }
        final Object obj = new Object();
        final Object[] objArr = new Object[1];
        synchronized (obj) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
                    com.mob.tools.MobLog.getInstance().w(r0);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:27:0x0041, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
                    com.mob.tools.MobLog.getInstance().w(r1);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0010, B:23:0x003b] */
                /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x0040=Splitter:B:25:0x0040, B:6:0x0015=Splitter:B:6:0x0015} */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean handleMessage(android.os.Message r7) {
                    /*
                        r6 = this;
                        r5 = 0
                        java.lang.Object r2 = r1
                        monitor-enter(r2)
                        java.lang.Object[] r0 = r2     // Catch:{ Throwable -> 0x0023 }
                        r1 = 0
                        com.mob.tools.utils.ReflectHelper$ReflectRunnable r3 = r0     // Catch:{ Throwable -> 0x0023 }
                        r4 = 0
                        java.lang.Object r3 = r3.run(r4)     // Catch:{ Throwable -> 0x0023 }
                        r0[r1] = r3     // Catch:{ Throwable -> 0x0023 }
                        java.lang.Object r0 = r1     // Catch:{ Throwable -> 0x0017 }
                        r0.notify()     // Catch:{ Throwable -> 0x0017 }
                    L_0x0015:
                        monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                        return r5
                    L_0x0017:
                        r0 = move-exception
                        com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0020 }
                        r1.w(r0)     // Catch:{ all -> 0x0020 }
                        goto L_0x0015
                    L_0x0020:
                        r0 = move-exception
                        monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                        throw r0
                    L_0x0023:
                        r0 = move-exception
                        com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003a }
                        r1.w(r0)     // Catch:{ all -> 0x003a }
                        java.lang.Object r0 = r1     // Catch:{ Throwable -> 0x0031 }
                        r0.notify()     // Catch:{ Throwable -> 0x0031 }
                        goto L_0x0015
                    L_0x0031:
                        r0 = move-exception
                        com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0020 }
                        r1.w(r0)     // Catch:{ all -> 0x0020 }
                        goto L_0x0015
                    L_0x003a:
                        r0 = move-exception
                        java.lang.Object r1 = r1     // Catch:{ Throwable -> 0x0041 }
                        r1.notify()     // Catch:{ Throwable -> 0x0041 }
                    L_0x0040:
                        throw r0     // Catch:{ all -> 0x0020 }
                    L_0x0041:
                        r1 = move-exception
                        com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0020 }
                        r3.w(r1)     // Catch:{ all -> 0x0020 }
                        goto L_0x0040
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.AnonymousClass3.handleMessage(android.os.Message):boolean");
                }
            });
            try {
                obj.wait();
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        return objArr[0];
    }

    public static Context getApplication() {
        try {
            Object currentActivityThread = currentActivityThread();
            if (currentActivityThread != null) {
                return (Context) ReflectHelper.invokeInstanceMethod(currentActivityThread, Strings.getString(33), new Object[0]);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    public HashMap<String, Object> getCurrentWifiInfo() {
        try {
            if (checkPermission(Constants.PERMISSION_ACCESS_WIFI_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
                if (systemServiceSafe != null) {
                    Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
                    if (invokeInstanceMethod != null) {
                        HashMap hashMap = new HashMap();
                        try {
                            hashMap.put("bssid", (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(4), new Object[0]));
                        } catch (Throwable th) {
                        }
                        try {
                            hashMap.put("ssid", (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(3), new Object[0]));
                        } catch (Throwable th2) {
                        }
                        try {
                            hashMap.put("ip", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(79), new Object[0])).intValue()));
                        } catch (Throwable th3) {
                        }
                        try {
                            hashMap.put("wlanMac", (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(5), new Object[0]));
                        } catch (Throwable th4) {
                        }
                        try {
                            hashMap.put("hidden", Boolean.valueOf(((Boolean) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(80), new Object[0])).booleanValue()));
                        } catch (Throwable th5) {
                        }
                        try {
                            hashMap.put("speed", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(81), new Object[0])).intValue()));
                        } catch (Throwable th6) {
                        }
                        try {
                            hashMap.put("networkId", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(60), new Object[0])).intValue()));
                        } catch (Throwable th7) {
                        }
                        try {
                            hashMap.put("level", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(62), new Object[0])).intValue()));
                        } catch (Throwable th8) {
                        }
                        try {
                            hashMap.put("frequency", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(82), new Object[0])).intValue()));
                        } catch (Throwable th9) {
                        }
                        return hashMap;
                    }
                }
            }
        } catch (Throwable th10) {
            MobLog.getInstance().d(th10);
        }
        return null;
    }

    public ArrayList<HashMap<String, Object>> getAvailableWifiList() {
        String[] split;
        String[] split2;
        String str;
        if (checkPermission(Constants.PERMISSION_ACCESS_WIFI_STATE)) {
            Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
            if (systemServiceSafe == null) {
                return null;
            }
            List list = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(34), new Object[0]);
            if (list == null) {
                return null;
            }
            if (VERSION.SDK_INT > 27) {
                String[] split3 = TextUtils.split(Strings.getString(72), StorageInterface.KEY_SPLITER);
                split = TextUtils.split(Strings.getString(73), StorageInterface.KEY_SPLITER);
                split2 = split3;
            } else {
                split = TextUtils.split(Strings.getString(36), StorageInterface.KEY_SPLITER);
                split2 = TextUtils.split(Strings.getString(35), StorageInterface.KEY_SPLITER);
            }
            ArrayList arrayList = new ArrayList();
            for (Object next : list) {
                HashMap hashMap = new HashMap();
                int length = split2.length;
                int i = 0;
                String str2 = null;
                while (true) {
                    if (i >= length) {
                        str = str2;
                        break;
                    }
                    try {
                        String trim = split2[i].trim();
                        if ("SSID".equals(trim)) {
                            str = (String) ReflectHelper.getInstanceField(next, trim);
                            try {
                                if (!TextUtils.isEmpty(str)) {
                                    hashMap.put(trim, str);
                                    i++;
                                    str2 = str;
                                }
                            } catch (Throwable th) {
                            }
                        } else {
                            if ("capabilities".equals(trim)) {
                                String str3 = (String) ReflectHelper.getInstanceField(next, trim);
                                if (str3 != null && str3.contains("[IBSS]")) {
                                    str = null;
                                    break;
                                }
                                hashMap.put(trim, str3);
                                str = str2;
                            } else {
                                hashMap.put(trim, ReflectHelper.getInstanceField(next, trim));
                                str = str2;
                            }
                            i++;
                            str2 = str;
                        }
                    } catch (Throwable th2) {
                        str = str2;
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    for (String trim2 : split) {
                        try {
                            String trim3 = trim2.trim();
                            Object instanceField = ReflectHelper.getInstanceField(next, trim3);
                            hashMap.put(trim3, instanceField == null ? null : instanceField.toString());
                        } catch (Throwable th3) {
                        }
                    }
                    try {
                        hashMap.put(Strings.getString(39), ReflectHelper.invokeInstanceMethod(next, Strings.getString(37), new Object[0]));
                    } catch (Throwable th4) {
                    }
                    try {
                        if (VERSION.SDK_INT < 28) {
                            List list2 = (List) ReflectHelper.getInstanceField(next, Strings.getString(38));
                            hashMap.put(Strings.getString(38), list2 == null ? null : new ArrayList(list2));
                        }
                    } catch (Throwable th5) {
                    }
                    try {
                        arrayList.add(hashMap);
                    } catch (Throwable th6) {
                        MobLog.getInstance().w(th6);
                    }
                }
            }
            return arrayList;
        }
        return null;
    }

    private int getWifiSecurity(String str) {
        if (str != null) {
            if (str.contains("WEP")) {
                return 1;
            }
            if (str.contains("PSK")) {
                return 2;
            }
            if (str.contains("EAP")) {
                return 3;
            }
        }
        return 0;
    }

    public boolean scanWifiList() {
        try {
            if (checkPermission("android.permission.CHANGE_WIFI_STATE")) {
                Object systemServiceSafe = getSystemServiceSafe(NetworkUtil.NETWORK_WIFI);
                if (systemServiceSafe == null) {
                    return false;
                }
                return ((Boolean) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(40), new Object[0])).booleanValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return false;
    }

    public int getStatusBarHeight() {
        if (VERSION.SDK_INT < 28) {
            try {
                return this.context.getResources().getDimensionPixelSize(((Integer) ReflectHelper.getStaticField(ReflectHelper.importClass("com.android.internal.R$dimen"), "status_bar_height")).intValue());
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
        return -1;
    }

    public boolean isPackageInstalled(String str) {
        try {
            return this.context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public List<Object[]> getIntent(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        Uri parse = Uri.parse(str);
        intent.setData(parse);
        List<ResolveInfo> queryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentServices) {
            Intent intent2 = new Intent();
            intent2.setData(parse);
            intent2.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
            arrayList.add(new Object[]{resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name, intent2});
        }
        return arrayList;
    }

    public int ss(Context context2, Intent intent) throws Throwable {
        try {
            return context2.startService(intent) == null ? 0 : 1;
        } catch (SecurityException e) {
            MobLog.getInstance().d(e);
            return 2;
        }
    }

    public int bs(final Context context2, Intent intent) throws Throwable {
        final boolean[] zArr = {false};
        try {
            if (!context2.bindService(intent, new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        synchronized (zArr) {
                            zArr[0] = true;
                            zArr.notifyAll();
                            context2.unbindService(this);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    try {
                        synchronized (zArr) {
                            zArr.notifyAll();
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }
            }, 1)) {
                return 0;
            }
            long j = 200;
            while (!zArr[0] && j > 0) {
                synchronized (zArr) {
                    j -= 20;
                    zArr.wait(20);
                }
            }
            if (!zArr[0]) {
                return 2;
            }
            return 1;
        } catch (SecurityException e) {
            MobLog.getInstance().d(e);
            return 3;
        }
    }

    public int cs(Context context2, Intent intent) {
        boolean z;
        boolean z2;
        boolean z3;
        if (VERSION.SDK_INT >= 26) {
            return 4;
        }
        try {
            ComponentName component = intent.getComponent();
            String packageName = component.getPackageName();
            String className = component.getClassName();
            List runningServices = ((ActivityManager) context2.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME)).getRunningServices(1000);
            if (runningServices != null && !runningServices.isEmpty()) {
                Iterator it = runningServices.iterator();
                z2 = false;
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    RunningServiceInfo runningServiceInfo = (RunningServiceInfo) it.next();
                    String packageName2 = runningServiceInfo.service.getPackageName();
                    String className2 = runningServiceInfo.service.getClassName();
                    if (!packageName2.equals(packageName)) {
                        z3 = z2;
                    } else if (className2.equals(className)) {
                        z = true;
                        z2 = true;
                        break;
                    } else {
                        z3 = true;
                    }
                    z2 = z3;
                }
            } else {
                z = false;
                z2 = false;
            }
            if (!z2) {
                return 0;
            }
            if (z) {
                return 1;
            }
            return 2;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return 3;
        }
    }

    public HashMap<String, Object> getCPUInfo() {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(Strings.getString(41));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList arrayList = new ArrayList();
            hashMap.put("processors", arrayList);
            HashMap hashMap2 = null;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (TextUtils.isEmpty(readLine)) {
                    if (hashMap2 != null) {
                        arrayList.add(hashMap2);
                    }
                    hashMap2 = null;
                } else {
                    String trim = readLine.trim();
                    if (trim.startsWith("processor")) {
                        if (hashMap2 != null) {
                            arrayList.add(hashMap2);
                        }
                        hashMap2 = new HashMap();
                    }
                    String[] split = trim.split(Config.TRACE_TODAY_VISIT_SPLIT);
                    if (split != null && split.length > 1) {
                        if (hashMap2 == null) {
                            hashMap.put(split[0].trim(), split[1].trim());
                        } else {
                            hashMap2.put(split[0].trim(), split[1].trim());
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return hashMap;
    }

    public ArrayList<ArrayList<String>> getTTYDriversInfo() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(Strings.getString(52));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (!TextUtils.isEmpty(readLine)) {
                    String[] split = readLine.trim().split(" ");
                    if (split.length > 1) {
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : split) {
                            if (!TextUtils.isEmpty(str)) {
                                arrayList2.add(str.trim());
                            }
                        }
                        arrayList.add(arrayList2);
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return arrayList;
    }

    public void getBatteryState(final ReflectRunnable<HashMap<String, Object>, Void> reflectRunnable) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            this.context.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    HashMap hashMap = new HashMap();
                    for (String str : intent.getExtras().keySet()) {
                        hashMap.put(str, intent.getExtras().get(str));
                    }
                    if (reflectRunnable != null) {
                        reflectRunnable.run(hashMap);
                    }
                    try {
                        context.unregisterReceiver(this);
                    } catch (Throwable th) {
                    }
                }
            }, intentFilter);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            if (reflectRunnable != null) {
                reflectRunnable.run(null);
            }
        }
    }

    public int getScreenBrightness() {
        char c = 65535;
        try {
            return System.getInt(this.context.getContentResolver(), "screen_brightness");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return c;
        }
    }

    public int getScreenBrightnessMode() {
        char c = 65535;
        try {
            return System.getInt(this.context.getContentResolver(), "screen_brightness_mode");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return c;
        }
    }

    public String getQemuKernel() {
        String str = "0";
        try {
            return (String) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(9)), Strings.getString(10), Strings.getString(53), "0");
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return str;
        }
    }

    public HashMap<String, HashMap<String, Long>> getSizeInfo() {
        String[] strArr;
        long availableBlocksLong;
        long blockSizeLong;
        long blockCountLong;
        long blockSizeLong2;
        HashMap<String, HashMap<String, Long>> hashMap = new HashMap<>();
        for (String str : new String[]{"sdcard", "data"}) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("available", Long.valueOf(-1));
            hashMap2.put(Config.EXCEPTION_MEMORY_FREE, Long.valueOf(-1));
            hashMap2.put(Config.EXCEPTION_MEMORY_TOTAL, Long.valueOf(-1));
            hashMap.put(str, hashMap2);
        }
        HashMap hashMap3 = new HashMap();
        try {
            String sdcardPath = getSdcardPath();
            if (sdcardPath != null) {
                hashMap3.put("sdcard", new StatFs(sdcardPath));
            }
        } catch (Throwable th) {
        }
        try {
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory != null) {
                hashMap3.put("data", new StatFs(dataDirectory.getPath()));
            }
        } catch (Throwable th2) {
        }
        for (Entry entry : hashMap3.entrySet()) {
            StatFs statFs = (StatFs) entry.getValue();
            if (VERSION.SDK_INT <= 18) {
                availableBlocksLong = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
                blockSizeLong = ((long) statFs.getBlockSize()) * ((long) statFs.getFreeBlocks());
                blockCountLong = (long) statFs.getBlockCount();
                blockSizeLong2 = (long) statFs.getBlockSize();
            } else {
                availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
                blockSizeLong = statFs.getBlockSizeLong() * statFs.getFreeBlocksLong();
                blockCountLong = statFs.getBlockCountLong();
                blockSizeLong2 = statFs.getBlockSizeLong();
            }
            long j = blockCountLong * blockSizeLong2;
            HashMap hashMap4 = (HashMap) hashMap.get(entry.getKey());
            hashMap4.put("available", Long.valueOf(availableBlocksLong));
            hashMap4.put(Config.EXCEPTION_MEMORY_FREE, Long.valueOf(blockSizeLong));
            hashMap4.put(Config.EXCEPTION_MEMORY_TOTAL, Long.valueOf(j));
        }
        return hashMap;
    }

    public HashMap<String, Long> getMemoryInfo() {
        HashMap<String, Long> hashMap = new HashMap<>();
        hashMap.put("available", Long.valueOf(-1));
        hashMap.put(Config.EXCEPTION_MEMORY_TOTAL, Long.valueOf(-1));
        hashMap.put("isLow", Long.valueOf(-1));
        hashMap.put("threshold", Long.valueOf(-1));
        try {
            Object systemServiceSafe = getSystemServiceSafe(Strings.getString(30));
            MemoryInfo memoryInfo = new MemoryInfo();
            ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(64), memoryInfo);
            hashMap.put("available", Long.valueOf(memoryInfo.availMem));
            hashMap.put(Config.EXCEPTION_MEMORY_TOTAL, Long.valueOf(memoryInfo.totalMem));
            hashMap.put("isLow", Long.valueOf(memoryInfo.lowMemory ? 1 : 0));
            hashMap.put("threshold", Long.valueOf(memoryInfo.threshold));
        } catch (Throwable th) {
        }
        return hashMap;
    }

    public Bitmap getWallPaper() {
        int i;
        int i2 = 1;
        try {
            WallpaperManager instance = WallpaperManager.getInstance(this.context);
            Drawable peekDrawable = instance.peekDrawable();
            if (peekDrawable == null) {
                peekDrawable = instance.getWallpaperInfo().loadThumbnail(this.context.getPackageManager());
                if (peekDrawable == null) {
                    return null;
                }
            }
            if (peekDrawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) peekDrawable).getBitmap();
            }
            if (peekDrawable.getIntrinsicWidth() <= 0 || peekDrawable.getIntrinsicHeight() <= 0) {
                i = 1;
            } else {
                i = peekDrawable.getIntrinsicWidth();
                i2 = peekDrawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            peekDrawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            peekDrawable.draw(canvas);
            return createBitmap;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getMIUIVersion() {
        String systemProperties = getSystemProperties(Strings.getString(65));
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(66));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(67));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            return getSystemProperties(Strings.getString(69));
        }
        return systemProperties;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getWAbcd(int r10) {
        /*
            r9 = this;
            r1 = 0
            boolean r0 = r9.getSdcardState()     // Catch:{ Throwable -> 0x009e }
            if (r0 != 0) goto L_0x0009
            r0 = r1
        L_0x0008:
            return r0
        L_0x0009:
            java.lang.String r3 = r9.getSdcardPath()     // Catch:{ Throwable -> 0x009e }
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x009e }
            if (r0 == 0) goto L_0x0015
            r0 = r1
            goto L_0x0008
        L_0x0015:
            r0 = 75
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x009e }
            java.lang.String r2 = ","
            java.lang.String[] r4 = r0.split(r2)     // Catch:{ Throwable -> 0x009e }
            if (r4 == 0) goto L_0x00a6
            int r0 = r4.length     // Catch:{ Throwable -> 0x009e }
            if (r0 <= 0) goto L_0x00a6
            int r5 = r4.length     // Catch:{ Throwable -> 0x009e }
            r0 = 0
            r2 = r0
        L_0x0029:
            if (r2 >= r5) goto L_0x00a6
            r0 = r4[r2]     // Catch:{ Throwable -> 0x009e }
            if (r0 == 0) goto L_0x009a
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x009e }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x009e }
            if (r6 != 0) goto L_0x009a
            java.io.File r6 = new java.io.File     // Catch:{ Throwable -> 0x0092 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0092 }
            r7.<init>()     // Catch:{ Throwable -> 0x0092 }
            java.lang.StringBuilder r7 = r7.append(r3)     // Catch:{ Throwable -> 0x0092 }
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch:{ Throwable -> 0x0092 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0092 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0092 }
            r7.<init>()     // Catch:{ Throwable -> 0x0092 }
            java.lang.String r8 = ".mn_"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092 }
            java.lang.String r8 = r9.getFixedString()     // Catch:{ Throwable -> 0x0092 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0092 }
            r6.<init>(r0, r7)     // Catch:{ Throwable -> 0x0092 }
            boolean r0 = r6.exists()     // Catch:{ Throwable -> 0x0092 }
            if (r0 == 0) goto L_0x009a
            boolean r0 = r6.isFile()     // Catch:{ Throwable -> 0x0092 }
            if (r0 == 0) goto L_0x009a
            java.lang.String r0 = r6.getPath()     // Catch:{ Throwable -> 0x0092 }
            java.util.HashMap r0 = r9.getMapFromOtherPlace(r0)     // Catch:{ Throwable -> 0x0092 }
            if (r0 == 0) goto L_0x009a
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ Throwable -> 0x0092 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ Throwable -> 0x0092 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0092 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0092 }
            if (r6 != 0) goto L_0x009a
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0092 }
            goto L_0x0008
        L_0x0092:
            r0 = move-exception
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x009e }
            r6.d(r0)     // Catch:{ Throwable -> 0x009e }
        L_0x009a:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x0029
        L_0x009e:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
        L_0x00a6:
            r0 = r1
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getWAbcd(int):java.lang.String");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveWabcd(java.lang.String r9, int r10) {
        /*
            r8 = this;
            boolean r0 = r8.getSdcardState()     // Catch:{ Throwable -> 0x00d5 }
            if (r0 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.lang.String r2 = r8.getSdcardPath()     // Catch:{ Throwable -> 0x00d5 }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x00d5 }
            if (r0 != 0) goto L_0x0006
            r0 = 75
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x00d5 }
            java.lang.String r1 = ","
            java.lang.String[] r3 = r0.split(r1)     // Catch:{ Throwable -> 0x00d5 }
            if (r3 == 0) goto L_0x0006
            int r0 = r3.length     // Catch:{ Throwable -> 0x00d5 }
            if (r0 <= 0) goto L_0x0006
            int r4 = r3.length     // Catch:{ Throwable -> 0x00d5 }
            r0 = 0
            r1 = r0
        L_0x0025:
            if (r1 >= r4) goto L_0x0006
            r0 = r3[r1]     // Catch:{ Throwable -> 0x00d5 }
            if (r0 == 0) goto L_0x00c7
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x00d5 }
            boolean r5 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00d5 }
            if (r5 != 0) goto L_0x00c7
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cc }
            r6.<init>()     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = r6.append(r2)     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cc }
            r6.<init>()     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r7 = ".mn_"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r7 = r8.getFixedString()     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00cc }
            r5.<init>(r0, r6)     // Catch:{ Throwable -> 0x00cc }
            r0 = 0
            boolean r6 = r5.exists()     // Catch:{ Throwable -> 0x00cc }
            if (r6 == 0) goto L_0x0077
            boolean r6 = r5.isFile()     // Catch:{ Throwable -> 0x00cc }
            if (r6 == 0) goto L_0x0077
            java.lang.String r0 = r5.getPath()     // Catch:{ Throwable -> 0x00cc }
            java.util.HashMap r0 = r8.getMapFromOtherPlace(r0)     // Catch:{ Throwable -> 0x00cc }
        L_0x0077:
            if (r0 != 0) goto L_0x007e
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00cc }
            r0.<init>()     // Catch:{ Throwable -> 0x00cc }
        L_0x007e:
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ Throwable -> 0x00cc }
            r0.put(r6, r9)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r6 = r8.getSortWabcd(r0)     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cc }
            r7.<init>()     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ Throwable -> 0x00cc }
            r7 = 77
            java.lang.String r7 = com.mob.tools.utils.Strings.getString(r7)     // Catch:{ Throwable -> 0x00cc }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r6 = com.mob.tools.utils.Data.MD5(r6)     // Catch:{ Throwable -> 0x00cc }
            r7 = 78
            java.lang.String r7 = com.mob.tools.utils.Strings.getString(r7)     // Catch:{ Throwable -> 0x00cc }
            r0.put(r7, r6)     // Catch:{ Throwable -> 0x00cc }
            r6 = 76
            java.lang.String r6 = com.mob.tools.utils.Strings.getString(r6)     // Catch:{ Throwable -> 0x00cc }
            com.mob.tools.utils.Hashon r7 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x00cc }
            r7.<init>()     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r0 = r7.fromHashMap(r0)     // Catch:{ Throwable -> 0x00cc }
            byte[] r0 = com.mob.tools.utils.Data.AES128Encode(r6, r0)     // Catch:{ Throwable -> 0x00cc }
            java.lang.String r5 = r5.getPath()     // Catch:{ Throwable -> 0x00cc }
            com.mob.tools.utils.ResHelper.saveObjectToFile(r5, r0)     // Catch:{ Throwable -> 0x00cc }
        L_0x00c7:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0025
        L_0x00cc:
            r0 = move-exception
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00d5 }
            r5.d(r0)     // Catch:{ Throwable -> 0x00d5 }
            goto L_0x00c7
        L_0x00d5:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.saveWabcd(java.lang.String, int):void");
    }

    private HashMap<String, Object> getMapFromOtherPlace(String str) {
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(str));
            if (!TextUtils.isEmpty(AES128Decode)) {
                HashMap fromJson = new Hashon().fromJson(AES128Decode);
                String str2 = (String) fromJson.remove(Strings.getString(78));
                String MD5 = Data.MD5(getSortWabcd(fromJson) + Strings.getString(77));
                if (str2 == null || str2.equals(MD5)) {
                    return fromJson;
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    private String getSortWabcd(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str = (String) hashMap.get("0");
        if (str != null) {
            sb.append(str);
        }
        String str2 = (String) hashMap.get("1");
        if (str2 != null) {
            sb.append(str2);
        }
        String str3 = (String) hashMap.get("2");
        if (str3 != null) {
            sb.append(str3);
        }
        String str4 = (String) hashMap.get("3");
        if (str4 != null) {
            sb.append(str4);
        }
        return sb.toString();
    }

    public String getFixedString() {
        if (this.fixedString == null) {
            try {
                List<Sensor> sensorList = ((SensorManager) getSystemServiceSafe("sensor")).getSensorList(-1);
                StringBuilder sb = new StringBuilder();
                for (Sensor sensor : sensorList) {
                    if (sensor.getType() >= 0) {
                        sb.append(sensor.getType()).append('.').append(sensor.getName()).append('-').append(sensor.getVersion()).append("-").append(sensor.getVendor()).append(10);
                    }
                }
                this.fixedString = String.valueOf(sb.toString().hashCode());
            } catch (Throwable th) {
            }
        }
        return this.fixedString;
    }

    public ArrayList<HashMap<String, Object>> getArpList() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.trim();
                if (!trim.toUpperCase(Locale.US).contains("IP") && trim.length() >= 63) {
                    String trim2 = trim.substring(41, 63).trim();
                    if (!trim2.startsWith("00:00:00:00:00:00")) {
                        String trim3 = trim.substring(0, 17).trim();
                        String trim4 = trim.substring(29, 32).trim();
                        HashMap hashMap = new HashMap();
                        hashMap.put("ip", trim3);
                        hashMap.put("flag", trim4);
                        hashMap.put(MidEntity.TAG_MAC, trim2);
                        arrayList.add(hashMap);
                    }
                }
            }
            bufferedReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        com.mob.tools.MobLog.getInstance().d(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2 = new java.io.BufferedReader(new java.io.FileReader("/proc/" + android.os.Process.myPid() + "/maps"));
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
        r3 = r2.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0086, code lost:
        if (r3 == null) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008a, code lost:
        r0 = r3.toLowerCase().contains("xposed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0095, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0096, code lost:
        com.mob.tools.MobLog.getInstance().d(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[ExcHandler: Throwable (r0v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:14:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cx() {
        /*
            r7 = this;
            r1 = 0
            r0 = 1
            android.content.Context r2 = r7.context     // Catch:{ Throwable -> 0x0012 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Throwable -> 0x0012 }
            java.lang.String r3 = "de.robv.android.xposed.installer"
            r4 = 0
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r4)     // Catch:{ Throwable -> 0x0012 }
            if (r2 == 0) goto L_0x0013
        L_0x0011:
            return r0
        L_0x0012:
            r2 = move-exception
        L_0x0013:
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Throwable -> 0x001b }
            java.lang.String r3 = "test"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x001b }
            throw r2     // Catch:{ Throwable -> 0x001b }
        L_0x001b:
            r2 = move-exception
            java.lang.StackTraceElement[] r3 = r2.getStackTrace()
            int r4 = r3.length
            r2 = r1
        L_0x0022:
            if (r2 >= r4) goto L_0x0035
            r5 = r3[r2]
            java.lang.String r5 = r5.getClassName()
            java.lang.String r6 = "de.robv.android.xposed.XposedBridge"
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x0011
            int r2 = r2 + 1
            goto L_0x0022
        L_0x0035:
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ InstantiationException -> 0x00a3, IllegalAccessException -> 0x00a0, Throwable -> 0x0052 }
            java.lang.String r3 = "de.robv.android.xposed.XposedHelpers"
            java.lang.Class r2 = r2.loadClass(r3)     // Catch:{ InstantiationException -> 0x00a3, IllegalAccessException -> 0x00a0, Throwable -> 0x0052 }
            r2.newInstance()     // Catch:{ InstantiationException -> 0x00a3, IllegalAccessException -> 0x00a0, Throwable -> 0x0052 }
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ InstantiationException -> 0x0050, IllegalAccessException -> 0x00a6, Throwable -> 0x0052 }
            java.lang.String r3 = "de.robv.android.xposed.XposedBridge"
            java.lang.Class r2 = r2.loadClass(r3)     // Catch:{ InstantiationException -> 0x0050, IllegalAccessException -> 0x00a6, Throwable -> 0x0052 }
            r2.newInstance()     // Catch:{ InstantiationException -> 0x0050, IllegalAccessException -> 0x00a6, Throwable -> 0x0052 }
            goto L_0x0011
        L_0x0050:
            r1 = move-exception
            goto L_0x0011
        L_0x0052:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0095 }
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Throwable -> 0x0095 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0095 }
            r3.<init>()     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r4 = "/proc/"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x0095 }
            int r4 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0095 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r4 = "/maps"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x0095 }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x0095 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0095 }
            r0 = r1
        L_0x0082:
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0095 }
            if (r3 == 0) goto L_0x0011
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = r3.toLowerCase()     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r3 = "xposed"
            boolean r0 = r0.contains(r3)     // Catch:{ Throwable -> 0x0095 }
            goto L_0x0082
        L_0x0095:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
            r0 = r1
            goto L_0x0011
        L_0x00a0:
            r1 = move-exception
            goto L_0x0011
        L_0x00a3:
            r1 = move-exception
            goto L_0x0011
        L_0x00a6:
            r1 = move-exception
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.cx():boolean");
    }

    public HashMap<String, Object> getIInfo() {
        try {
            Object systemServiceSafe = getSystemServiceSafe("phone");
            if (systemServiceSafe == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(Strings.getString(83), invokeInstanceMethod(systemServiceSafe, Strings.getString(84), new Object[0]));
            hashMap.put(Strings.getString(85), invokeInstanceMethod(systemServiceSafe, Strings.getString(86), new Object[0]));
            hashMap.put(Strings.getString(87), invokeInstanceMethod(systemServiceSafe, Strings.getString(88), new Object[0]));
            if (VERSION.SDK_INT >= 23) {
                hashMap.put(Strings.getString(89), invokeInstanceMethod(systemServiceSafe, Strings.getString(90), new Object[0]));
            }
            if (!checkPermission(Constants.PERMISSION_READ_PHONE_STATE)) {
                return hashMap;
            }
            if (VERSION.SDK_INT >= 26) {
                hashMap.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(92), new Object[0]));
                hashMap.put(Strings.getString(93), invokeInstanceMethod(systemServiceSafe, Strings.getString(94), new Object[0]));
            } else {
                hashMap.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), new Object[0]));
            }
            hashMap.put(Strings.getString(96), invokeInstanceMethod(systemServiceSafe, Strings.getString(25), new Object[0]));
            hashMap.put(Strings.getString(97), invokeInstanceMethod(systemServiceSafe, Strings.getString(98), new Object[0]));
            hashMap.put(Strings.getString(99), invokeInstanceMethod(systemServiceSafe, Strings.getString(14), new Object[0]));
            if (VERSION.SDK_INT >= 24) {
                hashMap.put(Strings.getString(100), invokeInstanceMethod(systemServiceSafe, Strings.getString(101), new Object[0]));
            }
            if (VERSION.SDK_INT >= 22) {
                Object systemServiceSafe2 = getSystemServiceSafe(Strings.getString(114));
                hashMap.put(Strings.getString(102), invokeInstanceMethod(systemServiceSafe2, Strings.getString(103), new Object[0]));
                List list = (List) invokeInstanceMethod(systemServiceSafe2, Strings.getString(104), new Object[0]);
                if (list != null) {
                    int size = list.size();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < size; i++) {
                        Object obj = list.get(i);
                        HashMap hashMap2 = new HashMap();
                        int intValue = ((Integer) invokeInstanceMethod(obj, Strings.getString(105), new Object[0])).intValue();
                        hashMap2.put(Strings.getString(106), invokeInstanceMethod(obj, Strings.getString(107), new Object[0]));
                        hashMap2.put(Strings.getString(108), invokeInstanceMethod(obj, Strings.getString(109), new Object[0]));
                        hashMap2.put(Strings.getString(110), invokeInstanceMethod(obj, Strings.getString(111), new Object[0]));
                        int intValue2 = ((Integer) invokeInstanceMethod(obj, Strings.getString(112), new Object[0])).intValue();
                        hashMap2.put(Strings.getString(83), invokeInstanceMethod(systemServiceSafe, Strings.getString(84), Integer.valueOf(intValue2)));
                        if (VERSION.SDK_INT >= 26) {
                            hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(92), Integer.valueOf(intValue2)));
                            hashMap2.put(Strings.getString(93), invokeInstanceMethod(systemServiceSafe, Strings.getString(94), Integer.valueOf(intValue2)));
                        } else if (VERSION.SDK_INT >= 23) {
                            hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), Integer.valueOf(intValue2)));
                        } else {
                            hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), new Object[0]));
                        }
                        hashMap2.put(Strings.getString(96), invokeInstanceMethod(systemServiceSafe, Strings.getString(25), Integer.valueOf(intValue)));
                        hashMap2.put(Strings.getString(99), invokeInstanceMethod(systemServiceSafe, Strings.getString(14), Integer.valueOf(intValue)));
                        arrayList.add(hashMap2);
                    }
                    hashMap.put(Strings.getString(113), arrayList);
                }
            }
            return hashMap;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) {
        try {
            return ReflectHelper.invokeInstanceMethod(obj, str, objArr);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public boolean checkPad() {
        return (this.context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public boolean checkADBModel(int i) {
        boolean z = true;
        switch (i) {
            case 1:
                return usbEnable();
            case 16:
                return devEnable();
            case 17:
                if (usbEnable() || devEnable()) {
                    return true;
                }
                return false;
            case BaseQuickAdapter.HEADER_VIEW /*273*/:
                if (!usbEnable() || !devEnable()) {
                    z = false;
                }
                return z;
            default:
                return false;
        }
    }

    public boolean usbEnable() {
        try {
            if (VERSION.SDK_INT >= 17) {
                if (Secure.getInt(this.context.getContentResolver(), "adb_enabled", 0) > 0) {
                    return true;
                }
                return false;
            } else if (Secure.getInt(this.context.getContentResolver(), "adb_enabled", 0) <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    public boolean devEnable() {
        try {
            if (VERSION.SDK_INT >= 17) {
                if (Secure.getInt(this.context.getContentResolver(), "development_settings_enabled", 0) > 0) {
                    return true;
                }
                return false;
            } else if (Secure.getInt(this.context.getContentResolver(), "development_settings_enabled", 0) <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    public boolean checkUA() {
        try {
            if (this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }

    public boolean isScopedStorage() {
        boolean z;
        boolean z2;
        if (VERSION.SDK_INT >= 29) {
            z = true;
        } else {
            z = false;
        }
        if (this.context.getApplicationInfo().targetSdkVersion >= 29) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
