package net.sf.json.groovy;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/* compiled from: GJson.groovy */
public class GJson implements GroovyObject {
    public static Long __timeStamp = new Long(1228798721211L);
    public static Long __timeStamp__239_neverHappen1228798721211 = new Long(0);
    static /* synthetic */ Class class$0 = class$("net.sf.json.groovy.GJson");
    static /* synthetic */ Class class$groovy$lang$ExpandoMetaClass;
    static /* synthetic */ Class class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$util$ArrayList;
    static /* synthetic */ Class class$java$util$Collection;
    static /* synthetic */ Class class$java$util$HashSet;
    static /* synthetic */ Class class$java$util$Map;
    static /* synthetic */ Class class$net$sf$json$JSONObject;
    static /* synthetic */ Class class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter;
    transient MetaClass metaClass;

    /* compiled from: GJson.groovy */
    class _enhanceCollection_closure4 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure4");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            Class cls;
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure4");
            } else {
                Class cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            if (class$net$sf$json$groovy$GJson == null) {
                cls = class$("net.sf.json.groovy.GJson");
                class$net$sf$json$groovy$GJson = cls;
            } else {
                cls = class$net$sf$json$groovy$GJson;
            }
            return ScriptBytecodeAdapter.compareEqual(ScriptBytecodeAdapter.getProperty(cls, obj, "name"), "isJsonEnhanced") ? Boolean.TRUE : Boolean.FALSE;
        }

        public _enhanceCollection_closure4(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure4");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceCollection_closure5 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$JSONArray;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;
        private Reference asType;

        public _enhanceCollection_closure5(Object obj, Object obj2, Reference reference) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure5");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
            this.asType = reference;
        }

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object call(Class cls) {
            Class cls2;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure5");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls2, this, "doCall", new Object[]{reference.get()});
        }

        public Object getAsType() {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure5");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return this.asType.get();
        }

        public Object doCall(Class cls) {
            Class cls2;
            Class cls3;
            Class cls4;
            Class cls5;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure5");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls6 = class$groovy$lang$MetaClass;
            }
            Object obj = reference.get();
            if (class$net$sf$json$JSONArray == null) {
                cls3 = class$("net.sf.json.JSONArray");
                class$net$sf$json$JSONArray = cls3;
            } else {
                cls3 = class$net$sf$json$JSONArray;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls3)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls4 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls4;
                } else {
                    cls4 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONArray == null) {
                    cls5 = class$("net.sf.json.JSONArray");
                    class$net$sf$json$JSONArray = cls5;
                } else {
                    cls5 = class$net$sf$json$JSONArray;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls4, cls5, "fromObject", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            return ScriptBytecodeAdapter.invokeClosure(this.asType.get(), new Object[]{reference.get()});
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceCollection_closure6 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure6");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure6");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return Boolean.TRUE;
        }

        public _enhanceCollection_closure6(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceCollection_closure6");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceJSONObject_closure10 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure10");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            Class cls;
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure10");
            } else {
                Class cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            if (class$net$sf$json$groovy$GJson == null) {
                cls = class$("net.sf.json.groovy.GJson");
                class$net$sf$json$groovy$GJson = cls;
            } else {
                cls = class$net$sf$json$groovy$GJson;
            }
            return ScriptBytecodeAdapter.compareEqual(ScriptBytecodeAdapter.getProperty(cls, obj, "name"), "isJsonEnhanced") ? Boolean.TRUE : Boolean.FALSE;
        }

        public _enhanceJSONObject_closure10(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure10");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceJSONObject_closure11 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public _enhanceJSONObject_closure11(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure11");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x0088  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doCall(java.lang.Object r12) {
            /*
                r11 = this;
                r7 = 2
                r10 = 1
                r9 = 0
                groovy.lang.Reference r2 = new groovy.lang.Reference
                r2.<init>(r12)
                java.lang.Class r0 = class$0
                if (r0 != 0) goto L_0x004e
                java.lang.String r0 = "net.sf.json.groovy.GJson$_enhanceJSONObject_closure11"
                java.lang.Class r0 = class$(r0)
                class$0 = r0
            L_0x0014:
                java.lang.Class r1 = class$groovy$lang$MetaClass
                if (r1 != 0) goto L_0x0051
                java.lang.String r1 = "groovy.lang.MetaClass"
                java.lang.Class r1 = class$(r1)
                class$groovy$lang$MetaClass = r1
            L_0x0020:
                java.lang.Object r1 = r2.get()
                boolean r1 = r1 instanceof java.util.Map
                if (r1 == 0) goto L_0x0057
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x0054
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x0034:
                java.lang.String r3 = "delegate"
                java.lang.Object r3 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.getProperty(r0, r11, r3)
                java.lang.String r4 = "putAll"
                java.lang.Object[] r5 = new java.lang.Object[r10]
                java.lang.Object r2 = r2.get()
                r5[r9] = r2
                org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethodN(r1, r3, r4, r5)
                java.lang.String r1 = "delegate"
                org.codehaus.groovy.runtime.ScriptBytecodeAdapter.getProperty(r0, r11, r1)
            L_0x004c:
                r0 = 0
                return r0
            L_0x004e:
                java.lang.Class r0 = class$0
                goto L_0x0014
            L_0x0051:
                java.lang.Class r1 = class$groovy$lang$MetaClass
                goto L_0x0020
            L_0x0054:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x0034
            L_0x0057:
                java.lang.Object r1 = r2.get()
                boolean r1 = r1 instanceof java.util.List
                if (r1 == 0) goto L_0x0113
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x010f
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x006b:
                java.lang.Object r3 = r2.get()
                java.lang.String r4 = "size"
                java.lang.Object r1 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethod0(r1, r3, r4)
                java.lang.Integer r3 = new java.lang.Integer
                r3.<init>(r7)
                boolean r1 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.compareGreaterThanEqual(r1, r3)
                if (r1 == 0) goto L_0x0113
                java.lang.Boolean r1 = java.lang.Boolean.TRUE
            L_0x0082:
                boolean r1 = org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation.booleanUnbox(r1)
                if (r1 == 0) goto L_0x004c
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x0117
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x0094:
                java.lang.Object r3 = r2.get()
                java.lang.String r4 = "remove"
                java.lang.Object[] r5 = new java.lang.Object[r10]
                java.lang.Integer r6 = new java.lang.Integer
                r6.<init>(r9)
                r5[r9] = r6
                java.lang.Object r1 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethodN(r1, r3, r4, r5)
                groovy.lang.Reference r3 = new groovy.lang.Reference
                r3.<init>(r1)
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x011b
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x00b8:
                java.lang.Object r4 = r2.get()
                java.lang.String r5 = "size"
                java.lang.Object r1 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethod0(r1, r4, r5)
                java.lang.Integer r4 = new java.lang.Integer
                r4.<init>(r10)
                boolean r1 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.compareEqual(r1, r4)
                if (r1 == 0) goto L_0x0124
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x011e
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x00d9:
                java.lang.String r4 = "delegate"
                java.lang.Object r4 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.getProperty(r0, r11, r4)
                java.lang.String r5 = "element"
                java.lang.Object[] r6 = new java.lang.Object[r7]
                java.lang.Object r0 = r3.get()
                r6[r9] = r0
                java.lang.Class r0 = class$net$sf$json$groovy$GJson
                if (r0 != 0) goto L_0x0121
                java.lang.String r0 = "net.sf.json.groovy.GJson"
                java.lang.Class r0 = class$(r0)
                class$net$sf$json$groovy$GJson = r0
            L_0x00f5:
                java.lang.Object r2 = r2.get()
                java.lang.String r3 = "get"
                java.lang.Object[] r7 = new java.lang.Object[r10]
                java.lang.Integer r8 = new java.lang.Integer
                r8.<init>(r9)
                r7[r9] = r8
                java.lang.Object r0 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethodN(r0, r2, r3, r7)
                r6[r10] = r0
                org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethodN(r1, r4, r5, r6)
                goto L_0x004c
            L_0x010f:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x006b
            L_0x0113:
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                goto L_0x0082
            L_0x0117:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x0094
            L_0x011b:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x00b8
            L_0x011e:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x00d9
            L_0x0121:
                java.lang.Class r0 = class$net$sf$json$groovy$GJson
                goto L_0x00f5
            L_0x0124:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                if (r1 != 0) goto L_0x014b
                java.lang.String r1 = "net.sf.json.groovy.GJson"
                java.lang.Class r1 = class$(r1)
                class$net$sf$json$groovy$GJson = r1
            L_0x0130:
                java.lang.String r4 = "delegate"
                java.lang.Object r0 = org.codehaus.groovy.runtime.ScriptBytecodeAdapter.getProperty(r0, r11, r4)
                java.lang.String r4 = "element"
                java.lang.Object[] r5 = new java.lang.Object[r7]
                java.lang.Object r3 = r3.get()
                r5[r9] = r3
                java.lang.Object r2 = r2.get()
                r5[r10] = r2
                org.codehaus.groovy.runtime.ScriptBytecodeAdapter.invokeMethodN(r1, r0, r4, r5)
                goto L_0x004c
            L_0x014b:
                java.lang.Class r1 = class$net$sf$json$groovy$GJson
                goto L_0x0130
            */
            throw new UnsupportedOperationException("Method not decompiled: net.sf.json.groovy.GJson._enhanceJSONObject_closure11.doCall(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceJSONObject_closure12 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object call(String str, Object obj) {
            Class cls;
            Reference reference = new Reference(str);
            Reference reference2 = new Reference(obj);
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure12");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{reference.get(), reference2.get()});
        }

        public _enhanceJSONObject_closure12(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure12");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }

        public Object doCall(String str, Object obj) {
            Class cls;
            Class cls2;
            boolean z;
            Class cls3;
            Class cls4;
            Reference reference = new Reference(str);
            Reference reference2 = new Reference(obj);
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure12");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls5 = class$groovy$lang$MetaClass;
            }
            if (class$net$sf$json$groovy$GJson == null) {
                cls2 = class$("net.sf.json.groovy.GJson");
                class$net$sf$json$groovy$GJson = cls2;
            } else {
                cls2 = class$net$sf$json$groovy$GJson;
            }
            Reference reference3 = new Reference(ScriptBytecodeAdapter.invokeMethodN(cls2, ScriptBytecodeAdapter.getProperty(cls, this, "delegate"), "opt", new Object[]{reference.get()}));
            if (!DefaultTypeTransformation.booleanUnbox(reference3.get())) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls3 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls3;
                } else {
                    cls3 = class$net$sf$json$groovy$GJson;
                }
                ScriptBytecodeAdapter.invokeMethodN(cls3, ScriptBytecodeAdapter.getProperty(cls, this, "delegate"), "element", new Object[]{reference.get(), reference2.get()});
                if (class$net$sf$json$groovy$GJson == null) {
                    cls4 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls4;
                } else {
                    cls4 = class$net$sf$json$groovy$GJson;
                }
                reference3.set(ScriptBytecodeAdapter.invokeMethodN(cls4, ScriptBytecodeAdapter.getProperty(cls, this, "delegate"), "get", new Object[]{reference.get()}));
            }
            return reference3.get();
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceJSONObject_closure13 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure13");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure13");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return Boolean.TRUE;
        }

        public _enhanceJSONObject_closure13(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceJSONObject_closure13");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceMap_closure7 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceMap_closure7");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            Class cls;
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure7");
            } else {
                Class cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            if (class$net$sf$json$groovy$GJson == null) {
                cls = class$("net.sf.json.groovy.GJson");
                class$net$sf$json$groovy$GJson = cls;
            } else {
                cls = class$net$sf$json$groovy$GJson;
            }
            return ScriptBytecodeAdapter.compareEqual(ScriptBytecodeAdapter.getProperty(cls, obj, "name"), "isJsonEnhanced") ? Boolean.TRUE : Boolean.FALSE;
        }

        public _enhanceMap_closure7(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure7");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceMap_closure8 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$JSONObject;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;
        private Reference asType;

        public _enhanceMap_closure8(Object obj, Object obj2, Reference reference) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure8");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
            this.asType = reference;
        }

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object call(Class cls) {
            Class cls2;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure8");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls2, this, "doCall", new Object[]{reference.get()});
        }

        public Object getAsType() {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure8");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return this.asType.get();
        }

        public Object doCall(Class cls) {
            Class cls2;
            Class cls3;
            Class cls4;
            Class cls5;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure8");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls6 = class$groovy$lang$MetaClass;
            }
            Object obj = reference.get();
            if (class$net$sf$json$JSONObject == null) {
                cls3 = class$("net.sf.json.JSONObject");
                class$net$sf$json$JSONObject = cls3;
            } else {
                cls3 = class$net$sf$json$JSONObject;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls3)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls4 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls4;
                } else {
                    cls4 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONObject == null) {
                    cls5 = class$("net.sf.json.JSONObject");
                    class$net$sf$json$JSONObject = cls5;
                } else {
                    cls5 = class$net$sf$json$JSONObject;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls4, cls5, "fromObject", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            return ScriptBytecodeAdapter.invokeClosure(this.asType.get(), new Object[]{reference.get()});
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceMap_closure9 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceMap_closure9");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure9");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return Boolean.TRUE;
        }

        public _enhanceMap_closure9(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceMap_closure9");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceString_closure1 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceString_closure1");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            Class cls;
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure1");
            } else {
                Class cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            if (class$net$sf$json$groovy$GJson == null) {
                cls = class$("net.sf.json.groovy.GJson");
                class$net$sf$json$groovy$GJson = cls;
            } else {
                cls = class$net$sf$json$groovy$GJson;
            }
            return ScriptBytecodeAdapter.compareEqual(ScriptBytecodeAdapter.getProperty(cls, obj, "name"), "isJsonEnhanced") ? Boolean.TRUE : Boolean.FALSE;
        }

        public _enhanceString_closure1(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure1");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceString_closure2 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;
        static /* synthetic */ Class class$net$sf$json$JSON;
        static /* synthetic */ Class class$net$sf$json$JSONArray;
        static /* synthetic */ Class class$net$sf$json$JSONFunction;
        static /* synthetic */ Class class$net$sf$json$JSONObject;
        static /* synthetic */ Class class$net$sf$json$JSONSerializer;
        static /* synthetic */ Class class$net$sf$json$groovy$GJson;
        private Reference asType;

        public _enhanceString_closure2(Object obj, Object obj2, Reference reference) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure2");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
            this.asType = reference;
        }

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object call(Class cls) {
            Class cls2;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceString_closure2");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls3 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls2, this, "doCall", new Object[]{reference.get()});
        }

        public Object getAsType() {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure2");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return this.asType.get();
        }

        public Object doCall(Class cls) {
            Class cls2;
            Class cls3;
            Class cls4;
            Class cls5;
            Class cls6;
            Class cls7;
            Class cls8;
            Class cls9;
            Class cls10;
            Class cls11;
            Class cls12;
            Class cls13;
            Class cls14;
            Reference reference = new Reference(cls);
            if (class$0 == null) {
                cls2 = class$("net.sf.json.groovy.GJson$_enhanceString_closure2");
                class$0 = cls2;
            } else {
                cls2 = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls15 = class$groovy$lang$MetaClass;
            }
            Object obj = reference.get();
            if (class$net$sf$json$JSON == null) {
                cls3 = class$("net.sf.json.JSON");
                class$net$sf$json$JSON = cls3;
            } else {
                cls3 = class$net$sf$json$JSON;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls3)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls13 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls13;
                } else {
                    cls13 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONSerializer == null) {
                    cls14 = class$("net.sf.json.JSONSerializer");
                    class$net$sf$json$JSONSerializer = cls14;
                } else {
                    cls14 = class$net$sf$json$JSONSerializer;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls13, cls14, "toJSON", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            if (class$net$sf$json$JSONArray == null) {
                cls4 = class$("net.sf.json.JSONArray");
                class$net$sf$json$JSONArray = cls4;
            } else {
                cls4 = class$net$sf$json$JSONArray;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls4)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls11 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls11;
                } else {
                    cls11 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONArray == null) {
                    cls12 = class$("net.sf.json.JSONArray");
                    class$net$sf$json$JSONArray = cls12;
                } else {
                    cls12 = class$net$sf$json$JSONArray;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls11, cls12, "fromObject", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            if (class$net$sf$json$JSONObject == null) {
                cls5 = class$("net.sf.json.JSONObject");
                class$net$sf$json$JSONObject = cls5;
            } else {
                cls5 = class$net$sf$json$JSONObject;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls5)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls9 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls9;
                } else {
                    cls9 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONObject == null) {
                    cls10 = class$("net.sf.json.JSONObject");
                    class$net$sf$json$JSONObject = cls10;
                } else {
                    cls10 = class$net$sf$json$JSONObject;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls9, cls10, "fromObject", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            if (class$net$sf$json$JSONFunction == null) {
                cls6 = class$("net.sf.json.JSONFunction");
                class$net$sf$json$JSONFunction = cls6;
            } else {
                cls6 = class$net$sf$json$JSONFunction;
            }
            if (ScriptBytecodeAdapter.isCase(obj, cls6)) {
                if (class$net$sf$json$groovy$GJson == null) {
                    cls7 = class$("net.sf.json.groovy.GJson");
                    class$net$sf$json$groovy$GJson = cls7;
                } else {
                    cls7 = class$net$sf$json$groovy$GJson;
                }
                if (class$net$sf$json$JSONFunction == null) {
                    cls8 = class$("net.sf.json.JSONFunction");
                    class$net$sf$json$JSONFunction = cls8;
                } else {
                    cls8 = class$net$sf$json$JSONFunction;
                }
                return ScriptBytecodeAdapter.invokeMethodN(cls7, cls8, "parse", new Object[]{ScriptBytecodeAdapter.getProperty(cls2, this, "delegate")});
            }
            return ScriptBytecodeAdapter.invokeClosure(this.asType.get(), new Object[]{reference.get()});
        }
    }

    /* compiled from: GJson.groovy */
    class _enhanceString_closure3 extends Closure implements GeneratedClosure {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$groovy$lang$MetaClass;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }

        public Object doCall() {
            Class cls;
            if (class$0 == null) {
                cls = class$("net.sf.json.groovy.GJson$_enhanceString_closure3");
                class$0 = cls;
            } else {
                cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(cls, this, "doCall", new Object[]{null});
        }

        public Object doCall(Object obj) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure3");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            return Boolean.TRUE;
        }

        public _enhanceString_closure3(Object obj, Object obj2) {
            if (class$0 == null) {
                class$0 = class$("net.sf.json.groovy.GJson$_enhanceString_closure3");
            } else {
                Class cls = class$0;
            }
            if (class$groovy$lang$MetaClass == null) {
                class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
            } else {
                Class cls2 = class$groovy$lang$MetaClass;
            }
            super(obj, obj2);
        }
    }

    static {
        if (class$0 == null) {
        } else {
            Class cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
        } else {
            Class cls2 = class$groovy$lang$MetaClass;
        }
    }

    public GJson() {
        Class cls;
        Class cls2;
        Class cls3;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            cls2 = class$("groovy.lang.MetaClass");
            class$groovy$lang$MetaClass = cls2;
        } else {
            cls2 = class$groovy$lang$MetaClass;
        }
        if (class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter == null) {
            cls3 = class$("org.codehaus.groovy.runtime.ScriptBytecodeAdapter");
            class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter = cls3;
        } else {
            cls3 = class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter;
        }
        this.metaClass = (MetaClass) ScriptBytecodeAdapter.castToType((MetaClass) ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeStaticMethodN(cls, cls3, "initMetaClass", new Object[]{this}), cls2), cls2);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public MetaClass getMetaClass() {
        Class cls;
        Class cls2;
        Class cls3;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            cls2 = class$("groovy.lang.MetaClass");
            class$groovy$lang$MetaClass = cls2;
        } else {
            cls2 = class$groovy$lang$MetaClass;
        }
        if (ScriptBytecodeAdapter.compareEqual(this.metaClass, null)) {
            if (class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter == null) {
                cls3 = class$("org.codehaus.groovy.runtime.ScriptBytecodeAdapter");
                class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter = cls3;
            } else {
                cls3 = class$org$codehaus$groovy$runtime$ScriptBytecodeAdapter;
            }
            this.metaClass = (MetaClass) ScriptBytecodeAdapter.castToType((MetaClass) ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.invokeStaticMethodN(cls, cls3, "initMetaClass", new Object[]{this}), cls2), cls2);
        }
        return (MetaClass) ScriptBytecodeAdapter.castToType(this.metaClass, cls2);
    }

    public Object getProperty(String str) {
        if (class$0 == null) {
            class$0 = class$("net.sf.json.groovy.GJson");
        } else {
            Class cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls2 = class$groovy$lang$MetaClass;
        }
        return getMetaClass().getProperty(this, str);
    }

    public Object invokeMethod(String str, Object obj) {
        if (class$0 == null) {
            class$0 = class$("net.sf.json.groovy.GJson");
        } else {
            Class cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls2 = class$groovy$lang$MetaClass;
        }
        return getMetaClass().invokeMethod(this, str, obj);
    }

    public void setMetaClass(MetaClass metaClass2) {
        if (class$0 == null) {
            class$0 = class$("net.sf.json.groovy.GJson");
        } else {
            Class cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls2 = class$groovy$lang$MetaClass;
        }
        this.metaClass = metaClass2;
    }

    public void setProperty(String str, Object obj) {
        if (class$0 == null) {
            class$0 = class$("net.sf.json.groovy.GJson");
        } else {
            Class cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls2 = class$groovy$lang$MetaClass;
        }
        getMetaClass().setProperty(this, str, obj);
    }

    /* access modifiers changed from: 0000 */
    public Object super$1$clone() {
        return clone();
    }

    /* access modifiers changed from: 0000 */
    public boolean super$1$equals(Object obj) {
        return equals(obj);
    }

    /* access modifiers changed from: 0000 */
    public void super$1$finalize() {
        finalize();
    }

    /* access modifiers changed from: 0000 */
    public Class super$1$getClass() {
        return getClass();
    }

    /* access modifiers changed from: 0000 */
    public int super$1$hashCode() {
        return hashCode();
    }

    /* access modifiers changed from: 0000 */
    public void super$1$notify() {
        notify();
    }

    /* access modifiers changed from: 0000 */
    public void super$1$notifyAll() {
        notifyAll();
    }

    /* access modifiers changed from: 0000 */
    public String super$1$toString() {
        return toString();
    }

    /* access modifiers changed from: 0000 */
    public void super$1$wait() {
        wait();
    }

    /* access modifiers changed from: 0000 */
    public void super$1$wait(long j) {
        wait(j);
    }

    /* access modifiers changed from: 0000 */
    public void super$1$wait(long j, int i) {
        wait(j, i);
    }

    /* access modifiers changed from: 0000 */
    public boolean this$2$enhanceCollection() {
        return enhanceCollection();
    }

    /* access modifiers changed from: 0000 */
    public boolean this$2$enhanceJSONObject() {
        return enhanceJSONObject();
    }

    /* access modifiers changed from: 0000 */
    public boolean this$2$enhanceMap() {
        return enhanceMap();
    }

    /* access modifiers changed from: 0000 */
    public boolean this$2$enhanceString() {
        return enhanceString();
    }

    public static void enhanceClasses() {
        Class cls;
        Class cls2;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls3 = class$groovy$lang$MetaClass;
        }
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeStaticMethod0(cls, cls, "enhanceString")) && !DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeStaticMethod0(cls, cls, "enhanceCollection"))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeStaticMethod0(cls, cls, "enhanceMap"))) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeStaticMethod0(cls, cls, "enhanceJSONObject"))) ? Boolean.FALSE : Boolean.TRUE)) {
            if (class$groovy$lang$ExpandoMetaClass == null) {
                cls2 = class$("groovy.lang.ExpandoMetaClass");
                class$groovy$lang$ExpandoMetaClass = cls2;
            } else {
                cls2 = class$groovy$lang$ExpandoMetaClass;
            }
            ScriptBytecodeAdapter.invokeMethod0(cls, cls2, "enableGlobally");
        }
    }

    private static boolean enhanceString() {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls8 = class$groovy$lang$MetaClass;
        }
        if (class$java$lang$String == null) {
            cls2 = class$("java.lang.String");
            class$java$lang$String = cls2;
        } else {
            cls2 = class$java$lang$String;
        }
        if (!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeMethodN(cls, ScriptBytecodeAdapter.getProperty(cls, ScriptBytecodeAdapter.getProperty(cls, cls2, "metaClass"), "methods"), "find", new Object[]{new _enhanceString_closure1(cls, cls)}))) {
            if (class$java$lang$String == null) {
                cls4 = class$("java.lang.String");
                class$java$lang$String = cls4;
            } else {
                cls4 = class$java$lang$String;
            }
            _enhanceString_closure2 _enhancestring_closure2 = new _enhanceString_closure2(cls, cls, new Reference(ScriptBytecodeAdapter.getMethodPointer(ScriptBytecodeAdapter.getProperty(cls, cls4, "metaClass"), "asType")));
            if (class$java$lang$String == null) {
                cls5 = class$("java.lang.String");
                class$java$lang$String = cls5;
            } else {
                cls5 = class$java$lang$String;
            }
            ScriptBytecodeAdapter.setProperty(_enhancestring_closure2, cls, ScriptBytecodeAdapter.getProperty(cls, cls5, "metaClass"), "asType");
            _enhanceString_closure3 _enhancestring_closure3 = new _enhanceString_closure3(cls, cls);
            if (class$java$lang$String == null) {
                cls6 = class$("java.lang.String");
                class$java$lang$String = cls6;
            } else {
                cls6 = class$java$lang$String;
            }
            ScriptBytecodeAdapter.setProperty(_enhancestring_closure3, cls, ScriptBytecodeAdapter.getProperty(cls, cls6, "metaClass"), "isJsonEnhanced");
            Boolean bool = Boolean.TRUE;
            if (class$java$lang$Boolean == null) {
                cls7 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls7;
            } else {
                cls7 = class$java$lang$Boolean;
            }
            return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool, cls7));
        }
        Boolean bool2 = Boolean.FALSE;
        if (class$java$lang$Boolean == null) {
            cls3 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls3;
        } else {
            cls3 = class$java$lang$Boolean;
        }
        return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool2, cls3));
    }

    private static boolean enhanceCollection() {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        Class cls8;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls9 = class$groovy$lang$MetaClass;
        }
        if (class$java$util$Collection == null) {
            cls2 = class$("java.util.Collection");
            class$java$util$Collection = cls2;
        } else {
            cls2 = class$java$util$Collection;
        }
        if (!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeMethodN(cls, ScriptBytecodeAdapter.getProperty(cls, ScriptBytecodeAdapter.getProperty(cls, cls2, "metaClass"), "methods"), "find", new Object[]{new _enhanceCollection_closure4(cls, cls)}))) {
            if (class$java$util$Collection == null) {
                cls4 = class$("java.util.Collection");
                class$java$util$Collection = cls4;
            } else {
                cls4 = class$java$util$Collection;
            }
            _enhanceCollection_closure5 _enhancecollection_closure5 = new _enhanceCollection_closure5(cls, cls, new Reference(ScriptBytecodeAdapter.getMethodPointer(ScriptBytecodeAdapter.getProperty(cls, cls4, "metaClass"), "asType")));
            if (class$java$util$ArrayList == null) {
                cls5 = class$("java.util.ArrayList");
                class$java$util$ArrayList = cls5;
            } else {
                cls5 = class$java$util$ArrayList;
            }
            ScriptBytecodeAdapter.setProperty(_enhancecollection_closure5, cls, ScriptBytecodeAdapter.getProperty(cls, cls5, "metaClass"), "asType");
            if (class$java$util$HashSet == null) {
                cls6 = class$("java.util.HashSet");
                class$java$util$HashSet = cls6;
            } else {
                cls6 = class$java$util$HashSet;
            }
            ScriptBytecodeAdapter.setProperty(_enhancecollection_closure5, cls, ScriptBytecodeAdapter.getProperty(cls, cls6, "metaClass"), "asType");
            _enhanceCollection_closure6 _enhancecollection_closure6 = new _enhanceCollection_closure6(cls, cls);
            if (class$java$util$Collection == null) {
                cls7 = class$("java.util.Collection");
                class$java$util$Collection = cls7;
            } else {
                cls7 = class$java$util$Collection;
            }
            ScriptBytecodeAdapter.setProperty(_enhancecollection_closure6, cls, ScriptBytecodeAdapter.getProperty(cls, cls7, "metaClass"), "isJsonEnhanced");
            Boolean bool = Boolean.TRUE;
            if (class$java$lang$Boolean == null) {
                cls8 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls8;
            } else {
                cls8 = class$java$lang$Boolean;
            }
            return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool, cls8));
        }
        Boolean bool2 = Boolean.FALSE;
        if (class$java$lang$Boolean == null) {
            cls3 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls3;
        } else {
            cls3 = class$java$lang$Boolean;
        }
        return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool2, cls3));
    }

    private static boolean enhanceMap() {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls8 = class$groovy$lang$MetaClass;
        }
        if (class$java$util$Map == null) {
            cls2 = class$("java.util.Map");
            class$java$util$Map = cls2;
        } else {
            cls2 = class$java$util$Map;
        }
        if (!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeMethodN(cls, ScriptBytecodeAdapter.getProperty(cls, ScriptBytecodeAdapter.getProperty(cls, cls2, "metaClass"), "methods"), "find", new Object[]{new _enhanceMap_closure7(cls, cls)}))) {
            if (class$java$util$Map == null) {
                cls4 = class$("java.util.Map");
                class$java$util$Map = cls4;
            } else {
                cls4 = class$java$util$Map;
            }
            _enhanceMap_closure8 _enhancemap_closure8 = new _enhanceMap_closure8(cls, cls, new Reference(ScriptBytecodeAdapter.getMethodPointer(ScriptBytecodeAdapter.getProperty(cls, cls4, "metaClass"), "asType")));
            if (class$java$util$Map == null) {
                cls5 = class$("java.util.Map");
                class$java$util$Map = cls5;
            } else {
                cls5 = class$java$util$Map;
            }
            ScriptBytecodeAdapter.setProperty(_enhancemap_closure8, cls, ScriptBytecodeAdapter.getProperty(cls, cls5, "metaClass"), "asType");
            _enhanceMap_closure9 _enhancemap_closure9 = new _enhanceMap_closure9(cls, cls);
            if (class$java$util$Map == null) {
                cls6 = class$("java.util.Map");
                class$java$util$Map = cls6;
            } else {
                cls6 = class$java$util$Map;
            }
            ScriptBytecodeAdapter.setProperty(_enhancemap_closure9, cls, ScriptBytecodeAdapter.getProperty(cls, cls6, "metaClass"), "isJsonEnhanced");
            Boolean bool = Boolean.TRUE;
            if (class$java$lang$Boolean == null) {
                cls7 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls7;
            } else {
                cls7 = class$java$lang$Boolean;
            }
            return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool, cls7));
        }
        Boolean bool2 = Boolean.FALSE;
        if (class$java$lang$Boolean == null) {
            cls3 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls3;
        } else {
            cls3 = class$java$lang$Boolean;
        }
        return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool2, cls3));
    }

    private static boolean enhanceJSONObject() {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        if (class$0 == null) {
            cls = class$("net.sf.json.groovy.GJson");
            class$0 = cls;
        } else {
            cls = class$0;
        }
        if (class$groovy$lang$MetaClass == null) {
            class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass");
        } else {
            Class cls8 = class$groovy$lang$MetaClass;
        }
        if (class$net$sf$json$JSONObject == null) {
            cls2 = class$("net.sf.json.JSONObject");
            class$net$sf$json$JSONObject = cls2;
        } else {
            cls2 = class$net$sf$json$JSONObject;
        }
        if (!DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.invokeMethodN(cls, ScriptBytecodeAdapter.getProperty(cls, ScriptBytecodeAdapter.getProperty(cls, cls2, "metaClass"), "methods"), "find", new Object[]{new _enhanceJSONObject_closure10(cls, cls)}))) {
            _enhanceJSONObject_closure11 _enhancejsonobject_closure11 = new _enhanceJSONObject_closure11(cls, cls);
            if (class$net$sf$json$JSONObject == null) {
                cls4 = class$("net.sf.json.JSONObject");
                class$net$sf$json$JSONObject = cls4;
            } else {
                cls4 = class$net$sf$json$JSONObject;
            }
            ScriptBytecodeAdapter.setProperty(_enhancejsonobject_closure11, cls, ScriptBytecodeAdapter.getProperty(cls, cls4, "metaClass"), "leftShift");
            _enhanceJSONObject_closure12 _enhancejsonobject_closure12 = new _enhanceJSONObject_closure12(cls, cls);
            if (class$net$sf$json$JSONObject == null) {
                cls5 = class$("net.sf.json.JSONObject");
                class$net$sf$json$JSONObject = cls5;
            } else {
                cls5 = class$net$sf$json$JSONObject;
            }
            ScriptBytecodeAdapter.setProperty(_enhancejsonobject_closure12, cls, ScriptBytecodeAdapter.getProperty(cls, cls5, "metaClass"), "get");
            _enhanceJSONObject_closure13 _enhancejsonobject_closure13 = new _enhanceJSONObject_closure13(cls, cls);
            if (class$net$sf$json$JSONObject == null) {
                cls6 = class$("net.sf.json.JSONObject");
                class$net$sf$json$JSONObject = cls6;
            } else {
                cls6 = class$net$sf$json$JSONObject;
            }
            ScriptBytecodeAdapter.setProperty(_enhancejsonobject_closure13, cls, ScriptBytecodeAdapter.getProperty(cls, cls6, "metaClass"), "isJsonEnhanced");
            Boolean bool = Boolean.TRUE;
            if (class$java$lang$Boolean == null) {
                cls7 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls7;
            } else {
                cls7 = class$java$lang$Boolean;
            }
            return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool, cls7));
        }
        Boolean bool2 = Boolean.FALSE;
        if (class$java$lang$Boolean == null) {
            cls3 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls3;
        } else {
            cls3 = class$java$lang$Boolean;
        }
        return DefaultTypeTransformation.booleanUnbox((Boolean) ScriptBytecodeAdapter.castToType(bool2, cls3));
    }
}
