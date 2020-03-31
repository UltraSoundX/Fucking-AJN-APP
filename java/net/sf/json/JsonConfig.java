package net.sf.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.processors.DefaultDefaultValueProcessor;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.DefaultValueProcessorMatcher;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonBeanProcessorMatcher;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.processors.JsonValueProcessorMatcher;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.processors.PropertyNameProcessorMatcher;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JavaIdentifierTransformer;
import net.sf.json.util.JsonEventListener;
import net.sf.json.util.NewBeanInstanceStrategy;
import net.sf.json.util.PropertyExclusionClassMatcher;
import net.sf.json.util.PropertyFilter;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang.StringUtils;

public class JsonConfig {
    private static final Class DEFAULT_COLLECTION_TYPE;
    private static final CycleDetectionStrategy DEFAULT_CYCLE_DETECTION_STRATEGY = CycleDetectionStrategy.STRICT;
    public static final DefaultValueProcessorMatcher DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER = DefaultValueProcessorMatcher.DEFAULT;
    private static final String[] DEFAULT_EXCLUDES = {"class", "declaringClass", "metaClass"};
    private static final JavaIdentifierTransformer DEFAULT_JAVA_IDENTIFIER_TRANSFORMER = JavaIdentifierTransformer.NOOP;
    public static final JsonBeanProcessorMatcher DEFAULT_JSON_BEAN_PROCESSOR_MATCHER = JsonBeanProcessorMatcher.DEFAULT;
    public static final JsonValueProcessorMatcher DEFAULT_JSON_VALUE_PROCESSOR_MATCHER = JsonValueProcessorMatcher.DEFAULT;
    public static final NewBeanInstanceStrategy DEFAULT_NEW_BEAN_INSTANCE_STRATEGY = NewBeanInstanceStrategy.DEFAULT;
    public static final PropertyExclusionClassMatcher DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER = PropertyExclusionClassMatcher.DEFAULT;
    public static final PropertyNameProcessorMatcher DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER = PropertyNameProcessorMatcher.DEFAULT;
    private static final DefaultValueProcessor DEFAULT_VALUE_PROCESSOR = new DefaultDefaultValueProcessor();
    private static final String[] EMPTY_EXCLUDES = new String[0];
    public static final int MODE_LIST = 1;
    public static final int MODE_OBJECT_ARRAY = 2;
    public static final int MODE_SET = 2;
    static Class class$java$util$Collection;
    static Class class$java$util$List;
    static Class class$java$util$Set;
    private int arrayMode = 1;
    private MultiKeyMap beanKeyMap = new MultiKeyMap();
    private Map beanProcessorMap = new HashMap();
    private MultiKeyMap beanTypeMap = new MultiKeyMap();
    private Map classMap;
    private Class collectionType = DEFAULT_COLLECTION_TYPE;
    private CycleDetectionStrategy cycleDetectionStrategy = DEFAULT_CYCLE_DETECTION_STRATEGY;
    private Map defaultValueMap = new HashMap();
    private DefaultValueProcessorMatcher defaultValueProcessorMatcher = DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER;
    private Class enclosedType;
    private List eventListeners = new ArrayList();
    private String[] excludes = EMPTY_EXCLUDES;
    private Map exclusionMap = new HashMap();
    private boolean handleJettisonEmptyElement;
    private boolean handleJettisonSingleElementArray;
    private boolean ignoreDefaultExcludes;
    private boolean ignoreJPATransient;
    private boolean ignoreTransientFields;
    private JavaIdentifierTransformer javaIdentifierTransformer = DEFAULT_JAVA_IDENTIFIER_TRANSFORMER;
    private PropertyFilter javaPropertyFilter;
    private Map javaPropertyNameProcessorMap = new HashMap();
    private PropertyNameProcessorMatcher javaPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
    private JsonBeanProcessorMatcher jsonBeanProcessorMatcher = DEFAULT_JSON_BEAN_PROCESSOR_MATCHER;
    private PropertyFilter jsonPropertyFilter;
    private Map jsonPropertyNameProcessorMap = new HashMap();
    private PropertyNameProcessorMatcher jsonPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
    private JsonValueProcessorMatcher jsonValueProcessorMatcher = DEFAULT_JSON_VALUE_PROCESSOR_MATCHER;
    private Map keyMap = new HashMap();
    private NewBeanInstanceStrategy newBeanInstanceStrategy = DEFAULT_NEW_BEAN_INSTANCE_STRATEGY;
    private PropertyExclusionClassMatcher propertyExclusionClassMatcher = DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER;
    private PropertySetStrategy propertySetStrategy;
    private Class rootClass;
    private boolean skipJavaIdentifierTransformationInMapKeys;
    private boolean triggerEvents;
    private Map typeMap = new HashMap();

