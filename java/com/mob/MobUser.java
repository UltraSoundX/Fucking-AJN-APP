package com.mob;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.h;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class MobUser implements PublicMemberKeeper {
    private static final String a = h.b("http://api.u.mob.com");
    private static MobCommunicator b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
    private static Handler c = MobHandlerThread.newHandler(Config.MODEL, (Callback) new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Object[] objArr = (Object[]) message.obj;
                    MobUser.c((String) objArr[0], (String) objArr[1], (String) objArr[2], (HashMap) objArr[3], (String) objArr[4]);
                    break;
                case 2:
                    MobUser.c((OnUserGotListener) message.obj);
                    break;
                case 3:
                    MobUser.e();
                    break;
            }
            return false;
        }
    });
    private static String d;
    /* access modifiers changed from: private */
    public static MobUser e = new MobUser();
    private boolean f;
    private String g;
    private String h;
    private String i;
    private HashMap<String, Object> j;
    private String k;
    private String l;
    private long m;
    private HashSet<UserWatcher> n = new HashSet<>();

    public interface OnUserGotListener {
        void onUserGot(MobUser mobUser);
    }

    public interface UserWatcher {
        void onUserStateChange(MobUser mobUser);
    }

    static void a(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Object[]{str, str2, str3, hashMap, str4};
        c.sendMessage(message);
    }

    static void a(OnUserGotListener onUserGotListener) {
        Message message = new Message();
        message.what = 2;
        message.obj = onUserGotListener;
        c.sendMessage(message);
    }

    static void a() {
        c.sendEmptyMessage(3);
    }

    private static String d() {
        if (d == null) {
            ArrayList products = MobProductCollector.getProducts();
            d = DeviceAuthorizer.authorize(products.isEmpty() ? null : (MobProduct) products.get(0));
        }
        return d;
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        if (e.l == null || !e.f() || !ResHelper.isEqual(str, e.g)) {
            d(str, str2, str3, hashMap, str4);
        } else {
            a(str2, str3, hashMap);
        }
    }

    private static void d(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        String str5;
        HashMap<String, Object> hashMap2;
        if (e.l != null) {
            e();
        }
        HashMap hashMap3 = new HashMap();
        ArrayList products = MobProductCollector.getProducts();
        ArrayList arrayList = new ArrayList();
        Iterator it = products.iterator();
        while (it.hasNext()) {
            arrayList.add(((MobProduct) it.next()).getProductTag());
        }
        hashMap3.put("sdks", arrayList);
        if (!TextUtils.isEmpty(str)) {
            hashMap3.put("appUserId", str);
        }
        hashMap3.put("appkey", MobSDK.getAppkey());
        hashMap3.put("nickname", TextUtils.isEmpty(str2) ? "" : str2);
        String str6 = "avatar";
        if (TextUtils.isEmpty(str3)) {
            str5 = "";
        } else {
            str5 = str3;
        }
        hashMap3.put(str6, str5);
        String str7 = "appUserMap";
        if (hashMap == null) {
            hashMap2 = new HashMap<>();
        } else {
            hashMap2 = hashMap;
        }
        hashMap3.put(str7, hashMap2);
        hashMap3.put("duid", d());
        if (!TextUtils.isEmpty(str4)) {
            hashMap3.put("sign", str4);
        }
        try {
            if (!a.ac()) {
                HashMap hashMap4 = (HashMap) b.requestSynchronized(hashMap3, a + "/login", false);
                String str8 = (String) hashMap4.get("mobUserId");
                long parseLong = Long.parseLong(String.valueOf(hashMap4.get("loginExpireAt")));
                a.a();
                e.g = str;
                e.f = TextUtils.isEmpty(str);
                e.h = str2;
                e.i = str3;
                e.j = hashMap;
                e.k = str4;
                e.l = str8;
                e.m = parseLong;
            }
            Iterator it2 = e.n.iterator();
            while (it2.hasNext()) {
                ((UserWatcher) it2.next()).onUserStateChange(e);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private static void a(String str, String str2, HashMap<String, Object> hashMap) {
        String str3;
        HashMap<String, Object> hashMap2;
        if (e.l == null) {
            e.h = str;
            e.i = str2;
            e.j = hashMap;
        } else if (!e.f()) {
            d(e.g, str, str2, hashMap, e.k);
        } else {
            HashMap hashMap3 = new HashMap();
            hashMap3.put("mobUserId", e.l);
            hashMap3.put("nickname", TextUtils.isEmpty(str) ? "" : str);
            String str4 = "avatar";
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = str2;
            }
            hashMap3.put(str4, str3);
            String str5 = "appUserMap";
            if (hashMap != null) {
                hashMap2 = new HashMap<>();
            } else {
                hashMap2 = hashMap;
            }
            hashMap3.put(str5, hashMap2);
            try {
                if (!a.ac()) {
                    b.requestSynchronized(hashMap3, a + "/modify", false);
                    e.h = str;
                    e.i = str2;
                    e.j = hashMap;
                }
                Iterator it = e.n.iterator();
                while (it.hasNext()) {
                    ((UserWatcher) it.next()).onUserStateChange(e);
                }
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void c(final OnUserGotListener onUserGotListener) {
        if (e.l == null || !e.f()) {
            d(e.g, e.h, e.i, e.j, e.k);
        }
        if (onUserGotListener != null) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                public boolean handleMessage(Message message) {
                    onUserGotListener.onUserGot(MobUser.e);
                    return false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void e() {
        boolean z = false;
        if (e.l != null && !a.ac()) {
            HashMap hashMap = new HashMap();
            hashMap.put("mobUserId", e.l);
            try {
                b.requestSynchronized(hashMap, a + "/logout", false);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        if (!(e.l == null && e.g == null && e.h == null && e.i == null && e.j == null && e.k == null && e.f && e.m == 0)) {
            z = true;
        }
        e.l = null;
        e.g = null;
        e.h = null;
        e.i = null;
        e.j = null;
        e.k = null;
        e.f = true;
        e.m = 0;
        if (z) {
            Iterator it = e.n.iterator();
            while (it.hasNext()) {
                ((UserWatcher) it.next()).onUserStateChange(e);
            }
        }
    }

    static HashMap<String, String> a(String[] strArr) {
        try {
            if (!a.ac()) {
                HashMap hashMap = new HashMap();
                hashMap.put("appUserIds", strArr);
                hashMap.put("appkey", MobSDK.getAppkey());
                return (HashMap) b.requestSynchronized(hashMap, a + "/exchange", false);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    private MobUser() {
    }

    private boolean f() {
        return a.a() < this.m;
    }

    public boolean isAnonymous() {
        return this.f;
    }

    public String getMobUserId() {
        return this.l;
    }

    public String getId() {
        return this.g;
    }

    public String getNickName() {
        return this.h;
    }

    public String getAvatar() {
        return this.i;
    }

    public HashMap<String, Object> getExtraInfo() {
        return this.j;
    }

    static void a(UserWatcher userWatcher) {
        synchronized (e.n) {
            e.n.add(userWatcher);
        }
    }

    static void b(UserWatcher userWatcher) {
        synchronized (e.n) {
            e.n.remove(userWatcher);
        }
    }
}
