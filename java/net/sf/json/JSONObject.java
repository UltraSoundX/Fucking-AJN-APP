package net.sf.json;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.json.regexp.RegexpUtils;
import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class JSONObject extends AbstractJSON implements Comparable, Map, JSON {
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Character;
    static Class class$java$lang$Class;
    static Class class$java$lang$Object;
    static Class class$java$lang$String;
    static Class class$java$util$Collection;
    static Class class$java$util$List;
    static Class class$java$util$Map;
    static Class class$java$util$Set;
    static Class class$net$sf$json$JSONArray;
    static Class class$net$sf$json$JSONFunction;
    static Class class$net$sf$json$JSONObject;
    private static final Log log;
    private boolean nullObject;
    private Map properties;

    static {
        Class cls;
        if (class$net$sf$json$JSONObject == null) {
            cls = class$("net.sf.json.JSONObject");
            class$net$sf$json$JSONObject = cls;
        } else {
            cls = class$net$sf$json$JSONObject;
        }
        log = LogFactory.getLog(cls);
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static JSONObject fromObject(Object obj) {
        return fromObject(obj, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v14, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v14, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 50
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
    public static net.sf.json.JSONObject fromObject(java.lang.Object r2, net.sf.json.JsonConfig r3) {
        /*
            if (r2 == 0) goto L_0x0008
            boolean r0 = net.sf.json.util.JSONUtils.isNull(r2)
            if (r0 == 0) goto L_0x000f
        L_0x0008:
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r1 = 1
            r0.<init>(r1)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = r2 instanceof net.sf.json.JSONObject
            if (r0 == 0) goto L_0x001a
            net.sf.json.JSONObject r2 = (net.sf.json.JSONObject) r2
            net.sf.json.JSONObject r0 = _fromJSONObject(r2, r3)
            goto L_0x000e
        L_0x001a:
            boolean r0 = r2 instanceof org.apache.commons.beanutils.DynaBean
            if (r0 == 0) goto L_0x0025
            org.apache.commons.beanutils.DynaBean r2 = (org.apache.commons.beanutils.DynaBean) r2
            net.sf.json.JSONObject r0 = _fromDynaBean(r2, r3)
            goto L_0x000e
        L_0x0025:
            boolean r0 = r2 instanceof net.sf.json.util.JSONTokener
            if (r0 == 0) goto L_0x0030
            net.sf.json.util.JSONTokener r2 = (net.sf.json.util.JSONTokener) r2
            net.sf.json.JSONObject r0 = _fromJSONTokener(r2, r3)
            goto L_0x000e
        L_0x0030:
            boolean r0 = r2 instanceof net.sf.json.JSONString
            if (r0 == 0) goto L_0x003b
            net.sf.json.JSONString r2 = (net.sf.json.JSONString) r2
            net.sf.json.JSONObject r0 = _fromJSONString(r2, r3)
            goto L_0x000e
        L_0x003b:
            boolean r0 = r2 instanceof java.util.Map
            if (r0 == 0) goto L_0x0046
            java.util.Map r2 = (java.util.Map) r2
            net.sf.json.JSONObject r0 = _fromMap(r2, r3)
            goto L_0x000e
        L_0x0046:
            boolean r0 = r2 instanceof java.lang.String
            if (r0 == 0) goto L_0x0051
            java.lang.String r2 = (java.lang.String) r2
            net.sf.json.JSONObject r0 = _fromString(r2, r3)
            goto L_0x000e
        L_0x0051:
            boolean r0 = net.sf.json.util.JSONUtils.isNumber(r2)
            if (r0 != 0) goto L_0x0063
            boolean r0 = net.sf.json.util.JSONUtils.isBoolean(r2)
            if (r0 != 0) goto L_0x0063
            boolean r0 = net.sf.json.util.JSONUtils.isString(r2)
            if (r0 == 0) goto L_0x0069
        L_0x0063:
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>()
            goto L_0x000e
        L_0x0069:
            boolean r0 = net.sf.json.util.JSONUtils.isArray(r2)
            if (r0 == 0) goto L_0x0077
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "'object' is an array. Use JSONArray instead"
            r0.<init>(r1)
            throw r0
        L_0x0077:
            net.sf.json.JSONObject r0 = _fromBean(r2, r3)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.fromObject(java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 101
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
    public static java.lang.Object toBean(net.sf.json.JSONObject r10) {
        /*
            r0 = 0
            if (r10 == 0) goto L_0x0009
            boolean r1 = r10.isNullObject()
            if (r1 == 0) goto L_0x000a
        L_0x0009:
            return r0
        L_0x000a:
            net.sf.json.JsonConfig r5 = new net.sf.json.JsonConfig
            r5.<init>()
            java.util.Map r6 = net.sf.json.util.JSONUtils.getProperties(r10)
            org.apache.commons.beanutils.DynaBean r3 = net.sf.json.util.JSONUtils.newDynaBean(r10, r5)
            net.sf.json.JSONArray r0 = r10.names(r5)
            java.util.Iterator r7 = r0.iterator()
        L_0x001f:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x011d
            java.lang.Object r0 = r7.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r8 = net.sf.json.util.JSONUtils.convertToJavaIdentifier(r0, r5)
            java.lang.Object r1 = r6.get(r0)
            java.lang.Class r1 = (java.lang.Class) r1
            java.lang.Object r2 = r10.get(r0)
            boolean r4 = net.sf.json.util.JSONUtils.isNull(r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x00dd
            boolean r4 = r2 instanceof net.sf.json.JSONArray     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 == 0) goto L_0x004f
            net.sf.json.JSONArray r2 = (net.sf.json.JSONArray) r2     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.util.Collection r2 = net.sf.json.JSONArray.toCollection(r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r3.set(r8, r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x001f
        L_0x004d:
            r0 = move-exception
            throw r0
        L_0x004f:
            java.lang.Class r4 = class$java$lang$String     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x00c6
            java.lang.String r4 = "java.lang.String"
            java.lang.Class r4 = class$(r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            class$java$lang$String = r4     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
        L_0x005b:
            boolean r4 = r4.isAssignableFrom(r1)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x009d
            java.lang.Class r4 = class$java$lang$Boolean     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x00c9
            java.lang.String r4 = "java.lang.Boolean"
            java.lang.Class r4 = class$(r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            class$java$lang$Boolean = r4     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
        L_0x006d:
            boolean r4 = r4.isAssignableFrom(r1)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x009d
            boolean r4 = net.sf.json.util.JSONUtils.isNumber(r1)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x009d
            java.lang.Class r4 = class$java$lang$Character     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x00cc
            java.lang.String r4 = "java.lang.Character"
            java.lang.Class r4 = class$(r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            class$java$lang$Character = r4     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
        L_0x0085:
            boolean r4 = r4.isAssignableFrom(r1)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x009d
            java.lang.Class r4 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x00cf
            java.lang.String r4 = "net.sf.json.JSONFunction"
            java.lang.Class r4 = class$(r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            class$net$sf$json$JSONFunction = r4     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
        L_0x0097:
            boolean r4 = r4.isAssignableFrom(r1)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r4 == 0) goto L_0x00d2
        L_0x009d:
            r3.set(r8, r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x001f
        L_0x00a2:
            r2 = move-exception
            net.sf.json.JSONException r3 = new net.sf.json.JSONException
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.String r5 = "Error while setting property="
            java.lang.StringBuffer r4 = r4.append(r5)
            java.lang.StringBuffer r0 = r4.append(r0)
            java.lang.String r4 = " type"
            java.lang.StringBuffer r0 = r0.append(r4)
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0, r2)
            throw r3
        L_0x00c6:
            java.lang.Class r4 = class$java$lang$String     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x005b
        L_0x00c9:
            java.lang.Class r4 = class$java$lang$Boolean     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x006d
        L_0x00cc:
            java.lang.Class r4 = class$java$lang$Character     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x0085
        L_0x00cf:
            java.lang.Class r4 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x0097
        L_0x00d2:
            net.sf.json.JSONObject r2 = (net.sf.json.JSONObject) r2     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.Object r2 = toBean(r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r3.set(r8, r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x001f
        L_0x00dd:
            boolean r2 = r1.isPrimitive()     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            if (r2 == 0) goto L_0x0117
            org.apache.commons.logging.Log r2 = log     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r4.<init>()     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.String r9 = "Tried to assign null value to "
            java.lang.StringBuffer r4 = r4.append(r9)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.StringBuffer r4 = r4.append(r8)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.String r9 = ":"
            java.lang.StringBuffer r4 = r4.append(r9)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.String r9 = r1.getName()     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.StringBuffer r4 = r4.append(r9)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r2.warn(r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            net.sf.ezmorph.MorpherRegistry r2 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r4 = 0
            java.lang.Object r2 = r2.morph(r1, r4)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            r3.set(r8, r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x001f
        L_0x0117:
            r2 = 0
            r3.set(r8, r2)     // Catch:{ JSONException -> 0x004d, Exception -> 0x00a2 }
            goto L_0x001f
        L_0x011d:
            r0 = r3
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.toBean(net.sf.json.JSONObject):java.lang.Object");
    }

    public static Object toBean(JSONObject jSONObject, Class cls) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        return toBean(jSONObject, jsonConfig);
    }

    public static Object toBean(JSONObject jSONObject, Class cls, Map map) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        jsonConfig.setClassMap(map);
        return toBean(jSONObject, jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v17, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v46, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 465
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
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object toBean(net.sf.json.JSONObject r14, net.sf.json.JsonConfig r15) {
        /*
            r7 = 0
            if (r14 == 0) goto L_0x0009
            boolean r0 = r14.isNullObject()
            if (r0 == 0) goto L_0x000a
        L_0x0009:
            return r7
        L_0x000a:
            java.lang.Class r8 = r15.getRootClass()
            java.util.Map r4 = r15.getClassMap()
            if (r8 != 0) goto L_0x0019
            java.lang.Object r7 = toBean(r14)
            goto L_0x0009
        L_0x0019:
            if (r4 != 0) goto L_0x001d
            java.util.Map r4 = java.util.Collections.EMPTY_MAP
        L_0x001d:
            boolean r0 = r8.isInterface()     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            if (r0 == 0) goto L_0x00c9
            java.lang.Class r0 = class$java$util$Map     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            if (r0 != 0) goto L_0x0050
            java.lang.String r0 = "java.util.Map"
            java.lang.Class r0 = class$(r0)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            class$java$util$Map = r0     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
        L_0x002f:
            boolean r0 = r0.isAssignableFrom(r8)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            if (r0 != 0) goto L_0x0053
            net.sf.json.JSONException r0 = new net.sf.json.JSONException     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            r1.<init>()     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            java.lang.String r2 = "beanClass is an interface. "
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            java.lang.StringBuffer r1 = r1.append(r8)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            throw r0     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
        L_0x004e:
            r0 = move-exception
            throw r0
        L_0x0050:
            java.lang.Class r0 = class$java$util$Map     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            goto L_0x002f
        L_0x0053:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            r0.<init>()     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            r7 = r0
        L_0x0059:
            java.util.Map r9 = net.sf.json.util.JSONUtils.getProperties(r14)
            net.sf.json.util.PropertyFilter r10 = r15.getJavaPropertyFilter()
            net.sf.json.JSONArray r0 = r14.names(r15)
            java.util.Iterator r11 = r0.iterator()
        L_0x0069:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0009
            java.lang.Object r3 = r11.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r0 = r9.get(r3)
            r6 = r0
            java.lang.Class r6 = (java.lang.Class) r6
            java.lang.Object r1 = r14.get(r3)
            if (r10 == 0) goto L_0x0088
            boolean r0 = r10.apply(r7, r3, r1)
            if (r0 != 0) goto L_0x0069
        L_0x0088:
            java.lang.Class r0 = class$java$util$Map
            if (r0 != 0) goto L_0x00da
            java.lang.String r0 = "java.util.Map"
            java.lang.Class r0 = class$(r0)
            class$java$util$Map = r0
        L_0x0094:
            boolean r0 = r0.isAssignableFrom(r8)
            if (r0 == 0) goto L_0x00dd
            boolean r0 = r15.isSkipJavaIdentifierTransformationInMapKeys()
            if (r0 == 0) goto L_0x00dd
            r0 = r3
        L_0x00a1:
            net.sf.json.processors.PropertyNameProcessor r2 = r15.findJavaPropertyNameProcessor(r8)
            if (r2 == 0) goto L_0x00ab
            java.lang.String r0 = r2.processPropertyName(r8, r0)
        L_0x00ab:
            java.lang.Class r2 = class$java$util$Map     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x00e2
            java.lang.String r2 = "java.util.Map"
            java.lang.Class r2 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$Map = r2     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x00b7:
            boolean r2 = r2.isAssignableFrom(r8)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x01a7
            boolean r2 = net.sf.json.util.JSONUtils.isNull(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x00e5
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x00c7:
            r0 = move-exception
            throw r0
        L_0x00c9:
            net.sf.json.util.NewBeanInstanceStrategy r0 = r15.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            java.lang.Object r0 = r0.newInstance(r8, r14)     // Catch:{ JSONException -> 0x004e, Exception -> 0x00d3 }
            r7 = r0
            goto L_0x0059
        L_0x00d3:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x00da:
            java.lang.Class r0 = class$java$util$Map
            goto L_0x0094
        L_0x00dd:
            java.lang.String r0 = net.sf.json.util.JSONUtils.convertToJavaIdentifier(r3, r15)
            goto L_0x00a1
        L_0x00e2:
            java.lang.Class r2 = class$java$util$Map     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x00b7
        L_0x00e5:
            boolean r2 = r1 instanceof net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x0126
            java.lang.Class r2 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0123
            java.lang.String r2 = "java.util.List"
            java.lang.Class r5 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$List = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x00f5:
            r2 = r15
            java.util.Collection r1 = convertPropertyValueToCollection(r0, r1, r2, r3, r4, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x00ff:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r4 = "Error while setting property="
            java.lang.StringBuffer r2 = r2.append(r4)
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.String r3 = " type "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r2 = r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0123:
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x00f5
        L_0x0126:
            java.lang.Class r2 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0170
            java.lang.String r2 = "java.lang.String"
            java.lang.Class r2 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$lang$String = r2     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0132:
            boolean r2 = r2.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x015c
            boolean r2 = net.sf.json.util.JSONUtils.isBoolean(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x015c
            boolean r2 = net.sf.json.util.JSONUtils.isNumber(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x015c
            boolean r2 = net.sf.json.util.JSONUtils.isString(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x015c
            java.lang.Class r2 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0173
            java.lang.String r2 = "net.sf.json.JSONFunction"
            java.lang.Class r2 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$net$sf$json$JSONFunction = r2     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0156:
            boolean r2 = r2.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x017b
        L_0x015c:
            boolean r2 = r15.isHandleJettisonEmptyElement()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x0176
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x0176
            r1 = 0
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0170:
            java.lang.Class r2 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0132
        L_0x0173:
            java.lang.Class r2 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0156
        L_0x0176:
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x017b:
            java.lang.Class r2 = findTargetClass(r0, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0185
            java.lang.Class r2 = findTargetClass(r3, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0185:
            net.sf.json.JsonConfig r5 = r15.copy()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setRootClass(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setClassMap(r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x019c
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Object r1 = toBean(r1, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x019c:
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Object r1 = toBean(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x01a7:
            java.beans.PropertyDescriptor r12 = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptor(r7, r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r12 == 0) goto L_0x01e1
            java.lang.reflect.Method r2 = r12.getWriteMethod()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x01e1
            org.apache.commons.logging.Log r1 = log     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = "Property '"
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r2.append(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = "' of "
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Class r2 = r7.getClass()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = " has no write method. SKIPPED."
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r1.warn(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x01e1:
            if (r12 == 0) goto L_0x0400
            java.lang.Class r2 = r12.getPropertyType()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            boolean r5 = net.sf.json.util.JSONUtils.isNull(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x03c0
            boolean r5 = r1 instanceof net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x0248
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x0215
            java.lang.String r5 = "java.util.List"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$List = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x01fd:
            java.lang.Class r13 = r12.getPropertyType()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            boolean r5 = r5.isAssignableFrom(r13)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x0218
            java.lang.Class r5 = r12.getPropertyType()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2 = r15
            java.util.Collection r1 = convertPropertyValueToCollection(r0, r1, r2, r3, r4, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0215:
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x01fd
        L_0x0218:
            java.lang.Class r5 = class$java$util$Set     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x023c
            java.lang.String r5 = "java.util.Set"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$Set = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0224:
            java.lang.Class r13 = r12.getPropertyType()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            boolean r5 = r5.isAssignableFrom(r13)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x023f
            java.lang.Class r5 = r12.getPropertyType()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2 = r15
            java.util.Collection r1 = convertPropertyValueToCollection(r0, r1, r2, r3, r4, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x023c:
            java.lang.Class r5 = class$java$util$Set     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0224
        L_0x023f:
            java.lang.Object r1 = convertPropertyValueToArray(r0, r1, r2, r15, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0248:
            java.lang.Class r5 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x0294
            java.lang.String r5 = "java.lang.String"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$lang$String = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0254:
            boolean r5 = r5.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x027e
            boolean r5 = net.sf.json.util.JSONUtils.isBoolean(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x027e
            boolean r5 = net.sf.json.util.JSONUtils.isNumber(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x027e
            boolean r5 = net.sf.json.util.JSONUtils.isString(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x027e
            java.lang.Class r5 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x0297
            java.lang.String r5 = "net.sf.json.JSONFunction"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$net$sf$json$JSONFunction = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0278:
            boolean r5 = r5.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x02f3
        L_0x027e:
            if (r12 == 0) goto L_0x02ae
            boolean r5 = r15.isHandleJettisonEmptyElement()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x029a
            java.lang.String r5 = ""
            boolean r5 = r5.equals(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x029a
            r1 = 0
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0294:
            java.lang.Class r5 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0254
        L_0x0297:
            java.lang.Class r5 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0278
        L_0x029a:
            boolean r5 = r2.isInstance(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x02a9
            java.lang.Object r1 = morphPropertyValue(r0, r1, r6, r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x02a9:
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x02ae:
            if (r8 == 0) goto L_0x02b4
            boolean r2 = r7 instanceof java.util.Map     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x02b9
        L_0x02b4:
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x02b9:
            org.apache.commons.logging.Log r1 = log     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = "Tried to assign property "
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r2.append(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = ":"
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r6.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = " to bean of class "
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Class r2 = r7.getClass()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r2.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r1.warn(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x02f3:
            boolean r5 = r15.isHandleJettisonSingleElementArray()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x0380
            net.sf.json.JSONArray r5 = new net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            net.sf.json.JSONArray r12 = r5.element(r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Class r5 = findTargetClass(r0, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x030c
            java.lang.Class r5 = findTargetClass(r3, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x030c:
            net.sf.json.JsonConfig r13 = r15.copy()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r13.setRootClass(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r13.setClassMap(r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            boolean r5 = r2.isArray()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x0325
            java.lang.Object r1 = net.sf.json.JSONArray.toArray(r12, r13)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0325:
            java.lang.Class r5 = class$net$sf$json$JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x033c
            java.lang.String r5 = "net.sf.json.JSONArray"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$net$sf$json$JSONArray = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0331:
            boolean r5 = r5.isAssignableFrom(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x033f
            setProperty(r7, r0, r12, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x033c:
            java.lang.Class r5 = class$net$sf$json$JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0331
        L_0x033f:
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x036f
            java.lang.String r5 = "java.util.List"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$List = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x034b:
            boolean r5 = r5.isAssignableFrom(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x0363
            java.lang.Class r5 = class$java$util$Set     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x0372
            java.lang.String r5 = "java.util.Set"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$Set = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x035d:
            boolean r5 = r5.isAssignableFrom(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x0375
        L_0x0363:
            r13.setCollectionType(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.util.Collection r1 = net.sf.json.JSONArray.toCollection(r12, r13)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x036f:
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x034b
        L_0x0372:
            java.lang.Class r5 = class$java$util$Set     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x035d
        L_0x0375:
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Object r1 = toBean(r1, r13)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0380:
            java.lang.Class r5 = class$java$lang$Object     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x03bb
            java.lang.String r5 = "java.lang.Object"
            java.lang.Class r5 = class$(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$lang$Object = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x038c:
            if (r2 == r5) goto L_0x0394
            boolean r5 = r2.isInterface()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 == 0) goto L_0x03a6
        L_0x0394:
            java.lang.Class r5 = findTargetClass(r0, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r5 != 0) goto L_0x039e
            java.lang.Class r5 = findTargetClass(r3, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x039e:
            if (r5 != 0) goto L_0x03be
            boolean r12 = r2.isInterface()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r12 == 0) goto L_0x03be
        L_0x03a6:
            net.sf.json.JsonConfig r5 = r15.copy()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setRootClass(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setClassMap(r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Object r1 = toBean(r1, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x03bb:
            java.lang.Class r5 = class$java$lang$Object     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x038c
        L_0x03be:
            r2 = r5
            goto L_0x03a6
        L_0x03c0:
            boolean r1 = r6.isPrimitive()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r1 == 0) goto L_0x03fa
            org.apache.commons.logging.Log r1 = log     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = "Tried to assign null value to "
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = r2.append(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = ":"
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = r6.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r1.warn(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            net.sf.ezmorph.MorpherRegistry r1 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2 = 0
            java.lang.Object r1 = r1.morph(r6, r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x03fa:
            r1 = 0
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0400:
            boolean r2 = net.sf.json.util.JSONUtils.isNull(r1)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x04d4
            boolean r2 = r1 instanceof net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x0423
            java.lang.Class r2 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0420
            java.lang.String r2 = "java.util.List"
            java.lang.Class r5 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$util$List = r5     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0416:
            r2 = r15
            java.util.Collection r1 = convertPropertyValueToCollection(r0, r1, r2, r3, r4, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x0420:
            java.lang.Class r5 = class$java$util$List     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0416
        L_0x0423:
            java.lang.Class r2 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x046a
            java.lang.String r2 = "java.lang.String"
            java.lang.Class r2 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$java$lang$String = r2     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x042f:
            boolean r2 = r2.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0459
            boolean r2 = net.sf.json.util.JSONUtils.isBoolean(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0459
            boolean r2 = net.sf.json.util.JSONUtils.isNumber(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0459
            boolean r2 = net.sf.json.util.JSONUtils.isString(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0459
            java.lang.Class r2 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x046d
            java.lang.String r2 = "net.sf.json.JSONFunction"
            java.lang.Class r2 = class$(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            class$net$sf$json$JSONFunction = r2     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x0453:
            boolean r2 = r2.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x04aa
        L_0x0459:
            if (r8 == 0) goto L_0x0465
            boolean r2 = r7 instanceof java.util.Map     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x0465
            net.sf.json.util.PropertySetStrategy r2 = r15.getPropertySetStrategy()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x0470
        L_0x0465:
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x046a:
            java.lang.Class r2 = class$java$lang$String     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x042f
        L_0x046d:
            java.lang.Class r2 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0453
        L_0x0470:
            org.apache.commons.logging.Log r1 = log     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = "Tried to assign property "
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r2.append(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = ":"
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r6.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = " to bean of class "
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Class r2 = r7.getClass()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r2.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r0 = r0.append(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r1.warn(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x04aa:
            boolean r2 = r15.isHandleJettisonSingleElementArray()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 == 0) goto L_0x04cf
            java.lang.Class r2 = findTargetClass(r0, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r2 != 0) goto L_0x04ba
            java.lang.Class r2 = findTargetClass(r3, r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
        L_0x04ba:
            net.sf.json.JsonConfig r5 = r15.copy()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setRootClass(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r5.setClassMap(r4)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.Object r1 = toBean(r1, r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x04cf:
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x04d4:
            boolean r1 = r6.isPrimitive()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            if (r1 == 0) goto L_0x050e
            org.apache.commons.logging.Log r1 = log     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2.<init>()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = "Tried to assign null value to "
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = r2.append(r0)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = ":"
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r5 = r6.getName()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.StringBuffer r2 = r2.append(r5)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r1.warn(r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            net.sf.ezmorph.MorpherRegistry r1 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            r2 = 0
            java.lang.Object r1 = r1.morph(r6, r2)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        L_0x050e:
            r1 = 0
            setProperty(r7, r0, r1, r15)     // Catch:{ JSONException -> 0x00c7, Exception -> 0x00ff }
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.toBean(net.sf.json.JSONObject, net.sf.json.JsonConfig):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r2v11, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r2v13, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 408
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
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object toBean(net.sf.json.JSONObject r16, java.lang.Object r17, net.sf.json.JsonConfig r18) {
        /*
            if (r16 == 0) goto L_0x000a
            boolean r2 = r16.isNullObject()
            if (r2 != 0) goto L_0x000a
            if (r17 != 0) goto L_0x000b
        L_0x000a:
            return r17
        L_0x000b:
            java.lang.Class r9 = r17.getClass()
            boolean r2 = r9.isInterface()
            if (r2 == 0) goto L_0x002e
            net.sf.json.JSONException r2 = new net.sf.json.JSONException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = "Root bean is an interface. "
            java.lang.StringBuffer r3 = r3.append(r4)
            java.lang.StringBuffer r3 = r3.append(r9)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x002e:
            java.util.Map r2 = r18.getClassMap()
            if (r2 != 0) goto L_0x04af
            java.util.Map r2 = java.util.Collections.EMPTY_MAP
            r5 = r2
        L_0x0037:
            java.util.Map r10 = net.sf.json.util.JSONUtils.getProperties(r16)
            net.sf.json.util.PropertyFilter r11 = r18.getJavaPropertyFilter()
            r0 = r16
            r1 = r18
            net.sf.json.JSONArray r2 = r0.names(r1)
            java.util.Iterator r12 = r2.iterator()
        L_0x004b:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x000a
            java.lang.Object r2 = r12.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r10.get(r2)
            java.lang.Class r3 = (java.lang.Class) r3
            r0 = r16
            java.lang.Object r4 = r0.get(r2)
            if (r11 == 0) goto L_0x006d
            r0 = r17
            boolean r6 = r11.apply(r0, r2, r4)
            if (r6 != 0) goto L_0x004b
        L_0x006d:
            r0 = r18
            java.lang.String r13 = net.sf.json.util.JSONUtils.convertToJavaIdentifier(r2, r0)
            r0 = r17
            java.beans.PropertyDescriptor r14 = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptor(r0, r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r14 == 0) goto L_0x00b0
            java.lang.reflect.Method r6 = r14.getWriteMethod()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x00b0
            org.apache.commons.logging.Log r4 = log     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = "Property '"
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = "' of "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r17.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = " has no write method. SKIPPED."
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r4.warn(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x00ae:
            r2 = move-exception
            throw r2
        L_0x00b0:
            boolean r6 = net.sf.json.util.JSONUtils.isNull(r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0467
            boolean r6 = r4 instanceof net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0211
            if (r14 == 0) goto L_0x00d2
            java.lang.Class r6 = class$java$util$List     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x011a
            java.lang.String r6 = "java.util.List"
            java.lang.Class r6 = class$(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$util$List = r6     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x00c8:
            java.lang.Class r7 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r6.isAssignableFrom(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x011d
        L_0x00d2:
            java.lang.Class r6 = findTargetClass(r13, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x00dc
            java.lang.Class r6 = findTargetClass(r2, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x00dc:
            net.sf.json.util.NewBeanInstanceStrategy r7 = r18.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r6 = r7.newInstance(r6, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.json.JSONArray r4 = (net.sf.json.JSONArray) r4     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            java.util.List r4 = net.sf.json.JSONArray.toList(r4, r6, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x00f6:
            r4 = move-exception
            net.sf.json.JSONException r5 = new net.sf.json.JSONException
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            java.lang.String r7 = "Error while setting property="
            java.lang.StringBuffer r6 = r6.append(r7)
            java.lang.StringBuffer r2 = r6.append(r2)
            java.lang.String r6 = " type "
            java.lang.StringBuffer r2 = r2.append(r6)
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r5.<init>(r2, r4)
            throw r5
        L_0x011a:
            java.lang.Class r6 = class$java$util$List     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x00c8
        L_0x011d:
            java.lang.Class r6 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = net.sf.json.util.JSONUtils.getInnerComponentType(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r6 = findTargetClass(r13, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r8 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r8 != 0) goto L_0x019f
            java.lang.String r8 = "java.lang.Object"
            java.lang.Class r8 = class$(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$Object = r8     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0135:
            boolean r8 = r7.equals(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r8 == 0) goto L_0x0150
            if (r6 == 0) goto L_0x0150
            java.lang.Class r8 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r8 != 0) goto L_0x01a2
            java.lang.String r8 = "java.lang.Object"
            java.lang.Class r8 = class$(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$Object = r8     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0149:
            boolean r8 = r6.equals(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r8 != 0) goto L_0x0150
            r7 = r6
        L_0x0150:
            net.sf.json.util.NewBeanInstanceStrategy r6 = r18.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r6 = r6.newInstance(r7, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.json.JSONArray r4 = (net.sf.json.JSONArray) r4     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            java.lang.Object r4 = net.sf.json.JSONArray.toArray(r4, r6, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r7.isPrimitive()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0185
            boolean r6 = net.sf.json.util.JSONUtils.isNumber(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0185
            java.lang.Class r6 = class$java$lang$Boolean     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x01a5
            java.lang.String r6 = "java.lang.Boolean"
            java.lang.Class r6 = class$(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$Boolean = r6     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0179:
            boolean r6 = r6.isAssignableFrom(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0185
            boolean r6 = net.sf.json.util.JSONUtils.isString(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x01a8
        L_0x0185:
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r7, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r7.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Object r4 = r6.morph(r7, r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0196:
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x019f:
            java.lang.Class r8 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x0135
        L_0x01a2:
            java.lang.Class r8 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x0149
        L_0x01a5:
            java.lang.Class r6 = class$java$lang$Boolean     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x0179
        L_0x01a8:
            java.lang.Class r6 = r4.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r8 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r6.equals(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0196
            java.lang.Class r8 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r6 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x020e
            java.lang.String r6 = "java.lang.Object"
            java.lang.Class r6 = class$(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$Object = r6     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x01c6:
            boolean r6 = r8.equals(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0196
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r7, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r8 = r8.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.Morpher r6 = r6.getMorpherFor(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.object.IdentityObjectMorpher r8 = net.sf.ezmorph.object.IdentityObjectMorpher.getInstance()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r8.equals(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x01fc
            net.sf.ezmorph.array.ObjectArrayMorpher r6 = new net.sf.ezmorph.array.ObjectArrayMorpher     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.bean.BeanMorpher r8 = new net.sf.ezmorph.bean.BeanMorpher     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.MorpherRegistry r14 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8.<init>(r7, r14)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.<init>(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.MorpherRegistry r8 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8.registerMorpher(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x01fc:
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r7, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r7.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Object r4 = r6.morph(r7, r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x0196
        L_0x020e:
            java.lang.Class r6 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x01c6
        L_0x0211:
            java.lang.Class r6 = class$java$lang$String     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0261
            java.lang.String r6 = "java.lang.String"
            java.lang.Class r6 = class$(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$String = r6     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x021d:
            boolean r6 = r6.isAssignableFrom(r3)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0247
            boolean r6 = net.sf.json.util.JSONUtils.isBoolean(r3)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0247
            boolean r6 = net.sf.json.util.JSONUtils.isNumber(r3)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0247
            boolean r6 = net.sf.json.util.JSONUtils.isString(r3)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0247
            java.lang.Class r6 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0264
            java.lang.String r6 = "net.sf.json.JSONFunction"
            java.lang.Class r6 = class$(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$net$sf$json$JSONFunction = r6     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0241:
            boolean r6 = r6.isAssignableFrom(r3)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0340
        L_0x0247:
            if (r14 == 0) goto L_0x02f7
            boolean r6 = r18.isHandleJettisonEmptyElement()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0267
            java.lang.String r6 = ""
            boolean r6 = r6.equals(r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0267
            r4 = 0
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0261:
            java.lang.Class r6 = class$java$lang$String     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x021d
        L_0x0264:
            java.lang.Class r6 = class$net$sf$json$JSONFunction     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x0241
        L_0x0267:
            java.lang.Class r6 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r6.isInstance(r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x02ee
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.Morpher r6 = r6.getMorpherFor(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.object.IdentityObjectMorpher r7 = net.sf.ezmorph.object.IdentityObjectMorpher.getInstance()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r6 = r7.equals(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x02d9
            org.apache.commons.logging.Log r6 = log     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r7.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = "Can't transform property '"
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r7 = r7.append(r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = "' from "
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = r3.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = " into "
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r8 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = r8.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r8 = ". Will register a default BeanMorpher"
            java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r7.toString()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.warn(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.bean.BeanMorpher r7 = new net.sf.ezmorph.bean.BeanMorpher     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r8 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.MorpherRegistry r15 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r7.<init>(r8, r15)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.registerMorpher(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x02d9:
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Object r4 = r6.morph(r7, r4)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x02ee:
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x02f7:
            r0 = r17
            boolean r6 = r0 instanceof java.util.Map     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0306
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0306:
            org.apache.commons.logging.Log r4 = log     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = "Tried to assign property "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = ":"
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r3.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = " to bean of class "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = r17.getClass()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r7.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r4.warn(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0340:
            if (r14 == 0) goto L_0x0407
            java.lang.Class r6 = r14.getPropertyType()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r7 = r18.isHandleJettisonSingleElementArray()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 == 0) goto L_0x03d2
            net.sf.json.JSONArray r7 = new net.sf.json.JSONArray     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r7.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            net.sf.json.JSONArray r8 = r7.element(r4, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.Class r7 = findTargetClass(r13, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 != 0) goto L_0x0361
            java.lang.Class r7 = findTargetClass(r2, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0361:
            net.sf.json.util.NewBeanInstanceStrategy r14 = r18.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r15 = 0
            java.lang.Object r14 = r14.newInstance(r7, r15)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            boolean r7 = r6.isArray()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 == 0) goto L_0x037f
            r0 = r18
            java.lang.Object r4 = net.sf.json.JSONArray.toArray(r8, r14, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x037f:
            java.lang.Class r7 = class$java$util$Collection     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 != 0) goto L_0x03a0
            java.lang.String r7 = "java.util.Collection"
            java.lang.Class r7 = class$(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$util$Collection = r7     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x038b:
            boolean r7 = r7.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 == 0) goto L_0x03a3
            r0 = r18
            java.util.List r4 = net.sf.json.JSONArray.toList(r8, r14, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x03a0:
            java.lang.Class r7 = class$java$util$Collection     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x038b
        L_0x03a3:
            java.lang.Class r7 = class$net$sf$json$JSONArray     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 != 0) goto L_0x03be
            java.lang.String r7 = "net.sf.json.JSONArray"
            java.lang.Class r7 = class$(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$net$sf$json$JSONArray = r7     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x03af:
            boolean r6 = r7.isAssignableFrom(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x03c1
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r8, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x03be:
            java.lang.Class r7 = class$net$sf$json$JSONArray     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x03af
        L_0x03c1:
            net.sf.json.JSONObject r4 = (net.sf.json.JSONObject) r4     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            java.lang.Object r4 = toBean(r4, r14, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x03d2:
            java.lang.Class r7 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r7 != 0) goto L_0x0404
            java.lang.String r7 = "java.lang.Object"
            java.lang.Class r7 = class$(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            class$java$lang$Object = r7     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x03de:
            if (r6 != r7) goto L_0x03ea
            java.lang.Class r6 = findTargetClass(r13, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x03ea
            java.lang.Class r6 = findTargetClass(r2, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x03ea:
            net.sf.json.util.NewBeanInstanceStrategy r7 = r18.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r6 = r7.newInstance(r6, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.json.JSONObject r4 = (net.sf.json.JSONObject) r4     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            java.lang.Object r4 = toBean(r4, r6, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0404:
            java.lang.Class r7 = class$java$lang$Object     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x03de
        L_0x0407:
            r0 = r17
            boolean r6 = r0 instanceof java.util.Map     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 == 0) goto L_0x0431
            java.lang.Class r6 = findTargetClass(r13, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r6 != 0) goto L_0x0417
            java.lang.Class r6 = findTargetClass(r2, r5)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
        L_0x0417:
            net.sf.json.util.NewBeanInstanceStrategy r7 = r18.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r8 = 0
            java.lang.Object r6 = r7.newInstance(r6, r8)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.json.JSONObject r4 = (net.sf.json.JSONObject) r4     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r18
            java.lang.Object r4 = toBean(r4, r6, r0)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0431:
            org.apache.commons.logging.Log r4 = log     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = "Tried to assign property "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = ":"
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r3.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = " to bean of class "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r9.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r4.warn(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x0467:
            boolean r4 = r3.isPrimitive()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            if (r4 == 0) goto L_0x04a5
            org.apache.commons.logging.Log r4 = log     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6.<init>()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = "Tried to assign null value to "
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r13)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = ":"
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r7 = r3.getName()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.StringBuffer r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r4.warn(r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            net.sf.ezmorph.MorpherRegistry r4 = net.sf.json.util.JSONUtils.getMorpherRegistry()     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r6 = 0
            java.lang.Object r4 = r4.morph(r3, r6)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x04a5:
            r4 = 0
            r0 = r17
            r1 = r18
            setProperty(r0, r13, r4, r1)     // Catch:{ JSONException -> 0x00ae, Exception -> 0x00f6 }
            goto L_0x004b
        L_0x04af:
            r5 = r2
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.toBean(net.sf.json.JSONObject, java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v17, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v20, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v17, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 109
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
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONObject _fromBean(java.lang.Object r11, net.sf.json.JsonConfig r12) {
        /*
            net.sf.json.AbstractJSON.fireObjectStartEvent(r12)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r11)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r12.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONObject r0 = r0.handleRepeatedReferenceAsObject(r11)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r12)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r12)
            throw r1
        L_0x0027:
            java.lang.Class r0 = r11.getClass()
            net.sf.json.processors.JsonBeanProcessor r0 = r12.findJsonBeanProcessor(r0)
            if (r0 == 0) goto L_0x006d
            net.sf.json.JSONObject r0 = r0.processBean(r11, r12)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            if (r0 != 0) goto L_0x0051
            java.lang.Class r0 = r11.getClass()     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            net.sf.json.processors.DefaultValueProcessor r0 = r12.findDefaultValueProcessor(r0)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            java.lang.Class r1 = r11.getClass()     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            java.lang.Object r0 = r0.getDefaultValue(r1)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            if (r0 != 0) goto L_0x0051
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            r1 = 1
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
        L_0x0051:
            net.sf.json.AbstractJSON.removeInstance(r11)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            net.sf.json.AbstractJSON.fireObjectEndEvent(r12)     // Catch:{ JSONException -> 0x0058, RuntimeException -> 0x0060 }
            goto L_0x0011
        L_0x0058:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r12)
            throw r0
        L_0x0060:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r12)
            throw r1
        L_0x006d:
            java.lang.Class r4 = r11.getClass()
            net.sf.json.processors.PropertyNameProcessor r5 = r12.findJsonPropertyNameProcessor(r4)
            java.util.Collection r6 = r12.getMergedExcludes(r4)
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>()
            java.beans.PropertyDescriptor[] r7 = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors(r11)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            net.sf.json.util.PropertyFilter r8 = r12.getJsonPropertyFilter()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r1 = 0
            r3 = r1
        L_0x0088:
            int r1 = r7.length     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r3 >= r1) goto L_0x013a
            r1 = r7[r3]     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r1 = r1.getName()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            boolean r2 = r6.contains(r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r2 == 0) goto L_0x009b
        L_0x0097:
            int r1 = r3 + 1
            r3 = r1
            goto L_0x0088
        L_0x009b:
            boolean r2 = r12.isIgnoreTransientFields()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r2 == 0) goto L_0x00a7
            boolean r2 = isTransientField(r1, r4)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r2 != 0) goto L_0x0097
        L_0x00a7:
            r2 = r7[r3]     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.Class r9 = r2.getPropertyType()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r2 = r7[r3]     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.reflect.Method r2 = r2.getReadMethod()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r2 == 0) goto L_0x0109
            java.lang.Object r2 = org.apache.commons.beanutils.PropertyUtils.getProperty(r11, r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r8 == 0) goto L_0x00c1
            boolean r10 = r8.apply(r11, r1, r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r10 != 0) goto L_0x0097
        L_0x00c1:
            net.sf.json.processors.JsonValueProcessor r10 = r12.findJsonValueProcessor(r4, r9, r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r10 == 0) goto L_0x00f2
            java.lang.Object r2 = r10.processObjectValue(r1, r2, r12)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            boolean r10 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            if (r10 != 0) goto L_0x00f2
            net.sf.json.JSONException r0 = new net.sf.json.JSONException     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r1.<init>()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r3 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r1 = r1.append(r3)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            throw r0     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
        L_0x00ea:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r12)
            throw r0
        L_0x00f2:
            if (r5 == 0) goto L_0x00f8
            java.lang.String r1 = r5.processPropertyName(r4, r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
        L_0x00f8:
            setValue(r0, r1, r2, r9, r12)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            goto L_0x0097
        L_0x00fc:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r12)
            throw r1
        L_0x0109:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r2.<init>()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r9 = "Property '"
            java.lang.StringBuffer r2 = r2.append(r9)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.StringBuffer r1 = r2.append(r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r2 = "' of "
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.Class r2 = r11.getClass()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r2 = " has no read method. SKIPPED"
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            net.sf.json.AbstractJSON.fireWarnEvent(r1, r12)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            org.apache.commons.logging.Log r2 = log     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            r2.warn(r1)     // Catch:{ JSONException -> 0x00ea, Exception -> 0x00fc }
            goto L_0x0097
        L_0x013a:
            net.sf.json.AbstractJSON.removeInstance(r11)
            net.sf.json.AbstractJSON.fireObjectEndEvent(r12)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._fromBean(java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v12, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 65
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
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONObject _fromDynaBean(org.apache.commons.beanutils.DynaBean r9, net.sf.json.JsonConfig r10) {
        /*
            net.sf.json.AbstractJSON.fireObjectStartEvent(r10)
            if (r9 != 0) goto L_0x000f
            net.sf.json.AbstractJSON.fireObjectEndEvent(r10)
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r1 = 1
            r0.<init>(r1)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r9)
            if (r0 != 0) goto L_0x0033
            net.sf.json.util.CycleDetectionStrategy r0 = r10.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x001e, RuntimeException -> 0x0026 }
            net.sf.json.JSONObject r0 = r0.handleRepeatedReferenceAsObject(r9)     // Catch:{ JSONException -> 0x001e, RuntimeException -> 0x0026 }
            goto L_0x000e
        L_0x001e:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r9)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r10)
            throw r0
        L_0x0026:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r9)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r10)
            throw r1
        L_0x0033:
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>()
            org.apache.commons.beanutils.DynaClass r1 = r9.getDynaClass()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            org.apache.commons.beanutils.DynaProperty[] r3 = r1.getDynaProperties()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.util.Collection r4 = r10.getMergedExcludes()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            net.sf.json.util.PropertyFilter r5 = r10.getJsonPropertyFilter()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            r1 = 0
            r2 = r1
        L_0x004a:
            int r1 = r3.length     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r2 >= r1) goto L_0x00b3
            r1 = r3[r2]     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.String r6 = r1.getName()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            boolean r7 = r4.contains(r6)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r7 == 0) goto L_0x005d
        L_0x0059:
            int r1 = r2 + 1
            r2 = r1
            goto L_0x004a
        L_0x005d:
            java.lang.Class r7 = r1.getType()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.String r1 = r1.getName()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.Object r1 = r9.get(r1)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r5 == 0) goto L_0x0071
            boolean r8 = r5.apply(r9, r6, r1)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r8 != 0) goto L_0x0059
        L_0x0071:
            net.sf.json.processors.JsonValueProcessor r8 = r10.findJsonValueProcessor(r7, r6)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r8 == 0) goto L_0x00a2
            java.lang.Object r1 = r8.processObjectValue(r6, r1, r10)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            boolean r8 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r1)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            if (r8 != 0) goto L_0x00a2
            net.sf.json.JSONException r0 = new net.sf.json.JSONException     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            r2.<init>()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.String r3 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r2 = r2.append(r3)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.StringBuffer r1 = r2.append(r1)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            throw r0     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
        L_0x009a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r9)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r10)
            throw r0
        L_0x00a2:
            setValue(r0, r6, r1, r7, r10)     // Catch:{ JSONException -> 0x009a, RuntimeException -> 0x00a6 }
            goto L_0x0059
        L_0x00a6:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r9)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r10)
            throw r1
        L_0x00b3:
            net.sf.json.AbstractJSON.removeInstance(r9)
            net.sf.json.AbstractJSON.fireObjectEndEvent(r10)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._fromDynaBean(org.apache.commons.beanutils.DynaBean, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 61
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
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONObject _fromJSONObject(net.sf.json.JSONObject r8, net.sf.json.JsonConfig r9) {
        /*
            r7 = 1
            net.sf.json.AbstractJSON.fireObjectStartEvent(r9)
            if (r8 == 0) goto L_0x000c
            boolean r0 = r8.isNullObject()
            if (r0 == 0) goto L_0x0015
        L_0x000c:
            net.sf.json.AbstractJSON.fireObjectEndEvent(r9)
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>(r7)
        L_0x0014:
            return r0
        L_0x0015:
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r8)
            if (r0 != 0) goto L_0x0039
            net.sf.json.util.CycleDetectionStrategy r0 = r9.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0024, RuntimeException -> 0x002c }
            net.sf.json.JSONObject r0 = r0.handleRepeatedReferenceAsObject(r8)     // Catch:{ JSONException -> 0x0024, RuntimeException -> 0x002c }
            goto L_0x0014
        L_0x0024:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r8)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r9)
            throw r0
        L_0x002c:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r8)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r9)
            throw r1
        L_0x0039:
            net.sf.json.JSONArray r1 = r8.names(r9)
            java.util.Collection r2 = r9.getMergedExcludes()
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>()
            net.sf.json.util.PropertyFilter r3 = r9.getJsonPropertyFilter()
            java.util.Iterator r1 = r1.iterator()
        L_0x004e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00ab
            java.lang.Object r4 = r1.next()
            if (r4 != 0) goto L_0x0062
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "JSON keys cannot be null."
            r0.<init>(r1)
            throw r0
        L_0x0062:
            boolean r5 = r4 instanceof java.lang.String
            if (r5 != 0) goto L_0x006e
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            java.lang.String r1 = "JSON keys must be strings."
            r0.<init>(r1)
            throw r0
        L_0x006e:
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = "null"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0082
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "JSON keys must not be null nor the 'null' string."
            r0.<init>(r1)
            throw r0
        L_0x0082:
            boolean r5 = r2.contains(r4)
            if (r5 != 0) goto L_0x004e
            java.lang.Object r5 = r8.opt(r4)
            if (r3 == 0) goto L_0x0094
            boolean r6 = r3.apply(r8, r4, r5)
            if (r6 != 0) goto L_0x004e
        L_0x0094:
            java.util.Map r6 = r0.properties
            boolean r6 = r6.containsKey(r4)
            if (r6 == 0) goto L_0x00a3
            r0.accumulate(r4, r5, r9)
            net.sf.json.AbstractJSON.firePropertySetEvent(r4, r5, r7, r9)
            goto L_0x004e
        L_0x00a3:
            r0._setInternal(r4, r5, r9)
            r6 = 0
            net.sf.json.AbstractJSON.firePropertySetEvent(r4, r5, r6, r9)
            goto L_0x004e
        L_0x00ab:
            net.sf.json.AbstractJSON.removeInstance(r8)
            net.sf.json.AbstractJSON.fireObjectEndEvent(r9)
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._fromJSONObject(net.sf.json.JSONObject, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    private static JSONObject _fromJSONString(JSONString jSONString, JsonConfig jsonConfig) {
        return _fromJSONTokener(new JSONTokener(jSONString.toJSONString()), jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v17, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v35, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v50, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v66, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 159
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
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONObject _fromJSONTokener(net.sf.json.util.JSONTokener r14, net.sf.json.JsonConfig r15) {
        /*
            r13 = 123(0x7b, float:1.72E-43)
            r12 = 125(0x7d, float:1.75E-43)
            r4 = 0
            r5 = 1
            net.sf.json.AbstractJSON.fireObjectStartEvent(r15)
            java.lang.String r1 = "null.*"
            boolean r1 = r14.matches(r1)     // Catch:{ JSONException -> 0x0028 }
            if (r1 == 0) goto L_0x001b
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            net.sf.json.JSONObject r1 = new net.sf.json.JSONObject     // Catch:{ JSONException -> 0x0028 }
            r2 = 1
            r1.<init>(r2)     // Catch:{ JSONException -> 0x0028 }
        L_0x001a:
            return r1
        L_0x001b:
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            if (r1 == r13) goto L_0x002d
            java.lang.String r1 = "A JSONObject text must begin with '{'"
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r15)
            throw r1
        L_0x002d:
            java.util.Collection r6 = r15.getMergedExcludes()     // Catch:{ JSONException -> 0x0028 }
            net.sf.json.util.PropertyFilter r7 = r15.getJsonPropertyFilter()     // Catch:{ JSONException -> 0x0028 }
            net.sf.json.JSONObject r3 = new net.sf.json.JSONObject     // Catch:{ JSONException -> 0x0028 }
            r3.<init>()     // Catch:{ JSONException -> 0x0028 }
        L_0x003a:
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            switch(r1) {
                case 0: goto L_0x008c;
                case 125: goto L_0x0093;
                default: goto L_0x0041;
            }     // Catch:{ JSONException -> 0x0028 }
        L_0x0041:
            r14.back()     // Catch:{ JSONException -> 0x0028 }
            java.lang.Object r1 = r14.nextValue(r15)     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r8 = r1.toString()     // Catch:{ JSONException -> 0x0028 }
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            r2 = 61
            if (r1 != r2) goto L_0x0098
            char r1 = r14.next()     // Catch:{ JSONException -> 0x0028 }
            r2 = 62
            if (r1 == r2) goto L_0x005f
            r14.back()     // Catch:{ JSONException -> 0x0028 }
        L_0x005f:
            char r1 = r14.peek()     // Catch:{ JSONException -> 0x0028 }
            r2 = 34
            if (r1 == r2) goto L_0x006b
            r2 = 39
            if (r1 != r2) goto L_0x00a3
        L_0x006b:
            r1 = r5
        L_0x006c:
            java.lang.Object r2 = r14.nextValue(r15)     // Catch:{ JSONException -> 0x0028 }
            if (r1 != 0) goto L_0x0078
            boolean r9 = net.sf.json.util.JSONUtils.isFunctionHeader(r2)     // Catch:{ JSONException -> 0x0028 }
            if (r9 != 0) goto L_0x0117
        L_0x0078:
            boolean r9 = r6.contains(r8)     // Catch:{ JSONException -> 0x0028 }
            if (r9 == 0) goto L_0x00bb
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            switch(r1) {
                case 44: goto L_0x00a5;
                case 59: goto L_0x00a5;
                case 125: goto L_0x00b5;
                default: goto L_0x0085;
            }     // Catch:{ JSONException -> 0x0028 }
        L_0x0085:
            java.lang.String r1 = "Expected a ',' or '}'"
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x008c:
            java.lang.String r1 = "A JSONObject text must end with '}'"
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x0093:
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = r3
            goto L_0x001a
        L_0x0098:
            r2 = 58
            if (r1 == r2) goto L_0x005f
            java.lang.String r1 = "Expected a ':' after a key"
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x00a3:
            r1 = r4
            goto L_0x006c
        L_0x00a5:
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            if (r1 != r12) goto L_0x00b1
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = r3
            goto L_0x001a
        L_0x00b1:
            r14.back()     // Catch:{ JSONException -> 0x0028 }
            goto L_0x003a
        L_0x00b5:
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = r3
            goto L_0x001a
        L_0x00bb:
            if (r7 == 0) goto L_0x00c3
            boolean r9 = r7.apply(r14, r8, r2)     // Catch:{ JSONException -> 0x0028 }
            if (r9 != 0) goto L_0x0101
        L_0x00c3:
            if (r1 == 0) goto L_0x00f2
            boolean r1 = r2 instanceof java.lang.String     // Catch:{ JSONException -> 0x0028 }
            if (r1 == 0) goto L_0x00f2
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0028 }
            r1 = r0
            boolean r1 = net.sf.json.util.JSONUtils.mayBeJSON(r1)     // Catch:{ JSONException -> 0x0028 }
            if (r1 != 0) goto L_0x00d9
            boolean r1 = net.sf.json.util.JSONUtils.isFunction(r2)     // Catch:{ JSONException -> 0x0028 }
            if (r1 == 0) goto L_0x00f2
        L_0x00d9:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x0028 }
            r1.<init>()     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r9 = "\""
            java.lang.StringBuffer r1 = r1.append(r9)     // Catch:{ JSONException -> 0x0028 }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r2 = "\""
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r2 = r1.toString()     // Catch:{ JSONException -> 0x0028 }
        L_0x00f2:
            java.util.Map r1 = r3.properties     // Catch:{ JSONException -> 0x0028 }
            boolean r1 = r1.containsKey(r8)     // Catch:{ JSONException -> 0x0028 }
            if (r1 == 0) goto L_0x010f
            r3.accumulate(r8, r2, r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = 1
            net.sf.json.AbstractJSON.firePropertySetEvent(r8, r2, r1, r15)     // Catch:{ JSONException -> 0x0028 }
        L_0x0101:
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            switch(r1) {
                case 44: goto L_0x0197;
                case 59: goto L_0x0197;
                case 125: goto L_0x01a8;
                default: goto L_0x0108;
            }     // Catch:{ JSONException -> 0x0028 }
        L_0x0108:
            java.lang.String r1 = "Expected a ',' or '}'"
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x010f:
            r3.element(r8, r2, r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = 0
            net.sf.json.AbstractJSON.firePropertySetEvent(r8, r2, r1, r15)     // Catch:{ JSONException -> 0x0028 }
            goto L_0x0101
        L_0x0117:
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0028 }
            r1 = r0
            java.lang.String r9 = net.sf.json.util.JSONUtils.getFunctionParams(r1)     // Catch:{ JSONException -> 0x0028 }
            java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x0028 }
            r10.<init>()     // Catch:{ JSONException -> 0x0028 }
            r1 = r4
        L_0x0125:
            char r11 = r14.next()     // Catch:{ JSONException -> 0x0028 }
            if (r11 != 0) goto L_0x0145
        L_0x012b:
            if (r1 == 0) goto L_0x0153
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x0028 }
            r1.<init>()     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r3 = "Unbalanced '{' or '}' on prop: "
            java.lang.StringBuffer r1 = r1.append(r3)     // Catch:{ JSONException -> 0x0028 }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0028 }
            net.sf.json.JSONException r1 = r14.syntaxError(r1)     // Catch:{ JSONException -> 0x0028 }
            throw r1     // Catch:{ JSONException -> 0x0028 }
        L_0x0145:
            if (r11 != r13) goto L_0x0149
            int r1 = r1 + 1
        L_0x0149:
            if (r11 != r12) goto L_0x014d
            int r1 = r1 + -1
        L_0x014d:
            r10.append(r11)     // Catch:{ JSONException -> 0x0028 }
            if (r1 != 0) goto L_0x0125
            goto L_0x012b
        L_0x0153:
            java.lang.String r1 = r10.toString()     // Catch:{ JSONException -> 0x0028 }
            r2 = 1
            int r10 = r1.length()     // Catch:{ JSONException -> 0x0028 }
            int r10 = r10 + -1
            java.lang.String r1 = r1.substring(r2, r10)     // Catch:{ JSONException -> 0x0028 }
            java.lang.String r2 = r1.trim()     // Catch:{ JSONException -> 0x0028 }
            net.sf.json.JSONFunction r10 = new net.sf.json.JSONFunction     // Catch:{ JSONException -> 0x0028 }
            if (r9 == 0) goto L_0x018c
            java.lang.String r1 = ","
            java.lang.String[] r1 = org.apache.commons.lang.StringUtils.split(r9, r1)     // Catch:{ JSONException -> 0x0028 }
        L_0x0170:
            r10.<init>(r1, r2)     // Catch:{ JSONException -> 0x0028 }
            if (r7 == 0) goto L_0x017b
            boolean r1 = r7.apply(r14, r8, r10)     // Catch:{ JSONException -> 0x0028 }
            if (r1 != 0) goto L_0x0101
        L_0x017b:
            java.util.Map r1 = r3.properties     // Catch:{ JSONException -> 0x0028 }
            boolean r1 = r1.containsKey(r8)     // Catch:{ JSONException -> 0x0028 }
            if (r1 == 0) goto L_0x018e
            r3.accumulate(r8, r10, r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = 1
            net.sf.json.AbstractJSON.firePropertySetEvent(r8, r10, r1, r15)     // Catch:{ JSONException -> 0x0028 }
            goto L_0x0101
        L_0x018c:
            r1 = 0
            goto L_0x0170
        L_0x018e:
            r3.element(r8, r10, r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = 0
            net.sf.json.AbstractJSON.firePropertySetEvent(r8, r10, r1, r15)     // Catch:{ JSONException -> 0x0028 }
            goto L_0x0101
        L_0x0197:
            char r1 = r14.nextClean()     // Catch:{ JSONException -> 0x0028 }
            if (r1 != r12) goto L_0x01a3
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = r3
            goto L_0x001a
        L_0x01a3:
            r14.back()     // Catch:{ JSONException -> 0x0028 }
            goto L_0x003a
        L_0x01a8:
            net.sf.json.AbstractJSON.fireObjectEndEvent(r15)     // Catch:{ JSONException -> 0x0028 }
            r1 = r3
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._fromJSONTokener(net.sf.json.util.JSONTokener, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v23, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
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
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONObject _fromMap(java.util.Map r7, net.sf.json.JsonConfig r8) {
        /*
            r1 = 1
            net.sf.json.AbstractJSON.fireObjectStartEvent(r8)
            if (r7 != 0) goto L_0x000f
            net.sf.json.AbstractJSON.fireObjectEndEvent(r8)
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r0.<init>(r1)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r7)
            if (r0 != 0) goto L_0x0033
            net.sf.json.util.CycleDetectionStrategy r0 = r8.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x001e, RuntimeException -> 0x0026 }
            net.sf.json.JSONObject r0 = r0.handleRepeatedReferenceAsObject(r7)     // Catch:{ JSONException -> 0x001e, RuntimeException -> 0x0026 }
            goto L_0x000e
        L_0x001e:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r7)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r8)
            throw r0
        L_0x0026:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r7)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r8)
            throw r1
        L_0x0033:
            java.util.Collection r2 = r8.getMergedExcludes()
            net.sf.json.JSONObject r1 = new net.sf.json.JSONObject
            r1.<init>()
            net.sf.json.util.PropertyFilter r3 = r8.getJsonPropertyFilter()
            java.util.Set r0 = r7.entrySet()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
        L_0x0048:
            boolean r0 = r4.hasNext()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r0 == 0) goto L_0x010b
            java.lang.Object r0 = r4.next()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.Object r5 = r0.getKey()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r5 != 0) goto L_0x006a
            net.sf.json.JSONException r0 = new net.sf.json.JSONException     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r1 = "JSON keys cannot be null."
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            throw r0     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
        L_0x0062:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r7)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r8)
            throw r0
        L_0x006a:
            boolean r6 = r5 instanceof java.lang.String     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 != 0) goto L_0x0083
            java.lang.ClassCastException r0 = new java.lang.ClassCastException     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r1 = "JSON keys must be strings."
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            throw r0     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
        L_0x0076:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r7)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r8)
            throw r1
        L_0x0083:
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r6 = "null"
            boolean r6 = r6.equals(r5)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 == 0) goto L_0x0097
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r1 = "JSON keys must not be null nor the 'null' string."
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            throw r0     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
        L_0x0097:
            boolean r6 = r2.contains(r5)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 != 0) goto L_0x0048
            java.lang.Object r0 = r0.getValue()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r3 == 0) goto L_0x00a9
            boolean r6 = r3.apply(r7, r5, r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 != 0) goto L_0x0048
        L_0x00a9:
            if (r0 == 0) goto L_0x00e1
            java.lang.Class r6 = r0.getClass()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            net.sf.json.processors.JsonValueProcessor r6 = r8.findJsonValueProcessor(r6, r5)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 == 0) goto L_0x00d8
            java.lang.Object r0 = r6.processObjectValue(r5, r0, r8)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            boolean r6 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r6 != 0) goto L_0x00d8
            net.sf.json.JSONException r1 = new net.sf.json.JSONException     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r2.<init>()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r3 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r2 = r2.append(r3)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.StringBuffer r0 = r2.append(r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            throw r1     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
        L_0x00d8:
            java.lang.Class r6 = r0.getClass()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            setValue(r1, r5, r0, r6, r8)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            goto L_0x0048
        L_0x00e1:
            java.util.Map r0 = r1.properties     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            boolean r0 = r0.containsKey(r5)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            if (r0 == 0) goto L_0x00fa
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r1.accumulate(r5, r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r6 = 1
            net.sf.json.AbstractJSON.firePropertySetEvent(r5, r0, r6, r8)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            goto L_0x0048
        L_0x00fa:
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r1.element(r5, r0)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            r6 = 0
            net.sf.json.AbstractJSON.firePropertySetEvent(r5, r0, r6, r8)     // Catch:{ JSONException -> 0x0062, RuntimeException -> 0x0076 }
            goto L_0x0048
        L_0x010b:
            net.sf.json.AbstractJSON.removeInstance(r7)
            net.sf.json.AbstractJSON.fireObjectEndEvent(r8)
            r0 = r1
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._fromMap(java.util.Map, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    private static JSONObject _fromString(String str, JsonConfig jsonConfig) {
        if (str != null && !"null".equals(str)) {
            return _fromJSONTokener(new JSONTokener(str), jsonConfig);
        }
        AbstractJSON.fireObjectStartEvent(jsonConfig);
        AbstractJSON.fireObjectEndEvent(jsonConfig);
        return new JSONObject(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object convertPropertyValueToArray(java.lang.String r6, java.lang.Object r7, java.lang.Class r8, net.sf.json.JsonConfig r9, java.util.Map r10) {
        /*
            r5 = 0
            java.lang.Class r2 = net.sf.json.util.JSONUtils.getInnerComponentType(r8)
            java.lang.Class r1 = findTargetClass(r6, r10)
            java.lang.Class r0 = class$java$lang$Object
            if (r0 != 0) goto L_0x0075
            java.lang.String r0 = "java.lang.Object"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Object = r0
        L_0x0015:
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00df
            if (r1 == 0) goto L_0x00df
            java.lang.Class r0 = class$java$lang$Object
            if (r0 != 0) goto L_0x0078
            java.lang.String r0 = "java.lang.Object"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Object = r0
        L_0x0029:
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x00df
            r0 = r1
        L_0x0030:
            net.sf.json.JsonConfig r1 = r9.copy()
            r1.setRootClass(r0)
            r1.setClassMap(r10)
            net.sf.json.JSONArray r7 = (net.sf.json.JSONArray) r7
            java.lang.Object r2 = net.sf.json.JSONArray.toArray(r7, r1)
            boolean r1 = r0.isPrimitive()
            if (r1 != 0) goto L_0x0064
            boolean r1 = net.sf.json.util.JSONUtils.isNumber(r0)
            if (r1 != 0) goto L_0x0064
            java.lang.Class r1 = class$java$lang$Boolean
            if (r1 != 0) goto L_0x007b
            java.lang.String r1 = "java.lang.Boolean"
            java.lang.Class r1 = class$(r1)
            class$java$lang$Boolean = r1
        L_0x0058:
            boolean r1 = r1.isAssignableFrom(r0)
            if (r1 != 0) goto L_0x0064
            boolean r1 = net.sf.json.util.JSONUtils.isString(r0)
            if (r1 == 0) goto L_0x007e
        L_0x0064:
            net.sf.ezmorph.MorpherRegistry r1 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r5)
            java.lang.Class r0 = r0.getClass()
            java.lang.Object r0 = r1.morph(r0, r2)
        L_0x0074:
            return r0
        L_0x0075:
            java.lang.Class r0 = class$java$lang$Object
            goto L_0x0015
        L_0x0078:
            java.lang.Class r0 = class$java$lang$Object
            goto L_0x0029
        L_0x007b:
            java.lang.Class r1 = class$java$lang$Boolean
            goto L_0x0058
        L_0x007e:
            java.lang.Class r1 = r2.getClass()
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00dd
            java.lang.Class r1 = class$java$lang$Object
            if (r1 != 0) goto L_0x00da
            java.lang.String r1 = "java.lang.Object"
            java.lang.Class r1 = class$(r1)
            class$java$lang$Object = r1
        L_0x0094:
            boolean r1 = r8.equals(r1)
            if (r1 != 0) goto L_0x00dd
            net.sf.ezmorph.MorpherRegistry r1 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r0, r5)
            java.lang.Class r3 = r3.getClass()
            net.sf.ezmorph.Morpher r1 = r1.getMorpherFor(r3)
            net.sf.ezmorph.object.IdentityObjectMorpher r3 = net.sf.ezmorph.object.IdentityObjectMorpher.getInstance()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x00c9
            net.sf.ezmorph.array.ObjectArrayMorpher r1 = new net.sf.ezmorph.array.ObjectArrayMorpher
            net.sf.ezmorph.bean.BeanMorpher r3 = new net.sf.ezmorph.bean.BeanMorpher
            net.sf.ezmorph.MorpherRegistry r4 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            r3.<init>(r0, r4)
            r1.<init>(r3)
            net.sf.ezmorph.MorpherRegistry r3 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            r3.registerMorpher(r1)
        L_0x00c9:
            net.sf.ezmorph.MorpherRegistry r1 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r5)
            java.lang.Class r0 = r0.getClass()
            java.lang.Object r0 = r1.morph(r0, r2)
            goto L_0x0074
        L_0x00da:
            java.lang.Class r1 = class$java$lang$Object
            goto L_0x0094
        L_0x00dd:
            r0 = r2
            goto L_0x0074
        L_0x00df:
            r0 = r2
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.convertPropertyValueToArray(java.lang.String, java.lang.Object, java.lang.Class, net.sf.json.JsonConfig, java.util.Map):java.lang.Object");
    }

    private static List convertPropertyValueToList(String str, Object obj, JsonConfig jsonConfig, String str2, Map map) {
        Class findTargetClass = findTargetClass(str, map);
        if (findTargetClass == null) {
            findTargetClass = findTargetClass(str2, map);
        }
        JsonConfig copy = jsonConfig.copy();
        copy.setRootClass(findTargetClass);
        copy.setClassMap(map);
        return (List) JSONArray.toCollection((JSONArray) obj, copy);
    }

    private static Collection convertPropertyValueToCollection(String str, Object obj, JsonConfig jsonConfig, String str2, Map map, Class cls) {
        Class findTargetClass = findTargetClass(str, map);
        if (findTargetClass == null) {
            findTargetClass = findTargetClass(str2, map);
        }
        JsonConfig copy = jsonConfig.copy();
        copy.setRootClass(findTargetClass);
        copy.setClassMap(map);
        copy.setCollectionType(cls);
        return JSONArray.toCollection((JSONArray) obj, copy);
    }

    private static Class findTargetClass(String str, Map map) {
        Class cls = (Class) map.get(str);
        if (cls != null) {
            return cls;
        }
        for (Entry entry : map.entrySet()) {
            if (RegexpUtils.getMatcher((String) entry.getKey()).matches(str)) {
                return (Class) entry.getValue();
            }
        }
        return cls;
    }

    private static boolean isTransientField(String str, Class cls) {
        try {
            if ((cls.getDeclaredField(str).getModifiers() & 128) == 128) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static Object morphPropertyValue(String str, Object obj, Class cls, Class cls2) {
        if (IdentityObjectMorpher.getInstance().equals(JSONUtils.getMorpherRegistry().getMorpherFor(cls2))) {
            log.warn(new StringBuffer().append("Can't transform property '").append(str).append("' from ").append(cls.getName()).append(" into ").append(cls2.getName()).append(". Will register a default BeanMorpher").toString());
            JSONUtils.getMorpherRegistry().registerMorpher(new BeanMorpher(cls2, JSONUtils.getMorpherRegistry()));
        }
        return JSONUtils.getMorpherRegistry().morph(cls2, obj);
    }

    private static void setProperty(Object obj, String str, Object obj2, JsonConfig jsonConfig) throws Exception {
        (jsonConfig.getPropertySetStrategy() != null ? jsonConfig.getPropertySetStrategy() : PropertySetStrategy.DEFAULT).setProperty(obj, str, obj2);
    }

    /* JADX WARNING: type inference failed for: r0v26, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v26, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 59
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
    private static void setValue(net.sf.json.JSONObject r4, java.lang.String r5, java.lang.Object r6, java.lang.Class r7, net.sf.json.JsonConfig r8) {
        /*
            r2 = 0
            if (r6 != 0) goto L_0x002a
            net.sf.json.processors.DefaultValueProcessor r0 = r8.findDefaultValueProcessor(r7)
            java.lang.Object r1 = r0.getDefaultValue(r7)
            boolean r0 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r1)
            if (r0 != 0) goto L_0x002b
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x002a:
            r1 = r6
        L_0x002b:
            java.util.Map r0 = r4.properties
            boolean r0 = r0.containsKey(r5)
            if (r0 == 0) goto L_0x0088
            java.lang.Class r0 = class$java$lang$String
            if (r0 != 0) goto L_0x006c
            java.lang.String r0 = "java.lang.String"
            java.lang.Class r0 = class$(r0)
            class$java$lang$String = r0
        L_0x003f:
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x0084
            java.lang.Object r0 = r4.opt(r5)
            boolean r2 = r0 instanceof net.sf.json.JSONArray
            if (r2 == 0) goto L_0x006f
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            java.lang.String r1 = (java.lang.String) r1
            r0.addString(r1)
        L_0x0054:
            r0 = 1
            r1 = r0
        L_0x0056:
            java.lang.Object r0 = r4.opt(r5)
            if (r1 == 0) goto L_0x0068
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            int r2 = r0.size()
            int r2 = r2 + -1
            java.lang.Object r0 = r0.get(r2)
        L_0x0068:
            net.sf.json.AbstractJSON.firePropertySetEvent(r5, r0, r1, r8)
            return
        L_0x006c:
            java.lang.Class r0 = class$java$lang$String
            goto L_0x003f
        L_0x006f:
            java.util.Map r2 = r4.properties
            net.sf.json.JSONArray r3 = new net.sf.json.JSONArray
            r3.<init>()
            net.sf.json.JSONArray r0 = r3.element(r0)
            java.lang.String r1 = (java.lang.String) r1
            net.sf.json.JSONArray r0 = r0.addString(r1)
            r2.put(r5, r0)
            goto L_0x0054
        L_0x0084:
            r4.accumulate(r5, r1, r8)
            goto L_0x0054
        L_0x0088:
            java.lang.Class r0 = class$java$lang$String
            if (r0 != 0) goto L_0x00a1
            java.lang.String r0 = "java.lang.String"
            java.lang.Class r0 = class$(r0)
            class$java$lang$String = r0
        L_0x0094:
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x00a4
            java.util.Map r0 = r4.properties
            r0.put(r5, r1)
            r1 = r2
            goto L_0x0056
        L_0x00a1:
            java.lang.Class r0 = class$java$lang$String
            goto L_0x0094
        L_0x00a4:
            r4._setInternal(r5, r1, r8)
            r1 = r2
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.setValue(net.sf.json.JSONObject, java.lang.String, java.lang.Object, java.lang.Class, net.sf.json.JsonConfig):void");
    }

    public JSONObject() {
        this.properties = new ListOrderedMap();
    }

    public JSONObject(boolean z) {
        this();
        this.nullObject = z;
    }

    public JSONObject accumulate(String str, boolean z) {
        return _accumulate(str, z ? Boolean.TRUE : Boolean.FALSE, new JsonConfig());
    }

    public JSONObject accumulate(String str, double d) {
        return _accumulate(str, new Double(d), new JsonConfig());
    }

    public JSONObject accumulate(String str, int i) {
        return _accumulate(str, new Integer(i), new JsonConfig());
    }

    public JSONObject accumulate(String str, long j) {
        return _accumulate(str, new Long(j), new JsonConfig());
    }

    public JSONObject accumulate(String str, Object obj) {
        return _accumulate(str, obj, new JsonConfig());
    }

    public JSONObject accumulate(String str, Object obj, JsonConfig jsonConfig) {
        return _accumulate(str, obj, jsonConfig);
    }

    public void accumulateAll(Map map) {
        accumulateAll(map, new JsonConfig());
    }

    public void accumulateAll(Map map, JsonConfig jsonConfig) {
        if (map instanceof JSONObject) {
            for (Entry entry : map.entrySet()) {
                accumulate((String) entry.getKey(), entry.getValue(), jsonConfig);
            }
            return;
        }
        for (Entry entry2 : map.entrySet()) {
            accumulate(String.valueOf(entry2.getKey()), entry2.getValue(), jsonConfig);
        }
    }

    public void clear() {
        this.properties.clear();
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof JSONObject)) {
            return -1;
        }
        JSONObject jSONObject = (JSONObject) obj;
        int size = size();
        int size2 = jSONObject.size();
        if (size < size2) {
            return -1;
        }
        if (size > size2) {
            return 1;
        }
        if (equals(jSONObject)) {
            return 0;
        }
        return -1;
    }

    public boolean containsKey(Object obj) {
        return this.properties.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return containsValue(obj, new JsonConfig());
    }

    public boolean containsValue(Object obj, JsonConfig jsonConfig) {
        try {
            return this.properties.containsValue(processValue(obj, jsonConfig));
        } catch (JSONException e) {
            return false;
        }
    }

    public JSONObject discard(String str) {
        verifyIsNull();
        this.properties.remove(str);
        return this;
    }

    public JSONObject element(String str, boolean z) {
        verifyIsNull();
        return element(str, (Object) z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONObject element(String str, Collection collection) {
        return element(str, collection, new JsonConfig());
    }

    public JSONObject element(String str, Collection collection, JsonConfig jsonConfig) {
        verifyIsNull();
        if (collection instanceof JSONArray) {
            return setInternal(str, collection, jsonConfig);
        }
        return element(str, (Collection) JSONArray.fromObject(collection, jsonConfig));
    }

    public JSONObject element(String str, double d) {
        verifyIsNull();
        Double d2 = new Double(d);
        JSONUtils.testValidity(d2);
        return element(str, (Object) d2);
    }

    public JSONObject element(String str, int i) {
        verifyIsNull();
        return element(str, (Object) new Integer(i));
    }

    public JSONObject element(String str, long j) {
        verifyIsNull();
        return element(str, (Object) new Long(j));
    }

    public JSONObject element(String str, Map map) {
        return element(str, map, new JsonConfig());
    }

    public JSONObject element(String str, Map map, JsonConfig jsonConfig) {
        verifyIsNull();
        if (map instanceof JSONObject) {
            return setInternal(str, map, jsonConfig);
        }
        return element(str, (Map) fromObject(map, jsonConfig), jsonConfig);
    }

    public JSONObject element(String str, Object obj) {
        return element(str, obj, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public net.sf.json.JSONObject element(java.lang.String r3, java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r2 = this;
            r2.verifyIsNull()
            if (r3 != 0) goto L_0x000d
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Null key."
            r0.<init>(r1)
            throw r0
        L_0x000d:
            if (r4 == 0) goto L_0x0017
            java.lang.Object r0 = r2.processValue(r3, r4, r5)
            r2._setInternal(r3, r0, r5)
        L_0x0016:
            return r2
        L_0x0017:
            r2.remove(r3)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.element(java.lang.String, java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    public JSONObject elementOpt(String str, Object obj) {
        return elementOpt(str, obj, new JsonConfig());
    }

    public JSONObject elementOpt(String str, Object obj, JsonConfig jsonConfig) {
        verifyIsNull();
        if (!(str == null || obj == null)) {
            element(str, obj, jsonConfig);
        }
        return this;
    }

    public Set entrySet() {
        return Collections.unmodifiableSet(this.properties.entrySet());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof JSONObject)) {
            return false;
        }
        JSONObject jSONObject = (JSONObject) obj;
        if (isNullObject()) {
            if (jSONObject.isNullObject()) {
                return true;
            }
            return false;
        } else if (jSONObject.isNullObject()) {
            return false;
        } else {
            if (jSONObject.size() != size()) {
                return false;
            }
            for (String str : this.properties.keySet()) {
                if (!jSONObject.properties.containsKey(str)) {
                    return false;
                }
                Object obj2 = this.properties.get(str);
                Object obj3 = jSONObject.properties.get(str);
                if (JSONNull.getInstance().equals(obj2)) {
                    if (!JSONNull.getInstance().equals(obj3)) {
                        return false;
                    }
                } else if (JSONNull.getInstance().equals(obj3)) {
                    return false;
                } else {
                    if (!(obj2 instanceof String) || !(obj3 instanceof JSONFunction)) {
                        if (!(obj2 instanceof JSONFunction) || !(obj3 instanceof String)) {
                            if (!(obj2 instanceof JSONObject) || !(obj3 instanceof JSONObject)) {
                                if (!(obj2 instanceof JSONArray) || !(obj3 instanceof JSONArray)) {
                                    if (!(obj2 instanceof JSONFunction) || !(obj3 instanceof JSONFunction)) {
                                        if (obj2 instanceof String) {
                                            if (!obj2.equals(String.valueOf(obj3))) {
                                                return false;
                                            }
                                        } else if (!(obj3 instanceof String)) {
                                            IdentityObjectMorpher morpherFor = JSONUtils.getMorpherRegistry().getMorpherFor(obj2.getClass());
                                            IdentityObjectMorpher morpherFor2 = JSONUtils.getMorpherRegistry().getMorpherFor(obj3.getClass());
                                            if (morpherFor == null || morpherFor == IdentityObjectMorpher.getInstance()) {
                                                if (morpherFor2 == null || morpherFor2 == IdentityObjectMorpher.getInstance()) {
                                                    if (!obj2.equals(obj3)) {
                                                        return false;
                                                    }
                                                } else if (!JSONUtils.getMorpherRegistry().morph(obj2.getClass(), obj2).equals(obj3)) {
                                                    return false;
                                                }
                                            } else if (!obj2.equals(JSONUtils.getMorpherRegistry().morph(obj2.getClass(), obj3))) {
                                                return false;
                                            }
                                        } else if (!obj3.equals(String.valueOf(obj2))) {
                                            return false;
                                        }
                                    } else if (!obj2.equals(obj3)) {
                                        return false;
                                    }
                                } else if (!obj2.equals(obj3)) {
                                    return false;
                                }
                            } else if (!obj2.equals(obj3)) {
                                return false;
                            }
                        } else if (!obj3.equals(String.valueOf(obj2))) {
                            return false;
                        }
                    } else if (!obj2.equals(String.valueOf(obj3))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public Object get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public Object get(String str) {
        verifyIsNull();
        return this.properties.get(str);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getBoolean(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r1 = r3.get(r4)
            if (r1 == 0) goto L_0x003a
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0020
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x0022
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "false"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0022
        L_0x0020:
            r0 = 0
        L_0x0021:
            return r0
        L_0x0022:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0038
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x003a
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "true"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x003a
        L_0x0038:
            r0 = 1
            goto L_0x0021
        L_0x003a:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a Boolean."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getBoolean(java.lang.String):boolean");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 32
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
    public double getDouble(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x003f
            boolean r1 = r0 instanceof java.lang.Number     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x0014
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ Exception -> 0x001b }
            double r0 = r0.doubleValue()     // Catch:{ Exception -> 0x001b }
        L_0x0013:
            return r0
        L_0x0014:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x001b }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x001b }
            goto L_0x0013
        L_0x001b:
            r0 = move-exception
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x003f:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getDouble(java.lang.String):double");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 21
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
    public int getInt(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x001a
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0014
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
        L_0x0013:
            return r0
        L_0x0014:
            double r0 = r3.getDouble(r4)
            int r0 = (int) r0
            goto L_0x0013
        L_0x001a:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getInt(java.lang.String):int");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 17
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
    public net.sf.json.JSONArray getJSONArray(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0 instanceof net.sf.json.JSONArray
            if (r1 == 0) goto L_0x0010
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            return r0
        L_0x0010:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a JSONArray."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getJSONArray(java.lang.String):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 21
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
    public net.sf.json.JSONObject getJSONObject(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            net.sf.json.JSONNull r1 = net.sf.json.JSONNull.getInstance()
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0018
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r1 = 1
            r0.<init>(r1)
        L_0x0017:
            return r0
        L_0x0018:
            boolean r1 = r0 instanceof net.sf.json.JSONObject
            if (r1 == 0) goto L_0x001f
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0
            goto L_0x0017
        L_0x001f:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a JSONObject."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getJSONObject(java.lang.String):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 21
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
    public long getLong(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x001a
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0014
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
        L_0x0013:
            return r0
        L_0x0014:
            double r0 = r3.getDouble(r4)
            long r0 = (long) r0
            goto L_0x0013
        L_0x001a:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getLong(java.lang.String):long");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public java.lang.String getString(java.lang.String r4) {
        /*
            r3 = this;
            r3.verifyIsNull()
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r0.toString()
            return r0
        L_0x000e:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONObject["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r4)
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.getString(java.lang.String):java.lang.String");
    }

    public boolean has(String str) {
        verifyIsNull();
        return this.properties.containsKey(str);
    }

    public int hashCode() {
        int i = 19;
        if (isNullObject()) {
            return JSONNull.getInstance().hashCode() + 19;
        }
        Iterator it = this.properties.entrySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            Entry entry = (Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            i = JSONUtils.hashCode(value) + key.hashCode() + i2;
        }
    }

    public boolean isArray() {
        return false;
    }

    public boolean isEmpty() {
        verifyIsNull();
        return this.properties.isEmpty();
    }

    public boolean isNullObject() {
        return this.nullObject;
    }

    public Iterator keys() {
        verifyIsNull();
        return keySet().iterator();
    }

    public Set keySet() {
        return Collections.unmodifiableSet(this.properties.keySet());
    }

    public JSONArray names() {
        return names(new JsonConfig());
    }

    public JSONArray names(JsonConfig jsonConfig) {
        verifyIsNull();
        JSONArray jSONArray = new JSONArray();
        Iterator keys = keys();
        while (keys.hasNext()) {
            jSONArray.element(keys.next(), jsonConfig);
        }
        return jSONArray;
    }

    public Object opt(String str) {
        verifyIsNull();
        if (str == null) {
            return null;
        }
        return this.properties.get(str);
    }

    public boolean optBoolean(String str) {
        verifyIsNull();
        return optBoolean(str, false);
    }

    public boolean optBoolean(String str, boolean z) {
        verifyIsNull();
        try {
            return getBoolean(str);
        } catch (Exception e) {
            return z;
        }
    }

    public double optDouble(String str) {
        verifyIsNull();
        return optDouble(str, Double.NaN);
    }

    public double optDouble(String str, double d) {
        verifyIsNull();
        try {
            Object opt = opt(str);
            return opt instanceof Number ? ((Number) opt).doubleValue() : new Double((String) opt).doubleValue();
        } catch (Exception e) {
            return d;
        }
    }

    public int optInt(String str) {
        verifyIsNull();
        return optInt(str, 0);
    }

    public int optInt(String str, int i) {
        verifyIsNull();
        try {
            return getInt(str);
        } catch (Exception e) {
            return i;
        }
    }

    public JSONArray optJSONArray(String str) {
        verifyIsNull();
        Object opt = opt(str);
        if (opt instanceof JSONArray) {
            return (JSONArray) opt;
        }
        return null;
    }

    public JSONObject optJSONObject(String str) {
        verifyIsNull();
        Object opt = opt(str);
        if (opt instanceof JSONObject) {
            return (JSONObject) opt;
        }
        return null;
    }

    public long optLong(String str) {
        verifyIsNull();
        return optLong(str, 0);
    }

    public long optLong(String str, long j) {
        verifyIsNull();
        try {
            return getLong(str);
        } catch (Exception e) {
            return j;
        }
    }

    public String optString(String str) {
        verifyIsNull();
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        verifyIsNull();
        Object opt = opt(str);
        return opt != null ? opt.toString() : str2;
    }

    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException("key is null.");
        }
        Object obj3 = this.properties.get(obj);
        element(String.valueOf(obj), obj2);
        return obj3;
    }

    public void putAll(Map map) {
        putAll(map, new JsonConfig());
    }

    public void putAll(Map map, JsonConfig jsonConfig) {
        if (map instanceof JSONObject) {
            for (Entry entry : map.entrySet()) {
                this.properties.put((String) entry.getKey(), entry.getValue());
            }
            return;
        }
        for (Entry entry2 : map.entrySet()) {
            element(String.valueOf(entry2.getKey()), entry2.getValue(), jsonConfig);
        }
    }

    public Object remove(Object obj) {
        return this.properties.remove(obj);
    }

    public Object remove(String str) {
        verifyIsNull();
        return this.properties.remove(str);
    }

    public int size() {
        verifyIsNull();
        return this.properties.size();
    }

    public JSONArray toJSONArray(JSONArray jSONArray) {
        verifyIsNull();
        if (jSONArray == null || jSONArray.size() == 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.size(); i++) {
            jSONArray2.element(opt(jSONArray.getString(i)));
        }
        return jSONArray2;
    }

    public String toString() {
        if (isNullObject()) {
            return JSONNull.getInstance().toString();
        }
        try {
            Iterator keys = keys();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (keys.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = keys.next();
                stringBuffer.append(JSONUtils.quote(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(JSONUtils.valueToString(this.properties.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String toString(int i) {
        if (isNullObject()) {
            return JSONNull.getInstance().toString();
        }
        if (i == 0) {
            return toString();
        }
        return toString(i, 0);
    }

    public String toString(int i, int i2) {
        if (isNullObject()) {
            return JSONNull.getInstance().toString();
        }
        int size = size();
        if (size == 0) {
            return "{}";
        }
        if (i == 0) {
            return toString();
        }
        Iterator keys = keys();
        StringBuffer stringBuffer = new StringBuffer("{");
        int i3 = i2 + i;
        if (size == 1) {
            Object next = keys.next();
            stringBuffer.append(JSONUtils.quote(next.toString()));
            stringBuffer.append(": ");
            stringBuffer.append(JSONUtils.valueToString(this.properties.get(next), i, i2));
        } else {
            while (keys.hasNext()) {
                Object next2 = keys.next();
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(",\n");
                } else {
                    stringBuffer.append(10);
                }
                for (int i4 = 0; i4 < i3; i4++) {
                    stringBuffer.append(' ');
                }
                stringBuffer.append(JSONUtils.quote(next2.toString()));
                stringBuffer.append(": ");
                stringBuffer.append(JSONUtils.valueToString(this.properties.get(next2), i, i3));
            }
            if (stringBuffer.length() > 1) {
                stringBuffer.append(10);
                for (int i5 = 0; i5 < i2; i5++) {
                    stringBuffer.append(' ');
                }
            }
            for (int i6 = 0; i6 < i2; i6++) {
                stringBuffer.insert(0, ' ');
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public Collection values() {
        return Collections.unmodifiableCollection(this.properties.values());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 37
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
    public java.io.Writer write(java.io.Writer r4) {
        /*
            r3 = this;
            boolean r0 = r3.isNullObject()     // Catch:{ IOException -> 0x0058 }
            if (r0 == 0) goto L_0x0012
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()     // Catch:{ IOException -> 0x0058 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0058 }
            r4.write(r0)     // Catch:{ IOException -> 0x0058 }
        L_0x0011:
            return r4
        L_0x0012:
            r0 = 0
            java.util.Iterator r1 = r3.keys()     // Catch:{ IOException -> 0x0058 }
            r2 = 123(0x7b, float:1.72E-43)
            r4.write(r2)     // Catch:{ IOException -> 0x0058 }
        L_0x001c:
            boolean r2 = r1.hasNext()     // Catch:{ IOException -> 0x0058 }
            if (r2 == 0) goto L_0x0067
            if (r0 == 0) goto L_0x0029
            r0 = 44
            r4.write(r0)     // Catch:{ IOException -> 0x0058 }
        L_0x0029:
            java.lang.Object r0 = r1.next()     // Catch:{ IOException -> 0x0058 }
            java.lang.String r2 = r0.toString()     // Catch:{ IOException -> 0x0058 }
            java.lang.String r2 = net.sf.json.util.JSONUtils.quote(r2)     // Catch:{ IOException -> 0x0058 }
            r4.write(r2)     // Catch:{ IOException -> 0x0058 }
            r2 = 58
            r4.write(r2)     // Catch:{ IOException -> 0x0058 }
            java.util.Map r2 = r3.properties     // Catch:{ IOException -> 0x0058 }
            java.lang.Object r0 = r2.get(r0)     // Catch:{ IOException -> 0x0058 }
            boolean r2 = r0 instanceof net.sf.json.JSONObject     // Catch:{ IOException -> 0x0058 }
            if (r2 == 0) goto L_0x004e
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0     // Catch:{ IOException -> 0x0058 }
            r0.write(r4)     // Catch:{ IOException -> 0x0058 }
        L_0x004c:
            r0 = 1
            goto L_0x001c
        L_0x004e:
            boolean r2 = r0 instanceof net.sf.json.JSONArray     // Catch:{ IOException -> 0x0058 }
            if (r2 == 0) goto L_0x005f
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0     // Catch:{ IOException -> 0x0058 }
            r0.write(r4)     // Catch:{ IOException -> 0x0058 }
            goto L_0x004c
        L_0x0058:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x005f:
            java.lang.String r0 = net.sf.json.util.JSONUtils.valueToString(r0)     // Catch:{ IOException -> 0x0058 }
            r4.write(r0)     // Catch:{ IOException -> 0x0058 }
            goto L_0x004c
        L_0x0067:
            r0 = 125(0x7d, float:1.75E-43)
            r4.write(r0)     // Catch:{ IOException -> 0x0058 }
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.write(java.io.Writer):java.io.Writer");
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
    private net.sf.json.JSONObject _accumulate(java.lang.String r3, java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r2 = this;
            boolean r0 = r2.isNullObject()
            if (r0 == 0) goto L_0x000e
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Can't accumulate on null object"
            r0.<init>(r1)
            throw r0
        L_0x000e:
            boolean r0 = r2.has(r3)
            if (r0 != 0) goto L_0x0018
            r2.setInternal(r3, r4, r5)
        L_0x0017:
            return r2
        L_0x0018:
            java.lang.Object r0 = r2.opt(r3)
            boolean r1 = r0 instanceof net.sf.json.JSONArray
            if (r1 == 0) goto L_0x0026
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            r0.element(r4, r5)
            goto L_0x0017
        L_0x0026:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            net.sf.json.JSONArray r0 = r1.element(r0)
            net.sf.json.JSONArray r0 = r0.element(r4, r5)
            r2.setInternal(r3, r0, r5)
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._accumulate(java.lang.String, java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r0.isAssignableFrom(r4.getClass()) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object _processValue(java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0018
            java.lang.Class r0 = class$java$lang$Class
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "java.lang.Class"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Class = r0
        L_0x000e:
            java.lang.Class r1 = r4.getClass()
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x001c
        L_0x0018:
            boolean r0 = r4 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0026
        L_0x001c:
            java.lang.Class r4 = (java.lang.Class) r4
            java.lang.String r4 = r4.getName()
        L_0x0022:
            return r4
        L_0x0023:
            java.lang.Class r0 = class$java$lang$Class
            goto L_0x000e
        L_0x0026:
            boolean r0 = r4 instanceof net.sf.json.JSON
            if (r0 == 0) goto L_0x002f
            net.sf.json.JSON r4 = net.sf.json.JSONSerializer.toJSON(r4, r5)
            goto L_0x0022
        L_0x002f:
            boolean r0 = net.sf.json.util.JSONUtils.isFunction(r4)
            if (r0 == 0) goto L_0x0040
            boolean r0 = r4 instanceof java.lang.String
            if (r0 == 0) goto L_0x0022
            java.lang.String r4 = (java.lang.String) r4
            net.sf.json.JSONFunction r4 = net.sf.json.JSONFunction.parse(r4)
            goto L_0x0022
        L_0x0040:
            boolean r0 = r4 instanceof net.sf.json.JSONString
            if (r0 == 0) goto L_0x004b
            net.sf.json.JSONString r4 = (net.sf.json.JSONString) r4
            net.sf.json.JSON r4 = net.sf.json.JSONSerializer.toJSON(r4, r5)
            goto L_0x0022
        L_0x004b:
            boolean r0 = net.sf.json.util.JSONUtils.isArray(r4)
            if (r0 == 0) goto L_0x0056
            net.sf.json.JSONArray r4 = net.sf.json.JSONArray.fromObject(r4, r5)
            goto L_0x0022
        L_0x0056:
            boolean r0 = net.sf.json.util.JSONUtils.isString(r4)
            if (r0 == 0) goto L_0x0084
            java.lang.String r1 = java.lang.String.valueOf(r4)
            boolean r0 = net.sf.json.util.JSONUtils.mayBeJSON(r1)
            if (r0 == 0) goto L_0x0071
            net.sf.json.JSON r4 = net.sf.json.JSONSerializer.toJSON(r1, r5)     // Catch:{ JSONException -> 0x006b }
            goto L_0x0022
        L_0x006b:
            r0 = move-exception
            java.lang.String r4 = net.sf.json.util.JSONUtils.stripQuotes(r1)
            goto L_0x0022
        L_0x0071:
            if (r4 != 0) goto L_0x0076
            java.lang.String r4 = ""
            goto L_0x0022
        L_0x0076:
            java.lang.String r0 = net.sf.json.util.JSONUtils.stripQuotes(r1)
            boolean r2 = net.sf.json.util.JSONUtils.mayBeJSON(r0)
            if (r2 == 0) goto L_0x0082
        L_0x0080:
            r4 = r0
            goto L_0x0022
        L_0x0082:
            r0 = r1
            goto L_0x0080
        L_0x0084:
            boolean r0 = net.sf.json.util.JSONUtils.isNumber(r4)
            if (r0 == 0) goto L_0x0094
            net.sf.json.util.JSONUtils.testValidity(r4)
            java.lang.Number r4 = (java.lang.Number) r4
            java.lang.Number r4 = net.sf.json.util.JSONUtils.transformNumber(r4)
            goto L_0x0022
        L_0x0094:
            boolean r0 = net.sf.json.util.JSONUtils.isBoolean(r4)
            if (r0 != 0) goto L_0x0022
            net.sf.json.JSONObject r4 = fromObject(r4, r5)
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._processValue(java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    private net.sf.json.JSONObject _setInternal(java.lang.String r3, java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r2 = this;
            r2.verifyIsNull()
            if (r3 != 0) goto L_0x000d
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Null key."
            r0.<init>(r1)
            throw r0
        L_0x000d:
            boolean r0 = net.sf.json.util.JSONUtils.isString(r4)
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = java.lang.String.valueOf(r4)
            boolean r0 = net.sf.json.util.JSONUtils.mayBeJSON(r0)
            if (r0 == 0) goto L_0x0023
            java.util.Map r0 = r2.properties
            r0.put(r3, r4)
        L_0x0022:
            return r2
        L_0x0023:
            java.lang.Object r0 = r2._processValue(r4, r5)
            net.sf.json.JSONObject r1 = net.sf.json.util.CycleDetectionStrategy.IGNORE_PROPERTY_OBJ
            if (r1 == r0) goto L_0x0022
            net.sf.json.JSONArray r1 = net.sf.json.util.CycleDetectionStrategy.IGNORE_PROPERTY_ARR
            if (r1 == r0) goto L_0x0022
            java.util.Map r1 = r2.properties
            r1.put(r3, r0)
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject._setInternal(java.lang.String, java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    private java.lang.Object processValue(java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0030
            java.lang.Class r0 = r4.getClass()
            net.sf.json.processors.JsonValueProcessor r0 = r5.findJsonValueProcessor(r0)
            if (r0 == 0) goto L_0x0030
            r1 = 0
            java.lang.Object r4 = r0.processObjectValue(r1, r4, r5)
            boolean r0 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r4)
            if (r0 != 0) goto L_0x0030
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0030:
            java.lang.Object r0 = r3._processValue(r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.processValue(java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    private java.lang.Object processValue(java.lang.String r4, java.lang.Object r5, net.sf.json.JsonConfig r6) {
        /*
            r3 = this;
            if (r5 == 0) goto L_0x0030
            java.lang.Class r0 = r5.getClass()
            net.sf.json.processors.JsonValueProcessor r0 = r6.findJsonValueProcessor(r0, r4)
            if (r0 == 0) goto L_0x0030
            r1 = 0
            java.lang.Object r5 = r0.processObjectValue(r1, r5, r6)
            boolean r0 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r5)
            if (r0 != 0) goto L_0x0030
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0030:
            java.lang.Object r0 = r3._processValue(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.processValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    private JSONObject setInternal(String str, Object obj, JsonConfig jsonConfig) {
        return _setInternal(str, processValue(str, obj, jsonConfig), jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 6
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
    private void verifyIsNull() {
        /*
            r2 = this;
            boolean r0 = r2.isNullObject()
            if (r0 == 0) goto L_0x000e
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "null object"
            r0.<init>(r1)
            throw r0
        L_0x000e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONObject.verifyIsNull():void");
    }
}