    static {
        Class cls;
        if (class$java$util$List == null) {
            cls = class$("java.util.List");
            class$java$util$List = cls;
        } else {
            cls = class$java$util$List;
        }
        DEFAULT_COLLECTION_TYPE = cls;
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public synchronized void addJsonEventListener(JsonEventListener jsonEventListener) {
        if (!this.eventListeners.contains(jsonEventListener)) {
            this.eventListeners.add(jsonEventListener);
        }
    }

    public void clearJavaPropertyNameProcessors() {
        this.javaPropertyNameProcessorMap.clear();
    }

    public void clearJsonBeanProcessors() {
        this.beanProcessorMap.clear();
    }

    public synchronized void clearJsonEventListeners() {
        this.eventListeners.clear();
    }

    public void clearJsonPropertyNameProcessors() {
        this.jsonPropertyNameProcessorMap.clear();
    }

    public void clearJsonValueProcessors() {
        this.beanKeyMap.clear();
        this.beanTypeMap.clear();
        this.keyMap.clear();
        this.typeMap.clear();
    }

    public void clearPropertyExclusions() {
        this.exclusionMap.clear();
    }

    public void clearPropertyNameProcessors() {
        clearJavaPropertyNameProcessors();
    }

    public JsonConfig copy() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.beanKeyMap.putAll(this.beanKeyMap);
        jsonConfig.beanTypeMap.putAll(this.beanTypeMap);
        jsonConfig.classMap = new HashMap();
        if (this.classMap != null) {
            jsonConfig.classMap.putAll(this.classMap);
        }
        jsonConfig.cycleDetectionStrategy = this.cycleDetectionStrategy;
        if (this.eventListeners != null) {
            jsonConfig.eventListeners.addAll(this.eventListeners);
        }
        if (this.excludes != null) {
            jsonConfig.excludes = new String[this.excludes.length];
            System.arraycopy(this.excludes, 0, jsonConfig.excludes, 0, this.excludes.length);
        }
        jsonConfig.handleJettisonEmptyElement = this.handleJettisonEmptyElement;
        jsonConfig.handleJettisonSingleElementArray = this.handleJettisonSingleElementArray;
        jsonConfig.ignoreDefaultExcludes = this.ignoreDefaultExcludes;
        jsonConfig.ignoreTransientFields = this.ignoreTransientFields;
        jsonConfig.javaIdentifierTransformer = this.javaIdentifierTransformer;
        jsonConfig.keyMap.putAll(this.keyMap);
        jsonConfig.beanProcessorMap.putAll(this.beanProcessorMap);
        jsonConfig.rootClass = this.rootClass;
        jsonConfig.skipJavaIdentifierTransformationInMapKeys = this.skipJavaIdentifierTransformationInMapKeys;
        jsonConfig.triggerEvents = this.triggerEvents;
        jsonConfig.typeMap.putAll(this.typeMap);
        jsonConfig.jsonPropertyFilter = this.jsonPropertyFilter;
        jsonConfig.javaPropertyFilter = this.javaPropertyFilter;
        jsonConfig.jsonBeanProcessorMatcher = this.jsonBeanProcessorMatcher;
        jsonConfig.newBeanInstanceStrategy = this.newBeanInstanceStrategy;
        jsonConfig.defaultValueProcessorMatcher = this.defaultValueProcessorMatcher;
        jsonConfig.defaultValueMap.putAll(this.defaultValueMap);
        jsonConfig.propertySetStrategy = this.propertySetStrategy;
        jsonConfig.ignoreJPATransient = this.ignoreJPATransient;
        jsonConfig.collectionType = this.collectionType;
        jsonConfig.enclosedType = this.enclosedType;
        jsonConfig.jsonValueProcessorMatcher = this.jsonValueProcessorMatcher;
        jsonConfig.javaPropertyNameProcessorMatcher = this.javaPropertyNameProcessorMatcher;
        jsonConfig.javaPropertyNameProcessorMap.putAll(this.javaPropertyNameProcessorMap);
        jsonConfig.jsonPropertyNameProcessorMatcher = this.jsonPropertyNameProcessorMatcher;
        jsonConfig.jsonPropertyNameProcessorMap.putAll(this.jsonPropertyNameProcessorMap);
        jsonConfig.propertyExclusionClassMatcher = this.propertyExclusionClassMatcher;
        jsonConfig.exclusionMap.putAll(this.exclusionMap);
        return jsonConfig;
    }

