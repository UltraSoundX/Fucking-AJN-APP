package net.sf.json.util;

import net.sf.json.JSONException;

public abstract class PropertySetStrategy {
    public static final PropertySetStrategy DEFAULT = new DefaultPropertySetStrategy(null);

    /* renamed from: net.sf.json.util.PropertySetStrategy$1 reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static final class DefaultPropertySetStrategy extends PropertySetStrategy {
        private DefaultPropertySetStrategy() {
        }

        DefaultPropertySetStrategy(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 10
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
        public void setProperty(java.lang.Object r3, java.lang.String r4, java.lang.Object r5) throws net.sf.json.JSONException {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map
                if (r0 == 0) goto L_0x000a
                java.util.Map r3 = (java.util.Map) r3
                r3.put(r4, r5)
            L_0x0009:
                return
            L_0x000a:
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty(r3, r4, r5)     // Catch:{ Exception -> 0x000e }
                goto L_0x0009
            L_0x000e:
                r0 = move-exception
                net.sf.json.JSONException r1 = new net.sf.json.JSONException
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.PropertySetStrategy.DefaultPropertySetStrategy.setProperty(java.lang.Object, java.lang.String, java.lang.Object):void");
        }
    }

    public abstract void setProperty(Object obj, String str, Object obj2) throws JSONException;
}
