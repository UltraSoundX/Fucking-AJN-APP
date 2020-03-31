package net.sf.json.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class CycleDetectionStrategy {
    public static final JSONArray IGNORE_PROPERTY_ARR = new JSONArray();
    public static final JSONObject IGNORE_PROPERTY_OBJ = new JSONObject();
    public static final CycleDetectionStrategy LENIENT = new LenientCycleDetectionStrategy(null);
    public static final CycleDetectionStrategy NOPROP = new LenientNoRefCycleDetectionStrategy(null);
    public static final CycleDetectionStrategy STRICT = new StrictCycleDetectionStrategy(null);

    /* renamed from: net.sf.json.util.CycleDetectionStrategy$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class LenientCycleDetectionStrategy extends CycleDetectionStrategy {
        private LenientCycleDetectionStrategy() {
        }

        LenientCycleDetectionStrategy(AnonymousClass1 r1) {
            this();
        }

        public JSONArray handleRepeatedReferenceAsArray(Object obj) {
            return new JSONArray();
        }

        public JSONObject handleRepeatedReferenceAsObject(Object obj) {
            return new JSONObject(true);
        }
    }

    private static final class LenientNoRefCycleDetectionStrategy extends CycleDetectionStrategy {
        private LenientNoRefCycleDetectionStrategy() {
        }

        LenientNoRefCycleDetectionStrategy(AnonymousClass1 r1) {
            this();
        }

        public JSONArray handleRepeatedReferenceAsArray(Object obj) {
            return CycleDetectionStrategy.IGNORE_PROPERTY_ARR;
        }

        public JSONObject handleRepeatedReferenceAsObject(Object obj) {
            return CycleDetectionStrategy.IGNORE_PROPERTY_OBJ;
        }
    }

    private static final class StrictCycleDetectionStrategy extends CycleDetectionStrategy {
        private StrictCycleDetectionStrategy() {
        }

        StrictCycleDetectionStrategy(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 3
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
        public net.sf.json.JSONArray handleRepeatedReferenceAsArray(java.lang.Object r3) {
            /*
                r2 = this;
                net.sf.json.JSONException r0 = new net.sf.json.JSONException
                java.lang.String r1 = "There is a cycle in the hierarchy!"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.CycleDetectionStrategy.StrictCycleDetectionStrategy.handleRepeatedReferenceAsArray(java.lang.Object):net.sf.json.JSONArray");
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 3
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
        public net.sf.json.JSONObject handleRepeatedReferenceAsObject(java.lang.Object r3) {
            /*
                r2 = this;
                net.sf.json.JSONException r0 = new net.sf.json.JSONException
                java.lang.String r1 = "There is a cycle in the hierarchy!"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.CycleDetectionStrategy.StrictCycleDetectionStrategy.handleRepeatedReferenceAsObject(java.lang.Object):net.sf.json.JSONObject");
        }
    }

    public abstract JSONArray handleRepeatedReferenceAsArray(Object obj);

    public abstract JSONObject handleRepeatedReferenceAsObject(Object obj);
}
