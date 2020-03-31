package net.sf.json.filters;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.util.PropertyFilter;

public class CompositePropertyFilter implements PropertyFilter {
    private List filters;

    public CompositePropertyFilter() {
        this(null);
    }

    public CompositePropertyFilter(List list) {
        this.filters = new ArrayList();
        if (list != null) {
            for (Object next : list) {
                if (next instanceof PropertyFilter) {
                    this.filters.add(next);
                }
            }
        }
    }

    public void addPropertyFilter(PropertyFilter propertyFilter) {
        if (propertyFilter != null) {
            this.filters.add(propertyFilter);
        }
    }

    public boolean apply(Object obj, String str, Object obj2) {
        for (PropertyFilter apply : this.filters) {
            if (apply.apply(obj, str, obj2)) {
                return true;
            }
        }
        return false;
    }

    public void removePropertyFilter(PropertyFilter propertyFilter) {
        if (propertyFilter != null) {
            this.filters.remove(propertyFilter);
        }
    }
}
