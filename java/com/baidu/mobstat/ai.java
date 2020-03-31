package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.baidu.mobstat.Config.EventViewType;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ai {
    private static ai b = new ai();
    public a a;
    private HandlerThread c = new HandlerThread("fullTraceHandleThread");
    private Handler d;
    private volatile int e;
    private int f;
    private JSONObject g = new JSONObject();
    private JSONArray h = new JSONArray();
    private JSONArray i = new JSONArray();
    private JSONArray j = new JSONArray();
    private JSONArray k = new JSONArray();
    private boolean l = false;

    public interface a {
        void a(JSONObject jSONObject);
    }

    public static ai a() {
        return b;
    }

    private ai() {
        this.c.start();
        this.c.setPriority(10);
        this.d = new Handler(this.c.getLooper());
    }

    public void a(Context context, String str, String str2, int i2, long j2, String str3, JSONArray jSONArray, String str4, JSONArray jSONArray2, String str5, Map<String, String> map, boolean z, JSONObject jSONObject, String str6) {
        final Context context2 = context;
        final String str7 = str;
        final String str8 = str2;
        final int i3 = i2;
        final long j3 = j2;
        final String str9 = str3;
        final JSONArray jSONArray3 = jSONArray;
        final String str10 = str4;
        final JSONArray jSONArray4 = jSONArray2;
        final String str11 = str5;
        final Map<String, String> map2 = map;
        final boolean z2 = z;
        final JSONObject jSONObject2 = jSONObject;
        final String str12 = str6;
        this.d.post(new Runnable() {
            public void run() {
                long sessionStartTime = BDStatCore.instance().getSessionStartTime();
                if (sessionStartTime > 0) {
                    ai.this.a(context2, sessionStartTime, str7, str8, i3, j3, str9, jSONArray3, str10, jSONArray4, str11, map2, z2, jSONObject2, str12);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Context context, long j2, String str, String str2, int i2, long j3, String str3, JSONArray jSONArray, String str4, JSONArray jSONArray2, String str5, Map<String, String> map, boolean z, JSONObject jSONObject, String str6) {
        String c2 = ap.c(jSONArray);
        String d2 = ap.d(jSONArray2);
        Context context2 = context;
        long j4 = j2;
        String str7 = str;
        String str8 = str2;
        int i3 = i2;
        long j5 = j3;
        Context context3 = context;
        a(context3, EventAnalysis.getEvent(context2, j4, str7, str8, i3, j5, 0, "", null, null, ap.a(str3), ap.a(str4), str5, EventViewType.EDIT.getValue(), 3, null, map, c2, d2, z, jSONObject, str6));
        b(context);
    }

    private void a(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (ao.c().b()) {
                ao.c().a("putEvent: " + jSONObject.toString());
            }
            String jSONObject2 = jSONObject.toString();
            if (a(context, jSONObject2)) {
                if (ao.c().b()) {
                    ao.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.e + "; addedSize:" + jSONObject2.length());
                }
                c(context);
            }
            EventAnalysis.doEventMerge(this.h, jSONObject);
        }
    }

    private boolean a(Context context, String str) {
        int i2;
        if (str != null) {
            i2 = str.getBytes().length;
        } else {
            i2 = 0;
        }
        if (i2 + this.e > 184320) {
            return true;
        }
        return false;
    }

    public void a(Context context, boolean z) {
        if (z) {
            c();
        } else {
            b();
        }
        try {
            b(context, this.g);
        } catch (Exception e2) {
        }
        if (this.h.length() != 0 || this.i.length() != 0 || this.j.length() != 0 || this.k.length() != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Config.HEADER_PART, this.g);
            } catch (Exception e3) {
            }
            try {
                jSONObject.put(Config.PRINCIPAL_PART, this.i);
            } catch (Exception e4) {
            }
            try {
                jSONObject.put(Config.EVENT_PART, this.h);
            } catch (Exception e5) {
            }
            try {
                jSONObject.put(Config.FEED_LIST_PART, this.j);
            } catch (Exception e6) {
            }
            try {
                jSONObject.put("sv", this.k);
            } catch (Exception e7) {
            }
            try {
                jSONObject.put(Config.EVENT_PAGE_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.b));
            } catch (Exception e8) {
            }
            try {
                jSONObject.put(Config.EVENT_PATH_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.a));
            } catch (Exception e9) {
            }
            try {
                jSONObject.put(Config.FEED_LIST_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.c));
            } catch (Exception e10) {
            }
            c(context, jSONObject);
            a(jSONObject);
            String jSONObject2 = jSONObject.toString();
            if (ao.c().b()) {
                ao.c().a("saveCurrentCacheToSend content: " + jSONObject2);
            }
            b(context, jSONObject2);
            b(context, !z);
            this.l = true;
        }
    }

    public void b(Context context, boolean z) {
        this.g = new JSONObject();
        a(context);
        this.i = new JSONArray();
        this.h = new JSONArray();
        this.j = new JSONArray();
        this.k = new JSONArray();
        if (!z) {
            ag.a().b();
        }
        b(context);
    }

    public void a(Context context) {
        CooperService.instance().getHeadObject().installHeader(context, this.g);
    }

    public void b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.HEADER_PART, new JSONObject(this.g.toString()));
            jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.i.toString()));
            jSONObject.put(Config.EVENT_PART, new JSONArray(this.h.toString()));
            jSONObject.put(Config.FEED_LIST_PART, new JSONArray(this.j.toString()));
            jSONObject.put("sv", new JSONArray(this.k.toString()));
            jSONObject.put(Config.EVENT_PAGE_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.b));
            jSONObject.put(Config.EVENT_PATH_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.a));
            jSONObject.put(Config.FEED_LIST_MAPPING, ag.a().a(com.baidu.mobstat.ag.a.c));
        } catch (Exception e2) {
        }
        String jSONObject2 = jSONObject.toString();
        int length = jSONObject2.getBytes().length;
        if (length < 184320) {
            this.e = length;
            at.a(context, bb.s(context) + Config.STAT_FULL_CACHE_FILE_NAME, jSONObject2, false);
        }
    }

    private void b(Context context, JSONObject jSONObject) {
        CooperService.instance().getHeadObject().installHeader(context, jSONObject);
        try {
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.SEQUENCE_INDEX, this.f);
            jSONObject.put("ss", BDStatCore.instance().getSessionStartTime());
            jSONObject.put("at", "1");
            jSONObject.put("sign", CooperService.instance().getUUID());
        } catch (Exception e2) {
        }
    }

    private void c(Context context) {
        this.i = a(this.i, BDStatCore.instance().getPageSessionHead());
        a(context, false);
        b();
    }

    private void b() {
        this.f++;
    }

    private void c() {
        this.f = 0;
    }

    private void c(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            } catch (Exception e2) {
            }
            try {
                jSONObject.put(Config.TRACE_PART, jSONObject2);
            } catch (Exception e3) {
            }
        }
    }

    private void a(JSONObject jSONObject) {
    }

    private void b(Context context, String str) {
        LogSender.instance().saveLogData(context, str, true);
        if (this.a != null) {
            try {
                this.a.a(new JSONObject(str));
            } catch (Exception e2) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONArray a(org.json.JSONArray r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            r1 = 0
            if (r8 == 0) goto L_0x0005
            if (r7 != 0) goto L_0x0006
        L_0x0005:
            return r7
        L_0x0006:
            java.lang.String r0 = "s"
            long r2 = r8.optLong(r0)
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0005
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            int r0 = r7.length()
            if (r0 != 0) goto L_0x003a
            java.lang.String r3 = r8.toString()     // Catch:{ Exception -> 0x0037 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0037 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0037 }
            java.lang.String r1 = "p"
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ Exception -> 0x0069 }
            r3.<init>()     // Catch:{ Exception -> 0x0069 }
            r0.put(r1, r3)     // Catch:{ Exception -> 0x0069 }
        L_0x0030:
            if (r0 == 0) goto L_0x0035
            r2.put(r0)
        L_0x0035:
            r7 = r2
            goto L_0x0005
        L_0x0037:
            r0 = move-exception
        L_0x0038:
            r0 = r1
            goto L_0x0030
        L_0x003a:
            r0 = 0
            org.json.JSONObject r0 = r7.getJSONObject(r0)     // Catch:{ Exception -> 0x005d }
        L_0x003f:
            if (r0 == 0) goto L_0x006c
            java.lang.String r3 = "p"
            org.json.JSONArray r0 = r0.getJSONArray(r3)     // Catch:{ Exception -> 0x0060 }
        L_0x0047:
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x0063 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0063 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0063 }
            if (r0 == 0) goto L_0x0057
            java.lang.String r1 = "p"
            r3.put(r1, r0)     // Catch:{ Exception -> 0x0066 }
        L_0x0057:
            if (r3 == 0) goto L_0x0035
            r2.put(r3)
            goto L_0x0035
        L_0x005d:
            r0 = move-exception
            r0 = r1
            goto L_0x003f
        L_0x0060:
            r0 = move-exception
            r0 = r1
            goto L_0x0047
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            r3 = r1
            goto L_0x0057
        L_0x0066:
            r0 = move-exception
            r1 = r3
            goto L_0x0064
        L_0x0069:
            r1 = move-exception
            r1 = r0
            goto L_0x0038
        L_0x006c:
            r0 = r1
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ai.a(org.json.JSONArray, org.json.JSONObject):org.json.JSONArray");
    }
}
