package com.baidu.b.a;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class h {
    private Context a;
    /* access modifiers changed from: private */
    public List<HashMap<String, String>> b = null;
    private a<String> c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected h(Context context) {
        this.a = context;
    }

    private List<HashMap<String, String>> a(HashMap<String, String> hashMap, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= strArr.length) {
                    break;
                }
                HashMap hashMap2 = new HashMap();
                for (String str : hashMap.keySet()) {
                    String str2 = str.toString();
                    hashMap2.put(str2, hashMap.get(str2));
                }
                hashMap2.put("mcode", strArr[i2]);
                arrayList.add(hashMap2);
                i = i2 + 1;
            }
        } else {
            HashMap hashMap3 = new HashMap();
            for (String str3 : hashMap.keySet()) {
                String str4 = str3.toString();
                hashMap3.put(str4, hashMap.get(str4));
            }
            arrayList.add(hashMap3);
        }
        return arrayList;
    }

    private void a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.a(jSONObject != null ? jSONObject.toString() : new JSONObject().toString());
        }
    }

    /* access modifiers changed from: private */
    public void a(List<HashMap<String, String>> list) {
        int i = 0;
        d.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            d.c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                d.a("syncConnect resuest " + i2 + "  start!!!");
                HashMap hashMap = (HashMap) list.get(i2);
                j jVar = new j(this.a);
                if (jVar.a()) {
                    String a2 = jVar.a(hashMap);
                    if (a2 == null) {
                        a2 = "";
                    }
                    d.a("syncConnect resuest " + i2 + "  result:" + a2);
                    arrayList.add(a2);
                    try {
                        JSONObject jSONObject = new JSONObject(a2);
                        if (jSONObject.has(NotificationCompat.CATEGORY_STATUS) && jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 0) {
                            d.a("auth end and break");
                            a(a2);
                            return;
                        }
                    } catch (JSONException e) {
                        d.a("continue-------------------------------");
                    }
                } else {
                    d.a("Current network is not available.");
                    arrayList.add(a.a("Current network is not available."));
                }
                d.a("syncConnect end");
                i = i2 + 1;
            } else {
                d.a("--iiiiii:" + i2 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
                if (list.size() > 0 && i2 == list.size() && arrayList.size() > 0 && i2 == arrayList.size() && i2 - 1 > 0) {
                    try {
                        JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i2 - 1));
                        if (jSONObject2.has(NotificationCompat.CATEGORY_STATUS) && jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS) != 0) {
                            d.a("i-1 result is not 0,return first result");
                            a((String) arrayList.get(0));
                            return;
                        }
                        return;
                    } catch (JSONException e2) {
                        a(a.a("JSONException:" + e2.getMessage()));
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(HashMap<String, String> hashMap, String[] strArr, a<String> aVar) {
        this.b = a(hashMap, strArr);
        this.c = aVar;
        new Thread(new i(this)).start();
    }
}
