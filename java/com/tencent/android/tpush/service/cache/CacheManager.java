package com.tencent.android.tpush.service.cache;

import android.content.Context;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MobileType;
import com.tencent.android.tpush.common.e;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.stat.b.c;
import com.tencent.mid.api.MidService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class CacheManager {
    private static Map<Long, RegisterEntity> a = null;

    /* compiled from: ProGuard */
    static class a implements Runnable {
        private Context a;
        private Map<Long, RegisterEntity> b;

        public a(Context context) {
            this.a = context;
        }

        public void run() {
            try {
                a(com.tencent.android.tpush.a.e(CacheManager.getContext()));
            } catch (Exception e) {
            }
        }

        public synchronized void a(Map<Long, RegisterEntity> map) {
            this.b = map;
        }

        public synchronized Map<Long, RegisterEntity> a() {
            return this.b;
        }
    }

    private CacheManager() {
    }

    public static Map<Long, RegisterEntity> getRegInfoByApps(Context context) {
        a aVar = new a(context);
        Thread thread = new Thread(aVar);
        thread.start();
        try {
            thread.join(3500);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, th.toString());
        }
        return aVar.a();
    }

    public static String findValidPackageByAccessid(long j) {
        RegisterEntity registerEntity = (RegisterEntity) getRegisterEntityMap().get(Long.valueOf(j));
        if (registerEntity == null || !registerEntity.isRegistered()) {
            return null;
        }
        return registerEntity.packageName;
    }

    public static RegisterEntity findValidRegisterEntityByPkg(String str) {
        if (i.b(str)) {
            return null;
        }
        for (Entry value : getRegisterEntityMap().entrySet()) {
            RegisterEntity registerEntity = (RegisterEntity) value.getValue();
            if (registerEntity != null && str.equals(registerEntity.packageName)) {
                return registerEntity;
            }
        }
        return null;
    }

    public static void removeRegisterInfos(String str) {
        a(str, 1);
    }

    public static List<String> getRegisterInfos(Context context) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        try {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && !i.b(registerEntity.packageName) && registerEntity.isRegistered()) {
                    arrayList2.add(registerEntity.packageName);
                }
            }
            arrayList = arrayList2;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
            arrayList = new ArrayList();
        }
        if (!arrayList.contains(context.getPackageName())) {
            arrayList.add(context.getPackageName());
        }
        return arrayList;
    }

    public static synchronized Map<Long, RegisterEntity> getRegisterEntityMap() {
        Map<Long, RegisterEntity> map;
        synchronized (CacheManager.class) {
            if (a == null) {
                a = getRegInfoByApps(getContext());
            }
            map = a;
        }
        return map;
    }

    public static RegisterEntity getCurrentAppRegisterEntity(Context context) {
        String a2 = h.a(context, a("cur.register", ".reg"), "");
        if (!i.b(a2)) {
            return RegisterEntity.decode(a2);
        }
        return null;
    }

    public static void setCurrentAppRegisterEntity(Context context, RegisterEntity registerEntity) {
        h.b(context, a("cur.register", ".reg"), RegisterEntity.encode(registerEntity));
    }

    public static Context getContext() {
        if (b.f() != null) {
            return b.f();
        }
        return XGPushManager.getContext();
    }

    public static void addRegisterInfo(RegisterEntity registerEntity) {
        if (registerEntity != null && registerEntity.accessId > 0) {
            getRegisterEntityMap().put(Long.valueOf(registerEntity.accessId), registerEntity);
        }
    }

    public static List<RegisterEntity> getRegisterInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && registerEntity.isRegistered()) {
                    arrayList.add(registerEntity);
                }
            }
        }
        return arrayList;
    }

    public static List<RegisterEntity> getUnregisterInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && !i.b(registerEntity.packageName) && registerEntity.isUnregistered()) {
                    arrayList.add(registerEntity);
                }
            }
        }
        return arrayList;
    }

    public static List<RegisterEntity> getUninstallInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && !i.b(registerEntity.packageName) && registerEntity.isUnstalled()) {
                    arrayList.add(registerEntity);
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<UnregInfo> getUninstallAndUnregisterInfo(Context context) {
        ArrayList<UnregInfo> arrayList = new ArrayList<>();
        if (context != null) {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && (registerEntity.isUnregistered() || registerEntity.isUnstalled())) {
                    UnregInfo unregInfo = new UnregInfo();
                    unregInfo.appInfo = new AppInfo(registerEntity.accessId, registerEntity.accessKey, i.d(registerEntity.packageName), 0);
                    unregInfo.isUninstall = (byte) registerEntity.state;
                    unregInfo.timestamp = registerEntity.timestamp;
                    arrayList.add(unregInfo);
                }
            }
        }
        return arrayList;
    }

    public static RegisterEntity getRegisterInfoByPkgName(String str) {
        return findValidRegisterEntityByPkg(str);
    }

    public static void UnregisterInfoByPkgName(String str) {
        a(str, 1);
    }

    public static void UnregisterInfoSuccessByPkgName(String str) {
        a(str, 3);
    }

    private static void a(String str, byte b) {
        if (!i.b(str)) {
            for (Entry value : getRegisterEntityMap().entrySet()) {
                RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                if (registerEntity != null && !i.b(registerEntity.packageName) && str.equals(registerEntity.packageName)) {
                    registerEntity.state = b;
                }
            }
        }
    }

    public static void UninstallInfoByPkgName(String str) {
        a(str, 2);
    }

    public static void UninstallInfoSuccessByPkgName(String str) {
        a(str, 4);
    }

    public static void removeRegisterInfoByPkgName(String str) {
        a(str);
    }

    private static void a(String str, String str2, int i) {
    }

    private static void a(String str) {
    }

    public static void updateUnregUninList(Context context, ArrayList<UnregInfo> arrayList) {
        if (context != null && arrayList != null && arrayList.size() > 0) {
            List unregisterInfo = getUnregisterInfo(context);
            List<RegisterEntity> uninstallInfo = getUninstallInfo(context);
            if (unregisterInfo != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    UnregInfo unregInfo = (UnregInfo) arrayList.get(i);
                    if (unregInfo.isUninstall == 1) {
                        for (int i2 = 0; i2 < unregisterInfo.size(); i2++) {
                            RegisterEntity registerEntity = (RegisterEntity) unregisterInfo.get(i2);
                            if (registerEntity.accessId == unregInfo.appInfo.accessId) {
                                a(registerEntity.packageName, a(registerEntity.packageName, ".reg"), 3);
                            }
                        }
                    }
                    if (unregInfo.isUninstall == 2) {
                        for (RegisterEntity registerEntity2 : uninstallInfo) {
                            if (registerEntity2.accessId == unregInfo.appInfo.accessId) {
                                a(registerEntity2.packageName, a(registerEntity2.packageName, ".reg"), 4);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String getToken(Context context) {
        String b = c.b(context);
        return MidService.isMidValid(b) ? b : MidService.getLocalMidOnly(context);
    }

    public static long getGuid(Context context) {
        return c.a(context);
    }

    public static boolean setToken(Context context, String str) {
        if (context == null || i.b(str) || str.equals(getToken(context))) {
            return false;
        }
        c.a(context, str);
        return true;
    }

    public static boolean setTokenAndGuid(Context context, String str, long j) {
        if (context != null) {
            try {
                if (!i.b(str)) {
                    long guid = getGuid(context);
                    String token = getToken(context);
                    if (j != guid || !str.equals(token)) {
                        if (j < 0) {
                            j = guid;
                        }
                        if (i.b(str) || !c.a(str)) {
                            str = token;
                        }
                        c.a(context, j, str);
                        return true;
                    }
                }
            } catch (Throwable th) {
            }
        }
        return false;
    }

    public static String getQua(Context context, long j) {
        String str = "";
        if (context != null) {
            return Rijndael.decrypt(h.a(context, ".com.tencent.tpush.cache.qua." + j, ""));
        }
        return str;
    }

    public static void setQua(Context context, long j, String str) {
        if (context != null && !i.b(str) && j > 0) {
            h.b(context, ".com.tencent.tpush.cache.qua." + j, str);
        }
    }

    public static synchronized void addOptStrategyList(Context context, String str, OptStrategyList optStrategyList) {
        synchronized (CacheManager.class) {
            if (!(context == null || str == null)) {
                addOptKey(context, str);
                String str2 = str + ".com.tencent.tpush.cache.redirect";
                try {
                    optStrategyList.setTimestamp(System.currentTimeMillis());
                    h.b(context, str2, Rijndael.encrypt(e.a((Serializable) optStrategyList)));
                } catch (Exception e) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
                }
            }
        }
        return;
    }

    public static synchronized void removeOptStrategyList(Context context, String str) {
        synchronized (CacheManager.class) {
            addOptStrategyList(context, str, new OptStrategyList());
        }
    }

    public static OptStrategyList getOptStrategyList(Context context, String str) {
        if (context == null || str == null) {
            try {
                throw new NullReturnException(new StringBuffer("getStrategy return null,contex is null(").append(context == null).append(") and key=").append(str).toString());
            } catch (Exception e) {
                throw new NullReturnException("getOptStrategyList return null,deserialize err", e);
            }
        } else {
            Object a2 = e.a(Rijndael.decrypt(h.a(context, str + ".com.tencent.tpush.cache.redirect", "")));
            if (a2 instanceof OptStrategyList) {
                return (OptStrategyList) a2;
            }
            throw new NullReturnException("getStrategy return null, because serializer object is not instanceof OptStrategyList");
        }
    }

    public static synchronized void addOptStrategy(StrategyItem strategyItem) {
        OptStrategyList optStrategyList;
        synchronized (CacheManager.class) {
            String l = i.l(b.f());
            try {
                optStrategyList = getOptStrategyList(b.f(), l);
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, ">> Can not get OptStrategyList from local", e);
                optStrategyList = new OptStrategyList();
            }
            if (strategyItem.getProtocolType() == 1) {
                if (strategyItem.getRedirect() == 0) {
                    optStrategyList.setHttpItem(strategyItem);
                } else {
                    optStrategyList.setHttpRedirectItem(strategyItem);
                }
            } else if (strategyItem.getRedirect() == 0) {
                optStrategyList.setTcpItem(strategyItem);
            } else {
                optStrategyList.setTcpRedirectItem(strategyItem);
            }
            addOptStrategyList(b.f(), l, optStrategyList);
        }
        return;
    }

    public static void addServerItems(Context context, String str, ArrayList<ServerItem> arrayList) {
        if (context != null && str != null) {
            saveDomainKey(context, str);
            try {
                h.b(context, str + ".com.tencent.tpush.cache.server", Rijndael.encrypt(e.a((Serializable) arrayList)));
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
            }
        }
    }

    public static ArrayList<ServerItem> getServerItems(Context context, String str) {
        if (str == null) {
            throw new NullReturnException("getServerItems return null,because key is null");
        }
        try {
            Object a2 = e.a(Rijndael.decrypt(h.a(context, str + ".com.tencent.tpush.cache.server", "")));
            if (a2 != null && (a2 instanceof ArrayList)) {
                return (ArrayList) a2;
            }
            throw new NullReturnException("getServerItems return null,because object not instance of Arraylist<?>");
        } catch (Exception e) {
            throw new NullReturnException("getServerItem return null,deseriallize err", e);
        }
    }

    public static void addOptKeyList(Context context, HashSet<String> hashSet) {
        if (context != null) {
            try {
                h.b(context, ".com.tencent.tpush.cache.keylist", Rijndael.encrypt(e.a((Serializable) hashSet)));
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
            }
        }
    }

    public static void addOptKey(Context context, String str) {
        HashSet hashSet;
        try {
            hashSet = getOptKeyList(context);
        } catch (Exception e) {
            hashSet = new HashSet();
        }
        hashSet.add(str);
        addOptKeyList(context, hashSet);
    }

    public static HashSet<String> getOptKeyList(Context context) {
        if (context == null) {
            throw new NullReturnException("getOptKeyList return null,because ctx is null");
        }
        try {
            Object a2 = e.a(Rijndael.decrypt(h.a(context, ".com.tencent.tpush.cache.keylist", "")));
            if (a2 instanceof HashSet) {
                return (HashSet) a2;
            }
            throw new NullReturnException("getOptKeyList return null,because object not instance of ArrayList<?>");
        } catch (Exception e) {
            throw new NullReturnException("getOptKeyList return null，deseriallize err", e);
        }
    }

    public static void clearOptKeyList(Context context) {
        if (context != null) {
            h.b(context, ".com.tencent.tpush.cache.keylist", "");
        }
    }

    public static void saveLoadIpTime(Context context, long j) {
        if (context != null && j > 0) {
            h.b(context, ".com.tencent.tpush.cache.load.ip.last.time", j);
        }
    }

    public static long getLastLoadIpTime(Context context) {
        if (context != null) {
            return h.a(context, ".com.tencent.tpush.cache.load.ip.last.time", 0);
        }
        return 0;
    }

    public static void saveSpeedTestList(Context context, ArrayList<ServerItem> arrayList) {
        if (context != null) {
            try {
                h.b(context, ".com.tencent.tpush.cache.speed.test", Rijndael.encrypt(e.a((Serializable) arrayList)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<ServerItem> getSpeedTestList(Context context) {
        if (context == null) {
            throw new NullReturnException("getSpeedTestList return null ,because ctx is null");
        }
        try {
            Object a2 = e.a(Rijndael.decrypt(h.a(context, ".com.tencent.tpush.cache.speed.test", "")));
            if (a2 instanceof ArrayList) {
                return (ArrayList) a2;
            }
            throw new NullReturnException("getSpeedTestList return null ,because instanceof err");
        } catch (Exception e) {
            throw new NullReturnException("getSpeedTestList return null ,because deserialize err", e);
        }
    }

    public static void saveDomain(Context context, String str) {
        if (context != null && str != null) {
            h.b(context, ".com.tencent.tpush.cache.domain", str);
        }
    }

    public static String getDomain(Context context) {
        if (context != null) {
            h.a(context, ".com.tencent.tpush.cache.domain", "");
        }
        return "";
    }

    public static void saveDomainKeyList(Context context, ArrayList<String> arrayList) {
        if (context != null) {
            String str = "";
            if (arrayList != null) {
                try {
                    str = e.a((Serializable) arrayList);
                } catch (Exception e) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
                    return;
                }
            }
            h.b(context, ".com.tencent.tpush.cache.domain.key", Rijndael.encrypt(str));
        }
    }

    public static void saveDomainKey(Context context, String str) {
        ArrayList arrayList;
        if (context != null) {
            try {
                arrayList = getDomainKeyList(context);
            } catch (Exception e) {
                arrayList = new ArrayList();
            }
            arrayList.add(str);
            saveDomainKeyList(context, arrayList);
        }
    }

    public static ArrayList<String> getDomainKeyList(Context context) {
        if (context == null) {
            throw new NullReturnException("getDomainKeyList return null,because ctx is null");
        }
        try {
            Object a2 = e.a(Rijndael.decrypt(h.a(context, ".com.tencent.tpush.cache.domain.key", "")));
            if (a2 instanceof ArrayList) {
                return (ArrayList) a2;
            }
            throw new NullReturnException("getDomainKeyList return null,because object not instance of ArrayList<?>");
        } catch (Exception e) {
            throw new NullReturnException("getDomainKeyList return null，deseriallize err", e);
        }
    }

    public static void clearDomainServerItem(Context context) {
        ArrayList arrayList;
        try {
            arrayList = getDomainKeyList(context);
        } catch (NullReturnException e) {
            arrayList = new ArrayList();
        }
        arrayList.add(String.valueOf(MobileType.CHINAMOBILE.getType()));
        arrayList.add(String.valueOf(MobileType.TELCOM.getType()));
        arrayList.add(String.valueOf(MobileType.UNICOM.getType()));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                h.b(context, ((String) it.next()) + ".com.tencent.tpush.cache.server", "");
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e2);
            }
        }
    }

    private static String a(String str, String str2) {
        return str + ".com.tencent.tpush.cache" + str2;
    }
}
