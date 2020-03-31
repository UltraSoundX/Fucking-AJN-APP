package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class ag {
    private static final ag a = new ag();
    private HashMap<String, String> b = new HashMap<>();
    private HashMap<Character, Integer> c = new HashMap<>();
    private HashMap<String, String> d = new HashMap<>();
    private HashMap<Character, Integer> e = new HashMap<>();
    private HashMap<String, String> f = new HashMap<>();
    private HashMap<Character, Integer> g = new HashMap<>();

    public static class a {
        public static int a = 0;
        public static int b = 1;
        public static int c = 2;
    }

    public static ag a() {
        return a;
    }

    public String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i == a.a) {
            String str2 = (String) this.b.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            a(str, this.c, this.b);
            return (String) this.b.get(str);
        } else if (i == a.c) {
            String str3 = (String) this.f.get(str);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            a(str, this.g, this.f);
            return (String) this.f.get(str);
        } else {
            String str4 = (String) this.d.get(str);
            if (!TextUtils.isEmpty(str4)) {
                return str4;
            }
            a(str, this.e, this.d);
            return (String) this.d.get(str);
        }
    }

    private void a(String str, HashMap<Character, Integer> hashMap, HashMap<String, String> hashMap2) {
        int i;
        char lowerCase = Character.toLowerCase(str.charAt(0));
        Integer num = (Integer) hashMap.get(Character.valueOf(lowerCase));
        if (num != null) {
            i = num.intValue() + 1;
        } else {
            i = 0;
        }
        String str2 = Character.toString(lowerCase) + i;
        hashMap.put(Character.valueOf(lowerCase), Integer.valueOf(i));
        hashMap2.put(str, str2);
    }

    public JSONObject a(int i) {
        HashMap<String, String> hashMap;
        if (i == a.a) {
            hashMap = this.b;
        } else if (i == a.c) {
            hashMap = this.f;
        } else {
            hashMap = this.d;
        }
        JSONObject jSONObject = new JSONObject();
        if (hashMap == null) {
            return jSONObject;
        }
        ArrayList<Entry> arrayList = new ArrayList<>(hashMap.entrySet());
        try {
            Collections.sort(arrayList, new Comparator<Entry<String, String>>() {
                /* renamed from: a */
                public int compare(Entry<String, String> entry, Entry<String, String> entry2) {
                    return ((String) entry.getValue()).compareTo((String) entry2.getValue());
                }
            });
        } catch (Exception e2) {
        }
        for (Entry entry : arrayList) {
            try {
                jSONObject.put((String) entry.getValue(), (String) entry.getKey());
            } catch (Exception e3) {
            }
        }
        return jSONObject;
    }

    public void b(int i) {
        if (i == a.a) {
            this.c.clear();
            this.b.clear();
        } else if (i == a.c) {
            this.g.clear();
            this.f.clear();
        } else {
            this.e.clear();
            this.d.clear();
        }
    }

    public void b() {
        b(a.a);
        b(a.c);
        b(a.b);
    }
}
