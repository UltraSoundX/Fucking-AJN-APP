package com.bumptech.glide.d.b.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.i.h;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

@TargetApi(19)
/* compiled from: SizeConfigStrategy */
public class i implements g {
    private static final Config[] a = {Config.ARGB_8888, null};
    private static final Config[] b = {Config.RGB_565};
    private static final Config[] c = {Config.ARGB_4444};
    private static final Config[] d = {Config.ALPHA_8};
    private final b e = new b();
    private final e<a, Bitmap> f = new e<>();
    private final Map<Config, NavigableMap<Integer, Integer>> g = new HashMap();

    /* renamed from: com.bumptech.glide.d.b.a.i$1 reason: invalid class name */
    /* compiled from: SizeConfigStrategy */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Config.values().length];

        static {
            try {
                a[Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: SizeConfigStrategy */
    static final class a implements h {
        private final b a;
        /* access modifiers changed from: private */
        public int b;
        private Config c;

        public a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, Config config) {
            this.b = i;
            this.c = config;
        }

        public void a() {
            this.a.a(this);
        }

        public String toString() {
            return i.b(this.b, this.c);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b != aVar.b) {
                return false;
            }
            if (this.c == null) {
                if (aVar.c != null) {
                    return false;
                }
            } else if (!this.c.equals(aVar.c)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.c != null ? this.c.hashCode() : 0) + (this.b * 31);
        }
    }

    /* compiled from: SizeConfigStrategy */
    static class b extends b<a> {
        b() {
        }

        public a a(int i, Config config) {
            a aVar = (a) c();
            aVar.a(i, config);
            return aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b() {
            return new a(this);
        }
    }

    public void a(Bitmap bitmap) {
        a a2 = this.e.a(h.a(bitmap), bitmap.getConfig());
        this.f.a(a2, bitmap);
        NavigableMap a3 = a(bitmap.getConfig());
        Integer num = (Integer) a3.get(Integer.valueOf(a2.b));
        a3.put(Integer.valueOf(a2.b), Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    public Bitmap a(int i, int i2, Config config) {
        int a2 = h.a(i, i2, config);
        Bitmap bitmap = (Bitmap) this.f.a(a(this.e.a(a2, config), a2, config));
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
            bitmap.reconfigure(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888);
        }
        return bitmap;
    }

    private a a(a aVar, int i, Config config) {
        Config[] b2 = b(config);
        int length = b2.length;
        int i2 = 0;
        while (i2 < length) {
            Config config2 = b2[i2];
            Integer num = (Integer) a(config2).ceilingKey(Integer.valueOf(i));
            if (num == null || num.intValue() > i * 8) {
                i2++;
            } else {
                if (num.intValue() == i) {
                    if (config2 == null) {
                        if (config == null) {
                            return aVar;
                        }
                    } else if (config2.equals(config)) {
                        return aVar;
                    }
                }
                this.e.a(aVar);
                return this.e.a(num.intValue(), config2);
            }
        }
        return aVar;
    }

    public Bitmap a() {
        Bitmap bitmap = (Bitmap) this.f.a();
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
        }
        return bitmap;
    }

    private void a(Integer num, Config config) {
        NavigableMap a2 = a(config);
        Integer num2 = (Integer) a2.get(num);
        if (num2.intValue() == 1) {
            a2.remove(num);
        } else {
            a2.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Config config) {
        NavigableMap<Integer, Integer> navigableMap = (NavigableMap) this.g.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.g.put(config, treeMap);
        return treeMap;
    }

    public String b(Bitmap bitmap) {
        return b(h.a(bitmap), bitmap.getConfig());
    }

    public String b(int i, int i2, Config config) {
        return b(h.a(i, i2, config), config);
    }

    public int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("SizeConfigStrategy{groupedMap=").append(this.f).append(", sortedSizes=(");
        for (Entry entry : this.g.entrySet()) {
            append.append(entry.getKey()).append('[').append(entry.getValue()).append("], ");
        }
        if (!this.g.isEmpty()) {
            append.replace(append.length() - 2, append.length(), "");
        }
        return append.append(")}").toString();
    }

    /* access modifiers changed from: private */
    public static String b(int i, Config config) {
        return "[" + i + "](" + config + ")";
    }

    private static Config[] b(Config config) {
        switch (AnonymousClass1.a[config.ordinal()]) {
            case 1:
                return a;
            case 2:
                return b;
            case 3:
                return c;
            case 4:
                return d;
            default:
                return new Config[]{config};
        }
    }
}
