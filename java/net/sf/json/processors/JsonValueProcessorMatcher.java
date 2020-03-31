package net.sf.json.processors;

import java.util.Set;

public abstract class JsonValueProcessorMatcher {
    public static final JsonValueProcessorMatcher DEFAULT = new DefaultJsonValueProcessorMatcher(null);

    /* renamed from: net.sf.json.processors.JsonValueProcessorMatcher$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultJsonValueProcessorMatcher extends JsonValueProcessorMatcher {
        private DefaultJsonValueProcessorMatcher() {
        }

        DefaultJsonValueProcessorMatcher(AnonymousClass1 r1) {
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
