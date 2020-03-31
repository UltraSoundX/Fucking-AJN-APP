package com.mob.commons;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: ProcessLevelSPDB */
public class g {
    private static volatile boolean a = false;
    private static SharePrefrenceHelper b;

    private static synchronized void w() {
        synchronized (g.class) {
            a(false);
        }
    }

    private static synchronized void a(boolean z) {
        synchronized (g.class) {
            if (b == null || z) {
                b = new SharePrefrenceHelper(MobSDK.getContext());
                b.open("mob_commons", 1);
            }
        }
    }

    public static synchronized String a() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_ext_info");
        }
        return string;
    }

    public static synchronized void a(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_ext_info", str);
        }
    }

    public static synchronized void a(long j) {
        synchronized (g.class) {
            w();
            b.putLong("wifi_last_time", Long.valueOf(j));
        }
    }

    public static synchronized String b() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("wifi_last_info");
        }
        return string;
    }

    public static synchronized void b(String str) {
        synchronized (g.class) {
            w();
            b.putString("wifi_last_info", str);
        }
    }

    public static synchronized void c(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_cellinfo", str);
        }
    }

    public static synchronized String c() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_cellinfo");
        }
        return string;
    }

    public static synchronized long d() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_cellinfo_next_total");
        }
        return j;
    }

    public static synchronized void b(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_cellinfo_next_total", Long.valueOf(j));
        }
    }

    public static synchronized long e() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_art_next_total");
        }
        return j;
    }

    public static synchronized void c(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_art_next_total", Long.valueOf(j));
        }
    }

    public static synchronized void d(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_switches", str);
        }
    }

    public static synchronized String f() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_switches");
        }
        return string;
    }

    public static synchronized void e(String str) {
        synchronized (g.class) {
            w();
            if (str == null) {
                b.remove("key_data_url");
            } else {
                b.putString("key_data_url", str);
            }
        }
    }

    public static synchronized String g() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_data_url");
        }
        return string;
    }

    public static synchronized void f(String str) {
        synchronized (g.class) {
            w();
            if (str == null) {
                b.remove("key_conf_url");
            } else {
                b.putString("key_conf_url", str);
            }
        }
    }

    public static synchronized String h() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_conf_url");
        }
        return string;
    }

    public static synchronized String i() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_wifi_list_hash");
        }
        return string;
    }

    public static synchronized void g(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_wifi_list_hash", str);
        }
    }

    public static synchronized long j() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_last_utag_config");
        }
        return j;
    }

    public static synchronized HashMap<String, Object> k() {
        HashMap<String, Object> fromJson;
        synchronized (g.class) {
            w();
            String string = b.getString("key_utag_config");
            if (TextUtils.isEmpty(string)) {
                fromJson = null;
            } else {
                fromJson = new Hashon().fromJson(string);
            }
        }
        return fromJson;
    }

    public static synchronized void a(HashMap<String, Object> hashMap) {
        synchronized (g.class) {
            w();
            if (hashMap == null) {
                b.remove("key_utag_config");
            } else {
                b.putString("key_utag_config", new Hashon().fromHashMap(hashMap));
                b.putLong("key_last_utag_config", Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public static synchronized HashMap<String, Object> l() {
        HashMap<String, Object> hashMap = null;
        synchronized (g.class) {
            w();
            if (System.currentTimeMillis() <= b.getLong("key_utags_buffer_time")) {
                String string = b.getString("key_utags");
                if (!TextUtils.isEmpty(string)) {
                    hashMap = new Hashon().fromJson(string);
                }
            }
        }
        return hashMap;
    }

    public static synchronized void a(HashMap<String, Object> hashMap, int i) {
        synchronized (g.class) {
            w();
            b.putString("key_utags_buffer_time", new Hashon().fromHashMap(hashMap));
            b.putLong("key_utags_buffer_time", Long.valueOf(System.currentTimeMillis() + ((long) (i * 1000))));
        }
    }

    public static synchronized String m() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_simulator_info_md5");
        }
        return string;
    }

    public static synchronized void h(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_simulator_info_md5", str);
        }
    }

    public static synchronized void d(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_backend_time", Long.valueOf(j));
        }
    }

    public static synchronized String n() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_lduid");
        }
        return string;
    }

    public static synchronized void i(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_lduid", str);
        }
    }

    public static synchronized long o() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_next_dev_ext_info_upload_time");
        }
        return j;
    }

    public static synchronized void e(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_next_dev_ext_info_upload_time", Long.valueOf(j));
        }
    }

    public static synchronized long p() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_next_upload_wifi_list_time");
        }
        return j;
    }

    public static synchronized void f(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_next_upload_wifi_list_time", Long.valueOf(j));
        }
    }

    public static synchronized void j(String str) {
        synchronized (g.class) {
            w();
            b.putString("key_buffered_location_md5", str);
        }
    }

    public static synchronized String q() {
        String string;
        synchronized (g.class) {
            w();
            string = b.getString("key_buffered_location_md5");
        }
        return string;
    }

    public static synchronized long r() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_next_upload_buffered_location_time");
        }
        return j;
    }

    public static synchronized void g(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_next_upload_buffered_location_time", Long.valueOf(j));
        }
    }

    public static synchronized long s() {
        long j;
        synchronized (g.class) {
            w();
            j = b.getLong("key_next_upload_app_active_time");
        }
        return j;
    }

    public static synchronized void h(long j) {
        synchronized (g.class) {
            w();
            b.putLong("key_next_upload_app_active_time", Long.valueOf(j));
        }
    }

    public static synchronized HashMap<Long, Long> t() {
        HashMap hashMap;
        synchronized (g.class) {
            w();
            String string = b.getString("key_app_active_time");
            HashMap hashMap2 = new HashMap();
            if (TextUtils.isEmpty(string)) {
                hashMap = hashMap2;
            } else {
                HashMap fromJson = new Hashon().fromJson(string);
                if (fromJson != null && !fromJson.isEmpty()) {
                    for (Entry entry : fromJson.entrySet()) {
                        if (entry != null) {
                            try {
                                hashMap2.put(Long.valueOf(Long.parseLong((String) entry.getKey())), entry.getValue());
                            } catch (Throwable th) {
                                MobLog.getInstance().d(th, "Parse long error", new Object[0]);
                            }
                        }
                    }
                }
                hashMap = hashMap2;
            }
        }
        return hashMap;
    }

    public static synchronized void b(HashMap<Long, Long> hashMap) {
        synchronized (g.class) {
            w();
            if (hashMap == null || hashMap.isEmpty()) {
                b.remove("key_app_active_time");
            } else {
                try {
                    HashMap hashMap2 = new HashMap();
                    for (Entry entry : hashMap.entrySet()) {
                        if (entry != null) {
                            hashMap2.put(String.valueOf(entry.getKey()), entry.getValue());
                        }
                    }
                    b.putString("key_app_active_time", new Hashon().fromHashMap(hashMap2));
                } catch (Throwable th) {
                    MobLog.getInstance().d(th, "Parse String error", new Object[0]);
                }
            }
        }
        return;
    }

    public static synchronized HashMap<String, Object> u() {
        HashMap<String, Object> fromJson;
        synchronized (g.class) {
            w();
            String string = b.getString("key_channels");
            if (TextUtils.isEmpty(string)) {
                fromJson = null;
            } else {
                fromJson = new Hashon().fromJson(string);
            }
        }
        return fromJson;
    }

    public static synchronized void c(HashMap<String, Object> hashMap) {
        synchronized (g.class) {
            w();
            b.putString("key_channels", new Hashon().fromHashMap(hashMap));
        }
    }

    public static void v() {
        if (!a) {
            a = true;
            new Thread() {
                public void run() {
                    synchronized (d.a) {
                        try {
                            c.a().a(11);
                            d.a.wait();
                            HashMap hashMap = (HashMap) a.ag();
                            if (hashMap != null && hashMap.size() > 0) {
                                c.a().a(12);
                                Object obj = hashMap.get("h");
                                Object obj2 = hashMap.get(Config.APP_KEY);
                                Object obj3 = hashMap.get("b");
                                Object obj4 = hashMap.get("s");
                                Object obj5 = hashMap.get("cn");
                                Object obj6 = hashMap.get("fn");
                                hashMap.clear();
                                b.a().a(obj, obj2, obj3, obj4, obj5, obj6);
                            }
                        } catch (Throwable th) {
                            c.a().a(3, th);
                        }
                    }
                }
            }.start();
        }
    }
}
