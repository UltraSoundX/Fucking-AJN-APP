package com.b.a;

import com.b.a.b.g;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: JsonObject */
public final class m extends j {
    private final g<String, j> a = new g<>();

    public void a(String str, j jVar) {
        if (jVar == null) {
            jVar = l.a;
        }
        this.a.put(str, jVar);
    }

    public Set<Entry<String, j>> o() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof m) && ((m) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
