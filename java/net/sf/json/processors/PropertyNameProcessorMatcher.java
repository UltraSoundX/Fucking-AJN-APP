package net.sf.json.processors;

import java.util.Set;

public abstract class PropertyNameProcessorMatcher {
    public static final PropertyNameProcessorMatcher DEFAULT = new DefaultPropertyNameProcessorMatcher(null);

    /* renamed from: net.sf.json.processors.PropertyNameProcessorMatcher$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultPropertyNameProcessorMatcher extends PropertyNameProcessorMatcher {
        private DefaultPropertyNameProcessorMatcher() {
        }

        DefaultPropertyNameProcessorMatcher(AnonymousClass1 r1) {
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
