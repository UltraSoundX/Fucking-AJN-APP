package com.mob.commons.utag;

import com.baidu.mobstat.Config;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.g;
import com.mob.commons.h;
import com.mob.tools.MobLog;
import com.mob.tools.RxMob;
import com.mob.tools.RxMob.QuickSubscribe;
import com.mob.tools.RxMob.Subscriber;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.tencent.mid.api.MidEntity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class TagRequester implements PublicMemberKeeper {
    private static HashMap<String, Object> a;
    /* access modifiers changed from: private */
    public static DeviceHelper b = DeviceHelper.getInstance(MobSDK.getContext());
    /* access modifiers changed from: private */
    public static boolean c;
    /* access modifiers changed from: private */
    public UserTagsResponse d;
    /* access modifiers changed from: private */
    public UserTagError e;

    public interface UserTagsResponse {
        void onResponse(Map<String, Object> map);
    }

    TagRequester() {
    }

    public TagRequester whenSuccess(UserTagsResponse userTagsResponse) {
        this.d = userTagsResponse;
        return this;
    }

    public TagRequester whenError(UserTagError userTagError) {
        this.e = userTagError;
        return this;
    }

    public synchronized void request() {
        RxMob.create(new QuickSubscribe<HashMap<String, Object>>() {
            /* access modifiers changed from: protected */
            public void doNext(Subscriber<HashMap<String, Object>> subscriber) throws Throwable {
                if (a.ac()) {
                    throw new ForbThrowable();
                }
                TagRequester.d();
                subscriber.onNext(TagRequester.e());
            }
        }).subscribeOnNewThreadAndObserveOnUIThread(new Subscriber<HashMap<String, Object>>() {
            /* renamed from: a */
            public void onNext(HashMap<String, Object> hashMap) {
                if (TagRequester.this.d != null) {
                    TagRequester.this.d.onResponse(hashMap);
                }
            }

            public void onError(Throwable th) {
                if (TagRequester.this.e != null) {
                    TagRequester.this.e.onError(th);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static synchronized void d() {
        boolean z = true;
        synchronized (TagRequester.class) {
            a = g.k();
            if (a == null || !a.containsKey("defHost") || !a.containsKey("defPort") || !a.containsKey("defSSLPort") || !a.containsKey("tagExpire")) {
                g.a(null);
                a = new HashMap<>();
                a.put("defHost", "api.utag.mob.com");
                a.put("defPort", Integer.valueOf(80));
                a.put("defSSLPort", Integer.valueOf(443));
                a.put("tagExpire", Integer.valueOf(86400));
            } else {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(g.j());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(instance.getTime());
                instance.setTimeInMillis(System.currentTimeMillis());
                if (format.equals(simpleDateFormat.format(instance.getTime()))) {
                    z = false;
                }
            }
            if (z && !c) {
                c = true;
                new Thread() {
                    public void run() {
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put("duid", DeviceAuthorizer.authorize(null));
                            hashMap.put(MidEntity.TAG_MAC, TagRequester.b.getMacAddress());
                            hashMap.put(MidEntity.TAG_IMEI, TagRequester.b.getIMEI());
                            hashMap.put("serialno", TagRequester.b.getSerialno());
                            hashMap.put("model", TagRequester.b.getModel());
                            hashMap.put("appkey", MobSDK.getAppkey());
                            hashMap.put("apppkg", TagRequester.b.getPackageName());
                            hashMap.put("appver", TagRequester.b.getAppVersionName());
                            hashMap.put("plat", Integer.valueOf(1));
                            g.a(TagRequester.b(hashMap, h.b("http://api.utag.mob.com/conf")));
                        } catch (Throwable th) {
                            MobLog.getInstance().w(th);
                        }
                        TagRequester.c = false;
                    }
                }.start();
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized HashMap<String, Object> e() throws Throwable {
        HashMap<String, Object> l;
        String str;
        int i;
        synchronized (TagRequester.class) {
            l = g.l();
            if (l == null || l.isEmpty()) {
                Object obj = a.get("defPort");
                if (obj == null || !(obj instanceof Integer)) {
                    str = null;
                } else {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue <= 0) {
                        str = "";
                    } else {
                        str = Config.TRACE_TODAY_VISIT_SPLIT + intValue;
                    }
                }
                String b2 = h.b("http://" + a.get("defHost") + str + "/utag");
                HashMap hashMap = new HashMap();
                hashMap.put("duid", DeviceAuthorizer.authorize(null));
                hashMap.put(MidEntity.TAG_MAC, b.getMacAddress());
                hashMap.put(MidEntity.TAG_IMEI, b.getIMEI());
                hashMap.put("serialno", b.getSerialno());
                hashMap.put("model", b.getModel());
                hashMap.put("appkey", MobSDK.getAppkey());
                hashMap.put("apppkg", b.getPackageName());
                hashMap.put("appver", b.getAppVersionName());
                hashMap.put("plat", Integer.valueOf(1));
                l = b(hashMap, b2);
                try {
                    i = Integer.parseInt(String.valueOf(a.get("tagExpire")));
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                    i = 0;
                }
                g.a(l, i);
            }
        }
        return l;
    }

    /* access modifiers changed from: private */
    public static HashMap<String, Object> b(HashMap<String, Object> hashMap, String str) throws Throwable {
        return (HashMap) new MobCommunicator(1024, "e3e28dce5fe8fc1bb56a25964219d5dc2976edb171b99b1103c2c4f89ad0b66fb58669fe69eb0b5d11e8be990b0715b4de2b4e5a5dcce121f47f18063d5d99f9", "256f461cc45979b52264ac022ff1353ea5f8140d35686ffdae2faee09db2006c3b43c2bb74ce6f4c51698db6384c1c0ceca958208d65c7ed345a04ea6349ca39601818c3d5500565ba49ed49c0f4014b06980d17fc069c95d30092d0cfdaddf783ea96c5f8bdc42b6765d71a5d12192ef74646b41d92f1caeba3123e71938d39").requestSynchronized(hashMap, str, false);
    }
}
