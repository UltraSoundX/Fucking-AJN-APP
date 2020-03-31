package net.sf.json.util;

public abstract class WebHijackPreventionStrategy {
    public static final WebHijackPreventionStrategy COMMENTS = new CommentWebHijackPreventionStrategy(null);
    public static final WebHijackPreventionStrategy INFINITE_LOOP = new InfiniteLoopWebHijackPreventionStrategy(null);

    /* renamed from: net.sf.json.util.WebHijackPreventionStrategy$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class CommentWebHijackPreventionStrategy extends WebHijackPreventionStrategy {
        private CommentWebHijackPreventionStrategy() {
        }

        CommentWebHijackPreventionStrategy(AnonymousClass1 r1) {
            this();
        }

        public String protect(String str) {
            return new StringBuffer().append("/*").append(str).append("*/").toString();
        }
    }

    private static final class InfiniteLoopWebHijackPreventionStrategy extends WebHijackPreventionStrategy {
        private InfiniteLoopWebHijackPreventionStrategy() {
        }

        InfiniteLoopWebHijackPreventionStrategy(AnonymousClass1 r1) {
            this();
        }

        public String protect(String str) {
            return new StringBuffer().append("while(1);").append(str).toString();
        }
    }

    public abstract String protect(String str);
}
