package com.tencent.android.tpush;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import com.tencent.android.tpush.a.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    private static ReentrantLock a = new ReentrantLock();
    private static Map<String, String> b = new HashMap();

    public static synchronized Map<String, ProviderInfo> a(Context context) {
        HashMap hashMap;
        synchronized (a.class) {
            hashMap = new HashMap();
            try {
                for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
                    if (providerInfo.name.equals(XGPushProvider.class.getName()) && providerInfo.authority.equals(a(providerInfo.packageName))) {
                        hashMap.put(providerInfo.packageName, providerInfo);
                        com.tencent.android.tpush.b.a.c(Constants.LogTag, providerInfo.authority + StorageInterface.KEY_SPLITER + providerInfo.packageName + StorageInterface.KEY_SPLITER + providerInfo.name);
                    }
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("ProviderUtil", "Package manager has died", th);
            }
        }
        return hashMap;
    }

    public static String a(String str) {
        return str + XGPushProvider.AUTH_PRIX;
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SettingsContentProvider.KEY, str2);
            b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.msg.getStr()), contentValues);
            return true;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("ProviderUtil", "sendMsgByPkgName", th);
            return false;
        }
    }

    public static void b(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.insert_mid_new.getStr()), contentValues);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("ProviderUtil", "", th);
        }
    }

    public static void c(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.insert_mid_old.getStr()), contentValues);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("ProviderUtil", "", th);
        }
    }

    public static boolean a(Context context, String str, Intent intent) {
        return a(context, str, intent.toURI());
    }

    public static void d(Context context, String str, String str2) {
        Uri parse = Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.feedback.getStr());
        ContentValues contentValues = new ContentValues();
        contentValues.put("feedback", Rijndael.encrypt(str2));
        try {
            b.a(context, parse, contentValues, null, null);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("SettingsContentProvider", "error : ", th);
        }
    }

    public static void b(Context context) {
        Map a2 = a(context);
        if (a2 != null && a2.size() != 0) {
            if (Build.MODEL.contains(Constants.VIVO_STR)) {
                try {
                    a.lock();
                    for (String str : a2.keySet()) {
                        if (i.c(context, str) && !str.equals(context.getPackageName())) {
                            com.tencent.android.tpush.b.a.e(Constants.LogTag, "heartbeat to " + str);
                            String decrypt = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.hearbeat.getStr())));
                            com.tencent.android.tpush.b.a.e(Constants.LogTag, "heartbeat " + str + " " + decrypt);
                            if (c.b(decrypt)) {
                                new JSONObject(decrypt).optInt("cnt", 0);
                                b.put(str, decrypt);
                            }
                        }
                    }
                    try {
                        a.unlock();
                    } catch (Throwable th) {
                    }
                } catch (Throwable th2) {
                }
            } else {
                for (String str2 : a2.keySet()) {
                    try {
                        if (i.c(context, str2) && !str2.equals(context.getPackageName())) {
                            com.tencent.android.tpush.b.a.e(Constants.LogTag, "heartbeat to " + str2);
                            String decrypt2 = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str2 + XGPushProvider.AUTH_PRIX + "/" + TypeStr.hearbeat.getStr())));
                            com.tencent.android.tpush.b.a.e(Constants.LogTag, "heartbeat " + str2 + " " + decrypt2);
                            if (c.b(decrypt2)) {
                                new JSONObject(decrypt2).optInt("cnt", 0);
                                b.put(str2, decrypt2);
                            }
                        }
                    } catch (Throwable th3) {
                        com.tencent.android.tpush.b.a.d("ProviderUtil", "", th3);
                    }
                }
            }
        }
    }

    public static String a(Context context, String str) {
        if (Build.MODEL.contains(Constants.VIVO_STR)) {
            try {
                a.lock();
                String decrypt = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.token.getStr())));
                com.tencent.android.tpush.b.a.e(Constants.LogTag, "get token from pkg:" + str + ", token:" + decrypt);
                if (decrypt == null || decrypt.trim().length() != 40) {
                    try {
                        a.unlock();
                    } catch (Throwable th) {
                    }
                } else {
                    try {
                        a.unlock();
                        return decrypt;
                    } catch (Throwable th2) {
                        return decrypt;
                    }
                }
            } catch (Throwable th3) {
            }
        } else {
            try {
                String decrypt2 = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.token.getStr())));
                com.tencent.android.tpush.b.a.e(Constants.LogTag, "get token from pkg:" + str + ", token:" + decrypt2);
                if (decrypt2 != null && decrypt2.trim().length() == 40) {
                    return decrypt2;
                }
            } catch (Throwable th4) {
                com.tencent.android.tpush.b.a.d("ProviderUtil", "", th4);
            }
        }
        return null;
    }

    public static Map<String, Integer> c(Context context) {
        Map a2 = a(context);
        HashMap hashMap = new HashMap();
        if (a2 == null || a2.size() == 0) {
            return hashMap;
        }
        for (String a3 : a2.keySet()) {
            String a4 = a(context, a3);
            if (com.tencent.android.tpush.stat.b.c.a(a4)) {
                Integer num = (Integer) hashMap.get(a4);
                if (num == null) {
                    hashMap.put(a4, Integer.valueOf(1));
                } else {
                    hashMap.put(a4, Integer.valueOf(num.intValue() + 1));
                }
            }
        }
        return hashMap;
    }

    public static String d(Context context) {
        String str;
        int i;
        String str2 = null;
        Map c = c(context);
        if (c != null && c.size() > 0) {
            int i2 = 0;
            for (Entry entry : c.entrySet()) {
                if (((Integer) entry.getValue()).intValue() > i2) {
                    i = ((Integer) entry.getValue()).intValue();
                    str = (String) entry.getKey();
                } else {
                    str = str2;
                    i = i2;
                }
                str2 = str;
                i2 = i;
            }
        }
        return str2;
    }

    public static RegisterEntity b(Context context, String str) {
        if (Build.MODEL.contains(Constants.VIVO_STR)) {
            try {
                String decrypt = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.register.getStr())));
                if (decrypt != null) {
                    RegisterEntity decode = RegisterEntity.decode(decrypt);
                    try {
                        a.unlock();
                        return decode;
                    } catch (Throwable th) {
                        return decode;
                    }
                } else {
                    try {
                        a.unlock();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
            }
        } else {
            try {
                String decrypt2 = Rijndael.decrypt(b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.register.getStr())));
                if (decrypt2 != null) {
                    return RegisterEntity.decode(decrypt2);
                }
            } catch (Throwable th4) {
                com.tencent.android.tpush.b.a.d("ProviderUtil", "getRegisterInfo", th4);
            }
        }
        return null;
    }

    public static Map<Long, RegisterEntity> e(Context context) {
        RegisterEntity b2;
        Map a2 = a(context);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        if (a2 == null || a2.size() == 0) {
            return concurrentHashMap;
        }
        for (String str : a2.keySet()) {
            if (str == null || !str.equals(context.getPackageName())) {
                b2 = b(context, str);
            } else {
                b2 = CacheManager.getCurrentAppRegisterEntity(context);
            }
            if (b2 != null && b2.accessId > 0) {
                concurrentHashMap.put(Long.valueOf(b2.accessId), b2);
            }
        }
        return concurrentHashMap;
    }
}
