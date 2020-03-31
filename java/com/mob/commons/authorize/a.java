package com.mob.commons.authorize;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.LockAction;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.d;
import com.mob.commons.g;
import com.mob.commons.h;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.mid.api.MidEntity;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

/* compiled from: Authorizer */
public final class a {
    /* access modifiers changed from: private */
    public static final String a = h.a("devs.data.mob.com");

    /* access modifiers changed from: 0000 */
    public final String a(final MobProduct mobProduct) {
        final String[] strArr = new String[1];
        d.a(d.a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                strArr[0] = a.this.a(mobProduct, a.this.i());
                return false;
            }
        });
        return strArr[0];
    }

    private File c() {
        return ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.duid");
    }

    private File d() {
        File file = new File(ResHelper.getDataCache(MobSDK.getContext()), "comm/dbs/.duid");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /* access modifiers changed from: private */
    public synchronized String a(MobProduct mobProduct, boolean z) {
        String f;
        boolean z2 = true;
        synchronized (this) {
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            boolean sdcardState = instance.getSdcardState();
            HashMap e = e();
            if (e == null || e.size() <= 0) {
                f = f();
                a(f, mobProduct);
            } else {
                f = (String) e.get("duid");
                if (f == null) {
                    f = f();
                    e.put("duid", f);
                } else {
                    if (sdcardState) {
                        String wAbcd = instance.getWAbcd(0);
                        if (TextUtils.isEmpty(wAbcd)) {
                            instance.saveWabcd(f, 0);
                            z2 = false;
                        } else if (!f.equals(wAbcd)) {
                            if (z) {
                                e.put("duid", wAbcd);
                                f = wAbcd;
                            } else {
                                instance.saveWabcd(f, 0);
                            }
                        }
                    }
                    z2 = false;
                }
                if (z2) {
                    a(e, true);
                }
                a(e, z2, mobProduct);
            }
        }
        return f;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032 A[SYNTHETIC, Splitter:B:18:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003e A[SYNTHETIC, Splitter:B:26:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> e() {
        /*
            r4 = this;
            r1 = 0
            java.io.File r0 = r4.c()     // Catch:{ Throwable -> 0x0042 }
            boolean r2 = r0.exists()     // Catch:{ Throwable -> 0x0042 }
            if (r2 == 0) goto L_0x004a
            boolean r2 = r0.isFile()     // Catch:{ Throwable -> 0x0042 }
            if (r2 == 0) goto L_0x004a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0027, all -> 0x003a }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0027, all -> 0x003a }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0027, all -> 0x003a }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0027, all -> 0x003a }
            java.lang.Object r0 = r2.readObject()     // Catch:{ Throwable -> 0x0052 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0052 }
            if (r2 == 0) goto L_0x0026
            r2.close()     // Catch:{ Throwable -> 0x004c }
        L_0x0026:
            return r0
        L_0x0027:
            r0 = move-exception
            r2 = r1
        L_0x0029:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0050 }
            r3.w(r0)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x004a
            r2.close()     // Catch:{ Throwable -> 0x0037 }
            r0 = r1
            goto L_0x0026
        L_0x0037:
            r0 = move-exception
            r0 = r1
            goto L_0x0026
        L_0x003a:
            r0 = move-exception
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ Throwable -> 0x004e }
        L_0x0041:
            throw r0     // Catch:{ Throwable -> 0x0042 }
        L_0x0042:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.w(r0)
        L_0x004a:
            r0 = r1
            goto L_0x0026
        L_0x004c:
            r1 = move-exception
            goto L_0x0026
        L_0x004e:
            r2 = move-exception
            goto L_0x0041
        L_0x0050:
            r0 = move-exception
            goto L_0x003c
        L_0x0052:
            r0 = move-exception
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.a.e():java.util.HashMap");
    }

    private String f() {
        if (DeviceAuthorizer.a != null) {
            return DeviceAuthorizer.a;
        }
        String g = g();
        if (g == null) {
            return a(true);
        }
        return g;
    }

    private String g() {
        try {
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            boolean sdcardState = instance.getSdcardState();
            String wAbcd = instance.getWAbcd(0);
            if (!TextUtils.isEmpty(wAbcd)) {
                return wAbcd;
            }
            if (com.mob.commons.a.I()) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("plat", Integer.valueOf(1));
            hashMap.put(MidEntity.TAG_IMEI, instance.getIMEI());
            hashMap.put("serialno", instance.getSerialno());
            hashMap.put(MidEntity.TAG_MAC, instance.getMacAddress());
            hashMap.put("model", instance.getModel());
            HashMap hashMap2 = (HashMap) new MobCommunicator(1024, "ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", "191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd").requestSynchronized(hashMap, a + "/dgen", false);
            String wAbcd2 = instance.getWAbcd(0);
            if (!TextUtils.isEmpty(wAbcd2)) {
                return wAbcd2;
            }
            Object obj = hashMap2.get("duid");
            if (obj != null) {
                if (sdcardState) {
                    instance.saveWabcd(String.valueOf(obj), 0);
                }
                return String.valueOf(obj);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    /* access modifiers changed from: private */
    public String a(boolean z) {
        String byteToHex;
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        try {
            boolean sdcardState = instance.getSdcardState();
            String wAbcd = instance.getWAbcd(0);
            if (!TextUtils.isEmpty(wAbcd)) {
                return wAbcd;
            }
            String model = instance.getModel();
            String imei = instance.getIMEI();
            String macAddress = instance.getMacAddress();
            String serialno = instance.getSerialno();
            if (!TextUtils.isEmpty(imei) || !TextUtils.isEmpty(macAddress) || !TextUtils.isEmpty(serialno)) {
                byteToHex = Data.byteToHex(Data.SHA1(model + Config.TRACE_TODAY_VISIT_SPLIT + imei + Config.TRACE_TODAY_VISIT_SPLIT + macAddress + Config.TRACE_TODAY_VISIT_SPLIT + serialno));
            } else {
                byteToHex = UUID.randomUUID().toString();
            }
            if (!z || !sdcardState) {
                return byteToHex;
            }
            instance.saveWabcd(byteToHex, 0);
            return byteToHex;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private void a(final HashMap<String, Object> hashMap, final boolean z, final MobProduct mobProduct) {
        new Thread() {
            public void run() {
                synchronized (a.a) {
                    try {
                        boolean z = z;
                        if (a.this.a(hashMap) || a.this.j()) {
                            a.this.b(hashMap);
                            a.this.a(hashMap, (String) hashMap.get("duid"));
                            z = true;
                        }
                        if (a.this.a(hashMap, mobProduct)) {
                            z = true;
                        }
                        if (z) {
                            a.this.c(hashMap);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public boolean a(HashMap<String, Object> hashMap) {
        boolean z;
        HashMap hashMap2 = (HashMap) hashMap.get("deviceInfo");
        if (hashMap2 == null) {
            return true;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        Object obj = hashMap.get("duid");
        String n = g.n();
        if (TextUtils.isEmpty(n) && obj != null) {
            g.i(String.valueOf(obj));
        }
        if (!TextUtils.isEmpty(n) && obj != null && !n.equals(obj)) {
            return true;
        }
        Object obj2 = hashMap2.get("adsid");
        String str = null;
        try {
            str = instance.getAdvertisingID();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        if (str != null && !str.equals(obj2)) {
            return true;
        }
        Object obj3 = hashMap2.get("phoneno");
        String ln = instance.getLN();
        if (ln != null && !ln.equals(obj3)) {
            return true;
        }
        Object obj4 = hashMap2.get("simserialno");
        String simSerialNumber = instance.getSimSerialNumber();
        if (simSerialNumber != null && !simSerialNumber.equals(obj4)) {
            return true;
        }
        Object obj5 = hashMap2.get(MidEntity.TAG_IMEI);
        String imei = instance.getIMEI();
        if (imei != null && !imei.equals(obj5)) {
            return true;
        }
        Object obj6 = hashMap2.get("serialno");
        String serialno = instance.getSerialno();
        if (serialno != null && !serialno.equals(obj6)) {
            return true;
        }
        Object obj7 = hashMap2.get(MidEntity.TAG_MAC);
        String macAddress = instance.getMacAddress();
        if (macAddress != null && !macAddress.equals(obj7)) {
            return true;
        }
        Object obj8 = hashMap2.get("model");
        String model = instance.getModel();
        if (model != null && !model.equals(obj8)) {
            return true;
        }
        Object obj9 = hashMap2.get("factory");
        String manufacturer = instance.getManufacturer();
        if (manufacturer != null && !manufacturer.equals(obj9)) {
            return true;
        }
        Object obj10 = hashMap2.get("carrier");
        String carrier = instance.getCarrier();
        if (carrier != null && !carrier.equals(obj10)) {
            return true;
        }
        Object obj11 = hashMap2.get(MidEntity.TAG_IMSI);
        String imsi = instance.getIMSI();
        if (imsi != null && !imsi.equals(obj11)) {
            return true;
        }
        Object obj12 = hashMap2.get("imsiArray");
        String[] queryIMSI = instance.queryIMSI();
        if (queryIMSI != null && queryIMSI.length > 0) {
            if (obj12 == null) {
                return true;
            }
            try {
                ArrayList arrayList = (ArrayList) obj12;
                if (arrayList.size() != queryIMSI.length) {
                    return true;
                }
                boolean z2 = false;
                for (String str2 : queryIMSI) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (str2.equals((String) it.next())) {
                                z2 = false;
                                break;
                            }
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (z2) {
                    return true;
                }
            } catch (Throwable th2) {
            }
        }
        Object obj13 = hashMap2.get("androidids");
        if (obj13 == 0) {
            Object obj14 = hashMap2.get("androidid");
            if (obj14 != 0) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(obj14);
                obj13 = arrayList2;
            } else {
                obj13 = obj14;
            }
        }
        if (obj13 == 0 || !(obj13 instanceof ArrayList)) {
            return true;
        }
        String androidID = instance.getAndroidID();
        Iterator it2 = ((ArrayList) obj13).iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            Object next = it2.next();
            if (next != null && next.equals(androidID)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return true;
        }
        Object obj15 = hashMap2.get("sysver");
        String oSVersionName = instance.getOSVersionName();
        if (oSVersionName != null && !oSVersionName.equals(obj15)) {
            return true;
        }
        Object obj16 = hashMap2.get(Config.EVENT_HEAT_XP);
        int i = instance.cx() ? 1 : 0;
        if (obj16 == null || !String.valueOf(i).equals(String.valueOf(obj16))) {
            return true;
        }
        Object obj17 = hashMap2.get("breaked");
        boolean isRooted = instance.isRooted();
        if (obj17 == null && isRooted) {
            return true;
        }
        if (obj17 == null || String.valueOf(obj17).equals(String.valueOf(isRooted))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0185 A[Catch:{ Throwable -> 0x012f, Throwable -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0197 A[Catch:{ Throwable -> 0x012f, Throwable -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01db A[Catch:{ Throwable -> 0x012f, Throwable -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x014a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.util.HashMap<java.lang.String, java.lang.Object> r12) {
        /*
            r11 = this;
            r3 = 1
            r2 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0139 }
            com.mob.tools.utils.DeviceHelper r6 = com.mob.tools.utils.DeviceHelper.getInstance(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = "deviceInfo"
            java.lang.Object r0 = r12.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x02a7
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0139 }
            r0.<init>()     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = "deviceInfo"
            r12.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
            r5 = r0
        L_0x001f:
            java.lang.String r0 = "phoneno"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getLN()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x0036
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x0036
            java.lang.String r0 = "phoneno"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x0036:
            java.lang.String r0 = "simserialno"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getSimSerialNumber()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x004d
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x004d
            java.lang.String r0 = "simserialno"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x004d:
            java.lang.String r0 = "adsid"
            java.lang.Object r1 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            r0 = 0
            java.lang.String r0 = r6.getAdvertisingID()     // Catch:{ Throwable -> 0x012f }
        L_0x0058:
            if (r0 == 0) goto L_0x0065
            boolean r1 = r0.equals(r1)     // Catch:{ Throwable -> 0x0139 }
            if (r1 != 0) goto L_0x0065
            java.lang.String r1 = "adsid"
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
        L_0x0065:
            java.lang.String r0 = "imei"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getIMEI()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x007c
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x007c
            java.lang.String r0 = "imei"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x007c:
            java.lang.String r0 = "serialno"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getSerialno()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x0093
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x0093
            java.lang.String r0 = "serialno"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x0093:
            java.lang.String r0 = "mac"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getMacAddress()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x00aa
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x00aa
            java.lang.String r0 = "mac"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x00aa:
            java.lang.String r0 = "model"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getModel()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x00c1
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x00c1
            java.lang.String r0 = "model"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x00c1:
            java.lang.String r0 = "factory"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getManufacturer()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x00d8
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x00d8
            java.lang.String r0 = "factory"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x00d8:
            java.lang.String r0 = "imsi"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getIMSI()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x00ef
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x00ef
            java.lang.String r0 = "imsi"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x00ef:
            java.lang.String r0 = "imsiArray"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String[] r7 = r6.queryIMSI()     // Catch:{ Throwable -> 0x0139 }
            if (r7 == 0) goto L_0x014f
            int r1 = r7.length     // Catch:{ Throwable -> 0x0139 }
            if (r1 <= 0) goto L_0x014f
            if (r0 != 0) goto L_0x0142
            r1 = r3
        L_0x0101:
            if (r1 != 0) goto L_0x0148
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x0263 }
            int r4 = r0.size()     // Catch:{ Throwable -> 0x0263 }
            int r1 = r7.length     // Catch:{ Throwable -> 0x0263 }
            if (r4 == r1) goto L_0x0144
            r1 = r3
        L_0x010d:
            if (r1 != 0) goto L_0x0146
            int r8 = r7.length     // Catch:{ Throwable -> 0x0263 }
            r4 = r2
        L_0x0111:
            if (r4 >= r8) goto L_0x0146
            r9 = r7[r4]     // Catch:{ Throwable -> 0x0263 }
            java.util.Iterator r10 = r0.iterator()     // Catch:{ Throwable -> 0x029c }
        L_0x0119:
            boolean r1 = r10.hasNext()     // Catch:{ Throwable -> 0x029c }
            if (r1 == 0) goto L_0x02a4
            java.lang.Object r1 = r10.next()     // Catch:{ Throwable -> 0x029c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x029c }
            boolean r1 = r9.equals(r1)     // Catch:{ Throwable -> 0x029c }
            if (r1 == 0) goto L_0x0119
            r1 = r2
        L_0x012c:
            int r4 = r4 + 1
            goto L_0x0111
        L_0x012f:
            r4 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0139 }
            r7.w(r4)     // Catch:{ Throwable -> 0x0139 }
            goto L_0x0058
        L_0x0139:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
        L_0x0141:
            return
        L_0x0142:
            r1 = r2
            goto L_0x0101
        L_0x0144:
            r1 = r2
            goto L_0x010d
        L_0x0146:
            r0 = r1
            r1 = r0
        L_0x0148:
            if (r1 == 0) goto L_0x014f
            java.lang.String r0 = "imsiArray"
            r5.put(r0, r7)     // Catch:{ Throwable -> 0x0139 }
        L_0x014f:
            java.lang.String r0 = "sysver"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getOSVersionName()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x0166
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x0166
            java.lang.String r0 = "sysver"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x0166:
            java.lang.String r0 = "carrier"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getCarrier()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x017d
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x017d
            java.lang.String r0 = "carrier"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x017d:
            java.lang.String r0 = "androidids"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x0195
            java.lang.String r0 = "androidid"
            java.lang.Object r1 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x02a1
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0139 }
            r0.<init>()     // Catch:{ Throwable -> 0x0139 }
            r0.add(r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x0195:
            if (r0 == 0) goto L_0x019b
            boolean r1 = r0 instanceof java.util.ArrayList     // Catch:{ Throwable -> 0x0139 }
            if (r1 != 0) goto L_0x026d
        L_0x019b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0139 }
            r0.<init>()     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = r6.getAndroidID()     // Catch:{ Throwable -> 0x0139 }
            r0.add(r1)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = "androidids"
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
        L_0x01ac:
            java.lang.String r0 = "breaked"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            boolean r1 = r6.isRooted()     // Catch:{ Throwable -> 0x0139 }
            if (r0 == 0) goto L_0x01c6
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0139 }
            boolean r0 = r0.equals(r4)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x01cf
        L_0x01c6:
            java.lang.String r0 = "breaked"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0139 }
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x01cf:
            java.lang.String r0 = "xp"
            java.lang.Object r1 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            boolean r0 = r6.cx()     // Catch:{ Throwable -> 0x0139 }
            if (r0 == 0) goto L_0x0296
            r0 = r3
        L_0x01dc:
            if (r1 == 0) goto L_0x01ec
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0139 }
            boolean r1 = r4.equals(r1)     // Catch:{ Throwable -> 0x0139 }
            if (r1 != 0) goto L_0x01f5
        L_0x01ec:
            java.lang.String r1 = "xp"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0139 }
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
        L_0x01f5:
            java.lang.String r0 = "pad"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Throwable -> 0x0139 }
            boolean r1 = r6.checkPad()     // Catch:{ Throwable -> 0x0139 }
            if (r1 == 0) goto L_0x0299
        L_0x0201:
            if (r0 == 0) goto L_0x0211
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0139 }
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x021a
        L_0x0211:
            java.lang.String r0 = "pad"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0139 }
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x021a:
            java.util.HashMap r0 = r6.getIInfo()     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = "sims"
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = "duid"
            java.lang.Object r0 = r12.get(r0)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = com.mob.commons.g.n()     // Catch:{ Throwable -> 0x0139 }
            if (r0 == 0) goto L_0x0240
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0139 }
            if (r2 != 0) goto L_0x0240
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0139 }
            if (r0 != 0) goto L_0x0240
            java.lang.String r0 = "lduid"
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
        L_0x0240:
            java.lang.String r0 = "plat"
            r1 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0139 }
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = "screensize"
            java.lang.String r1 = r6.getScreenSize()     // Catch:{ Throwable -> 0x0139 }
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = "deviceType"
            java.lang.String r1 = r6.getDeviceType()     // Catch:{ Throwable -> 0x0139 }
            r5.put(r0, r1)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r0 = "deviceInfo"
            r12.put(r0, r5)     // Catch:{ Throwable -> 0x0139 }
            goto L_0x0141
        L_0x0263:
            r0 = move-exception
        L_0x0264:
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0139 }
            r4.d(r0)     // Catch:{ Throwable -> 0x0139 }
            goto L_0x0148
        L_0x026d:
            java.lang.String r4 = r6.getAndroidID()     // Catch:{ Throwable -> 0x0139 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x0139 }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Throwable -> 0x0139 }
        L_0x0277:
            boolean r7 = r1.hasNext()     // Catch:{ Throwable -> 0x0139 }
            if (r7 == 0) goto L_0x029f
            java.lang.Object r7 = r1.next()     // Catch:{ Throwable -> 0x0139 }
            if (r7 == 0) goto L_0x0277
            boolean r7 = r7.equals(r4)     // Catch:{ Throwable -> 0x0139 }
            if (r7 == 0) goto L_0x0277
            r1 = r3
        L_0x028a:
            if (r1 != 0) goto L_0x01ac
            r0.add(r4)     // Catch:{ Throwable -> 0x0139 }
            java.lang.String r1 = "androidids"
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x0139 }
            goto L_0x01ac
        L_0x0296:
            r0 = r2
            goto L_0x01dc
        L_0x0299:
            r3 = r2
            goto L_0x0201
        L_0x029c:
            r0 = move-exception
            r1 = r3
            goto L_0x0264
        L_0x029f:
            r1 = r2
            goto L_0x028a
        L_0x02a1:
            r0 = r1
            goto L_0x0195
        L_0x02a4:
            r1 = r3
            goto L_0x012c
        L_0x02a7:
            r5 = r0
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.a.b(java.util.HashMap):void");
    }

    private String h() {
        StringBuilder sb = new StringBuilder();
        sb.append(Config.FEED_LIST_MAPPING);
        sb.append("k.");
        sb.append("co");
        sb.append("mm");
        sb.append("on");
        sb.append("ap");
        sb.append(".s");
        sb.append("dk");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void a(HashMap<String, Object> hashMap, String str) {
        try {
            if (!com.mob.commons.a.I()) {
                HashMap hashMap2 = (HashMap) hashMap.get("deviceInfo");
                HashMap hashMap3 = new HashMap();
                for (Entry entry : hashMap2.entrySet()) {
                    hashMap3.put(entry.getKey(), entry.getValue());
                }
                try {
                    hashMap3.put("carrier", Integer.valueOf(ResHelper.parseInt(String.valueOf(hashMap3.get("carrier")))));
                } catch (Throwable th) {
                }
                ArrayList arrayList = (ArrayList) hashMap3.remove("androidids");
                if (arrayList != null && !arrayList.isEmpty()) {
                    hashMap3.put("androidid", arrayList.get(arrayList.size() - 1));
                }
                DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                hashMap3.put("duid", str);
                String[] queryIMEI = instance.queryIMEI();
                if (queryIMEI != null && queryIMEI.length > 0) {
                    hashMap3.put("imeiArray", queryIMEI);
                }
                try {
                    HashMap listNetworkHardware = instance.listNetworkHardware();
                    if (listNetworkHardware != null && !listNetworkHardware.isEmpty()) {
                        ArrayList arrayList2 = new ArrayList();
                        for (Entry entry2 : listNetworkHardware.entrySet()) {
                            HashMap hashMap4 = new HashMap();
                            hashMap4.put("ss", entry2.getKey());
                            hashMap4.put(MidEntity.TAG_MAC, entry2.getValue());
                            arrayList2.add(hashMap4);
                        }
                        hashMap3.put("macArray", arrayList2);
                    }
                } catch (Throwable th2) {
                }
                HashMap memoryInfo = instance.getMemoryInfo();
                HashMap sizeInfo = instance.getSizeInfo();
                if (memoryInfo != null) {
                    hashMap3.put("ram", memoryInfo.get(Config.EXCEPTION_MEMORY_TOTAL));
                }
                if (sizeInfo != null) {
                    HashMap hashMap5 = (HashMap) sizeInfo.get("sdcard");
                    if (hashMap5 != null) {
                        hashMap3.put("sdcardStorage", hashMap5.get(Config.EXCEPTION_MEMORY_TOTAL));
                    }
                    HashMap hashMap6 = (HashMap) sizeInfo.get("data");
                    if (hashMap6 != null) {
                        hashMap3.put("dataStorage", hashMap6.get(Config.EXCEPTION_MEMORY_TOTAL));
                    }
                }
                hashMap3.put("romImg", instance.getMIUIVersion());
                Hashon hashon = new Hashon();
                String encodeToString = Base64.encodeToString(Data.AES128Encode(h(), hashon.fromHashMap(hashMap3)), 2);
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(new KVPair(Config.MODEL, encodeToString));
                NetworkTimeOut networkTimeOut = new NetworkTimeOut();
                networkTimeOut.readTimout = Config.SESSION_PERIOD;
                networkTimeOut.connectionTimeout = Config.SESSION_PERIOD;
                NetworkHelper networkHelper = new NetworkHelper();
                String str2 = a + "/dinfo";
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
                if ("200".equals(String.valueOf(hashon.fromJson(networkHelper.httpPost(str2, arrayList3, null, arrayList4, networkTimeOut)).get(NotificationCompat.CATEGORY_STATUS)))) {
                    g.i(str);
                    a(com.mob.commons.a.a() + com.mob.commons.a.L());
                }
            }
        } catch (Throwable th3) {
            MobLog.getInstance().w(th3);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(HashMap<String, Object> hashMap, MobProduct mobProduct) {
        HashMap hashMap2;
        if (mobProduct == null) {
            mobProduct = new MobProduct() {
                public String getProductTag() {
                    return "COMMON";
                }

                public int getSdkver() {
                    return MobSDK.SDK_VERSION_CODE;
                }
            };
        }
        boolean z = false;
        try {
            HashMap hashMap3 = (HashMap) hashMap.get("appInfo");
            if (hashMap3 == null) {
                HashMap hashMap4 = new HashMap();
                hashMap.put("appInfo", hashMap4);
                hashMap2 = hashMap4;
                z = true;
            } else {
                hashMap2 = hashMap3;
            }
            String packageName = DeviceHelper.getInstance(MobSDK.getContext()).getPackageName();
            HashMap hashMap5 = (HashMap) hashMap2.get(packageName);
            if (hashMap5 == null) {
                hashMap5 = new HashMap();
                hashMap2.put(packageName, hashMap5);
                z = true;
            }
            String str = (String) hashMap5.get(mobProduct.getProductTag());
            String appkey = MobSDK.getAppkey();
            if ((str == null || !str.equals(appkey)) && a(mobProduct, hashMap)) {
                return true;
            }
            return z;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    private boolean a(MobProduct mobProduct, HashMap<String, Object> hashMap) throws Throwable {
        if (com.mob.commons.a.I()) {
            return false;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("product", mobProduct.getProductTag()));
        String str = (String) hashMap.get("duid");
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        arrayList.add(new KVPair("duid", str));
        arrayList.add(new KVPair("apppkg", String.valueOf(instance.getPackageName())));
        arrayList.add(new KVPair("appver", String.valueOf(instance.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(mobProduct.getSdkver())));
        arrayList.add(new KVPair("network", String.valueOf(instance.getDetailNetworkTypeForStatic())));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = Config.SESSION_PERIOD;
        networkTimeOut.connectionTimeout = Config.SESSION_PERIOD;
        NetworkHelper networkHelper = new NetworkHelper();
        String str2 = a + "/dsign";
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        HashMap fromJson = new Hashon().fromJson(networkHelper.httpPost(str2, arrayList, null, arrayList2, networkTimeOut));
        if ("true".equals(String.valueOf(fromJson.get("reup")))) {
            a(hashMap, str);
        }
        if (!"200".equals(String.valueOf(fromJson.get(NotificationCompat.CATEGORY_STATUS)))) {
            return false;
        }
        ((HashMap) ((HashMap) hashMap.get("appInfo")).get(instance.getPackageName())).put(mobProduct.getProductTag(), MobSDK.getAppkey());
        return true;
    }

    /* access modifiers changed from: private */
    public void c(final HashMap<String, Object> hashMap) {
        d.a(d.a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                a.this.a(hashMap, false);
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0099 A[SYNTHETIC, Splitter:B:33:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a5 A[SYNTHETIC, Splitter:B:38:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.HashMap<java.lang.String, java.lang.Object> r6, boolean r7) {
        /*
            r5 = this;
            r2 = 0
            if (r7 != 0) goto L_0x003d
            java.lang.String r0 = "duid"
            java.lang.Object r0 = r6.get(r0)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            android.content.Context r1 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            com.mob.tools.utils.DeviceHelper r1 = com.mob.tools.utils.DeviceHelper.getInstance(r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            boolean r3 = r1.getSdcardState()     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r3 == 0) goto L_0x003d
            java.util.HashMap r3 = r5.e()     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r3 == 0) goto L_0x0077
            java.lang.String r1 = "duid"
            java.lang.Object r1 = r3.get(r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            r3 = 0
            java.lang.Object r1 = com.mob.tools.utils.ResHelper.forceCast(r1, r3)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r3 != 0) goto L_0x003d
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r0 != 0) goto L_0x003d
            java.lang.String r0 = "duid"
            r6.put(r0, r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
        L_0x003d:
            java.io.File r0 = r5.c()     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            r1.writeObject(r6)     // Catch:{ Throwable -> 0x00b1 }
            java.io.File r2 = r5.d()     // Catch:{ Throwable -> 0x00b1 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00b1 }
            java.lang.String r4 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x00b1 }
            boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x00b1 }
            if (r3 != 0) goto L_0x006e
            r2.delete()     // Catch:{ Throwable -> 0x00b1 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00b1 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x00b1 }
            com.mob.tools.utils.ResHelper.copyFile(r0, r2)     // Catch:{ Throwable -> 0x00b1 }
        L_0x006e:
            if (r1 == 0) goto L_0x0076
            r1.flush()     // Catch:{ Throwable -> 0x00b3 }
            r1.close()     // Catch:{ Throwable -> 0x00b3 }
        L_0x0076:
            return
        L_0x0077:
            r3 = 0
            java.lang.String r1 = r1.getWAbcd(r3)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r3 != 0) goto L_0x003d
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            if (r0 != 0) goto L_0x003d
            java.lang.String r0 = "duid"
            r6.put(r0, r1)     // Catch:{ Throwable -> 0x008e, all -> 0x00a2 }
            goto L_0x003d
        L_0x008e:
            r0 = move-exception
            r1 = r2
        L_0x0090:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00ae }
            r2.w(r0)     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x0076
            r1.flush()     // Catch:{ Throwable -> 0x00a0 }
            r1.close()     // Catch:{ Throwable -> 0x00a0 }
            goto L_0x0076
        L_0x00a0:
            r0 = move-exception
            goto L_0x0076
        L_0x00a2:
            r0 = move-exception
        L_0x00a3:
            if (r2 == 0) goto L_0x00ab
            r2.flush()     // Catch:{ Throwable -> 0x00ac }
            r2.close()     // Catch:{ Throwable -> 0x00ac }
        L_0x00ab:
            throw r0
        L_0x00ac:
            r1 = move-exception
            goto L_0x00ab
        L_0x00ae:
            r0 = move-exception
            r2 = r1
            goto L_0x00a3
        L_0x00b1:
            r0 = move-exception
            goto L_0x0090
        L_0x00b3:
            r0 = move-exception
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.a.a(java.util.HashMap, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean i() {
        try {
            File c = c();
            File d = d();
            if (c.getPath().equals(d.getPath())) {
                return false;
            }
            if (!c.exists() || !c.isFile()) {
                if (!d.exists() || !d.isFile()) {
                    return false;
                }
                ResHelper.copyFile(d.getAbsolutePath(), c.getAbsolutePath());
                return true;
            } else if (d.exists() && d.isFile()) {
                return false;
            } else {
                ResHelper.copyFile(c.getAbsolutePath(), d.getAbsolutePath());
                return false;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    private void a(final String str, final MobProduct mobProduct) {
        new Thread() {
            public void run() {
                synchronized (a.a) {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("duid", str);
                        a.this.b(hashMap);
                        a.this.a(hashMap, str);
                        a.this.a(hashMap, mobProduct);
                        a.this.c(hashMap);
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: 0000 */
    public final String a(final boolean z, final boolean z2) {
        final String[] strArr = new String[1];
        d.a(d.a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                HashMap hashMap;
                try {
                    if (!z) {
                        a.this.i();
                    }
                    HashMap c2 = a.this.e();
                    if (c2 == null) {
                        hashMap = new HashMap();
                    } else {
                        hashMap = c2;
                    }
                    String str = (String) hashMap.get("duid");
                    if (str == null && z2) {
                        str = a.this.a(!z);
                        if (!z) {
                            hashMap.put("duid", str);
                            a.this.a(hashMap, false);
                        }
                    }
                    strArr[0] = str;
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
                return false;
            }
        });
        return strArr[0];
    }

    /* access modifiers changed from: 0000 */
    public final String a() {
        String str;
        try {
            HashMap e = e();
            if (e != null) {
                str = (String) e.get("duid");
            } else {
                str = null;
            }
            if (str == null) {
                return f();
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private void a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.digap")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    /* access modifiers changed from: private */
    public boolean j() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.digap");
        if (cacheRootFile == null || !cacheRootFile.exists()) {
            a(com.mob.commons.a.L() + com.mob.commons.a.a());
            return false;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(cacheRootFile));
            long readLong = dataInputStream.readLong();
            dataInputStream.close();
            if (readLong < com.mob.commons.a.a()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }
}
