package com.e23.ajn.ccb.d;

import android.util.Log;
import com.b.a.e;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* compiled from: EntityUtils */
public class d {
    public static Map a(Object obj) {
        Field[] fields;
        HashMap hashMap = new HashMap();
        for (Field field : obj.getClass().getFields()) {
            String name = field.getName();
            String str = "";
            if (obj != null) {
                try {
                    Object obj2 = field.get(obj);
                    if (obj2 != null) {
                        str = obj2 instanceof String ? String.valueOf(obj2) : new e().a(obj2);
                    }
                } catch (Exception e) {
                    Log.e("", e.toString());
                }
            }
            hashMap.put(name, str);
        }
        return hashMap;
    }
}
