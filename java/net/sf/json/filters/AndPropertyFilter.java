package net.sf.json.filters;

import net.sf.json.util.PropertyFilter;

public class AndPropertyFilter implements PropertyFilter {
    private PropertyFilter filter1;
    private PropertyFilter filter2;

    public AndPropertyFilter(PropertyFilter propertyFilter, PropertyFilter propertyFilter2) {
        this.filter1 = propertyFilter;
        this.filter2 = propertyFilter2;
    }

    public boolean apply(Object obj, String str, Object obj2) {
        if (this.filter1 == null || !this.filter1.apply(obj, str, obj2) || this.filter2 == null || !this.filter2.apply(obj, str, obj2)) {
            return false;
        }
        return true;
    }
}
