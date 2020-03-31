package net.sf.json.processors;

import java.util.Set;

public abstract class DefaultValueProcessorMatcher {
    public static final DefaultValueProcessorMatcher DEFAULT = new DefaultDefaultValueProcessorMatcher(null);

    /* renamed from: net.sf.json.processors.DefaultValueProcessorMatcher$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultDefaultValueProcessorMatcher extends DefaultValueProcessorMatcher {
        private DefaultDefaultValueProcessorMatcher() {
        }

        DefaultDefaultValueProcessorMatcher(AnonymousClass1 r1) {
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
