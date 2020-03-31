package net.sf.json.processors;

import java.util.Set;

public abstract class JsonBeanProcessorMatcher {
    public static final JsonBeanProcessorMatcher DEFAULT = new DefaultJsonBeanProcessorMatcher(null);

    /* renamed from: net.sf.json.processors.JsonBeanProcessorMatcher$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {
        private DefaultJsonBeanProcessorMatcher() {
        }

        DefaultJsonBeanProcessorMatcher(AnonymousClass1 r1) {
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
