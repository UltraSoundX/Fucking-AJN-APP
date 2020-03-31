package net.sf.json.util;

import org.apache.commons.lang.StringUtils;

public abstract class JavaIdentifierTransformer {
    public static final JavaIdentifierTransformer CAMEL_CASE = new CamelCaseJavaIdentifierTransformer(null);
    public static final JavaIdentifierTransformer NOOP = new NoopJavaIdentifierTransformer(null);
    public static final JavaIdentifierTransformer STRICT = new StrictJavaIdentifierTransformer(null);
    public static final JavaIdentifierTransformer UNDERSCORE = new UnderscoreJavaIdentifierTransformer(null);
    public static final JavaIdentifierTransformer WHITESPACE = new WhiteSpaceJavaIdentifierTransformer(null);

    /* renamed from: net.sf.json.util.JavaIdentifierTransformer$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class CamelCaseJavaIdentifierTransformer extends JavaIdentifierTransformer {
        private CamelCaseJavaIdentifierTransformer() {
        }

        CamelCaseJavaIdentifierTransformer(AnonymousClass1 r1) {
            this();
        }

        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }
            char[] charArray = shaveOffNonJavaIdentifierStartChars(str).toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            boolean z = false;
            for (int i = 0; i < charArray.length; i++) {
                if (!Character.isJavaIdentifierPart(charArray[i]) || Character.isWhitespace(charArray[i])) {
                    z = true;
                } else if (z) {
                    stringBuffer.append(Character.toUpperCase(charArray[i]));
                    z = false;
                } else {
                    stringBuffer.append(charArray[i]);
                }
            }
            return stringBuffer.toString();
        }
    }

    private static final class NoopJavaIdentifierTransformer extends JavaIdentifierTransformer {
        private NoopJavaIdentifierTransformer() {
        }

        NoopJavaIdentifierTransformer(AnonymousClass1 r1) {
            this();
        }

        public String transformToJavaIdentifier(String str) {
            return str;
        }
    }

    private static final class StrictJavaIdentifierTransformer extends JavaIdentifierTransformer {
        private StrictJavaIdentifierTransformer() {
        }

        StrictJavaIdentifierTransformer(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 9
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String transformToJavaIdentifier(java.lang.String r4) {
            /*
                r3 = this;
                net.sf.json.JSONException r0 = new net.sf.json.JSONException
                java.lang.StringBuffer r1 = new java.lang.StringBuffer
                r1.<init>()
                java.lang.String r2 = "'"
                java.lang.StringBuffer r1 = r1.append(r2)
                java.lang.StringBuffer r1 = r1.append(r4)
                java.lang.String r2 = "' is not a valid Java identifier."
                java.lang.StringBuffer r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JavaIdentifierTransformer.StrictJavaIdentifierTransformer.transformToJavaIdentifier(java.lang.String):java.lang.String");
        }
    }

    private static final class UnderscoreJavaIdentifierTransformer extends JavaIdentifierTransformer {
        private UnderscoreJavaIdentifierTransformer() {
        }

        UnderscoreJavaIdentifierTransformer(AnonymousClass1 r1) {
            this();
        }

        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }
            char[] charArray = shaveOffNonJavaIdentifierStartChars(str).toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            boolean z = false;
            for (int i = 0; i < charArray.length; i++) {
                if (!Character.isJavaIdentifierPart(charArray[i]) || Character.isWhitespace(charArray[i])) {
                    z = true;
                } else {
                    if (z) {
                        stringBuffer.append("_");
                        z = false;
                    }
                    stringBuffer.append(charArray[i]);
                }
            }
            if (stringBuffer.charAt(stringBuffer.length() - 1) == '_') {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            return stringBuffer.toString();
        }
    }

    private static final class WhiteSpaceJavaIdentifierTransformer extends JavaIdentifierTransformer {
        private WhiteSpaceJavaIdentifierTransformer() {
        }

        WhiteSpaceJavaIdentifierTransformer(AnonymousClass1 r1) {
            this();
        }

        public String transformToJavaIdentifier(String str) {
            if (str == null) {
                return null;
            }
            char[] charArray = StringUtils.deleteWhitespace(shaveOffNonJavaIdentifierStartChars(str)).toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < charArray.length; i++) {
                if (Character.isJavaIdentifierPart(charArray[i])) {
                    stringBuffer.append(charArray[i]);
                }
            }
            return stringBuffer.toString();
        }
    }

    public abstract String transformToJavaIdentifier(String str);

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 20
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String shaveOffNonJavaIdentifierStartChars(java.lang.String r6) {
        /*
            r5 = this;
            r2 = 1
            r1 = 0
            r0 = r1
            r3 = r6
        L_0x0004:
            if (r0 != 0) goto L_0x003b
            char r4 = r3.charAt(r1)
            boolean r4 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r4 != 0) goto L_0x0039
            java.lang.String r3 = r3.substring(r2)
            int r4 = r3.length()
            if (r4 != 0) goto L_0x0004
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Can't convert '"
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r6)
            java.lang.String r2 = "' to a valid Java identifier"
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0039:
            r0 = r2
            goto L_0x0004
        L_0x003b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JavaIdentifierTransformer.shaveOffNonJavaIdentifierStartChars(java.lang.String):java.lang.String");
    }
}
