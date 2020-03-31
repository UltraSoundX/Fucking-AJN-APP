package com.mob.commons.utag;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.h;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SQLiteHelper;
import com.mob.tools.utils.SQLiteHelper.SingleTableDB;
import com.tencent.mid.api.MidEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public final class UserTager implements PublicMemberKeeper {
    private static Handler a = MobHandlerThread.newHandler("t", (Callback) new Callback() {
        public boolean handleMessage(Message message) {
            if (!a.ac()) {
                switch (message.what) {
                    case 1:
                        if (!UserTager.d()) {
                            UserTager.c();
                            break;
                        }
                        break;
                    case 2:
                        Object[] objArr = (Object[]) message.obj;
                        UserTager.b((HashMap) objArr[0], (UserTagError) objArr[1]);
                        UserTager.c();
                        break;
                }
            }
            return false;
        }
    });
    private static SingleTableDB b = SQLiteHelper.getDatabase(MobSDK.getContext(), "UserTag_1");
    private static Hashon c = new Hashon();
    private static DeviceHelper d = DeviceHelper.getInstance(MobSDK.getContext());
    private HashMap<String, Object> e = new HashMap<>();
    private UserTagError f;

    public class CustomTag implements PublicMemberKeeper {
        private UserTager b;
        private String c;

        private CustomTag(UserTager userTager, String str) {
            this.b = userTager;
            this.c = str;
        }

        public UserTager withValue(Number number) {
            return a(number);
        }

        public UserTager withValue(Boolean bool) {
            return a(bool);
        }

        public UserTager withValue(String str) {
            return a(str);
        }

        private UserTager a(Object obj) {
            this.b.a(this.c, obj);
            return this.b;
        }
    }

    static {
        b.addField("time", "text", true);
        b.addField("data", "text", true);
        c();
    }

    /* access modifiers changed from: private */
    public static void c() {
        a.removeMessages(1);
        long j = OkHttpUtils.DEFAULT_MILLISECONDS;
        String networkType = d.getNetworkType();
        if (networkType == null || "none".equals(networkType)) {
            j = 600000;
        }
        a.sendEmptyMessageDelayed(1, j);
    }

    /* access modifiers changed from: private */
    public static void b(HashMap<String, Object> hashMap, UserTagError userTagError) {
        IllegalArgumentException illegalArgumentException;
        Iterator it = hashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                illegalArgumentException = null;
                break;
            }
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            if (str == null) {
                illegalArgumentException = new IllegalArgumentException("found a key of null");
                break;
            } else if (str.length() > 30) {
                illegalArgumentException = new IllegalArgumentException("key '" + str + "' is too long: " + str.length() + " > " + 30);
                break;
            } else {
                Object value = entry.getValue();
                if (value != null && (value instanceof String)) {
                    String str2 = (String) value;
                    if (str2.length() > 255) {
                        illegalArgumentException = new IllegalArgumentException("value '" + str2 + "' is too long: " + str2.length() + " > " + 255);
                        break;
                    }
                }
            }
        }
        if (illegalArgumentException == null) {
            try {
                long a2 = a.a();
                hashMap.put("___datetime", Long.valueOf(a2));
                ContentValues contentValues = new ContentValues();
                contentValues.put("time", String.valueOf(a2));
                contentValues.put("data", c.fromHashMap(hashMap));
                SQLiteHelper.insert(b, contentValues);
            } catch (Throwable th) {
                if (userTagError != null) {
                    userTagError.onError(th);
                }
            }
        } else if (userTagError != null) {
            userTagError.onError(illegalArgumentException);
        }
    }

    /* access modifiers changed from: private */
    public static boolean d() {
        String networkType = d.getNetworkType();
        if (networkType == null || "none".equals(networkType) || a.I()) {
            return false;
        }
        ArrayList e2 = e();
        if (e2 != null && e2.size() > 0) {
            if (!a(e2)) {
                return false;
            }
            b(e2);
            if (e2.size() == 50) {
                d();
            }
        }
        return true;
    }

    private static ArrayList<HashMap<String, Object>> e() {
        try {
            Cursor query = SQLiteHelper.query(b, new String[]{"data"}, null, null, null);
            if (query == null) {
                return null;
            }
            if (!query.moveToFirst()) {
                query.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            do {
                arrayList.add(c.fromJson(query.getString(0)));
                if (arrayList.size() > 50) {
                    break;
                }
            } while (query.moveToNext());
            query.close();
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private static boolean a(ArrayList<HashMap<String, Object>> arrayList) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("plat", Integer.valueOf(1));
            hashMap.put(MidEntity.TAG_MAC, d.getMacAddress());
            hashMap.put("duid", DeviceAuthorizer.authorize(null));
            hashMap.put("model", d.getModel());
            hashMap.put(MidEntity.TAG_IMEI, d.getIMEI());
            hashMap.put("serialno", d.getSerialno());
            hashMap.put("appkey", MobSDK.getAppkey());
            hashMap.put("apppkg", d.getPackageName());
            hashMap.put("appver", d.getAppVersionName());
            hashMap.put("datas", arrayList);
            new MobCommunicator(1024, "e3e28dce5fe8fc1bb56a25964219d5dc2976edb171b99b1103c2c4f89ad0b66fb58669fe69eb0b5d11e8be990b0715b4de2b4e5a5dcce121f47f18063d5d99f9", "256f461cc45979b52264ac022ff1353ea5f8140d35686ffdae2faee09db2006c3b43c2bb74ce6f4c51698db6384c1c0ceca958208d65c7ed345a04ea6349ca39601818c3d5500565ba49ed49c0f4014b06980d17fc069c95d30092d0cfdaddf783ea96c5f8bdc42b6765d71a5d12192ef74646b41d92f1caeba3123e71938d39").requestSynchronized(hashMap, h.b("http://api.utag.mob.com/bdata"), false);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }

    private static void b(ArrayList<HashMap<String, Object>> arrayList) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap hashMap = (HashMap) it.next();
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append('\'').append(hashMap.get("___datetime")).append('\'');
            }
            SQLiteHelper.delete(b, "time in (" + sb.toString() + ")", null);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    UserTager() {
    }

    public CustomTag set(String str) {
        return new CustomTag(this, str);
    }

    /* access modifiers changed from: private */
    public void a(String str, Object obj) {
        this.e.put(str, obj);
    }

    public UserTager whenError(UserTagError userTagError) {
        this.f = userTagError;
        return this;
    }

    public void commit() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.e);
        Message message = new Message();
        message.what = 2;
        message.obj = new Object[]{hashMap, this.f};
        a.sendMessage(message);
    }
}
