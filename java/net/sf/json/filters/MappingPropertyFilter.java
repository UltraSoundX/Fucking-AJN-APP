package net.sf.json.filters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.sf.json.util.PropertyFilter;

public abstract class MappingPropertyFilter implements PropertyFilter {
    private Map filters;

    /* access modifiers changed from: protected */
    public abstract boolean keyMatches(Object obj, Object obj2, String str, Object obj3);

    public MappingPropertyFilter() {
        this(null);
    }

    public MappingPropertyFilter(Map map) {
        this.filters = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof PropertyFilter) {
                    this.filters.put(key, value);
                }
            }
        }
    }

    public void addPropertyFilter(Object obj, PropertyFilter propertyFilter) {
        if (propertyFilter != null) {
            this.filters.put(obj, propertyFilter);
        }
    }

    public boolean apply(Object obj, String str, Object obj2) {
        for (Entry entry : this.filters.entrySet()) {
            if (keyMatches(entry.getKey(), obj, str, obj2)) {
                return ((PropertyFilter) entry.getValue()).apply(obj, str, obj2);
            }
        }
        return false;
    }

    public void removePropertyFilter(Object obj) {
        if (obj != null) {
            this.filters.remove(obj);
        }
    }
}
