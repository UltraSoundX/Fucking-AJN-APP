package net.sf.json;

import com.tencent.mid.sotrage.StorageInterface;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;

public final class JSONArray extends AbstractJSON implements Comparable, List, JSON {
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Byte;
    static Class class$java$lang$Character;
    static Class class$java$lang$Class;
    static Class class$java$lang$Object;
    static Class class$java$lang$Short;
    static Class class$java$lang$String;
    static Class class$java$util$List;
    static Class class$java$util$Set;
    static Class class$net$sf$json$JSONArray;
    static Class class$net$sf$json$JSONFunction;
    private List elements = new ArrayList();
    private boolean expandElements;

    private class JSONArrayListIterator implements ListIterator {
        int currentIndex = 0;
        int lastIndex = -1;
        private final JSONArray this$0;

        JSONArrayListIterator(JSONArray jSONArray) {
            this.this$0 = jSONArray;
        }

        JSONArrayListIterator(JSONArray jSONArray, int i) {
            this.this$0 = jSONArray;
            this.currentIndex = i;
        }

        public boolean hasNext() {
            return this.currentIndex != this.this$0.size();
        }

        public Object next() {
            try {
                Object obj = this.this$0.get(this.currentIndex);
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                this.lastIndex = i;
                return obj;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (this.lastIndex == -1) {
                throw new IllegalStateException();
            }
            try {
                this.this$0.remove(this.lastIndex);
                if (this.lastIndex < this.currentIndex) {
                    this.currentIndex--;
                }
                this.lastIndex = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasPrevious() {
            return this.currentIndex != 0;
        }

        public Object previous() {
            try {
                int i = this.currentIndex - 1;
                Object obj = this.this$0.get(i);
                this.currentIndex = i;
                this.lastIndex = i;
                return obj;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return this.currentIndex;
        }

        public int previousIndex() {
            return this.currentIndex - 1;
        }

        public void set(Object obj) {
            if (this.lastIndex == -1) {
                throw new IllegalStateException();
            }
            try {
                this.this$0.set(this.lastIndex, obj);
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Object obj) {
            try {
                JSONArray jSONArray = this.this$0;
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                jSONArray.add(i, obj);
                this.lastIndex = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public static JSONArray fromObject(Object obj) {
        return fromObject(obj, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v14, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v21, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v14, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 120
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
    public static net.sf.json.JSONArray fromObject(java.lang.Object r3, net.sf.json.JsonConfig r4) {
        /*
            r2 = 0
            boolean r0 = r3 instanceof net.sf.json.JSONString
            if (r0 == 0) goto L_0x000c
            net.sf.json.JSONString r3 = (net.sf.json.JSONString) r3
            net.sf.json.JSONArray r0 = _fromJSONString(r3, r4)
        L_0x000b:
            return r0
        L_0x000c:
            boolean r0 = r3 instanceof net.sf.json.JSONArray
            if (r0 == 0) goto L_0x0017
            net.sf.json.JSONArray r3 = (net.sf.json.JSONArray) r3
            net.sf.json.JSONArray r0 = _fromJSONArray(r3, r4)
            goto L_0x000b
        L_0x0017:
            boolean r0 = r3 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0022
            java.util.Collection r3 = (java.util.Collection) r3
            net.sf.json.JSONArray r0 = _fromCollection(r3, r4)
            goto L_0x000b
        L_0x0022:
            boolean r0 = r3 instanceof net.sf.json.util.JSONTokener
            if (r0 == 0) goto L_0x002d
            net.sf.json.util.JSONTokener r3 = (net.sf.json.util.JSONTokener) r3
            net.sf.json.JSONArray r0 = _fromJSONTokener(r3, r4)
            goto L_0x000b
        L_0x002d:
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L_0x0038
            java.lang.String r3 = (java.lang.String) r3
            net.sf.json.JSONArray r0 = _fromString(r3, r4)
            goto L_0x000b
        L_0x0038:
            if (r3 == 0) goto L_0x00d0
            java.lang.Class r0 = r3.getClass()
            boolean r0 = r0.isArray()
            if (r0 == 0) goto L_0x00d0
            java.lang.Class r0 = r3.getClass()
            java.lang.Class r0 = r0.getComponentType()
            boolean r1 = r0.isPrimitive()
            if (r1 != 0) goto L_0x005b
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x005b:
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 != r1) goto L_0x0068
            boolean[] r3 = (boolean[]) r3
            boolean[] r3 = (boolean[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x0068:
            java.lang.Class r1 = java.lang.Byte.TYPE
            if (r0 != r1) goto L_0x0075
            byte[] r3 = (byte[]) r3
            byte[] r3 = (byte[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x0075:
            java.lang.Class r1 = java.lang.Short.TYPE
            if (r0 != r1) goto L_0x0082
            short[] r3 = (short[]) r3
            short[] r3 = (short[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x0082:
            java.lang.Class r1 = java.lang.Integer.TYPE
            if (r0 != r1) goto L_0x0090
            int[] r3 = (int[]) r3
            int[] r3 = (int[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x0090:
            java.lang.Class r1 = java.lang.Long.TYPE
            if (r0 != r1) goto L_0x009e
            long[] r3 = (long[]) r3
            long[] r3 = (long[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x009e:
            java.lang.Class r1 = java.lang.Float.TYPE
            if (r0 != r1) goto L_0x00ac
            float[] r3 = (float[]) r3
            float[] r3 = (float[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x00ac:
            java.lang.Class r1 = java.lang.Double.TYPE
            if (r0 != r1) goto L_0x00ba
            double[] r3 = (double[]) r3
            double[] r3 = (double[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x00ba:
            java.lang.Class r1 = java.lang.Character.TYPE
            if (r0 != r1) goto L_0x00c8
            char[] r3 = (char[]) r3
            char[] r3 = (char[]) r3
            net.sf.json.JSONArray r0 = _fromArray(r3, r4)
            goto L_0x000b
        L_0x00c8:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Unsupported type"
            r0.<init>(r1)
            throw r0
        L_0x00d0:
            boolean r0 = net.sf.json.util.JSONUtils.isBoolean(r3)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = net.sf.json.util.JSONUtils.isFunction(r3)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = net.sf.json.util.JSONUtils.isNumber(r3)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = net.sf.json.util.JSONUtils.isNull(r3)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = net.sf.json.util.JSONUtils.isString(r3)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = r3 instanceof net.sf.json.JSON
            if (r0 == 0) goto L_0x010a
        L_0x00f2:
            net.sf.json.AbstractJSON.fireArrayStartEvent(r4)
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            net.sf.json.JSONArray r0 = r0.element(r3, r4)
            java.lang.Object r1 = r0.get(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r2, r1, r4)
            net.sf.json.AbstractJSON.fireArrayStartEvent(r4)
            goto L_0x000b
        L_0x010a:
            boolean r0 = net.sf.json.util.JSONUtils.isObject(r3)
            if (r0 == 0) goto L_0x012c
            net.sf.json.AbstractJSON.fireArrayStartEvent(r4)
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            net.sf.json.JSONObject r1 = net.sf.json.JSONObject.fromObject(r3, r4)
            net.sf.json.JSONArray r0 = r0.element(r1)
            java.lang.Object r1 = r0.get(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r2, r1, r4)
            net.sf.json.AbstractJSON.fireArrayStartEvent(r4)
            goto L_0x000b
        L_0x012c:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Unsupported type"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.fromObject(java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    public static int[] getDimensions(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return new int[]{0};
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        processArrayDimensions(jSONArray, arrayList, 0);
        int[] iArr = new int[arrayList.size()];
        int i = 0;
        for (Integer intValue : arrayList) {
            int i2 = i + 1;
            iArr[i] = intValue.intValue();
            i = i2;
        }
        return iArr;
    }

    public static Object toArray(JSONArray jSONArray) {
        return toArray(jSONArray, new JsonConfig());
    }

    public static Object toArray(JSONArray jSONArray, Class cls) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        return toArray(jSONArray, jsonConfig);
    }

    public static Object toArray(JSONArray jSONArray, Class cls, Map map) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        jsonConfig.setClassMap(map);
        return toArray(jSONArray, jsonConfig);
    }

    public static Object toArray(JSONArray jSONArray, JsonConfig jsonConfig) {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        Class cls8;
        Class cls9;
        Class rootClass = jsonConfig.getRootClass();
        Map classMap = jsonConfig.getClassMap();
        if (jSONArray.size() == 0) {
            if (rootClass != null) {
                cls9 = rootClass;
            } else if (class$java$lang$Object == null) {
                cls9 = class$("java.lang.Object");
                class$java$lang$Object = cls9;
            } else {
                cls9 = class$java$lang$Object;
            }
            return Array.newInstance(cls9, 0);
        }
        int[] dimensions = getDimensions(jSONArray);
        if (rootClass != null) {
            cls = rootClass;
        } else if (class$java$lang$Object == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        } else {
            cls = class$java$lang$Object;
        }
        Object newInstance = Array.newInstance(cls, dimensions);
        int size = jSONArray.size();
        for (int i = 0; i < size; i++) {
            Object obj = jSONArray.get(i);
            if (JSONUtils.isNull(obj)) {
                Array.set(newInstance, i, null);
            } else {
                Class cls10 = obj.getClass();
                if (class$net$sf$json$JSONArray == null) {
                    cls2 = class$("net.sf.json.JSONArray");
                    class$net$sf$json$JSONArray = cls2;
                } else {
                    cls2 = class$net$sf$json$JSONArray;
                }
                if (cls2.isAssignableFrom(cls10)) {
                    Array.set(newInstance, i, toArray((JSONArray) obj, rootClass, classMap));
                } else {
                    if (class$java$lang$String == null) {
                        cls3 = class$("java.lang.String");
                        class$java$lang$String = cls3;
                    } else {
                        cls3 = class$java$lang$String;
                    }
                    if (!cls3.isAssignableFrom(cls10)) {
                        if (class$java$lang$Boolean == null) {
                            cls4 = class$("java.lang.Boolean");
                            class$java$lang$Boolean = cls4;
                        } else {
                            cls4 = class$java$lang$Boolean;
                        }
                        if (!cls4.isAssignableFrom(cls10)) {
                            if (class$java$lang$Character == null) {
                                cls5 = class$("java.lang.Character");
                                class$java$lang$Character = cls5;
                            } else {
                                cls5 = class$java$lang$Character;
                            }
                            if (!cls5.isAssignableFrom(cls10)) {
                                if (class$net$sf$json$JSONFunction == null) {
                                    cls6 = class$("net.sf.json.JSONFunction");
                                    class$net$sf$json$JSONFunction = cls6;
                                } else {
                                    cls6 = class$net$sf$json$JSONFunction;
                                }
                                if (!cls6.isAssignableFrom(cls10)) {
                                    if (JSONUtils.isNumber(cls10)) {
                                        if (rootClass != null) {
                                            if (class$java$lang$Byte == null) {
                                                cls8 = class$("java.lang.Byte");
                                                class$java$lang$Byte = cls8;
                                            } else {
                                                cls8 = class$java$lang$Byte;
                                            }
                                            if (cls8.isAssignableFrom(rootClass) || Byte.TYPE.isAssignableFrom(rootClass)) {
                                                Array.set(newInstance, i, Byte.valueOf(String.valueOf(obj)));
                                            }
                                        }
                                        if (rootClass != null) {
                                            if (class$java$lang$Short == null) {
                                                cls7 = class$("java.lang.Short");
                                                class$java$lang$Short = cls7;
                                            } else {
                                                cls7 = class$java$lang$Short;
                                            }
                                            if (cls7.isAssignableFrom(rootClass) || Short.TYPE.isAssignableFrom(rootClass)) {
                                                Array.set(newInstance, i, Short.valueOf(String.valueOf(obj)));
                                            }
                                        }
                                        Array.set(newInstance, i, obj);
                                    } else if (rootClass != null) {
                                        JsonConfig copy = jsonConfig.copy();
                                        copy.setRootClass(rootClass);
                                        copy.setClassMap(classMap);
                                        Array.set(newInstance, i, JSONObject.toBean((JSONObject) obj, copy));
                                    } else {
                                        Array.set(newInstance, i, JSONObject.toBean((JSONObject) obj));
                                    }
                                }
                            }
                        }
                    }
                    if (rootClass != null && !rootClass.isAssignableFrom(cls10)) {
                        obj = JSONUtils.getMorpherRegistry().morph(rootClass, obj);
                    }
                    Array.set(newInstance, i, obj);
                }
            }
        }
        return newInstance;
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 89
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
    public static java.lang.Object toArray(net.sf.json.JSONArray r9, java.lang.Object r10, net.sf.json.JsonConfig r11) {
        /*
            r8 = 0
            r2 = 0
            java.lang.Class r1 = r10.getClass()
            int r0 = r9.size()
            if (r0 != 0) goto L_0x0011
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r1, r2)
        L_0x0010:
            return r0
        L_0x0011:
            int[] r3 = getDimensions(r9)
            if (r1 != 0) goto L_0x0042
            java.lang.Class r0 = class$java$lang$Object
            if (r0 != 0) goto L_0x003f
            java.lang.String r0 = "java.lang.Object"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Object = r0
        L_0x0023:
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r0, r3)
            int r5 = r9.size()
            r4 = r2
        L_0x002c:
            if (r4 >= r5) goto L_0x00f7
            java.lang.Object r0 = r9.get(r4)
            boolean r2 = net.sf.json.util.JSONUtils.isNull(r0)
            if (r2 == 0) goto L_0x0044
            java.lang.reflect.Array.set(r3, r4, r8)
        L_0x003b:
            int r0 = r4 + 1
            r4 = r0
            goto L_0x002c
        L_0x003f:
            java.lang.Class r0 = class$java$lang$Object
            goto L_0x0023
        L_0x0042:
            r0 = r1
            goto L_0x0023
        L_0x0044:
            java.lang.Class r6 = r0.getClass()
            java.lang.Class r2 = class$net$sf$json$JSONArray
            if (r2 != 0) goto L_0x0064
            java.lang.String r2 = "net.sf.json.JSONArray"
            java.lang.Class r2 = class$(r2)
            class$net$sf$json$JSONArray = r2
        L_0x0054:
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 == 0) goto L_0x0067
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            java.lang.Object r0 = toArray(r0, r10, r11)
            java.lang.reflect.Array.set(r3, r4, r0)
            goto L_0x003b
        L_0x0064:
            java.lang.Class r2 = class$net$sf$json$JSONArray
            goto L_0x0054
        L_0x0067:
            java.lang.Class r2 = class$java$lang$String
            if (r2 != 0) goto L_0x00ca
            java.lang.String r2 = "java.lang.String"
            java.lang.Class r2 = class$(r2)
            class$java$lang$String = r2
        L_0x0073:
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 != 0) goto L_0x00b5
            java.lang.Class r2 = class$java$lang$Boolean
            if (r2 != 0) goto L_0x00cd
            java.lang.String r2 = "java.lang.Boolean"
            java.lang.Class r2 = class$(r2)
            class$java$lang$Boolean = r2
        L_0x0085:
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 != 0) goto L_0x00b5
            boolean r2 = net.sf.json.util.JSONUtils.isNumber(r6)
            if (r2 != 0) goto L_0x00b5
            java.lang.Class r2 = class$java$lang$Character
            if (r2 != 0) goto L_0x00d0
            java.lang.String r2 = "java.lang.Character"
            java.lang.Class r2 = class$(r2)
            class$java$lang$Character = r2
        L_0x009d:
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 != 0) goto L_0x00b5
            java.lang.Class r2 = class$net$sf$json$JSONFunction
            if (r2 != 0) goto L_0x00d3
            java.lang.String r2 = "net.sf.json.JSONFunction"
            java.lang.Class r2 = class$(r2)
            class$net$sf$json$JSONFunction = r2
        L_0x00af:
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 == 0) goto L_0x00d6
        L_0x00b5:
            if (r1 == 0) goto L_0x00c5
            boolean r2 = r1.isAssignableFrom(r6)
            if (r2 != 0) goto L_0x00c5
            net.sf.ezmorph.MorpherRegistry r2 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Object r0 = r2.morph(r1, r0)
        L_0x00c5:
            java.lang.reflect.Array.set(r3, r4, r0)
            goto L_0x003b
        L_0x00ca:
            java.lang.Class r2 = class$java$lang$String
            goto L_0x0073
        L_0x00cd:
            java.lang.Class r2 = class$java$lang$Boolean
            goto L_0x0085
        L_0x00d0:
            java.lang.Class r2 = class$java$lang$Character
            goto L_0x009d
        L_0x00d3:
            java.lang.Class r2 = class$net$sf$json$JSONFunction
            goto L_0x00af
        L_0x00d6:
            net.sf.json.util.NewBeanInstanceStrategy r2 = r11.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            java.lang.Class r6 = r10.getClass()     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            r7 = 0
            java.lang.Object r2 = r2.newInstance(r6, r7)     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            java.lang.Object r0 = net.sf.json.JSONObject.toBean(r0, r2, r11)     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            java.lang.reflect.Array.set(r3, r4, r0)     // Catch:{ JSONException -> 0x00ee, Exception -> 0x00f0 }
            goto L_0x003b
        L_0x00ee:
            r0 = move-exception
            throw r0
        L_0x00f0:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x00f7:
            r0 = r3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.toArray(net.sf.json.JSONArray, java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    public static Collection toCollection(JSONArray jSONArray) {
        return toCollection(jSONArray, new JsonConfig());
    }

    public static Collection toCollection(JSONArray jSONArray, Class cls) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        return toCollection(jSONArray, jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v28, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 126
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
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Collection toCollection(net.sf.json.JSONArray r9, net.sf.json.JsonConfig r10) {
        /*
            java.lang.Class r1 = r10.getCollectionType()
            boolean r0 = r1.isInterface()
            if (r0 == 0) goto L_0x007c
            java.lang.Class r0 = class$java$util$List
            if (r0 != 0) goto L_0x0044
            java.lang.String r0 = "java.util.List"
            java.lang.Class r0 = class$(r0)
            class$java$util$List = r0
        L_0x0016:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0047
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = r0
        L_0x0022:
            java.lang.Class r4 = r10.getRootClass()
            java.util.Map r5 = r10.getClassMap()
            int r6 = r9.size()
            r0 = 0
            r3 = r0
        L_0x0030:
            if (r3 >= r6) goto L_0x0175
            java.lang.Object r0 = r9.get(r3)
            boolean r2 = net.sf.json.util.JSONUtils.isNull(r0)
            if (r2 == 0) goto L_0x0092
            r0 = 0
            r1.add(r0)
        L_0x0040:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x0030
        L_0x0044:
            java.lang.Class r0 = class$java$util$List
            goto L_0x0016
        L_0x0047:
            java.lang.Class r0 = class$java$util$Set
            if (r0 != 0) goto L_0x0060
            java.lang.String r0 = "java.util.Set"
            java.lang.Class r0 = class$(r0)
            class$java$util$Set = r0
        L_0x0053:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0063
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = r0
            goto L_0x0022
        L_0x0060:
            java.lang.Class r0 = class$java$util$Set
            goto L_0x0053
        L_0x0063:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "unknown interface: "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x007c:
            java.lang.Object r0 = r1.newInstance()     // Catch:{ InstantiationException -> 0x0084, IllegalAccessException -> 0x008b }
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ InstantiationException -> 0x0084, IllegalAccessException -> 0x008b }
            r1 = r0
            goto L_0x0022
        L_0x0084:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x008b:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x0092:
            java.lang.Class r7 = r0.getClass()
            java.lang.Class r2 = class$net$sf$json$JSONArray
            if (r2 != 0) goto L_0x00b6
            java.lang.String r2 = "net.sf.json.JSONArray"
            java.lang.Class r2 = class$(r2)
            class$net$sf$json$JSONArray = r2
        L_0x00a2:
            java.lang.Class r8 = r0.getClass()
            boolean r2 = r2.isAssignableFrom(r8)
            if (r2 == 0) goto L_0x00b9
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            java.util.Collection r0 = toCollection(r0, r10)
            r1.add(r0)
            goto L_0x0040
        L_0x00b6:
            java.lang.Class r2 = class$net$sf$json$JSONArray
            goto L_0x00a2
        L_0x00b9:
            java.lang.Class r2 = class$java$lang$String
            if (r2 != 0) goto L_0x0142
            java.lang.String r2 = "java.lang.String"
            java.lang.Class r2 = class$(r2)
            class$java$lang$String = r2
        L_0x00c5:
            boolean r2 = r2.isAssignableFrom(r7)
            if (r2 != 0) goto L_0x0107
            java.lang.Class r2 = class$java$lang$Boolean
            if (r2 != 0) goto L_0x0145
            java.lang.String r2 = "java.lang.Boolean"
            java.lang.Class r2 = class$(r2)
            class$java$lang$Boolean = r2
        L_0x00d7:
            boolean r2 = r2.isAssignableFrom(r7)
            if (r2 != 0) goto L_0x0107
            boolean r2 = net.sf.json.util.JSONUtils.isNumber(r7)
            if (r2 != 0) goto L_0x0107
            java.lang.Class r2 = class$java$lang$Character
            if (r2 != 0) goto L_0x0148
            java.lang.String r2 = "java.lang.Character"
            java.lang.Class r2 = class$(r2)
            class$java$lang$Character = r2
        L_0x00ef:
            boolean r2 = r2.isAssignableFrom(r7)
            if (r2 != 0) goto L_0x0107
            java.lang.Class r2 = class$net$sf$json$JSONFunction
            if (r2 != 0) goto L_0x014b
            java.lang.String r2 = "net.sf.json.JSONFunction"
            java.lang.Class r2 = class$(r2)
            class$net$sf$json$JSONFunction = r2
        L_0x0101:
            boolean r2 = r2.isAssignableFrom(r7)
            if (r2 == 0) goto L_0x0153
        L_0x0107:
            java.lang.Class r2 = r0.getClass()
            boolean r2 = r2.isAssignableFrom(r7)
            if (r2 != 0) goto L_0x014e
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "can't assign value "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.StringBuffer r2 = r2.append(r0)
            java.lang.String r3 = " of type "
            java.lang.StringBuffer r2 = r2.append(r3)
            java.lang.Class r0 = r0.getClass()
            java.lang.StringBuffer r0 = r2.append(r0)
            java.lang.String r2 = " to Collection of type "
            java.lang.StringBuffer r0 = r0.append(r2)
            java.lang.StringBuffer r0 = r0.append(r7)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0142:
            java.lang.Class r2 = class$java$lang$String
            goto L_0x00c5
        L_0x0145:
            java.lang.Class r2 = class$java$lang$Boolean
            goto L_0x00d7
        L_0x0148:
            java.lang.Class r2 = class$java$lang$Character
            goto L_0x00ef
        L_0x014b:
            java.lang.Class r2 = class$net$sf$json$JSONFunction
            goto L_0x0101
        L_0x014e:
            r1.add(r0)
            goto L_0x0040
        L_0x0153:
            if (r4 == 0) goto L_0x016a
            net.sf.json.JsonConfig r2 = r10.copy()
            r2.setRootClass(r4)
            r2.setClassMap(r5)
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0
            java.lang.Object r0 = net.sf.json.JSONObject.toBean(r0, r2)
            r1.add(r0)
            goto L_0x0040
        L_0x016a:
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0
            java.lang.Object r0 = net.sf.json.JSONObject.toBean(r0)
            r1.add(r0)
            goto L_0x0040
        L_0x0175:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.toCollection(net.sf.json.JSONArray, net.sf.json.JsonConfig):java.util.Collection");
    }

    public static List toList(JSONArray jSONArray) {
        return toList(jSONArray, new JsonConfig());
    }

    public static List toList(JSONArray jSONArray, Class cls) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        return toList(jSONArray, jsonConfig);
    }

    public static List toList(JSONArray jSONArray, Class cls, Map map) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(cls);
        jsonConfig.setClassMap(map);
        return toList(jSONArray, jsonConfig);
    }

    public static List toList(JSONArray jSONArray, JsonConfig jsonConfig) {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        if (jSONArray.size() == 0) {
            return new ArrayList();
        }
        Class rootClass = jsonConfig.getRootClass();
        Map classMap = jsonConfig.getClassMap();
        ArrayList arrayList = new ArrayList();
        int size = jSONArray.size();
        for (int i = 0; i < size; i++) {
            Object obj = jSONArray.get(i);
            if (JSONUtils.isNull(obj)) {
                arrayList.add(null);
            } else {
                Class cls6 = obj.getClass();
                if (class$net$sf$json$JSONArray == null) {
                    cls = class$("net.sf.json.JSONArray");
                    class$net$sf$json$JSONArray = cls;
                } else {
                    cls = class$net$sf$json$JSONArray;
                }
                if (cls.isAssignableFrom(cls6)) {
                    arrayList.add(toList((JSONArray) obj, rootClass, classMap));
                } else {
                    if (class$java$lang$String == null) {
                        cls2 = class$("java.lang.String");
                        class$java$lang$String = cls2;
                    } else {
                        cls2 = class$java$lang$String;
                    }
                    if (!cls2.isAssignableFrom(cls6)) {
                        if (class$java$lang$Boolean == null) {
                            cls3 = class$("java.lang.Boolean");
                            class$java$lang$Boolean = cls3;
                        } else {
                            cls3 = class$java$lang$Boolean;
                        }
                        if (!cls3.isAssignableFrom(cls6) && !JSONUtils.isNumber(cls6)) {
                            if (class$java$lang$Character == null) {
                                cls4 = class$("java.lang.Character");
                                class$java$lang$Character = cls4;
                            } else {
                                cls4 = class$java$lang$Character;
                            }
                            if (!cls4.isAssignableFrom(cls6)) {
                                if (class$net$sf$json$JSONFunction == null) {
                                    cls5 = class$("net.sf.json.JSONFunction");
                                    class$net$sf$json$JSONFunction = cls5;
                                } else {
                                    cls5 = class$net$sf$json$JSONFunction;
                                }
                                if (!cls5.isAssignableFrom(cls6)) {
                                    if (rootClass != null) {
                                        JsonConfig copy = jsonConfig.copy();
                                        copy.setRootClass(rootClass);
                                        copy.setClassMap(classMap);
                                        arrayList.add(JSONObject.toBean((JSONObject) obj, copy));
                                    } else {
                                        arrayList.add(JSONObject.toBean((JSONObject) obj));
                                    }
                                }
                            }
                        }
                    }
                    if (rootClass != null && !rootClass.isAssignableFrom(cls6)) {
                        obj = JSONUtils.getMorpherRegistry().morph(rootClass, obj);
                    }
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: type inference failed for: r1v22, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v22, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 75
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
    public static java.util.List toList(net.sf.json.JSONArray r8, java.lang.Object r9, net.sf.json.JsonConfig r10) {
        /*
            r7 = 0
            int r0 = r8.size()
            if (r0 == 0) goto L_0x0009
            if (r9 != 0) goto L_0x000f
        L_0x0009:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x000e:
            return r0
        L_0x000f:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r4 = r8.size()
            r0 = 0
            r3 = r0
        L_0x001a:
            if (r3 >= r4) goto L_0x00cf
            java.lang.Object r0 = r8.get(r3)
            boolean r1 = net.sf.json.util.JSONUtils.isNull(r0)
            if (r1 == 0) goto L_0x002d
            r2.add(r7)
        L_0x0029:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x001a
        L_0x002d:
            java.lang.Class r5 = r0.getClass()
            java.lang.Class r1 = class$net$sf$json$JSONArray
            if (r1 != 0) goto L_0x004d
            java.lang.String r1 = "net.sf.json.JSONArray"
            java.lang.Class r1 = class$(r1)
            class$net$sf$json$JSONArray = r1
        L_0x003d:
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 == 0) goto L_0x0050
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            java.util.List r0 = toList(r0, r9, r10)
            r2.add(r0)
            goto L_0x0029
        L_0x004d:
            java.lang.Class r1 = class$net$sf$json$JSONArray
            goto L_0x003d
        L_0x0050:
            java.lang.Class r1 = class$java$lang$String
            if (r1 != 0) goto L_0x00a2
            java.lang.String r1 = "java.lang.String"
            java.lang.Class r1 = class$(r1)
            class$java$lang$String = r1
        L_0x005c:
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 != 0) goto L_0x009e
            java.lang.Class r1 = class$java$lang$Boolean
            if (r1 != 0) goto L_0x00a5
            java.lang.String r1 = "java.lang.Boolean"
            java.lang.Class r1 = class$(r1)
            class$java$lang$Boolean = r1
        L_0x006e:
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 != 0) goto L_0x009e
            boolean r1 = net.sf.json.util.JSONUtils.isNumber(r5)
            if (r1 != 0) goto L_0x009e
            java.lang.Class r1 = class$java$lang$Character
            if (r1 != 0) goto L_0x00a8
            java.lang.String r1 = "java.lang.Character"
            java.lang.Class r1 = class$(r1)
            class$java$lang$Character = r1
        L_0x0086:
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 != 0) goto L_0x009e
            java.lang.Class r1 = class$net$sf$json$JSONFunction
            if (r1 != 0) goto L_0x00ab
            java.lang.String r1 = "net.sf.json.JSONFunction"
            java.lang.Class r1 = class$(r1)
            class$net$sf$json$JSONFunction = r1
        L_0x0098:
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 == 0) goto L_0x00ae
        L_0x009e:
            r2.add(r0)
            goto L_0x0029
        L_0x00a2:
            java.lang.Class r1 = class$java$lang$String
            goto L_0x005c
        L_0x00a5:
            java.lang.Class r1 = class$java$lang$Boolean
            goto L_0x006e
        L_0x00a8:
            java.lang.Class r1 = class$java$lang$Character
            goto L_0x0086
        L_0x00ab:
            java.lang.Class r1 = class$net$sf$json$JSONFunction
            goto L_0x0098
        L_0x00ae:
            net.sf.json.util.NewBeanInstanceStrategy r1 = r10.getNewBeanInstanceStrategy()     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            java.lang.Class r5 = r9.getClass()     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            r6 = 0
            java.lang.Object r1 = r1.newInstance(r5, r6)     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            java.lang.Object r0 = net.sf.json.JSONObject.toBean(r0, r1, r10)     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            r2.add(r0)     // Catch:{ JSONException -> 0x00c6, Exception -> 0x00c8 }
            goto L_0x0029
        L_0x00c6:
            r0 = move-exception
            throw r0
        L_0x00c8:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x00cf:
            r0 = r2
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.toList(net.sf.json.JSONArray, java.lang.Object, net.sf.json.JsonConfig):java.util.List");
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v5, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 31
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
    private static net.sf.json.JSONArray _fromArray(boolean[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r2 = new net.sf.json.JSONArray
            r2.<init>()
            r0 = 0
        L_0x002d:
            int r1 = r4.length
            if (r0 >= r1) goto L_0x0044
            boolean r1 = r4[r0]
            if (r1 == 0) goto L_0x0041
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
        L_0x0036:
            java.util.List r3 = r2.elements
            r3.add(r1)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r1, r5)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0041:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            goto L_0x0036
        L_0x0044:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            r0 = r2
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(boolean[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 30
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
    private static net.sf.json.JSONArray _fromArray(byte[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            r0 = 0
        L_0x002d:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x0046
            java.lang.Byte r2 = new java.lang.Byte
            byte r3 = r4[r0]
            r2.<init>(r3)
            java.lang.Number r2 = net.sf.json.util.JSONUtils.transformNumber(r2)
            java.util.List r3 = r1.elements
            r3.add(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r2, r5)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0046:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            r0 = r1
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(byte[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONArray _fromArray(char[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            r0 = 0
        L_0x002d:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x0042
            java.lang.Character r2 = new java.lang.Character
            char r3 = r4[r0]
            r2.<init>(r3)
            java.util.List r3 = r1.elements
            r3.add(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r2, r5)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0042:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            r0 = r1
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(char[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 33
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
    private static net.sf.json.JSONArray _fromArray(double[] r6, net.sf.json.JsonConfig r7) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r7)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r6)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r7.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r6)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r7)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r7)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            r1 = 0
        L_0x002d:
            int r2 = r6.length     // Catch:{ JSONException -> 0x0045 }
            if (r1 >= r2) goto L_0x004d
            java.lang.Double r2 = new java.lang.Double     // Catch:{ JSONException -> 0x0045 }
            r4 = r6[r1]     // Catch:{ JSONException -> 0x0045 }
            r2.<init>(r4)     // Catch:{ JSONException -> 0x0045 }
            net.sf.json.util.JSONUtils.testValidity(r2)     // Catch:{ JSONException -> 0x0045 }
            java.util.List r3 = r0.elements     // Catch:{ JSONException -> 0x0045 }
            r3.add(r2)     // Catch:{ JSONException -> 0x0045 }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r1, r2, r7)     // Catch:{ JSONException -> 0x0045 }
            int r1 = r1 + 1
            goto L_0x002d
        L_0x0045:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r7)
            throw r0
        L_0x004d:
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r7)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(double[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 33
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
    private static net.sf.json.JSONArray _fromArray(float[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            r1 = 0
        L_0x002d:
            int r2 = r4.length     // Catch:{ JSONException -> 0x0045 }
            if (r1 >= r2) goto L_0x004d
            java.lang.Float r2 = new java.lang.Float     // Catch:{ JSONException -> 0x0045 }
            r3 = r4[r1]     // Catch:{ JSONException -> 0x0045 }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x0045 }
            net.sf.json.util.JSONUtils.testValidity(r2)     // Catch:{ JSONException -> 0x0045 }
            java.util.List r3 = r0.elements     // Catch:{ JSONException -> 0x0045 }
            r3.add(r2)     // Catch:{ JSONException -> 0x0045 }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r1, r2, r5)     // Catch:{ JSONException -> 0x0045 }
            int r1 = r1 + 1
            goto L_0x002d
        L_0x0045:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x004d:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(float[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONArray _fromArray(int[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            r0 = 0
        L_0x002d:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x0042
            java.lang.Integer r2 = new java.lang.Integer
            r3 = r4[r0]
            r2.<init>(r3)
            java.util.List r3 = r1.elements
            r3.add(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r2, r5)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0042:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            r0 = r1
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(int[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 30
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
    private static net.sf.json.JSONArray _fromArray(long[] r6, net.sf.json.JsonConfig r7) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r7)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r6)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r7.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r6)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r7)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r7)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            r0 = 0
        L_0x002d:
            int r2 = r6.length
            if (r0 >= r2) goto L_0x0046
            java.lang.Long r2 = new java.lang.Long
            r4 = r6[r0]
            r2.<init>(r4)
            java.lang.Number r2 = net.sf.json.util.JSONUtils.transformNumber(r2)
            java.util.List r3 = r1.elements
            r3.add(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r2, r7)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0046:
            net.sf.json.AbstractJSON.removeInstance(r6)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r7)
            r0 = r1
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(long[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v2, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 36
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
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONArray _fromArray(java.lang.Object[] r3, net.sf.json.JsonConfig r4) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r4)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r3)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r4.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r3)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r3)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r4)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r3)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r4)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            r1 = 0
        L_0x002d:
            int r2 = r3.length     // Catch:{ JSONException -> 0x003f, RuntimeException -> 0x0047 }
            if (r1 >= r2) goto L_0x0054
            r2 = r3[r1]     // Catch:{ JSONException -> 0x003f, RuntimeException -> 0x0047 }
            r0.addValue(r2, r4)     // Catch:{ JSONException -> 0x003f, RuntimeException -> 0x0047 }
            java.lang.Object r2 = r0.get(r1)     // Catch:{ JSONException -> 0x003f, RuntimeException -> 0x0047 }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r1, r2, r4)     // Catch:{ JSONException -> 0x003f, RuntimeException -> 0x0047 }
            int r1 = r1 + 1
            goto L_0x002d
        L_0x003f:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r3)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r4)
            throw r0
        L_0x0047:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r3)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r4)
            throw r1
        L_0x0054:
            net.sf.json.AbstractJSON.removeInstance(r3)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r4)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(java.lang.Object[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 30
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
    private static net.sf.json.JSONArray _fromArray(short[] r4, net.sf.json.JsonConfig r5) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r5)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r4)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r5.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r4)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r5)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r5)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r1 = new net.sf.json.JSONArray
            r1.<init>()
            r0 = 0
        L_0x002d:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x0046
            java.lang.Short r2 = new java.lang.Short
            short r3 = r4[r0]
            r2.<init>(r3)
            java.lang.Number r2 = net.sf.json.util.JSONUtils.transformNumber(r2)
            java.util.List r3 = r1.elements
            r3.add(r2)
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r2, r5)
            int r0 = r0 + 1
            goto L_0x002d
        L_0x0046:
            net.sf.json.AbstractJSON.removeInstance(r4)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r5)
            r0 = r1
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromArray(short[], net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Collection, code=java.util.Collection<java.lang.Object>, for r5v0, types: [java.util.Collection<java.lang.Object>, java.util.Collection, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 38
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
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONArray _fromCollection(java.util.Collection<java.lang.Object> r5, net.sf.json.JsonConfig r6) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r6)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r5)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r6.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r5)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r6)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r6)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r0 = new net.sf.json.JSONArray
            r0.<init>()
            r1 = 0
            java.util.Iterator r3 = r5.iterator()     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
        L_0x0031:
            boolean r2 = r3.hasNext()     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
            if (r2 == 0) goto L_0x005e
            java.lang.Object r2 = r3.next()     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
            r0.addValue(r2, r6)     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
            int r2 = r1 + 1
            java.lang.Object r4 = r0.get(r1)     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r1, r4, r6)     // Catch:{ JSONException -> 0x0049, RuntimeException -> 0x0051 }
            r1 = r2
            goto L_0x0031
        L_0x0049:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r6)
            throw r0
        L_0x0051:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r6)
            throw r1
        L_0x005e:
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r6)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromCollection(java.util.Collection, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v3, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 30
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
    private static net.sf.json.JSONArray _fromJSONArray(net.sf.json.JSONArray r5, net.sf.json.JsonConfig r6) {
        /*
            net.sf.json.AbstractJSON.fireArrayStartEvent(r6)
            boolean r0 = net.sf.json.AbstractJSON.addInstance(r5)
            if (r0 != 0) goto L_0x0027
            net.sf.json.util.CycleDetectionStrategy r0 = r6.getCycleDetectionStrategy()     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
            net.sf.json.JSONArray r0 = r0.handleRepeatedReferenceAsArray(r5)     // Catch:{ JSONException -> 0x0012, RuntimeException -> 0x001a }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.AbstractJSON.fireErrorEvent(r0, r6)
            throw r0
        L_0x001a:
            r0 = move-exception
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r6)
            throw r1
        L_0x0027:
            net.sf.json.JSONArray r2 = new net.sf.json.JSONArray
            r2.<init>()
            r0 = 0
            java.util.Iterator r3 = r5.iterator()
        L_0x0031:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x0047
            java.lang.Object r4 = r3.next()
            java.util.List r1 = r2.elements
            r1.add(r4)
            int r1 = r0 + 1
            net.sf.json.AbstractJSON.fireElementAddedEvent(r0, r4, r6)
            r0 = r1
            goto L_0x0031
        L_0x0047:
            net.sf.json.AbstractJSON.removeInstance(r5)
            net.sf.json.AbstractJSON.fireArrayEndEvent(r6)
            r0 = r2
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromJSONArray(net.sf.json.JSONArray, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    private static JSONArray _fromJSONString(JSONString jSONString, JsonConfig jsonConfig) {
        return _fromJSONTokener(new JSONTokener(jSONString.toJSONString()), jsonConfig);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v17, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v37, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r1v43, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [net.sf.json.JSONException, java.lang.Throwable]
  mth insns count: 108
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
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static net.sf.json.JSONArray _fromJSONTokener(net.sf.json.util.JSONTokener r11, net.sf.json.JsonConfig r12) {
        /*
            r10 = 93
            r4 = 0
            net.sf.json.AbstractJSON.fireArrayStartEvent(r12)
            net.sf.json.JSONArray r3 = new net.sf.json.JSONArray
            r3.<init>()
            char r1 = r11.nextClean()     // Catch:{ JSONException -> 0x001a }
            r2 = 91
            if (r1 == r2) goto L_0x001f
            java.lang.String r1 = "A JSONArray text must start with '['"
            net.sf.json.JSONException r1 = r11.syntaxError(r1)     // Catch:{ JSONException -> 0x001a }
            throw r1     // Catch:{ JSONException -> 0x001a }
        L_0x001a:
            r1 = move-exception
            net.sf.json.AbstractJSON.fireErrorEvent(r1, r12)
            throw r1
        L_0x001f:
            char r1 = r11.nextClean()     // Catch:{ JSONException -> 0x001a }
            if (r1 != r10) goto L_0x002a
            net.sf.json.AbstractJSON.fireArrayEndEvent(r12)     // Catch:{ JSONException -> 0x001a }
            r1 = r3
        L_0x0029:
            return r1
        L_0x002a:
            r11.back()     // Catch:{ JSONException -> 0x001a }
            r5 = r4
        L_0x002e:
            char r1 = r11.nextClean()     // Catch:{ JSONException -> 0x001a }
            r2 = 44
            if (r1 != r2) goto L_0x0059
            r11.back()     // Catch:{ JSONException -> 0x001a }
            java.util.List r1 = r3.elements     // Catch:{ JSONException -> 0x001a }
            net.sf.json.JSONNull r2 = net.sf.json.JSONNull.getInstance()     // Catch:{ JSONException -> 0x001a }
            r1.add(r2)     // Catch:{ JSONException -> 0x001a }
            int r1 = r5 + 1
            java.lang.Object r2 = r3.get(r5)     // Catch:{ JSONException -> 0x001a }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r5, r2, r12)     // Catch:{ JSONException -> 0x001a }
        L_0x004b:
            char r2 = r11.nextClean()     // Catch:{ JSONException -> 0x001a }
            switch(r2) {
                case 44: goto L_0x010e;
                case 59: goto L_0x010e;
                case 93: goto L_0x0120;
                default: goto L_0x0052;
            }     // Catch:{ JSONException -> 0x001a }
        L_0x0052:
            java.lang.String r1 = "Expected a ',' or ']'"
            net.sf.json.JSONException r1 = r11.syntaxError(r1)     // Catch:{ JSONException -> 0x001a }
            throw r1     // Catch:{ JSONException -> 0x001a }
        L_0x0059:
            r11.back()     // Catch:{ JSONException -> 0x001a }
            java.lang.Object r2 = r11.nextValue(r12)     // Catch:{ JSONException -> 0x001a }
            boolean r1 = net.sf.json.util.JSONUtils.isFunctionHeader(r2)     // Catch:{ JSONException -> 0x001a }
            if (r1 != 0) goto L_0x009e
            boolean r1 = r2 instanceof java.lang.String     // Catch:{ JSONException -> 0x001a }
            if (r1 == 0) goto L_0x009a
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x001a }
            r1 = r0
            boolean r1 = net.sf.json.util.JSONUtils.mayBeJSON(r1)     // Catch:{ JSONException -> 0x001a }
            if (r1 == 0) goto L_0x009a
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x001a }
            r1.<init>()     // Catch:{ JSONException -> 0x001a }
            java.lang.String r6 = "\""
            java.lang.StringBuffer r1 = r1.append(r6)     // Catch:{ JSONException -> 0x001a }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x001a }
            java.lang.String r2 = "\""
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x001a }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x001a }
            r3.addValue(r1, r12)     // Catch:{ JSONException -> 0x001a }
        L_0x0090:
            int r1 = r5 + 1
            java.lang.Object r2 = r3.get(r5)     // Catch:{ JSONException -> 0x001a }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r5, r2, r12)     // Catch:{ JSONException -> 0x001a }
            goto L_0x004b
        L_0x009a:
            r3.addValue(r2, r12)     // Catch:{ JSONException -> 0x001a }
            goto L_0x0090
        L_0x009e:
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x001a }
            r1 = r0
            java.lang.String r6 = net.sf.json.util.JSONUtils.getFunctionParams(r1)     // Catch:{ JSONException -> 0x001a }
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x001a }
            r7.<init>()     // Catch:{ JSONException -> 0x001a }
            r1 = r4
        L_0x00ac:
            char r8 = r11.next()     // Catch:{ JSONException -> 0x001a }
            if (r8 != 0) goto L_0x00cc
        L_0x00b2:
            if (r1 == 0) goto L_0x00de
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ JSONException -> 0x001a }
            r1.<init>()     // Catch:{ JSONException -> 0x001a }
            java.lang.String r3 = "Unbalanced '{' or '}' on prop: "
            java.lang.StringBuffer r1 = r1.append(r3)     // Catch:{ JSONException -> 0x001a }
            java.lang.StringBuffer r1 = r1.append(r2)     // Catch:{ JSONException -> 0x001a }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x001a }
            net.sf.json.JSONException r1 = r11.syntaxError(r1)     // Catch:{ JSONException -> 0x001a }
            throw r1     // Catch:{ JSONException -> 0x001a }
        L_0x00cc:
            r9 = 123(0x7b, float:1.72E-43)
            if (r8 != r9) goto L_0x00d2
            int r1 = r1 + 1
        L_0x00d2:
            r9 = 125(0x7d, float:1.75E-43)
            if (r8 != r9) goto L_0x00d8
            int r1 = r1 + -1
        L_0x00d8:
            r7.append(r8)     // Catch:{ JSONException -> 0x001a }
            if (r1 != 0) goto L_0x00ac
            goto L_0x00b2
        L_0x00de:
            java.lang.String r1 = r7.toString()     // Catch:{ JSONException -> 0x001a }
            r2 = 1
            int r7 = r1.length()     // Catch:{ JSONException -> 0x001a }
            int r7 = r7 + -1
            java.lang.String r1 = r1.substring(r2, r7)     // Catch:{ JSONException -> 0x001a }
            java.lang.String r2 = r1.trim()     // Catch:{ JSONException -> 0x001a }
            net.sf.json.JSONFunction r7 = new net.sf.json.JSONFunction     // Catch:{ JSONException -> 0x001a }
            if (r6 == 0) goto L_0x010c
            java.lang.String r1 = ","
            java.lang.String[] r1 = org.apache.commons.lang.StringUtils.split(r6, r1)     // Catch:{ JSONException -> 0x001a }
        L_0x00fb:
            r7.<init>(r1, r2)     // Catch:{ JSONException -> 0x001a }
            r3.addValue(r7, r12)     // Catch:{ JSONException -> 0x001a }
            int r1 = r5 + 1
            java.lang.Object r2 = r3.get(r5)     // Catch:{ JSONException -> 0x001a }
            net.sf.json.AbstractJSON.fireElementAddedEvent(r5, r2, r12)     // Catch:{ JSONException -> 0x001a }
            goto L_0x004b
        L_0x010c:
            r1 = 0
            goto L_0x00fb
        L_0x010e:
            char r2 = r11.nextClean()     // Catch:{ JSONException -> 0x001a }
            if (r2 != r10) goto L_0x011a
            net.sf.json.AbstractJSON.fireArrayEndEvent(r12)     // Catch:{ JSONException -> 0x001a }
            r1 = r3
            goto L_0x0029
        L_0x011a:
            r11.back()     // Catch:{ JSONException -> 0x001a }
            r5 = r1
            goto L_0x002e
        L_0x0120:
            net.sf.json.AbstractJSON.fireArrayEndEvent(r12)     // Catch:{ JSONException -> 0x001a }
            r1 = r3
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._fromJSONTokener(net.sf.json.util.JSONTokener, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    private static JSONArray _fromString(String str, JsonConfig jsonConfig) {
        return _fromJSONTokener(new JSONTokener(str), jsonConfig);
    }

    private static void processArrayDimensions(JSONArray jSONArray, List list, int i) {
        if (list.size() <= i) {
            list.add(new Integer(jSONArray.size()));
        } else {
            if (jSONArray.size() > ((Integer) list.get(i)).intValue()) {
                list.set(i, new Integer(jSONArray.size()));
            }
        }
        Iterator it = jSONArray.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof JSONArray) {
                processArrayDimensions((JSONArray) next, list, i + 1);
            }
        }
    }

    public void add(int i, Object obj) {
        add(i, obj, new JsonConfig());
    }

    public void add(int i, Object obj, JsonConfig jsonConfig) {
        this.elements.add(i, processValue(obj, jsonConfig));
    }

    public boolean add(Object obj) {
        return add(obj, new JsonConfig());
    }

    public boolean add(Object obj, JsonConfig jsonConfig) {
        element(obj, jsonConfig);
        return true;
    }

    public boolean addAll(Collection collection) {
        return addAll(collection, new JsonConfig());
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Collection, code=java.util.Collection<java.lang.Object>, for r3v0, types: [java.util.Collection<java.lang.Object>, java.util.Collection] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean addAll(java.util.Collection<java.lang.Object> r3, net.sf.json.JsonConfig r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0008
            int r0 = r3.size()
            if (r0 != 0) goto L_0x000a
        L_0x0008:
            r0 = 0
        L_0x0009:
            return r0
        L_0x000a:
            java.util.Iterator r0 = r3.iterator()
        L_0x000e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x001c
            java.lang.Object r1 = r0.next()
            r2.element(r1, r4)
            goto L_0x000e
        L_0x001c:
            r0 = 1
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.addAll(java.util.Collection, net.sf.json.JsonConfig):boolean");
    }

    public boolean addAll(int i, Collection collection) {
        return addAll(i, collection, new JsonConfig());
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Collection, code=java.util.Collection<java.lang.Object>, for r7v0, types: [java.util.Collection<java.lang.Object>, java.util.Collection] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean addAll(int r6, java.util.Collection<java.lang.Object> r7, net.sf.json.JsonConfig r8) {
        /*
            r5 = this;
            r0 = 0
            if (r7 == 0) goto L_0x0009
            int r1 = r7.size()
            if (r1 != 0) goto L_0x000a
        L_0x0009:
            return r0
        L_0x000a:
            java.util.Iterator r2 = r7.iterator()
        L_0x000e:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x0026
            java.util.List r3 = r5.elements
            int r1 = r0 + 1
            int r0 = r0 + r6
            java.lang.Object r4 = r2.next()
            java.lang.Object r4 = r5.processValue(r4, r8)
            r3.add(r0, r4)
            r0 = r1
            goto L_0x000e
        L_0x0026:
            r0 = 1
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.addAll(int, java.util.Collection, net.sf.json.JsonConfig):boolean");
    }

    public void clear() {
        this.elements.clear();
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof JSONArray)) {
            return -1;
        }
        JSONArray jSONArray = (JSONArray) obj;
        int size = size();
        int size2 = jSONArray.size();
        if (size < size2) {
            return -1;
        }
        if (size > size2) {
            return 1;
        }
        if (equals(jSONArray)) {
            return 0;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return contains(obj, new JsonConfig());
    }

    public boolean contains(Object obj, JsonConfig jsonConfig) {
        return this.elements.contains(processValue(obj, jsonConfig));
    }

    public boolean containsAll(Collection collection) {
        return containsAll(collection, new JsonConfig());
    }

    public boolean containsAll(Collection collection, JsonConfig jsonConfig) {
        return this.elements.containsAll(fromObject(collection, jsonConfig));
    }

    public JSONArray discard(int i) {
        this.elements.remove(i);
        return this;
    }

    public JSONArray discard(Object obj) {
        this.elements.remove(obj);
        return this;
    }

    public JSONArray element(boolean z) {
        return element((Object) z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray element(Collection collection) {
        return element(collection, new JsonConfig());
    }

    public JSONArray element(Collection collection, JsonConfig jsonConfig) {
        if (!(collection instanceof JSONArray)) {
            return element((Collection) _fromCollection(collection, jsonConfig));
        }
        this.elements.add(collection);
        return this;
    }

    public JSONArray element(double d) {
        Double d2 = new Double(d);
        JSONUtils.testValidity(d2);
        return element((Object) d2);
    }

    public JSONArray element(int i) {
        return element((Object) new Integer(i));
    }

    public JSONArray element(int i, boolean z) {
        return element(i, (Object) z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray element(int i, Collection collection) {
        return element(i, collection, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSONArray element(int r4, java.util.Collection r5, net.sf.json.JsonConfig r6) {
        /*
            r3 = this;
            boolean r0 = r5 instanceof net.sf.json.JSONArray
            if (r0 == 0) goto L_0x0043
            if (r4 >= 0) goto L_0x0025
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0025:
            int r0 = r3.size()
            if (r4 >= r0) goto L_0x0031
            java.util.List r0 = r3.elements
            r0.set(r4, r5)
        L_0x0030:
            return r3
        L_0x0031:
            int r0 = r3.size()
            if (r4 == r0) goto L_0x003f
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            r3.element(r0)
            goto L_0x0031
        L_0x003f:
            r3.element(r5, r6)
            goto L_0x0030
        L_0x0043:
            net.sf.json.JSONArray r0 = _fromCollection(r5, r6)
            net.sf.json.JSONArray r3 = r3.element(r4, r0)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.element(int, java.util.Collection, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    public JSONArray element(int i, double d) {
        return element(i, (Object) new Double(d));
    }

    public JSONArray element(int i, int i2) {
        return element(i, (Object) new Integer(i2));
    }

    public JSONArray element(int i, long j) {
        return element(i, (Object) new Long(j));
    }

    public JSONArray element(int i, Map map) {
        return element(i, map, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v6, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSONArray element(int r4, java.util.Map r5, net.sf.json.JsonConfig r6) {
        /*
            r3 = this;
            boolean r0 = r5 instanceof net.sf.json.JSONObject
            if (r0 == 0) goto L_0x0043
            if (r4 >= 0) goto L_0x0025
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0025:
            int r0 = r3.size()
            if (r4 >= r0) goto L_0x0031
            java.util.List r0 = r3.elements
            r0.set(r4, r5)
        L_0x0030:
            return r3
        L_0x0031:
            int r0 = r3.size()
            if (r4 == r0) goto L_0x003f
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            r3.element(r0)
            goto L_0x0031
        L_0x003f:
            r3.element(r5, r6)
            goto L_0x0030
        L_0x0043:
            net.sf.json.JSONObject r0 = net.sf.json.JSONObject.fromObject(r5, r6)
            net.sf.json.JSONArray r3 = r3.element(r4, r0)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.element(int, java.util.Map, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    public JSONArray element(int i, Object obj) {
        return element(i, obj, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public net.sf.json.JSONArray element(int r4, java.lang.Object r5, net.sf.json.JsonConfig r6) {
        /*
            r3 = this;
            net.sf.json.util.JSONUtils.testValidity(r5)
            if (r4 >= 0) goto L_0x0024
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0024:
            int r0 = r3.size()
            if (r4 >= r0) goto L_0x0034
            java.util.List r0 = r3.elements
            java.lang.Object r1 = r3.processValue(r5, r6)
            r0.set(r4, r1)
        L_0x0033:
            return r3
        L_0x0034:
            int r0 = r3.size()
            if (r4 == r0) goto L_0x0042
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            r3.element(r0)
            goto L_0x0034
        L_0x0042:
            r3.element(r5, r6)
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.element(int, java.lang.Object, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    public JSONArray element(int i, String str) {
        return element(i, str, new JsonConfig());
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v9, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSONArray element(int r4, java.lang.String r5, net.sf.json.JsonConfig r6) {
        /*
            r3 = this;
            if (r4 >= 0) goto L_0x0021
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0021:
            int r0 = r3.size()
            if (r4 >= r0) goto L_0x0056
            if (r5 != 0) goto L_0x0031
            java.util.List r0 = r3.elements
            java.lang.String r1 = ""
            r0.set(r4, r1)
        L_0x0030:
            return r3
        L_0x0031:
            boolean r0 = net.sf.json.util.JSONUtils.mayBeJSON(r5)
            if (r0 == 0) goto L_0x004c
            java.util.List r0 = r3.elements     // Catch:{ JSONException -> 0x0041 }
            net.sf.json.JSON r1 = net.sf.json.JSONSerializer.toJSON(r5, r6)     // Catch:{ JSONException -> 0x0041 }
            r0.set(r4, r1)     // Catch:{ JSONException -> 0x0041 }
            goto L_0x0030
        L_0x0041:
            r0 = move-exception
            java.util.List r0 = r3.elements
            java.lang.String r1 = net.sf.json.util.JSONUtils.stripQuotes(r5)
            r0.set(r4, r1)
            goto L_0x0030
        L_0x004c:
            java.util.List r0 = r3.elements
            java.lang.String r1 = net.sf.json.util.JSONUtils.stripQuotes(r5)
            r0.set(r4, r1)
            goto L_0x0030
        L_0x0056:
            int r0 = r3.size()
            if (r4 == r0) goto L_0x0064
            net.sf.json.JSONNull r0 = net.sf.json.JSONNull.getInstance()
            r3.element(r0)
            goto L_0x0056
        L_0x0064:
            r3.element(r5, r6)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.element(int, java.lang.String, net.sf.json.JsonConfig):net.sf.json.JSONArray");
    }

    public JSONArray element(JSONNull jSONNull) {
        this.elements.add(jSONNull);
        return this;
    }

    public JSONArray element(JSONObject jSONObject) {
        this.elements.add(jSONObject);
        return this;
    }

    public JSONArray element(long j) {
        return element((Object) JSONUtils.transformNumber(new Long(j)));
    }

    public JSONArray element(Map map) {
        return element(map, new JsonConfig());
    }

    public JSONArray element(Map map, JsonConfig jsonConfig) {
        if (!(map instanceof JSONObject)) {
            return element(JSONObject.fromObject(map, jsonConfig));
        }
        this.elements.add(map);
        return this;
    }

    public JSONArray element(Object obj) {
        return element(obj, new JsonConfig());
    }

    public JSONArray element(Object obj, JsonConfig jsonConfig) {
        return addValue(obj, jsonConfig);
    }

    public JSONArray element(String str) {
        return element(str, new JsonConfig());
    }

    public JSONArray element(String str, JsonConfig jsonConfig) {
        if (str == null) {
            this.elements.add("");
        } else if (JSONUtils.mayBeJSON(str)) {
            try {
                this.elements.add(JSONSerializer.toJSON((Object) str, jsonConfig));
            } catch (JSONException e) {
                this.elements.add(JSONUtils.stripQuotes(str));
            }
        } else {
            this.elements.add(JSONUtils.stripQuotes(str));
        }
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        JSONArray jSONArray = (JSONArray) obj;
        if (jSONArray.size() != size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            Object obj2 = get(i);
            Object obj3 = jSONArray.get(i);
            if (JSONNull.getInstance().equals(obj2)) {
                if (!JSONNull.getInstance().equals(obj3)) {
                    return false;
                }
            } else if (JSONNull.getInstance().equals(obj3)) {
                return false;
            } else {
                if ((obj2 instanceof JSONArray) && (obj3 instanceof JSONArray)) {
                    if (!((JSONArray) obj3).equals((JSONArray) obj2)) {
                        return false;
                    }
                } else if (!(obj2 instanceof String) || !(obj3 instanceof JSONFunction)) {
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

    public Object get(int i) {
        return this.elements.get(i);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getBoolean(int r4) {
        /*
            r3 = this;
            java.lang.Object r1 = r3.get(r4)
            if (r1 == 0) goto L_0x0037
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x001d
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x001f
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "false"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x001f
        L_0x001d:
            r0 = 0
        L_0x001e:
            return r0
        L_0x001f:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0035
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x0037
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "true"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0037
        L_0x0035:
            r0 = 1
            goto L_0x001e
        L_0x0037:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a Boolean."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getBoolean(int):boolean");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getDouble(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x0038
            boolean r1 = r0 instanceof java.lang.Number     // Catch:{ Exception -> 0x0018 }
            if (r1 == 0) goto L_0x0011
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ Exception -> 0x0018 }
            double r0 = r0.doubleValue()     // Catch:{ Exception -> 0x0018 }
        L_0x0010:
            return r0
        L_0x0011:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0018 }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x0018 }
            goto L_0x0010
        L_0x0018:
            r0 = move-exception
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0038:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getDouble(int):double");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 19
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
    public int getInt(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x0017
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0011
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
        L_0x0010:
            return r0
        L_0x0011:
            double r0 = r3.getDouble(r4)
            int r0 = (int) r0
            goto L_0x0010
        L_0x0017:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getInt(int):int");
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public net.sf.json.JSONArray getJSONArray(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x000d
            boolean r1 = r0 instanceof net.sf.json.JSONArray
            if (r1 == 0) goto L_0x000d
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            return r0
        L_0x000d:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a JSONArray."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getJSONArray(int):net.sf.json.JSONArray");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 19
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
    public net.sf.json.JSONObject getJSONObject(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            net.sf.json.JSONNull r1 = net.sf.json.JSONNull.getInstance()
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0015
            net.sf.json.JSONObject r0 = new net.sf.json.JSONObject
            r1 = 1
            r0.<init>(r1)
        L_0x0014:
            return r0
        L_0x0015:
            boolean r1 = r0 instanceof net.sf.json.JSONObject
            if (r1 == 0) goto L_0x001c
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0
            goto L_0x0014
        L_0x001c:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a JSONObject."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getJSONObject(int):net.sf.json.JSONObject");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 19
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
    public long getLong(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x0017
            boolean r1 = r0 instanceof java.lang.Number
            if (r1 == 0) goto L_0x0011
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
        L_0x0010:
            return r0
        L_0x0011:
            double r0 = r3.getDouble(r4)
            long r0 = (long) r0
            goto L_0x0010
        L_0x0017:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] is not a number."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getLong(int):long");
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    public java.lang.String getString(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.get(r4)
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = r0.toString()
            return r0
        L_0x000b:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "JSONArray["
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r2 = "] not found."
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.getString(int):java.lang.String");
    }

    public int hashCode() {
        int i = 29;
        for (Object hashCode : this.elements) {
            i += JSONUtils.hashCode(hashCode);
        }
        return i;
    }

    public int indexOf(Object obj) {
        return this.elements.indexOf(obj);
    }

    public boolean isArray() {
        return true;
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public boolean isExpandElements() {
        return this.expandElements;
    }

    public Iterator iterator() {
        return new JSONArrayListIterator(this);
    }

    public String join(String str) {
        return join(str, false);
    }

    public String join(String str, boolean z) {
        int size = size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            String valueToString = JSONUtils.valueToString(this.elements.get(i));
            if (z) {
                valueToString = JSONUtils.stripQuotes(valueToString);
            }
            stringBuffer.append(valueToString);
        }
        return stringBuffer.toString();
    }

    public int lastIndexOf(Object obj) {
        return this.elements.lastIndexOf(obj);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(int i) {
        if (i >= 0 && i <= size()) {
            return new JSONArrayListIterator(this, i);
        }
        throw new IndexOutOfBoundsException(new StringBuffer().append("Index: ").append(i).toString());
    }

    public Object opt(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }
        return this.elements.get(i);
    }

    public boolean optBoolean(int i) {
        return optBoolean(i, false);
    }

    public boolean optBoolean(int i, boolean z) {
        try {
            return getBoolean(i);
        } catch (Exception e) {
            return z;
        }
    }

    public double optDouble(int i) {
        return optDouble(i, Double.NaN);
    }

    public double optDouble(int i, double d) {
        try {
            return getDouble(i);
        } catch (Exception e) {
            return d;
        }
    }

    public int optInt(int i) {
        return optInt(i, 0);
    }

    public int optInt(int i, int i2) {
        try {
            return getInt(i);
        } catch (Exception e) {
            return i2;
        }
    }

    public JSONArray optJSONArray(int i) {
        Object opt = opt(i);
        if (opt instanceof JSONArray) {
            return (JSONArray) opt;
        }
        return null;
    }

    public JSONObject optJSONObject(int i) {
        Object opt = opt(i);
        if (opt instanceof JSONObject) {
            return (JSONObject) opt;
        }
        return null;
    }

    public long optLong(int i) {
        return optLong(i, 0);
    }

    public long optLong(int i, long j) {
        try {
            return getLong(i);
        } catch (Exception e) {
            return j;
        }
    }

    public String optString(int i) {
        return optString(i, "");
    }

    public String optString(int i, String str) {
        Object opt = opt(i);
        return opt != null ? opt.toString() : str;
    }

    public Object remove(int i) {
        return this.elements.remove(i);
    }

    public boolean remove(Object obj) {
        return this.elements.remove(obj);
    }

    public boolean removeAll(Collection collection) {
        return removeAll(collection, new JsonConfig());
    }

    public boolean removeAll(Collection collection, JsonConfig jsonConfig) {
        return this.elements.removeAll(fromObject(collection, jsonConfig));
    }

    public boolean retainAll(Collection collection) {
        return retainAll(collection, new JsonConfig());
    }

    public boolean retainAll(Collection collection, JsonConfig jsonConfig) {
        return this.elements.retainAll(fromObject(collection, jsonConfig));
    }

    public Object set(int i, Object obj) {
        return set(i, obj, new JsonConfig());
    }

    public Object set(int i, Object obj, JsonConfig jsonConfig) {
        Object obj2 = get(i);
        element(i, obj, jsonConfig);
        return obj2;
    }

    public void setExpandElements(boolean z) {
        this.expandElements = z;
    }

    public int size() {
        return this.elements.size();
    }

    public List subList(int i, int i2) {
        return this.elements.subList(i, i2);
    }

    public Object[] toArray() {
        return this.elements.toArray();
    }

    public Object[] toArray(Object[] objArr) {
        return this.elements.toArray(objArr);
    }

    public JSONObject toJSONObject(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.size() == 0 || size() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < jSONArray.size(); i++) {
            jSONObject.element(jSONArray.getString(i), opt(i));
        }
        return jSONObject;
    }

    public String toString() {
        try {
            return new StringBuffer().append('[').append(join(StorageInterface.KEY_SPLITER)).append(']').toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String toString(int i) {
        if (i == 0) {
            return toString();
        }
        return toString(i, 0);
    }

    public String toString(int i, int i2) {
        int size = size();
        if (size == 0) {
            return "[]";
        }
        if (i == 0) {
            return toString();
        }
        StringBuffer stringBuffer = new StringBuffer("[");
        if (size == 1) {
            stringBuffer.append(JSONUtils.valueToString(this.elements.get(0), i, i2));
        } else {
            int i3 = i2 + i;
            stringBuffer.append(10);
            for (int i4 = 0; i4 < size; i4++) {
                if (i4 > 0) {
                    stringBuffer.append(",\n");
                }
                for (int i5 = 0; i5 < i3; i5++) {
                    stringBuffer.append(' ');
                }
                stringBuffer.append(JSONUtils.valueToString(this.elements.get(i4), i, i3));
            }
            stringBuffer.append(10);
            for (int i6 = 0; i6 < i2; i6++) {
                stringBuffer.append(' ');
            }
            for (int i7 = 0; i7 < i2; i7++) {
                stringBuffer.insert(0, ' ');
            }
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.Throwable, net.sf.json.JSONException]
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.Writer write(java.io.Writer r5) {
        /*
            r4 = this;
            r0 = 0
            int r3 = r4.size()     // Catch:{ IOException -> 0x0033 }
            r1 = 91
            r5.write(r1)     // Catch:{ IOException -> 0x0033 }
            r1 = r0
        L_0x000b:
            if (r1 >= r3) goto L_0x0042
            if (r0 == 0) goto L_0x0014
            r0 = 44
            r5.write(r0)     // Catch:{ IOException -> 0x0033 }
        L_0x0014:
            java.util.List r0 = r4.elements     // Catch:{ IOException -> 0x0033 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ IOException -> 0x0033 }
            boolean r2 = r0 instanceof net.sf.json.JSONObject     // Catch:{ IOException -> 0x0033 }
            if (r2 == 0) goto L_0x0029
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0     // Catch:{ IOException -> 0x0033 }
            r0.write(r5)     // Catch:{ IOException -> 0x0033 }
        L_0x0023:
            r2 = 1
            int r0 = r1 + 1
            r1 = r0
            r0 = r2
            goto L_0x000b
        L_0x0029:
            boolean r2 = r0 instanceof net.sf.json.JSONArray     // Catch:{ IOException -> 0x0033 }
            if (r2 == 0) goto L_0x003a
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0     // Catch:{ IOException -> 0x0033 }
            r0.write(r5)     // Catch:{ IOException -> 0x0033 }
            goto L_0x0023
        L_0x0033:
            r0 = move-exception
            net.sf.json.JSONException r1 = new net.sf.json.JSONException
            r1.<init>(r0)
            throw r1
        L_0x003a:
            java.lang.String r0 = net.sf.json.util.JSONUtils.valueToString(r0)     // Catch:{ IOException -> 0x0033 }
            r5.write(r0)     // Catch:{ IOException -> 0x0033 }
            goto L_0x0023
        L_0x0042:
            r0 = 93
            r5.write(r0)     // Catch:{ IOException -> 0x0033 }
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.write(java.io.Writer):java.io.Writer");
    }

    /* access modifiers changed from: protected */
    public JSONArray addString(String str) {
        if (str != null) {
            this.elements.add(str);
        }
        return this;
    }

    private JSONArray _addValue(Object obj, JsonConfig jsonConfig) {
        this.elements.add(_processValue(obj, jsonConfig));
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r0.isAssignableFrom(r3.getClass()) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object _processValue(java.lang.Object r3, net.sf.json.JsonConfig r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0018
            java.lang.Class r0 = class$java$lang$Class
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "java.lang.Class"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Class = r0
        L_0x000e:
            java.lang.Class r1 = r3.getClass()
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L_0x001c
        L_0x0018:
            boolean r0 = r3 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0026
        L_0x001c:
            java.lang.Class r3 = (java.lang.Class) r3
            java.lang.String r3 = r3.getName()
        L_0x0022:
            return r3
        L_0x0023:
            java.lang.Class r0 = class$java$lang$Class
            goto L_0x000e
        L_0x0026:
            boolean r0 = net.sf.json.util.JSONUtils.isFunction(r3)
            if (r0 == 0) goto L_0x0037
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L_0x0022
            java.lang.String r3 = (java.lang.String) r3
            net.sf.json.JSONFunction r3 = net.sf.json.JSONFunction.parse(r3)
            goto L_0x0022
        L_0x0037:
            boolean r0 = r3 instanceof net.sf.json.JSONString
            if (r0 == 0) goto L_0x0042
            net.sf.json.JSONString r3 = (net.sf.json.JSONString) r3
            net.sf.json.JSON r3 = net.sf.json.JSONSerializer.toJSON(r3, r4)
            goto L_0x0022
        L_0x0042:
            boolean r0 = r3 instanceof net.sf.json.JSON
            if (r0 == 0) goto L_0x004b
            net.sf.json.JSON r3 = net.sf.json.JSONSerializer.toJSON(r3, r4)
            goto L_0x0022
        L_0x004b:
            boolean r0 = net.sf.json.util.JSONUtils.isArray(r3)
            if (r0 == 0) goto L_0x0056
            net.sf.json.JSONArray r3 = fromObject(r3, r4)
            goto L_0x0022
        L_0x0056:
            boolean r0 = r3 instanceof net.sf.json.util.JSONTokener
            if (r0 == 0) goto L_0x0061
            net.sf.json.util.JSONTokener r3 = (net.sf.json.util.JSONTokener) r3
            net.sf.json.JSONArray r3 = _fromJSONTokener(r3, r4)
            goto L_0x0022
        L_0x0061:
            boolean r0 = net.sf.json.util.JSONUtils.isString(r3)
            if (r0 == 0) goto L_0x007c
            java.lang.String r3 = java.lang.String.valueOf(r3)
            boolean r0 = net.sf.json.util.JSONUtils.mayBeJSON(r3)
            if (r0 == 0) goto L_0x0022
            net.sf.json.JSON r3 = net.sf.json.JSONSerializer.toJSON(r3, r4)     // Catch:{ JSONException -> 0x0076 }
            goto L_0x0022
        L_0x0076:
            r0 = move-exception
            java.lang.String r3 = net.sf.json.util.JSONUtils.stripQuotes(r3)
            goto L_0x0022
        L_0x007c:
            boolean r0 = net.sf.json.util.JSONUtils.isNumber(r3)
            if (r0 == 0) goto L_0x008c
            net.sf.json.util.JSONUtils.testValidity(r3)
            java.lang.Number r3 = (java.lang.Number) r3
            java.lang.Number r3 = net.sf.json.util.JSONUtils.transformNumber(r3)
            goto L_0x0022
        L_0x008c:
            boolean r0 = net.sf.json.util.JSONUtils.isBoolean(r3)
            if (r0 != 0) goto L_0x0022
            net.sf.json.JSONObject r3 = net.sf.json.JSONObject.fromObject(r3, r4)
            boolean r0 = r3.isNullObject()
            if (r0 == 0) goto L_0x0022
            net.sf.json.JSONNull r3 = net.sf.json.JSONNull.getInstance()
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray._processValue(java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }

    private JSONArray addValue(Object obj, JsonConfig jsonConfig) {
        return _addValue(processValue(obj, jsonConfig), jsonConfig);
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object processValue(java.lang.Object r4, net.sf.json.JsonConfig r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x002f
            java.lang.Class r0 = r4.getClass()
            net.sf.json.processors.JsonValueProcessor r0 = r5.findJsonValueProcessor(r0)
            if (r0 == 0) goto L_0x002f
            java.lang.Object r4 = r0.processArrayValue(r4, r5)
            boolean r0 = net.sf.json.processors.JsonVerifier.isValidJsonValue(r4)
            if (r0 != 0) goto L_0x002f
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Value is not a valid JSON value. "
            java.lang.StringBuffer r1 = r1.append(r2)
            java.lang.StringBuffer r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x002f:
            java.lang.Object r0 = r3._processValue(r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.JSONArray.processValue(java.lang.Object, net.sf.json.JsonConfig):java.lang.Object");
    }
}
