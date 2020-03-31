package com.tencent.android.tpush.c;

import android.content.Context;
import android.content.Intent;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.e;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.e.a;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c {
    private static c a = new c();
    private static final byte[] b = new byte[0];

    private c() {
    }

    public static c a() {
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.tencent.android.tpush.data.MessageId> a(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x001b
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ".tpns.msg.id"
            java.lang.Object r0 = r2.a(r3, r4, r0)
            if (r0 == 0) goto L_0x001b
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001a:
            return r0
        L_0x001b:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.c.c.a(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public void a(Context context, String str, ArrayList<MessageId> arrayList) {
        synchronized (b) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id", arrayList);
            }
        }
    }

    public ArrayList<Intent> a(Context context) {
        if (context != null) {
            return a.c(context);
        }
        return null;
    }

    public void a(Context context, Intent intent) {
        if (context != null) {
            a.a(context, intent);
        }
    }

    public void b(Context context) {
        if (context != null) {
            a.a(context);
        }
    }

    public void a(Context context, long j) {
        if (context != null) {
            a.f(context, j);
        }
    }

    public void b(Context context, long j) {
        if (context != null) {
            a.e(context, j);
        }
    }

    public void c(Context context, long j) {
        if (context != null) {
            a.a(context, j);
        }
    }

    public void d(Context context, long j) {
        if (context != null) {
            a.b(context, j);
        }
    }

    public void e(Context context, long j) {
        if (context != null) {
            a.c(context, j);
        }
    }

    public void f(Context context, long j) {
        if (context != null) {
            a.d(context, j);
        }
    }

    public void c(Context context) {
        if (context != null) {
            a.b(context);
        }
    }

    public void a(Context context, String str, MessageId messageId) {
        ArrayList arrayList;
        synchronized (b) {
            if (context != null) {
                if (!i.b(str) && messageId != null) {
                    ArrayList a2 = a(context, str);
                    if (a2 != null) {
                        int i = 0;
                        while (true) {
                            if (i >= a2.size()) {
                                arrayList = a2;
                                break;
                            } else if (((MessageId) a2.get(i)).id == messageId.id) {
                                a2.remove(i);
                                arrayList = a2;
                                break;
                            } else {
                                i++;
                            }
                        }
                    } else {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(messageId);
                    a(context, str, arrayList);
                }
            }
        }
    }

    public MessageId a(Context context, String str, long j) {
        if (context != null && !i.b(str) && j > 0) {
            ArrayList<MessageId> a2 = a(context, str);
            if (a2 != null && a2.size() > 0) {
                for (MessageId messageId : a2) {
                    if (messageId.id == j) {
                        return messageId;
                    }
                }
            }
        }
        return null;
    }

    public boolean b(Context context, String str, long j) {
        if (context != null && !i.b(str) && j > 0) {
            ArrayList<MessageId> a2 = a(context, str);
            if (a2 != null && a2.size() > 0) {
                for (MessageId messageId : a2) {
                    if (messageId.id == j) {
                        return messageId.isMsgAcked();
                    }
                }
            }
        }
        return false;
    }

    public void b(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (jSONObject.optInt("action", 2)) {
                case 1:
                    for (String valueOf : jSONObject.optString("pushIdList", "").split(StorageInterface.KEY_SPLITER)) {
                        a(context, Long.valueOf(valueOf).longValue());
                    }
                    return;
                case 2:
                    b(context);
                    return;
                case 3:
                    int optInt = jSONObject.optInt("enabled", -1);
                    com.tencent.android.tpush.b.a.f("MessageManager", "setLogToFile with cmd = " + optInt);
                    com.tencent.android.tpush.b.a.a(optInt);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("MessageManager", "onCrtlMsgHandle", e);
        }
        com.tencent.android.tpush.b.a.d("MessageManager", "onCrtlMsgHandle", e);
    }

    public static String g(Context context, long j) {
        String str = "" + h.a(context, "tpush_msgId_" + j, "");
        if (str == null || str.trim().length() == 0) {
            str = g.a(context, "tpush_msgId_" + j, true);
        }
        if (str != null && str.length() > 20480) {
            str = str.substring(0, str.indexOf("@@", Config.MAX_CACHE_JSON_CAPACIT_EXCEPTION));
        }
        return str != null ? str : "";
    }

    private void a(Context context, String str, String str2, ArrayList<?> arrayList) {
        try {
            if (arrayList.size() > 50) {
                arrayList.subList(0, 10).clear();
            }
            h.b(context, str + str2, Rijndael.encrypt(e.a((Serializable) arrayList)));
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("MessageManager", "putSettings", e);
        }
    }

    private Object a(Context context, String str, String str2) {
        boolean z = false;
        try {
            return e.a(Rijndael.decrypt(h.a(context, str + str2, (String) null)));
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("MessageManager", "getSettings", e);
            return z;
        }
    }

    public ArrayList<CachedMessageIntent> d(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String c : registerInfos) {
            ArrayList c2 = c(context, c);
            if (c2 != null && c2.size() > 0) {
                arrayList.addAll(c2);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0015 A[Catch:{ Throwable -> 0x001b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.tencent.android.tpush.data.CachedMessageIntent> c(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x0022
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)     // Catch:{ Throwable -> 0x001b }
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = ".tpns.msg.id.cached"
            java.lang.Object r0 = r2.a(r3, r4, r0)     // Catch:{ Throwable -> 0x001b }
            if (r0 == 0) goto L_0x0022
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x001b }
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x001b }
            r0.<init>()     // Catch:{ Throwable -> 0x001b }
        L_0x001a:
            return r0
        L_0x001b:
            r0 = move-exception
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            goto L_0x001a
        L_0x0022:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.c.c.c(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public void a(Context context, String str, Intent intent) {
        ArrayList arrayList;
        synchronized (b) {
            if (context != null) {
                if (!i.b(str) && intent != null) {
                    CachedMessageIntent cachedMessageIntent = new CachedMessageIntent();
                    cachedMessageIntent.pkgName = str;
                    cachedMessageIntent.msgId = intent.getLongExtra(MessageKey.MSG_ID, -1);
                    cachedMessageIntent.intent = Rijndael.encrypt(intent.toUri(1));
                    ArrayList c = c(context, str);
                    if (c == null) {
                        arrayList = new ArrayList();
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        for (int i = 0; i < c.size(); i++) {
                            CachedMessageIntent cachedMessageIntent2 = (CachedMessageIntent) c.get(i);
                            if (cachedMessageIntent2.equals(cachedMessageIntent)) {
                                arrayList2.add(cachedMessageIntent2);
                            }
                        }
                        c.removeAll(arrayList2);
                        arrayList = c;
                    }
                    if (arrayList != null && arrayList.size() > 45) {
                        int size = arrayList.size() - 45;
                        if (size >= 0) {
                            com.tencent.android.tpush.b.a.g("MessageManager", "too much cache msg, try to cut " + size + " list.size: " + arrayList.size());
                            arrayList.subList(0, size).clear();
                        }
                    }
                    arrayList.add(cachedMessageIntent);
                    b(context, str, arrayList);
                }
            }
        }
    }

    public void b(Context context, String str, ArrayList<CachedMessageIntent> arrayList) {
        synchronized (b) {
            if (!(context == null || arrayList == null)) {
                com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "updateCachedMsgIntentByPkgName, size: " + arrayList.size());
                a(context, str, ".tpns.msg.id.cached", arrayList);
            }
        }
    }

    public void d(Context context, String str) {
        synchronized (b) {
            if (context != null) {
                b(context, str, new ArrayList<>());
            }
        }
    }

    public void c(Context context, String str, long j) {
        synchronized (b) {
            if (context != null) {
                ArrayList c = c(context, str);
                if (c != null && c.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < c.size(); i++) {
                        CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) c.get(i);
                        if (cachedMessageIntent.msgId == j) {
                            arrayList.add(cachedMessageIntent);
                        }
                    }
                    if (arrayList != null && arrayList.size() == 0) {
                        com.tencent.android.tpush.b.a.i("MessageManager", "deleteCachedMsgIntentByPkgName do not have MessageId = " + j);
                    }
                    c.removeAll(arrayList);
                }
                b(context, str, c);
            }
        }
    }

    public void a(Context context, List<CachedMessageIntent> list, ArrayList<CachedMessageIntent> arrayList) {
        synchronized (b) {
            if (!(context == null || list == null)) {
                if (list.size() > 0) {
                    try {
                        ArrayList arrayList2 = new ArrayList();
                        if (arrayList != null && arrayList.size() > 0) {
                            HashMap hashMap = new HashMap();
                            int i = 0;
                            while (true) {
                                int i2 = i;
                                if (i2 >= arrayList.size()) {
                                    break;
                                }
                                CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) arrayList.get(i2);
                                for (CachedMessageIntent cachedMessageIntent2 : list) {
                                    if (cachedMessageIntent.equals(cachedMessageIntent2)) {
                                        arrayList2.add(cachedMessageIntent);
                                        ArrayList arrayList3 = (ArrayList) hashMap.get(cachedMessageIntent2.pkgName);
                                        if (arrayList3 == null) {
                                            arrayList3 = new ArrayList();
                                        }
                                        hashMap.put(cachedMessageIntent2.pkgName, arrayList3);
                                    }
                                }
                                i = i2 + 1;
                            }
                            arrayList.removeAll(arrayList2);
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                CachedMessageIntent cachedMessageIntent3 = (CachedMessageIntent) it.next();
                                ArrayList arrayList4 = (ArrayList) hashMap.get(cachedMessageIntent3.pkgName);
                                if (arrayList4 == null) {
                                    arrayList4 = new ArrayList();
                                }
                                arrayList4.add(cachedMessageIntent3);
                                hashMap.put(cachedMessageIntent3.pkgName, arrayList4);
                            }
                            for (String str : hashMap.keySet()) {
                                b(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Exception e) {
                        com.tencent.android.tpush.b.a.d("MessageManager", "deleteCachedMsgIntent", e);
                    }
                }
            }
        }
    }
}
