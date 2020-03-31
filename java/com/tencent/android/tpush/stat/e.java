package com.tencent.android.tpush.stat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import com.stub.StubApp;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.d;
import com.tencent.android.tpush.stat.event.a;
import com.tencent.android.tpush.stat.event.a.C0072a;
import com.tencent.android.tpush.stat.event.b;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e {
    static volatile long a = 0;
    /* access modifiers changed from: private */
    public static Map<String, Long> b = new ConcurrentHashMap();
    private static volatile Handler c = null;
    private static volatile int d = 0;
    /* access modifiers changed from: private */
    public static volatile String e = "";
    /* access modifiers changed from: private */
    public static volatile String f = "";
    /* access modifiers changed from: private */
    public static d g = c.b();
    /* access modifiers changed from: private */
    public static UncaughtExceptionHandler h = null;
    /* access modifiers changed from: private */
    public static Context i = null;
    private static String j = null;
    /* access modifiers changed from: private */
    public static volatile SharedPreferences k = null;

    public static Context a(Context context) {
        return context != null ? context : i;
    }

    public static void b(Context context) {
        if (context != null) {
            i = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
    }

    static boolean c(Context context) {
        long a2 = com.tencent.android.tpush.stat.a.e.a(context, b.c, 0);
        long a3 = c.a("2.0.6");
        boolean z = true;
        if (a3 <= a2) {
            g.e("MTA is disable for current version:" + a3 + ",wakeup version:" + a2);
            z = false;
        }
        b.a(z);
        return z;
    }

    static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    static synchronized void d(Context context) {
        synchronized (e.class) {
            if (context != null) {
                if (c == null && c(context)) {
                    final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                    i = origApplicationContext;
                    HandlerThread handlerThread = new HandlerThread("XgStat");
                    handlerThread.start();
                    c = new Handler(handlerThread.getLooper());
                    c.post(new Runnable() {
                        public void run() {
                            a.a(e.i).e();
                            c.a(origApplicationContext, true);
                            d.b(origApplicationContext);
                            e.h = Thread.getDefaultUncaughtExceptionHandler();
                            if (b.a() == StatReportStrategy.APP_LAUNCH) {
                                e.a(origApplicationContext, -1);
                            }
                            if (b.b()) {
                                e.g.h("Init MTA StatService success.");
                            }
                            String c = c.c(e.i);
                            if (c == null || c.trim().length() == 0) {
                                c = "default";
                            }
                            e.k = origApplicationContext.getSharedPreferences("." + (c + ".xg.stat."), 0);
                        }
                    });
                }
            }
        }
    }

    public static Handler e(Context context) {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    try {
                        d(context);
                    } catch (Throwable th) {
                        g.a(th);
                        b.a(false);
                    }
                }
            }
        }
        return c;
    }

    static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (b.b.d != 0) {
                jSONObject2.put("v", b.b.d);
            }
            jSONObject.put(Integer.toString(b.b.a), jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            if (b.a.d != 0) {
                jSONObject3.put("v", b.a.d);
            }
            jSONObject.put(Integer.toString(b.a.a), jSONObject3);
        } catch (JSONException e2) {
            g.b((Throwable) e2);
        }
        return jSONObject;
    }

    static void a(Context context, long j2) {
        a((b) new com.tencent.android.tpush.stat.event.e(context, d, a(), j2));
    }

    static int b(Context context, long j2) {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (a == 0) {
            a = com.tencent.android.tpush.stat.a.e.a(i, "_INTER_MTA_NEXT_DAY", 0);
        }
        if (d != 0 && currentTimeMillis < a) {
            z = false;
        }
        if (z) {
            d = c.a();
            a = c.c();
            com.tencent.android.tpush.stat.a.e.b(i, "_INTER_MTA_NEXT_DAY", a);
            a(context, j2);
        }
        return d;
    }

    public static void a(Context context, final b bVar) {
        if (b.c()) {
            if (i == null) {
                d(context);
            }
            if (e(a(context)) != null) {
                c.post(new Runnable() {
                    public void run() {
                        try {
                            e.a(bVar);
                        } catch (Throwable th) {
                            e.g.b(th);
                        }
                    }
                });
            }
        }
    }

    public static void a(Context context, String str, Properties properties, long j2, long j3) {
        if (b.c()) {
            final Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (a(str)) {
                g.e("The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                final C0072a aVar = new C0072a(str, null, properties);
                if (e(a2) != null) {
                    final long j4 = j2;
                    final long j5 = j3;
                    c.post(new Runnable() {
                        public void run() {
                            try {
                                a aVar = new a(a2, e.b(a2, j4), aVar.a, j4, j5);
                                aVar.a().c = aVar.c;
                                e.a((b) aVar);
                            } catch (Throwable th) {
                                e.g.b(th);
                            }
                        }
                    });
                }
            }
        }
    }

    public static void a(Context context, final ArrayList<TpnsPushMsg> arrayList) {
        if (b.c()) {
            final Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                g.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a2) != null) {
                c.post(new Runnable() {
                    public void run() {
                        try {
                            ArrayList arrayList = new ArrayList(arrayList.size());
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                TpnsPushMsg tpnsPushMsg = (TpnsPushMsg) it.next();
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("type", "" + tpnsPushMsg.type);
                                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + tpnsPushMsg.busiMsgId);
                                jSONObject.put(MessageKey.MSG_ID, "" + tpnsPushMsg.msgId);
                                a aVar = new a(a2, e.b(a2, tpnsPushMsg.accessId), "SrvAck", tpnsPushMsg.accessId, tpnsPushMsg.timestamp);
                                aVar.a().c = jSONObject;
                                arrayList.add(aVar);
                            }
                            e.a((List<b>) arrayList);
                        } catch (Throwable th) {
                            e.g.b(th);
                        }
                    }
                });
            }
        }
    }

    public static void b(Context context, final ArrayList<TpnsPushClientReport> arrayList) {
        if (b.c()) {
            final Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                g.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a2) != null) {
                c.post(new Runnable() {
                    public void run() {
                        try {
                            ArrayList arrayList = new ArrayList(arrayList.size());
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                TpnsPushClientReport tpnsPushClientReport = (TpnsPushClientReport) it.next();
                                long j = tpnsPushClientReport.type;
                                long j2 = tpnsPushClientReport.timestamp;
                                long j3 = tpnsPushClientReport.broadcastId;
                                long j4 = tpnsPushClientReport.msgId;
                                long j5 = tpnsPushClientReport.accessId;
                                long j6 = tpnsPushClientReport.channelId;
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("type", "" + j);
                                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + j3);
                                jSONObject.put(MessageKey.MSG_ID, "" + j4);
                                jSONObject.put(MessageKey.MSG_CHANNEL_ID, "" + j6);
                                a aVar = new a(a2, e.b(a2, j5), "Ack", j5, j2);
                                aVar.a().c = jSONObject;
                                arrayList.add(aVar);
                            }
                            e.a((List<b>) arrayList);
                        } catch (Throwable th) {
                            e.g.b(th);
                        }
                    }
                });
            }
        }
    }

    public static void c(Context context, final ArrayList<TpnsClickClientReport> arrayList) {
        if (b.c()) {
            final Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                g.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a2) != null) {
                c.post(new Runnable() {
                    public void run() {
                        try {
                            ArrayList arrayList = new ArrayList(arrayList.size());
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                TpnsClickClientReport tpnsClickClientReport = (TpnsClickClientReport) it.next();
                                long j = tpnsClickClientReport.type;
                                long j2 = tpnsClickClientReport.timestamp;
                                long j3 = tpnsClickClientReport.broadcastId;
                                long j4 = tpnsClickClientReport.msgId;
                                long j5 = tpnsClickClientReport.accessId;
                                long j6 = tpnsClickClientReport.action;
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("type", "" + j);
                                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + j3);
                                jSONObject.put(MessageKey.MSG_ID, "" + j4);
                                jSONObject.put("action", "" + j6);
                                a aVar = new a(a2, e.b(a2, j5), "Action", j5, j2);
                                aVar.a().c = jSONObject;
                                arrayList.add(aVar);
                            }
                            e.a((List<b>) arrayList);
                        } catch (Throwable th) {
                            e.g.b(th);
                        }
                    }
                });
            }
        }
    }

    public static void a(Context context, String str, Properties properties, long j2) {
        if (b.c()) {
            final Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (a(str)) {
                g.e("The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                final C0072a aVar = new C0072a(str, null, properties);
                if (e(a2) != null) {
                    final long j3 = j2;
                    final String str2 = str;
                    c.post(new Runnable() {
                        public void run() {
                            try {
                                a aVar = new a(a2, e.b(a2, j3), str2, j3, System.currentTimeMillis());
                                aVar.a().c = aVar.c;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(aVar);
                                e.a((List<b>) arrayList);
                            } catch (Throwable th) {
                                e.g.b(th);
                            }
                        }
                    });
                }
            }
        }
    }

    public static void a(Context context, int i2) {
        if (b.c()) {
            if (b.b()) {
                g.b((Object) "commitEvents, maxNumber=" + i2);
            }
            Context a2 = a(context);
            if (a2 == null) {
                g.e("The Context of StatService.commitEvents() can not be null!");
            } else if (i2 < -1 || i2 == 0) {
                g.e("The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
            } else if (a.a(a2).c() && e(a2) != null) {
                c.post(new Runnable() {
                    public void run() {
                        try {
                            e.b();
                        } catch (Throwable th) {
                            e.g.b(th);
                        }
                    }
                });
            }
        }
    }

    static void a(final List<b> list) {
        g.h("sentEventList size:" + list.size());
        if (a.a(i).c()) {
            d.b(i).b(list, new c() {
                public void a() {
                }

                public void b() {
                    e.b(list);
                }
            });
        } else {
            b(list);
        }
    }

    static void a(final b bVar) {
        if (a.a(i).c()) {
            d.b(i).a(bVar, (c) new c() {
                public void a() {
                    e.g.h("send Event sucess:" + bVar.b());
                }

                public void b() {
                    e.b(Arrays.asList(new b[]{bVar}));
                }
            });
            return;
        }
        b(Arrays.asList(new b[]{bVar}));
    }

    static synchronized void b(List<?> list) {
        synchronized (e.class) {
            if (list != null) {
                try {
                    if (k != null) {
                        g.h("store event size:" + list.size());
                        Editor edit = k.edit();
                        for (Object obj : list) {
                            edit.putLong(obj.toString(), System.currentTimeMillis());
                        }
                        edit.commit();
                    }
                } catch (Exception e2) {
                    g.b((Throwable) e2);
                }
            }
        }
        return;
    }

    static synchronized void c(List<?> list) {
        synchronized (e.class) {
            if (list != null) {
                try {
                    if (k != null) {
                        g.h("delete event size:" + list.size());
                        Editor edit = k.edit();
                        for (Object obj : list) {
                            edit.remove(obj.toString());
                        }
                        edit.commit();
                    }
                } catch (Exception e2) {
                    g.b((Throwable) e2);
                }
            }
        }
        return;
    }

    static synchronized void d(List<?> list) {
        synchronized (e.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis() - 259200000;
                if (!(list == null || k == null)) {
                    Editor edit = k.edit();
                    for (Object obj : list) {
                        String obj2 = obj.toString();
                        if (k.getLong(obj2, 1) < currentTimeMillis) {
                            edit.remove(obj2);
                        }
                    }
                    edit.commit();
                }
            } catch (Exception e2) {
                g.b((Throwable) e2);
            }
        }
        return;
    }

    static void e(final List<String> list) {
        if (list != null && list.size() != 0) {
            d.b(i).b(list, new c() {
                public void a() {
                    e.c(list);
                }

                public void b() {
                    e.d(list);
                }
            });
        }
    }

    public static void a(final Context context, String str, final long j2) {
        final String str2 = new String(str);
        if (e(context) != null) {
            c.post(new Runnable() {
                public void run() {
                    try {
                        synchronized (e.b) {
                            if (e.b.size() >= b.f()) {
                                e.g.e("The number of page events exceeds the maximum value " + Integer.toString(b.f()));
                                return;
                            }
                            e.e = str2;
                            if (e.b.containsKey(e.e)) {
                                e.g.f("Duplicate PageID : " + e.e + ", onResume() repeated?");
                                return;
                            }
                            e.b.put(e.e, Long.valueOf(System.currentTimeMillis()));
                            e.b(context, j2);
                        }
                    } catch (Throwable th) {
                        e.g.b(th);
                    }
                }
            });
        }
    }

    public static void b(Context context, String str, long j2) {
        if (b.c()) {
            Context a2 = a(context);
            if (a2 == null || str == null || str.length() == 0) {
                g.e("The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
            } else {
                a(a2, str, j2);
            }
        }
    }

    private static void b(Context context, String str, long j2, long j3, long j4) {
        final String str2 = new String(str);
        if (e(context) != null) {
            final Context context2 = context;
            final long j5 = j2;
            final long j6 = j3;
            final long j7 = j4;
            c.post(new Runnable() {
                public void run() {
                    Long l;
                    try {
                        synchronized (e.b) {
                            l = (Long) e.b.remove(str2);
                        }
                        if (l != null) {
                            Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                            if (valueOf.longValue() <= 0) {
                                valueOf = Long.valueOf(1);
                            }
                            String g = e.f;
                            if (g != null && g.equals(str2)) {
                                g = "-";
                            }
                            com.tencent.android.tpush.stat.event.d dVar = new com.tencent.android.tpush.stat.event.d(context2, g, str2, e.b(context2, j5), valueOf, j5);
                            if (j6 > 0) {
                                dVar.n = j6;
                            }
                            if (j7 > 0) {
                                dVar.n = j7;
                            }
                            if (!str2.equals(e.e)) {
                                e.g.c("Invalid invocation since previous onResume on diff page.");
                            }
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(dVar);
                            e.a((List<b>) arrayList);
                            e.f = str2;
                            return;
                        }
                        e.g.f("Starttime for PageID:" + str2 + " not found, lost onResume()?");
                    } catch (Throwable th) {
                        e.g.b(th);
                    }
                }
            });
        }
    }

    public static void c(Context context, String str, long j2) {
        if (b.c()) {
            Context a2 = a(context);
            if (a2 == null || str == null || str.length() == 0) {
                g.e("The Context or pageName of StatService.trackEndPage() can not be null or empty!");
            } else {
                b(a2, str, j2, 0, 0);
            }
        }
    }

    public static void a(Context context, String str, long j2, long j3, long j4) {
        if (b.c()) {
            Context a2 = a(context);
            if (a2 == null || str == null || str.length() == 0) {
                g.e("The Context or pageName of StatService.trackEndPage() can not be null or empty!");
            } else {
                b(a2, str, j2, j3, j4);
            }
        }
    }

    static synchronized void b() {
        synchronized (e.class) {
            if (k != null) {
                Map all = k.getAll();
                if (all != null && all.size() > 0) {
                    ArrayList arrayList = new ArrayList(10);
                    for (Entry key : all.entrySet()) {
                        arrayList.add(key.getKey());
                        if (arrayList.size() == 10) {
                            e((List<String>) arrayList);
                            arrayList.clear();
                        }
                    }
                    e((List<String>) arrayList);
                    arrayList.clear();
                }
            }
        }
    }
}
