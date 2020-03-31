package com.bumptech.glide.d.c;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: LazyHeaders */
public final class j implements e {
    private final Map<String, List<i>> c;
    private volatile Map<String, String> d;

    /* compiled from: LazyHeaders */
    public static final class a {
        private static final String a = System.getProperty("http.agent");
        private static final Map<String, List<i>> b;
        private boolean c = true;
        private Map<String, List<i>> d = b;
        private boolean e = true;
        private boolean f = true;

        static {
            HashMap hashMap = new HashMap(2);
            if (!TextUtils.isEmpty(a)) {
                hashMap.put("User-Agent", Collections.singletonList(new b(a)));
            }
            hashMap.put("Accept-Encoding", Collections.singletonList(new b("identity")));
            b = Collections.unmodifiableMap(hashMap);
        }

        public j a() {
            this.c = true;
            return new j(this.d);
        }
    }

    /* compiled from: LazyHeaders */
    static final class b implements i {
        private final String a;

        b(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.a + '\'' + '}';
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            return this.a.equals(((b) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    j(Map<String, List<i>> map) {
        this.c = Collections.unmodifiableMap(map);
    }

    public Map<String, String> a() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = Collections.unmodifiableMap(b());
                }
            }
        }
        return this.d;
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.c.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List list = (List) entry.getValue();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                sb.append(((i) list.get(i2)).a());
                if (i2 != list.size() - 1) {
                    sb.append(',');
                }
                i = i2 + 1;
            }
            hashMap.put(entry.getKey(), sb.toString());
        }
        return hashMap;
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.c + '}';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        return this.c.equals(((j) obj).c);
    }

    public int hashCode() {
        return this.c.hashCode();
    }
}