    public void disableEventTriggering() {
        this.triggerEvents = false;
    }

    public void enableEventTriggering() {
        this.triggerEvents = true;
    }

    public DefaultValueProcessor findDefaultValueProcessor(Class cls) {
        if (!this.defaultValueMap.isEmpty()) {
            DefaultValueProcessor defaultValueProcessor = (DefaultValueProcessor) this.defaultValueMap.get(this.defaultValueProcessorMatcher.getMatch(cls, this.defaultValueMap.keySet()));
            if (defaultValueProcessor != null) {
                return defaultValueProcessor;
            }
        }
        return DEFAULT_VALUE_PROCESSOR;
    }

    public PropertyNameProcessor findJavaPropertyNameProcessor(Class cls) {
        if (this.javaPropertyNameProcessorMap.isEmpty()) {
            return null;
        }
        return (PropertyNameProcessor) this.javaPropertyNameProcessorMap.get(this.javaPropertyNameProcessorMatcher.getMatch(cls, this.javaPropertyNameProcessorMap.keySet()));
    }

    public JsonBeanProcessor findJsonBeanProcessor(Class cls) {
        if (this.beanProcessorMap.isEmpty()) {
            return null;
        }
        return (JsonBeanProcessor) this.beanProcessorMap.get(this.jsonBeanProcessorMatcher.getMatch(cls, this.beanProcessorMap.keySet()));
    }

    public PropertyNameProcessor findJsonPropertyNameProcessor(Class cls) {
        if (this.jsonPropertyNameProcessorMap.isEmpty()) {
            return null;
        }
        return (PropertyNameProcessor) this.jsonPropertyNameProcessorMap.get(this.jsonPropertyNameProcessorMatcher.getMatch(cls, this.jsonPropertyNameProcessorMap.keySet()));
    }

    public JsonValueProcessor findJsonValueProcessor(Class cls) {
        if (this.typeMap.isEmpty()) {
            return null;
        }
        return (JsonValueProcessor) this.typeMap.get(this.jsonValueProcessorMatcher.getMatch(cls, this.typeMap.keySet()));
    }

    public JsonValueProcessor findJsonValueProcessor(Class cls, Class cls2, String str) {
        JsonValueProcessor jsonValueProcessor = (JsonValueProcessor) this.beanKeyMap.get(cls, str);
        if (jsonValueProcessor != null) {
            return jsonValueProcessor;
        }
        JsonValueProcessor jsonValueProcessor2 = (JsonValueProcessor) this.beanTypeMap.get(cls, cls2);
        if (jsonValueProcessor2 != null) {
            return jsonValueProcessor2;
        }
        JsonValueProcessor jsonValueProcessor3 = (JsonValueProcessor) this.keyMap.get(str);
        if (jsonValueProcessor3 != null) {
            return jsonValueProcessor3;
        }
        JsonValueProcessor jsonValueProcessor4 = (JsonValueProcessor) this.typeMap.get(this.jsonValueProcessorMatcher.getMatch(cls2, this.typeMap.keySet()));
        if (jsonValueProcessor4 == null) {
            return null;
        }
        return jsonValueProcessor4;
    }

    public JsonValueProcessor findJsonValueProcessor(Class cls, String str) {
        JsonValueProcessor jsonValueProcessor = (JsonValueProcessor) this.keyMap.get(str);
        if (jsonValueProcessor != null) {
            return jsonValueProcessor;
        }
        JsonValueProcessor jsonValueProcessor2 = (JsonValueProcessor) this.typeMap.get(this.jsonValueProcessorMatcher.getMatch(cls, this.typeMap.keySet()));
        if (jsonValueProcessor2 == null) {
            return null;
        }
        return jsonValueProcessor2;
    }

