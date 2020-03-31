package com.e23.ajn.c;

import android.text.TextUtils;
import com.e23.ajn.d.p;
import com.tencent.android.tpush.common.Constants;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: RequestParameters */
public class b {
    public static Map<String, String> a(Map<String, String> map) {
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        map.put("platform", "android");
        String b = p.b(Constants.FLAG_TOKEN, "");
        if (!TextUtils.isEmpty(b)) {
            map.put(Constants.FLAG_TOKEN, b);
        }
        map.put("timestamp", Long.toString(System.currentTimeMillis()));
        return map;
    }
}
