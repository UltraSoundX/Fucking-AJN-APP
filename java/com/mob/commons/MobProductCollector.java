package com.mob.commons;

import cn.sharesdk.framework.ShareSDK;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.tencent.mid.sotrage.StorageInterface;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

public class MobProductCollector implements PublicMemberKeeper {
    public static final String[] MOB_PRODUCTS = {ShareSDK.SDK_TAG, "SMSSDK", "SHAREREC", "MOBAPI", "MOBLINK", "UMSSDK", "CMSSDK", "BBSSDK", "SHOPSDK", "PAYSDK", "MOBIM", "MOBPUSH", "ANALYSDK", "MOBVERIFY", "SECVERIFY", "MOBADSDK"};
    public static final String[] MOB_SOLUTIONS = {"GROWSOLUTION"};
    private static boolean a = false;
    private static final HashMap<String, MobProduct> b = new HashMap<>();
    private static final HashMap<String, MobSolution> c = new HashMap<>();

    public static synchronized boolean registerProduct(MobProduct mobProduct) {
        boolean z;
        synchronized (MobProductCollector.class) {
            if (mobProduct != null) {
                if (!b.containsKey(mobProduct.getProductTag())) {
                    b.put(mobProduct.getProductTag(), mobProduct);
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public static synchronized void collect() {
        synchronized (MobProductCollector.class) {
            getProducts();
        }
    }

    public static void syncInit() {
        try {
            MOBLINK moblink = new MOBLINK();
            if (moblink instanceof MobProduct) {
                moblink.getProductTag();
            }
        } catch (Throwable th) {
        }
    }

    public static synchronized ArrayList<MobProduct> getProducts() {
        ArrayList<MobProduct> arrayList;
        synchronized (MobProductCollector.class) {
            if (!a) {
                b.putAll(a());
                a = true;
            }
            arrayList = new ArrayList<>();
            arrayList.addAll(b.values());
        }
        return arrayList;
    }

    private static HashMap<String, MobProduct> a() {
        Class cls;
        HashMap<String, MobProduct> hashMap = new HashMap<>();
        for (Object next : f.a) {
            try {
                if (next instanceof String) {
                    cls = Class.forName(String.valueOf(next).trim());
                } else {
                    cls = (Class) next;
                }
                if (!MobProduct.class.isAssignableFrom(cls) || MobProduct.class.equals(cls)) {
                    if (MobSolution.class.isAssignableFrom(cls) && !MobSolution.class.equals(cls)) {
                        MobSolution mobSolution = (MobSolution) cls.newInstance();
                        String solutionTag = mobSolution.getSolutionTag();
                        String[] strArr = MOB_SOLUTIONS;
                        int length = strArr.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String str = strArr[i];
                            if (str.equals(solutionTag)) {
                                c.put(str, mobSolution);
                                break;
                            }
                            i++;
                        }
                    } else {
                        cls.newInstance();
                    }
                } else {
                    MobProduct mobProduct = (MobProduct) cls.newInstance();
                    String productTag = mobProduct.getProductTag();
                    String[] strArr2 = MOB_PRODUCTS;
                    int length2 = strArr2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        String str2 = strArr2[i2];
                        if (str2.equals(productTag)) {
                            hashMap.put(str2, mobProduct);
                            break;
                        }
                        i2++;
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
        return hashMap;
    }

    public static synchronized String getUserIdentity() {
        String userIdentity;
        synchronized (MobProductCollector.class) {
            userIdentity = getUserIdentity(getProducts());
        }
        return userIdentity;
    }

    public static synchronized String getUserIdentity(ArrayList<MobProduct> arrayList) {
        String str;
        String str2;
        int i;
        String str3;
        synchronized (MobProductCollector.class) {
            try {
                DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                String encode = URLEncoder.encode(instance.getPackageName(), "utf-8");
                String encode2 = URLEncoder.encode(instance.getAppVersionName(), "utf-8");
                String encode3 = URLEncoder.encode(instance.getManufacturer(), "utf-8");
                String encode4 = URLEncoder.encode(instance.getModel(), "utf-8");
                HashMap b2 = e.a().b();
                String str4 = "APP/" + encode + ";" + encode2;
                String str5 = "SYS/Android;" + instance.getOSVersionInt();
                String str6 = "SDI/" + instance.getDeviceKey();
                String str7 = "FM/" + encode3 + ";" + encode4;
                String str8 = "NE/" + instance.getNetworkTypeForStatic() + ";" + instance.getCarrier();
                String str9 = "Lang/" + Locale.getDefault().toString().replace("-r", "-");
                String str10 = "CLV/" + MobSDK.SDK_VERSION_CODE;
                String str11 = "SDK/";
                if (!arrayList.isEmpty()) {
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        try {
                            MobProduct mobProduct = (MobProduct) arrayList.get(i2);
                            if (i2 != 0) {
                                str11 = str11 + StorageInterface.KEY_SPLITER;
                            }
                            str3 = str11 + mobProduct.getProductTag() + ";" + mobProduct.getSdkver() + ";" + b2.get(mobProduct.getProductTag());
                        } catch (Throwable th) {
                            str3 = str11;
                        }
                        i2++;
                        str11 = str3;
                    }
                }
                String str12 = str11;
                String str13 = "DC/1";
                String str14 = "";
                if (!c.isEmpty()) {
                    str14 = " P/";
                    int i3 = 0;
                    for (Entry entry : c.entrySet()) {
                        if (i3 != 0) {
                            try {
                                str14 = str14 + StorageInterface.KEY_SPLITER;
                            } catch (Throwable th2) {
                                i = i3;
                                str2 = str14;
                            }
                        }
                        MobSolution mobSolution = (MobSolution) entry.getValue();
                        int i4 = i3 + 1;
                        str2 = str14 + mobSolution.getSolutionTag() + ";" + mobSolution.getSolutionVer();
                        i = i4;
                        str14 = str2;
                        i3 = i;
                    }
                }
                str = str4 + " " + str5 + " " + str6 + " " + str7 + " " + str8 + " " + str9 + " " + str10 + " " + str12 + " " + str13 + str14;
            } catch (Throwable th3) {
                MobLog.getInstance().w(th3);
                str = "";
            }
        }
        return str;
    }
}