    public PropertyNameProcessor findPropertyNameProcessor(Class cls) {
        return findJavaPropertyNameProcessor(cls);
    }

    public int getArrayMode() {
        return this.arrayMode;
    }

    public Map getClassMap() {
        return this.classMap;
    }

    public Class getCollectionType() {
        return this.collectionType;
    }

    public CycleDetectionStrategy getCycleDetectionStrategy() {
        return this.cycleDetectionStrategy;
    }

    public DefaultValueProcessorMatcher getDefaultValueProcessorMatcher() {
        return this.defaultValueProcessorMatcher;
    }

    public Class getEnclosedType() {
        return this.enclosedType;
    }

    public String[] getExcludes() {
        return this.excludes;
    }

    public JavaIdentifierTransformer getJavaIdentifierTransformer() {
        return this.javaIdentifierTransformer;
    }

    public PropertyFilter getJavaPropertyFilter() {
        return this.javaPropertyFilter;
    }

    public PropertyNameProcessorMatcher getJavaPropertyNameProcessorMatcher() {
        return this.javaPropertyNameProcessorMatcher;
    }

    public JsonBeanProcessorMatcher getJsonBeanProcessorMatcher() {
        return this.jsonBeanProcessorMatcher;
    }

    public synchronized List getJsonEventListeners() {
        return this.eventListeners;
    }

    public PropertyFilter getJsonPropertyFilter() {
        return this.jsonPropertyFilter;
    }

    public PropertyNameProcessorMatcher getJsonPropertyNameProcessorMatcher() {
        return this.javaPropertyNameProcessorMatcher;
    }

    public JsonValueProcessorMatcher getJsonValueProcessorMatcher() {
        return this.jsonValueProcessorMatcher;
    }

