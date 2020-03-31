package net.sf.json.filters;

import net.sf.json.util.PropertyFilter;

public class FalsePropertyFilter implements PropertyFilter {
    public boolean apply(Object obj, String str, Object obj2) {
        return false;
    }
}
