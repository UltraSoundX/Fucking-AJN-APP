package net.sf.json;

import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;

public class JSONSerializer {
    public static Object toJava(JSON json) {
        return toJava(json, new JsonConfig());
    }

    public static Object toJava(JSON json, JsonConfig jsonConfig) {
        if (JSONUtils.isNull(json)) {
            return null;
        }
        if (!(json instanceof JSONArray)) {
            return JSONObject.toBean((JSONObject) json, jsonConfig);
        }
        if (jsonConfig.getArrayMode() == 2) {
            return JSONArray.toArray((JSONArray) json, jsonConfig);
        }
        return JSONArray.toCollection((JSONArray) json, jsonConfig);
    }

    public static JSON toJSON(Object obj) {
        return toJSON(obj, new JsonConfig());
    }

    public static JSON toJSON(Object obj, JsonConfig jsonConfig) {
        if (obj == null) {
            return JSONNull.getInstance();
        }
        if (obj instanceof JSONString) {
            return toJSON((JSONString) obj, jsonConfig);
        }
        if (obj instanceof String) {
            return toJSON((String) obj, jsonConfig);
        }
        if (JSONUtils.isArray(obj)) {
            return JSONArray.fromObject(obj, jsonConfig);
        }
        try {
            return JSONObject.fromObject(obj, jsonConfig);
        } catch (JSONException e) {
            if (obj instanceof JSONTokener) {
                ((JSONTokener) obj).reset();
            }
            return JSONArray.fromObject(obj, jsonConfig);
        }
    }

    private static JSON toJSON(JSONString jSONString, JsonConfig jsonConfig) {
        return toJSON(jSONString.toJSONString(), jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 18
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
    private static net.sf.json.JSON toJSON(java.lang.String r2, net.sf.json.JsonConfig r3) {
        /*
            java.lang.String r0 = "["
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x000d
            net.sf.json.JSONArray r0 = net.sf.json.JSONArray.fromObject(r2, r3)
        L_0x000c:
            return r0
        L_0x000d:
            java.lang.String r0 = "{"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x001a
            net.sf.json.JSONObject r0 = net.sf.json.JSONObject.fromObject(r2, r3)
            goto L_0x000c
        L_0x001a:
            java.lang.String r0 = "null"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0027
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            goto L_0x000c
        L_0x0027:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Invalid JSON String"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONSerializer.toJSON(java.lang.String, net.sf.json.JsonConfig):net.sf.json.JSON");
    }
}
