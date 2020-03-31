package net.sf.json.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.JsonConfig;
import net.sf.json.regexp.RegexpMatcher;
import net.sf.json.regexp.RegexpUtils;
import org.apache.commons.beanutils.DynaBean;

public final class JSONUtils {
    public static final String DOUBLE_QUOTE = "\"";
    private static RegexpMatcher FUNCTION_BODY_MATCHER = RegexpUtils.getMatcher(FUNCTION_BODY_PATTERN);
    private static final String FUNCTION_BODY_PATTERN = "^function[ ]?\\(.*?\\)[ \n\t]*\\{(.*?)\\}$";
    private static RegexpMatcher FUNCTION_HEADER_MATCHER = RegexpUtils.getMatcher(FUNCTION_HEADER_PATTERN);
    private static final String FUNCTION_HEADER_PATTERN = "^function[ ]?\\(.*?\\)$";
    private static RegexpMatcher FUNCTION_MACTHER = RegexpUtils.getMatcher(FUNCTION_PATTERN, true);
    private static RegexpMatcher FUNCTION_PARAMS_MATCHER = RegexpUtils.getMatcher(FUNCTION_PARAMS_PATTERN);
    private static final String FUNCTION_PARAMS_PATTERN = "^function[ ]?\\((.*?)\\).*";
    private static final String FUNCTION_PATTERN = "^function[ ]?\\(.*?\\)[ \n\t]*\\{.*?\\}$";
    private static final String FUNCTION_PREFIX = "function";
    public static final String SINGLE_QUOTE = "'";
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Character;
    static Class class$java$lang$Double;
    static Class class$java$lang$Float;
    static Class class$java$lang$Integer;
    static Class class$java$lang$Long;
    static Class class$java$lang$Number;
    static Class class$java$lang$Object;
    static Class class$java$lang$String;
    static Class class$java$math$BigDecimal;
    static Class class$java$math$BigInteger;
    static Class class$java$util$Collection;
    static Class class$java$util$List;
    static Class class$net$sf$json$JSONArray;
    static Class class$net$sf$json$JSONFunction;
    private static final MorpherRegistry morpherRegistry = new MorpherRegistry();

    static {
        MorphUtils.registerStandardMorphers(morpherRegistry);
    }

