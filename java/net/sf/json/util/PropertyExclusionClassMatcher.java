package net.sf.json.util;

import java.util.Set;

public abstract class PropertyExclusionClassMatcher {
    public static final PropertyExclusionClassMatcher DEFAULT = new DefaultPropertyExclusionClassMatcher(null);

    /* renamed from: net.sf.json.util.PropertyExclusionClassMatcher$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultPropertyExclusionClassMatcher extends PropertyExclusionClassMatcher {
        private DefaultPropertyExclusionClassMatcher() {
        }

        DefaultPropertyExclusionClassMatcher(AnonymousClass1 r1) {
            this();
        }

        public Object getMatch(Class cls, Set set) {
            if (cls == null || set == null || !set.contains(cls)) {
                return null;
            }
            return cls;
        }
    }

    public abstract Object getMatch(Class cls, Set set);
}
