package net.sf.json.filters;

import net.sf.json.util.PropertyFilter;

public class NotPropertyFilter implements PropertyFilter {
    private PropertyFilter filter;

    public NotPropertyFilter(PropertyFilter propertyFilter) {
        this.filter = propertyFilter;
    }

    public boolean apply(Object obj, String str, Object obj2) {
        if (this.filter == null || this.filter.apply(obj, str, obj2)) {
            return false;
        }
        return true;
    }
}
