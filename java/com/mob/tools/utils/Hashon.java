package com.mob.tools.utils;

import com.mob.tools.MobLog;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class Hashon {

    public interface TabulateAdapter {
        Object tabulate();
    }

    public <T> HashMap<String, T> fromJson(String str) {
        if (str == null || str.isEmpty()) {
            return new HashMap<>();
        }
        try {
            if (str.startsWith("[") && str.endsWith("]")) {
                str = "{\"fakelist\":" + str + "}";
            }
            return fromJson(new JSONObject(str));
        } catch (Throwable th) {
            MobLog.getInstance().w(str);
            MobLog.getInstance().w(th);
            return new HashMap<>();
        }
    }

    private <T> HashMap<String, T> fromJson(JSONObject jSONObject) throws Throwable {
        HashMap<String, T> hashMap = new HashMap<>();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (JSONObject.NULL.equals(opt)) {
                opt = null;
            }
            if (opt != null) {
                if (opt instanceof JSONObject) {
                    opt = fromJson((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = fromJson((JSONArray) opt);
                }
                hashMap.put(str, opt);
            }
        }
        return hashMap;
    }

    private ArrayList<Object> fromJson(JSONArray jSONArray) throws Throwable {
        ArrayList<Object> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                opt = fromJson((JSONObject) opt);
            } else if (opt instanceof JSONArray) {
                opt = fromJson((JSONArray) opt);
            }
            arrayList.add(opt);
        }
        return arrayList;
    }

    public <T> String fromHashMap(HashMap<String, T> hashMap) {
        try {
            JSONObject jSONObject = getJSONObject(hashMap);
            if (jSONObject == null) {
                return "";
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "";
        }
    }

    private <T> JSONObject getJSONObject(HashMap<String, T> hashMap) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                value = getJSONObject((HashMap) value);
            } else if (value instanceof ArrayList) {
                value = getJSONArray((ArrayList) value);
            } else if (isBasicArray(value)) {
                value = getJSONArray(arrayToList(value));
            }
            jSONObject.put((String) entry.getKey(), value);
        }
        return jSONObject;
    }

    private boolean isBasicArray(Object obj) {
        return (obj instanceof byte[]) || (obj instanceof short[]) || (obj instanceof int[]) || (obj instanceof long[]) || (obj instanceof float[]) || (obj instanceof double[]) || (obj instanceof char[]) || (obj instanceof boolean[]) || (obj instanceof String[]);
    }

    private ArrayList<?> arrayToList(Object obj) {
        int i = 0;
        if (obj instanceof byte[]) {
            ArrayList<?> arrayList = new ArrayList<>();
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            while (i < length) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof short[]) {
            ArrayList<?> arrayList2 = new ArrayList<>();
            short[] sArr = (short[]) obj;
            int length2 = sArr.length;
            while (i < length2) {
                arrayList2.add(Short.valueOf(sArr[i]));
                i++;
            }
            return arrayList2;
        } else if (obj instanceof int[]) {
            ArrayList<?> arrayList3 = new ArrayList<>();
            int[] iArr = (int[]) obj;
            int length3 = iArr.length;
            while (i < length3) {
                arrayList3.add(Integer.valueOf(iArr[i]));
                i++;
            }
            return arrayList3;
        } else if (obj instanceof long[]) {
            ArrayList<?> arrayList4 = new ArrayList<>();
            long[] jArr = (long[]) obj;
            int length4 = jArr.length;
            while (i < length4) {
                arrayList4.add(Long.valueOf(jArr[i]));
                i++;
            }
            return arrayList4;
        } else if (obj instanceof float[]) {
            ArrayList<?> arrayList5 = new ArrayList<>();
            float[] fArr = (float[]) obj;
            int length5 = fArr.length;
            while (i < length5) {
                arrayList5.add(Float.valueOf(fArr[i]));
                i++;
            }
            return arrayList5;
        } else if (obj instanceof double[]) {
            ArrayList<?> arrayList6 = new ArrayList<>();
            double[] dArr = (double[]) obj;
            int length6 = dArr.length;
            while (i < length6) {
                arrayList6.add(Double.valueOf(dArr[i]));
                i++;
            }
            return arrayList6;
        } else if (obj instanceof char[]) {
            ArrayList<?> arrayList7 = new ArrayList<>();
            char[] cArr = (char[]) obj;
            int length7 = cArr.length;
            while (i < length7) {
                arrayList7.add(Character.valueOf(cArr[i]));
                i++;
            }
            return arrayList7;
        } else if (obj instanceof boolean[]) {
            ArrayList<?> arrayList8 = new ArrayList<>();
            boolean[] zArr = (boolean[]) obj;
            int length8 = zArr.length;
            while (i < length8) {
                arrayList8.add(Boolean.valueOf(zArr[i]));
                i++;
            }
            return arrayList8;
        } else if (obj instanceof String[]) {
            return new ArrayList<>(Arrays.asList((String[]) obj));
        } else {
            return null;
        }
    }

    private JSONArray getJSONArray(ArrayList<Object> arrayList) throws Throwable {
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof HashMap) {
                next = getJSONObject((HashMap) next);
            } else if (next instanceof ArrayList) {
                next = getJSONArray((ArrayList) next);
            }
            jSONArray.put(next);
        }
        return jSONArray;
    }

    public String format(String str) {
        try {
            return format("", fromJson(str));
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "";
        }
    }

    private String format(String str, HashMap<String, Object> hashMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        String str2 = str + "\t";
        int i = 0;
        Iterator it = hashMap.entrySet().iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (i2 > 0) {
                    sb.append(",\n");
                }
                sb.append(str2).append('\"').append((String) entry.getKey()).append("\":");
                Object value = entry.getValue();
                if (value instanceof HashMap) {
                    sb.append(format(str2, (HashMap) value));
                } else if (value instanceof ArrayList) {
                    sb.append(format(str2, (ArrayList) value));
                } else if (value instanceof String) {
                    sb.append('\"').append(value).append('\"');
                } else {
                    sb.append(value);
                }
                i = i2 + 1;
            } else {
                sb.append(10).append(str).append('}');
                return sb.toString();
            }
        }
    }

    private String format(String str, ArrayList<Object> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        String str2 = str + "\t";
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                Object next = it.next();
                if (i2 > 0) {
                    sb.append(",\n");
                }
                sb.append(str2);
                if (next instanceof HashMap) {
                    sb.append(format(str2, (HashMap) next));
                } else if (next instanceof ArrayList) {
                    sb.append(format(str2, (ArrayList) next));
                } else if (next instanceof String) {
                    sb.append('\"').append(next).append('\"');
                } else {
                    sb.append(next);
                }
                i = i2 + 1;
            } else {
                sb.append(10).append(str).append(']');
                return sb.toString();
            }
        }
    }

    public String fromObject(Object obj) {
        Object obj2 = null;
        try {
            obj2 = tabulate(obj);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        if (obj2 == null) {
            return "";
        }
        if (!(obj2 instanceof ArrayList)) {
            return fromHashMap((HashMap) obj2);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("list", obj2);
        String fromHashMap = fromHashMap(hashMap);
        return fromHashMap.substring("{\"list\":".length(), fromHashMap.length() - 1).trim();
    }

    private Object tabulate(Object obj) throws Throwable {
        Field[] declaredFields;
        if (obj == null || obj.getClass().isPrimitive() || (obj instanceof String) || (obj instanceof Number) || (obj instanceof Character) || (obj instanceof Boolean)) {
            return obj;
        }
        if (obj instanceof TabulateAdapter) {
            return tabulate(((TabulateAdapter) obj).tabulate());
        }
        if (obj instanceof Enum) {
            HashMap hashMap = new HashMap();
            hashMap.put("enum", ((Enum) obj).name());
            return hashMap;
        } else if (obj.getClass().isArray()) {
            ArrayList arrayList = new ArrayList();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                arrayList.add(tabulate(Array.get(obj, i)));
            }
            return arrayList;
        } else if (obj instanceof Collection) {
            ArrayList arrayList2 = new ArrayList();
            for (Object tabulate : (Collection) obj) {
                arrayList2.add(tabulate(tabulate));
            }
            return arrayList2;
        } else if (obj instanceof Map) {
            HashMap hashMap2 = new HashMap();
            for (Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    hashMap2.put((String) key, tabulate(entry.getValue()));
                }
            }
            return hashMap2;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (Class cls = obj.getClass(); !cls.equals(Object.class); cls = cls.getSuperclass()) {
                arrayList3.add(0, cls);
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                for (Field field : ((Class) it.next()).getDeclaredFields()) {
                    if (!Modifier.isStatic(field.getModifiers()) && !field.getName().contains("$")) {
                        arrayList4.add(field);
                    }
                }
            }
            HashMap hashMap3 = new HashMap();
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                Field field2 = (Field) it2.next();
                field2.setAccessible(true);
                hashMap3.put(field2.getName(), tabulate(field2.get(obj)));
            }
            return hashMap3;
        }
    }

    public <T> T fromJson(String str, Class<T> cls) {
        Object obj;
        Type[] typeArr;
        HashMap fromJson = fromJson(str);
        if (!str.startsWith("[") || !str.endsWith("]")) {
            obj = fromJson;
        } else {
            obj = fromJson.get("fakelist");
        }
        try {
            Type genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            } else {
                typeArr = null;
            }
            return tabulate(obj, cls, typeArr);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private <T> T tabulate(Object obj, Class<T> cls, Type[] typeArr) throws Throwable {
        Type[] typeArr2;
        Field field;
        Type type;
        Type type2;
        Object obj2;
        Object obj3;
        Type type3;
        int i = 0;
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || cls.equals(Character.class)) {
            if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
                return Boolean.valueOf("true".equals(String.valueOf(obj)));
            }
            if (cls.equals(Character.TYPE) || cls.equals(Character.class)) {
                return Character.valueOf(String.valueOf(obj).charAt(0));
            }
            if (cls.equals(Byte.TYPE) || cls.equals(Byte.class)) {
                return Byte.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Short.TYPE) || cls.equals(Short.class)) {
                return Short.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Integer.TYPE) || cls.equals(Integer.class)) {
                return Integer.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Long.TYPE) || cls.equals(Long.class)) {
                return Long.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Float.TYPE) || cls.equals(Float.class)) {
                return Float.valueOf(String.valueOf(obj));
            }
            return Double.valueOf(String.valueOf(obj));
        } else if (TabulateAdapter.class.isAssignableFrom(cls)) {
            try {
                return ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(cls.getName()), "valueOf", obj);
            } catch (Throwable th) {
                return null;
            }
        } else if (cls.equals(String.class) || cls.equals(Boolean.class)) {
            return obj;
        } else {
            if (cls.isEnum()) {
                return Enum.valueOf(cls, String.valueOf(((HashMap) obj).get("enum")));
            }
            if (cls.isArray()) {
                ArrayList arrayList = (ArrayList) obj;
                Class componentType = cls.getComponentType();
                T newInstance = Array.newInstance(componentType, arrayList.size());
                int size = arrayList.size();
                while (i < size) {
                    Array.set(newInstance, i, tabulate(arrayList.get(i), componentType, null));
                    i++;
                }
                return newInstance;
            } else if (Collection.class.isAssignableFrom(cls)) {
                T t = (Collection) cls.newInstance();
                if (typeArr == null || typeArr.length <= 0) {
                    type3 = null;
                } else {
                    type3 = typeArr[0];
                }
                ArrayList arrayList2 = (ArrayList) obj;
                int size2 = arrayList2.size();
                while (i < size2) {
                    if (type3 != null && (type3 instanceof Class) && !type3.equals(Object.class)) {
                        t.add(tabulate(arrayList2.get(i), (Class) type3, null));
                    } else if (type3 == null || !(type3 instanceof ParameterizedType)) {
                        t.add(arrayList2.get(i));
                    } else {
                        ParameterizedType parameterizedType = (ParameterizedType) type3;
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        t.add(tabulate(arrayList2.get(i), (Class) parameterizedType.getRawType(), actualTypeArguments));
                    }
                    i++;
                }
                return t;
            } else if (Map.class.isAssignableFrom(cls)) {
                T t2 = (Map) cls.newInstance();
                if (typeArr == null || typeArr.length <= 1) {
                    type = null;
                    type2 = null;
                } else {
                    type2 = typeArr[0];
                    type = typeArr[1];
                }
                HashMap hashMap = (HashMap) obj;
                for (Object next : hashMap.keySet()) {
                    if (type2 != null && (type2 instanceof Class) && !type.equals(Object.class)) {
                        obj2 = tabulate(next, (Class) type2, null);
                    } else if (type2 == null || !(type2 instanceof ParameterizedType)) {
                        obj2 = next;
                    } else {
                        ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                        obj2 = tabulate(next, (Class) parameterizedType2.getRawType(), parameterizedType2.getActualTypeArguments());
                    }
                    if (type != null && (type instanceof Class) && !type.equals(Object.class)) {
                        obj3 = tabulate(hashMap.get(next), (Class) type, null);
                    } else if (type == null || !(type instanceof ParameterizedType)) {
                        obj3 = hashMap.get(next);
                    } else {
                        ParameterizedType parameterizedType3 = (ParameterizedType) type;
                        Type[] actualTypeArguments2 = parameterizedType3.getActualTypeArguments();
                        obj3 = tabulate(hashMap.get(next), (Class) parameterizedType3.getRawType(), actualTypeArguments2);
                    }
                    t2.put(obj2, obj3);
                }
                return t2;
            } else {
                ArrayList arrayList3 = new ArrayList();
                for (Class<T> cls2 = cls; !cls2.equals(Object.class); cls2 = cls2.getSuperclass()) {
                    arrayList3.add(cls2);
                }
                HashMap hashMap2 = (HashMap) obj;
                HashMap hashMap3 = new HashMap();
                for (String str : hashMap2.keySet()) {
                    if (hashMap2.get(str) != null) {
                        Iterator it = arrayList3.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            try {
                                field = ((Class) it.next()).getDeclaredField(str);
                                continue;
                            } catch (Throwable th2) {
                                field = null;
                                continue;
                            }
                            if (field != null) {
                                hashMap3.put(str, field);
                                break;
                            }
                        }
                    }
                }
                Object newInstance2 = ReflectHelper.newInstance(ReflectHelper.getName(cls), new Object[0]);
                for (String str2 : hashMap3.keySet()) {
                    Object obj4 = hashMap2.get(str2);
                    Field field2 = (Field) hashMap3.get(str2);
                    Class type4 = field2.getType();
                    Type genericType = field2.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        typeArr2 = ((ParameterizedType) genericType).getActualTypeArguments();
                    } else {
                        typeArr2 = null;
                    }
                    field2.setAccessible(true);
                    field2.set(newInstance2, tabulate(obj4, type4, typeArr2));
                }
                return newInstance2;
            }
        }
    }
}