    public Collection getMergedExcludes() {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.excludes.length; i++) {
            String str = this.excludes[i];
            if (!StringUtils.isBlank(this.excludes[i])) {
                hashSet.add(str.trim());
            }
        }
        if (!this.ignoreDefaultExcludes) {
            for (int i2 = 0; i2 < DEFAULT_EXCLUDES.length; i2++) {
                if (!hashSet.contains(DEFAULT_EXCLUDES[i2])) {
                    hashSet.add(DEFAULT_EXCLUDES[i2]);
                }
            }
        }
        return hashSet;
    }

    public Collection getMergedExcludes(Class cls) {
        if (cls == null) {
            return getMergedExcludes();
        }
        Collection mergedExcludes = getMergedExcludes();
        if (!this.exclusionMap.isEmpty()) {
            Set set = (Set) this.exclusionMap.get(this.propertyExclusionClassMatcher.getMatch(cls, this.exclusionMap.keySet()));
            if (set != null && !set.isEmpty()) {
                for (Object next : set) {
                    if (!mergedExcludes.contains(next)) {
                        mergedExcludes.add(next);
                    }
                }
            }
        }
        return mergedExcludes;
    }

    public NewBeanInstanceStrategy getNewBeanInstanceStrategy() {
        return this.newBeanInstanceStrategy;
    }

    public PropertyExclusionClassMatcher getPropertyExclusionClassMatcher() {
        return this.propertyExclusionClassMatcher;
    }

    public PropertyNameProcessorMatcher getPropertyNameProcessorMatcher() {
        return getJavaPropertyNameProcessorMatcher();
    }

    public PropertySetStrategy getPropertySetStrategy() {
        return this.propertySetStrategy;
    }

    public Class getRootClass() {
        return this.rootClass;
    }

    public boolean isEventTriggeringEnabled() {
        return this.triggerEvents;
    }

    public boolean isHandleJettisonEmptyElement() {
        return this.handleJettisonEmptyElement;
    }

    public boolean isHandleJettisonSingleElementArray() {
        return this.handleJettisonSingleElementArray;
    }

    public boolean isIgnoreDefaultExcludes() {
        return this.ignoreDefaultExcludes;
    }

    public boolean isIgnoreJPATransient() {
        return this.ignoreJPATransient;
    }

    public boolean isIgnoreTransientFields() {
        return this.ignoreTransientFields;
    }

    public boolean isSkipJavaIdentifierTransformationInMapKeys() {
        return this.skipJavaIdentifierTransformationInMapKeys;
    }

    public void registerDefaultValueProcessor(Class cls, DefaultValueProcessor defaultValueProcessor) {
        if (cls != null && defaultValueProcessor != null) {
            this.defaultValueMap.put(cls, defaultValueProcessor);
        }
    }

    public void registerJavaPropertyNameProcessor(Class cls, PropertyNameProcessor propertyNameProcessor) {
        if (cls != null && propertyNameProcessor != null) {
            this.javaPropertyNameProcessorMap.put(cls, propertyNameProcessor);
        }
    }

    public void registerJsonBeanProcessor(Class cls, JsonBeanProcessor jsonBeanProcessor) {
        if (cls != null && jsonBeanProcessor != null) {
            this.beanProcessorMap.put(cls, jsonBeanProcessor);
        }
    }

    public void registerJsonPropertyNameProcessor(Class cls, PropertyNameProcessor propertyNameProcessor) {
        if (cls != null && propertyNameProcessor != null) {
            this.jsonPropertyNameProcessorMap.put(cls, propertyNameProcessor);
        }
    }

    public void registerJsonValueProcessor(Class cls, Class cls2, JsonValueProcessor jsonValueProcessor) {
        if (cls != null && cls2 != null && jsonValueProcessor != null) {
            this.beanTypeMap.put(cls, cls2, jsonValueProcessor);
        }
    }

    public void registerJsonValueProcessor(Class cls, JsonValueProcessor jsonValueProcessor) {
        if (cls != null && jsonValueProcessor != null) {
            this.typeMap.put(cls, jsonValueProcessor);
        }
    }

    public void registerJsonValueProcessor(Class cls, String str, JsonValueProcessor jsonValueProcessor) {
        if (cls != null && str != null && jsonValueProcessor != null) {
            this.beanKeyMap.put(cls, str, jsonValueProcessor);
        }
    }

    public void registerJsonValueProcessor(String str, JsonValueProcessor jsonValueProcessor) {
        if (str != null && jsonValueProcessor != null) {
            this.keyMap.put(str, jsonValueProcessor);
        }
    }

    public void registerPropertyExclusion(Class cls, String str) {
        if (cls != null && str != null) {
            Set set = (Set) this.exclusionMap.get(cls);
            if (set == null) {
                set = new HashSet();
                this.exclusionMap.put(cls, set);
            }
            if (!set.contains(str)) {
                set.add(str);
            }
        }
    }

    public void registerPropertyExclusions(Class cls, String[] strArr) {
        if (cls != null && strArr != null && strArr.length > 0) {
            Set set = (Set) this.exclusionMap.get(cls);
            if (set == null) {
                set = new HashSet();
                this.exclusionMap.put(cls, set);
            }
            for (int i = 0; i < strArr.length; i++) {
                if (!set.contains(strArr[i])) {
                    set.add(strArr[i]);
                }
            }
        }
    }

    public void registerPropertyNameProcessor(Class cls, PropertyNameProcessor propertyNameProcessor) {
        registerJavaPropertyNameProcessor(cls, propertyNameProcessor);
    }

    public synchronized void removeJsonEventListener(JsonEventListener jsonEventListener) {
        this.eventListeners.remove(jsonEventListener);
    }

    public void reset() {
        this.excludes = EMPTY_EXCLUDES;
        this.ignoreDefaultExcludes = false;
        this.ignoreTransientFields = false;
        this.javaIdentifierTransformer = DEFAULT_JAVA_IDENTIFIER_TRANSFORMER;
        this.cycleDetectionStrategy = DEFAULT_CYCLE_DETECTION_STRATEGY;
        this.skipJavaIdentifierTransformationInMapKeys = false;
        this.triggerEvents = false;
        this.handleJettisonEmptyElement = false;
        this.handleJettisonSingleElementArray = false;
        this.arrayMode = 1;
        this.rootClass = null;
        this.classMap = null;
        this.keyMap.clear();
        this.typeMap.clear();
        this.beanKeyMap.clear();
        this.beanTypeMap.clear();
        this.jsonPropertyFilter = null;
        this.javaPropertyFilter = null;
        this.jsonBeanProcessorMatcher = DEFAULT_JSON_BEAN_PROCESSOR_MATCHER;
        this.newBeanInstanceStrategy = DEFAULT_NEW_BEAN_INSTANCE_STRATEGY;
        this.defaultValueProcessorMatcher = DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER;
        this.defaultValueMap.clear();
        this.propertySetStrategy = null;
        this.ignoreJPATransient = false;
        this.collectionType = DEFAULT_COLLECTION_TYPE;
        this.enclosedType = null;
        this.jsonValueProcessorMatcher = DEFAULT_JSON_VALUE_PROCESSOR_MATCHER;
        this.javaPropertyNameProcessorMap.clear();
        this.javaPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
        this.jsonPropertyNameProcessorMap.clear();
        this.jsonPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
        this.beanProcessorMap.clear();
        this.propertyExclusionClassMatcher = DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER;
        this.exclusionMap.clear();
    }

    public void setArrayMode(int i) {
        Class cls;
        if (i == 2) {
            this.arrayMode = i;
        } else if (i == 2) {
            this.arrayMode = i;
            if (class$java$util$Set == null) {
                cls = class$("java.util.Set");
                class$java$util$Set = cls;
            } else {
                cls = class$java$util$Set;
            }
            this.collectionType = cls;
        } else {
            this.arrayMode = 1;
            this.enclosedType = DEFAULT_COLLECTION_TYPE;
        }
    }

    public void setClassMap(Map map) {
        this.classMap = map;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public void setCollectionType(java.lang.Class r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0037
            java.lang.Class r0 = class$java$util$Collection
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "java.util.Collection"
            java.lang.Class r0 = class$(r0)
            class$java$util$Collection = r0
        L_0x000e:
            boolean r0 = r0.isAssignableFrom(r4)
            if (r0 != 0) goto L_0x0034
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "The configured collectionType is not a Collection: "
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r2 = r4.getName()
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0031:
            java.lang.Class r0 = class$java$util$Collection
            goto L_0x000e
        L_0x0034:
            r3.collectionType = r4
        L_0x0036:
            return
        L_0x0037:
            java.lang.Class r0 = DEFAULT_COLLECTION_TYPE
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JsonConfig.setCollectionType(java.lang.Class):void");
    }

    public void setCycleDetectionStrategy(CycleDetectionStrategy cycleDetectionStrategy2) {
        if (cycleDetectionStrategy2 == null) {
            cycleDetectionStrategy2 = DEFAULT_CYCLE_DETECTION_STRATEGY;
        }
        this.cycleDetectionStrategy = cycleDetectionStrategy2;
    }

    public void setDefaultValueProcessorMatcher(DefaultValueProcessorMatcher defaultValueProcessorMatcher2) {
        if (defaultValueProcessorMatcher2 == null) {
            defaultValueProcessorMatcher2 = DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER;
        }
        this.defaultValueProcessorMatcher = defaultValueProcessorMatcher2;
    }

    public void setEnclosedType(Class cls) {
        this.enclosedType = cls;
    }

    public void setExcludes(String[] strArr) {
        if (strArr == null) {
            strArr = EMPTY_EXCLUDES;
        }
        this.excludes = strArr;
    }

    public void setHandleJettisonEmptyElement(boolean z) {
        this.handleJettisonEmptyElement = z;
    }

    public void setHandleJettisonSingleElementArray(boolean z) {
        this.handleJettisonSingleElementArray = z;
    }

    public void setIgnoreDefaultExcludes(boolean z) {
        this.ignoreDefaultExcludes = z;
    }

    public void setIgnoreJPATransient(boolean z) {
        this.ignoreJPATransient = z;
    }

    public void setIgnoreTransientFields(boolean z) {
        this.ignoreTransientFields = z;
    }

    public void setJavaIdentifierTransformer(JavaIdentifierTransformer javaIdentifierTransformer2) {
        if (javaIdentifierTransformer2 == null) {
            javaIdentifierTransformer2 = DEFAULT_JAVA_IDENTIFIER_TRANSFORMER;
        }
        this.javaIdentifierTransformer = javaIdentifierTransformer2;
    }

    public void setJavaPropertyFilter(PropertyFilter propertyFilter) {
        this.javaPropertyFilter = propertyFilter;
    }

    public void setJavaPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher) {
        if (propertyNameProcessorMatcher == null) {
            propertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
        }
        this.javaPropertyNameProcessorMatcher = propertyNameProcessorMatcher;
    }

    public void setJsonBeanProcessorMatcher(JsonBeanProcessorMatcher jsonBeanProcessorMatcher2) {
        if (jsonBeanProcessorMatcher2 == null) {
            jsonBeanProcessorMatcher2 = DEFAULT_JSON_BEAN_PROCESSOR_MATCHER;
        }
        this.jsonBeanProcessorMatcher = jsonBeanProcessorMatcher2;
    }

    public void setJsonPropertyFilter(PropertyFilter propertyFilter) {
        this.jsonPropertyFilter = propertyFilter;
    }

    public void setJsonPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher) {
        if (propertyNameProcessorMatcher == null) {
            propertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
        }
        this.jsonPropertyNameProcessorMatcher = propertyNameProcessorMatcher;
    }

    public void setJsonValueProcessorMatcher(JsonValueProcessorMatcher jsonValueProcessorMatcher2) {
        if (jsonValueProcessorMatcher2 == null) {
            jsonValueProcessorMatcher2 = DEFAULT_JSON_VALUE_PROCESSOR_MATCHER;
        }
        this.jsonValueProcessorMatcher = jsonValueProcessorMatcher2;
    }

    public void setNewBeanInstanceStrategy(NewBeanInstanceStrategy newBeanInstanceStrategy2) {
        if (newBeanInstanceStrategy2 == null) {
            newBeanInstanceStrategy2 = DEFAULT_NEW_BEAN_INSTANCE_STRATEGY;
        }
        this.newBeanInstanceStrategy = newBeanInstanceStrategy2;
    }

    public void setPropertyExclusionClassMatcher(PropertyExclusionClassMatcher propertyExclusionClassMatcher2) {
        if (propertyExclusionClassMatcher2 == null) {
            propertyExclusionClassMatcher2 = DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER;
        }
        this.propertyExclusionClassMatcher = propertyExclusionClassMatcher2;
    }

    public void setPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher) {
        setJavaPropertyNameProcessorMatcher(propertyNameProcessorMatcher);
    }

    public void setPropertySetStrategy(PropertySetStrategy propertySetStrategy2) {
        this.propertySetStrategy = propertySetStrategy2;
    }

    public void setRootClass(Class cls) {
        this.rootClass = cls;
    }

    public void setSkipJavaIdentifierTransformationInMapKeys(boolean z) {
        this.skipJavaIdentifierTransformationInMapKeys = z;
    }

    public void unregisterDefaultValueProcessor(Class cls) {
        if (cls != null) {
            this.defaultValueMap.remove(cls);
        }
    }

    public void unregisterJavaPropertyNameProcessor(Class cls) {
        if (cls != null) {
            this.javaPropertyNameProcessorMap.remove(cls);
        }
    }

    public void unregisterJsonBeanProcessor(Class cls) {
        if (cls != null) {
            this.beanProcessorMap.remove(cls);
        }
    }

    public void unregisterJsonPropertyNameProcessor(Class cls) {
        if (cls != null) {
            this.jsonPropertyNameProcessorMap.remove(cls);
        }
    }

    public void unregisterJsonValueProcessor(Class cls) {
        if (cls != null) {
            this.typeMap.remove(cls);
        }
    }

    public void unregisterJsonValueProcessor(Class cls, Class cls2) {
        if (cls != null && cls2 != null) {
            this.beanTypeMap.remove(cls, cls2);
        }
    }

    public void unregisterJsonValueProcessor(Class cls, String str) {
        if (cls != null && str != null) {
            this.beanKeyMap.remove(cls, str);
        }
    }

    public void unregisterJsonValueProcessor(String str) {
        if (str != null) {
            this.keyMap.remove(str);
        }
    }

    public void unregisterPropertyExclusion(Class cls, String str) {
        if (cls != null && str != null) {
            Set set = (Set) this.exclusionMap.get(cls);
            if (set == null) {
                set = new HashSet();
                this.exclusionMap.put(cls, set);
            }
            set.remove(str);
        }
    }

    public void unregisterPropertyExclusions(Class cls) {
        if (cls != null) {
            Set set = (Set) this.exclusionMap.get(cls);
            if (set != null) {
                set.clear();
            }
        }
    }

    public void unregisterPropertyNameProcessor(Class cls) {
        unregisterJavaPropertyNameProcessor(cls);
    }
}