    public static String convertToJavaIdentifier(String str) {
        return convertToJavaIdentifier(str, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 8
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
    public static java.lang.String convertToJavaIdentifier(java.lang.String r2, net.sf.json.JsonConfig r3) {
        /*
            net.sf.json.util.JavaIdentifierTransformer r0 = r3.getJavaIdentifierTransformer()     // Catch:{ JSONException -> 0x0009, Exception -> 0x000b }
            java.lang.String r0 = r0.transformToJavaIdentifier(r2)     // Catch:{ JSONException -> 0x0009, Exception -> 0x000b }
            return r0
        L_0x0009:
            r0 = move-exception
            throw r0
        L_0x000b:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.convertToJavaIdentifier(java.lang.String, net.sf.json.JsonConfig):java.lang.String");
    }

    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }
        String d2 = Double.toString(d);
        if (d2.indexOf(46) <= 0 || d2.indexOf(101) >= 0 || d2.indexOf(69) >= 0) {
            return d2;
        }
        while (d2.endsWith("0")) {
            d2 = d2.substring(0, d2.length() - 1);
        }
        if (d2.endsWith(".")) {
            return d2.substring(0, d2.length() - 1);
        }
        return d2;
    }

    public static String getFunctionBody(String str) {
        return FUNCTION_BODY_MATCHER.getGroupIfMatches(str, 1);
    }

    public static String getFunctionParams(String str) {
        return FUNCTION_PARAMS_MATCHER.getGroupIfMatches(str, 1);
    }

    public static Class getInnerComponentType(Class cls) {
        return !cls.isArray() ? cls : getInnerComponentType(cls.getComponentType());
    }

    public static MorpherRegistry getMorpherRegistry() {
        return morpherRegistry;
    }

    public static Map getProperties(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, getTypeClass(jSONObject.get(str)));
        }
        return hashMap;
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v22, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v7, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 129
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
    public static java.lang.Class getTypeClass(java.lang.Object r2) {
        /*
            boolean r0 = isNull(r2)
            if (r0 == 0) goto L_0x0016
            java.lang.Class r0 = class$java$lang$Object
            if (r0 != 0) goto L_0x0013
            java.lang.String r0 = "java.lang.Object"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Object = r0
        L_0x0012:
            return r0
        L_0x0013:
            java.lang.Class r0 = class$java$lang$Object
            goto L_0x0012
        L_0x0016:
            boolean r0 = isArray(r2)
            if (r0 == 0) goto L_0x002c
            java.lang.Class r0 = class$java$util$List
            if (r0 != 0) goto L_0x0029
            java.lang.String r0 = "java.util.List"
            java.lang.Class r0 = class$(r0)
            class$java$util$List = r0
            goto L_0x0012
        L_0x0029:
            java.lang.Class r0 = class$java$util$List
            goto L_0x0012
        L_0x002c:
            boolean r0 = isFunction(r2)
            if (r0 == 0) goto L_0x0042
            java.lang.Class r0 = class$net$sf$json$JSONFunction
            if (r0 != 0) goto L_0x003f
            java.lang.String r0 = "net.sf.json.JSONFunction"
            java.lang.Class r0 = class$(r0)
            class$net$sf$json$JSONFunction = r0
            goto L_0x0012
        L_0x003f:
            java.lang.Class r0 = class$net$sf$json$JSONFunction
            goto L_0x0012
        L_0x0042:
            boolean r0 = isBoolean(r2)
            if (r0 == 0) goto L_0x0058
            java.lang.Class r0 = class$java$lang$Boolean
            if (r0 != 0) goto L_0x0055
            java.lang.String r0 = "java.lang.Boolean"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Boolean = r0
            goto L_0x0012
        L_0x0055:
            java.lang.Class r0 = class$java$lang$Boolean
            goto L_0x0012
        L_0x0058:
            boolean r0 = isNumber(r2)
            if (r0 == 0) goto L_0x00f4
            java.lang.Number r2 = (java.lang.Number) r2
            boolean r0 = isInteger(r2)
            if (r0 == 0) goto L_0x0076
            java.lang.Class r0 = class$java$lang$Integer
            if (r0 != 0) goto L_0x0073
            java.lang.String r0 = "java.lang.Integer"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Integer = r0
            goto L_0x0012
        L_0x0073:
            java.lang.Class r0 = class$java$lang$Integer
            goto L_0x0012
        L_0x0076:
            boolean r0 = isLong(r2)
            if (r0 == 0) goto L_0x008c
            java.lang.Class r0 = class$java$lang$Long
            if (r0 != 0) goto L_0x0089
            java.lang.String r0 = "java.lang.Long"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Long = r0
            goto L_0x0012
        L_0x0089:
            java.lang.Class r0 = class$java$lang$Long
            goto L_0x0012
        L_0x008c:
            boolean r0 = isFloat(r2)
            if (r0 == 0) goto L_0x00a4
            java.lang.Class r0 = class$java$lang$Float
            if (r0 != 0) goto L_0x00a0
            java.lang.String r0 = "java.lang.Float"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Float = r0
            goto L_0x0012
        L_0x00a0:
            java.lang.Class r0 = class$java$lang$Float
            goto L_0x0012
        L_0x00a4:
            boolean r0 = isBigInteger(r2)
            if (r0 == 0) goto L_0x00bc
            java.lang.Class r0 = class$java$math$BigInteger
            if (r0 != 0) goto L_0x00b8
            java.lang.String r0 = "java.math.BigInteger"
            java.lang.Class r0 = class$(r0)
            class$java$math$BigInteger = r0
            goto L_0x0012
        L_0x00b8:
            java.lang.Class r0 = class$java$math$BigInteger
            goto L_0x0012
        L_0x00bc:
            boolean r0 = isBigDecimal(r2)
            if (r0 == 0) goto L_0x00d4
            java.lang.Class r0 = class$java$math$BigDecimal
            if (r0 != 0) goto L_0x00d0
            java.lang.String r0 = "java.math.BigDecimal"
            java.lang.Class r0 = class$(r0)
            class$java$math$BigDecimal = r0
            goto L_0x0012
        L_0x00d0:
            java.lang.Class r0 = class$java$math$BigDecimal
            goto L_0x0012
        L_0x00d4:
            boolean r0 = isDouble(r2)
            if (r0 == 0) goto L_0x00ec
            java.lang.Class r0 = class$java$lang$Double
            if (r0 != 0) goto L_0x00e8
            java.lang.String r0 = "java.lang.Double"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Double = r0
            goto L_0x0012
        L_0x00e8:
            java.lang.Class r0 = class$java$lang$Double
            goto L_0x0012
        L_0x00ec:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Unsupported type"
            r0.<init>(r1)
            throw r0
        L_0x00f4:
            boolean r0 = isString(r2)
            if (r0 == 0) goto L_0x010c
            java.lang.Class r0 = class$java$lang$String
            if (r0 != 0) goto L_0x0108
            java.lang.String r0 = "java.lang.String"
            java.lang.Class r0 = class$(r0)
            class$java$lang$String = r0
            goto L_0x0012
        L_0x0108:
            java.lang.Class r0 = class$java$lang$String
            goto L_0x0012
        L_0x010c:
            boolean r0 = isObject(r2)
            if (r0 == 0) goto L_0x0124
            java.lang.Class r0 = class$java$lang$Object
            if (r0 != 0) goto L_0x0120
            java.lang.String r0 = "java.lang.Object"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Object = r0
            goto L_0x0012
        L_0x0120:
            java.lang.Class r0 = class$java$lang$Object
            goto L_0x0012
        L_0x0124:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Unsupported type"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.getTypeClass(java.lang.Object):java.lang.Class");
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return JSONNull.getInstance().hashCode();
        }
        if ((obj instanceof JSON) || (obj instanceof String) || (obj instanceof JSONFunction)) {
            return obj.hashCode();
        }
        return String.valueOf(obj).hashCode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r0.isAssignableFrom(r1) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isArray(java.lang.Class r1) {
        /*
            if (r1 == 0) goto L_0x0034
            boolean r0 = r1.isArray()
            if (r0 != 0) goto L_0x002c
            java.lang.Class r0 = class$java$util$Collection
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = "java.util.Collection"
            java.lang.Class r0 = class$(r0)
            class$java$util$Collection = r0
        L_0x0014:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x002c
            java.lang.Class r0 = class$net$sf$json$JSONArray
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "net.sf.json.JSONArray"
            java.lang.Class r0 = class$(r0)
            class$net$sf$json$JSONArray = r0
        L_0x0026:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0034
        L_0x002c:
            r0 = 1
        L_0x002d:
            return r0
        L_0x002e:
            java.lang.Class r0 = class$java$util$Collection
            goto L_0x0014
        L_0x0031:
            java.lang.Class r0 = class$net$sf$json$JSONArray
            goto L_0x0026
        L_0x0034:
            r0 = 0
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isArray(java.lang.Class):boolean");
    }

    public static boolean isArray(Object obj) {
        if ((obj == null || !obj.getClass().isArray()) && !(obj instanceof Collection) && !(obj instanceof JSONArray)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r0.isAssignableFrom(r1) != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isBoolean(java.lang.Class r1) {
        /*
            if (r1 == 0) goto L_0x0021
            java.lang.Class r0 = java.lang.Boolean.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x001c
            java.lang.Class r0 = class$java$lang$Boolean
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = "java.lang.Boolean"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Boolean = r0
        L_0x0016:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0021
        L_0x001c:
            r0 = 1
        L_0x001d:
            return r0
        L_0x001e:
            java.lang.Class r0 = class$java$lang$Boolean
            goto L_0x0016
        L_0x0021:
            r0 = 0
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isBoolean(java.lang.Class):boolean");
    }

    public static boolean isBoolean(Object obj) {
        if ((obj instanceof Boolean) || (obj != null && obj.getClass() == Boolean.TYPE)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r0.isAssignableFrom(r1) != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isDouble(java.lang.Class r1) {
        /*
            if (r1 == 0) goto L_0x0021
            java.lang.Class r0 = java.lang.Double.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x001c
            java.lang.Class r0 = class$java$lang$Double
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = "java.lang.Double"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Double = r0
        L_0x0016:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0021
        L_0x001c:
            r0 = 1
        L_0x001d:
            return r0
        L_0x001e:
            java.lang.Class r0 = class$java$lang$Double
            goto L_0x0016
        L_0x0021:
            r0 = 0
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isDouble(java.lang.Class):boolean");
    }

    public static boolean isFunction(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (!str.startsWith("function") || !FUNCTION_MACTHER.matches(str)) {
                return false;
            }
            return true;
        } else if (!(obj instanceof JSONFunction)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isFunctionHeader(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        String str = (String) obj;
        if (!str.startsWith("function") || !FUNCTION_HEADER_MATCHER.matches(str)) {
            return false;
        }
        return true;
    }

    public static boolean isJavaIdentifier(String str) {
        if (str.length() == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object obj) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).isNullObject();
        }
        return JSONNull.getInstance().equals(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r0.isAssignableFrom(r1) != false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isNumber(java.lang.Class r1) {
        /*
            if (r1 == 0) goto L_0x0049
            java.lang.Class r0 = java.lang.Byte.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = java.lang.Short.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = java.lang.Float.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = java.lang.Double.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x0044
            java.lang.Class r0 = class$java$lang$Number
            if (r0 != 0) goto L_0x0046
            java.lang.String r0 = "java.lang.Number"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Number = r0
        L_0x003e:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0049
        L_0x0044:
            r0 = 1
        L_0x0045:
            return r0
        L_0x0046:
            java.lang.Class r0 = class$java$lang$Number
            goto L_0x003e
        L_0x0049:
            r0 = 0
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isNumber(java.lang.Class):boolean");
    }

    public static boolean isNumber(Object obj) {
        if ((obj == null || obj.getClass() != Byte.TYPE) && ((obj == null || obj.getClass() != Short.TYPE) && ((obj == null || obj.getClass() != Integer.TYPE) && ((obj == null || obj.getClass() != Long.TYPE) && ((obj == null || obj.getClass() != Float.TYPE) && (obj == null || obj.getClass() != Double.TYPE)))))) {
            return obj instanceof Number;
        }
        return true;
    }

    public static boolean isObject(Object obj) {
        return (!isNumber(obj) && !isString(obj) && !isBoolean(obj) && !isArray(obj) && !isFunction(obj)) || isNull(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (r0.isAssignableFrom(r1) != false) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isString(java.lang.Class r1) {
        /*
            if (r1 == 0) goto L_0x0036
            java.lang.Class r0 = class$java$lang$String
            if (r0 != 0) goto L_0x0030
            java.lang.String r0 = "java.lang.String"
            java.lang.Class r0 = class$(r0)
            class$java$lang$String = r0
        L_0x000e:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x002e
            java.lang.Class r0 = java.lang.Character.TYPE
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x002e
            java.lang.Class r0 = class$java$lang$Character
            if (r0 != 0) goto L_0x0033
            java.lang.String r0 = "java.lang.Character"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Character = r0
        L_0x0028:
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0036
        L_0x002e:
            r0 = 1
        L_0x002f:
            return r0
        L_0x0030:
            java.lang.Class r0 = class$java$lang$String
            goto L_0x000e
        L_0x0033:
            java.lang.Class r0 = class$java$lang$Character
            goto L_0x0028
        L_0x0036:
            r0 = 0
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isString(java.lang.Class):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r0.isAssignableFrom(r2.getClass()) != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isString(java.lang.Object r2) {
        /*
            boolean r0 = r2 instanceof java.lang.String
            if (r0 != 0) goto L_0x0028
            boolean r0 = r2 instanceof java.lang.Character
            if (r0 != 0) goto L_0x0028
            if (r2 == 0) goto L_0x002d
            java.lang.Class r0 = r2.getClass()
            java.lang.Class r1 = java.lang.Character.TYPE
            if (r0 == r1) goto L_0x0028
            java.lang.Class r0 = class$java$lang$String
            if (r0 != 0) goto L_0x002a
            java.lang.String r0 = "java.lang.String"
            java.lang.Class r0 = class$(r0)
            class$java$lang$String = r0
        L_0x001e:
            java.lang.Class r1 = r2.getClass()
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x002d
        L_0x0028:
            r0 = 1
        L_0x0029:
            return r0
        L_0x002a:
            java.lang.Class r0 = class$java$lang$String
            goto L_0x001e
        L_0x002d:
            r0 = 0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.isString(java.lang.Object):boolean");
    }

    public static boolean mayBeJSON(String str) {
        return str != null && ("null".equals(str) || ((str.startsWith("[") && str.endsWith("]")) || (str.startsWith("{") && str.endsWith("}"))));
    }

    public static DynaBean newDynaBean(JSONObject jSONObject) {
        return newDynaBean(jSONObject, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public static org.apache.commons.beanutils.DynaBean newDynaBean(net.sf.json.JSONObject r5, net.sf.json.JsonConfig r6) {
        /*
            java.util.Map r1 = getProperties(r5)
            java.util.Set r0 = r1.entrySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x000c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0036
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r0 = r0.getKey()
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = isJavaIdentifier(r0)
            if (r3 != 0) goto L_0x000c
            java.lang.String r3 = convertToJavaIdentifier(r0, r6)
            int r4 = r3.compareTo(r0)
            if (r4 == 0) goto L_0x000c
            java.lang.Object r0 = r1.remove(r0)
            r1.put(r3, r0)
            goto L_0x000c
        L_0x0036:
            net.sf.ezmorph.bean.MorphDynaClass r2 = new net.sf.ezmorph.bean.MorphDynaClass
            r2.<init>(r1)
            org.apache.commons.beanutils.DynaBean r0 = r2.newInstance()     // Catch:{ Exception -> 0x0045 }
            net.sf.ezmorph.bean.MorphDynaBean r0 = (net.sf.ezmorph.bean.MorphDynaBean) r0     // Catch:{ Exception -> 0x0045 }
            r0.setDynaBeanClass(r2)     // Catch:{ Exception -> 0x0045 }
            return r0
        L_0x0045:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.newDynaBean(net.sf.json.JSONObject, net.sf.json.JsonConfig):org.apache.commons.beanutils.DynaBean");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 29
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
    public static java.lang.String numberToString(java.lang.Number r3) {
        /*
            r2 = 0
            if (r3 != 0) goto L_0x000b
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Null pointer"
            r0.<init>(r1)
            throw r0
        L_0x000b:
            testValidity(r3)
            java.lang.String r0 = r3.toString()
            r1 = 46
            int r1 = r0.indexOf(r1)
            if (r1 <= 0) goto L_0x004f
            r1 = 101(0x65, float:1.42E-43)
            int r1 = r0.indexOf(r1)
            if (r1 >= 0) goto L_0x004f
            r1 = 69
            int r1 = r0.indexOf(r1)
            if (r1 >= 0) goto L_0x004f
        L_0x002a:
            java.lang.String r1 = "0"
            boolean r1 = r0.endsWith(r1)
            if (r1 == 0) goto L_0x003d
            int r1 = r0.length()
            int r1 = r1 + -1
            java.lang.String r0 = r0.substring(r2, r1)
            goto L_0x002a
        L_0x003d:
            java.lang.String r1 = "."
            boolean r1 = r0.endsWith(r1)
            if (r1 == 0) goto L_0x004f
            int r1 = r0.length()
            int r1 = r1 + -1
            java.lang.String r0 = r0.substring(r2, r1)
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.numberToString(java.lang.Number):java.lang.String");
    }

    public static String quote(String str) {
        if (isFunction(str)) {
            return str;
        }
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length * 2);
        char[] charArray = str.toCharArray();
        char[] cArr = new char[1030];
        stringBuffer.append('\"');
        int i = 0;
        int i2 = 0;
        char c = 0;
        while (i2 < length) {
            if (i > 1024) {
                stringBuffer.append(cArr, 0, i);
                i = 0;
            }
            char c2 = charArray[i2];
            switch (c2) {
                case '\"':
                case '\\':
                    int i3 = i + 1;
                    cArr[i] = '\\';
                    i = i3 + 1;
                    cArr[i3] = c2;
                    break;
                case '/':
                    if (c == '<') {
                        int i4 = i + 1;
                        cArr[i] = '\\';
                        i = i4;
                    }
                    int i5 = i + 1;
                    cArr[i] = c2;
                    i = i5;
                    break;
                default:
                    if (c2 >= ' ') {
                        int i6 = i + 1;
                        cArr[i] = c2;
                        i = i6;
                        break;
                    } else {
                        switch (c2) {
                            case 8:
                                int i7 = i + 1;
                                cArr[i] = '\\';
                                i = i7 + 1;
                                cArr[i7] = 'b';
                                break;
                            case 9:
                                int i8 = i + 1;
                                cArr[i] = '\\';
                                i = i8 + 1;
                                cArr[i8] = 't';
                                break;
                            case 10:
                                int i9 = i + 1;
                                cArr[i] = '\\';
                                i = i9 + 1;
                                cArr[i9] = 'n';
                                break;
                            case 12:
                                int i10 = i + 1;
                                cArr[i] = '\\';
                                i = i10 + 1;
                                cArr[i10] = 'f';
                                break;
                            case 13:
                                int i11 = i + 1;
                                cArr[i] = '\\';
                                i = i11 + 1;
                                cArr[i11] = 'r';
                                break;
                            default:
                                String stringBuffer2 = new StringBuffer().append("000").append(Integer.toHexString(c2)).toString();
                                int length2 = stringBuffer2.length();
                                int i12 = i + 1;
                                cArr[i] = '\\';
                                int i13 = i12 + 1;
                                cArr[i12] = 'u';
                                int i14 = i13 + 1;
                                cArr[i13] = stringBuffer2.charAt(length2 - 4);
                                int i15 = i14 + 1;
                                cArr[i14] = stringBuffer2.charAt(length2 - 3);
                                int i16 = i15 + 1;
                                cArr[i15] = stringBuffer2.charAt(length2 - 2);
                                i = i16 + 1;
                                cArr[i16] = stringBuffer2.charAt(length2 - 1);
                                break;
                        }
                    }
            }
            i2++;
            c = c2;
        }
        stringBuffer.append(cArr, 0, i);
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    public static String stripQuotes(String str) {
        if (str.length() < 2) {
            return str;
        }
        if (str.startsWith(SINGLE_QUOTE) && str.endsWith(SINGLE_QUOTE)) {
            return str.substring(1, str.length() - 1);
        }
        if (!str.startsWith(DOUBLE_QUOTE) || !str.endsWith(DOUBLE_QUOTE)) {
            return str;
        }
        return str.substring(1, str.length() - 1);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v12, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v7, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 34
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
    public static void testValidity(java.lang.Object r2) {
        /*
            if (r2 == 0) goto L_0x0044
            boolean r0 = r2 instanceof java.lang.Double
            if (r0 == 0) goto L_0x001f
            r0 = r2
            java.lang.Double r0 = (java.lang.Double) r0
            boolean r0 = r0.isInfinite()
            if (r0 != 0) goto L_0x0017
            java.lang.Double r2 = (java.lang.Double) r2
            boolean r0 = r2.isNaN()
            if (r0 == 0) goto L_0x0044
        L_0x0017:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "JSON does not allow non-finite numbers"
            r0.<init>(r1)
            throw r0
        L_0x001f:
            boolean r0 = r2 instanceof java.lang.Float
            if (r0 == 0) goto L_0x003c
            r0 = r2
            java.lang.Float r0 = (java.lang.Float) r0
            boolean r0 = r0.isInfinite()
            if (r0 != 0) goto L_0x0034
            java.lang.Float r2 = (java.lang.Float) r2
            boolean r0 = r2.isNaN()
            if (r0 == 0) goto L_0x0044
        L_0x0034:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "JSON does not allow non-finite numbers."
            r0.<init>(r1)
            throw r0
        L_0x003c:
            boolean r0 = r2 instanceof java.math.BigDecimal
            if (r0 != 0) goto L_0x0044
            boolean r0 = r2 instanceof java.math.BigInteger
            if (r0 == 0) goto L_0x0044
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.testValidity(java.lang.Object):void");
    }

    public static Number transformNumber(Number number) {
        if (number instanceof Float) {
            return new Double(number.doubleValue());
        }
        if (number instanceof Short) {
            return new Integer(number.intValue());
        }
        if (number instanceof Byte) {
            return new Integer(number.intValue());
        }
        if (!(number instanceof Long)) {
            return number;
        }
        if (number.longValue() > new Long(2147483647L).longValue() || number.longValue() < -2147483648L) {
            return number;
        }
        return new Integer(number.intValue());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 44
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
    public static java.lang.String valueToString(java.lang.Object r4) {
        /*
            if (r4 == 0) goto L_0x0008
            boolean r0 = isNull(r4)
            if (r0 == 0) goto L_0x000b
        L_0x0008:
            java.lang.String r0 = "null"
        L_0x000a:
            return r0
        L_0x000b:
            boolean r0 = r4 instanceof net.sf.json.JSONFunction
            if (r0 == 0) goto L_0x0016
            net.sf.json.JSONFunction r4 = (net.sf.json.JSONFunction) r4
            java.lang.String r0 = r4.toString()
            goto L_0x000a
        L_0x0016:
            boolean r0 = r4 instanceof net.sf.json.JSONString
            if (r0 == 0) goto L_0x0047
            net.sf.json.JSONString r4 = (net.sf.json.JSONString) r4     // Catch:{ Exception -> 0x0027 }
            java.lang.String r0 = r4.toJSONString()     // Catch:{ Exception -> 0x0027 }
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L_0x002e
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x000a
        L_0x0027:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x002e:
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Bad value from toJSONString: "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0047:
            boolean r0 = r4 instanceof java.lang.Number
            if (r0 == 0) goto L_0x0052
            java.lang.Number r4 = (java.lang.Number) r4
            java.lang.String r0 = numberToString(r4)
            goto L_0x000a
        L_0x0052:
            boolean r0 = r4 instanceof java.lang.Boolean
            if (r0 != 0) goto L_0x005e
            boolean r0 = r4 instanceof net.sf.json.JSONObject
            if (r0 != 0) goto L_0x005e
            boolean r0 = r4 instanceof net.sf.json.JSONArray
            if (r0 == 0) goto L_0x0063
        L_0x005e:
            java.lang.String r0 = r4.toString()
            goto L_0x000a
        L_0x0063:
            java.lang.String r0 = r4.toString()
            java.lang.String r0 = quote(r0)
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.util.JSONUtils.valueToString(java.lang.Object):java.lang.String");
    }

    public static String valueToString(Object obj, int i, int i2) {
        if (obj == null || isNull(obj)) {
            return "null";
        }
        if (obj instanceof JSONFunction) {
            return ((JSONFunction) obj).toString();
        }
        if (obj instanceof JSONString) {
            return ((JSONString) obj).toJSONString();
        }
        if (obj instanceof Number) {
            return numberToString((Number) obj);
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toString(i, i2);
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toString(i, i2);
        }
        return quote(obj.toString());
    }

    private static boolean isBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return true;
        }
        try {
            new BigDecimal(String.valueOf(number));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isBigInteger(Number number) {
        if (number instanceof BigInteger) {
            return true;
        }
        try {
            new BigInteger(String.valueOf(number));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(Number number) {
        if (number instanceof Double) {
            return true;
        }
        try {
            if (Double.isInfinite(Double.parseDouble(String.valueOf(number)))) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(Number number) {
        if (number instanceof Float) {
            return true;
        }
        try {
            if (Float.isInfinite(Float.parseFloat(String.valueOf(number)))) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isInteger(Number number) {
        if (number instanceof Integer) {
            return true;
        }
        try {
            Integer.parseInt(String.valueOf(number));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isLong(Number number) {
        if (number instanceof Long) {
            return true;
        }
        try {
            Long.parseLong(String.valueOf(number));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private JSONUtils() {
    }
}
