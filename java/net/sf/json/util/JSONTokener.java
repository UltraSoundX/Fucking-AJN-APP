package net.sf.json.util;

import net.sf.json.JSONException;
import net.sf.json.JsonConfig;
import net.sf.json.regexp.RegexpUtils;

public class JSONTokener {
    private int myIndex = 0;
    private String mySource;

    public static int dehexchar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c < 'a' || c > 'f') {
            return -1;
        }
        return c - 'W';
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONTokener(java.lang.String r5) {
        /*
            r4 = this;
            r2 = 0
            r4.<init>()
            r4.myIndex = r2
            if (r5 == 0) goto L_0x002f
            java.lang.String r0 = r5.trim()
        L_0x000c:
            int r1 = r0.length()
            if (r1 <= 0) goto L_0x0041
            char r1 = r0.charAt(r2)
            int r2 = r0.length()
            int r2 = r2 + -1
            char r2 = r0.charAt(r2)
            r3 = 91
            if (r1 != r3) goto L_0x0032
            r3 = 93
            if (r2 == r3) goto L_0x0032
            java.lang.String r0 = "Found starting '[' but missing ']' at the end."
            net.sf.json.JSONException r0 = r4.syntaxError(r0)
            throw r0
        L_0x002f:
            java.lang.String r0 = ""
            goto L_0x000c
        L_0x0032:
            r3 = 123(0x7b, float:1.72E-43)
            if (r1 != r3) goto L_0x0041
            r1 = 125(0x7d, float:1.75E-43)
            if (r2 == r1) goto L_0x0041
            java.lang.String r0 = "Found starting '{' but missing '}' at the end."
            net.sf.json.JSONException r0 = r4.syntaxError(r0)
            throw r0
        L_0x0041:
            r4.mySource = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.<init>(java.lang.String):void");
    }

    public void back() {
        if (this.myIndex > 0) {
            this.myIndex--;
        }
    }

    public int length() {
        if (this.mySource == null) {
            return 0;
        }
        return this.mySource.length();
    }

    public boolean matches(String str) {
        return RegexpUtils.getMatcher(str).matches(this.mySource.substring(this.myIndex));
    }

    public boolean more() {
        return this.myIndex < this.mySource.length();
    }

    public char next() {
        if (!more()) {
            return 0;
        }
        char charAt = this.mySource.charAt(this.myIndex);
        this.myIndex++;
        return charAt;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public char next(char r4) {
        /*
            r3 = this;
            char r0 = r3.next()
            if (r0 == r4) goto L_0x002e
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Expected '"
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "' and instead saw '"
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r0 = r1.append(r0)
            java.lang.String r1 = "'."
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            net.sf.json.JSONException r0 = r3.syntaxError(r0)
            throw r0
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.next(char):char");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public java.lang.String next(int r4) {
        /*
            r3 = this;
            int r0 = r3.myIndex
            int r1 = r0 + r4
            java.lang.String r2 = r3.mySource
            int r2 = r2.length()
            if (r1 < r2) goto L_0x0013
            java.lang.String r0 = "Substring bounds error"
            net.sf.json.JSONException r0 = r3.syntaxError(r0)
            throw r0
        L_0x0013:
            int r2 = r3.myIndex
            int r2 = r2 + r4
            r3.myIndex = r2
            java.lang.String r2 = r3.mySource
            java.lang.String r0 = r2.substring(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.next(int):java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: CFG modification limit reached, blocks count: 142 */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0006, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r1 == 10) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r1 == 13) goto L_0x0006;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 28
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
    public char nextClean() {
        /*
            r5 = this;
            r4 = 13
            r3 = 10
            r0 = 47
        L_0x0006:
            char r1 = r5.next()
            if (r1 != r0) goto L_0x003d
            char r1 = r5.next()
            switch(r1) {
                case 42: goto L_0x0025;
                case 47: goto L_0x0017;
                default: goto L_0x0013;
            }
        L_0x0013:
            r5.back()
        L_0x0016:
            return r0
        L_0x0017:
            char r1 = r5.next()
            if (r1 == r3) goto L_0x0006
            if (r1 == r4) goto L_0x0006
            if (r1 != 0) goto L_0x0017
            goto L_0x0006
        L_0x0022:
            r5.back()
        L_0x0025:
            char r1 = r5.next()
            if (r1 != 0) goto L_0x0032
            java.lang.String r0 = "Unclosed comment."
            net.sf.json.JSONException r0 = r5.syntaxError(r0)
            throw r0
        L_0x0032:
            r2 = 42
            if (r1 != r2) goto L_0x0025
            char r1 = r5.next()
            if (r1 != r0) goto L_0x0022
            goto L_0x0006
        L_0x003d:
            r2 = 35
            if (r1 != r2) goto L_0x004c
        L_0x0041:
            char r1 = r5.next()
            if (r1 == r3) goto L_0x0006
            if (r1 == r4) goto L_0x0006
            if (r1 != 0) goto L_0x0041
            goto L_0x0006
        L_0x004c:
            if (r1 == 0) goto L_0x0052
            r2 = 32
            if (r1 <= r2) goto L_0x0006
        L_0x0052:
            r0 = r1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.nextClean():char");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 26
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
    public java.lang.String nextString(char r4) {
        /*
            r3 = this;
            r2 = 16
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0007:
            char r1 = r3.next()
            switch(r1) {
                case 0: goto L_0x0015;
                case 10: goto L_0x0015;
                case 13: goto L_0x0015;
                case 92: goto L_0x001c;
                default: goto L_0x000e;
            }
        L_0x000e:
            if (r1 != r4) goto L_0x0061
            java.lang.String r0 = r0.toString()
            return r0
        L_0x0015:
            java.lang.String r0 = "Unterminated string"
            net.sf.json.JSONException r0 = r3.syntaxError(r0)
            throw r0
        L_0x001c:
            char r1 = r3.next()
            switch(r1) {
                case 98: goto L_0x0027;
                case 102: goto L_0x0039;
                case 110: goto L_0x0033;
                case 114: goto L_0x003f;
                case 116: goto L_0x002d;
                case 117: goto L_0x0045;
                case 120: goto L_0x0053;
                default: goto L_0x0023;
            }
        L_0x0023:
            r0.append(r1)
            goto L_0x0007
        L_0x0027:
            r1 = 8
            r0.append(r1)
            goto L_0x0007
        L_0x002d:
            r1 = 9
            r0.append(r1)
            goto L_0x0007
        L_0x0033:
            r1 = 10
            r0.append(r1)
            goto L_0x0007
        L_0x0039:
            r1 = 12
            r0.append(r1)
            goto L_0x0007
        L_0x003f:
            r1 = 13
            r0.append(r1)
            goto L_0x0007
        L_0x0045:
            r1 = 4
            java.lang.String r1 = r3.next(r1)
            int r1 = java.lang.Integer.parseInt(r1, r2)
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0007
        L_0x0053:
            r1 = 2
            java.lang.String r1 = r3.next(r1)
            int r1 = java.lang.Integer.parseInt(r1, r2)
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0007
        L_0x0061:
            r0.append(r1)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.nextString(char):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String nextTo(char r4) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            char r1 = r3.next()
            if (r1 == r4) goto L_0x0015
            if (r1 == 0) goto L_0x0015
            r2 = 10
            if (r1 == r2) goto L_0x0015
            r2 = 13
            if (r1 != r2) goto L_0x0023
        L_0x0015:
            if (r1 == 0) goto L_0x001a
            r3.back()
        L_0x001a:
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            return r0
        L_0x0023:
            r0.append(r1)
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.nextTo(char):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String nextTo(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            char r1 = r3.next()
            int r2 = r4.indexOf(r1)
            if (r2 >= 0) goto L_0x0019
            if (r1 == 0) goto L_0x0019
            r2 = 10
            if (r1 == r2) goto L_0x0019
            r2 = 13
            if (r1 != r2) goto L_0x0027
        L_0x0019:
            if (r1 == 0) goto L_0x001e
            r3.back()
        L_0x001e:
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            return r0
        L_0x0027:
            r0.append(r1)
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.nextTo(java.lang.String):java.lang.String");
    }

    public Object nextValue() {
        return nextValue(new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v35, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v42, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v35, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 93
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
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object nextValue(net.sf.json.JsonConfig r8) {
        /*
            r7 = this;
            r6 = 48
            r5 = 2
            r4 = 1
            char r2 = r7.nextClean()
            switch(r2) {
                case 34: goto L_0x0025;
                case 39: goto L_0x0025;
                case 91: goto L_0x0032;
                case 123: goto L_0x002a;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r0 = r2
        L_0x0011:
            r3 = 32
            if (r0 < r3) goto L_0x003a
            java.lang.String r3 = ",:]}/\\\"[{;=#"
            int r3 = r3.indexOf(r0)
            if (r3 >= 0) goto L_0x003a
            r1.append(r0)
            char r0 = r7.next()
            goto L_0x0011
        L_0x0025:
            java.lang.String r0 = r7.nextString(r2)
        L_0x0029:
            return r0
        L_0x002a:
            r7.back()
            net.sf.json.JSONObject r0 = net.sf.json.JSONObject.fromObject(r7, r8)
            goto L_0x0029
        L_0x0032:
            r7.back()
            net.sf.json.JSONArray r0 = net.sf.json.JSONArray.fromObject(r7, r8)
            goto L_0x0029
        L_0x003a:
            r7.back()
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = ""
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0054
            java.lang.String r0 = "Missing value."
            net.sf.json.JSONException r0 = r7.syntaxError(r0)
            throw r0
        L_0x0054:
            java.lang.String r0 = "true"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x005f
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x0029
        L_0x005f:
            java.lang.String r0 = "false"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x006a
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            goto L_0x0029
        L_0x006a:
            java.lang.String r0 = "null"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0077
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            goto L_0x0029
        L_0x0077:
            if (r2 < r6) goto L_0x007d
            r0 = 57
            if (r2 <= r0) goto L_0x0089
        L_0x007d:
            r0 = 46
            if (r2 == r0) goto L_0x0089
            r0 = 45
            if (r2 == r0) goto L_0x0089
            r0 = 43
            if (r2 != r0) goto L_0x00de
        L_0x0089:
            if (r2 != r6) goto L_0x00b4
            int r0 = r1.length()
            if (r0 <= r5) goto L_0x00cf
            char r0 = r1.charAt(r4)
            r2 = 120(0x78, float:1.68E-43)
            if (r0 == r2) goto L_0x00a1
            char r0 = r1.charAt(r4)
            r2 = 88
            if (r0 != r2) goto L_0x00cf
        L_0x00a1:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00b3 }
            r2 = 2
            java.lang.String r2 = r1.substring(r2)     // Catch:{ Exception -> 0x00b3 }
            r3 = 16
            int r2 = java.lang.Integer.parseInt(r2, r3)     // Catch:{ Exception -> 0x00b3 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00b3 }
            goto L_0x0029
        L_0x00b3:
            r0 = move-exception
        L_0x00b4:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00bb }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00bb }
            goto L_0x0029
        L_0x00bb:
            r0 = move-exception
            java.lang.Long r0 = new java.lang.Long     // Catch:{ Exception -> 0x00c3 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00c3 }
            goto L_0x0029
        L_0x00c3:
            r0 = move-exception
            java.lang.Double r0 = new java.lang.Double     // Catch:{ Exception -> 0x00cb }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00cb }
            goto L_0x0029
        L_0x00cb:
            r0 = move-exception
            r0 = r1
            goto L_0x0029
        L_0x00cf:
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ Exception -> 0x00dc }
            r2 = 8
            int r2 = java.lang.Integer.parseInt(r1, r2)     // Catch:{ Exception -> 0x00dc }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00dc }
            goto L_0x0029
        L_0x00dc:
            r0 = move-exception
            goto L_0x00b4
        L_0x00de:
            boolean r0 = net.sf.json.util.JSONUtils.isFunctionHeader(r1)
            if (r0 != 0) goto L_0x00ea
            boolean r0 = net.sf.json.util.JSONUtils.isFunction(r1)
            if (r0 == 0) goto L_0x00ed
        L_0x00ea:
            r0 = r1
            goto L_0x0029
        L_0x00ed:
            char r0 = r7.peek()
            switch(r0) {
                case 44: goto L_0x00f7;
                case 91: goto L_0x00f7;
                case 93: goto L_0x00f7;
                case 123: goto L_0x00f7;
                case 125: goto L_0x00f7;
                default: goto L_0x00f4;
            }
        L_0x00f4:
            r0 = r1
            goto L_0x0029
        L_0x00f7:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Unquotted string '"
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r1 = r2.append(r1)
            java.lang.String r2 = "'"
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONTokener.nextValue(net.sf.json.JsonConfig):java.lang.Object");
    }

    public char peek() {
        if (more()) {
            return this.mySource.charAt(this.myIndex);
        }
        return 0;
    }

    public void reset() {
        this.myIndex = 0;
    }

    public void skipPast(String str) {
        this.myIndex = this.mySource.indexOf(str, this.myIndex);
        if (this.myIndex < 0) {
            this.myIndex = this.mySource.length();
        } else {
            this.myIndex += str.length();
        }
    }

    public char skipTo(char c) {
        char next;
        int i = this.myIndex;
        while (true) {
            next = next();
            if (next != 0) {
                if (next == c) {
                    back();
                    break;
                }
            } else {
                this.myIndex = i;
                break;
            }
        }
        return next;
    }

    public JSONException syntaxError(String str) {
        return new JSONException(new StringBuffer().append(str).append(toString()).toString());
    }

    public String toString() {
        return new StringBuffer().append(" at character ").append(this.myIndex).append(" of ").append(this.mySource).toString();
    }
}
