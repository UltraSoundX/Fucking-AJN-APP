package android.arch.core.b;

import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: FastSafeIterableMap */
public class a<K, V> extends b<K, V> {
    private HashMap<K, c<K, V>> a = new HashMap<>();

    /* access modifiers changed from: protected */
    public c<K, V> a(K k) {
        return (c) this.a.get(k);
    }

    public V a(K k, V v) {
        c a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        this.a.put(k, b(k, v));
        return null;
    }

    public V b(K k) {
        V b = super.b(k);
        this.a.remove(k);
        return b;
    }

    public boolean c(K k) {
        return this.a.containsKey(k);
    }

    public Entry<K, V> d(K k) {
        if (c(k)) {
            return ((c) this.a.get(k)).d;
        }
        return null;
    }
}
