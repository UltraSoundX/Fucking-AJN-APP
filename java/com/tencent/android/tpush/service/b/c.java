package com.tencent.android.tpush.service.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c {
    private JSONObject a = new JSONObject();
    private List<String> b = new ArrayList();

    public static c a(String str) {
        String[] split;
        c cVar = new c();
        try {
            cVar.a = new JSONObject(str);
            String optString = cVar.a.optString("ips", null);
            if (optString != null && optString.trim().length() > 7) {
                if (optString.contains(";")) {
                    for (String str2 : optString.split(";")) {
                        if (b.b(str2)) {
                            cVar.b.add(str2);
                        }
                    }
                } else if (b.b(optString)) {
                    cVar.b.add(optString);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cVar;
    }

    public String a() {
        if (this.b == null) {
            return null;
        }
        int size = this.b.size();
        if (size == 1) {
            return (String) this.b.get(0);
        }
        if (size <= 1) {
            return null;
        }
        Collections.shuffle(this.b);
        return (String) this.b.get(0);
    }

    public String toString() {
        return this.a.toString();
    }

    public long b() {
        return this.a.optLong("exp", System.currentTimeMillis());
    }
}
