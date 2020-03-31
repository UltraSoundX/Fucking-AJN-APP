package com.c.a.c.b.b;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: MinimalFieldHeader */
class f implements Iterable<e> {
    private final List<e> a = new LinkedList();
    private final Map<String, List<e>> b = new HashMap();

    public void a(e eVar) {
        if (eVar != null) {
            String lowerCase = eVar.a().toLowerCase(Locale.US);
            List list = (List) this.b.get(lowerCase);
            if (list == null) {
                list = new LinkedList();
                this.b.put(lowerCase, list);
            }
            list.add(eVar);
            this.a.add(eVar);
        }
    }

    public e a(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.b.get(str.toLowerCase(Locale.US));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (e) list.get(0);
    }

    public Iterator<e> iterator() {
        return Collections.unmodifiableList(this.a).iterator();
    }

    public String toString() {
        return this.a.toString();
    }
}
