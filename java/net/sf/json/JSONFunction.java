package net.sf.json;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class JSONFunction implements Serializable {
    private static final String[] EMPTY_PARAM_ARRAY = new String[0];
    private String[] params;
    private String text;

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
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
    public static net.sf.json.JSONFunction parse(java.lang.String r4) {
        /*
            boolean r0 = net.sf.json.util.JSONUtils.isFunction(r4)
            if (r0 != 0) goto L_0x001f
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "String is not a function. "
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x001f:
            java.lang.String r1 = net.sf.json.util.JSONUtils.getFunctionParams(r4)
            java.lang.String r0 = net.sf.json.util.JSONUtils.getFunctionBody(r4)
            net.sf.json.JSONFunction r2 = new net.sf.json.JSONFunction
            if (r1 == 0) goto L_0x0037
            java.lang.String r3 = ","
            java.lang.String[] r1 = org.apache.commons.lang.StringUtils.split(r1, r3)
        L_0x0031:
            if (r0 == 0) goto L_0x0039
        L_0x0033:
            r2.<init>(r1, r0)
            return r2
        L_0x0037:
            r1 = 0
            goto L_0x0031
        L_0x0039:
            java.lang.String r0 = ""
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONFunction.parse(java.lang.String):net.sf.json.JSONFunction");
    }

    public JSONFunction(String str) {
        this(null, str);
    }

    public JSONFunction(String[] strArr, String str) {
        this.text = str != null ? str.trim() : "";
        if (strArr == null) {
            this.params = EMPTY_PARAM_ARRAY;
        } else if (strArr.length != 1 || !strArr[0].trim().equals("")) {
            this.params = new String[strArr.length];
            System.arraycopy(strArr, 0, this.params, 0, strArr.length);
            for (int i = 0; i < strArr.length; i++) {
                this.params[i] = this.params[i].trim();
            }
        } else {
            this.params = EMPTY_PARAM_ARRAY;
        }
    }

    public boolean equals(Object obj) {
        boolean z = 0;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return z;
        }
        if (obj instanceof String) {
            try {
                return equals(parse((String) obj));
            } catch (JSONException e) {
                return z;
            }
        } else if (!(obj instanceof JSONFunction)) {
            return z;
        } else {
            JSONFunction jSONFunction = (JSONFunction) obj;
            if (this.params.length != jSONFunction.params.length) {
                return z;
            }
            EqualsBuilder equalsBuilder = new EqualsBuilder();
            for (int i = z; i < this.params.length; i++) {
                equalsBuilder.append(this.params[i], jSONFunction.params[i]);
            }
            equalsBuilder.append(this.text, jSONFunction.text);
            return equalsBuilder.isEquals();
        }
    }

    public String[] getParams() {
        return this.params;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        for (String append : this.params) {
            hashCodeBuilder.append(append);
        }
        hashCodeBuilder.append(this.text);
        return hashCodeBuilder.toHashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("function(");
        if (this.params.length > 0) {
            for (int i = 0; i < this.params.length - 1; i++) {
                stringBuffer.append(this.params[i]).append(',');
            }
            stringBuffer.append(this.params[this.params.length - 1]);
        }
        stringBuffer.append("){");
        if (this.text.length() > 0) {
            stringBuffer.append(' ').append(this.text).append(' ');
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
