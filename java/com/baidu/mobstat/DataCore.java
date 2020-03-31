package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.StatService.WearListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataCore {
    private static JSONObject a = new JSONObject();
    private static DataCore b = new DataCore();
    private JSONArray c = new JSONArray();
    private JSONArray d = new JSONArray();
    private JSONArray e = new JSONArray();
    private boolean f = false;
    private volatile int g = 0;
    private WearListener h;
    private JSONObject i;
    private Object j = new Object();

    public static DataCore instance() {
        return b;
    }

    private DataCore() {
    }

    public void init(Context context) {
        instance().loadStatData(context);
        instance().loadLastSession(context);
        instance().installHeader(context);
    }

    public int getCacheFileSzie() {
        return this.g;
    }

    public JSONObject getLogData() {
        return this.i;
    }

    public void putSession(Session session) {
        putSession(session.constructJSONObject());
    }

    public void putSession(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (a(jSONObject.toString())) {
                am.c().b("[WARNING] data to put exceed limit, ignored");
                return;
            }
            synchronized (this.c) {
                try {
                    this.c.put(this.c.length(), jSONObject);
                } catch (JSONException e2) {
                }
            }
        }
    }

    public void putSession(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(new JSONObject().toString())) {
            try {
                putSession(new JSONObject(str));
            } catch (JSONException e2) {
            }
        }
    }

    private boolean a(String str) {
        return (str.getBytes().length + BDStatCore.instance().getSessionSize()) + this.g > 184320;
    }

    public void putEvent(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (a(jSONObject.toString())) {
                am.c().b("[WARNING] data to put exceed limit, ignored");
                return;
            }
            synchronized (this.d) {
                EventAnalysis.doEventMerge(this.d, jSONObject);
            }
        }
    }

    public void installHeader(Context context) {
        synchronized (a) {
            CooperService.instance().getHeadObject().installHeader(context, a);
        }
    }

    public void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.c) {
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.c.toString()));
            }
            synchronized (this.d) {
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.d.toString()));
            }
            synchronized (a) {
                jSONObject.put(Config.HEADER_PART, new JSONObject(a.toString()));
            }
        } catch (Exception e2) {
        }
        String jSONObject2 = jSONObject.toString();
        if (a()) {
            am.c().a("[WARNING] stat cache exceed 184320 Bytes, ignored");
            return;
        }
        int length = jSONObject2.getBytes().length;
        if (length >= 184320) {
            a(true);
            return;
        }
        this.g = length;
        at.a(context, bb.s(context) + Config.STAT_CACHE_FILE_NAME, jSONObject2, false);
        synchronized (this.e) {
            at.a(context, Config.LAST_AP_INFO_FILE_NAME, this.e.toString(), false);
        }
    }

    private void a(boolean z) {
        this.f = z;
    }

    private boolean a() {
        return this.f;
    }

    public void loadLastSession(Context context) {
        if (context != null) {
            String str = bb.s(context) + Config.LAST_SESSION_FILE_NAME;
            if (at.c(context, str)) {
                String a2 = at.a(context, str);
                if (!TextUtils.isEmpty(a2)) {
                    at.a(context, str, new JSONObject().toString(), false);
                    putSession(a2);
                    flush(context);
                }
            }
        }
    }

    public void loadWifiData(Context context) {
        JSONArray jSONArray;
        if (context != null && at.c(context, Config.LAST_AP_INFO_FILE_NAME)) {
            try {
                JSONArray jSONArray2 = new JSONArray(at.a(context, Config.LAST_AP_INFO_FILE_NAME));
                int length = jSONArray2.length();
                if (length >= 10) {
                    jSONArray = new JSONArray();
                    for (int i2 = length - 10; i2 < length; i2++) {
                        jSONArray.put(jSONArray2.get(i2));
                    }
                } else {
                    jSONArray = jSONArray2;
                }
                String g2 = bb.g(1, context);
                if (!TextUtils.isEmpty(g2)) {
                    jSONArray.put(g2);
                }
                synchronized (this.e) {
                    this.e = jSONArray;
                }
            } catch (JSONException e2) {
            }
        }
    }

    public void loadStatData(Context context) {
        if (context != null) {
            String str = bb.s(context) + Config.STAT_CACHE_FILE_NAME;
            if (at.c(context, str)) {
                String a2 = at.a(context, str);
                if (!TextUtils.isEmpty(a2)) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = new JSONObject(a2);
                    } catch (Exception e2) {
                    }
                    if (jSONObject != null) {
                        long currentTimeMillis = System.currentTimeMillis();
                        try {
                            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
                            if (jSONArray != null) {
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                    if (currentTimeMillis - jSONObject2.getLong("s") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                                        putSession(jSONObject2);
                                    }
                                }
                            }
                        } catch (Exception e3) {
                        }
                        try {
                            JSONArray jSONArray2 = jSONObject.getJSONArray(Config.EVENT_PART);
                            if (jSONArray2 != null) {
                                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i3);
                                    if (currentTimeMillis - jSONObject3.getLong("t") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                                        putEvent(context, jSONObject3);
                                    }
                                }
                            }
                        } catch (Exception e4) {
                        }
                        try {
                            JSONObject jSONObject4 = jSONObject.getJSONObject(Config.HEADER_PART);
                            if (jSONObject4 != null) {
                                synchronized (a) {
                                    a = jSONObject4;
                                    if (TextUtils.isEmpty(av.a().p(context))) {
                                        String string = a.getString(Config.DEVICE_ID_SEC);
                                        if (!TextUtils.isEmpty(string)) {
                                            av.a().k(context, string);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e5) {
                        }
                    }
                }
            }
        }
    }

    public String constructLogWithEmptyBody(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (TextUtils.isEmpty(headObject.e)) {
            headObject.installHeader(context, jSONObject2);
        } else {
            headObject.updateHeader(context, jSONObject2);
        }
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("t", currentTimeMillis);
            jSONObject2.put("ss", currentTimeMillis);
            jSONObject2.put(Config.WIFI_LOCATION, jSONArray);
            jSONObject2.put(Config.SEQUENCE_INDEX, 0);
            jSONObject2.put("sign", CooperService.instance().getUUID());
            jSONObject2.put(Config.APP_KEY, str);
            jSONObject.put(Config.HEADER_PART, jSONObject2);
            try {
                jSONObject.put(Config.PRINCIPAL_PART, jSONArray);
                try {
                    jSONObject.put(Config.EVENT_PART, jSONArray);
                    try {
                        jSONObject.put(Config.EXCEPTION_PART, jSONArray);
                        return jSONObject.toString();
                    } catch (JSONException e2) {
                        return null;
                    }
                } catch (JSONException e3) {
                    return null;
                }
            } catch (JSONException e4) {
                return null;
            }
        } catch (Exception e5) {
            return null;
        }
    }

    private void a(Context context, JSONObject jSONObject, boolean z) {
        boolean z2 = true;
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(Config.TRACE_APPLICATION_SESSION, z ? 1 : 0);
            } catch (Exception e2) {
            }
            try {
                jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            } catch (Exception e3) {
            }
            try {
                jSONObject2.put(Config.TRACE_CIRCLE, af.a());
            } catch (Exception e4) {
            }
            try {
                jSONObject.put(Config.TRACE_PART, jSONObject2);
            } catch (Exception e5) {
                z2 = false;
            }
            if (z2) {
                a(context, jSONObject, jSONObject2);
            }
        }
    }

    private void a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        long j2;
        long j3;
        int a2 = a(jSONObject);
        try {
            JSONObject jSONObject3 = jSONObject.getJSONObject(Config.HEADER_PART);
            if (jSONObject3 != null) {
                j3 = jSONObject3.getLong("ss");
            } else {
                j3 = 0;
            }
            j2 = j3;
        } catch (Exception e2) {
            j2 = 0;
        }
        if (j2 == 0) {
            j2 = System.currentTimeMillis();
        }
        a(context, jSONObject2, j2, a2);
    }

    private int a(JSONObject jSONObject) {
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        if (jSONObject == null) {
            return 0;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.HEADER_PART);
            i2 = (jSONObject2.getLong("ss") <= 0 || jSONObject2.getLong(Config.SEQUENCE_INDEX) != 0) ? 0 : 1;
        } catch (Exception e2) {
            i2 = 0;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
            if (jSONArray == null || jSONArray.length() == 0) {
                return i3;
            }
            while (true) {
                try {
                    int i6 = i5;
                    i4 = i3;
                    if (i6 >= jSONArray.length()) {
                        return i4;
                    }
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i6);
                    long j2 = jSONObject3.getLong("c");
                    if (jSONObject3.getLong("e") == 0 || j2 != 0) {
                        i3 = i4;
                    } else {
                        i3 = i4 + 1;
                    }
                    i5 = i6 + 1;
                } catch (Exception e3) {
                    return i4;
                }
            }
        } catch (Exception e4) {
            return i3;
        }
    }

    private void a(Context context, JSONObject jSONObject, long j2, int i2) {
        long longValue;
        int i3;
        int i4;
        long j3;
        JSONArray jSONArray;
        long longValue2 = ae.a().b(context).longValue();
        if (longValue2 <= 0 && i2 != 0) {
            ae.a().a(context, j2);
            longValue2 = j2;
        }
        a(jSONObject, Config.TRACE_VISIT_FIRST, (Object) Long.valueOf(longValue2));
        if (i2 != 0) {
            long longValue3 = ae.a().c(context).longValue();
            longValue = j2 - longValue3;
            if (longValue3 != 0 && longValue <= 0) {
                longValue = -1;
            } else if (longValue3 == 0) {
                longValue = 0;
            }
            ae.a().b(context, j2);
            ae.a().c(context, longValue);
        } else {
            longValue = ae.a().d(context).longValue();
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_LAST_INTERVAL, (Object) Long.valueOf(longValue));
        String str = "";
        String str2 = "";
        String e2 = ae.a().e(context);
        if (!TextUtils.isEmpty(e2) && e2.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            String[] split = e2.split(Config.TRACE_TODAY_VISIT_SPLIT);
            if (split != null && split.length == 2) {
                str = split[0];
                str2 = split[1];
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                i3 = Integer.valueOf(str2).intValue();
            } catch (Exception e3) {
                i3 = 0;
            }
        } else {
            i3 = 0;
        }
        String a2 = bc.a(j2);
        if (TextUtils.isEmpty(str) || a2.equals(str)) {
            i4 = i2 + i3;
        } else {
            i4 = i2;
        }
        if (i2 != 0) {
            ae.a().a(context, a2 + Config.TRACE_TODAY_VISIT_SPLIT + i4);
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_TODAY_COUNT, (Object) Integer.valueOf(i4));
        if (!TextUtils.isEmpty(str)) {
            try {
                j3 = (long) Integer.valueOf(str).intValue();
            } catch (Exception e4) {
                j3 = 0;
            }
        } else {
            j3 = 0;
        }
        if (j3 == 0 || TextUtils.isEmpty(str) || a2.equals(str) || i2 == 0) {
            String f2 = ae.a().f(context);
            if (!TextUtils.isEmpty(f2)) {
                try {
                    jSONArray = new JSONArray(f2);
                } catch (Exception e5) {
                    jSONArray = null;
                }
            } else {
                jSONArray = null;
            }
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            a(jSONObject, Config.TRACE_VISIT_RECENT, (Object) jSONArray);
            return;
        }
        JSONArray a3 = a(context, j3, (long) i3);
        ae.a().b(context, a3.toString());
        a(jSONObject, Config.TRACE_VISIT_RECENT, (Object) a3);
    }

    private JSONArray a(Context context, long j2, long j3) {
        boolean z;
        List list;
        List arrayList = new ArrayList();
        String f2 = ae.a().f(context);
        if (!TextUtils.isEmpty(f2)) {
            try {
                JSONArray jSONArray = new JSONArray(f2);
                if (!(jSONArray == null || jSONArray.length() == 0)) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        arrayList.add((JSONObject) jSONArray.get(i2));
                    }
                }
            } catch (Exception e2) {
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            try {
                if (((JSONObject) it.next()).getLong(Config.TRACE_VISIT_RECENT_DAY) == j2) {
                    z = false;
                    break;
                }
            } catch (Exception e3) {
            }
        }
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Config.TRACE_VISIT_RECENT_DAY, j2);
                jSONObject.put(Config.TRACE_VISIT_RECENT_COUNT, j3);
                arrayList.add(jSONObject);
            } catch (Exception e4) {
            }
        }
        int size = arrayList.size();
        if (size > 5) {
            list = arrayList.subList(size - 5, size);
        } else {
            list = arrayList;
        }
        return new JSONArray(list);
    }

    private void a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject != null) {
            if (!jSONObject.has(Config.TRACE_VISIT)) {
                try {
                    jSONObject.put(Config.TRACE_VISIT, new JSONObject());
                } catch (Exception e2) {
                }
            }
            try {
                ((JSONObject) jSONObject.get(Config.TRACE_VISIT)).put(str, obj);
            } catch (Exception e3) {
            }
        }
    }

    public void saveLogDataAndSendForRaven(Context context) {
        synchronized (this.j) {
        }
    }

    public void sendDataForDueros(Context context) {
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j2, boolean z3) {
        saveLogData(context, z, z2, j2, z3, null);
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j2, boolean z3, JSONObject jSONObject) {
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (headObject != null) {
            synchronized (a) {
                if (TextUtils.isEmpty(headObject.e)) {
                    headObject.installHeader(context, a);
                } else {
                    headObject.updateHeader(context, a);
                }
            }
            if (TextUtils.isEmpty(headObject.e)) {
                am.c().c("[WARNING] 无法找到有效APP Key, 请参考文档配置");
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        synchronized (a) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                String optString = a.optString("at");
                String optString2 = a.optString(Config.CUSTOM_USER_ID);
                if (!TextUtils.isEmpty(optString) && optString.equals("0")) {
                    if (!optString2.equals(CooperService.instance().getLastUserId(context))) {
                        a.put(Config.UID_CHANGE, optString2);
                    } else {
                        a.put(Config.UID_CHANGE, "");
                    }
                    CooperService.instance().setLastUserId(context, optString2);
                }
                a.put("t", currentTimeMillis);
                a.put(Config.SEQUENCE_INDEX, z ? 0 : 1);
                a.put("ss", j2);
                synchronized (this.e) {
                    a.put(Config.WIFI_LOCATION, this.e);
                }
                a.put("sign", CooperService.instance().getUUID());
                b(context, a, jSONObject);
                jSONObject2.put(Config.HEADER_PART, a);
                synchronized (this.c) {
                    try {
                        jSONObject2.put(Config.PRINCIPAL_PART, this.c);
                        synchronized (this.d) {
                            try {
                                jSONObject2.put(Config.EVENT_PART, this.d);
                                try {
                                    jSONObject2.put(Config.EXCEPTION_PART, new JSONArray());
                                    a(context, jSONObject2, z2);
                                    b(jSONObject2);
                                    a(context, jSONObject2);
                                    a(context, jSONObject2.toString(), z, z3);
                                    this.i = jSONObject2;
                                    clearCache(context);
                                } catch (JSONException e2) {
                                }
                            } catch (JSONException e3) {
                            }
                        }
                    } catch (JSONException e4) {
                    }
                }
            } catch (Exception e5) {
            }
        }
    }

    private void b(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private void b(JSONObject jSONObject) {
    }

    private void a(Context context, JSONObject jSONObject) {
    }

    private void a(Context context, String str, boolean z, boolean z2) {
        if (this.h == null || !this.h.onSendLogData(str)) {
            LogSender.instance().saveLogData(context, str, false);
            am.c().a("Save log: " + str);
            return;
        }
        am.c().a("Log has been passed to app level, log: " + str);
    }

    public void clearCache(Context context) {
        a(false);
        synchronized (a) {
            a = new JSONObject();
        }
        installHeader(context);
        a(context);
    }

    private void a(Context context) {
        synchronized (this.d) {
            this.d = new JSONArray();
        }
        synchronized (this.c) {
            this.c = new JSONArray();
        }
        synchronized (this.e) {
            this.e = new JSONArray();
        }
        flush(context);
    }
}
