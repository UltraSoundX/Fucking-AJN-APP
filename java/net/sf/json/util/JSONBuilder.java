package net.sf.json.util;

import java.io.Writer;

public class JSONBuilder {
    private static final int MAXDEPTH = 20;
    private boolean comma = false;
    protected char mode = 'i';
    private char[] stack = new char[20];
    private int top = 0;
    protected Writer writer;

    public JSONBuilder(Writer writer2) {
        this.writer = writer2;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v11, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 27
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private net.sf.json.util.JSONBuilder append(java.lang.String r4) {
        /*
            r3 = this;
            r2 = 111(0x6f, float:1.56E-43)
            r1 = 97
            if (r4 != 0) goto L_0x000e
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Null pointer"
            r0.<init>(r1)
            throw r0
        L_0x000e:
            char r0 = r3.mode
            if (r0 == r2) goto L_0x0016
            char r0 = r3.mode
            if (r0 != r1) goto L_0x003d
        L_0x0016:
            boolean r0 = r3.comma     // Catch:{ IOException -> 0x0036 }
            if (r0 == 0) goto L_0x0025
            char r0 = r3.mode     // Catch:{ IOException -> 0x0036 }
            if (r0 != r1) goto L_0x0025
            java.io.Writer r0 = r3.writer     // Catch:{ IOException -> 0x0036 }
            r1 = 44
            r0.write(r1)     // Catch:{ IOException -> 0x0036 }
        L_0x0025:
            java.io.Writer r0 = r3.writer     // Catch:{ IOException -> 0x0036 }
            r0.write(r4)     // Catch:{ IOException -> 0x0036 }
            char r0 = r3.mode
            if (r0 != r2) goto L_0x0032
            r0 = 107(0x6b, float:1.5E-43)
            r3.mode = r0
        L_0x0032:
            r0 = 1
            r3.comma = r0
            return r3
        L_0x0036:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x003d:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Value out of sequence."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.append(java.lang.String):net.sf.json.util.JSONBuilder");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 14
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.util.JSONBuilder array() {
        /*
            r3 = this;
            r2 = 97
            char r0 = r3.mode
            r1 = 105(0x69, float:1.47E-43)
            if (r0 == r1) goto L_0x0012
            char r0 = r3.mode
            r1 = 111(0x6f, float:1.56E-43)
            if (r0 == r1) goto L_0x0012
            char r0 = r3.mode
            if (r0 != r2) goto L_0x001e
        L_0x0012:
            r3.push(r2)
            java.lang.String r0 = "["
            r3.append(r0)
            r0 = 0
            r3.comma = r0
            return r3
        L_0x001e:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Misplaced array."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.array():net.sf.json.util.JSONBuilder");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 16
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private net.sf.json.util.JSONBuilder end(char r3, char r4) {
        /*
            r2 = this;
            char r0 = r2.mode
            if (r0 == r3) goto L_0x0013
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r0 = 111(0x6f, float:1.56E-43)
            if (r3 != r0) goto L_0x0010
            java.lang.String r0 = "Misplaced endObject."
        L_0x000c:
            r1.<init>(r0)
            throw r1
        L_0x0010:
            java.lang.String r0 = "Misplaced endArray."
            goto L_0x000c
        L_0x0013:
            r2.pop(r3)
            java.io.Writer r0 = r2.writer     // Catch:{ IOException -> 0x001f }
            r0.write(r4)     // Catch:{ IOException -> 0x001f }
            r0 = 1
            r2.comma = r0
            return r2
        L_0x001f:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.end(char, char):net.sf.json.util.JSONBuilder");
    }

    public JSONBuilder endArray() {
        return end('a', ']');
    }

    public JSONBuilder endObject() {
        return end('k', '}');
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v9, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 24
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.util.JSONBuilder key(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x000a
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Null key."
            r0.<init>(r1)
            throw r0
        L_0x000a:
            char r0 = r2.mode
            r1 = 107(0x6b, float:1.5E-43)
            if (r0 != r1) goto L_0x003a
            boolean r0 = r2.comma     // Catch:{ IOException -> 0x0033 }
            if (r0 == 0) goto L_0x001b
            java.io.Writer r0 = r2.writer     // Catch:{ IOException -> 0x0033 }
            r1 = 44
            r0.write(r1)     // Catch:{ IOException -> 0x0033 }
        L_0x001b:
            java.io.Writer r0 = r2.writer     // Catch:{ IOException -> 0x0033 }
            java.lang.String r1 = net.sf.json.util.JSONUtils.quote(r3)     // Catch:{ IOException -> 0x0033 }
            r0.write(r1)     // Catch:{ IOException -> 0x0033 }
            java.io.Writer r0 = r2.writer     // Catch:{ IOException -> 0x0033 }
            r1 = 58
            r0.write(r1)     // Catch:{ IOException -> 0x0033 }
            r0 = 0
            r2.comma = r0     // Catch:{ IOException -> 0x0033 }
            r0 = 111(0x6f, float:1.56E-43)
            r2.mode = r0     // Catch:{ IOException -> 0x0033 }
            return r2
        L_0x0033:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x003a:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Misplaced key."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.key(java.lang.String):net.sf.json.util.JSONBuilder");
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 15
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.util.JSONBuilder object() {
        /*
            r3 = this;
            r2 = 111(0x6f, float:1.56E-43)
            char r0 = r3.mode
            r1 = 105(0x69, float:1.47E-43)
            if (r0 != r1) goto L_0x000a
            r3.mode = r2
        L_0x000a:
            char r0 = r3.mode
            if (r0 == r2) goto L_0x0014
            char r0 = r3.mode
            r1 = 97
            if (r0 != r1) goto L_0x0022
        L_0x0014:
            java.lang.String r0 = "{"
            r3.append(r0)
            r0 = 107(0x6b, float:1.5E-43)
            r3.push(r0)
            r0 = 0
            r3.comma = r0
            return r3
        L_0x0022:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Misplaced object."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.object():net.sf.json.util.JSONBuilder");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 22
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void pop(char r3) {
        /*
            r2 = this;
            int r0 = r2.top
            if (r0 <= 0) goto L_0x000e
            char[] r0 = r2.stack
            int r1 = r2.top
            int r1 = r1 + -1
            char r0 = r0[r1]
            if (r0 == r3) goto L_0x0016
        L_0x000e:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Nesting error."
            r0.<init>(r1)
            throw r0
        L_0x0016:
            int r0 = r2.top
            int r0 = r0 + -1
            r2.top = r0
            int r0 = r2.top
            if (r0 != 0) goto L_0x0025
            r0 = 100
        L_0x0022:
            r2.mode = r0
            return
        L_0x0025:
            char[] r0 = r2.stack
            int r1 = r2.top
            int r1 = r1 + -1
            char r0 = r0[r1]
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.pop(char):void");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 13
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void push(char r3) {
        /*
            r2 = this;
            int r0 = r2.top
            r1 = 20
            if (r0 < r1) goto L_0x000e
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Nesting too deep."
            r0.<init>(r1)
            throw r0
        L_0x000e:
            char[] r0 = r2.stack
            int r1 = r2.top
            r0[r1] = r3
            r2.mode = r3
            int r0 = r2.top
            int r0 = r0 + 1
            r2.top = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONBuilder.push(char):void");
    }

    public JSONBuilder value(boolean z) {
        return append(z ? "true" : "false");
    }

    public JSONBuilder value(double d) {
        return value((Object) new Double(d));
    }

    public JSONBuilder value(long j) {
        return append(Long.toString(j));
    }

    public JSONBuilder value(Object obj) {
        return append(JSONUtils.valueToString(obj));
    }
}
